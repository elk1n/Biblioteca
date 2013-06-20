
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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

 public void validarCampos(){
     
     if (campoNuevaEditorial == null || campoNuevaEditorial.getText().equals("") || campoNuevaEditorial.getText().length()>45 || campoNuevaEditorial.getText().isEmpty()){
          
          validarNuevaEditorial.setText("Debe rellenar este campo");         
      }
     else{
         
         validarNuevaEditorial.setText(""); 
     }
 }
    /**
     * Initializes the controller class.
     */
        public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
