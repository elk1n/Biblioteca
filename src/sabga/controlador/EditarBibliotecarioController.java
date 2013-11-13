
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Usuario;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Consultas;

/**
 * @author Elk1n 
 */

public class EditarBibliotecarioController implements Initializable, ControlledScreen {
    public Conexion con;
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    
    @FXML 
    private ComboBox comboEstado;  
    @FXML 
    private TableView tablaAdmin;    
    @FXML 
    private TableColumn tDocumento, tNombre, tApellido, tUsuario, tCorreo, tTelefono, tEstado, clmnTipo;
    private final ObservableList<Usuario> listaAdmin;
    private final Consultas consulta;
   
    public EditarBibliotecarioController(){         
        listaAdmin = FXCollections.observableArrayList();
        consulta = new Consultas();
    }   
    
    private void inicio(){
        
        tDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));        
        tNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        tUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuario"));
        tCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tTelefono.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefono"));
        tEstado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        listaAdmin.addAll(consulta.getListaBibliotecarios());
        tablaAdmin.setItems(listaAdmin);
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
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }
}
