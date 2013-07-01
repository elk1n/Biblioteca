
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;


public class InicioSesionController implements Initializable{
    
    @FXML private ComboBox comboTipoAdmin;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboTipoAdmin.getSelectionModel().selectFirst();
    }    
    
    
    
}
