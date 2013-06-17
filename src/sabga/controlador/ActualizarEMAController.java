
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */
public class ActualizarEMAController implements Initializable, ControlledScreen {
  
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    
    public ActualizarEMAController(){
        
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
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
