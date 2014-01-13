package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

public class NuevaEditorialController {

    private Stage dialogStage;
    @FXML
    private Label validarNuevaEditorial;
    @FXML
    private TextField campoNuevaEditorial;    
    private ValidarMaterial validarEditorial;
    private ConfirmarMaterial nuevaEditorial;
    private final Consultas consulta;
    
    public NuevaEditorialController(){
        consulta = new Consultas();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevaEditorial(ActionEvent evento) {
        procesarNuevaEditorial();
    }
    
    public void procesarNuevaEditorial(){
         
        validarCampos();
         nuevaEditorial = new ConfirmarMaterial();
        if(nuevaEditorial.confirmarNuevaEditorial(campoNuevaEditorial.getText())){
            consulta.registrarUnicoValor(2, campoNuevaEditorial.getText().trim());
            if(consulta.getMensaje()!= null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar la editorial", "Error Registrar Editorial");
                campoNuevaEditorial.clear();
            }else{
                Utilidades.mensaje(null, "Editorial registrada correctamente", "Registrando Editorial", "Registro Exitoso");  
            }
        }
    }
      
    public void validarCampos() {
        validarEditorial = new ValidarMaterial();
        validarEditorial.validarNuevaEditorial(campoNuevaEditorial.getText());
        validarNuevaEditorial.setText(validarEditorial.getErrorEditorial());
    }

    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaEditorial.clear();
        this.dialogStage.close();    
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
