
package sabga.controlador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.net.URL;

import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.modelo.ValidarUsuario;

/**
 * @author Nanny
 */

public class EditarUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;    
    private ScreensController controlador;
    private final Dialogo dialogo;
    @FXML
    private TableView tablaUsuarios;
    @FXML
    private TableColumn clmnTipo, clmnDocumento, clmnNombre, clmnApellido, clmnCorreo;
    @FXML 
    private TextField txtFiltrar, txtfMulta, txtfNombre, txtfApellido, txtfDocumento, txtfCorreo, txtfTelefono,
                      txtfDireccion;
    @FXML 
    private ComboBox comboTipo, comboGrado, comboCurso, comboJornada, comboEstado;     
    @FXML 
    private Label validarNombre, validarApellidos, validarDocumento, validarCorreo, validarTelefono, validarDireccion, validarMulta;
   
    public EditarUsuarioController(){    
        dialogo = new Dialogo(); 
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
     }
    
    public void setVentanaPrincipal(Sabga principal) {
        this.paginaPrincipal = principal;
    } 
    
    @FXML
    public void habilitarMulta(ActionEvent evento){        
        txtfMulta.setDisable(false);
    }
    
    @FXML
    public void dialogoDetalleUsuario(ActionEvent evento){        
        paginaPrincipal = new Sabga();
        dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información Detallada del Usuario", paginaPrincipal.getPrimaryStage(), null,5);       
    }
    
    @FXML
    public void validarDatos(ActionEvent evento){
        
//        ValidarUsuario validarDatosUsuario = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoDocumento.getText(),
//                                                                campoCorreo.getText(), campoTelefono.getText(), campoDireccion.getText(),campoMulta.getText());
//    
//        validarDatosUsuario.validarACUsuario();
//        validarNombre.setText(validarDatosUsuario.getErrorNombreUsuario());
//        validarApellidos.setText(validarDatosUsuario.getErrorApellidosUsuario());
//        validarDocumento.setText(validarDatosUsuario.getErrorDocumentoUsuario());
//        validarCorreo.setText(validarDatosUsuario.getErrorCorreoUsuario());
//        validarTelefono.setText(validarDatosUsuario.getErrorTelefonoUsuario());
//        validarDireccion.setText(validarDatosUsuario.getErrorDireccionUsuario());
//        validarMulta.setText(validarDatosUsuario.getErrorMulta());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              
    }
}
    
