
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
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
    
    @FXML private Label validarCodigoClasifiacionAC, validarTituloAC, validarAnioPublicacionAC, validarPublicacionAC, validarNumeroPaginasAC,
                        validarEditorialAC, validarEstadoAC, validarAutoresAC, validarMateriasAC;
    
    @FXML private TextField campoCodigoClasificacionAC, campoTituloAC, campoAnioPublicacionAC, campoPublicacionAC, campoNumeroPaginasAC,
                            campoEditorialAC, campoEjemplaresDisponiblesAC, campoHabilitadoAC, campoDeshabilitadoAC, campoMantenimientoAC,
                            campoAutor1AC, campoAutor2AC, campoAutor3AC, campoAutor4AC, campoAutor5AC, campoAutor6AC, campoAutor7AC, campoAutor8AC,
                            campoAutor9AC, campoAutor10AC, campoMateria1AC , campoMateria2AC, campoMateria3AC, campoMateria4AC, campoMateria5AC,
                            campoMateria6AC, campoMateria7AC, campoMateria8AC, campoMateria9AC, campoMateria10AC;
    
    @FXML private CheckBox checkboxHabilitadoAC, checkboxDeshabilitadoAC, checkboxMantenimientoAC;
    
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
        est= new Tooltip("Esto es una prueba de un Tooltip, esto es otra prueba, esto es otra otra prueba");
         botonNuevaEditorial.setTooltip(est); 
            MenuItem h = new MenuItem("Esto es una prueba de un menú contextual ");
            ContextMenu es = new ContextMenu(h);            
            botonNuevaEditorial.setContextMenu(es);
            
        
        }});
        
        
    }    

   
}
