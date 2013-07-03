
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;

/**
 *
 * @author Elk1n
 
 */

public class EditarEMAController implements Initializable, ControlledScreen {
  
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Label validarNombreAutor, validarApellidosAutor, validarEditorial, validarMateria;
    
    @FXML private TextField campoNombreAutor, campoApellidosAutor, campoEditorial, campoMateria;
    
    @FXML private TitledPane acordeonAutor, acordeonEditorial, acordeonMateria;
    
        
    public EditarEMAController(){
        
    }
    

    @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    public void validarActualizarEMA(ActionEvent evento){
        
        ValidarMaterial validar = new ValidarMaterial(campoNombreAutor.getText(), campoApellidosAutor.getText(), campoEditorial.getText(), campoMateria.getText());
        
       if(acordeonAutor.isExpanded()){
           validar.validarAutorAC();
           validarNombreAutor.setText(validar.getErrorNombreAutor());
           validarApellidosAutor.setText(validar.getErrorApellidosAutor());
       }
       
       else if (acordeonEditorial.isExpanded()){
           validar.validarEditorialAC();
           validarEditorial.setText(validar.getErrorNombreEditorial());
       }
       
       else if(acordeonMateria.isExpanded()){
           validar.validarMateriaAC();
           validarMateria.setText(validar.getErrorNombreMateria());
       }      
      
    }
    
    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
