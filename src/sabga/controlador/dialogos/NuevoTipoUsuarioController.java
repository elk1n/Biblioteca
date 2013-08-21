
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
import javafx.stage.Stage;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class NuevoTipoUsuarioController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private TextField campoNuevoTipoU;
    @FXML
    private Label validarNuevoTipoU;
    
    private ValidarUsuario validarTipoUsuario;
    private ConfirmarUsuario nuevoTipoUsuario;
    private Conexion con;
    private String mensaje;
    
    public NuevoTipoUsuarioController(){
    
        con = new Conexion();
    
    }
        
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarTipoUsuario(ActionEvent evento){
        
        procesarNuevoTipoUsuario();
    }
    
    public void procesarNuevoTipoUsuario(){
         
        validarCampos();
         nuevoTipoUsuario = new ConfirmarUsuario();
        if(nuevoTipoUsuario.confirmarTipoUsuario(campoNuevoTipoU.getText())){
            
            try {
                registarTipoUsuario();
                if(mensaje!=null){
                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar el nuevo tipo de usuario", "Error Registrar Tipo Usuario");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Nuevo tipo de usuario registrado correctamente", "Registrando Tipo de Usuario", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar el nuevo tipo de usuario", "Error Registrar Tipo Usuario");  
            }
        }
    }
    
    public void registarTipoUsuario() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarTipoUsuario(?,?) }");

            con.getProcedimiento().setString("tipoUsuario", campoNuevoTipoU.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar el nuevo tipo de usuario", "Error Registrar Tipo Usuario");  

        } finally {
            con.desconectar();
        }
    }
    
     public void validarCampos() {

        validarTipoUsuario = new ValidarUsuario();
        validarTipoUsuario.validarNuevoTipoUsuario(campoNuevoTipoU.getText());
        validarNuevoTipoU.setText(validarTipoUsuario.getErrorTipoUsuario());

    }
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevoTipoU.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
