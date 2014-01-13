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

public class NuevoGradoController implements Initializable {

    private Stage dialogStage;
    @FXML
    private Label validarNuevoGrado;
    @FXML
    private TextField campoNuevoGrado;    
    private ValidarUsuario validarGrado;
    private ConfirmarUsuario nuevoGrado;
    private final Consultas consulta;
    
    public NuevoGradoController(){
       consulta = new Consultas();        
    }
       
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarGrado(ActionEvent evento){    
        procesarNuevoGrado();
    }
    
    public void procesarNuevoGrado(){
         
        validarCampos();
        nuevoGrado = new ConfirmarUsuario();
        if(nuevoGrado.confirmarGrado(campoNuevoGrado.getText())){
            consulta.registrarUnicoValor(6, campoNuevoGrado.getText().trim());
            if(consulta.getMensaje() != null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar el grado", "Error Registrar Grado");
            }
            else{
                Utilidades.mensaje(null, "Grado registrado correctamente", "Registrando Grado", "Registro Exitoso");
                campoNuevoGrado.clear();  
            }
        }
    }
        
    public void validarCampos() {

        validarGrado = new ValidarUsuario();
        validarGrado.validarNuevoGrado(campoNuevoGrado.getText());
        validarNuevoGrado.setText(validarGrado.getErrorGrado());
    }
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevoGrado.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
