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
import sabga.atributos.Multa;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */
public class VerMultasController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private Label lblValidar, lblTotal, lblPagado, lblAlcanzado, lblResultado;
    @FXML
    private Button btnBorrar;
    @FXML
    private TextField txtfMulta, txtfBuscar;
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableView<Multa> tablaMultas;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private TableView<Devolucion> tablaDevolucion;
    @FXML
    private TableColumn<Multa, String> clmnDocumento, clmnNombre, clmnApellido, clmnFechaReserva, clmnFechaPrestamo, clmnEstadoMulta,
            clmnEstadoPrestamo, clmnValorTotal, clmnValorPagado, clmnValorMulta, clmnFechaPago;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplar, clmnTitulo, clmnCodigo, clmnTipo, clmnClase, clmnFechaEntrega, clmnEstadoEjemplar;
    @FXML
    private TableColumn<Devolucion, String> clmnEjemplarDevo, clmnFechaEntregaDevo;
    @FXML
    private MenuItem menuDetalleMaterial, menuMultas, menuDetalle;

    private final ObservableList<String> multas;
    private final ObservableList<Multa> listaMultas;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<Devolucion> listaDevoluciones;
    private final Consultas consulta;
    private final Dialogo dialogo;
    
    public VerMultasController() {

        multas = FXCollections.observableArrayList();
        listaMultas = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        listaDevoluciones = FXCollections.observableArrayList();
        consulta = new Consultas();
        dialogo = new Dialogo();
    }

    @FXML
    public void listarLasMultas(ActionEvent evento) {
        listarMultas();
    }

    public void cargarDetallePrestamo() {
        cargarEjemplares();
    }

    @FXML
    public void pagarMulta(ActionEvent evento) {
        pagarMulta();
    }

    @FXML
    public void verDetalleMaterial(ActionEvent evento) {
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
    public void buscarMulta(ActionEvent evento){
        buscarMultas();
    }
    
    private void buscarMultas() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaMultas();
            listaMultas.addAll(consulta.getListadoMultas(4, txtfBuscar.getText().trim()));
            tablaMultas.setItems(listaMultas);
            if (listaMultas.isEmpty()) {
                comboListar.getSelectionModel().clearSelection();
                lblResultado.setText("No se han encontrado resultados.");
            } else {
                lblResultado.setText(null);
                comboListar.getSelectionModel().clearSelection();
                int total = 0, pagado = 0, maximo = 0;
                for (Multa m : listaMultas) {

                    total += m.getValor();
                    pagado += m.getValorPagado();
                    maximo += m.getValorTotal();
                }
                lblTotal.setText(String.valueOf(total));
                lblPagado.setText(String.valueOf(pagado));
                lblAlcanzado.setText(String.valueOf(maximo));
            }
        }
    }
        
    private void cargarEjemplares() {

        if (tablaMultas.getSelectionModel().getSelectedItem() != null) {
            prepararTablaEjemplares();
            listaEjemplares.addAll(consulta.getListaDetallePrestamoMulta(4,
                    listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getPrestamo()));
            listaDevoluciones.addAll(consulta.getListaDetalleDevolucion(listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getPrestamo()));
            tablaEjemplar.setItems(listaEjemplares);
            tablaDevolucion.setItems(listaDevoluciones);
            txtfMulta.setText(String.valueOf(listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getValor()));
       
        }
    }

    private void listarMultas() {

        if (!comboListar.getSelectionModel().isEmpty()) {
            prepararTablaMultas();
            int total = 0, pagado = 0, maximo = 0;
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                listaMultas.addAll(consulta.getListadoMultas(1, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                listaMultas.addAll(consulta.getListadoMultas(2, ""));
            } else if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                listaMultas.addAll(consulta.getListadoMultas(3, ""));
            }
            tablaMultas.setItems(listaMultas);
            for (Multa m : listaMultas) {
                
                total += m.getValor();
                pagado += m.getValorPagado();
                maximo += m.getValorTotal();
            }
            lblTotal.setText(String.valueOf(total));
            lblPagado.setText(String.valueOf(pagado));
            lblAlcanzado.setText(String.valueOf(maximo));
        }
    }

    private void prepararTablaEjemplares() {

        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
        clmnFechaEntrega.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
        clmnEstadoEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        listaEjemplares.clear();

        clmnEjemplarDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
        clmnFechaEntregaDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        listaDevoluciones.clear();

    }

    private void prepararTablaMultas() {
        
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Multa, String>("identificacion"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Multa, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Multa, String>("apellido"));
        clmnFechaReserva.setCellValueFactory(new PropertyValueFactory<Multa, String>("fechaReserva"));
        clmnFechaPrestamo.setCellValueFactory(new PropertyValueFactory<Multa, String>("fecha"));
        clmnEstadoPrestamo.setCellValueFactory(new PropertyValueFactory<Multa, String>("estadoPrestamo"));
        clmnValorTotal.setCellValueFactory(new PropertyValueFactory<Multa, String>("valorTotal"));
        clmnValorPagado.setCellValueFactory(new PropertyValueFactory<Multa, String>("valorPagado"));
        clmnValorMulta.setCellValueFactory(new PropertyValueFactory<Multa, String>("valor"));
        clmnFechaPago.setCellValueFactory(new PropertyValueFactory<Multa, String>("fechaPago"));
        clmnEstadoMulta.setCellValueFactory(new PropertyValueFactory<Multa, String>("estado"));
        listaMultas.clear();
        listaEjemplares.clear();
        listaDevoluciones.clear();
        txtfMulta.clear();
        lblResultado.setText(null);
        lblAlcanzado.setText(null);
        lblPagado.setText(null);
        lblTotal.setText(null);
                    
    }

    private void pagarMulta() {

        if (tablaMultas.getSelectionModel().getSelectedItem() != null) {
            mensajesError();
            if (validar()) {
                if (Integer.parseInt(txtfMulta.getText()) == listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getValor()) {
                    consulta.pagarMultas(2, listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getIdMulta(),
                            Integer.parseInt(txtfMulta.getText()));
                    resultadoRegistro();
                } else {
                    consulta.pagarMultas(1, listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getIdMulta(),
                            Integer.parseInt(txtfMulta.getText()));
                    resultadoRegistro();
                }
            }
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe seleccionar un préstamo de la lista", "", "Seleccione Préstamo");
        }
    }

    private boolean validar() {

        ConfirmarUsuario confirmar = new ConfirmarUsuario();
        if (confirmar.confirmarPagoMulta(txtfMulta.getText())) {
            if (Integer.parseInt(txtfMulta.getText()) > listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getValor()) {
                lblValidar.setText("El valor a pagar no puede se mayor al valor de la deuda.");
            }
            return Integer.parseInt(txtfMulta.getText()) <= listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getValor();
        } else {
            return false;
        }
    }

    private void mensajesError() {

        ValidarUsuario validar = new ValidarUsuario();
        validar.validarPagoMulta(txtfMulta.getText());
        lblValidar.setText(validar.getErrorMulta());
    }

    private void resultadoRegistro() {

        if (consulta.getMensaje() == null) {
            Utilidades.mensaje(null, "La multa se actualizó correctamente.", "", "Pagar Multa");
            listaMultas.clear();
            listaEjemplares.clear();
            listaDevoluciones.clear();
            txtfMulta.clear();
        } else {
            Utilidades.mensajeError(null, consulta.getMensaje(), "La multa no pudo ser actualizada.", "Error Pago Multa");
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

    private void detalleUsuario() {

        if (tablaMultas.getSelectionModel().getSelectedItem() != null) {
            menuDetalle.setDisable(true);
            dialogo.setCodigoMatricula(listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getIdentificacion());
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null, null, 5);
            menuDetalle.setDisable(false);
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }

    private void multasUsuario() {

        if (tablaMultas.getSelectionModel().getSelectedItem() != null) {
            menuMultas.setDisable(true);
            dialogo.setCodigoMatricula(listaMultas.get(tablaMultas.getSelectionModel().getSelectedIndex()).getIdentificacion());
            dialogo.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null, null, 17);
            menuMultas.setDisable(false);
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
     @FXML
    public void borrarCampo(ActionEvent evento){
        
        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
        lblResultado.setText(null);
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

        multas.add("Vigentes");
        multas.add("Pagadas");
        multas.add("Todas");
        comboListar.setItems(multas);
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
