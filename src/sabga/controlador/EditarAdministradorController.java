
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
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarUsuario;

/**
 * 
 * @author Elk1n
 * 
 */

public class EditarAdministradorController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    
    @FXML private TextField campoNombre, campoApellidos, campoUsuario, campoCorreo, campoDocumento, campoTelefono;
    
    @FXML private PasswordField campoContrasenia, campoNuevaContrasenia, campoConfirmacion;
    
    @FXML private ComboBox comboEstado;
    
    @FXML private Label validarNombre, validarApellidos, validarUsuario, validarCorreo, validarDocumento, validarTelefono, validarContrasenia,
                        validarNuevaContrasenia, validarConfirmacion;
    
    @FXML private Pane panelBusqueda, panelResultado;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    @FXML
    public void validarDatos(ActionEvent evento){
        
        ValidarUsuario validarEdicion = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoUsuario.getText(), campoCorreo.getText(),
                                                           campoDocumento.getText(), campoTelefono.getText(), comboEstado.getSelectionModel().getSelectedItem());
        
        validarEdicion.validarEdicionAdmin();
        validarNombre.setText(validarEdicion.getErrorNombreUsuario());
        validarApellidos.setText(validarEdicion.getErrorApellidosUsuario());
        validarUsuario.setText(validarEdicion.getErrorUsuario());
        validarCorreo.setText(validarEdicion.getErrorCorreoUsuario());
        validarDocumento.setText(validarEdicion.getErrorDocumentoUsuario());
        validarTelefono.setText(validarEdicion.getErrorTelefonoUsuario());
    
    } 
    
    @FXML
    public void validarNuevaContrasenia(ActionEvent evento){
        
        ValidarUsuario validarNuevaContra = new ValidarUsuario(campoContrasenia.getText(), campoNuevaContrasenia.getText(), campoConfirmacion.getText());
        
        validarNuevaContra.validarNuevaContrasenia();
        validarContrasenia.setText(validarNuevaContra.getErrorContrasenia());
        validarNuevaContrasenia.setText(validarNuevaContra.getErrorNuevaContrasenia());
        validarConfirmacion.setText(validarNuevaContra.getErrorConfirmacion());
               
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      //  panelBusqueda.getStylesheets().add("sabga/vista/css/estilo.css");
       // panelResultado.getStylesheets().add("sabga/vista/css/estilo.css");
       // panelBusqueda.getStyleClass().add("resultado");
      //  panelResultado.getStyleClass().add("resultado");
        
    }    
}
