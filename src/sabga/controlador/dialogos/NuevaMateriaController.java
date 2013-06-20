/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class NuevaMateriaController {

   private Stage dialogStage;
   
   @FXML private Label validarNuevaMateria;
   @FXML private TextField campoNuevaMateria;
 
    
 public void setDialogStage(Stage dialogStage) {
     
     this.dialogStage = dialogStage;	
 }

 public void validarCampos(){
     
     if (campoNuevaMateria== null || campoNuevaMateria.getText().equals("") || campoNuevaMateria.getText().length()>45 || campoNuevaMateria.getText().isEmpty()){
          
          validarNuevaMateria.setText("Debe rellenar este campo");         
      }
     else{
         
         validarNuevaMateria.setText(""); 
     }
 }
 
 
 
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
