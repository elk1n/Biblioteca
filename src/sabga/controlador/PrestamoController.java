
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;


public class PrestamoController implements Initializable, ControlledScreen{
        
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private ComboBox comboBusqueda;
    
    @FXML private TextField campoBusqueda;
    
    @FXML private Label validarBusqueda;
              
    @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }

    @FXML
    public void buscarMaterial(ActionEvent evento){
        
       // ValidarMaterial validar = new ValidarMaterial(campoBusqueda.getText());
        
       // validar.validarEditorialAC();
       // validarBusqueda.setText(validar.getErrorNombreEditorial());
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        comboBusqueda.getSelectionModel().selectFirst();
    }    
}
