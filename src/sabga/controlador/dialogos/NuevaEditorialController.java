
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sabga.modelo.ValidarMaterial;

/**
 *
 * @author Elk1n
 * 
 */
public class NuevaEditorialController {
    
     private Stage dialogStage;
     
     @FXML private Label validarNuevaEditorial;
     @FXML private TextField campoNuevaEditorial;
    
 public void setDialogStage(Stage dialogStage) {
     
     this.dialogStage = dialogStage;	
 }

 @FXML
 public void guardarNuevaEditorial(ActionEvent evento){
     
     nuevaEditorial();
 }
 
 
 public void nuevaEditorial(){
     
     ValidarMaterial validarEditorial = new ValidarMaterial(campoNuevaEditorial.getText());
     
     validarEditorial.validarEditorialAC();
     validarNuevaEditorial.setText(validarEditorial.getErrorNombreEditorial());
     
 }
    /**
     * Initializes the controller class.
     */
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
