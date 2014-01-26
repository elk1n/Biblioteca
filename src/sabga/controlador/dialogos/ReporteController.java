
package sabga.controlador.dialogos;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
    private Pane panelOpciones, panelReporte;
    @FXML
    private Button btnReporte, btnAtras;
    private final Reporte reportes;
    private File carpeta;
    private String archivo;
     private final String ruta = System.getenv("APPDATA")+"/Sabga/Preferencias.properties";
    
    public ReporteController(){       
        reportes = new Reporte();       
    }
    
    @FXML
    public void handleBotonReporte(){
        botonReporte();
    }
    
    @FXML
    public void volverAtras(){
        atras();
    }
    
    @FXML
    public void cerrar(ActionEvent evento){
        this.dialogStage.close();
    }
     
    private void botonReporte(){
        
        if(btnReporte.getText().equals("Ver Reporte")){
            verReporte();
            verUsuarios();
        }else{
            guardar();
        }        
    }
    @FXML
    public void verUsuarios(){
        
        verReporte();
        InputStream strea = ClassLoader.getSystemResourceAsStream("sabga/reportes/ReporteUsuarios.jasper");
              //  getClass().getResourceAsStream("ReporteUsuarios.jasper");
        //InputStream stream = this.getClass().getResourceAsStream("reportes/ReporteUsuarios.jasper");
       
      //  String master = System.getProperty("user.dir")+ "/reportes/ReporteUsuarios.jasper";
       // File a = new File()
        reportes.crearReporteHTML(strea, null, "ListaUsuarios.html");
        cargarReporte("ListaUsuarios.html");
        
    }
    
    private void guardar(){
          InputStream stream = ClassLoader.getSystemResourceAsStream("sabga/reportes/ReporteUsuarios.jasper");
       String master = System.getProperty("user.dir")+ "/reportes/ReporteUsuarios.jasper";
        reportes.guardarReportePDF(stream, null);
    }
   
    private void cargarReporte(String file){

        archivo = file;
        carpeta = new File(System.getenv("APPDATA")+"/Sabga/Reportes/"+archivo);   
        try {
            webReportes.getEngine().load(carpeta.toURI().toURL().toString());
        } catch (MalformedURLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al cargar el reporte", "Error Reporte");
        }    
    }
        
    private void verReporte(){
        
        panelOpciones.setDisable(true);
        panelOpciones.setVisible(false);
        panelReporte.setDisable(false);
        panelReporte.setVisible(true);
        btnReporte.setText("Guardar Reporte");
        btnAtras.setDisable(false);
        btnAtras.setVisible(true);    
    }
    
    private void atras(){
        
        panelOpciones.setDisable(false);
        panelOpciones.setVisible(true);
        panelReporte.setDisable(true);
        panelReporte.setVisible(false);
        btnReporte.setText("Ver Reporte");
        btnAtras.setDisable(true);
        btnAtras.setVisible(false);
        
    }
    
    private void inicio(){
        atras();      
    }
    
    private File setArchivoReporte(String ruta){    
        File reporte = new File(ruta);
        return reporte;
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
        
        inicio();
        
    }    
    
}
