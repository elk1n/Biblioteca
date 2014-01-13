package sabga.controlador.dialogos;

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

public class NuevoAutorController {

    private Stage dialogStage;
    @FXML
    private Label validarNombreAutor, validarApellidosAutor;
    @FXML
    private TextField campoNombreNuevoAutor, campoApellidosNuevoAutor;    
    private ValidarMaterial validarNuevoAutor;
    private ConfirmarMaterial nuevoAutor;
    private final Consultas consulta;
        
    public NuevoAutorController(){ 
        consulta = new Consultas();
    }
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevoAutor(ActionEvent evento) {
        procesarNuevoAutor();          
    }
    
    public void procesarNuevoAutor(){
    
        validarCampos();
        nuevoAutor = new ConfirmarMaterial();
        if(nuevoAutor.confirmarNuevoAutor(campoNombreNuevoAutor.getText(), campoApellidosNuevoAutor.getText())){
            consulta.registrarAutor(campoNombreNuevoAutor.getText().trim(), campoApellidosNuevoAutor.getText().trim());
            if(consulta.getMensaje() != null){
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al tratar de registrar el autor", "Error Guardar Autor");
            }
            else{
                Utilidades.mensaje(null, "El autor se ha registrado correctamente", "Registrando Autor", "Registro Exitoso");
                campoApellidosNuevoAutor.clear();
                campoNombreNuevoAutor.clear();
            }
        } 
    }
            
    public void validarCampos() {
        
        validarNuevoAutor = new ValidarMaterial();
        validarNuevoAutor.validarNuevoAutor(campoNombreNuevoAutor.getText(),campoApellidosNuevoAutor.getText());
        validarNombreAutor.setText(validarNuevoAutor.getErrorNombreAutor());
        validarApellidosAutor.setText(validarNuevoAutor.getErrorApellidosAutor());

    }
    
    @FXML
    public void cancelar(ActionEvent evento){
    
        campoApellidosNuevoAutor.clear();
        campoNombreNuevoAutor.clear();
        this.dialogStage.close();    
    }

    @FXML
    public void initialize() {
            
    }
}
