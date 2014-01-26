
package sabga.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.scene.input.DataFormat.URL;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Reporte {
    
    private File archivo;
    private final File carpeta;
    private final Conexion con;
   
    
    public Reporte(){
        
         con = new Conexion();
         con.conectar();
         carpeta = new File(System.getenv("APPDATA")+"/Sabga/Reportes");
         if(!carpeta.exists()){
             carpeta.mkdir();
         }    
    }

    public void guardarReportePDF(InputStream archivoJasper, Map<String,Object> parametro){
    
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Reporte");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Archivo PDF (*.pdf)", "*.pdf"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            try {
                String name = file.getName();
                if (name.indexOf(".") == -1) {
                    name += ".pdf";
                    file = new File(file.getParentFile(), name);
                }
                JasperReport reporte = (JasperReport) JRLoader.loadObject(archivoJasper);
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getConexion());
                JRExporter exporter = new JRPdfExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(file.getPath()));
                exporter.exportReport();
            } catch (JRException  ex) {
                 Utilidades.mensajeError(null, ex.getMessage(), "Error al cargar el reporte.", "Error Reporte");
            }
        }        
    }
    
    public void crearReporteHTML(InputStream archivoJasper, Map<String,Object> parametro, String nombreArchivo) {        
        
        try {
            JasperReport reporte = (JasperReport) JRLoader.loadObject(archivoJasper);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con.getConexion());
            JRExporter exporter = new JRXhtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(carpeta+"/"+nombreArchivo));
            exporter.exportReport();
        } catch (JRException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al cargar el reporte.", "Error Reporte");
        }
    }
       
}
