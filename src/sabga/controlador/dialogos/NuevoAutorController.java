
package sabga.controlador.dialogos;

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

public class NuevoAutorController{

    private Stage dialogStage;
    
    @FXML private Label validarNombreAutor, validarApellidosAutor;
    @FXML private TextField campoNombreNuevoAutor, campoApellidosNuevoAutor;
 
    
 public void setDialogStage(Stage dialogStage) {
     
     this.dialogStage = dialogStage;	
 }
 
 @FXML
 public void guardarNuevoAutor(ActionEvent evento){
     
     nuevoAutor();
 
 }
 
  public void nuevoAutor(){
      
      ValidarMaterial validarNuevoAutor = new ValidarMaterial(campoNombreNuevoAutor.getText(), campoApellidosNuevoAutor.getText());
      
      validarNuevoAutor.validarAutorAC();
      validarNombreAutor.setText(validarNuevoAutor.getErrorNombreAutor());
      validarApellidosAutor.setText(validarNuevoAutor.getErrorApellidosAutor());
      
  }
 
 
    @FXML
    public void initialize() {
        // TODO
    }    
}
