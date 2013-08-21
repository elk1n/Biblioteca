
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
 * @author Elk1n
 */

public class EditarEMAController implements Initializable, ControlledScreen {
  
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Label validarNombreAutor, validarApellidosAutor, validarEditorial, validarMateria, validarClaseMaterial, validarTipoMaterial;
    
    @FXML private TextField campoNombreAutor, campoApellidosAutor, campoEditorial, campoMateria, campoTipoMaterial, campoClaseMaterial;
    
    @FXML private TitledPane acordeonAutor, acordeonEditorial, acordeonMateria, acordeonTipo, acordeonClase;
    
        
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
        
        ValidarMaterial validar = new ValidarMaterial(campoNombreAutor.getText(), campoApellidosAutor.getText(), campoEditorial.getText(), 
                                                      campoMateria.getText(), campoTipoMaterial.getText(), campoClaseMaterial.getText());
        
       if(acordeonAutor.isExpanded()){
           validar.validarAutorAC();
           validarNombreAutor.setText(validar.getErrorNombreAutor());
           validarApellidosAutor.setText(validar.getErrorApellidosAutor());
       }
       
       else if (acordeonEditorial.isExpanded()){
           validar.validarEditorialAC();
//           validarEditorial.setText(validar.getErrorNombreEditorial());
       }
       
       else if(acordeonMateria.isExpanded()){
           validar.validarMateriaAC();
           validarMateria.setText(validar.getErrorNombreMateria());
       }
       
        else if(acordeonTipo.isExpanded()){
       //    validar.validarNuevoTipoMaterial();
         //  validarTipoMaterial.setText(validar.getErrorNuevoTipoMaterial());
       } 
      
        else if(acordeonClase.isExpanded()){
          // validar.validarNuevaClaseMaterial();
           validarClaseMaterial.setText(validar.getErrorNuevaClaseMaterial());
       } 
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
