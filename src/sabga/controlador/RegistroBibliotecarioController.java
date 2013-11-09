
package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.table.DefaultTableModel;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;

    
    
/**
 *
 * @author 
 * 
 */
public class RegistroBibliotecarioController implements Initializable, ControlledScreen {
     private Stage dialogStage;
     private String mensaje;
     Conexion con = new Conexion();
     private ConfirmarUsuario nuevoAuxiliar;
   
   DefaultTableModel modelo;
   // Usuario us=new Usuario();
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    
    @FXML public TextField campoNombre, campoApellidos, campoUsuario, campoCorreo, campoDocumento, campoTelefono;
    
    @FXML private Button aniadir;
            
    @FXML public PasswordField campoContrasenia, campoContrasenia2;
    
    @FXML public ComboBox comboTipoAdmin;

    @FXML private Label validarNombre, validarApellidos, validarUsuario, validarContrasenia, validarConfirmacion, validarCorreo, validarDocumento,
                        validarTelefono, validarTipoAdmin;

     @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
     
    @FXML
     public void validarNuevoAuxiliar(ActionEvent evento) throws SQLException {
   //procesarAuxiliar(); 
         Validacion(); 
        con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarBibliotecario(?,?,?,?,?,?,?,?) }");

            con.getProcedimiento().setString("documento", campoDocumento.getText().trim());
            con.getProcedimiento().setString("usuario", campoUsuario.getText().trim());            
            con.getProcedimiento().setString("contrasenia", Utilidades.encriptar(campoContrasenia.getText().trim()));
            con.getProcedimiento().setString("tipoAdministrador", comboTipoAdmin.getValue().toString().trim());
            con.getProcedimiento().setString("nombre", campoNombre.getText().trim());
            con.getProcedimiento().setString("apellidos", campoApellidos.getText().trim());
            con.getProcedimiento().setString("correo", campoCorreo.getText().trim());
            con.getProcedimiento().setString("telefono", campoTelefono.getText().trim());
//            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
//            mensaje=con.getProcedimiento().getString("mensaje");
                     
    }
    public void procesarAuxiliar() {    
         try {
             Validacion();             
             nuevoAuxiliar = new ConfirmarUsuario();
            // if(nuevoAuxiliar.confirmarUsuario(campoDocumento.getText(), campoUsuario.getText(), campoContrasenia.getText(), comboTipoAdmin.getValue(), campoNombre.getText(), campoApellidos.getText(), campoCorreo.getText(), campoTelefono.getText()))
             {
                 
                 try {
                     registarBibliotecario();
                     if(mensaje!=null){
                         
                          Utilidades.mensajeAdvertencia(null, mensaje, "Error al tratar de registrar el auxiliar", "Error Guardar Auxiliar");
                     }
                     else{
                         //dialogStage.setOpacity(0);
                         Utilidades.mensaje(null, "El autor se ha registrado correctamente", "Registrando Auxiliar", "Registro Exitoso");
                         dialogStage.close();
                     }
                 } 
                 catch (SQLException ex) {
                     
                     Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar el auxiliar", "Error Guardar Auxiliar");  
                 } 
             }
         } catch (SQLException ex) {
             Logger.getLogger(RegistroBibliotecarioController.class.getName()).log(Level.SEVERE, null, ex);
         }
    }
         
      public void registarBibliotecario() throws SQLException {
   
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarBibliotecario(?,?,?,?,?,?,?,?) }");

            con.getProcedimiento().setString("documento", campoDocumento.getText().trim());
            con.getProcedimiento().setString("usuario", campoUsuario.getText().trim());            
            con.getProcedimiento().setString("contrasenia", Utilidades.encriptar(campoContrasenia.getText().trim()));
            con.getProcedimiento().setString("tipoAdministrador", comboTipoAdmin.getValue().toString().trim());
            con.getProcedimiento().setString("nombre", campoNombre.getText().trim());
            con.getProcedimiento().setString("apellidos", campoApellidos.getText().trim());
            con.getProcedimiento().setString("correo", campoCorreo.getText().trim());
            con.getProcedimiento().setString("telefono", campoTelefono.getText().trim());
//            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar el auxiliar", "Error Guardar Auxiliar");  

        } finally {
            con.desconectar();
        }
    }
      public void Validacion() throws SQLException{
        ValidarUsuario validarNuevoAdmin = new ValidarUsuario(comboTipoAdmin.getSelectionModel().getSelectedItem(), campoNombre.getText(), campoApellidos.getText(),
                                                              campoUsuario.getText(), campoContrasenia.getText(), campoContrasenia2.getText(), campoCorreo.getText(),
                                                              campoDocumento.getText(), campoTelefono.getText());
        validarNuevoAdmin.validarAdminAxiliar();
        validarTipoAdmin.setText(validarNuevoAdmin.getErrorTipoAdmin());
        validarNombre.setText(validarNuevoAdmin.getErrorNombreUsuario());
        validarApellidos.setText(validarNuevoAdmin.getErrorApellidosUsuario());
        validarUsuario.setText(validarNuevoAdmin.getErrorUsuario());
        validarContrasenia.setText(validarNuevoAdmin.getErrorContrasenia());
        validarConfirmacion.setText(validarNuevoAdmin.getErrorConfirmacion());
        validarCorreo.setText(validarNuevoAdmin.getErrorCorreoUsuario());
        validarDocumento.setText(validarNuevoAdmin.getErrorDocumentoUsuario());
        validarTelefono.setText(validarNuevoAdmin.getErrorTelefonoUsuario());

    } 
      
       @FXML
    public void cancelar(ActionEvent evento){
    
        campoDocumento.clear();
        campoUsuario.clear();
        campoContrasenia.clear();
        campoNombre.clear();
        campoApellidos.clear();
        campoCorreo.clear();
        campoTelefono.clear();
        this.dialogStage.close();    
    }
           
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

} 
