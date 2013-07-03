
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;


public class RestablecerContraseniaController implements Initializable {
    
    private Sabga ventanaPrincipal; 

    @FXML private Label validarUsuario, validarDocumento, validarCorreo, validarContrasenia, validarConfirmacion, 
                        etiquetaContrasenia, etiquetaConfirmar;
    
    @FXML private TextField campoUsuario, campoDocumento, campoCorreo;
    
    @FXML private Button botonRestablecer;
    
    @FXML private PasswordField campoContrasenia, campoConfirmacion;
    
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    }
    @FXML
    public void buscarUsuario(ActionEvent evento){
        
        validarDatos();
         ConfirmarUsuario confirmar = new ConfirmarUsuario();
         if(confirmar.confirmarDatosUsuario(campoUsuario.getText(), campoDocumento.getText(), campoCorreo.getText())){
            
            etiquetaContrasenia.setVisible(true);
            etiquetaConfirmar.setVisible(true);
            campoContrasenia.setVisible(true);
            campoConfirmacion.setVisible(true);
            botonRestablecer.setVisible(true);
            campoUsuario.setDisable(true);
            campoDocumento.setDisable(true);
            campoCorreo.setDisable(true);
         }
        
    
    }
    
    @FXML
    public void nuevaContrasenia(ActionEvent evento){
        
        validarNuevaContrasenia();
    }
    
    public void validarDatos(){
        
        ValidarUsuario validarDatos = new ValidarUsuario(campoUsuario.getText(), campoDocumento.getText(), campoCorreo.getText());
        validarDatos.validarRestablecer();
        validarUsuario.setText(validarDatos.getErrorContrasenia());
        validarDocumento.setText(validarDatos.getErrorNuevaContrasenia());
        validarCorreo.setText(validarDatos.getErrorConfirmacion());
      
    }
    
    public void validarNuevaContrasenia(){
        
        ValidarUsuario nuevaContrasenia = new ValidarUsuario(campoContrasenia.getText(), campoConfirmacion.getText());
        
        nuevaContrasenia.validarContrasenia();
        validarContrasenia.setText(nuevaContrasenia.getErrorNuevaContrasenia());
        validarConfirmacion.setText(nuevaContrasenia.getErrorConfirmacion());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        etiquetaContrasenia.setVisible(false);
        etiquetaConfirmar.setVisible(false);
        campoContrasenia.setVisible(false);
        campoConfirmacion.setVisible(false);
        botonRestablecer.setVisible(false);
        
    }    
}
