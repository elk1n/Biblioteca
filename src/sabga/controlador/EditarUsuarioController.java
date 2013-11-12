
package sabga.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private Button btnDetalle, btnBorrar;
    @FXML
    private RadioButton radioBuscar, radioFiltrar;
    @FXML
    private TableView tablaUsuarios;
    @FXML
    private TableColumn clmnTipo, clmnDocumento, clmnNombre, clmnApellido, clmnCorreo;
    @FXML 
    private TextField txtfFiltrar, txtfNombre, txtfApellido, txtfDocumento, txtfCorreo, txtfTelefono,
                      txtfDireccion, txtfBuscar;
    @FXML 
    private ComboBox comboTipo, comboGrado, comboCurso, comboJornada, comboEstado, comboListar;     
    @FXML 
    private Label lblMulta,validarNombre, validarApellidos, validarDocumento, validarCorreo, validarTelefono, validarDireccion, 
                  validarMulta, lblGrado, lblCurso, lblJornada;
    
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Usuario> filtrarUsuarios;
    private final ObservableList estados;
    private final Seleccion select;
    
   
    public EditarUsuarioController(){
        
        dialogo = new Dialogo();
        consulta = new Consultas();
        select = new Seleccion();
        estados = FXCollections.observableArrayList();
        listaUsuarios = FXCollections.observableArrayList();
        filtrarUsuarios = FXCollections.observableArrayList();
        estados.add("Habilitado");
        estados.add("Inhabilitado");
        listaUsuarios.addListener(new ListChangeListener<Usuario>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Usuario> change) {
                updateFilteredData();
            }
        });
    }
    
    @FXML
    public void guardarCambios(ActionEvent evento){
       
    }
       
    @FXML
    public void buscarUsuario(ActionEvent evento){
        buscarUsuario();
    }
    
    @FXML
    public void listarUsuarios(ActionEvent evento){
        listarUsuario();
    }
       
    private void tablaUsuarios(){
        
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tablaUsuarios.setEditable(true);
        listaUsuarios.clear();
           
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
            lblMulta.setText(String.valueOf(consulta.getMulta()));
            comboGrado.getSelectionModel().select(consulta.getGrado());
            comboCurso.getSelectionModel().select(consulta.getCurso());
            comboJornada.getSelectionModel().select(consulta.getJornada());
            comboTipo.getSelectionModel().select(consulta.getTipoUsuario());
            comboEstado.getSelectionModel().select(consulta.getEstado());
        }
    }
    
    private void buscarUsuario(){
        
        if (!"".equals(txtfBuscar.getText()) && radioBuscar.isSelected()) {
            tablaUsuarios();
            filtrarUsuarios.addAll(listaUsuarios);
            listaUsuarios.addAll(consulta.getListaUsuarioBusqueda(txtfBuscar.getText().trim()));
            tablaUsuarios.setItems(filtrarUsuarios);
        }
    }
    
    private void listarUsuario(){
        
        tablaUsuarios();
        filtrarUsuarios.addAll(listaUsuarios);
        if(comboListar.getSelectionModel().getSelectedItem().toString().contains("Todos")){ 
            listaUsuarios.addAll(consulta.getListaUsuarios(2, null));
        }
        else {
            listaUsuarios.addAll(consulta.getListaUsuarios(1, comboListar.getSelectionModel().getSelectedItem().toString()));            
        }
        tablaUsuarios.setItems(filtrarUsuarios);
    }
   
    @FXML
    private void buscarFiltrar(){
        
        if(radioBuscar.isSelected()){
            txtfFiltrar.setText("");
            txtfFiltrar.setDisable(true);
            txtfFiltrar.setVisible(false); 
            txtfBuscar.setText("");
            txtfBuscar.setDisable(false);
            txtfBuscar.setVisible(true);
        }
        else if(radioFiltrar.isSelected()){
            txtfBuscar.setText("");
            txtfBuscar.setDisable(true);
            txtfBuscar.setVisible(false);
            txtfFiltrar.setText("");
            txtfFiltrar.setDisable(false);
            txtfFiltrar.setVisible(true);
        }
    
    }
    
    @FXML
    private void mostrarBoton(KeyEvent evento) {

        if ("".equals(txtfBuscar.getText()) && "".equals(txtfFiltrar.getText())){            
            btnBorrar.setVisible(false);      
        }
        else {
           btnBorrar.setVisible(true); 
        }                 
    }
    
    @FXML
    private void borrarCampo(ActionEvent evento) {
        
        txtfFiltrar.setText("");
        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
     }
    
    public void setVentanaPrincipal(Sabga principal) {
        this.paginaPrincipal = principal;
    } 
        
    @FXML
    public void dialogoDetalleUsuario(ActionEvent evento){        
        paginaPrincipal = new Sabga();
        btnDetalle.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información Detallada del Usuario", paginaPrincipal.getPrimaryStage(), null,5);       
        btnDetalle.setDisable(false);
    }
    
    private void inicio(){
        
        btnBorrar.setVisible(false);
        buscarFiltrar();
        comboGrado.setItems(consulta.llenarLista(select.getListaGrado(), select.getGrado()));
        comboCurso.setItems(consulta.llenarLista(select.getListaCurso(), select.getCurso()));
        comboJornada.setItems(consulta.llenarLista(select.getListaJornada(), select.getJornada()));
        comboTipo.setItems(consulta.llenarLista(select.getListaTipoUsuario(), select.getTipoUsuario()));        
        comboListar.setItems(consulta.llenarLista(select.getListaUsuarios(), select.getUsuarios()));
        comboListar.getItems().add("Todos");
        comboEstado.setItems(estados);
        
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
    
    private void updateFilteredData() {
        
      filtrarUsuarios.clear();
      for (Usuario m : listaUsuarios) {
          if (matchesFilter(m)) {
              filtrarUsuarios.add(m);
          }
      }     
      reapplyTableSortOrder();
  }
    
    private boolean matchesFilter(Usuario usuarios) {
        
      String filterString = txtfFiltrar.getText();
      if (filterString == null || filterString.isEmpty()) {
          return true;
      }
      
      String lowerCaseFilterString = filterString.toLowerCase();
      
      if (usuarios.getDocumento().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      } else if (usuarios.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      } else if (usuarios.getApellido().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      } else if (usuarios.getCorreo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      }     
      return false;
  }   
    
    private void reapplyTableSortOrder() {
        
      ArrayList<TableColumn<Usuario, ?>> sortOrder = new ArrayList<>(tablaUsuarios.getSortOrder());
      tablaUsuarios.getSortOrder().clear();
      tablaUsuarios.getSortOrder().addAll(sortOrder);
  }  
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        inicio();
        txtfFiltrar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                mostrarBoton(null);
                updateFilteredData();
            }
        });
    }
}
    
