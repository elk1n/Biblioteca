
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;

/**
 * @author Nanny
 */

public class EditarUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;    
    private ScreensController controlador;
    private final Dialogo dialogo;
    private final Consultas consulta;
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
    
    private final ObservableList<Usuario> listaUsuarios;
    private final Seleccion select;
    
   
    public EditarUsuarioController(){    
        dialogo = new Dialogo();
        consulta = new Consultas();
        select = new Seleccion();
        listaUsuarios = FXCollections.observableArrayList();
    }
    
    private void tablaUsuarios(){
        
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tablaUsuarios.setEditable(true);
        listaUsuarios.addAll(consulta.getListaUsuarios());
        tablaUsuarios.setItems(listaUsuarios);   
    }
    
    @FXML
    private void mapearUsuario(){
    
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){
            consulta.mapearUsuarios(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            txtfNombre.setText(consulta.getNombre());
            txtfApellido.setText(consulta.getApellido());
            txtfDocumento.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            txtfCorreo.setText(consulta.getCorreo());
            txtfTelefono.setText(consulta.getTelefono());
            txtfDireccion.setText(consulta.getDireccion());
            txtfMulta.setText(String.valueOf(consulta.getMulta()));            
        }
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
    
    private void inicio(){
              
        comboGrado.setItems(consulta.llenarLista(select.getListaGrado(), select.getGrado()));
        comboCurso.setItems(consulta.llenarLista(select.getListaCurso(), select.getCurso()));
        comboJornada.setItems(consulta.llenarLista(select.getListaJornada(), select.getJornada()));
        comboTipo.setItems(consulta.llenarLista(select.getListaTipoUsuario(), select.getTipoUsuario())); 
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
              tablaUsuarios();
    }
}
    
