
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.atributos.Atributos;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;


public class InicioSesionController implements Initializable{
    
    private Sabga ventanaPrincipal;  
    
    @FXML 
    private TextField campoUsuario;    
    @FXML 
    private PasswordField campoContrasenia;    
    @FXML 
    private Label validarUsuario, validacion;    
    @FXML 
    private ComboBox<String> comboTipoAdmin;
    private final Consultas consulta;
    private final Atributos atributos;
 
    public InicioSesionController(){                
        consulta = new Consultas();
        atributos = new Atributos();
    }
   
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {        
	this.ventanaPrincipal = ventanaPrincipal;
    }
     
    @FXML
    public void iniciarSesion(ActionEvent evento){          
        inicioSesion();        
    }
   
    private void inicioSesion(){
    
        mensajesUsuario();        
        ConfirmarUsuario confirmar = new ConfirmarUsuario();        
        if(confirmar.confirmarUsuario(campoUsuario.getText().trim(), campoContrasenia.getText().trim())){
            
            if(consulta.consultarUsuario(campoUsuario.getText().trim(), Utilidades.encriptar(campoContrasenia.getText().trim()),
                                         comboTipoAdmin.getSelectionModel().getSelectedItem().toString())==1){
                consulta.getDatosBibliotecario(campoUsuario.getText().trim());
                if (consulta.getMensaje() == null) {
                    atributos.setUsuarioAdmin(campoUsuario.getText().trim());
                    atributos.setDocumento(consulta.getDocumento());
                    atributos.setNombre(consulta.getNombre());
                    atributos.setApellido(consulta.getApellido());
                    atributos.setTipoUsuario(comboTipoAdmin.getSelectionModel().getSelectedItem().toString().toLowerCase());
                    this.ventanaPrincipal.inciarSesion();
                    campoUsuario.clear();
                    campoContrasenia.clear();
                }else{
                validacion.setText("No se ha podido acceder a la aplicación.");
            } 
            }
            else{
                validacion.setText("El nombre de usuario o la contraseña introducidos no son correctos.");
                campoContrasenia.clear();
            }           
        }
    }
      
    @FXML
    public void restablecerContrasenia(ActionEvent evento){
       this.ventanaPrincipal.dialogoRestablecerContrasenia();
       
    }
    
    public void mensajesUsuario(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarUsuario(campoUsuario.getText(), campoContrasenia.getText());
        validarUsuario.setText(validar.getErrorNombreUsuario());
        validacion.setText(validar.getErrorContrasenia());
    }
    
    private void inicio(){
       try{
           comboTipoAdmin.setItems(consulta.llenarLista(11));
       }
        catch(Exception ex){            
           atributos.setEstadoBaseDatos(0);
       }
        comboTipoAdmin.getSelectionModel().selectFirst();
    }
       
    @FXML
    public void salir(ActionEvent evento){        
        System.exit(0);
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        inicio();
        
    }    
    
}
