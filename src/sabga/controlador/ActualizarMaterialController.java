
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class ActualizarMaterialController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Button botonNuevaEditorial;
    
    @FXML private  Tooltip est;
    
    public ActualizarMaterialController(){


    }
    
     @Override
    public void setScreenParent(ScreensController screenParent) {
      
         controlador = screenParent;
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
     public void dialogoNuevaEditorial(){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaEditorial();
     }
    
    @FXML
    public void dialogoNuevoAutor(){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevoAutor();
    }
    
    @FXML
    public void dialogoNuevaMateria(){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaMateria();
    }
    
    @FXML
    public void dialogoDetalleMaterial(){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.dialogoDetalleMaterial();        
    }
    
    /**
     * 
     * Initializes the controller class.
     *
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        Platform.runLater(new Runnable() {public void run() { 
        est= new Tooltip("Esto es una puta prueba, esto es una una prueba, esto es una prueba");
         botonNuevaEditorial.setTooltip(est); 
            MenuItem h = new MenuItem("esto es otra prueba");
            ContextMenu es = new ContextMenu(h);            
            botonNuevaEditorial.setContextMenu(es);
            
        
        }});
        
        
    }    

   
}
