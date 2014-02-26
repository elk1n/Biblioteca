
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Devolucion;
import sabga.atributos.Multa;
import sabga.atributos.Usuario;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class MultaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label lblNombre, lblDocumento, lblCorreo, lblMulta, lblValidar, lblCurso, lblGrupo, lblJornada;
    @FXML
    private TableView<Devolucion> tablaDevolucion, tablaDetalle;
    @FXML
    private TableView<Multa> tablaPrestamo;    
    @FXML
    private TableColumn<Multa, String> clmnDocumento, clmnNombre, clmnFecha, clmnEstado, clmnValor;
    @FXML
    private TableColumn<Devolucion, String> clmnEjemplar, clmnTitulo, clmnCodigo, clmnFechaDevo, clmnEstadoEjem,
                                            clmnEjemplarDevo, clmnFechaEntrega;
    @FXML
    private TextField txtfMulta;
    
    private final Consultas consulta;
    private final ObservableList<Devolucion> listaEjemplares, listaDevoluciones;
    private final ObservableList<Multa> listaPrestamos;
    private final ObservableList<Usuario> datosUsuario;
      
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public MultaController(){
        
        consulta = new Consultas();
        listaEjemplares = FXCollections.observableArrayList();
        listaPrestamos = FXCollections.observableArrayList();
        listaDevoluciones = FXCollections.observableArrayList();
        datosUsuario = FXCollections.observableArrayList();
    }
    
    public void setTablaDetalle(){
        detallePrestamo();
    }
    
    @FXML
    public void pagarMulta(ActionEvent evento){ 
        pagarMulta();
    }
    
    public void verMultasUsuario(String codigo_matricula){
        
        
        datosUsuario.addAll(consulta.getDatosUsuarioMulta(2, codigo_matricula));
        if (!datosUsuario.isEmpty()) {
            lblDocumento.setText(codigo_matricula);
            for (Usuario u : datosUsuario) {
                lblNombre.setText(u.getTipo());
                lblCorreo.setText(u.getDocumento());
                lblCurso.setText(u.getNombre());
                lblGrupo.setText(u.getApellido());
                lblJornada.setText(u.getCorreo());
            }
        }
        tabla(codigo_matricula);
    
    }
        
    private void tabla(String codigo_matricula){
         
        int multa=0;
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Multa, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Multa, String>("nombre"));
        clmnFecha.setCellValueFactory(new PropertyValueFactory<Multa, String>("fecha"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Multa, String>("estado"));
        clmnValor.setCellValueFactory(new PropertyValueFactory<Multa, String>("valor"));
        listaPrestamos.addAll(consulta.getMulta(codigo_matricula));
        tablaPrestamo.setItems(listaPrestamos);
        for(Multa p: listaPrestamos){
            multa += p.getValor();
        }     
        lblMulta.setText(String.valueOf(multa));
    }
    
    private void detallePrestamo(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
            prepararTablaDevolucion();
            listaEjemplares.addAll(consulta.getListaDetallePrestamo(1, listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getPrestamo()));           
            tablaDetalle.setItems(listaEjemplares);
            txtfMulta.setText(String.valueOf(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getValor()));
        }
    }
    
    private void prepararTablaDevolucion(){
    
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("titulo"));
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("codigo"));
        clmnFechaDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        clmnEstadoEjem.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("estado"));
        listaEjemplares.clear();  
        
        clmnEjemplarDevo.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
        clmnFechaEntrega.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha"));
        listaDevoluciones.clear();
        listaDevoluciones.addAll(consulta.getListaDetalleDevolucion(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getPrestamo()));
        tablaDevolucion.setItems(listaDevoluciones);
    }
    
    private void pagarMulta(){
    
        if (tablaPrestamo.getSelectionModel().getSelectedItem() != null) {
            mensajesError();
            if (validar()) {
                if(Integer.parseInt(txtfMulta.getText()) == listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getValor()){
                    consulta.pagarMultas(2, listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdMulta(),
                                         Integer.parseInt(txtfMulta.getText()));
                    resultadoRegistro();                    
                }else{
                    consulta.pagarMultas(1, listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getIdMulta(),
                                         Integer.parseInt(txtfMulta.getText()));
                   resultadoRegistro();
                }
            }
        } else {
            Utilidades.mensajeAdvertencia(dialogStage, "Debe seleccionar un préstamo de la lista", "", "Seleccione Préstamo");
        }
  
    }
    
    private boolean validar(){
    
        ConfirmarUsuario confirmar = new ConfirmarUsuario();
        if(confirmar.confirmarPagoMulta(txtfMulta.getText())){
            if(Integer.parseInt(txtfMulta.getText()) > listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getValor()){
                lblValidar.setText("El valor a pagar no puede se mayor al valor de la deuda.");
            }
            return Integer.parseInt(txtfMulta.getText()) <= listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getValor();        
        }else{
            return false;
        }    
    }
    
    private void mensajesError(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarPagoMulta(txtfMulta.getText());
        lblValidar.setText(validar.getErrorMulta());       
    }
    
    @FXML
    public void cerrar(ActionEvent evento){    
        this.dialogStage.close();    
    }
    
    private void resultadoRegistro() {

        if (consulta.getMensaje() == null) {
            Utilidades.mensaje(null, "La multa se actualizó correctamente.", "", "Pagar Multa");
            listaPrestamos.clear();
            listaEjemplares.clear();
            listaDevoluciones.clear();
            tabla(lblDocumento.getText());
            txtfMulta.clear();
        } else {
            Utilidades.mensajeError(null, consulta.getMensaje(), "La multa no pudo ser actualizada.", "Error Pago Multa");
        }
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    
    
}
