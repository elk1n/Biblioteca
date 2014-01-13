
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
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class NuevoTipoUsuarioController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private TextField campoNuevoTipoU;
    @FXML
    private Label validarNuevoTipoU;
    
    private ValidarUsuario validarTipoUsuario;
    private ConfirmarUsuario nuevoTipoUsuario;
    private final Consultas consulta;
    
    public NuevoTipoUsuarioController(){
        consulta = new Consultas();
    }
        
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarTipoUsuario(ActionEvent evento){       
        procesarNuevoTipoUsuario();
    }
    
    public void procesarNuevoTipoUsuario(){
         
        validarCampos();
         nuevoTipoUsuario = new ConfirmarUsuario();
        if(nuevoTipoUsuario.confirmarTipoUsuario(campoNuevoTipoU.getText())){
            consulta.registrarUnicoValor(8, campoNuevoTipoU.getText().trim());
            if(consulta.getMensaje()!=null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar el nuevo tipo de usuario", "Error Registrar Tipo Usuario");
            }
            else{
                Utilidades.mensaje(null, "Nuevo tipo de usuario registrado correctamente", "Registrando Tipo de Usuario", "Registro Exitoso");
                campoNuevoTipoU.clear();  
            }
        }
    }
        
     public void validarCampos() {

        validarTipoUsuario = new ValidarUsuario();
        validarTipoUsuario.validarNuevoTipoUsuario(campoNuevoTipoU.getText());
        validarNuevoTipoU.setText(validarTipoUsuario.getErrorTipoUsuario());
    }
     
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevoTipoU.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
