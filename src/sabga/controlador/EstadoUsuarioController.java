
package sabga.controlador;

import sabga.modelo.EstadoUsuarios;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarUsuario;

/**
 *
 * @author Nanny
 * 
 */

public class EstadoUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    private ScreensController controlador;
   

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;

     }
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;

    } 
    
     @FXML private GridPane camposG;
     @FXML private GridPane camposG2;
    
    // Declaramos los botones
    @FXML private Button pagarBT;
    @FXML private Button aceptarBT;
   // @FXML private Button modificarBT;
       
    // Declaramos los textfileds
    @FXML public TextField documentoTF1;
    @FXML private TextField nombreTF;    
    @FXML public TextField documentoTF;     
    @FXML public TextField correoTF;
    @FXML public TextField jornadaTF;
    
    @FXML public TextField cambioMulta;
    @FXML public TextField nomTF;
    @FXML public TextField tipTF;
    @FXML public TextField tiTF;
    @FXML public TextField maTF;
    @FXML public TextField auTF;
    @FXML public TextField feTF;
    @FXML public TextField esTF;
   
     @FXML private TableView<EstadoUsuarios> tablaRegistro;
    
        @FXML private TableColumn nombreAdminCL;
        @FXML private TableColumn tipoCL;
        @FXML private TableColumn tituloCL;
        @FXML private TableColumn materiaCL;
        @FXML private TableColumn autorCL;
        @FXML private TableColumn fechaDevCL;
        @FXML private TableColumn estadoPrestamoCL;
        @FXML private TableColumn multaCL;
            
        @FXML private Label validacion;
        @FXML private Label validarMulta;
        
                
    ObservableList<EstadoUsuarios> usu;
      
    public int posicionReporteEnTabla;    
     
   public String documento,nombre2="Alis Rios",documento2="12345",correo2="nanana@gmail.com",
            jornada2="Mañana";
   
    public String nombre3="Andrés Rincon",documento3="54321",correo3="andy@gmail.com",
            jornada3="Tarde";
    
    
           @FXML private void aceptar(ActionEvent event) {
           getDocumento();
           
           camposG.setDisable(true); 
         
           nombreTF.setText("");
           documentoTF.setText("");
           correoTF.setText("");
           jornadaTF.setText(""); 
           
       
         if(documento.equals("54321"))
        {
           tablaRegistro.setDisable(false); 
           camposG.setDisable(true); 
            validacion.setVisible(false);
            nombreTF.setText(nombre3);
            documentoTF.setText(documento3);
            correoTF.setText(correo3);
            jornadaTF.setText(jornada3);            
       }  
        
       /*
         else if(documento.equals("12345"))
        {
            tablaRegistro.setDisable(false); 
            camposG.setDisable(true); 
            validacion.setVisible(false);
            nombreTF.setText(nombre2);
            documentoTF.setText(documento2);
            correoTF.setText(correo2);
            jornadaTF.setText(jornada2);            
       }
       
      */
        else 
        {
            validacion.setText("El documento '" + documento +"'" + "no se pudo encontrar" +"");
            validacion.setVisible(true);
             tablaRegistro.setDisable(true);
        }
       }
       
       public String getDocumento()
    {
        documento=documentoTF1.getText();
        return documento;
    }
       
     @FXML private void pagar(ActionEvent event) { 
         
         EstadoUsuarios persona = new EstadoUsuarios();
              
            if(documento.equals("54321")){
          
                camposG2.setDisable(false);    
                cambioMulta.setDisable(true);  
                pagarBT.setDisable(true);
                
                ValidarUsuario validarValorMulta  = new ValidarUsuario();
 
                if (validarValorMulta.validarNumero(cambioMulta.getText(), 12)==true){
                    validarMulta.setText("");
                    persona.admin.set(nomTF.getText());
                    persona.tipo.set(tipTF.getText());
                    persona.titulo.set(tiTF.getText());
                    persona.materia.set(maTF.getText());
                    persona.autor.set(auTF.getText());
                    persona.fechaDev.set(feTF.getText());
                    persona.estadoPrestamo.set(esTF.getText());
                    persona.multa.set(cambioMulta.getText());

                    usu.set(posicionReporteEnTabla, persona);  
                }
                
                else{
                    validarMulta.setText("Debe ingresar un número");
                }
            }
     }
     //Listener de la tabla personas
      private final ListChangeListener<EstadoUsuarios> selectorTablaReportes =
            new ListChangeListener<EstadoUsuarios>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends EstadoUsuarios> c) {
                    ponerReporteSeleccionado();
                }
            };
       //PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
        public EstadoUsuarios getTablaReporteSeleccionada() {
        if (tablaRegistro != null) {
            List<EstadoUsuarios> tabla2 = tablaRegistro.getSelectionModel().getSelectedItems();
            if (tabla2.size() == 1) {
                final EstadoUsuarios competicionSeleccionada = tabla2.get(0);
                 
        camposG2.setDisable(false);
        nomTF.setDisable(true);
        tipTF.setDisable(true);
        tiTF.setDisable(true);
        maTF.setDisable(true);
        auTF.setDisable(true);
        feTF.setDisable(true);
        esTF.setDisable(true);
        cambioMulta.setDisable(false);        
        pagarBT.setVisible(true);  
       
        return competicionSeleccionada;
       }           
        }return null;
}
     //Método para poner en los textFields la tupla que selccionemos 
     private void ponerReporteSeleccionado() {
        final EstadoUsuarios personas = getTablaReporteSeleccionada();
        posicionReporteEnTabla = usu.indexOf(personas);

         
        if (personas != null) {
           
            // Pongo los textFields con los datos correspondientes
            
            nomTF.setText(personas.getAdmin());
            tipTF.setText(personas.getTipo());
            tiTF.setText(personas.getTitulo());
            maTF.setText(personas.getMateria());
            auTF.setText(personas.getAutor());
            feTF.setText(personas.getFechaDev());
            esTF.setText(personas.getEstadoPrestamo());
            cambioMulta.setText(personas.getMulta());
            
            // Pongo los botones en su estado correspondiente
           pagarBT.setDisable(false); 
        }
    }   
    
     private void inicializarTablaRegistro() {
        nombreAdminCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("admin"));
        tipoCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("tipo"));
        tituloCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("titulo"));
        materiaCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("materia"));
        autorCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("autor"));
        fechaDevCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("fechaDev"));
        estadoPrestamoCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("estadoPrestamo"));
        multaCL.setCellValueFactory(new PropertyValueFactory<EstadoUsuarios, String>("multa"));

        usu = FXCollections.observableArrayList();
        tablaRegistro.setItems(usu);
    }    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.inicializarTablaRegistro();
        
        final ObservableList<EstadoUsuarios> tablaPersonaSel = tablaRegistro.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaReportes);
        
        getDocumento();
        // Inicializamos la tabla con algunos datos aleatorios
       //if(documento1.equals("12345")){
            EstadoUsuarios a = new EstadoUsuarios();
            tablaRegistro.setDisable(true);
            a.admin.set("Guillermo");
            a.tipo.set("Libro" );
            a.titulo.set("Nose");
            a.materia.set("Sociales");
            a.autor.set("Juan Manuel G.");
            a.fechaDev.set("18/06/2013");
            a.estadoPrestamo.set("En prestamo");
            a.multa.set("100");
            
            usu.add(a);
       /* }
       else if(documento1.equals("54321")){
            EstadoUsuarios b = new EstadoUsuarios();
            
            tablaRegistro.setDisable(true);
            b.admin.set("Naty");
            b.tipo.set("Libro" );
            b.titulo.set("Las matematicas de mundo");
            b.materia.set("Matematicas");
            b.autor.set("Gabriel Perez");
            b.fechaDev.set("20/06/2013");
            b.estadoPrestamo.set("En prestamo");
            b.multa.set("0");
            
            usu.add(b);
        }
      else{
           tablaRegistro.setDisable(false);
      }
    }   */ 
    }}
    
