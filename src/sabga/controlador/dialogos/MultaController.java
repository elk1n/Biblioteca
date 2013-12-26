/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sabga.atributos.Atributos;

/**
 * FXML Controller class
 *
 * @author Elk1n
 */

public class MultaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label lblNombre, lblDocumento, lblCorreo;
    @FXML
    private TableView tablaPrestamo, tablaDetalle;
    @FXML
    private TableColumn clmnDocumento, clmnNombre, clmnFecha, clmnEstado, clmnValor, clmnEjemplar, clmnTitulo, clmnCodigo,
                        clmnFechaDevo, clmnFechaEntre, clmnEstadoEjem;
    
    private final Atributos atributo;
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public MultaController(){
        atributo = new Atributos();
    }
    
    private void detalleMulta(){
    
        lblNombre.setText(atributo.getNombreUsuario()+" "+atributo.getApellidoUsuario());
        lblDocumento.setText(atributo.getDocumentoUsuario());
        lblCorreo.setText(atributo.getCorreoUsuario());    
    }
    
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       detalleMulta();
    }    
    
}
