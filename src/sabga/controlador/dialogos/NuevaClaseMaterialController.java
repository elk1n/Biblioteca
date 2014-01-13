
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;


/**
 * @author Elk1n
 */

public class NuevaClaseMaterialController implements Initializable {
    
    private Stage dialogStage;    
    @FXML
    private Label validacionClaseM;
    @FXML
    private TextField campoNombreClaseM;    
    private ValidarMaterial validarClaseM;
    private ConfirmarMaterial nuevaClaseM;
    private final Consultas consulta;
    
    public NuevaClaseMaterialController(){
        consulta = new Consultas();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarClaseMaterial(ActionEvent evento){        
        procesarNuevaClaseMaterial();       
    }
    
    public void procesarNuevaClaseMaterial(){
         
         validarCampos();
         nuevaClaseM = new ConfirmarMaterial();
        if(nuevaClaseM.confirmarNuevaClaseMaterial(campoNombreClaseM.getText())){           
             consulta.registrarUnicoValor(1, campoNombreClaseM.getText().trim());
             if(consulta.getMensaje()!= null){
                 Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar la nueva clase de material", "Error Registrar Clase Material");
             }
             else{
                 Utilidades.mensaje(null, "Clase de material registrada correctamente", "Registrando nueva clase de material", "Registro Exitoso");
                 campoNombreClaseM.clear();
             }
        }
    }
        
    public void validarCampos() {
        
        validarClaseM = new ValidarMaterial();
        validarClaseM.validarClaseMaterial(campoNombreClaseM.getText());
        validacionClaseM.setText(validarClaseM.getErrorClaseMaterial());
    }
    
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNombreClaseM.clear();
        this.dialogStage.close();    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
