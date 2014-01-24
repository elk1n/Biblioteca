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
import sabga.atributos.Atributos;
import sabga.atributos.Ejemplar;
import sabga.atributos.Prestamo;
import sabga.atributos.Reserva;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class VerReservasController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;

    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableView<Reserva> tablaReserva;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private Button btnBorrar;
    @FXML
    private Label lblBusqueda;
    @FXML
    private TextField txtfBuscar;
    @FXML
    private TableColumn<Reserva, String> clmnDocumento, clmnNombre, clmnApellido, clmnCorreo, clmnGrado, clmnCurso, clmnJornada,
                                         clmnFechaReserva, clmnEstado;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplar, clmnTitulo, clmnCodigo, clmnIsbn, clmnEditorial, clmnTipo, clmnClase,
                                          clmnEstadoE;
    @FXML
    private MenuItem menuDetalle, menuMultas, menuDetalleMaterial;

    private final ObservableList<String> reservas;
    private final ObservableList<Reserva> listaReservas;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final Consultas consulta;
    private final Dialogo dialogo;
    private final Atributos atributo;

    public VerReservasController() {

        reservas = FXCollections.observableArrayList();
        listaReservas = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        consulta = new Consultas();
        dialogo = new Dialogo();
        atributo = new Atributos();
    }

    @FXML
    public void listarLasReservas(ActionEvent evento) {
        listarReservas();
    }

    @FXML
    public void bucarReservas(ActionEvent evento) {
        buscarReserva();
    }
    
    public void cargarEjemplar(){
        cargarEjemplares();
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

    private void buscarReserva() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaReserva();
            listaReservas.addAll(consulta.getListaReservas(5, txtfBuscar.getText().trim()));
            tablaReserva.setItems(listaReservas);
            if (listaReservas.isEmpty()) {
                comboListar.getSelectionModel().clearSelection();
                lblBusqueda.setText("No se han encontrado resultados.");
            } else {
                lblBusqueda.setText(null);
                comboListar.getSelectionModel().clearSelection();
            }
            //lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }

    private void listarReservas() {

        if (!comboListar.getSelectionModel().isEmpty()) {
            prepararTablaReserva();
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                listaReservas.addAll(consulta.getListaReservas(1, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                listaReservas.addAll(consulta.getListaReservas(2, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                listaReservas.addAll(consulta.getListaReservas(3, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
                listaReservas.addAll(consulta.getListaReservas(4, ""));
            }
            tablaReserva.setItems(listaReservas);
            lblBusqueda.setText(null);
            // lblTotal.setText(String.valueOf(listaPrestamos.size()));
        }
    }
    
    private void cargarEjemplares() {

        if (tablaReserva.getSelectionModel().getSelectedItem() != null) {
            prepararTablaEjemplares();
            listaEjemplares.addAll(consulta.getListaDetalleReservas(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getId()));
            tablaEjemplar.setItems(listaEjemplares);
            setDatosUsuario();
        }
    }

    private void prepararTablaReserva() {

        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Reserva, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Reserva, String>("correo"));
        clmnGrado.setCellValueFactory(new PropertyValueFactory<Reserva, String>("grado"));
        clmnCurso.setCellValueFactory(new PropertyValueFactory<Reserva, String>("curso"));
        clmnJornada.setCellValueFactory(new PropertyValueFactory<Reserva, String>("jornada"));
        clmnFechaReserva.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fecha"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        listaReservas.clear();
        listaEjemplares.clear();
    }

    private void prepararTablaEjemplares() {

        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
        clmnIsbn.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("editorial"));
        clmnEditorial.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
        clmnEstadoE.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        listaEjemplares.clear();
    }
    
    private void setDatosUsuario() {   
        
        atributo.setDocumentoUsuario(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getDocumento());
        atributo.setNombreUsuario(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getNombre());
        atributo.setApellidoUsuario(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getApellido());
        atributo.setCorreoUsuario(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getCorreo());
    }
    
    private void detalleUsuario(){
        
        if(tablaReserva.getSelectionModel().getSelectedItem() != null){            
            menuDetalle.setDisable(true);           
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            menuDetalle.setDisable(false);  
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void multasUsuario(){
        
        if(tablaReserva.getSelectionModel().getSelectedItem() != null){            
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
    public void borrarCampo(ActionEvent evento) {

        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
        lblBusqueda.setText(null);
    }

    @FXML
    public void mostrarBoton() {

        if ("".equals(txtfBuscar.getText())) {
            btnBorrar.setVisible(false);
        } else {
            btnBorrar.setVisible(true);
        }
    }

    private void inicio() {
        // hboxFechaI.getChildren().add(fechaInicio);
        // hboxFechaF.getChildren().add(fechaFinal);
        reservas.add("Vigentes");
        reservas.add("Canceladas");
        reservas.add("Pasaron a Préstamo");
        reservas.add("Todas");
        comboListar.setItems(reservas);
        btnBorrar.setVisible(false);
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
    }

    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }

}
