
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
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;


public class InicioSesionController implements Initializable{
    
    private Sabga ventanaPrincipal;  
    
    @FXML private TextField campoUsuario;
    
    @FXML private PasswordField campoContrasenia;
    
    @FXML private Label validarUsuario, validacion;
    
    @FXML private ComboBox comboTipoAdmin;
 
   
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    }
     
    @FXML
    public void iniciarSesion(ActionEvent evento){
        
        validarUsuario();        
        ConfirmarUsuario confirmar = new ConfirmarUsuario();
        
        if(confirmar.confirmarUsuario(campoUsuario.getText(), campoContrasenia.getText())){
           this.ventanaPrincipal.inciarSesion();
           campoUsuario.clear();
           campoContrasenia.clear();
        }
                 
    }
    
    @FXML
    public void restablecerContrasenia(ActionEvent evento){
        
       this.ventanaPrincipal.dialogoRestablecerContrasenia();
        
    }
    
    public void validarUsuario(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarUsuario(campoUsuario.getText(), campoContrasenia.getText());
        validarUsuario.setText(validar.getErrorNombreUsuario());
        validacion.setText(validar.getErrorContrasenia());
    }
    
    
    @FXML
    public void salir(ActionEvent evento){
        
        System.exit(0);
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        comboTipoAdmin.getSelectionModel().selectFirst();
    
    }    
    
    
    
}
