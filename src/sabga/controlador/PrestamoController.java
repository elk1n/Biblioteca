
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Ejemplar;
import sabga.atributos.Material;
import sabga.atributos.Reserva;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;


public class PrestamoController implements Initializable, ControlledScreen{
        
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML 
    private ComboBox comboListar, comboListaUsuario;
    @FXML 
    private TextField txtfBuscar, txtfBuscarUsuario, txtfBuscarReserva;
    @FXML 
    private Label validarBusqueda;
    @FXML
    private Button btnDetalle;
    @FXML
    private HBox hboxFecha;
    @FXML
    private TableView tablaMaterial, tablaEjemplar, tablaUsuarios, tablaReserva, tablaDetalleReserva;
    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase, clmnTipo, clmnEjemplar, clmnEstado, clmnDispo, clmnDocumento,
                        clmnNombre, clmnApellido, clmnCorreo, clmnTipoUsuario, clmnEstadoUsuario, clmnDocumentoRe, clmnNombreRe,
                        clmnApellidoRe, clmnFecha, clmnEstadoRe, clmnTituloDe, clmnCodigoDe, clmnAutor, clmnEditorial, clmnMateria;
    private final DatePicker fechaDevolucion;
    private final Consultas consulta;
    private final Seleccion select;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Reserva> listaReservas;
    private final Dialogo dialogos;
    
    
    public PrestamoController(){
       
       fechaDevolucion = new DatePicker();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
       listaMaterial = FXCollections.observableArrayList();
       listaEjemplares = FXCollections.observableArrayList();
       listaUsuarios = FXCollections.observableArrayList();
       listaReservas = FXCollections.observableArrayList();
       consulta = new Consultas();
       select = new Seleccion();
       dialogos = new Dialogo();
    }
    
    @FXML
    public void listarMaterial(ActionEvent evento){                        
        prepararTablaMaterial();
        listar();    
    }
    
    public void cargarEjemlar(){    
        mapearEjemplar();
    }
    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento){        
        detalleMaterial();
    }
          
    @FXML
    public void buscarMaterial(ActionEvent evento){
        buscarMaterial();
    }
    
    @FXML
    public void listarUsuarios(ActionEvent evento){
        listarUsuario();  
    }
    
    @FXML
    public void buscarUsuario(ActionEvent evento){    
        buscarUsuario();
    }
    
    @FXML
    public void listarReservas(ActionEvent evento){
        listarReservas();
    }
    
    @FXML
    public void buscarReserva(ActionEvent evento){
        buscarReserva();        
    }
    
    private void buscarReserva(){
        
        if (!"".equals(txtfBuscarReserva.getText())) {
            prepararTablaReserva();
            listaReservas.addAll(consulta.getListaReservaBusqueda(txtfBuscarReserva.getText().trim()));
            tablaReserva.setItems(listaReservas);
        }
    }
    
    private void listarReservas(){
        prepararTablaReserva();
        listaReservas.addAll(consulta.getListaReservas());
        tablaReserva.setItems(listaReservas);
    }
    
    private void prepararTablaReserva(){
        
        clmnDocumentoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("documento"));
        clmnNombreRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombre"));
        clmnApellidoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido"));
        clmnFecha.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fecha"));
        clmnEstadoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        tablaReserva.setEditable(true);
        listaReservas.clear();
        
    }
    
    private void buscarUsuario(){
    
         if (!"".equals(txtfBuscarUsuario.getText())) {
            prepararTablaUsuario();
            listaUsuarios.addAll(consulta.getListaUsuarioBusqueda(txtfBuscarUsuario.getText().trim()));
            tablaUsuarios.setItems(listaUsuarios);
        }   
    }
    
    private void listarUsuario(){
        
        prepararTablaUsuario();
        if(comboListaUsuario.getSelectionModel().getSelectedItem().toString().contains("Todos")){ 
            listaUsuarios.addAll(consulta.getListaUsuarios(2, null));
        }else {
            listaUsuarios.addAll(consulta.getListaUsuarios(1, comboListaUsuario.getSelectionModel().getSelectedItem().toString()));            
        }
        tablaUsuarios.setItems(listaUsuarios);
    }
     
    private void prepararTablaUsuario(){
        
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        clmnTipoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnEstadoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        tablaUsuarios.setEditable(true);
        listaUsuarios.clear();      
    }
    
    private void buscarMaterial() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialBusqueda(txtfBuscar.getText().trim()));
            tablaMaterial.setItems(listaMaterial);
        }
    }
    
    private void detalleMaterial(){
    
         if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            ventanaPrincipal = new Sabga();
            btnDetalle.setDisable(true);
            dialogos.setId(Integer.parseInt(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
            dialogos.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", ventanaPrincipal.getPrimaryStage(), null, 4);           
            btnDetalle.setDisable(false);
        }
        else{
            Utilidades.mensaje(null,"Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }    
    }
    
    private void mapearEjemplar(){
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            listaEjemplares.clear();
            clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
            clmnEstado.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
            clmnDispo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("disponibilidad"));
            tablaEjemplar.setEditable(true);
            listaEjemplares.addAll(consulta.listaEjemplares(Integer.parseInt(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
            tablaEjemplar.setItems(listaEjemplares);
        }
    }
    
    private void prepararTablaMaterial(){   
        
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));        
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Material, String>("tipo"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));        
        tablaMaterial.setEditable(true);
        listaMaterial.clear();
        listaEjemplares.clear();
           
    }
    
    private void listar(){    
        
        listaMaterial.addAll(consulta.getListaMaterial(comboListar.getSelectionModel().getSelectedItem().toString()));
        tablaMaterial.setItems(listaMaterial);
    }
    
    private void inicio(){
        comboListar.setItems(consulta.llenarLista(select.getListaTipoMaterial(), select.getTipoMaterial()));
        comboListaUsuario.setItems(consulta.llenarLista(select.getListaUsuarios(), select.getUsuarios()));
        comboListaUsuario.getItems().add("Todos");
      
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) { 
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        hboxFecha.getChildren().add(fechaDevolucion);
        inicio();
    }    
}
