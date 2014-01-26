
package sabga.controlador.dialogos;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
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
    private RadioButton radioAllUsers, radioEstadoUsuario, radioListarGrado, radioTipoMaterial;
    @FXML
    private ComboBox<String> comboListarUsuarios, comboEstadoUsuario, comboGrado, comboTipoMaterial;
    private final Reporte reportes;
    private String archivoJasper;
    Map<String,Object> parametroJasper;
    private boolean archivoCargado = false;
    private final Consultas consulta;
    
    public ReporteController(){       
        reportes = new Reporte(); 
        consulta = new Consultas();
        parametroJasper =  new HashMap<>();
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
    
    @FXML
    public void generarReportesMaterial(ActionEvent evento){
        reportesMaterial();
    }

    private void reportesMaterial() {
        parametroJasper.clear();
        if (radioTipoMaterial.isSelected() && comboTipoMaterial.getSelectionModel().getSelectedItem() != null) {

            if (radioTipoMaterial.isSelected() && comboTipoMaterial.getSelectionModel().getSelectedItem().toLowerCase().contains("libro")) {
                crearReporte("sabga/reportes/ReporteLibros.jasper", "ListaLibros.html");
            }else{
               parametroJasper.put("tipo_material", comboTipoMaterial.getSelectionModel().getSelectedItem());
               crearReporte("sabga/reportes/ReporteOtroMaterial.jasper", "ListadoOtroMaterial.html"); 
            }
        }

    }
    
    private void reportesUsuario() {

        parametroJasper.clear();
        if (radioAllUsers.isSelected() && comboListarUsuarios.getSelectionModel().getSelectedItem() != null) {
            if (comboListarUsuarios.getSelectionModel().getSelectedItem().equals("Todos")) {
                crearReporte("sabga/reportes/ReporteUsuarios.jasper", "ListaUsuarios.html");

            } else {
                parametroJasper.put("tipo_de_usuario", comboListarUsuarios.getSelectionModel().getSelectedItem());
                crearReporte("sabga/reportes/ReporteUsuariosXTipo.jasper", "ListaUsuariosXTipo.html");
            }
        }

        if (radioEstadoUsuario.isSelected() && comboEstadoUsuario.getSelectionModel().getSelectedItem() != null) {
            if (comboEstadoUsuario.getSelectionModel().getSelectedIndex() == 0) {
                parametroJasper.put("estado_usuario", 1);
                crearReporte("sabga/reportes/ReporteUsuariosXEstado.jasper", "ListaUsuariosXEstado.html");
                
            } else if(comboEstadoUsuario.getSelectionModel().getSelectedIndex() == 1){
                parametroJasper.put("estado_usuario", 2);
                crearReporte("sabga/reportes/ReporteUsuariosXEstado.jasper", "ListaUsuariosXEstado.html");
                
            }else if(comboEstadoUsuario.getSelectionModel().getSelectedIndex() == 2){
                crearReporte("sabga/reportes/ReporteUsuariosMultados.jasper", "ListaUsuariosMultados.html");
            }
        }
        if (radioListarGrado.isSelected() && comboGrado.getSelectionModel().getSelectedItem() != null) {
            parametroJasper.put("grado", comboGrado.getSelectionModel().getSelectedItem());
            crearReporte("sabga/reportes/ReporteUsuariosXGrado.jasper", "ListaUsuariosXGrado.html");
        }
        
    }

    private void crearReporte( String ruta, String archivoHTML){
            
        reportes.crearReporteHTML(ruta, parametroJasper, archivoHTML);
        cargarReporte(archivoHTML);
        archivoJasper = ruta;      
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
    
    private void inicio(){
        
        comboListarUsuarios.setItems(consulta.llenarLista(6));        
        comboListarUsuarios.getItems().add("Todos");
        comboEstadoUsuario.getItems().add("Habilitados");
        comboEstadoUsuario.getItems().add("Inhabilitados");
        comboEstadoUsuario.getItems().add("Multados");
        comboGrado.setItems(consulta.llenarLista(7));
        comboTipoMaterial.setItems(consulta.llenarLista(2));
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
