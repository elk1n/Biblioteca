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

/*
 * @author Elk1n
 */
public class NuevoCursoController implements Initializable {

    private Stage dialogStage;
    @FXML
    private TextField campoNuevoCurso;
    @FXML
    private Label validarNuevoCurso;
    private ValidarUsuario validarCurso;
    private ConfirmarUsuario nuevaCurso;
    private final Consultas consulta;

    public NuevoCursoController() {
        consulta = new Consultas();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarCurso(ActionEvent evento){    
        procesarNuevoCurso();
    }
    
    public void procesarNuevoCurso() {

        validarCampos();
        nuevaCurso = new ConfirmarUsuario();
        if (nuevaCurso.confirmarCurso(campoNuevoCurso.getText())) {
            consulta.registrarUnicoValor(5, campoNuevoCurso.getText().trim());
            if (consulta.getMensaje() != null) {
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar el curso", "Error Registrar Curso");
            } else {
                Utilidades.mensaje(null, "Curso registrado correctamente", "Registrando Curso", "Registro Exitoso");
                campoNuevoCurso.clear();
            }
        }
    }
    
    public void validarCampos() {

        validarCurso = new ValidarUsuario();
        validarCurso.validarNuevoCurso(campoNuevoCurso.getText());
        validarNuevoCurso.setText(validarCurso.getErrorCurso());
    }

    @FXML
    public void cancelar(ActionEvent evento) {
        campoNuevoCurso.clear();
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
