
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

public class NuevaJornadaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label validarNuevaJornada;
    @FXML
    private TextField campoNuevaJornada;   
    private ValidarUsuario validarJornada;
    private ConfirmarUsuario nuevaJornada;
    private final Consultas consulta;
        
    public NuevaJornadaController(){
        consulta = new Consultas();          
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarJoranda(ActionEvent evento){      
        procesarNuevaJornada();
    }
    
    public void procesarNuevaJornada(){
         
        validarCampos();
         nuevaJornada = new ConfirmarUsuario();
        if(nuevaJornada.confirmarJornada(campoNuevaJornada.getText())){
            consulta.registrarUnicoValor(3, campoNuevaJornada.getText().trim());
            if(consulta.getMensaje()!=null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar la jornada", "Error Registrar Jornada");
            }
            else{
                Utilidades.mensaje(null, "Jornada registrada correctamente", "Registrando Jornada", "Registro Exitoso");
                campoNuevaJornada.clear();  
            }
        }
    }
       
    public void validarCampos() {

        validarJornada = new ValidarUsuario();
        validarJornada.validarNuevaJornada(campoNuevaJornada.getText());
        validarNuevaJornada.setText(validarJornada.getErrorJornada());

    }
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaJornada.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
