
package sabga.controlador.dialogos;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import np.com.ngopal.control.AutoFillTextBox;
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
    private RadioButton radioAllUsers, radioEstadoUsuario, radioListarGrado, radioTipoMaterial, radioAutor, radioMateria,
                        radioEstadoPrestamos, radioFecha, radioEditorial;
    @FXML
    private ComboBox<String> comboListarUsuarios, comboEstadoUsuario, comboGrado, comboTipoMaterial, comboEstadoPrestamos;
    @FXML
    private HBox hboxMaterias, hboxAutores, hboxFechaI, hboxFechaF, hboxEditoriales;
    private final Reporte reportes;
    private String archivoJasper;
    Map<String,Object> parametroJasper;
    private boolean archivoCargado = false;
    private final Consultas consulta;
    private final AutoFillTextBox<String> autores, materias, editoriales;
    private final ObservableList<String> listaBusquedaMaterias, listaBusquedaAutores, listaBusquedaEditoriales;
    private final DatePicker fechaInicio;
    private final DatePicker fechaFinal;
    private final SimpleDateFormat formato;

    public ReporteController() {
        
        reportes = new Reporte();
        consulta = new Consultas();
        autores = new AutoFillTextBox<>();
        materias = new AutoFillTextBox<>();
        editoriales = new AutoFillTextBox<>();
        parametroJasper = new HashMap<>();
        listaBusquedaAutores = FXCollections.observableArrayList();
        listaBusquedaMaterias = FXCollections.observableArrayList();
        listaBusquedaEditoriales = FXCollections.observableArrayList();
        fechaInicio = new DatePicker();
        fechaFinal = new DatePicker();
        formato = new SimpleDateFormat("YYYY-MM-dd");
        fechaInicio.setDateFormat(formato);
        fechaFinal.setDateFormat(formato);
        fechaInicio.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
        fechaInicio.getStylesheets().add("sabga/vista/css/DatePicker.css");
        fechaFinal.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
        fechaFinal.getStylesheets().add("sabga/vista/css/DatePicker.css");
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
    
    @FXML
    public void generarReportePrestamos(ActionEvent evento){
        reportesPrestamos();
    }

    private void reportesPrestamos(){
        
        parametroJasper.clear();
        if(radioEstadoPrestamos.isSelected() && comboEstadoPrestamos.getSelectionModel().getSelectedItem() != null){  
            
            if(radioEstadoPrestamos.isSelected() && comboEstadoPrestamos.getSelectionModel().getSelectedIndex() == 0){
               parametroJasper.put("estado_prestamo", 1);
               crearReporte("sabga/reportes/ReportePrestamosVigentes.jasper", "ListadoPrestamosEstado.html"); 
               
            }else if(radioEstadoPrestamos.isSelected() && comboEstadoPrestamos.getSelectionModel().getSelectedIndex() == 1){
               parametroJasper.put("estado_prestamo", 2);
               crearReporte("sabga/reportes/ReportePrestamosVigentes.jasper", "ListadoPrestamosEstado.html"); 
               
            }else if(radioEstadoPrestamos.isSelected() && comboEstadoPrestamos.getSelectionModel().getSelectedIndex() == 2){
               crearReporte("sabga/reportes/ReportePrestamos.jasper", "ListadoPrestamosEstado.html"); 
            }
        }
        
        if(radioFecha.isSelected() && fechaInicio.getSelectedDate() != null){
        
            if(fechaInicio.getSelectedDate() != null && fechaFinal.getSelectedDate() == null){
                parametroJasper.put("fecha_inicial", fechaInicio.getSelectedDate());
                parametroJasper.put("fecha_final", fechaInicio.getSelectedDate());
                crearReporte("sabga/reportes/ReportePrestamosXFecha.jasper", "ListadoPrestamosFecha.html"); 
            }            
        }
        if(radioFecha.isSelected() && fechaInicio.getSelectedDate() != null && fechaFinal.getSelectedDate() != null){
            if(fechaInicio.getSelectedDate().before(fechaFinal.getSelectedDate())){
                parametroJasper.put("fecha_inicial", fechaInicio.getSelectedDate());
                parametroJasper.put("fecha_final", fechaFinal.getSelectedDate());
                crearReporte("sabga/reportes/ReportePrestamosXFecha.jasper", "ListadoPrestamosFecha.html"); 
            
            }else if(fechaInicio.getSelectedDate().equals(fechaFinal.getSelectedDate())){
                parametroJasper.put("fecha_inicial", fechaInicio.getSelectedDate());
                parametroJasper.put("fecha_final", fechaInicio.getSelectedDate());
                crearReporte("sabga/reportes/ReportePrestamosXFecha.jasper", "ListadoPrestamosFecha.html"); 
            }else if(fechaInicio.getSelectedDate().after(fechaFinal.getSelectedDate())){
                parametroJasper.put("fecha_inicial", fechaFinal.getSelectedDate());
                parametroJasper.put("fecha_final", fechaInicio.getSelectedDate());
                crearReporte("sabga/reportes/ReportePrestamosXFecha.jasper", "ListadoPrestamosFecha.html"); 
            }
        }
    
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
        if (radioAutor.isSelected() && autores.getTextbox().getText() != null) {
            if (listaBusquedaAutores.indexOf(autores.getText()) != -1) {
                 parametroJasper.put("nombre_autor", autores.getText());
                 crearReporte("sabga/reportes/ReporteLibrosXAutor.jasper", "ListadoLibrosPorAutor.html");            
            }            
        }
        if (radioMateria.isSelected() && materias.getTextbox().getText() != null) {
            if (listaBusquedaMaterias.indexOf(materias.getText()) != -1) {
                parametroJasper.put("nombre_materia", materias.getText());
                crearReporte("sabga/reportes/ReporteMaterialXMateria.jasper", "ListadoMaterialPorMateria.html");
            }
        }
        if (radioEditorial.isSelected() && editoriales.getTextbox().getText() != null) {
            if (listaBusquedaEditoriales.indexOf(editoriales.getText()) != -1) {
                parametroJasper.put("nombre_editorial", editoriales.getText());
                crearReporte("sabga/reportes/ReporteMaterialXEditorial.jasper", "ListadoMaterialPorEditorial.html");
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
        comboEstadoPrestamos.getItems().add("Vigentes");
        comboEstadoPrestamos.getItems().add("Devueltos");
        comboEstadoPrestamos.getItems().add("Todos");
        comboGrado.setItems(consulta.llenarLista(7));
        materias.setPrefSize(240, 30);
        autores.setPrefSize(240, 30);
        editoriales.setPrefSize(240, 30);
        comboTipoMaterial.setItems(consulta.llenarLista(2));
        listaBusquedaMaterias.addAll(consulta.llenarLista(3));
        listaBusquedaAutores.addAll(consulta.llenarLista(12));
        listaBusquedaEditoriales.addAll(consulta.llenarLista(4));
        materias.getTextbox().setPromptText("Buscar Materia");
        autores.getTextbox().setPromptText("Buscar Autor (nombre)");
        editoriales.getTextbox().setPromptText("Buscar Editorial");
        materias.setData(listaBusquedaMaterias);
        autores.setData(listaBusquedaAutores);
        editoriales.setData(listaBusquedaEditoriales);
        hboxMaterias.getChildren().add(materias);
        hboxAutores.getChildren().add(autores);
        hboxEditoriales.getChildren().add(editoriales);
        hboxFechaI.getChildren().add(fechaInicio);
        hboxFechaF.getChildren().add(fechaFinal);
        
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
