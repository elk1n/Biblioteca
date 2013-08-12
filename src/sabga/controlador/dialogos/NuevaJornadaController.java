
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

public class NuevaJornadaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label validarNuevaJornda;
    @FXML
    private TextField campoNuevaJorndad;
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
