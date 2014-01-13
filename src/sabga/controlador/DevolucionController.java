
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Devolucion;
import sabga.atributos.Prestamo;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class DevolucionController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML 
    private Label lblBusqueda, lblNombre, lblDocumento, lblValidarFecha;  
    @FXML
    private ComboBox<String> comboOpcion, comboPrestamos;
    @FXML
    private Button btnBorrar;
    @FXML 
    private TextField txtfBuscar;
    @FXML
    private HBox hboxFecha;
    @FXML
    private TableView<Devolucion> tablaDevolucion;
    @FXML
    private TableView<Prestamo> tablaPrestamo;
    @FXML
    private TableColumn<Devolucion, String> clmnEjemplar,  clmnTitulo, clmnCodigo, clmnFechaDevolucion, clmnEstadoEjemplar;
    @FXML
    private TableColumn<Prestamo, String> clmnDocumento, clmnNombre, clmnApellido, clmnFechaPrestamo, clmnEstadoPrestamo;
    private final ObservableList<Prestamo> listaPrestamos; 
    private final ObservableList<Devolucion> listaEjemplares, ejemplarDevolucion, listaEjemplaresRestantes;
    private final ObservableList<String> prestamos;
    private final DatePicker fechaDevolucion;
    private final Consultas consulta;
    private int idPrestamo, idEjemplar;
    private String estado;
    private Calendar calendario;
    private final SimpleDateFormat formato;
    
    public DevolucionController(){
       
       listaPrestamos = FXCollections.observableArrayList();
       listaEjemplares = FXCollections.observableArrayList();
       prestamos = FXCollections.observableArrayList();
       ejemplarDevolucion = FXCollections.observableArrayList();
       listaEjemplaresRestantes = FXCollections.observableArrayList();
       fechaDevolucion = new DatePicker();
       consulta = new Consultas();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
       formato = new SimpleDateFormat("YYYY-MM-dd");
       calendario = Calendar.getInstance();
       calendario = new GregorianCalendar();
    
    }
    
    @FXML
    public void buscar(ActionEvent evento){
       buscarPrestamo();
    }
    
    @FXML
    public void listarPrestamos(ActionEvent evento){    
        listarPrestamos();
    }
    
    public void mapearDetallePrestamo(){
        detallePrestamo();
    }
    
    @FXML
    public void aceptar(ActionEvent evento){
        devolverRenovar();
    }
    
    public void ejemplarDevolucion(){        
        getEjemplarDevolucion();
    }
    
    private void devolverRenovar() {

        if (comboOpcion.getSelectionModel().getSelectedIndex() == 0) {
            devolucionCompleta();
        } else if (comboOpcion.getSelectionModel().getSelectedIndex() == 1) {
            devolverEjemplar();
        } else if (comboOpcion.getSelectionModel().getSelectedIndex() == 2) {
            renovarTodo();
        } else if (comboOpcion.getSelectionModel().getSelectedIndex() == 3) {
            renovarEjemplar();
        }
    }
    
    private void renovarTodo() {

        if (tablaPrestamo.getSelectionModel().getSelectedItem() != null && idPrestamo != 0
            && !listaEjemplares.isEmpty() && estado.equalsIgnoreCase("Vigente")) {
            ValidarMaterial renovacion = new ValidarMaterial();
            ConfirmarMaterial renovaciones = new ConfirmarMaterial();
            renovacion.validarRenovacion(fechaDevolucion.getSelectedDate());
            lblValidarFecha.setText(renovacion.getErrorFecha());
            if (renovaciones.confirmarRenovacion(fechaDevolucion.getSelectedDate())) {
                listaEjemplares();
                consulta.registrarRenovacion(1, idPrestamo, listaEjemplaresRestantes, formato.format(fechaDevolucion.getSelectedDate()));
                if (consulta.getMensaje() == null) {
                    Utilidades.mensaje(null, "La renovación se ha registrado exitosamente.", "", "Registrar Renovación");
                    listarPrestamos();
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La renovación no ha sido registrada.", "Error Registro Renovación");
                }
            }
        }
    }
    
    private void renovarEjemplar(){
        
        if (tablaPrestamo.getSelectionModel().getSelectedItem() != null && idPrestamo != 0
                && !listaEjemplares.isEmpty() && estado.equalsIgnoreCase("Vigente")) {
            ValidarMaterial renovacion = new ValidarMaterial();
            ConfirmarMaterial renovaciones = new ConfirmarMaterial();
            renovacion.validarRenovacion(fechaDevolucion.getSelectedDate());
            lblValidarFecha.setText(renovacion.getErrorFecha());
            if (renovaciones.confirmarRenovacion(fechaDevolucion.getSelectedDate())) {
                if (tablaDevolucion.getSelectionModel().getSelectedItem() != null) {
                    getEjemplarDevolucion();
                    consulta.registrarRenovacion(1, idPrestamo, ejemplarDevolucion, formato.format(fechaDevolucion.getSelectedDate()));
                    if (consulta.getMensaje() == null) {
                        Utilidades.mensaje(null, "La renovación se ha registrado exitosamente.", "", "Registrar Renovación");
                        listarPrestamos();
                    } else {
                        Utilidades.mensajeError(null, consulta.getMensaje(), "La renovación no ha sido registrada.", "Error Registro Renovación");
                    }
                } else {
                    Utilidades.mensajeAdvertencia(null, "Debe seleccionar un ejemplar de la lista.", " ", "Seleccionar Ejemplar");
                }
            }
        }           
    }
    
    private void devolverEjemplar() {

        if (tablaPrestamo.getSelectionModel().getSelectedItem() != null && idPrestamo != 0 && !ejemplarDevolucion.isEmpty()
            && tablaDevolucion.getSelectionModel().getSelectedItem() != null && estado.equalsIgnoreCase("Vigente")) {
            if (consulta.getIdDevolucion(idPrestamo) == 0) {
                consulta.registrarDevolucion(2, idPrestamo, ejemplarDevolucion, formato.format(calendario.getTime()));
                if (consulta.getMensaje() == null) {
                    Utilidades.mensaje(null, "La devolución se ha registrado correctamente.", "", "Registrar Devolución");
                     listarPrestamos();
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La devolución no ha sido registrada.", "Error Registro Devolución");
                }
            } else {
                 consulta.registrarDetalleDevolucion(consulta.getIdDevolucion(idPrestamo), idPrestamo, ejemplarDevolucion, formato.format(calendario.getTime()));
                if (consulta.getMensaje() == null) {
                    listarPrestamos();
                    Utilidades.mensaje(null, "La devolución se ha registrado correctamente.", "", "Registrar Devolución");
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La devolución no ha sido registrada.", "Error Registro Devolución");
                }
            }
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe seleccionar un ejemplar de la lista. ", "", "Seleccionar Ejemplar");
        }
    }

    private void devolucionCompleta() {

        if (tablaPrestamo.getSelectionModel().getSelectedItem() != null && !listaEjemplares.isEmpty() && 
            idPrestamo != 0 && estado.equalsIgnoreCase("Vigente")) {
            if (consulta.getIdDevolucion(idPrestamo) == 0) {
                consulta.registrarDevolucion(1, idPrestamo, listaEjemplares, formato.format(calendario.getTime()));
                if (consulta.getMensaje() == null) {
                    listarPrestamos();
                    Utilidades.mensaje(null, "La devolución se ha registrado correctamente.", "", "Registrar Devolución");                   
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La devolución no ha sido registrada.", "Error Registro Devolución");
                }
            } else {
                listaEjemplares();
                consulta.registrarDetalleDevolucion(consulta.getIdDevolucion(idPrestamo), idPrestamo, listaEjemplaresRestantes, formato.format(calendario.getTime()));
                if (consulta.getMensaje() == null) {
                    listarPrestamos();
                    Utilidades.mensaje(null, "La devolución se ha registrado correctamente.", "", "Registrar Devolución");
                } else {
                    Utilidades.mensajeError(null, consulta.getMensaje(), "La devolución no ha sido registrada.", "Error Registro Devolución");
                }
            }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un prestamo.", "", "Registro Devolución");
        }
    }
    
    private void getEjemplarDevolucion(){
        
        ejemplarDevolucion.clear();
        if(tablaDevolucion.getSelectionModel().getSelectedItem() != null){
            if(listaEjemplares.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getEstado().contains("Prestado")){
                ejemplarDevolucion.add(listaEjemplares.get(tablaDevolucion.getSelectionModel().getSelectedIndex()));
            }            
        }        
    }
       
    private void detallePrestamo(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
            prepararTablaDevolucion();
            listaEjemplares.addAll(consulta.getListaDetallePrestamo(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo()));
            idPrestamo = listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo();
            estado = listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getEstado();
            consulta.mapearInfoAdmin(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo());
            lblNombre.setText(consulta.getNombre());
            lblDocumento.setText(consulta.getDocumento());
            tablaDevolucion.setItems(listaEjemplares);
        }
    }
    
    private void prepararTablaDevolucion(){
    
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("codigo"));
        clmnFechaDevolucion.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        clmnEstadoEjemplar.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estado"));
        listaEjemplares.clear();
    
    }
    
    private void buscarPrestamo() {
        
        if (!"".equals(txtfBuscar.getText()) && txtfBuscar.getText().length() < 254) {
            preparTablaPrestamo();
            listaPrestamos.addAll(consulta.buscarPrestamo(txtfBuscar.getText().trim()));
            tablaPrestamo.setItems(listaPrestamos);
            if(listaPrestamos.isEmpty()){
                comboPrestamos.getSelectionModel().clearSelection();
                lblBusqueda.setText("No se han encontrado resultados.");
            }else{
                lblBusqueda.setText(null);
                comboPrestamos.getSelectionModel().clearSelection();
            }
        }
    }

    private void listarPrestamos() {

        preparTablaPrestamo();
        if (comboPrestamos.getSelectionModel().getSelectedIndex() == 0) {
            listaPrestamos.addAll(consulta.getListaPrestamo(1));
        } else if (comboPrestamos.getSelectionModel().getSelectedIndex() == 1) {
            listaPrestamos.addAll(consulta.getListaPrestamo(2));
        }
        tablaPrestamo.setItems(listaPrestamos);
        listaEjemplares.clear();
        lblDocumento.setText(null);
        lblNombre.setText(null);
        lblBusqueda.setText(null);
    }
    
    private void preparTablaPrestamo(){
        
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("apellido"));
        clmnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fecha"));
        clmnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
        listaPrestamos.clear();
    }
    
    private void listaEjemplares() {
        
        if (!listaEjemplares.isEmpty()) {
            listaEjemplaresRestantes.clear();
            for (Devolucion d : listaEjemplares) {
                if (d.getEstado().contains("Prestado")) {
                    listaEjemplaresRestantes.add(d);
                }
            }
        }
    }
    
    private void inicio() {
        
        hboxFecha.getChildren().add(fechaDevolucion);
        comboOpcion.getSelectionModel().selectFirst();
        prestamos.add("Vigentes");
        prestamos.add("Devueltos");
        comboPrestamos.setItems(prestamos);
        btnBorrar.setVisible(false);
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