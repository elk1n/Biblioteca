
package sabga.controlador;

import sabga.modelo.RegistroAdministrador;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;

/**
 *
 * @author Nanny
 */
public class RegistrarAdministradorController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
   @FXML private GridPane camposG;
    
    // Declaramos los botones
    @FXML private Button registrarBT;
    @FXML private Button aceptarBT;
    @FXML private Button modificarBT;
       
    // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField apellidoTF; 
    @FXML public TextField mailTF;
    @FXML public TextField documentoTF;    
    @FXML public TextField telefonoTF;
    @FXML public TextField direccionTF;
    @FXML public TextField usuarioTF;
    @FXML public TextField contraseniaTF;
       
    //Declaramos los combobox
    @FXML public ComboBox jornadaCB;
    @FXML public ComboBox tipoCB;
    @FXML public ComboBox cursoCB;
    @FXML public ComboBox grupoCB;
    @FXML public ComboBox estadoCB;
   
    
       // Declaramos la tabla y las columnas
    @FXML private TableView<RegistroAdministrador> tablaAdmin;
    
        @FXML private TableColumn tipoCL;
        @FXML private TableColumn nombreCL;
        @FXML private TableColumn apellidoCL;
        @FXML private TableColumn usuarioCL;
        @FXML private TableColumn contraseniaCL;
        @FXML private TableColumn cursoCL;
        @FXML private TableColumn grupoCL;
        @FXML private TableColumn correoCL;
        @FXML private TableColumn documentoCL;
        @FXML private TableColumn jornadaCL;
        @FXML private TableColumn telefonoCL;
        @FXML private TableColumn direccionCL;
        @FXML private TableColumn estadoCL;  
    
    ObservableList<RegistroAdministrador> usu;
    public int posicionAdminEnTabla;
    
    //Declaramos los label de las validaciones
    @FXML private Label vtip;
    @FXML private Label vnom;
    @FXML private Label vap;
    @FXML private Label vmail;
    @FXML private Label vcu;
    @FXML private Label vgrup;
    @FXML private Label vdoc;
    @FXML private Label vjor;
    @FXML private Label vtel;
    @FXML private Label vdir;
    
    
     @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
     
    /**
     * Método que realiza las acciones tras pulsar el boton "Registrar"
     *
     * @param event
     */
     
       @FXML private void aceptar(ActionEvent event) {
            String valorTipo =tipoCB.getSelectionModel().getSelectedItem().toString();
            
            if(valorTipo.equals("Administrador"))
        {
        camposG.setVisible(true);
        cursoCB.setDisable(true);
        grupoCB.setDisable(true);
        jornadaCB.setDisable(true);
        direccionTF.setDisable(true);
        telefonoTF.setDisable(true);
        registrarBT.setVisible(true);
        modificarBT.setVisible(true);
        
        nombreTF.setText("");
        apellidoTF.setText("");
        usuarioTF.setText("");
        contraseniaTF.setText("");
        cursoCB.setValue("");
        grupoCB.setValue("");
        mailTF.setText("");
        documentoTF.setText("");
        jornadaCB.setValue("");
        telefonoTF.setText("");
        direccionTF.setText("");
        estadoCB.setValue("");
        }
       else{
        camposG.setVisible(true);    
        cursoCB.setDisable(false);
        grupoCB.setDisable(false);
        jornadaCB.setDisable(false);
        direccionTF.setDisable(false);
        telefonoTF.setDisable(false); 
        registrarBT.setVisible(true);
        modificarBT.setVisible(true);
        
        nombreTF.setText("");
        apellidoTF.setText("");
        usuarioTF.setText("");
        contraseniaTF.setText("");
        cursoCB.setValue("");
        grupoCB.setValue("");
        mailTF.setText("");
        documentoTF.setText("");
        jornadaCB.setValue("");
        telefonoTF.setText("");
        direccionTF.setText("");
        estadoCB.setValue("");
            }
       }
       
    
     @FXML private void registrar(ActionEvent event) {
          RegistroAdministrador persona = new RegistroAdministrador();
        persona.tipo.set((String) tipoCB.getValue());  
        persona.nombre.set(nombreTF.getText());
        persona.apellido.set(apellidoTF.getText());
        persona.usuario.set(usuarioTF.getText());
        persona.contrasenia.set(contraseniaTF.getText());
        persona.grado.set((String) cursoCB.getValue());
        persona.grupo.set((String) grupoCB.getValue());
        persona.mail.set(mailTF.getText());
        persona.documento.set(documentoTF.getText());
        persona.jornada.set((String) jornadaCB.getValue());
        persona.telefono.set(telefonoTF.getText());
        persona.direccion.set(direccionTF.getText());
        persona.estado.set((String) estadoCB.getValue());
         usu.add(persona);
         
        
     }
     
     /**
     * Método que realiza las acciones tras pulsar el boton "Modificar"
     *
     * @param event
     */
    @FXML private void modificar(ActionEvent event) {
        
        RegistroAdministrador persona = new RegistroAdministrador();
               String valorTipo =tipoCB.getSelectionModel().getSelectedItem().toString();
            
            if(valorTipo.equals("Administrador"))
        {
        camposG.setVisible(true);    
        cursoCB.setDisable(false);
        grupoCB.setDisable(false);
        jornadaCB.setDisable(false);
        direccionTF.setDisable(false);
        telefonoTF.setDisable(false);        
        registrarBT.setVisible(true);
        modificarBT.setVisible(true);
        
        persona.tipo.set((String) tipoCB.getValue());
        persona.nombre.set(nombreTF.getText());
        persona.apellido.set(apellidoTF.getText());     
        persona.usuario.set(usuarioTF.getText());
        persona.contrasenia.set(contraseniaTF.getText());
        persona.mail.set(mailTF.getText());
        persona.documento.set(documentoTF.getText());
        persona.estado.set((String) estadoCB.getValue());
        }
       else{
        camposG.setVisible(true);
        cursoCB.setDisable(true);
        grupoCB.setDisable(true);
        jornadaCB.setDisable(true);
        direccionTF.setDisable(true);
        telefonoTF.setDisable(true);
        registrarBT.setVisible(true);
        modificarBT.setVisible(true);
        
        persona.tipo.set((String) tipoCB.getValue());
        persona.nombre.set(nombreTF.getText());
        persona.apellido.set(apellidoTF.getText());
        persona.usuario.set(usuarioTF.getText());
        persona.contrasenia.set(contraseniaTF.getText());
        persona.grado.set((String) cursoCB.getValue());
        persona.grupo.set((String) grupoCB.getValue());
        persona.mail.set(mailTF.getText());
        persona.documento.set(documentoTF.getText());
        persona.jornada.set((String) jornadaCB.getValue());
        persona.telefono.set(telefonoTF.getText()); 
        persona.direccion.set(direccionTF.getText());
        persona.estado.set((String) estadoCB.getValue());
            }
            usu.set(posicionAdminEnTabla, persona);
    }
      
    
     
    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<RegistroAdministrador> selectorTablaAdmin =
            new ListChangeListener<RegistroAdministrador>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends RegistroAdministrador> c) {
                    ponerUsuarioSeleccionado();
                }
            };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public RegistroAdministrador getTablaAdminSeleccionada() {
        if (tablaAdmin != null) {
            List<RegistroAdministrador> tabla = tablaAdmin.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final RegistroAdministrador competicionSeleccionada = tabla.get(0);
                String valorTipo =tipoCB.getSelectionModel().getSelectedItem().toString();
                if(valorTipo.equals("Administrador"))
        {
          cursoCB.setDisable(true);
        grupoCB.setDisable(true);
        jornadaCB.setDisable(true);
        direccionTF.setDisable(true);
        telefonoTF.setDisable(true);
        registrarBT.setVisible(true);  
       
        }
 else{
        cursoCB.setDisable(false);
        grupoCB.setDisable(false);
        jornadaCB.setDisable(false);
        direccionTF.setDisable(false);
        telefonoTF.setDisable(false);
        registrarBT.setVisible(true);         
        
        
            }
            
                return competicionSeleccionada;
            }
           
        }
        return null;
    }
        
    /**
     * Método para poner en los textFields la tupla que selccionemos
     */
    private void ponerUsuarioSeleccionado() {
        final RegistroAdministrador persona = getTablaAdminSeleccionada();
        posicionAdminEnTabla = usu.indexOf(persona);

         
        if (persona != null) {
           
            // Pongo los textFields con los datos correspondientes
            tipoCB.setValue(persona.getTipo());
            nombreTF.setText(persona.getNombre());
            apellidoTF.setText(persona.getApellido());
            usuarioTF.setText(persona.getUsuario());
            contraseniaTF.setText(persona.getContrasenia());
            cursoCB.setValue(persona.getGrado());            
            grupoCB.setValue(persona.getGrupo());
            mailTF.setText(persona.getMail());
            documentoTF.setText(persona.getDocumento().toString());
            jornadaCB.setValue(persona.getJornada());
            telefonoTF.setText(persona.getTelefono().toString());
            direccionTF.setText(persona.getDireccion());
            estadoCB.setValue(persona.getEstado());
            
            // Pongo los botones en su estado correspondiente
            modificarBT.setDisable(false);
            //eliminarBT.setDisable(false);
            registrarBT.setDisable(false);

        }
    }

    /**
     * MÃ©todo para inicializar la tabla
     */
    private void inicializarTablaAdmin() {
        tipoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("tipo"));
        nombreCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("nombre"));
        apellidoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("apellido"));
        usuarioCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("usuario"));
        contraseniaCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("contrasenia"));
        cursoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("grado"));
        grupoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("grupo"));
        correoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("mail"));
        documentoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("documento"));
        jornadaCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("jornada"));
        telefonoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("telefono"));
        direccionCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("direccion"));
        estadoCL.setCellValueFactory(new PropertyValueFactory<RegistroAdministrador, String>("estado"));

        usu = FXCollections.observableArrayList();
        tablaAdmin.setItems(usu);
    }   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // Inicializamos la tabla
        this.inicializarTablaAdmin();
        
        // Ponemos estos dos botones para que no se puedan seleccionar
    
        modificarBT.setDisable(true);
       // eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<RegistroAdministrador> tablaPersonaSel = tablaAdmin.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaAdmin);
        
        // Inicializamos la tabla con algunos datos aleatorios
        for (int i = 0; i < 1; i++) {
            RegistroAdministrador a = new RegistroAdministrador();
             a.tipo.set("Estudiante");
            a.nombre.set("Nombre " );
            a.apellido.set("Apellido ");
            a.usuario.set("nany ");
            a.contrasenia.set("123"); 
            a.grado.set("8");
            a.grupo.set("A");
            a.mail.set("nana@gmail.com");
            a.documento.set("123456");
            a.jornada.set("Mañana");
            a.telefono.set("2223445");
            a.direccion.set("cr.32a");
            a.estado.set("Habilitado");
            usu.add(a);
        }
      
    }
    } 