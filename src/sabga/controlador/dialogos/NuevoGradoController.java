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

public class NuevoGradoController implements Initializable {

    private Stage dialogStage;
    @FXML
    private Label validarNuevoGrado;
    @FXML
    private TextField campoNuevoGrado;
    
    private ValidarUsuario validarGrado;
    private ConfirmarUsuario nuevoGrado;
    private Conexion con;
    private String mensaje;
    
    public NuevoGradoController(){
        
        con = new Conexion();
        
    }
       
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarGrado(ActionEvent evento){
        
        procesarNuevoGrado();
    }
    
    public void procesarNuevoGrado(){
         
        validarCampos();
        nuevoGrado = new ConfirmarUsuario();
        if(nuevoGrado.confirmarGrado(campoNuevoGrado.getText())){
            
            try {
                registrarGrado();
                if(mensaje!=null){
                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar el grado", "Error Registrar Grado");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Grado registrado correctamente", "Registrando Grado", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar el grado", "Error Registrar Grado");  
            }
        }
    }
    
    public void registrarGrado() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarGrado(?,?) }");

            con.getProcedimiento().setString("nuevoGrado", campoNuevoGrado.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar el nuevo grado", "Error Registrar Grado");  

        } finally {
            con.desconectar();
        }
    }
    
    public void validarCampos() {

        validarGrado = new ValidarUsuario();
        validarGrado.validarNuevoGrado(campoNuevoGrado.getText());
        validarNuevoGrado.setText(validarGrado.getErrorGrado());
    }
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevoGrado.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
