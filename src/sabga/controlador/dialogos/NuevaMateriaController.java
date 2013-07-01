/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

 @FXML
 public void guardarNuevaMateria(ActionEvent evento){
     
   nuevaMateria();
  
 }
 
 public void nuevaMateria(){
     
     ValidarMaterial validarMateria = new ValidarMaterial(campoNuevaMateria.getText());
     
     validarMateria.validarEditorialAC();
     validarNuevaMateria.setText(validarMateria.getErrorNombreEditorial());
 }
 
 
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
}
