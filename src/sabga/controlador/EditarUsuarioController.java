
package sabga.controlador;

import java.net.URL;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarUsuario;

/**
 *
 * @author Nanny
 * 
 */

public class EditarUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    
    private ScreensController controlador;
    
    @FXML private TextField campoNombre, campoApellidos, campoDocumento, campoCorreo, campoTelefono, campoDireccion, campoMulta;
    
    @FXML private ComboBox comboTipoUsuario, comboCurso, comboGrupo, comboJornada, comboEstado;
    
    @FXML private Label validarNombre, validarApellidos, validarDocumento, validarCorreo, validarTelefono, validarDireccion, validarMulta;
   

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;

     }
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;

    } 
    
    @FXML
    public void habilitarMulta(ActionEvent evento){
        
        campoMulta.setDisable(false);
    }
    
    @FXML
    public void dialogoDetalleUsuario(ActionEvent evento){
        
        paginaPrincipal = new Sabga();
        paginaPrincipal.dialogoDetalleUsuario();        
    }
    
    @FXML
    public void validarDatos(ActionEvent evento){
        
        ValidarUsuario validarDatosUsuario = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoDocumento.getText(),
                                                                campoCorreo.getText(), campoTelefono.getText(), campoDireccion.getText(),campoMulta.getText());
    
        validarDatosUsuario.validarACUsuario();
        validarNombre.setText(validarDatosUsuario.getErrorNombreUsuario());
        validarApellidos.setText(validarDatosUsuario.getErrorApellidosUsuario());
        validarDocumento.setText(validarDatosUsuario.getErrorDocumentoUsuario());
        validarCorreo.setText(validarDatosUsuario.getErrorCorreoUsuario());
        validarTelefono.setText(validarDatosUsuario.getErrorTelefonoUsuario());
        validarDireccion.setText(validarDatosUsuario.getErrorDireccionUsuario());
        validarMulta.setText(validarDatosUsuario.getErrorMulta());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        campoMulta.setText("5500");
        comboTipoUsuario.getSelectionModel().selectFirst();
        comboGrupo.getSelectionModel().selectLast();
            
    }
}
    
