
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import sabga.Sabga;
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;

/**
 *
 * @author Elk1n
 * 
 */

public class PaginaInicioController implements Initializable, ControlledScreen {

    private Sabga paginaPrincipal;
    private ScreensController controlador;
   

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;

     }
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;

    } 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
}
