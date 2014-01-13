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
public class NuevoTipoMaterialController implements Initializable {

    private Stage dialogStage;
    @FXML
    private Label validarNuevoTipoM;
    @FXML
    private TextField campoNuevoTipoM;
    private ValidarMaterial validarTipoMaterial;
    private ConfirmarMaterial nuevoTipoMaterial;
    private final Consultas consulta;
    
    public NuevoTipoMaterialController() {
        consulta = new Consultas();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevoTipoMaterial(ActionEvent evento) {
        procesarNuevoTipoMaterial();
    }

    public void procesarNuevoTipoMaterial() {

        validarCampos();
        nuevoTipoMaterial = new ConfirmarMaterial();
        if (nuevoTipoMaterial.confirmarNuevoTipoMaterial(campoNuevoTipoM.getText())) {
            consulta.registrarUnicoValor(7, campoNuevoTipoM.getText().trim());
            if (consulta.getMensaje() != null) {
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al registrar el nuevo tipo de material", "Error Registrar Tipo Material");
            } else {
                Utilidades.mensaje(null, "Tipo de material registrado correctamente", "Registrando Tipo Material", "Registro Exitoso");
                campoNuevoTipoM.clear();
            }
        }
    }
    
    public void validarCampos() {

        validarTipoMaterial = new ValidarMaterial();
        validarTipoMaterial.validarTipoMaterial(campoNuevoTipoM.getText());
        validarNuevoTipoM.setText(validarTipoMaterial.getErrorTipoMaterial());
    }

    @FXML
    public void cancelar(ActionEvent evento) {
        campoNuevoTipoM.clear();
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
