package sabga.controlador.dialogos;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class NuevaEditorialController {

    private Stage dialogStage;
    @FXML
    private Label validarNuevaEditorial;
    @FXML
    private TextField campoNuevaEditorial;
    
    private ValidarMaterial validarEditorial;
    private ConfirmarMaterial nuevaEditorial;
    private final Conexion con;
    private String mensaje;
    
    public NuevaEditorialController(){
        con = new Conexion();    
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevaEditorial(ActionEvent evento) {
        procesarNuevaEditorial();
    }
    
    public void procesarNuevaEditorial(){
         
        validarCampos();
         nuevaEditorial = new ConfirmarMaterial();
        if(nuevaEditorial.confirmarNuevaEditorial(campoNuevaEditorial.getText())){
            
            try {
                registarEditorial();
                if(mensaje!=null){                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar la editorial", "Error Registrar Editorial");
                }else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Editorial registrada correctamente", "Registrando Editorial", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) { 
                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar la editorial", "Error Registrar Editorial");  
            }
        }
    }
    
    public void registarEditorial() throws SQLException {
  
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarEditorial(?,?) }");
            con.getProcedimiento().setString("editorial", campoNuevaEditorial.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");
        } catch (SQLException e) {
            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar la nueva editorial", "Error Registrar Editorial");  
        } finally {
            con.desconectar();
        }
    }
    
    public void validarCampos() {
        validarEditorial = new ValidarMaterial();
        validarEditorial.validarNuevaEditorial(campoNuevaEditorial.getText());
        validarNuevaEditorial.setText(validarEditorial.getErrorEditorial());
    }

    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaEditorial.clear();
        this.dialogStage.close();    
    }
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
