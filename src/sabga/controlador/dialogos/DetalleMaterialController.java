
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import sabga.modelo.Consultas;

/**
 * @author Elk1n
 */

public class DetalleMaterialController implements Initializable {

    private Stage dialogStage;
    private int id;
    private final Consultas consulta;
    @FXML
    private ListView listaMaterias, listaAutores;
    
    @FXML
    private Label lblTitulo, lblCodigo, lblEditorial, lblPublicacion, lblAnio, lblClase, lblTipo, 
                  lblEjemplares, lblHabilitados, lblInhabilitados, lblMantenimiento, lblDisponible, lblPrestado, lblReservado;
    
    public DetalleMaterialController(){   
        consulta = new Consultas();
           
    }
    
    public void detalleMaterial(int id){
        
            consulta.mapearMaterial(id);
            lblTitulo.setText(consulta.getTitulo());
            lblCodigo.setText(consulta.getClasificacion());
            lblEditorial.setText(consulta.getEditorial());
            lblPublicacion.setText(consulta.getPublicacion());            
            lblAnio.setText(String.valueOf(consulta.getAnio()));
            lblClase.setText(consulta.getClaseMaterial());
            lblTipo.setText(consulta.getTipoMaterial());            
            lblEjemplares.setText(String.valueOf(consulta.getEjemplares()));
            listaMaterias.setItems(consulta.listaMaterias(id));
            listaAutores.setItems(consulta.listaAutoresMaterial(id));
            lblHabilitados.setText(String.valueOf(consulta.getHabilitado()));
            lblInhabilitados.setText(String.valueOf(consulta.getInhabilitado()));
            lblMantenimiento.setText(String.valueOf(consulta.getReparacion()));
            lblDisponible.setText(String.valueOf(consulta.getDisponible()));
            lblPrestado.setText(String.valueOf(consulta.getPrestado()));
            lblReservado.setText(String.valueOf(consulta.getReservado()));
    }
    
    public void setId(int codigo){
        this.id = codigo;
    }
   
    public void setDialogStage(Stage dialogStage) {     
        this.dialogStage = dialogStage;	 
    }
    
    @FXML
    private void cerrar(ActionEvent evento){
        this.dialogStage.close();
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
}
