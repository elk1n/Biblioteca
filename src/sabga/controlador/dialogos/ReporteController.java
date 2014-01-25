/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sabga.controlador.dialogos;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */
public class ReporteController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private WebView webReportes;
    
    @FXML
    public void cerrar(ActionEvent evento){
        this.dialogStage.close();
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
        
//        File f = new File("index.html");
//        try {
//            webReportes.getEngine().load(f.toURI().toURL().toString());
//        } catch (MalformedURLException ex) {
//           
//        }
    }    
    
}
