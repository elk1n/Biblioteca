
package sabga.controlador;
import sabga.modelo.pagina;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;

/**
 *
 * @author Nanny
 * 
 */
public class PaginaInicio1Controller implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    private ScreensController controlador;
   

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;

     }
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;

    } 
    
    
    @FXML private TableView<pagina> tabla1;
     @FXML private TableView<pagina> tabla2;
    
        @FXML private TableColumn documentoCL;
        @FXML private TableColumn tipoCL;
        @FXML private TableColumn tituloCL;
        @FXML private TableColumn autorCL;
        @FXML private TableColumn fechaDevCL;
        @FXML private TableColumn estadoPrestamoCL;
        
        @FXML private TableColumn documento2CL;
        @FXML private TableColumn tipo2CL;
        @FXML private TableColumn titulo2CL;
        @FXML private TableColumn autor2CL;
        @FXML private TableColumn fechaDev2CL;
        @FXML private TableColumn estadoPrestamo2CL;
                      
    ObservableList<pagina> usu;
    
     //Listener de la tabla personas
      private final ListChangeListener<pagina> selectorTablaReportes =
            new ListChangeListener<pagina>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends pagina> c) {
                    
                }
            };
      
      private final ListChangeListener<pagina> selectorTablaReportes2 =
            new ListChangeListener<pagina>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends pagina> c) {
                    
                }
            };
    @FXML
     private void inicializarTablaRegistro() {
        documentoCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("documento"));
        tipoCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("tipo"));
        tituloCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("titulo"));
        autorCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("autor"));
        fechaDevCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("fechaDev"));
        estadoPrestamoCL.setCellValueFactory(new PropertyValueFactory<pagina, String>("estadoPrestamo"));

        usu = FXCollections.observableArrayList();
        tabla1.setItems(usu);
    }    
    
    private void inicializarTablaRegistro2() {
        documento2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("documento"));
        tipo2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("tipo"));
        titulo2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("titulo"));
        autor2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("autor"));
        fechaDev2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("fechaDev"));
        estadoPrestamo2CL.setCellValueFactory(new PropertyValueFactory<pagina, String>("estadoPrestamo"));

        usu = FXCollections.observableArrayList();
        tabla2.setItems(usu);
    }    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.inicializarTablaRegistro();
        
        final ObservableList<pagina> tablaPersonaSel = tabla1.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaReportes);
        
       for (int i = 0; i < 10; i++) {
       
            pagina a = new pagina();
            
            a.documento.set("12345");
            a.tipo.set("Libro" );
            a.titulo.set("El amanecer");
            a.autor.set("Camilo Herrera.");
            a.fechaDev.set("18/06/2013");
            a.estadoPrestamo.set("En reserva");
            
            usu.add(a);
       }
       
       this.inicializarTablaRegistro2();
        
        final ObservableList<pagina> tablaPersonaSel2 = tabla2.getSelectionModel().getSelectedItems();
        tablaPersonaSel2.addListener(selectorTablaReportes2);
        
       for (int i = 0; i < 10; i++) {
         pagina b = new pagina();
            
            b.documento.set("54321");
            b.tipo.set("Libro" );
            b.titulo.set("La odisea");
            b.autor.set("No se sabe");
            b.fechaDev.set("30/06/2013");
            b.estadoPrestamo.set("En prestamo");
            
            usu.add(b);
       }
    }    
}
