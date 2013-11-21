
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * @author  
 */

public class RegistroBibliotecarioController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;       
    @FXML 
    private TextField campoNombre, campoApellidos, campoUsuario, campoCorreo, campoDocumento, campoTelefono;                   
    @FXML 
    private PasswordField campoContrasenia, campoContrasenia2;    
    @FXML 
    private ComboBox comboTipoAdmin;
    @FXML 
    private Label validarNombre, validarApellidos, validarUsuario, validarContrasenia, validarConfirmacion, validarCorreo,
                  validarDocumento, validarTelefono, validarTipoAdmin;
    private final Consultas consultar;
    private final ObservableList listaAdmin;
    
    public RegistroBibliotecarioController(){
        consultar = new Consultas();
        listaAdmin = FXCollections.observableArrayList();
    }
    
    @FXML 
    public void registrarBibliotecario(ActionEvent evento){
       registrarBiblitecario();          
   }
    
    private void registrarBiblitecario(){
        mensajes();
        ConfirmarUsuario bibliotecario = new ConfirmarUsuario();
        if(bibliotecario.nuevoBibliotecario(comboTipoAdmin.getSelectionModel().getSelectedItem(), campoNombre.getText(), campoApellidos.getText(),
                                            campoUsuario.getText(), campoContrasenia.getText(), campoContrasenia2.getText(), campoCorreo.getText(),
                                            campoDocumento.getText(), campoTelefono.getText())){           
            consultar.registrarBibliotecario(campoDocumento.getText(), campoUsuario.getText(), campoContrasenia.getText(),
                                             comboTipoAdmin.getSelectionModel().getSelectedItem().toString(), campoNombre.getText(), 
                                             campoApellidos.getText(), campoCorreo.getText(), campoTelefono.getText());
            if (consultar.getMensaje() == null) {
                limpiarCampos();
                Utilidades.mensaje(null, "El bibliotecario se ha registrado correctamente.", "", "Registrar Bibliotecario");
            } else {
                Utilidades.mensajeError(null, consultar.getMensaje(), "El bibliotecario no ha sido registrado.", "Error Registro");
            }    
        }        
   }
   
    private void mensajes(){
       
        ValidarUsuario bibliotecario = new ValidarUsuario();
        bibliotecario.validarNuevoBibliotecario(comboTipoAdmin.getSelectionModel().getSelectedItem(), campoNombre.getText(), campoApellidos.getText(),
                                                campoUsuario.getText(), campoContrasenia.getText(), campoContrasenia2.getText(), campoCorreo.getText(),
                                                campoDocumento.getText(), campoTelefono.getText());
        
        validarTipoAdmin.setText(bibliotecario.getErrorTipoAdmin());
        validarNombre.setText(bibliotecario.getErrorNombre());
        validarApellidos.setText(bibliotecario.getErrorApellido());
        validarUsuario.setText(bibliotecario.getErrorUsuario());
        validarContrasenia.setText(bibliotecario.getErrorContrasenia());
        validarConfirmacion.setText(bibliotecario.getErrorContrasenia());
        validarCorreo.setText(bibliotecario.getErrorCorreo());
        validarDocumento.setText(bibliotecario.getErrorDocumento());
        validarTelefono.setText(bibliotecario.getErrorTelefono());    
   }
     
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;        
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
           
    public void limpiarCampos(){
        comboTipoAdmin.getSelectionModel().clearSelection();
        campoDocumento.clear();
        campoUsuario.clear();
        campoContrasenia.clear();
        campoContrasenia2.clear();
        campoNombre.clear();
        campoApellidos.clear();
        campoCorreo.clear();
        campoTelefono.clear();
    }
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaAdmin.add("Auxiliar");
        listaAdmin.add("Bibliotecario");
        comboTipoAdmin.setItems(listaAdmin);
    }

} 
