
package sabga.controlador.dialogos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.Pane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sabga.configuracion.Utilidades;
import sabga.modelo.Reporte;

/**
 * FXML Controller class
 * @author Elk1n
 */

public class ReporteController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private WebView webReportes;
    @FXML
    private RadioButton radioAllUsers;
    private final Reporte reportes;
    private String archivoJasper;
    Map<String,Object> parametroJasper;
    private boolean archivoCargado = false;
    
    public ReporteController(){       
        reportes = new Reporte();       
    }
    
    @FXML
    public void generarReportesUsuario(ActionEvent evento){
        reportesUsuario();
    }
        
    @FXML
    public void cerrar(ActionEvent evento){
        this.dialogStage.close();
    }
    
    @FXML
    public void guardarReporte(ActionEvent evento){
        guardar();
    }
         
    private void reportesUsuario(){
        
        if(radioAllUsers.isSelected()){
              crearReporte("sabga/reportes/ReporteUsuarios.jasper", null, "ListaUsuarios.html");
        }        
    }
    
    private void crearReporte( String ruta, Map<String,Object> parametro, String archivoHTML){
    
        reportes.crearReporteHTML(ruta, parametro, archivoHTML);
        cargarReporte(archivoHTML);
        archivoJasper = ruta;
        parametroJasper = parametro;
        archivoCargado = true;
        
    }
    
    private void guardar() {

        if (archivoCargado) {
            reportes.guardarReportePDF(archivoJasper, parametroJasper);
        }
    }

    private void cargarReporte(String file){

      File  carpeta = new File(System.getenv("APPDATA")+"/Sabga/Reportes/"+file);   
        try {
            webReportes.getEngine().load(carpeta.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al cargar el reporte", "Error Reporte");
        }    
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
                
    }    
    
}
