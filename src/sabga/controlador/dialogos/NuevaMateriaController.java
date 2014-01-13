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

public class NuevaMateriaController {

    private Stage dialogStage;
    @FXML
    private Label validarNuevaMateria;
    @FXML
    private TextField campoNuevaMateria;
    private ValidarMaterial validarMateria;
    private ConfirmarMaterial nuevaMateria;
    private final Consultas consulta;
    
    public NuevaMateriaController(){
        consulta = new Consultas();        
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevaMateria(ActionEvent evento) {
        procesarNuevaMateria();
    }
    
    public void procesarNuevaMateria(){
        validarCampos();
         nuevaMateria = new ConfirmarMaterial();
        if(nuevaMateria.confirmarNuevaMateria(campoNuevaMateria.getText())){
            consulta.registrarUnicoValor(4, campoNuevaMateria.getText().trim());
            if(consulta.getMensaje()!= null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al tratar de registrar la materia", "Error Registrar Materia");
            }
            else{
                Utilidades.mensaje(null, "Materia registrada correctamente", "Registrando Materia", "Registro Exitoso");
                campoNuevaMateria.clear();  
            }
        }
    }
       
    public void validarCampos() {

        validarMateria = new ValidarMaterial();
        validarMateria.validarNuevaMateria(campoNuevaMateria.getText());
        validarNuevaMateria.setText(validarMateria.getErrorNombreMateria());
    }
    
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaMateria.clear();
        this.dialogStage.close();    
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    }
}
