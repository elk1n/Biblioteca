
package sabga.controlador.dialogos;

import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import sabga.configuracion.Utilidades;

/*
 * @author Elk1n
 */

public class CodigoBarrasController implements Initializable {
    
    private Stage dialogStage;
    private String codigoBarras;
    private BufferedImage symbol;
    private int nCopias;
    
    @FXML
    private ImageView imagenCodigoBarras;
    @FXML
    private Label lblMensajes, lblTitulo;
    private Object bais;
        
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setCodigoBarras(String codigo){    
        codigoBarras=codigo;
    }
    
    
    public void pintarCodigo(int copias){
        
        nCopias = copias;
        Code128Bean codigo = new Code128Bean();
        //Code39Bean codigo = new Code39Bean();
        final int dpi = 326;
        codigo.doQuietZone(false);
        try {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            codigo.generateBarcode(canvas, codigoBarras);
            canvas.finish();
            symbol = canvas.getBufferedImage();
            Image image = SwingFXUtils.toFXImage(symbol, null);
            imagenCodigoBarras.setImage(image);

        } catch (IOException e) {
             Utilidades.mensajeError(null, e.getMessage(), "Error al crear el código de barras", "Error Código Barras");
        }
    }
     
    @FXML
    public void imprimir( ActionEvent evento){
        imprimirCodigo(symbol, nCopias);     
    }
    
    @FXML
    public void guardarCodigo(ActionEvent evento){     
        guardarCodigo();    
    }
    
    public void imprimirCodigo(BufferedImage imagen, int copias){
              
    try{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "PNG", baos);
        byte[] data = baos.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
        PrinterJob pj = PrinterJob.getPrinterJob();
        boolean okay = pj.printDialog(pras);
        pj.setCopies(copias);        
        if (okay) {
            lblMensajes.setText("Imprimiendo...");
            PrintService service = pj.getPrintService();
            DocPrintJob job = service.createPrintJob();
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(bais, flavor, das);
            job.print(doc, pras);
            lblMensajes.setText("Completado: " + service.getName());         
        }
    }catch (HeadlessException | IOException | PrintException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al imprimir el código de barras", "Error Imprimir");
        }       
    }
    
    private void guardarCodigo(){
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Código Barras");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files (*.png)", "*.png","*.jpg","*.gif"));
        fileChooser.setInitialFileName(codigoBarras.concat(".png"));
        File file = fileChooser.showSaveDialog(null);
        
        if (file != null) {
            String name = file.getName();
            if (name.indexOf(".") == -1) {
                name += ".png";
                file = new File(file.getParentFile(), name);
            }
            try {
                ImageIO.write(symbol, "png", file);
            } catch (IOException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar el código de barras", "Error Código Barras");
            }
        }
    }
    
    public void setTitulo(String titulo){
         lblTitulo.setText(titulo);
    }
     
    public void setMensaje(String mensaje){
        lblMensajes.setText(mensaje);
    }
    
    
    @FXML
    public void cerrar(ActionEvent evento){
        dialogStage.close();
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
       
    }    
    
}
