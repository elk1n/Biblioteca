

package sabga.controlador.dialogos;

import java.awt.image.BufferedImage;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
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
    
    @FXML
    private ImageView imagenCodigoBarras;
    @FXML
    private Label lblMensajes;
    
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    public void setCodigoBarras(String codigo){    
        codigoBarras=codigo;
    }
    
    public void pintarCodigo(){
    
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
             Utilidades.mensajeError(null,e.getMessage(), "Error al crear el código de barras", "Error Código Barras");
        }
    }
    
    public void imprimirCodigo(BufferedImage image) throws PrinterException, PrintException, IOException {

        lblMensajes.setText("Imprimiendo...");
        
        DocFlavor docFlavor = DocFlavor.INPUT_STREAM.PNG ;
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();       
        attributes.add(new Copies(1));
        javax.print.PrintService printServices[] = PrintServiceLookup.lookupPrintServices(docFlavor, attributes);
        
        if (printServices.length == 0) {
            lblMensajes.setText("El servicio de impresión para extensión PNG no se esta disponible!");
            throw new RuntimeException("PrintService for PNG not available!");
        }
       // System.out.println("Got PrintService: " + printServices[0].getName());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        // Muestra dialogo de impresión...
        boolean print = PrinterJob.getPrinterJob().printDialog(attributes);

        if (print) {
            DocPrintJob job = printServices[0].createPrintJob();
            Doc doc = new SimpleDoc(in, docFlavor, null);
            job.print(doc, attributes);
            in.close();
        }
        lblMensajes.setText("Completado: " +printServices[0].getName());
        //System.out.println("Done PrintService: " + print);
    }
    
    @FXML
    public void imprimir( ActionEvent evento){
        try {
            imprimirCodigo(symbol);            
        } catch (PrinterException | PrintException | IOException ex) {
            Logger.getLogger(CodigoBarrasController.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    @FXML
    public void cerrar(ActionEvent evento){
        dialogStage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
       
    }    
    
}
