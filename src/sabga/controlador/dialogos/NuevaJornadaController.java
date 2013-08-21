
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

public class NuevaJornadaController implements Initializable {
    
    private Stage dialogStage;
    @FXML
    private Label validarNuevaJornada;
    @FXML
    private TextField campoNuevaJornada;
    
    private ValidarUsuario validarJornada;
    private ConfirmarUsuario nuevaJornada;
    private Conexion con;
    private String mensaje;
    
    public NuevaJornadaController(){
    
        con = new Conexion();    
    }
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarJoranda(ActionEvent evento){
        
        procesarNuevaJornada();
    }
    
    public void procesarNuevaJornada(){
         
        validarCampos();
         nuevaJornada = new ConfirmarUsuario();
        if(nuevaJornada.confirmarJornada(campoNuevaJornada.getText())){
            
            try {
                registarJornada();
                if(mensaje!=null){
                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar la jornada", "Error Registrar Jornada");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Jornada registrada correctamente", "Registrando Jornada", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar la jornada", "Error Registrar Jornada");  
            }
        }
    }
    
    public void registarJornada() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarJornada(?,?) }");

            con.getProcedimiento().setString("nuevaJornada", campoNuevaJornada.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar la nueva Jornada", "Error Registrar Jornada");  

        } finally {
            con.desconectar();
        }
    }
    
    public void validarCampos() {

        validarJornada = new ValidarUsuario();
        validarJornada.validarNuevaJornada(campoNuevaJornada.getText());
        validarNuevaJornada.setText(validarJornada.getErrorJornada());

    }
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaJornada.clear();
        this.dialogStage.close();    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
