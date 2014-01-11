
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Autor;
import sabga.atributos.Ejemplar;
import sabga.atributos.Materia;
import sabga.modelo.Consultas;

/**
 * @author Elk1n
 */

public class DetalleMaterialController implements Initializable {

    private Stage dialogStage;
    private final Consultas consulta;
    @FXML
    private ListView <Materia>listaMaterias;
    @FXML
    private TableView tblEjemplares, tablaAutores;
    @FXML
    private TableColumn clmnEjemplar, clmnEstado, clmnDispo, clmnNombre, clmnApellido;
    @FXML
    private Label lblTitulo, lblCodigo, lblEditorial, lblPublicacion, lblAnio, lblClase, lblTipo;
                
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
        listaMaterias.setItems(consulta.listaMaterias(id));
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        clmnDispo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("disponibilidad"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
        tablaAutores.setItems(consulta.listaAutores(id));
        tblEjemplares.setItems(consulta.listaEjemplares(id));
        
    }
  
    public void setDialogStage(Stage dialogStage) {     
        this.dialogStage = dialogStage;	 
    }
    
    @FXML
    public void cerrar(ActionEvent evento){
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
