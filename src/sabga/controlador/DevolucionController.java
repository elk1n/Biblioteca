
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class DevolucionController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Label validarBusqueda;
    
    @FXML private TextField campoBusqueda;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    public void buscarDevolucion(ActionEvent evento){
        
        buscar();
    }
    
    public void buscar(){
       
      //  ValidarMaterial validarBuscar = new ValidarMaterial(campoBusqueda.getText());
       // validarBuscar.validarEditorialAC();
       // validarBusqueda.setText(validarBuscar.getErrorNombreEditorial());
              
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
