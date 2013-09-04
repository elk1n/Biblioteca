
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
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.ValidarMaterial;


/**
 * @author Elk1n
 */

public class NuevaClaseMaterialController implements Initializable {
    
    private Stage dialogStage;
    
    @FXML
    private Label validacionClaseM;
    @FXML
    private TextField campoNombreClaseM;
    
    private ValidarMaterial validarClaseM;
    private ConfirmarMaterial nuevaClaseM;
    private Conexion con;
    private String mensaje;
    
    public NuevaClaseMaterialController(){
    
        con = new Conexion();
    }
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarClaseMaterial(ActionEvent evento){
        
        procesarNuevaClaseMaterial();       
    }
    
    public void procesarNuevaClaseMaterial(){
         
        validarCampos();
         nuevaClaseM = new ConfirmarMaterial();
        if(nuevaClaseM.confirmarNuevaClaseMaterial(campoNombreClaseM.getText())){
            
            try {
                registarNuevaClaseMaterial();
                if(mensaje!=null){
                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar la nueva clase de material", "Error Registrar Clase Material");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Clase de material registrada correctamente", "Registrando nueva clase de material", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar la nueva clase material", "Error Registrar Clase Material");  
            }
        }
    }
    
    public void registarNuevaClaseMaterial() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarClaseMaterial(?,?) }");

            con.getProcedimiento().setString("claseMaterial", campoNombreClaseM.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar la nueva clase de material", "Error Registrar Clase Material");  

        } finally {
            con.desconectar();
        }
    }
    
    public void validarCampos() {
        
        validarClaseM = new ValidarMaterial();
        validarClaseM.validarClaseMaterial(campoNombreClaseM.getText());
        validacionClaseM.setText(validarClaseM.getErrorClaseMaterial());
    }
    
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNombreClaseM.clear();
        this.dialogStage.close();    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
