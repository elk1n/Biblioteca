
package sabga.controlador;

import java.net.URL;
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
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Devolucion;
import sabga.atributos.Ejemplar;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */
public class VerDevolucionesController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableColumn<Devolucion, String> clmnDocumento, clmnNombre, clmnApellido, clmnBibliotecario, clmnFechaPrestamo,
                                          clmnFechaReserva, clmnEstadoPrestamo, clmnEstadoDevolucion;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplar, clmnTitulo, clmnCodigo, clmnTipo, clmnClase, clmnFechaD, clmnFechaE,
                                          clmnEstado;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private TableView<Devolucion> tablaDevolucion;
    @FXML
    private MenuItem menuDetalleMaterial, menuDetalle, menuMultas;
    @FXML
    private Label lblBusqueda;
    @FXML
    private TextField txtfBuscar;
    @FXML
    private Button btnBorrar;
    
    private final ObservableList<String> devoluciones;
    private final ObservableList<Devolucion> listaDevoluciones;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final Consultas consulta;
    private final Dialogo dialogo;
    
    public VerDevolucionesController(){
    
        devoluciones = FXCollections.observableArrayList();
        listaDevoluciones = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        consulta = new Consultas();
        dialogo = new Dialogo();
    }
    
    @FXML
    public void listarLasDevoluciones(ActionEvent evento){
        listarDevoluciones();
    }
    
    public void cargarEjemplar(){
        cargarEjemplares();
    }
    
    @FXML
    public void verDetalleMaterial(ActionEvent evento){
        detalleMaterial();
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
    public void bucarDevoluciones(ActionEvent evento){
        buscarDevolucion();
    }
    
    private void buscarDevolucion() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaDevoluciones();
            listaDevoluciones.addAll(consulta.getListaDevoluciones(4, txtfBuscar.getText().trim()));
            tablaDevolucion.setItems(listaDevoluciones);
            if (listaDevoluciones.isEmpty()) {
                comboListar.getSelectionModel().clearSelection();
                lblBusqueda.setText("No se han encontrado resultados.");
            } else {
                lblBusqueda.setText(null);
                comboListar.getSelectionModel().clearSelection();
            }
            //  lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }

    private void listarDevoluciones() {

        if (!comboListar.getSelectionModel().isEmpty()) {
            prepararTablaDevoluciones();
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                listaDevoluciones.addAll(consulta.getListaDevoluciones(1, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                listaDevoluciones.addAll(consulta.getListaDevoluciones(2, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                listaDevoluciones.addAll(consulta.getListaDevoluciones(3, ""));
            }
            lblBusqueda.setText(null);
            tablaDevolucion.setItems(listaDevoluciones);
           // lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }

    private void cargarEjemplares() {

        if (tablaDevolucion.getSelectionModel().getSelectedItem() != null) {
            prepararTablaEjemplares();
            listaEjemplares.addAll(consulta.getListaDetalleDevoluciones(
                                   listaDevoluciones.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getIdDevolucion(),
                                   listaDevoluciones.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getIdPrestamo()));
            tablaEjemplar.setItems(listaEjemplares);
        }
    }
    
    private void prepararTablaDevoluciones() {

        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("apellido"));
        clmnBibliotecario.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("bibliotecario"));
        clmnFechaReserva.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fechaReserva"));
        clmnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        clmnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estadoPrestamo"));
        clmnEstadoDevolucion.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estado"));
        listaDevoluciones.clear();
        listaEjemplares.clear();       
    }
    
    private void prepararTablaEjemplares(){
    
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("editorial"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
        clmnFechaD.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
        clmnFechaE.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        listaEjemplares.clear();    
    }
    
    private void detalleUsuario(){
        
        if(tablaDevolucion.getSelectionModel().getSelectedItem() != null){            
            menuDetalle.setDisable(true);
            dialogo.setCodigoMatricula(listaDevoluciones.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getDocumento());
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            menuDetalle.setDisable(false);  
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void multasUsuario(){
        
        if(tablaDevolucion.getSelectionModel().getSelectedItem() != null){            
            menuMultas.setDisable(true);
            dialogo.setCodigoMatricula(listaDevoluciones.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getDocumento());
            dialogo.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            menuMultas.setDisable(false); 
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void detalleMaterial() {

        if (tablaEjemplar.getSelectionModel().getSelectedItem() != null) {
            menuDetalleMaterial.setDisable(true);
            dialogo.setId(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getIdMaterial());
            dialogo.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", null, null, 4);
            menuDetalleMaterial.setDisable(false);
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
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

        devoluciones.add("Pendientes");
        devoluciones.add("Finalizadas");
        devoluciones.add("Todas");
        comboListar.setItems(devoluciones);
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
