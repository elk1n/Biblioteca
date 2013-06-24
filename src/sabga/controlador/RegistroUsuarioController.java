
package sabga.controlador;

import sabga.modelo.usuarios;
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
import sabga.modelo.ValidarUsuario;

/**
 *
 * @author Nanny
 * 
 */

public class RegistroUsuarioController implements Initializable, ControlledScreen {
    
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
    
    // Declaramos los botones
    @FXML private Button registrarBT;
    @FXML private Button aceptarBT;
    @FXML private Button modificarBT;
       
    // Declaramos los textfileds
    @FXML private TextField nombreTF;
    @FXML private TextField apellidoTF; 
    @FXML private TextField mailTF;
    @FXML public TextField documentoTF;    
    @FXML public TextField telefonoTF;
    @FXML public TextField direccionTF;
       
    //Declaramos los combobox
    @FXML public ComboBox jornadaCB;
    @FXML public ComboBox tipoCB;
    @FXML public ComboBox cursoCB;
    @FXML public ComboBox grupoCB;
    @FXML public ComboBox estadoCB;
   
    
       // Declaramos la tabla y las columnas
    @FXML private TableView<usuarios> tablaUsuarios;
    
        @FXML private TableColumn tipoCL;
        @FXML private TableColumn nombreCL;
        @FXML private TableColumn apellidoCL;
        @FXML private TableColumn cursoCL;
        @FXML private TableColumn grupoCL;
        @FXML private TableColumn correoCL;
        @FXML private TableColumn documentoCL;
        @FXML private TableColumn jornadaCL;
        @FXML private TableColumn telefonoCL;
        @FXML private TableColumn direccionCL;
        @FXML private TableColumn estadoCL;  
    
    ObservableList<usuarios> usu;
    
    public int posicionUsuarioEnTabla;
    
     //Declaramos los label de las validaciones
    @FXML private Label validarNombre;
    @FXML private Label validarApellidos;
    @FXML private Label validarCurso;
    @FXML private Label validarGrupo;
    @FXML private Label validarCorreo;
    @FXML private Label validarDocumento;
    @FXML private Label validarJornada;
    @FXML private Label validarTelefono;
    @FXML private Label validarDireccion;
    @FXML private Label validarEstado;
    
      
    /**
     * Método que realiza las acciones tras pulsar el boton "Registrar"
     *
     * @param event
     */
     
       @FXML private void aceptar(ActionEvent event) {
            String valorTipo =tipoCB.getSelectionModel().getSelectedItem().toString();
            
            if(valorTipo.equals("Empleado"))
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
      
    /*@FXML private void ValidaRegistro(ActionEvent event) {
        usuarios persona = new usuarios();        
                            
        if (tipoCB.getValue().equals("")){
        vtip.setVisible(true);
          }
          else {
        vtip.setVisible(false);
          }
        if (nombreTF.getText().equals("")){
        vnom.setVisible(true);
          }
          else {
        vnom.setVisible(false);
          }
           if (apellidoTF.getText().equals("")){
            vap.setVisible(true);   
           }
           else{
            vap.setVisible(false);  
           }
           if (cursoCB.getValue().equals("")){
              vcu.setVisible(true);    
           }
           else{
           vcu.setVisible(false);
           }
           if (grupoCB.getValue().equals("")){
              vgrup.setVisible(true);    
           }
           else{
          vgrup.setVisible(false);        
           }
           if (mailTF.getText().equals("")){
              vmail.setVisible(true);    
           }
           else{
           vmail.setVisible(false);        
           }
            if (documentoTF.getText().equals("")){
              vdoc.setVisible(true);    
           }
           else{
           vdoc.setVisible(false);        
           }
            if (jornadaCB.getValue().equals("")){
              vjor.setVisible(true);    
           }
           else{
           vjor.setVisible(false);        
           }
           
            if (telefonoTF.getText().equals("")){
              vtel.setVisible(true);    
           }
           else{
                vtel.setVisible(false);        
           }
         if (direccionTF.getText().equals("")){
              vdir.setVisible(true);    
           }
           else{
                vdir.setVisible(false);         
         }
        
        if (!tipoCB.getValue().equals("")&&!nombreTF.getText().equals("")&&!apellidoTF.getText().equals("")&&!cursoCB.getValue().equals("")&&!grupoCB.getValue().equals("")&&!mailTF.getText().equals("")&&!documentoTF.getText().equals("")&&!jornadaCB.getValue().equals("")&&!telefonoTF.getText().equals("")&&!direccionTF.getText().equals("")){
        usu.add(persona);
        
    }
    }*/
    
     @FXML private void registrar(ActionEvent event) {
         
         if (tipoCB.getSelectionModel().getSelectedIndex()==0){
         
                ValidarUsuario validarNuevoUsuario = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), cursoCB.getSelectionModel().getSelectedItem(),
                                                                        grupoCB.getSelectionModel().getSelectedItem(), mailTF.getText(), documentoTF.getText(),
                                                                        jornadaCB.getSelectionModel().getSelectedItem(), telefonoTF.getText(), direccionTF.getText(),
                                                                        estadoCB.getSelectionModel().getSelectedItem());

                validarNuevoUsuario.validarNuevoUsuario();
                validarNombre.setText(validarNuevoUsuario.getErrorNombreUsuario());
                validarApellidos.setText(validarNuevoUsuario.getErrorApellidosUsuario());
                validarCurso.setText(validarNuevoUsuario.getErrorCursoUsuario());
                validarGrupo.setText(validarNuevoUsuario.getErrorGrupoUsuario());
                validarCorreo.setText(validarNuevoUsuario.getErrorCorreoUsuario());
                validarDocumento.setText(validarNuevoUsuario.getErrorDocumentoUsuario());
                validarJornada.setText(validarNuevoUsuario.getErrorJornadaUsuario());
                validarTelefono.setText(validarNuevoUsuario.getErrorTelefonoUsuario());
                validarDireccion.setText(validarNuevoUsuario.getErrorEstadoUsuario());
                validarEstado.setText(validarNuevoUsuario.getErrorEstadoUsuario());

               if (validarNuevoUsuario.validarNuevoUsuario()==true){

                       usuarios persona = new usuarios();
                       persona.tipo.set((String) tipoCB.getValue());  
                       persona.nombre.set(nombreTF.getText());
                       persona.apellido.set(apellidoTF.getText());
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
        
         else if(tipoCB.getSelectionModel().getSelectedIndex()==1){
             
             ValidarUsuario validarEmpleado = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), mailTF.getText(), documentoTF.getText(), 
                                                                 estadoCB.getSelectionModel().getSelectedItem());
             
             validarEmpleado.validarNuevoEmpleado();
             validarNombre.setText(validarEmpleado.getErrorNombreUsuario());
             validarApellidos.setText(validarEmpleado.getErrorApellidosUsuario());
             validarCorreo.setText(validarEmpleado.getErrorCorreoUsuario());
             validarDocumento.setText(validarEmpleado.getErrorDocumentoUsuario());
             validarEstado.setText(validarEmpleado.getErrorEstadoUsuario());
             
             if (validarEmpleado.validarNuevoEmpleado()==true){

                       usuarios persona = new usuarios();
                       persona.tipo.set((String) tipoCB.getValue());  
                       persona.nombre.set(nombreTF.getText());
                       persona.apellido.set(apellidoTF.getText());
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
        
        usuarios persona = new usuarios();
                     
            if(tipoCB.getSelectionModel().getSelectedIndex()==1){
                
                camposG.setVisible(true);    
                cursoCB.setDisable(false);
                grupoCB.setDisable(false);
                jornadaCB.setDisable(false);
                direccionTF.setDisable(false);
                telefonoTF.setDisable(false);        
                registrarBT.setVisible(true);
                modificarBT.setVisible(true);
                
                ValidarUsuario validarEmpleado = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), mailTF.getText(), documentoTF.getText(), 
                                                                 estadoCB.getSelectionModel().getSelectedItem());
                  
                validarEmpleado.validarNuevoEmpleado();
                validarNombre.setText(validarEmpleado.getErrorNombreUsuario());
                validarApellidos.setText(validarEmpleado.getErrorApellidosUsuario());
                validarCorreo.setText(validarEmpleado.getErrorCorreoUsuario());
                validarDocumento.setText(validarEmpleado.getErrorDocumentoUsuario());
                validarEstado.setText(validarEmpleado.getErrorEstadoUsuario());
             
                if (validarEmpleado.validarNuevoEmpleado()==true){

                    persona.tipo.set((String) tipoCB.getValue());
                    persona.nombre.set(nombreTF.getText());
                    persona.apellido.set(apellidoTF.getText());        
                    persona.mail.set(mailTF.getText());
                    persona.documento.set(documentoTF.getText());
                    persona.estado.set((String) estadoCB.getValue());

                }
            }
            
            else if (tipoCB.getSelectionModel().getSelectedIndex()==0){
                
                camposG.setVisible(true);
                cursoCB.setDisable(true);
                grupoCB.setDisable(true);
                jornadaCB.setDisable(true);
                direccionTF.setDisable(true);
                telefonoTF.setDisable(true);
                registrarBT.setVisible(true);
                modificarBT.setVisible(true);
                
                ValidarUsuario validarNuevoUsuario = new ValidarUsuario(nombreTF.getText(), apellidoTF.getText(), cursoCB.getSelectionModel().getSelectedItem(),
                                                                        grupoCB.getSelectionModel().getSelectedItem(), mailTF.getText(), documentoTF.getText(),
                                                                        jornadaCB.getSelectionModel().getSelectedItem(), telefonoTF.getText(), direccionTF.getText(),
                                                                        estadoCB.getSelectionModel().getSelectedItem());

                validarNuevoUsuario.validarNuevoUsuario();
                validarNombre.setText(validarNuevoUsuario.getErrorNombreUsuario());
                validarApellidos.setText(validarNuevoUsuario.getErrorApellidosUsuario());
                validarCurso.setText(validarNuevoUsuario.getErrorCursoUsuario());
                validarGrupo.setText(validarNuevoUsuario.getErrorGrupoUsuario());
                validarCorreo.setText(validarNuevoUsuario.getErrorCorreoUsuario());
                validarDocumento.setText(validarNuevoUsuario.getErrorDocumentoUsuario());
                validarJornada.setText(validarNuevoUsuario.getErrorJornadaUsuario());
                validarTelefono.setText(validarNuevoUsuario.getErrorTelefonoUsuario());
                validarDireccion.setText(validarNuevoUsuario.getErrorEstadoUsuario());
                validarEstado.setText(validarNuevoUsuario.getErrorEstadoUsuario());

               if (validarNuevoUsuario.validarNuevoUsuario()==true){
                    
                    persona.tipo.set((String) tipoCB.getValue());
                    persona.nombre.set(nombreTF.getText());
                    persona.apellido.set(apellidoTF.getText());
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
            usu.set(posicionUsuarioEnTabla, persona);
    }
      
    
     
    /**
     * Listener de la tabla personas
     */
    private final ListChangeListener<usuarios> selectorTablaUsuarios =
            new ListChangeListener<usuarios>() {
                @Override
                public void onChanged(ListChangeListener.Change<? extends usuarios> c) {
                    ponerUsuarioSeleccionado();
                }
            };

    /**
     * PARA SELECCIONAR UNA CELDA DE LA TABLA "tablaPersonas"
     */
    public usuarios getTablaUsuarioSeleccionada() {
        if (tablaUsuarios != null) {
            List<usuarios> tabla = tablaUsuarios.getSelectionModel().getSelectedItems();
            if (tabla.size() == 1) {
                final usuarios competicionSeleccionada = tabla.get(0);
                String valorTipo =tipoCB.getSelectionModel().getSelectedItem().toString();
                if(valorTipo.equals("Empleado"))
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
        final usuarios persona = getTablaUsuarioSeleccionada();
        posicionUsuarioEnTabla = usu.indexOf(persona);

         
        if (persona != null) {
           
            // Pongo los textFields con los datos correspondientes
             tipoCB.setValue(persona.getTipo());
            nombreTF.setText(persona.getNombre());
            apellidoTF.setText(persona.getApellido());
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
    private void inicializarTablaUsuarios() {
        tipoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("tipo"));
        nombreCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("nombre"));
        apellidoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("apellido"));
        cursoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("grado"));
        grupoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("grupo"));
        correoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("mail"));
        documentoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("documento"));
        jornadaCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("jornada"));
        telefonoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("telefono"));
        direccionCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("direccion"));
        estadoCL.setCellValueFactory(new PropertyValueFactory<usuarios, String>("estado"));

        usu = FXCollections.observableArrayList();
        tablaUsuarios.setItems(usu);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Inicializamos la tabla
        this.inicializarTablaUsuarios();
        
        // Ponemos estos dos botones para que no se puedan seleccionar
    
        modificarBT.setDisable(true);
       // eliminarBT.setDisable(true);

        // Seleccionar las tuplas de la tabla de las personas
        final ObservableList<usuarios> tablaPersonaSel = tablaUsuarios.getSelectionModel().getSelectedItems();
        tablaPersonaSel.addListener(selectorTablaUsuarios);
        
        // Inicializamos la tabla con algunos datos aleatorios
        for (int i = 0; i < 1; i++) {
            usuarios a = new usuarios();
             a.tipo.set("Estudiante");
            a.nombre.set("Nombre " );
            a.apellido.set("Apellido ");
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

