
package sabga.controlador;

import Usuario.Administrador;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ValidarUsuario;

/**
 * 
 * @author Elk1n
 * 
 */

public class EditarBibliotecarioController implements Initializable, ControlledScreen {
    public Conexion con;
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    
    public  ObservableList<Administrador> listaa;
    
    @FXML private TextField campoNombre, campoApellidos, campoUsuario, campoCorreo, campoDocumento, campoTelefono;
    
    @FXML private PasswordField campoContrasenia, campoNuevaContrasenia, campoConfirmacion;
    
    @FXML private ComboBox comboEstado;
    
    @FXML private TitledPane acordeonCambiar, acordeonDatos;
    
    @FXML private TableView tablaAdmin;
    
    @FXML private TableColumn tDocumento,tNombre, tApellido, tUsuario, tCorreo,tTelefono,tEstado;
    
    @FXML private Label validarNombre, validarApellidos, validarUsuario, validarCorreo, validarDocumento, validarTelefono, validarContrasenia,
                        validarNuevaContrasenia, validarConfirmacion;
        
    private String mensaje,documento,usuario, nombre, apellido, telefono, correo,estado;
    public int variable;
    
    public EditarBibliotecarioController(){ 
        listaa = FXCollections.observableArrayList();
    }   
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    @FXML
    public void validar(){
        
//        ValidarUsuario validarEdicion = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoUsuario.getText(), campoCorreo.getText(),
//                                                           campoDocumento.getText(), campoTelefono.getText(), comboEstado.getSelectionModel().getSelectedItem());
//        
//        validarEdicion.validarEdicionAdmin();
//        validarNombre.setText(validarEdicion.getErrorNombreUsuario());
//        validarApellidos.setText(validarEdicion.getErrorApellidosUsuario());
//        validarUsuario.setText(validarEdicion.getErrorUsuario());
//        validarCorreo.setText(validarEdicion.getErrorCorreoUsuario());
//        validarDocumento.setText(validarEdicion.getErrorDocumentoUsuario());
//        validarTelefono.setText(validarEdicion.getErrorTelefonoUsuario());
    
    } 
    
    @FXML
    public void validarNuevaContrasenia(ActionEvent evento){
        
//        ValidarUsuario validarNuevaContra = new ValidarUsuario(campoContrasenia.getText(), campoNuevaContrasenia.getText(), campoConfirmacion.getText());
//        
//        validarNuevaContra.validarNuevaContrasenia();
//        validarContrasenia.setText(validarNuevaContra.getErrorContrasenia());
//        validarNuevaContrasenia.setText(validarNuevaContra.getErrorNuevaContrasenia());
//        validarConfirmacion.setText(validarNuevaContra.getErrorConfirmacion());
//               
    }
    
public Administrador getPosiciones(){
List<Administrador> tabla= tablaAdmin.getSelectionModel().getSelectedItems();
final Administrador tabla1 = tabla.get(0);
return tabla1;
}

public void mapearDatos() {
    
final Administrador admin = getPosiciones();
       
            //acordeonDatos.setExpanded(true);

             variable=listaa.indexOf(admin);
            campoNombre.setText(admin.getNombre());
            campoApellidos.setText(admin.getApellido());
            campoUsuario.setText(admin.getUsuarioo());
            campoCorreo.setText(admin.getMail());
            campoDocumento.setText(admin.getDocumento_bibliotecario());
            campoTelefono.setText(admin.getTelefono());           
            comboEstado.getSelectionModel().select(admin.getEstado());   
            
   if(comboEstado.getSelectionModel().getSelectedItem().equals("Inhabilitado")){
       campoNombre.setDisable(true);
       campoApellidos.setDisable(true);
       campoUsuario.setDisable(true);
       campoCorreo.setDisable(true);
       campoDocumento.setDisable(true);
       campoTelefono.setDisable(true);
       comboEstado.setDisable(false);      
      
   }
   else{
       campoNombre.setDisable(false);
       campoApellidos.setDisable(false);
       campoUsuario.setDisable(false);
       campoCorreo.setDisable(false);
       campoDocumento.setDisable(false);
       campoTelefono.setDisable(false);
       comboEstado.setDisable(false); 
      
   }
        
   }

     public void validarDatos(ActionEvent evento) throws SQLException{
    int est;
    Conexion cons = new Conexion();
    validar();
    cons.conectar();
   
       if (comboEstado.getSelectionModel().getSelectedItem().equals("Habilitado")){est=1;}
       else{est=0;}
        try {
            
            cons.getConexion().setAutoCommit(false);
            cons.procedimiento("{ CALL editarBibliotecario1(?,?,?,?,?,?,?)}");     
            cons.getProcedimiento().setString("documento",campoDocumento.getText().trim());  
            cons.getProcedimiento().setString("usuario", campoUsuario.getText().trim());
            cons.getProcedimiento().setString("nombre", campoNombre.getText().trim());
            cons.getProcedimiento().setString("apellidos", campoApellidos.getText().trim());
            cons.getProcedimiento().setString("correo", campoCorreo.getText().trim());
            cons.getProcedimiento().setString("telefono", campoTelefono.getText().trim());
            cons.getProcedimiento().setInt("estado", est);
            //cons.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            
           //System.out.println("el doc es "+ documento);
            cons.getProcedimiento().execute();
            cons.getConexion().commit();
            //mensaje = cons.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            cons.getConexion().rollback();
            //Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de editar el administrador", "Error Editar Administrador");  

        } finally {
            cons.desconectar();
        }    
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
          
Conexion con = new Conexion();
        try {
           
        con.conectar();
         con.setResultado(con.getStatement().executeQuery("SELECT documento_bibliotecario,nombre_usuario,tipo_administrador,nombre,apellidos,correo, telefono,estado FROM tbl_BIBLIOTECARIO"));
         
          while (con.getResultado().next()){
        listaa.add(new Administrador(con.getResultado().getString("documento_bibliotecario"), con.getResultado().getString("nombre_Usuario"), con.getResultado().getString("tipo_administrador"), con.getResultado().getString("nombre"), con.getResultado().getString("apellidos"), con.getResultado().getString("correo"), con.getResultado().getString("telefono"), con.getResultado().getString("estado")));
          }
          /*for(Administrador p: listaa){
           System.out.println("el doc es "+p.documento_bibliotecario+"    "+p.usuarioo+"    "+p.tipoAdmin+"    "+p.nombre+"    "+p.apellido+"    "+p.mail+"    "+p.telefono+"    "+p.estado); 
       }*/
         con.desconectar(); 
    }   
         catch (SQLException ex) {
             Logger.getLogger(EditarBibliotecarioController.class.getName()).log(Level.SEVERE, null,ex);
                     
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde\nllenar tabla Usuario", "Error");
        }
        tDocumento.setCellValueFactory(new PropertyValueFactory<Administrador, String>("documento_bibliotecario"));
        tDocumento.setPrefWidth(200);
        tNombre.setCellValueFactory(new PropertyValueFactory<Administrador, String>("nombre"));
        tNombre.setPrefWidth(200);
        tApellido.setCellValueFactory(new PropertyValueFactory<Administrador, String>("apellido"));
        tApellido.setPrefWidth(200);
        tUsuario.setCellValueFactory(new PropertyValueFactory<Administrador, String>("usuarioo"));
        tUsuario.setPrefWidth(200);
        tCorreo.setCellValueFactory(new PropertyValueFactory<Administrador, String>("mail"));
        tCorreo.setPrefWidth(200);
        tTelefono.setCellValueFactory(new PropertyValueFactory<Administrador, String>("telefono"));
        tTelefono.setPrefWidth(200);
        tEstado.setCellValueFactory(new PropertyValueFactory<Administrador, String>("estado"));
        tEstado.setPrefWidth(200);
        
tablaAdmin.setItems(listaa);
}
}
