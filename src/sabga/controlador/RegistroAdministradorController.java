
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
import javafx.scene.control.PasswordField;
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
 */
public class RegistroAdministradorController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private TextField campoNombre, campoApellidos, campoUsuario;
    
    @FXML private PasswordField campoContrasenia, campoContrasenia2;
    
    @FXML public ComboBox jornadaCB;
  
 
    
    ObservableList<RegistroAdministrador> usu;
    public int posicionAdminEnTabla;
    
    //Declaramos los label de las validaciones
    @FXML private Label validarNombre;
    @FXML private Label validarApellidos;
    @FXML private Label validarUsuario;
    @FXML private Label validarContrasenia;
    @FXML private Label validarCurso;
    @FXML private Label validarGrupo;
    @FXML private Label validarCorreo;
    @FXML private Label validarDocumento;
    @FXML private Label validarJornada;
    @FXML private Label validarTelefono;
    @FXML private Label validarDireccion;
    @FXML private Label validarEstado;
    
    
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
        camposG2.setVisible(true);
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
        camposG2.setVisible(true);
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
         
        if (tipoCB.getSelectionModel().getSelectedIndex() == 0){
            
            ValidarUsuario validarAuxiliar = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), usuarioTF.getText(), contraseniaTF.getText(),
                                                                cursoCB.getSelectionModel().getSelectedItem(),grupoCB.getSelectionModel().getSelectedItem(),
                                                                mailTF.getText(), documentoTF.getText(), jornadaCB.getSelectionModel().getSelectedItem(),
                                                                telefonoTF.getText(),direccionTF.getText(),estadoCB.getSelectionModel().getSelectedItem());
            
            validarAuxiliar.validarAdminAxiliar();
            validarNombre.setText(validarAuxiliar.getErrorNombreUsuario());
            validarApellidos.setText(validarAuxiliar.getErrorApellidosUsuario());
            validarUsuario.setText(validarAuxiliar.getErrorUsuario());
            validarContrasenia.setText(validarAuxiliar.getErrorContrasenia());
            validarCurso.setText(validarAuxiliar.getErrorCursoUsuario());
            validarGrupo.setText(validarAuxiliar.getErrorGrupoUsuario());
            validarCorreo.setText(validarAuxiliar.getErrorCorreoUsuario());
            validarDocumento.setText(validarAuxiliar.getErrorDocumentoUsuario());
            validarJornada.setText(validarAuxiliar.getErrorJornadaUsuario());
            validarTelefono.setText(validarAuxiliar.getErrorTelefonoUsuario());
            validarDireccion.setText(validarAuxiliar.getErrorDireccionUsuario());
            validarEstado.setText(validarAuxiliar.getErrorEstadoUsuario());
            
            if (validarAuxiliar.validarAdminAxiliar()==true){
                
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
        
        }
        else if (tipoCB.getSelectionModel().getSelectedIndex()==1){
            
                ValidarUsuario validarAuxiliar = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), usuarioTF.getText(), contraseniaTF.getText(), 
                                                                    mailTF.getText(), documentoTF.getText(), estadoCB.getSelectionModel().getSelectedItem());

                validarAuxiliar.validarAdminAxiliar();
                validarNombre.setText(validarAuxiliar.getErrorNombreUsuario());
                validarApellidos.setText(validarAuxiliar.getErrorApellidosUsuario());
                validarUsuario.setText(validarAuxiliar.getErrorUsuario());
                validarContrasenia.setText(validarAuxiliar.getErrorContrasenia());      
                validarCorreo.setText(validarAuxiliar.getErrorCorreoUsuario());
                validarDocumento.setText(validarAuxiliar.getErrorDocumentoUsuario());
                validarEstado.setText(validarAuxiliar.getErrorEstadoUsuario());

             if (validarAuxiliar.validarAdminAxiliar()==true){
                
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
        
        
        }
        
         
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
        camposG2.setVisible(true);
        cursoCB.setDisable(false);
        grupoCB.setDisable(false);
        jornadaCB.setDisable(false);
        direccionTF.setDisable(false);
        telefonoTF.setDisable(false);        
        registrarBT.setVisible(true);
        modificarBT.setVisible(true);
        
         ValidarUsuario validarAuxiliar = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), usuarioTF.getText(), contraseniaTF.getText(), 
                                                                    mailTF.getText(), documentoTF.getText(), estadoCB.getSelectionModel().getSelectedItem());

                validarAuxiliar.validarAdminAxiliar();
                validarNombre.setText(validarAuxiliar.getErrorNombreUsuario());
                validarApellidos.setText(validarAuxiliar.getErrorApellidosUsuario());
                validarUsuario.setText(validarAuxiliar.getErrorUsuario());
                validarContrasenia.setText(validarAuxiliar.getErrorContrasenia());      
                validarCorreo.setText(validarAuxiliar.getErrorCorreoUsuario());
                validarDocumento.setText(validarAuxiliar.getErrorDocumentoUsuario());
                validarEstado.setText(validarAuxiliar.getErrorEstadoUsuario());
                
                  if (validarAuxiliar.validarAdminAxiliar()==true){
              
                        persona.tipo.set((String) tipoCB.getValue());
                        persona.nombre.set(nombreTF.getText());
                        persona.apellido.set(apellidoTF.getText());     
                        persona.usuario.set(usuarioTF.getText());
                        persona.contrasenia.set(contraseniaTF.getText());
                        persona.mail.set(mailTF.getText());
                        persona.documento.set(documentoTF.getText());
                        persona.estado.set((String) estadoCB.getValue());
        
                  }
        
            }
            else {
        
                camposG.setVisible(true);
                camposG2.setVisible(true);
                cursoCB.setDisable(true);
                grupoCB.setDisable(true);
                jornadaCB.setDisable(true);
                direccionTF.setDisable(true);
                telefonoTF.setDisable(true);
                registrarBT.setVisible(true);
                modificarBT.setVisible(true);
                
                 ValidarUsuario validarAuxiliar = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), usuarioTF.getText(), contraseniaTF.getText(),
                                                                cursoCB.getSelectionModel().getSelectedItem(),grupoCB.getSelectionModel().getSelectedItem(),
                                                                mailTF.getText(), documentoTF.getText(), jornadaCB.getSelectionModel().getSelectedItem(),
                                                                telefonoTF.getText(),direccionTF.getText(),estadoCB.getSelectionModel().getSelectedItem());
            
            validarAuxiliar.validarAdminAxiliar();
            validarNombre.setText(validarAuxiliar.getErrorNombreUsuario());
            validarApellidos.setText(validarAuxiliar.getErrorApellidosUsuario());
            validarUsuario.setText(validarAuxiliar.getErrorUsuario());
            validarContrasenia.setText(validarAuxiliar.getErrorContrasenia());
            validarCurso.setText(validarAuxiliar.getErrorCursoUsuario());
            validarGrupo.setText(validarAuxiliar.getErrorGrupoUsuario());
            validarCorreo.setText(validarAuxiliar.getErrorCorreoUsuario());
            validarDocumento.setText(validarAuxiliar.getErrorDocumentoUsuario());
            validarJornada.setText(validarAuxiliar.getErrorJornadaUsuario());
            validarTelefono.setText(validarAuxiliar.getErrorTelefonoUsuario());
            validarDireccion.setText(validarAuxiliar.getErrorDireccionUsuario());
            validarEstado.setText(validarAuxiliar.getErrorEstadoUsuario());
                
            
             if (validarAuxiliar.validarAdminAxiliar()==true){
                
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
