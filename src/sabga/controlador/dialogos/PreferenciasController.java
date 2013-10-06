
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sabga.modelo.Validacion;
import sabga.preferencias.Preferencias;

/**
 * @author Elk1n
 */
public class PreferenciasController implements Initializable {

    private Stage dialogStage;
    private final Preferencias configuracion;
    private final Validacion validar;
    
    @FXML
    private TextField txtfCorreo, txtfPuerto, txtfHost;    
    @FXML
    private PasswordField pswfClave;
    
    public PreferenciasController(){
        
        configuracion = new Preferencias();
        validar = new Validacion();                  
    }
    
    @FXML
    private void cambiarCorreo(ActionEvent evento){
    
      //  configuracion.setCorreo("elkinruiz@yahoo.com");
    }
    private void guardarCambios(){
    
        if(validar.validarCorreo(txtfCorreo.getText(), 90) && !txtfCorreo.getText().equals(configuracion.getCorreo())){
            configuracion.setCorreo(txtfCorreo.getText());
        }
    
    }
    
    @FXML
    private void cancelar(){
        
        dialogStage.close();
    }
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtfCorreo.setText(configuracion.getCorreo());
        pswfClave.setText(configuracion.getContrasenia());
        txtfHost.setText(configuracion.getHost());
        txtfPuerto.setText(configuracion.getPuerto());
    
    }    
    
}
