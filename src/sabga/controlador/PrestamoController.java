
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
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;


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
    @FXML
    private TableView tablaMaterial, tablaEjemplar;
    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase, clmnTipo, clmnEjemplar, clmnEstado, clmnDispo;
    private final DatePicker fechaDevolucion;
    private final Consultas consulta;
    private final Seleccion select;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Ejemplar> listaEjemplares;
    
    
    public PrestamoController(){
       
       fechaDevolucion = new DatePicker();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
       listaMaterial = FXCollections.observableArrayList();
       listaEjemplares = FXCollections.observableArrayList();
       consulta = new Consultas();
       select = new Seleccion();
    }
    
    @FXML
    public void listarMaterial(ActionEvent evento){                        
        prepararTablaMaterial();
        listar();    
    }
    
    public void cargarEjemlar(){    
        mapearEjemplar();
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
           
    }
    
    private void listar(){    
        
        listaMaterial.addAll(consulta.getListaMaterial(comboListar.getSelectionModel().getSelectedItem().toString()));
        tablaMaterial.setItems(listaMaterial);
    }
    
    private void inicio(){
        comboListar.setItems(consulta.llenarLista(select.getListaTipoMaterial(), select.getTipoMaterial()));        
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
