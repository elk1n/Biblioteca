
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
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
    private ComboBox comboBusqueda;
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

    @FXML
    public void buscarMaterial(ActionEvent evento){
        
       SimpleDateFormat fff= new SimpleDateFormat("YYYY-MM-dd");
       System.out.println(fff.format(fechaDevolucion.getSelectedDate()));
    
       // ValidarMaterial validar = new ValidarMaterial(campoBusqueda.getText());
        
       // validar.validarEditorialAC();
       // validarBusqueda.setText(validar.getErrorNombreEditorial());       
    }
    
    @FXML
    public void prueba(ActionEvent evento){
        Atributos esto = new Atributos();
        System.out.println(esto.getDatoBusqueda());
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        System.out.println("Esto es una prueba");
        hboxFecha.getChildren().add(fechaDevolucion);
    }    
}
