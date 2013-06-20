
package sabga.controlador.dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Elk1n
 *
 */

public class NuevoAutorController  {

    private Stage dialogStage;
    
    @FXML private Label validarNombreAutor, validarApellidosAutor;
    @FXML private TextField campoNombreNuevoAutor, campoApellidosNuevoAutor;
 
    
 public void setDialogStage(Stage dialogStage) {
     
     this.dialogStage = dialogStage;	
 }
 
 public void validarCampos(ActionEvent evento){
     
     if (campoNombreNuevoAutor == null || campoNombreNuevoAutor.getText().equals("") || campoNombreNuevoAutor.getText().length()>45 || campoNombreNuevoAutor.getText().isEmpty()){
          
          validarNombreAutor.setText("Debe rellenar este campo");         
      }
     else{
         
         validarNombreAutor.setText(""); 
     }
     if (campoApellidosNuevoAutor == null || campoApellidosNuevoAutor.getText().equals("") || campoApellidosNuevoAutor.getText().length()>45 || campoApellidosNuevoAutor.getText().isEmpty()){
          
          validarApellidosAutor.setText("Debe rellenar este campo");         
      }
     else{
          validarApellidosAutor.setText("");
     }
 
 }
 
    @FXML
    public void initialize() {
        // TODO
    }    
}
