
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.configuracion.ControlledScreen;

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
    
    public CuentaController (){
        atributos = new Atributos();
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
        System.out.println(atributos.getUsuarioAdmin());
    }    

    
    
}
