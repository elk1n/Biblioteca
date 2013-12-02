
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
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
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Devolucion;
import sabga.atributos.Ejemplar;
import sabga.atributos.Prestamo;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Consultas;

/**
 * @author Elk1n
 */

public class DevolucionController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML 
    private Label validarBusqueda, lblNombre, lblDocumento;  
    @FXML
    private ComboBox comboOpcion, comboPrestamos;
    @FXML 
    private TextField txtfBuscar;
    @FXML
    private HBox hboxFecha;
    @FXML
    private TableView tablaPrestamo, tablaDevolucion;
    @FXML
    private TableColumn clmnDocumento, clmnNombre, clmnApellido, clmnFechaPrestamo, clmnEstadoPrestamo, clmnEjemplar, 
                        clmnTitulo, clmnCodigo, clmnFechaDevolucion, clmnEstadoEjemplar;
    private final ObservableList<Prestamo> listaPrestamos; 
    private final ObservableList<Devolucion> listaEjemplares;
    private final ObservableList prestamos;
    private final DatePicker fechaDevolucion;
    private final Consultas consulta;
    
    public DevolucionController(){
       
       listaPrestamos = FXCollections.observableArrayList();
       listaEjemplares = FXCollections.observableArrayList();
       prestamos = FXCollections.observableArrayList();
       fechaDevolucion = new DatePicker();
       consulta = new Consultas();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
     
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
    
    private void detallePrestamo(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
            prepararTablaDevolucion();
            listaEjemplares.addAll(consulta.getListaDetallePrestamo(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo()));
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
    }
    
    private void preparTablaPrestamo(){
        
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("apellido"));
        clmnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fecha"));
        clmnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
        listaPrestamos.clear();
    }
    
    private void inicio() {
        hboxFecha.getChildren().add(fechaDevolucion);
        comboOpcion.getSelectionModel().selectFirst();
        prestamos.add("Vigentes");
        prestamos.add("Devueltos");
        comboPrestamos.setItems(prestamos);
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