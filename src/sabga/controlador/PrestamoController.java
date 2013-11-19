
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.configuracion.ControlledScreen;


public class PrestamoController implements Initializable, ControlledScreen{
        
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML 
    private ComboBox comboListar;
    @FXML 
    private TextField campoBusqueda;
    @FXML 
    private Label validarBusqueda;
    @FXML
    private HBox hboxFecha;
    private final DatePicker fechaDevolucion;
    
    
    public PrestamoController(){
       
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        hboxFecha.getChildren().add(fechaDevolucion);
        
    }    
}
