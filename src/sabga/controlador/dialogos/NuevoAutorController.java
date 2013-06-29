
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

public class NuevoAutorController extends ValidarMaterial{

    private Stage dialogStage;
    
    @FXML private Label validarNombreAutor, validarApellidosAutor;
    @FXML private TextField campoNombreNuevoAutor, campoApellidosNuevoAutor;
 
    
 public void setDialogStage(Stage dialogStage) {
     
     this.dialogStage = dialogStage;	
 }
 
 public void validarCampos(ActionEvent evento){
     
     if (validarCampoTexto(campoNombreNuevoAutor.getText(), 45) == false){
         
         validarNombreAutor.setText(getMensajeError());         
     }
    
     if (validarCampoTexto(campoApellidosNuevoAutor.getText(), 45) == false){
          
          validarApellidosAutor.setText(getMensajeError());         
      }
   
 }
 
    @FXML
    public void initialize() {
        // TODO
    }    
}
