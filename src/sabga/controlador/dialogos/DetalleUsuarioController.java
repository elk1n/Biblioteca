package sabga.controlador.dialogos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Devolucion;
import sabga.atributos.Ejemplar;
import sabga.atributos.Prestamo;
import sabga.atributos.Reserva;
import sabga.atributos.Usuario;
import sabga.modelo.Consultas;

/**
 * @author Elk1n
 */

public class DetalleUsuarioController {

    @FXML
    private Label lblCodigo, lblNombre, lblApellido, lblTelefono, lblCorreo, lblDireccion, lblCurso, lblGrupo, lblJornada,
                  lblTipoUsuario, lblEstado;
    @FXML
    private TableView<Prestamo> tablaPrestamo; 
    @FXML
    private TableView<Ejemplar> tablaDetallePres, tablaDetalleRese, tablaDetalleDevo;
    @FXML
    private TableView<Reserva>tablaReserva;
    @FXML
    private TableView<Devolucion> tablaDevolucion ;
    @FXML
    private TableColumn<Prestamo, String> clmnFechaPres, clmnEstadoPres, clmnReserva, clmnDocumento, clmnNombre;
    @FXML
    private TableColumn<Ejemplar, String> clmnTitulo, clmnEjemplar, clmnCodigo, clmnEstadoEjem, clmnTipo, clmnClaseMate,
                                          clmnFechaDevo, clmnTituloRese, clmnEjemplarRese, clmnCodigoRese, clmnEstadoEjemRese, 
                                          clmnTipoRese, clmnClaseRese, clmnTituloDevo, clmnEjemplarDevo, clmnFechaDevolucion,
                                          clmnFechaEntrega, clmnCodigoDevo, clmnEstadoEjemDevo, clmnTipoDevo, clmnClaseDevo; 
    @FXML
    private TableColumn<Reserva, String>clmnFechaRese, clmnEstadoRese;
    @FXML
    private TableColumn<Devolucion, String>  clmnFechaPresDevo, clmnEstadoPresDevo, clmnEstadoDevo, clmnDocumentoDevo, clmnNombreDevo;
    
    private Stage dialogStage;
    private final Consultas consulta;
    private final ObservableList<Prestamo> listaPrestamos;
    private final ObservableList<Reserva> listaReservas;
    private final ObservableList<Devolucion> listaDevolucion;
    private final ObservableList<Usuario> datosUsuario;
    
    public DetalleUsuarioController(){
        
        listaPrestamos = FXCollections.observableArrayList();
        listaReservas = FXCollections.observableArrayList();
        listaDevolucion = FXCollections.observableArrayList();
        datosUsuario = FXCollections.observableArrayList();
        consulta = new Consultas();
    }
    
    public void mostrarDetallePres(){
        detallePrestamo();
    }
    
    public void mostrarDetalleRese(){
        detalleReserva();
    }
    
    public void mostrarDetalleDevo(){
        detalleDevolucion();
    }
    
    public void datosDelUsuario(String idUsuario) {

        datosUsuario.addAll(consulta.getDatosUsuario(1, idUsuario));
        if (!datosUsuario.isEmpty()) {
            lblCodigo.setText(idUsuario);
            for (Usuario u : datosUsuario) {
                lblNombre.setText(u.getNombre());
                lblApellido.setText(u.getApellido());
                lblTelefono.setText(u.getTelefono());
                lblCorreo.setText(u.getCorreo());
                lblDireccion.setText(u.getDocumento());
                lblCurso.setText(u.getGrado());
                lblGrupo.setText(u.getCurso());
                lblJornada.setText(u.getJornada());
                lblTipoUsuario.setText(u.getTipo());
                lblEstado.setText(u.getEstado());
            }
        }
        prestamos(idUsuario);
        reservas(idUsuario);
        devoluciones(idUsuario);
    }
    
    private void prestamos(String idUsuario){
        
        listaPrestamos.clear();
        clmnFechaPres.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("documento"));
        clmnEstadoPres.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("nombre"));
        clmnReserva.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("apellido"));
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("fecha"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("estado"));
        listaPrestamos.addAll(consulta.getListaPrestamoUsusario(idUsuario));
        tablaPrestamo.setItems(listaPrestamos);
    }
   
    private void detallePrestamo(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){      
           clmnTitulo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
           clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
           clmnFechaDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
           clmnCodigo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
           clmnEstadoEjem.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
           clmnTipo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
           clmnClaseMate.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
           tablaDetallePres.setItems(consulta.getListaDetallePrestamoUsuario(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdPrestamo()));
        }
    }
    
    private void reservas(String idUsuario){
        
        listaReservas.clear();
        clmnFechaRese.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fecha"));
        clmnEstadoRese.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        listaReservas.setAll(consulta.getListaReservaUsuario(idUsuario));
        tablaReserva.setItems(listaReservas);
    }
    
    private void detalleReserva(){
        
        if(tablaReserva.getSelectionModel().getSelectedItem() != null){      
           clmnTituloRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
           clmnEjemplarRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
           clmnCodigoRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
           clmnEstadoEjemRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
           clmnTipoRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
           clmnClaseRese.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
           tablaDetalleRese.setItems(consulta.getListaDetalleReservaUsuario(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getId()));
        }
    }
   
    private void devoluciones(String idUsuario){
        
        listaDevolucion.clear();
        clmnFechaPresDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        clmnEstadoPresDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estadoPrestamo"));
        clmnEstadoDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estado"));
        clmnDocumentoDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("documento"));
        clmnNombreDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("bibliotecario"));
        listaDevolucion.addAll(consulta.getListaDevolucionUsuario(idUsuario));
        tablaDevolucion.setItems(listaDevolucion);        
    }
    
    private void detalleDevolucion() {

        if (tablaDevolucion.getSelectionModel().getSelectedItem() != null) {
            clmnTituloDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("titulo"));
            clmnEjemplarDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
            clmnFechaDevolucion.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fecha"));
            clmnFechaEntrega.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("fechaEntrega"));
            clmnCodigoDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("codigo"));
            clmnEstadoEjemDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
            clmnTipoDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("tipo"));
            clmnClaseDevo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("clase"));
            tablaDetalleDevo.setItems(consulta.getListaDetalleDevolucionUsuario(listaDevolucion.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getIdDevolucion(),
                                                                                listaDevolucion.get(tablaDevolucion.getSelectionModel().getSelectedIndex()).getIdPrestamo()));

        }

    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void cerrar(){
        this.dialogStage.close();
    }
        
    @FXML
    public void initialize() {

    }
}
