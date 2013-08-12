
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Elk1n
 */

public class NuevoTipoUsuarioController implements Initializable {
    
    private Stage dialogStage;
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    private TextField campoNuevoTipoU;
    @FXML
    private Label validarNuevoTipoU;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
