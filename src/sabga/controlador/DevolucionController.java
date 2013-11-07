
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Devolucion;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class DevolucionController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML 
    private Label validarBusqueda;   
    @FXML 
    private TextField campoBusqueda;
    @FXML
    private HBox hboxFecha;
    @FXML
    private TableView tablaDevolucion;
    @FXML
    private TableColumn clmnEjemplar, clmnTitulo, clmnCodigo, clmnDevolver;
    ObservableList<Devolucion> datos = FXCollections.observableArrayList();
   
    private final DatePicker fechaDevolucion;
    
    public DevolucionController(){
        
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
    public void buscarDevolucion(ActionEvent evento){
        
        buscar();
    }
    
    public void buscar(){
       
      //  ValidarMaterial validarBuscar = new ValidarMaterial(campoBusqueda.getText());
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
         
         
       datos.add(new Devolucion(25, "Esto es una", "Esto es una maltida prueba"));
       clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
       clmnTitulo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("titulo"));
       clmnCodigo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("codigo"));
  
       clmnDevolver.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Devolucion, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Devolucion, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });
       
           clmnDevolver.setCellFactory(
                new Callback<TableColumn<Devolucion, Boolean>, TableCell<Devolucion, Boolean>>() {
            @Override
            public TableCell<Devolucion, Boolean> call(TableColumn<Devolucion, Boolean> p) {
                return new ButtonCell();
            }
         
        });
           
       tablaDevolucion.setItems(datos);
    }
       
}

class ButtonCell extends TableCell<Devolucion, Boolean> {
        final Button cellButton = new Button("Devolver");
         
        ButtonCell(){
             
            cellButton.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent t) {
                    System.out.println("Esto es una prueba.... si señor");
                    // do something when button clicked
                    //...
                }
            });
            
        }
        
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }


}