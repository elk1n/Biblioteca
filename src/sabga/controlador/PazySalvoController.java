
package sabga.controlador;

import sabga.atributos.Usuario;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import javafx.scene.control.Dialogs;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
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
import sabga.atributos.Atributos;
import sabga.configuracion.Dialogo;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;

/**
 * FXML Controller class
 * @author Nanny
 */

public class PazySalvoController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private Pane panelBuscar, panelRecibo, recibo;
    @FXML
    private ComboBox comboListar, comboJornada, comboGrado, comboCurso;
    @FXML
    private TextField txtfBuscar, txtfNombre, txtfApellido;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableView tablaUsuarios;
    @FXML
    private TableColumn clmnTipo, clmnDocumento, clmnNombre, clmnApellido, clmnCorreo, clmnTelefono, clmnGrado, clmnCurso, clmnJornada,
                        clmnEstado;
    @FXML
    private Label lblBuscarUsuario, lblFecha, lblBibliotecario, lblCoordinacion;
    private final ObservableList<Usuario> listaUsuarios;
    private final Consultas consulta;
    private final Seleccion select;
    private final Atributos atributo;
    private final Dialogo dialogo;
    private Calendar calendario;
    private final SimpleDateFormat formato;
    private BufferedImage imagen;
   
    public PazySalvoController() {
        
        consulta = new Consultas();
        select = new Seleccion();
        atributo = new Atributos();
        dialogo = new Dialogo();
        listaUsuarios = FXCollections.observableArrayList();
        calendario = Calendar.getInstance();
        calendario = new GregorianCalendar();
        formato = new SimpleDateFormat("EEEE dd 'de' MMMM 'de' YYYY hh:mm aa");
    }

    @FXML
    public void vistaPazySalvo(ActionEvent evento){
        vistaPazySalvo();
    }
    
    @FXML
    public void vistaBusqueda(ActionEvent evento){
        vistaBusqueda();
    }
       
    @FXML
    public void listarUsuarios(ActionEvent evento){
        listarUsuarios();
    }
    
    @FXML
    public void buscarUsuario(ActionEvent evento){    
        buscarUsuario();
    }
    
    @FXML
    public void detalleMultas(ActionEvent evento){
        multas();
    }
        
    @FXML
    public void detalleUsuario(ActionEvent evento){
        dialogoDetalleUsuario();
    }
    
    @FXML
    public void cargarDatosUsuario(ActionEvent evento){
        setDatosUsuario();
    } 
    
    public void seleccionUsuario(){
        seleccionarUsuario();
    }
    
    @FXML
    public void guardarCaptura(ActionEvent evento) {
        
        if(!txtfNombre.getText().isEmpty() && !txtfApellido.getText().isEmpty()){
            crearCaptura();
            guardarCaptura();
        }else{
            Utilidades.mensajeAdvertencia(null, "Debe ingresar un nombre y un apellido", "", "Debe Ingresar Datos");
        }         
    }
    
    @FXML
    public void imprimirCaptura(ActionEvent evento) {
        
        if(!txtfNombre.getText().isEmpty() && !txtfApellido.getText().isEmpty()){
            crearCaptura();
            imprimirCaptura();
        }else{
            Utilidades.mensajeAdvertencia(null, "Debe ingresar un nombre y un apellido", "", "Debe Ingresar Datos");
        }        
    }
    
    private void setDatosUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            if(consulta.getValorMulta(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento())>0 ||
               consulta.getEstadoPrestamos(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento())>0){
                
                Utilidades.mensajeOpcion(null, "Desea expedir el Paz y Salvo?",
                                        "El usuario se encuentra multado o tiene ejemplares por devolver. Por favor verifique las Multas "
                                        + "o el Detalle de Usuario.",
                                        "Seleccionar Usuario");
                    if (Utilidades.getMensajeOpcion() == Dialogs.DialogResponse.YES) {
                            cargarDatosUsuario();
                    }                
            }else{
                cargarDatosUsuario();
            }  
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }            
    }
    
    private void cargarDatosUsuario(){
        
        txtfNombre.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre());
        txtfApellido.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido());
        comboCurso.getSelectionModel().select(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCurso());
        comboGrado.getSelectionModel().select(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getGrado());
        comboJornada.getSelectionModel().select(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getJornada());
        lblBibliotecario.setText(consulta.getNombreBibliotecario(atributo.getUsuarioAdmin())+lblCoordinacion.getText());
        vistaPazySalvo();
        
    }
    
    private void seleccionarUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            atributo.setDocumentoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            atributo.setNombreUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre());
            atributo.setApellidoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido());
            atributo.setCorreoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCorreo());   
        }
    }
    
    private void dialogoDetalleUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            panelBuscar.setDisable(true);
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            panelBuscar.setDisable(false);     
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void multas(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            panelBuscar.setDisable(true);
            dialogo.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            panelBuscar.setDisable(false);     
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void listarUsuarios(){
        
         if (!comboListar.getSelectionModel().isEmpty()) {            
            prepararTabla();
            if (comboListar.getSelectionModel().getSelectedItem().toString().contains("Todos")) {
                listaUsuarios.addAll(consulta.getListaUsuariosRecibo(4, null));
            } else {
                listaUsuarios.addAll(consulta.getListaUsuariosRecibo(3, comboListar.getSelectionModel().getSelectedItem().toString()));
            }
            tablaUsuarios.setItems(listaUsuarios);
            lblBuscarUsuario.setText(null);
        }
    }
    
    private void buscarUsuario(){
    
         if (!"".equals(txtfBuscar.getText())) {
            prepararTabla();
            listaUsuarios.addAll(consulta.getListaUsuariosRecibo(5, txtfBuscar.getText().trim()));
            tablaUsuarios.setItems(listaUsuarios);
            if(listaUsuarios.isEmpty()){
                comboListar.getSelectionModel().clearSelection();
                lblBuscarUsuario.setText("No se han encontrado resultados.");
            }else{
                lblBuscarUsuario.setText(null);
                comboListar.getSelectionModel().clearSelection();
            }
        }   
    }
     
    private void prepararTabla(){
    
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        clmnTelefono.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefono"));
        clmnGrado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("grado"));
        clmnCurso.setCellValueFactory(new PropertyValueFactory<Usuario, String>("curso"));
        clmnJornada.setCellValueFactory(new PropertyValueFactory<Usuario, String>("jornada"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        listaUsuarios.clear();
    
    }
    
    private void vistaPazySalvo(){
        
        panelRecibo.setDisable(false);
        panelRecibo.setVisible(true);
        panelBuscar.setDisable(true);
        panelBuscar.setVisible(false);
        lblBibliotecario.setText(consulta.getNombreBibliotecario(atributo.getUsuarioAdmin())+lblCoordinacion.getText());
    }
    
    private void vistaBusqueda(){
        
        panelRecibo.setDisable(true);
        panelRecibo.setVisible(false);
        panelBuscar.setDisable(false);
        panelBuscar.setVisible(true);
        txtfNombre.clear();
        txtfApellido.clear();
        lblBibliotecario.setText(lblCoordinacion.getText());
        comboCurso.getSelectionModel().clearSelection();
        comboJornada.getSelectionModel().clearSelection();
        comboGrado.getSelectionModel().clearSelection();
    }
    
    private void crearCaptura(){
        
        WritableImage snapshot = recibo.snapshot(new SnapshotParameters(), null);       
        imagen = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, null);
    }
    
    private void guardarCaptura() {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar Paz y Salvo");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files (*.png)", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String name = file.getName();
            if (name.indexOf(".") == -1) {
                name += ".png";
                file = new File(file.getParentFile(), name);
            }
            try {
                ImageIO.write(imagen, "png", file);
            } catch (IOException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar la imagen", "Error Imagen");
            }
        }
    }
    
    private void imprimirCaptura() {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(imagen, "PNG", baos);
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            PrinterJob pj = PrinterJob.getPrinterJob();
            boolean okay = pj.printDialog(pras);       
            if (okay) {
                PrintService service = pj.getPrintService();
                DocPrintJob job = service.createPrintJob();
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(bais, flavor, das);
                job.print(doc, pras);        
            }
        } catch (HeadlessException | IOException | PrintException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al imprimir el Paz y Salvo", "Error Imprimir");
        }
    }
    
    @FXML
    public void mostrarBoton(KeyEvent evento) {

        if ("".equals(txtfBuscar.getText())){            
            btnBorrar.setVisible(false);      
        }
        else {
           btnBorrar.setVisible(true); 
        }                 
    }
    
    @FXML
    public void borrarCampo(ActionEvent evento) {      
        txtfBuscar.setText("");
        lblBuscarUsuario.setText(null);
        btnBorrar.setVisible(false);
    }
    
    /**
     * Initializes the controller class.
     * @param screenParent
     */
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    private void inicio(){   
        
        vistaBusqueda();
        btnBorrar.setVisible(false);
        comboListar.setItems(consulta.llenarLista(select.getListaUsuarios(), select.getUsuarios()));
        comboListar.getItems().add("Todos");
        comboGrado.setItems(consulta.llenarLista(select.getListaGrado(), select.getGrado()));
        comboCurso.setItems(consulta.llenarLista(select.getListaCurso(), select.getCurso()));
        comboJornada.setItems(consulta.llenarLista(select.getListaJornada(), select.getJornada()));
        lblFecha.setText(formato.format(calendario.getTime()));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }
   
}
