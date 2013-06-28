
package sabga.controlador;

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
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarUsuario;

/**
 *
 * @author Elk1n
 * 
 */
public class RegistroAdministradorController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private TextField campoNombre, campoApellidos, campoUsuario, campoCorreo, campoDocumento, campoTelefono;
    
    @FXML private PasswordField campoContrasenia, campoContrasenia2;
    
    @FXML private ComboBox comboTipoAdmin;

    @FXML private Label validarNombre, validarApellidos, validarUsuario, validarContrasenia, validarConfirmacion, validarCorreo, validarDocumento,
                        validarTelefono, validarTipoAdmin;

    
     @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
     
    @FXML
     public void validarNuevoAuxiliar(ActionEvent evento) {
   
        ValidarUsuario validarNuevoAdmin = new ValidarUsuario(comboTipoAdmin.getSelectionModel().getSelectedItem(), campoNombre.getText(), campoApellidos.getText(),
                                                              campoUsuario.getText(), campoContrasenia.getText(), campoContrasenia2.getText(), campoCorreo.getText(),
                                                              campoDocumento.getText(), campoTelefono.getText());
        validarNuevoAdmin.validarAdminAxiliar();
        validarTipoAdmin.setText(validarNuevoAdmin.getErrorTipoAdmin());
        validarNombre.setText(validarNuevoAdmin.getErrorNombreUsuario());
        validarApellidos.setText(validarNuevoAdmin.getErrorApellidosUsuario());
        validarUsuario.setText(validarNuevoAdmin.getErrorUsuario());
        validarContrasenia.setText(validarNuevoAdmin.getErrorContrasenia());
        validarConfirmacion.setText(validarNuevoAdmin.getErrorConfirmacion());
        validarCorreo.setText(validarNuevoAdmin.getErrorCorreoUsuario());
        validarDocumento.setText(validarNuevoAdmin.getErrorDocumentoUsuario());
        validarTelefono.setText(validarNuevoAdmin.getErrorTelefonoUsuario());
        
     }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      
    }
    
} 
