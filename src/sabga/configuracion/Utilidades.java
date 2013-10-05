
package sabga.configuracion;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Dialogs;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.codec.binary.Hex;

/**
 * @author Elk1n
 */

public class Utilidades {
    
   private static Dialogs.DialogResponse mensajeError;
   private static Dialogs.DialogResponse mensajeConfimacion;
   private static Dialogs.DialogResponse mensajeInformacion;
   private static final String NUMEROS = "0123456789";
   private static final String MAYUSCULAS = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
   private static final String MINUSCULAS = "abcdefghijklmnñopqrstuvwxyz";
	 

   public static Dialogs.DialogResponse getMensajeConfimacion() {
       
        return mensajeConfimacion;
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
                        
         mensajeConfimacion = Dialogs.showConfirmDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK_CANCEL);        
    }
    
    public static void mensajeAdvertencia(Stage propietario, String mensanje, String encabezado, String titulo){
    
        mensajeInformacion = Dialogs.showWarningDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);
    }
    
    public static void mensaje(Stage propietario, String mensanje, String encabezado, String titulo){
    
       Dialogs.showInformationDialog(propietario, mensanje, encabezado, titulo);
        
    }
    
    public static String encriptar(String dato){
    
         MessageDigest md;
	 String password = "";
        try {           
            md= MessageDigest.getInstance("SHA-256");
            md.update(dato.getBytes());
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
    
    public static boolean enviarCorreo(String destinatario, String usuario, String clave){
           
        try {
            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "smtp-mail.outlook.com");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.user", "bibliotecagilbertoalzate@outlook.com");
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getDefaultInstance(props);
            
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("bibliotecagilbertoalzate@outlook.com", "Biblioteca Gilberto Alzate"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            message.setSubject("Nueva Contraseña");
            message.setText("Usted a solicitado una nueva clave para el ingreso al sistema SABGA.\n"
                            +"Usuario: "+ usuario+"\n"+"Contraseña: "+clave+"\n\n"+
                             "Despues de ingresar, si lo desea puede cambiar la contraseña por una que pueda recordar fácilmente en el "
                            + "menú administrador submenú confuguración.");

            Transport t = session.getTransport("smtp");

            t.connect("bibliotecagilbertoalzate@outlook.com", "biblioteca1958");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            return true;
        } catch (AddressException e) {
            mensajeError(null,e.getMessage(),"Error en la dirección de correo","Error Enviar Mensaje");
            return false;
        } catch (MessagingException | UnsupportedEncodingException e) {
            mensajeError(null,e.getMessage(),"Error al eviar el mensaje de correo","Error Enviar Mensaje");
            return false;
        }
            
    }
    
}
