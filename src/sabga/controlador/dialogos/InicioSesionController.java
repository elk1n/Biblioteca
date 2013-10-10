
package sabga.controlador.dialogos;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;


public class InicioSesionController implements Initializable{
    
    private Sabga ventanaPrincipal;  
    
    @FXML 
    private TextField campoUsuario;    
    @FXML 
    private PasswordField campoContrasenia;    
    @FXML 
    private Label validarUsuario, validacion;    
    @FXML 
    private ComboBox comboTipoAdmin;
    
    private final ObservableList listaAdministradores;
    
    private final Conexion con;
    private int usuario = 0;
 
    public InicioSesionController(){        
        con = new Conexion();
        listaAdministradores = FXCollections.observableArrayList();
    }
   
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {        
	this.ventanaPrincipal = ventanaPrincipal;
    }
     
    @FXML
    public void iniciarSesion(ActionEvent evento){  
        
        inicioSesion();        
    }
   
    private void inicioSesion(){
    
        mensajesUsuario();        
        ConfirmarUsuario confirmar = new ConfirmarUsuario();        
        if(confirmar.confirmarUsuario(campoUsuario.getText(), campoContrasenia.getText())){            
            consultarUsuario();
            if(usuario==1){
                this.ventanaPrincipal.inciarSesion();
                campoUsuario.clear();
                campoContrasenia.clear();
            }
            else{
                validacion.setText("El nombre de usuario o la contraseña introducidos no son correctos.");
            }           
        }
    }
   
    private void consultarUsuario(){

        try {
            con.conectar();
            con.procedimiento("{ ? = CALL inicioSesion(?,?,?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setString("usuario", campoUsuario.getText().trim());
            con.getProcedimiento().setString("clave",Utilidades.encriptar(campoContrasenia.getText().trim()));            
            con.getProcedimiento().setString("tipo", comboTipoAdmin.getSelectionModel().getSelectedItem().toString());            
            con.getProcedimiento().execute();
            usuario = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al inciar sesión", "Error Iniciar Sesión");  
        } finally {
            con.desconectar();
        } 
    }
    
    @FXML
    public void restablecerContrasenia(ActionEvent evento){
       this.ventanaPrincipal.dialogoRestablecerContrasenia();
       
    }
    
    public void mensajesUsuario(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarUsuario(campoUsuario.getText(), campoContrasenia.getText());
        validarUsuario.setText(validar.getErrorNombreUsuario());
        validacion.setText(validar.getErrorContrasenia());
    }
    
    private void llenarComboBox(){
    
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT tipo_administrador FROM tbl_BIBLIOTECARIO"));

            while (con.getResultado().next()) {
                listaAdministradores.add(con.getResultado().getString("tipo_administrador"));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
        comboTipoAdmin.setItems(listaAdministradores);
    }
   
    @FXML
    private void salir(ActionEvent evento){        
        System.exit(0);
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        llenarComboBox();
        comboTipoAdmin.getSelectionModel().selectFirst();
    }    
    
}
