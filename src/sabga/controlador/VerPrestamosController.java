
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Ejemplar;
import sabga.atributos.Prestamo;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.atributos.Atributos;

/**
 * FXML Controller class
 * @author Elk1n
 */

public class VerPrestamosController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private Label lblTotal, lblBusqueda;
    @FXML
    private HBox hboxFechaI, hboxFechaF;
    @FXML
    private TextField txtfBuscar;
    @FXML
    private Button btnBorrar;
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableView<Prestamo> tablaPrestamo;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private TableColumn<Prestamo, String> clmnDocumento, clmnNombre, clmnNombreB, clmnApellido, clmnFechaPrestamo,
                                          clmnFechaReserva, clmnEstado, clmnGrado, clmnCurso, clmnJornada, clmnEstadoPrestamo;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplar, clmnTitulo, clmnCodigo, clmnEditorial, clmnTipo, clmnClase, clmnFechaDevolucion,
                                          clmnEstadoEjemplar;
    @FXML
    private MenuItem menuDetalle, menuMultas, menuDetalleMaterial;
    
    private final DatePicker fechaInicio;
    private final DatePicker fechaFinal;
    private Calendar calendario;
    private final SimpleDateFormat formato;
    private final ObservableList<String> prestamos;
    private final ObservableList<Prestamo> listaPrestamos;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final Consultas consulta;
    private final Dialogo dialogo;
    private final Atributos atributo;
    
    public VerPrestamosController(){
        
        fechaInicio = new DatePicker();
        fechaFinal = new DatePicker();
        formato = new SimpleDateFormat("YYYY-MM-dd");
        fechaInicio.setDateFormat(formato);
        fechaFinal.setDateFormat(formato);
        fechaInicio.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
        fechaInicio.getStylesheets().add("sabga/vista/css/DatePicker.css");
        fechaFinal.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
        fechaFinal.getStylesheets().add("sabga/vista/css/DatePicker.css");
        calendario = Calendar.getInstance();
        calendario = new GregorianCalendar();
        prestamos = FXCollections.observableArrayList();
        listaPrestamos = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        consulta = new Consultas();    
        dialogo = new Dialogo();
        atributo = new Atributos();
    }
    
    @FXML
    public void listarLosPrestamos(ActionEvent evento){
        listarPrestamos();
    }
    
    public void cargarEjemplar(){
        cargarEjemplares();
    }
    
    @FXML
    public void bucarPrestamos(ActionEvent evento){
        buscarPrestamo();
    }
    
    @FXML
    public void listarPrestamosFecha(ActionEvent evento){
        listarPrestamoFecha();
    }
    
    @FXML
    public void verDetalleUsuario(ActionEvent evento){
        detalleUsuario();
    }
    
    @FXML
    public void verMultasUsuario(ActionEvent evento){
        multasUsuario();
    }
    
    @FXML
    public void verDetalleMaterial(ActionEvent evento){
        detalleMaterial();
    }
    
    
    private void listarPrestamoFecha(){
    
        if(fechaInicio.getSelectedDate() != null && fechaFinal.getSelectedDate() == null){
            prepararTablaPrestamos();
            listaPrestamos.addAll(consulta.getListaDePrestamos(5, formato.format(fechaInicio.getSelectedDate()), ""));
            tablaPrestamo.setItems(listaPrestamos);
            lblTotal.setText(String.valueOf(listaPrestamos.size()));           
        }
        if(fechaInicio.getSelectedDate() != null && fechaFinal.getSelectedDate() != null){
            prepararTablaPrestamos();      
            if(fechaInicio.getSelectedDate().before(fechaFinal.getSelectedDate())){
                listaPrestamos.addAll(consulta.getListaDePrestamos(6, formato.format(fechaInicio.getSelectedDate()),
                                                                      formato.format(fechaFinal.getSelectedDate())));
            }else if(fechaInicio.getSelectedDate().equals(fechaFinal.getSelectedDate())){
                listaPrestamos.addAll(consulta.getListaDePrestamos(5, formato.format(fechaInicio.getSelectedDate()), ""));
            }else if(fechaInicio.getSelectedDate().after(fechaFinal.getSelectedDate())){
                 listaPrestamos.addAll(consulta.getListaDePrestamos(6, formato.format(fechaFinal.getSelectedDate()),
                                                                       formato.format(fechaInicio.getSelectedDate())));
            }
            tablaPrestamo.setItems(listaPrestamos);
            lblTotal.setText(String.valueOf(listaPrestamos.size()));    
        }
    }
    
    private void buscarPrestamo() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaPrestamos();
            listaPrestamos.addAll(consulta.getListaDePrestamos(4, txtfBuscar.getText().trim(), ""));
            tablaPrestamo.setItems(listaPrestamos);
            if (listaPrestamos.isEmpty()) {
                comboListar.getSelectionModel().clearSelection();
                lblBusqueda.setText("No se han encontrado resultados.");
            } else {
                lblBusqueda.setText(null);
                comboListar.getSelectionModel().clearSelection();
            }
            lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }
    
    private void listarPrestamos() {

        if (!comboListar.getSelectionModel().isEmpty()) {
            prepararTablaPrestamos();
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(2, "", "" ));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(3, "", ""));
            }else if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(1, "", ""));
            }
            tablaPrestamo.setItems(listaPrestamos);
            lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }
    
    private void prepararTablaPrestamos(){
    
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("apellido"));
        clmnNombreB.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("bibliotecario"));
        clmnGrado.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("grado"));
        clmnCurso.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("curso"));
        clmnJornada.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("jornada"));
        clmnFechaReserva.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fechaReserva"));
        clmnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fecha"));
        clmnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
        listaPrestamos.clear();
        listaEjemplares.clear();
    }
    
    private void cargarEjemplares(){
    
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
            prepararTablaEjemplares();
            listaEjemplares.addAll(consulta.getListaDetalleDePrestamos(3,
                                   listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo()));
            tablaEjemplar.setItems(listaEjemplares);
            setDatosUsuario();
        }
    }
     
    private void setDatosUsuario() {   
        
        atributo.setDocumentoUsuario(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getDocumento());
        atributo.setNombreUsuario(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getNombre());
        atributo.setApellidoUsuario(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getApellido());
        atributo.setCorreoUsuario(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getCorreo());
    }
    
    
    private void prepararTablaEjemplares(){
    
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
        clmnEditorial.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("editorial"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
        clmnFechaDevolucion.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
        clmnEstadoEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        listaEjemplares.clear();    
    }
    
    private void detalleUsuario(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){            
            menuDetalle.setDisable(true);           
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            menuDetalle.setDisable(false);  
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void multasUsuario(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){            
            menuMultas.setDisable(true);
            dialogo.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            menuMultas.setDisable(false); 
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void detalleMaterial(){        
  
        if (tablaEjemplar.getSelectionModel().getSelectedItem() != null) {
            menuDetalleMaterial.setDisable(true);
            dialogo.setId(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getIdMaterial());
            dialogo.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", null  , null, 4);           
            menuDetalleMaterial.setDisable(false);
        }
        else{
            Utilidades.mensaje(null,"Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }
    }
    
    @FXML
    public void borrarCampo(ActionEvent evento){
        
        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
        lblBusqueda.setText(null);
    }
  
    @FXML
    public void mostrarBoton(){
        
        if ("".equals(txtfBuscar.getText())){            
            btnBorrar.setVisible(false);      
        }
        else {
           btnBorrar.setVisible(true); 
        }               
    }
    
    private void inicio() {
        
        hboxFechaI.getChildren().add(fechaInicio);
        hboxFechaF.getChildren().add(fechaFinal);
        prestamos.add("Vigentes");
        prestamos.add("Devueltos");
        prestamos.add("Todos");
        comboListar.setItems(prestamos);
        btnBorrar.setVisible(false);
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) { 
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }    
    
}
