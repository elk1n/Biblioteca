
package sabga.configuracion;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Hex;
import sabga.preferencias.Preferencias;

/**
 * @author Elk1n
 */

public class Utilidades {
    
   private static DialogResponse mensajeError;
   private static DialogResponse mensajeConfimacion;
   private static DialogResponse mensajeInformacion;
   private static DialogResponse mensajeOpcion;
   private static final String NUMEROS = "0123456789";
   private static final String MAYUSCULAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
   private static final String MINUSCULAS = "abcdefghijklmnñopqrstuvwxyz";
   private static Preferencias pref;
	
   public Utilidades(){
            
   }

    public static String initCap(String string) {

        char[] letras = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < letras.length; i++) {
            if (!found && Character.isLetter(letras[i])) {
                letras[i] = Character.toUpperCase(letras[i]);
                found = true;
            } else if (Character.isWhitespace(letras[i]) || letras[i] == '.' || letras[i] == '\'') { // adicionar otros caracteres aquí
                found = false;
            }
        }
        return String.valueOf(letras);
    }
    
    public static void mensajeError(Stage propietario, String mensanje, String encabezado, String titulo){                        
         mensajeError = Dialogs.showErrorDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);        
    }
    
    public static void mensajeConfirmacion(Stage propietario, String mensanje, String encabezado, String titulo){                        
         mensajeConfimacion = Dialogs.showConfirmDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);        
    }
    
    public static void mensajeOpcion(Stage propietario, String mensanje, String encabezado, String titulo){                        
         mensajeOpcion = Dialogs.showConfirmDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.YES_NO);        
    }
    
    public static void mensajeAdvertencia(Stage propietario, String mensanje, String encabezado, String titulo){    
        mensajeInformacion = Dialogs.showWarningDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);
    }
    
    public static void mensaje(Stage propietario, String mensanje, String encabezado, String titulo){    
       Dialogs.showInformationDialog(propietario, mensanje, encabezado, titulo);
        
    }
    
    public static String encriptar(String dato){
         String contrasenia = dato.trim();
         MessageDigest md;
	 String password = "";
        try {           
            md= MessageDigest.getInstance("SHA-256");
            md.update(contrasenia.getBytes());
            byte[] mb = md.digest();
            password = String.valueOf(Hex.encodeHex(mb));                                  
        } catch (NoSuchAlgorithmException e) {
           mensajeError(null,e.getMessage(),"Error al cifrar la contraseña","Error Encriptación");
        }
        return password;
    }
       
    public static String claveAleatoria(){
           
        String key = NUMEROS + MAYUSCULAS + MINUSCULAS;
        String pswd = "";
        
        for (int i = 0; i < 8; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }
    
    public static boolean enviarCorreo(String destinatario, String asunto, String mensaje){
           
         pref = new Preferencias();          
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", pref.getHost());
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", pref.getPuerto());
            props.setProperty("mail.smtp.user", pref.getCorreo());
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(pref.getCorreo(), "Biblioteca Gilberto Alzate"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject(asunto);
            message.setText(mensaje);

            Transport t = session.getTransport("smtp");

            t.connect(pref.getCorreo(), pref.getContrasenia());
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        } catch (AddressException e) {
            mensajeError(null,e.getMessage(),"Error en la dirección de correo", "Error Enviar Mensaje");
            return false;
        } catch (MessagingException | UnsupportedEncodingException e) {
            mensajeError(null,e.getMessage(),"Error al eviar el mensaje de correo","Error Enviar Mensaje");
            return false;
        }
            
    }
    
    public static int setNumero(String campoTexto){
    
        int numero;
        
        if(campoTexto == null || campoTexto.isEmpty() ){
            numero = 0;
        }else{
            numero = Integer.parseInt(campoTexto);
        }
       return numero;
    }
    
    public static String getDesencadenador(ActionEvent eventos) {
        String objeto = eventos.getSource().toString();
        Pattern patron = Pattern.compile("[id=]([a-zA-Z0-9]+)[,]");
        Matcher matcher = patron.matcher(objeto);
        matcher.find();
        String source = matcher.group(1);
        return source;
    }

    public static DialogResponse getMensajeOpcion() {       
        return mensajeOpcion;
   }
    
    public static DialogResponse getMensajeConfimacion() {
        return mensajeConfimacion;
    }

}
