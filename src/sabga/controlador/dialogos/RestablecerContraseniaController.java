
package sabga.controlador.dialogos;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;


public class RestablecerContraseniaController implements Initializable {
    
    private Sabga ventanaPrincipal; 

    @FXML 
    private Label lblValidarDocumento, lblValidarCorreo, lblValidacion;    
    @FXML 
    private TextField txtfDocumento, txtfCorreo;
    @FXML 
    private Pane panelAyuda;
    @FXML
    private ToggleButton btnAyuda;    
    private final Conexion con;
    private String usuario = null, clave;    
    public RestablecerContraseniaController(){
    
        con = new Conexion();
    }
           
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {       
	this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    public void nuevaContrasenia(ActionEvent evento){        
         verificarUsuario();
    }
    
    private void verificarUsuario(){
        
        mensajesError();
        ConfirmarUsuario confirmar = new ConfirmarUsuario();
        if(confirmar.confirmarRecuperarContrasenia(txtfDocumento.getText(), txtfCorreo.getText())){           
            cambiarContrasenia();
        }
    }
    
    private void cambiarContrasenia(){
        
        validarUsuario();
        if(usuario != null){
            try {               
                guardarContrasenia();               
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar la nueva clave", "Error Guardar Clave");
            }                                  
        }
        else{
             lblValidacion.setText("No se ha encontrado el documento o correo del usuario.");
        }     
    }
    
    private void guardarContrasenia() throws SQLException{
          
        String asunto = "Nueva Contraseña";
        String mensaje= "Usted a solicitado una nueva clave para el ingreso al sistema SABGA.\n"+
                        "Usuario: "+ usuario+"\n"+
                        "Contraseña: "+clave+"\n\n"+
                        "Despues de ingresar, si lo desea puede cambiar la contraseña por una que pueda recordar fácilmente en el "+
                        "botón Menú opción Mi Cuenta.";
                
          try {

            clave = Utilidades.claveAleatoria();
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL nuevaContrasenia(?,?,?) }");            
            con.getProcedimiento().setString("documento", txtfDocumento.getText().trim());
            con.getProcedimiento().setString("email", txtfCorreo.getText().trim());
            con.getProcedimiento().setString("clave", Utilidades.encriptar(clave));
            con.getProcedimiento().execute();
            
            if(Utilidades.enviarCorreo(txtfCorreo.getText().trim(), asunto, mensaje)){
                con.getConexion().commit();
                Utilidades.mensaje(null,"La nueva clave se ha generado correctamente", "La contraseña se ha enviado a su correo", "Cotraseña Generada");
                cancelar(null);
            }
            else{
                con.getConexion().rollback();
                lblValidacion.setText("No se ha realizado el cambio de contraseña, favor intente nuevamente");
            }           
        } catch (SQLException e) {
            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al guardar la nueva clave", "Error Guardar Clave");  
        } finally {
            con.desconectar();
        }   
    
    }
    
    private void validarUsuario(){
    
         try {
            con.conectar();
            con.procedimiento("{ ? = CALL verificarBibliotecario(?,?) }");
            con.getProcedimiento().registerOutParameter(1, Types.VARCHAR);
            con.getProcedimiento().setString("documento", txtfDocumento.getText().trim());
            con.getProcedimiento().setString("email",txtfCorreo.getText().trim());                   
            con.getProcedimiento().execute();
            usuario = con.getProcedimiento().getString(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al verificar el usuario", "Error Vefificar Usuario");  
        } finally {
            con.desconectar();
        }     
    }
              
    private void mensajesError(){
        
         ValidarUsuario validarCampos = new ValidarUsuario();
         validarCampos.validarRecuperarContrasenia(txtfDocumento.getText(), txtfCorreo.getText());
         lblValidarDocumento.setText(validarCampos.getErrorDocumento());
         lblValidarCorreo.setText(validarCampos.getErrorCorreo());      
    }
    
    @FXML
    private void cancelar(ActionEvent evento){        
        this.ventanaPrincipal.ocultarDialogo();
        this.ventanaPrincipal.dialogoInicioSesion();       
    }
    
    @FXML
    public void ayuda(ActionEvent evento){    
        
        if(btnAyuda.isSelected()){         
            panelAyuda.setVisible(true);         
        }else{
            panelAyuda.setVisible(false);
        }         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
}
