
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Prestamo;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Consultas;

/**
 * FXML Controller class
 * @author Elk1n
 */

public class VerPrestamosController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private Label lblTotal;
    @FXML
    private HBox hboxFechaI, hboxFechaF;
    @FXML
    private Button btnBorrar;
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableView<Prestamo> tablaPrestamo;
    @FXML
    private TableColumn<Prestamo, String> clmnDocumento, clmnNombre, clmnNombreB, clmnApellido, clmnFechaPrestamo,
                                          clmnFechaReserva, clmnEstado, clmnGrado, clmnCurso, clmnJornada, clmnEstadoPrestamo;
    
    private final DatePicker fechaInicio;
    private final DatePicker fechaFinal;
    private Calendar calendario;
    private final SimpleDateFormat formato;
    private final ObservableList<String> prestamos;
    private final ObservableList<Prestamo> listaPrestamos;
    private final Consultas consulta;
    
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
        consulta = new Consultas();
    
    }
    
    @FXML
    public void listarLosPrestamos(ActionEvent evento){
        listarPrestamos();
    }
    
    private void listarPrestamos() {

        if (!comboListar.getSelectionModel().isEmpty()) {
            prepararTablaPrestamos();
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(2));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(3));
            }else if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                listaPrestamos.addAll(consulta.getListaDePrestamos(1));
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
