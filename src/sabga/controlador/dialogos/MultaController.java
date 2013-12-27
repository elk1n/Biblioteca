
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Atributos;
import sabga.atributos.Devolucion;
import sabga.atributos.Multa;
import sabga.modelo.Consultas;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class MultaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label lblNombre, lblDocumento, lblCorreo;
    @FXML
    private TableView tablaPrestamo, tablaDetalle, tablaDevolucion;
    @FXML
    private TableColumn clmnDocumento, clmnNombre, clmnFecha, clmnEstado, clmnValor, clmnEjemplar, clmnTitulo, clmnCodigo,
                        clmnFechaDevo, clmnEstadoEjem, clmnEjemplarDevo, clmnFechaEntrega;
    
    private final Atributos atributo;
    private final Consultas consulta;
    private final ObservableList<Devolucion> listaEjemplares;
    private final ObservableList<Multa> listaPrestamos;
      
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public MultaController(){
        
        atributo = new Atributos();
        consulta = new Consultas();
        listaEjemplares = FXCollections.observableArrayList();
        listaPrestamos = FXCollections.observableArrayList();
    }
    
    public void setTablaDetalle(){
        detallePrestamo();
    }
    
    private void detalleMulta(){
    
        lblNombre.setText(atributo.getNombreUsuario()+" "+atributo.getApellidoUsuario());
        lblDocumento.setText(atributo.getDocumentoUsuario());
        lblCorreo.setText(atributo.getCorreoUsuario());
        tabla();
    }
    
    private void tabla(){
         
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Multa, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Multa, String>("nombre"));
        clmnFecha.setCellValueFactory(new PropertyValueFactory<Multa, String>("fecha"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Multa, String>("estado"));
        clmnValor.setCellValueFactory(new PropertyValueFactory<Multa, Double>("valor"));
        listaPrestamos.addAll(consulta.getMulta(Integer.parseInt(atributo.getDocumentoUsuario())));
        tablaPrestamo.setItems(listaPrestamos);
    }
    
    private void detallePrestamo(){
        
        if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
            prepararTablaDevolucion();
            listaEjemplares.addAll(consulta.getListaDetallePrestamo(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getPrestamo()));           
            tablaDetalle.setItems(listaEjemplares);        
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
        tablaDevolucion.setItems(consulta.getListaDetalleDevolucion(listaPrestamos.get(tablaPrestamo.getSelectionModel().getSelectedIndex()).getPrestamo()));
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       detalleMulta();
    }    
    
}
