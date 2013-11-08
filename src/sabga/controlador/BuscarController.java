
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.configuracion.ControlledScreen;

/**
 * @author Elk1n
 */

public class BuscarController implements Initializable,  ControlledScreen {
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    @FXML
    private Label lbl;
    
    public BuscarController(){
    
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {       
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public void setLabel(){
    
        Atributos dd = new Atributos();
        lbl.setText(dd.getDatoBusqueda());
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setLabel();
    }    
    
}
