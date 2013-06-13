/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ActualizarMaterialController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    private ScreensController controlador;
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;
    }
    
     @Override
    public void setScreenParent(ScreensController screenParent) {
      
         controlador = screenParent;
    }
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   
}
