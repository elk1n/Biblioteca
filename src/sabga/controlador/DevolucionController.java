
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
import sabga.configuracion.ControlledScreen;

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
                        clmnTitulo, clmnCodigo, clmnFechaDevolucion;
    public ObservableList<Devolucion> datos;
   
    private final DatePicker fechaDevolucion;
    
    public DevolucionController(){
        
       datos = FXCollections.observableArrayList(); 
       fechaDevolucion = new DatePicker();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
 
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {       
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    public void buscarDevolucion(ActionEvent evento) throws IOException{
        
        //ventanaPrincipal.pruebaDato();
        // buscar();
    }
    @FXML
    public  void buscar(){
      
       //ValidarMaterial validarBuscar = new ValidarMaterial(campoBusqueda.getText());
       // validarBuscar.validarEditorialAC();
       // validarBusqueda.setText(validarBuscar.getErrorNombreEditorial());
              
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         hboxFecha.getChildren().add(fechaDevolucion);
         comboOpcion.getSelectionModel().selectFirst();
         
       datos.add(new Devolucion(25, "Esto es una", "Esto es una prueba"));
       datos.add(new Devolucion(25, "Esto es una...", "Esto es otra prueba"));
       clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
       clmnTitulo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("titulo"));
       clmnCodigo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("codigo"));
  
    }
}