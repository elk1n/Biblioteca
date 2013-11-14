
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Consultas;

/**
 * @author Elk1n
 */

public class CuentaController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    @FXML
    private TextField txtfDocumento, txtfNombre, txtfApellido, txtfCorreo, txtfTelefono, txtfUsuario;
    @FXML
    private PasswordField passContrasenia, passNuevaContra, passConfirmacion;
    @FXML
    private Label lblDocumento, lblNombre, lblApellido, lblCorreo, lblTelefono, lblUsuario, lblContrasenia;
    private final Atributos atributos;
    private final Consultas consultar;
    
    public CuentaController (){
        atributos = new Atributos();
        consultar = new Consultas();
    }
    
    private void mapearDatos(){
        
        consultar.mapearBibliotecario(atributos.getUsuarioAdmin());
        txtfDocumento.setText(consultar.getDocumento());
        txtfNombre.setText(consultar.getNombre());
        txtfApellido.setText(consultar.getApellido());
        txtfCorreo.setText(consultar.getCorreo());
        txtfTelefono.setText(consultar.getTelefono());
        txtfUsuario.setText(consultar.getUsuario());
    }
    
    private void mensajes(){
    
    
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
        mapearDatos();
    }    

    
    
}
