
package sabga.controlador.dialogos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.modelo.Validacion;
import sabga.preferencias.Preferencias;

/**
 * @author Elk1n
 */

public class PreferenciasController implements Initializable {

    private Stage dialogStage;
    private final Preferencias configuracion;
    private final Validacion validar;
    private final String CORREO ="bibliotecagilbertoalzate@outlook.com";
    private final String CLAVE = "biblioteca1958";
    private final String HOST = "smtp-mail.outlook.com";
    private final String PUERTO = "587";
    private final String DIRECCION = "localhost";
    private final String PUERTO_BASE = "8889";
    private final String NOMBRE = "SABGA";
    private final String USUARIO = "root";
    private final String CONTRASENIA = "";    
    private final int NUMERO_MAXIMO_EJEMPLARES = 3;
    private final int VALOR_MULTA = 100;
    private final int BUFFER = 10485760;  
    private final String RUTA = "C:/Wamp/bin/mysql/mysql5.6.12/bin/mysqldump.exe";
    //para guardar en memmoria
    private StringBuffer temp = null;
    //para guardar el archivo SQL
    private FileWriter  fichero = null;
    private PrintWriter pw = null;
       
    @FXML
    private TextField txtfCorreo, txtfPuerto, txtfHost, txtfEjemplares, txtfDireccion, txtfPuertoB, txtfBase, txtfUsuario,
                      txtfMulta, txtfRuta;                    
    @FXML
    private PasswordField pswfClave, txtfContrasenia;
    @FXML
    private Label lblCorreo, lblClave, lblHost, lblPuerto, lblDireccion, lblPuertoB, lblBase, lblUsuario, lblContrasenia,
                  lblEjemplares, lblMulta, lblRuta;
    
    private final Consultas consulta;
    
    public PreferenciasController(){        
        configuracion = new Preferencias();
        validar = new Validacion();  
        consulta = new Consultas();
    }
    
    @FXML
    public void setValoresCorreo(){        
        setValoresPredeterminadosCorreo();
    }
    
    @FXML
    public void setValoresBase(){
        setValoresPredeterminadosBase();
    }
    
    @FXML
    public void setValoresGeneral(){
        setValoresPredeterminadosGeneral();
    }
    
    @FXML
    public void cambiarDatosCorreo(ActionEvent evento){    
        guardarCambios();       
    }
    
    @FXML
    public void cambiarDatosBase(ActionEvent evento){
        guardarCambiosBase();
    }
    
    @FXML
    public void cambiarDatosGeneral(ActionEvent evento){
        guardarCambiosGeneral();
    }
    
    @FXML
    public void cancelar(){        
        dialogStage.close();
    }
   
    @FXML
    public void guardarCopiaSeguridad(ActionEvent evento){
        guardarCopiaSeguridad();
    }
       
    private void guardarCambios(){
    
        if (validar.validarCorreo(txtfCorreo.getText(), 90)) {
            configuracion.setCorreo(txtfCorreo.getText().trim());
            lblCorreo.setText(null);
        } else {
            lblCorreo.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTexto(pswfClave.getText(), 32)) {
            configuracion.setContrasenia(pswfClave.getText().trim());
            lblClave.setText(null);
        } else {
            lblClave.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTexto(txtfHost.getText(), 255)) {
            configuracion.setHost(txtfHost.getText().trim());
            lblHost.setText(null);
        } else {
            lblHost.setText(validar.getMensajeError());
        }
        if (validar.validarNumero(txtfPuerto.getText(), 10)) {
            configuracion.setPuerto(txtfPuerto.getText());
            lblPuerto.setText(null);
        } else {
            lblPuerto.setText(validar.getMensajeError());
        }
        cargarValoresCorreo();
    }
    
    private void guardarCambiosBase(){
    
        if (validar.validarCampoTexto(txtfDireccion.getText(), 255)) {
            configuracion.setDireccionBase(txtfDireccion.getText().trim());
            lblDireccion.setText(null);
        } else {
            lblDireccion.setText(validar.getMensajeError());
        }
        if (validar.validarNumero(txtfPuertoB.getText(), 10)) {
            configuracion.setPuertoBase(txtfPuertoB.getText().trim());
            lblPuertoB.setText(null);
        } else {
            lblPuertoB.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTexto(txtfBase.getText(), 255)) {
            configuracion.setNombreBaseDatos(txtfBase.getText().trim());
            lblBase.setText(null);
        } else {
            lblBase.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTexto(txtfUsuario.getText(), 255)) {
            configuracion.setUsuarioBaseDatos(txtfUsuario.getText());
            lblUsuario.setText(null);
        } else {
            lblUsuario.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTextoNull(txtfContrasenia.getText(), 255)) {
            configuracion.setContraseniaBase(txtfContrasenia.getText());
            lblContrasenia.setText(null);
        } else {
            lblContrasenia.setText(validar.getMensajeError());
        }
        if (validar.validarCampoTexto(txtfRuta.getText(), 255)) {
            configuracion.setRutaAmysqldump(txtfRuta.getText());
            lblRuta.setText(null);
        } else {
            lblRuta.setText(validar.getMensajeError());
        }
        cargarValoresBaseDatos();
    }
    
    private void guardarCambiosGeneral(){
    
        if (validar.validarNumero(txtfEjemplares.getText(), 10)) {
            configuracion.setNuemeroEjemplares(txtfEjemplares.getText().trim());
            lblEjemplares.setText(null);
        } else {
            lblEjemplares.setText(validar.getMensajeError());
        }
        if (validar.validarNumero(txtfMulta.getText(), 10)) {
            consulta.setValorMulta(Integer.parseInt(txtfMulta.getText().trim()));
            lblMulta.setText(null);
        } else {
            lblMulta.setText(validar.getMensajeError());
        }
    }
        
    private void setValoresPredeterminadosCorreo(){
        
        configuracion.setCorreo(CORREO);
        configuracion.setContrasenia(CLAVE);
        configuracion.setHost(HOST);
        configuracion.setPuerto(PUERTO);
        limpiarCamposCorreo();
        cargarValoresCorreo();        
    }
    
    private void setValoresPredeterminadosBase(){
    
        configuracion.setDireccionBase(DIRECCION);
        configuracion.setPuertoBase(PUERTO_BASE);
        configuracion.setNombreBaseDatos(NOMBRE);
        configuracion.setUsuarioBaseDatos(USUARIO);
        configuracion.setContraseniaBase(CONTRASENIA);
        configuracion.setRutaAmysqldump(RUTA);
        limpiarCamposBase();
        cargarValoresBaseDatos();        
    }
    
    private void setValoresPredeterminadosGeneral(){
    
        configuracion.setNuemeroEjemplares(String.valueOf(NUMERO_MAXIMO_EJEMPLARES));
        consulta.setValorMulta(VALOR_MULTA);
        limpiarCamposGeneral();
        cargarValoresGeneral();
    }
        
    private void cargarValoresCorreo(){
        
        txtfCorreo.setText(configuracion.getCorreo());
        pswfClave.setText(configuracion.getContrasenia());
        txtfHost.setText(configuracion.getHost());
        txtfPuerto.setText(configuracion.getPuerto());
    }
    
    private void cargarValoresGeneral(){
        try{
            txtfEjemplares.setText(String.valueOf(configuracion.getNumeroEjemplares()));
            txtfMulta.setText(String.valueOf(consulta.getValorPorMulta()));
        }catch(Exception e){
        
        }     
    }
    
    private void cargarValoresBaseDatos(){
        
        txtfDireccion.setText(configuracion.getDireccionBase());   
        txtfPuertoB.setText(configuracion.getPuertoBaseDatos());
        txtfBase.setText(configuracion.getNombreBaseDatos());
        txtfUsuario.setText(configuracion.getUsuarioBase());
        txtfContrasenia.setText(configuracion.getContraseniaBase());
        txtfRuta.setText(configuracion.getRutaAmysqldump());
    
    }
    
    private void limpiarCamposCorreo(){
        
        lblCorreo.setText(null);
        lblClave.setText(null);
        lblHost.setText(null);
        lblPuerto.setText(null);
    }
    
    private void limpiarCamposBase(){
        
        lblDireccion.setText(null);
        lblPuertoB.setText(null);
        lblBase.setText(null);
        lblUsuario.setText(null);
        lblContrasenia.setText(null);
        lblRuta.setText(null);
    }
    
    private void limpiarCamposGeneral(){
        
        lblEjemplares.setText(null);
        lblMulta.setText(null);
    }
    
    private void guardarCopiaSeguridad() {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar copia de seguridad de la base de datos "+configuracion.getNombreBaseDatos());
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("SQL Files (*.sql)", "*.sql"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String name = file.getName();
            if (name.indexOf(".") == -1) {
                name += ".sql";
                file = new File(file.getParentFile(), name);
            }
            try {
                Process run = Runtime.getRuntime().exec(
                configuracion.getRutaAmysqldump()+
                " --host=" + configuracion.getDireccionBase() + 
                " --port=" + configuracion.getPuertoBaseDatos() +
                " --user=" + configuracion.getUsuarioBase() + 
                " --password=" + configuracion.getContraseniaBase() +
                " --compact --complete-insert --extended-insert --databases" +
                " --default-character-set=utf8 --routines --add-drop-table " +
                configuracion.getNombreBaseDatos());
                //se guarda en memoria el backup
                InputStream in = run.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                temp = new StringBuffer();
                int count;
                char[] cbuf = new char[BUFFER];
                while ((count = br.read(cbuf, 0, BUFFER)) != -1)
                    temp.append(cbuf, 0, count);
                br.close();
                in.close();        
                /* se crea y escribe el archivo SQL */
                fichero = new FileWriter(file);
                pw = new PrintWriter(fichero);                                                    
                pw.println(temp.toString());  
                
            } catch (IOException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar la copia de seguridad", "Error Copia Seguridad");
            }finally{
                try {
                    if (null != fichero){
                        fichero.close();
                    }
                } catch (IOException ex) {
                    Utilidades.mensajeError(null, ex.getMessage(), "Error al cerrar el archivo", "Error Copia Seguridad");                    
                }
            }
        }
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
        
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarValoresCorreo();
        cargarValoresGeneral();
        cargarValoresBaseDatos();
    }    
    
}
