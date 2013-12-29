
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
import javafx.scene.layout.AnchorPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;
import sabga.modelo.ValidarUsuario;

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
    private AnchorPane panelFondo;
    @FXML 
    private TextField txtfFiltrar, txtfNombre, txtfApellido, txtfDocumento, txtfCorreo, txtfTelefono,
                      txtfDireccion, txtfBuscar;
    @FXML 
    private ComboBox comboTipo, comboGrado, comboCurso, comboJornada, comboEstado, comboListar;     
    @FXML 
    private Label lblMulta, lblNombre, lblApellido, lblDocumento, lblCorreo, lblTelefono, lblDireccion, 
                  lblGrado, lblCurso, lblJornada, lblResultado;
    
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Usuario> filtrarUsuarios;
    private final ObservableList estados;
    private final Seleccion select;
    private final Dialogo dialogos;
    private final Atributos atributo;
    
   
    public EditarUsuarioController(){
        
        dialogo = new Dialogo();
        consulta = new Consultas();
        select = new Seleccion();
        dialogos = new Dialogo();
        atributo = new Atributos();
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
       guardarCambios();
    }
       
    @FXML
    public void buscarUsuario(ActionEvent evento){
        buscarUsuario();
    }
    
    @FXML
    public void listarUsuarios(ActionEvent evento){
        listarUsuario();
    }
    
    @FXML
    public void detalleMultas(ActionEvent evento){
        multas();
    }
        
    @FXML
    public void dialogoDetalleUsuario(ActionEvent evento){
        dialogoDetalleUsuario();
    }
    
    private void multas(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            panelFondo.setDisable(true);
            dialogos.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            panelFondo.setDisable(false);     
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void guardarCambios(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){
            mensajesError();
            String id = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento();
            int estado = comboEstado.getSelectionModel().getSelectedItem().toString().trim().equals("Habilitado") ? 1: 2;
            ConfirmarUsuario estudiante = new ConfirmarUsuario();
            editarUsuario(id, estado);
        }        
    }
  
    private void editarUsuario(String id, int estado){
    
        if(comboTipo.getSelectionModel().getSelectedItem().toString().toLowerCase().trim().contains("estudiante")){
            ConfirmarUsuario estudiante = new ConfirmarUsuario();
            if(estudiante.nuevoEstudiante(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                          comboGrado.getSelectionModel().getSelectedItem(), comboCurso.getSelectionModel().getSelectedItem(),
                                          comboJornada.getSelectionModel().getSelectedItem(), txtfDocumento.getText(), txtfTelefono.getText(),
                                          txtfDireccion.getText())){
                consulta.editarUsuario(1, id, comboTipo.getSelectionModel().getSelectedItem().toString(), txtfNombre.getText(),
                                       txtfApellido.getText(), txtfCorreo.getText(), txtfDocumento.getText(),
                                       comboGrado.getSelectionModel().getSelectedItem().toString(), comboCurso.getSelectionModel().getSelectedItem().toString(),
                                       comboJornada.getSelectionModel().getSelectedItem().toString(), txtfTelefono.getText(), txtfDireccion.getText(),
                                       estado);
                if (consulta.getMensaje() == null) {
                    limpiarCampos();
                    Utilidades.mensaje(null, "La información se ha actualizado correctamente.", "", "Editar Datos Usuario");
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La información no ha sido actualizada.", "Error Edición");
                }
            }        
        }else {
            ConfirmarUsuario funcionario = new ConfirmarUsuario();
            if(funcionario.nuevoFuncionario(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),txtfDocumento.getText(), 
                                            txtfTelefono.getText(), txtfDireccion.getText())){
                consulta.editarUsuario(2, id, comboTipo.getSelectionModel().getSelectedItem().toString(), txtfNombre.getText(),
                                             txtfApellido.getText(), txtfCorreo.getText(), txtfDocumento.getText(), null, null, null,
                                             txtfTelefono.getText(), txtfDireccion.getText(),estado);                
                if(consulta.getMensaje()== null){
                        limpiarCampos();
                        Utilidades.mensaje(null, "La información se ha actualizado correctamente.","", "Editar Datos Usuario");                        
                    }
                else{
                    Utilidades.mensajeError(null, consulta.getMensaje(),"La información no ha sido actualizada.", "Error Edición");
                }
            }  
        }    
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
    public void mapearUsuario(){
    
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
            atributo.setNombreUsuario(consulta.getNombre());
            atributo.setApellidoUsuario(consulta.getApellido());
            atributo.setDocumentoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            atributo.setCorreoUsuario(consulta.getCorreo());            
        }
    }
    
    private void buscarUsuario(){
        limpiarDetalle();
        if (!"".equals(txtfBuscar.getText()) && radioBuscar.isSelected()) {
            tablaUsuarios();
            filtrarUsuarios.addAll(listaUsuarios);
            listaUsuarios.addAll(consulta.getListaUsuarioBusqueda(txtfBuscar.getText().trim()));
            tablaUsuarios.setItems(filtrarUsuarios);
            if(filtrarUsuarios.isEmpty()){
                comboListar.getSelectionModel().clearSelection();
                lblResultado.setText("No se han encontrado resultados.");
            }else{
                lblResultado.setText(null);
                comboListar.getSelectionModel().clearSelection();
            }
        }
    }
    
    private void listarUsuario(){
        
        limpiarDetalle();
        if (!comboListar.getSelectionModel().isEmpty()) {
            tablaUsuarios();
            filtrarUsuarios.addAll(listaUsuarios);
            if (comboListar.getSelectionModel().getSelectedItem().toString().contains("Todos")) {
                listaUsuarios.addAll(consulta.getListaUsuarios(2, null));
            } else {
                listaUsuarios.addAll(consulta.getListaUsuarios(1, comboListar.getSelectionModel().getSelectedItem().toString()));
            }
            tablaUsuarios.setItems(filtrarUsuarios);
        }      
    }
   
    public void mensajesError(){
        
        if(comboTipo.getSelectionModel().getSelectedItem().toString().toLowerCase().contains("estudiante")){
                ValidarUsuario estudiante = new ValidarUsuario();
                estudiante.validarNuevoEstudiante(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                                  comboGrado.getSelectionModel().getSelectedItem(), comboCurso.getSelectionModel().getSelectedItem(),
                                                  comboJornada.getSelectionModel().getSelectedItem(), txtfDocumento.getText(),
                                                  txtfTelefono.getText(),txtfDireccion.getText());

                lblNombre.setText(estudiante.getErrorNombre());
                lblApellido.setText(estudiante.getErrorApellido());
                lblDocumento.setText(estudiante.getErrorDocumento());
                lblCorreo.setText(estudiante.getErrorCorreo());
                lblTelefono.setText(estudiante.getErrorTelefono());
                lblDireccion.setText(estudiante.getErrorDireccion());
                lblGrado.setText(estudiante.getErrorGrado());
                lblCurso.setText(estudiante.getErrorCurso());
                lblJornada.setText(estudiante.getErrorJornada());        
        }else{
            ValidarUsuario funcionario = new ValidarUsuario();
            funcionario.validarNuevoFuncionario(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                                txtfDocumento.getText(), txtfTelefono.getText(),txtfDireccion.getText());
                        
            lblNombre.setText(funcionario.getErrorNombre());
            lblApellido.setText(funcionario.getErrorApellido());
            lblCorreo.setText(funcionario.getErrorCorreo());
            lblDocumento.setText(funcionario.getErrorDocumento());
            lblTelefono.setText(funcionario.getErrorTelefono());
            lblDireccion.setText(funcionario.getErrorDireccion());
        }    
    }
    
    private void limpiarCampos(){
        
        tablaUsuarios.getSelectionModel().clearSelection();
        txtfNombre.setText("");
        txtfApellido.setText("");
        txtfDocumento.setText("");
        txtfCorreo.setText("");
        txtfTelefono.setText("");
        txtfDireccion.setText("");
        comboGrado.getSelectionModel().clearSelection();
        comboCurso.getSelectionModel().clearSelection();
        comboJornada.getSelectionModel().clearSelection();
        comboTipo.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();    
    }
    
    private void limpiarDetalle(){
        
        txtfNombre.setText("");
        txtfApellido.setText("");
        txtfDocumento.setText("");
        txtfCorreo.setText("");
        txtfTelefono.setText("");
        txtfDireccion.setText("");
        comboGrado.getSelectionModel().clearSelection();
        comboCurso.getSelectionModel().clearSelection();
        comboJornada.getSelectionModel().clearSelection();
        comboTipo.getSelectionModel().clearSelection();
        comboEstado.getSelectionModel().clearSelection();            
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
    public void borrarCampo(ActionEvent evento) {
        
        txtfFiltrar.setText("");
        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
        lblResultado.setText(null);
    }
           
    private void dialogoDetalleUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            panelFondo.setDisable(true);
            dialogos.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            panelFondo.setDisable(false);     
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
          
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
     }
    
    public void setVentanaPrincipal(Sabga principal) {
        this.paginaPrincipal = principal;
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
    
