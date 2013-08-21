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

public class NuevaMateriaController {

    private Stage dialogStage;
    @FXML
    private Label validarNuevaMateria;
    @FXML
    private TextField campoNuevaMateria;
    
    private ValidarMaterial validarMateria;
    private ConfirmarMaterial nuevaMateria;
    private Conexion con;
    private String mensaje;
    
    public NuevaMateriaController(){
    
        con = new Conexion();  
    }

    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevaMateria(ActionEvent evento) {

        procesarNuevaMateria();
    }
    public void procesarNuevaMateria(){
        validarCampos();
         nuevaMateria = new ConfirmarMaterial();
        if(nuevaMateria.confirmarNuevaMateria(campoNuevaMateria.getText())){
            
            try {
                registarMateria();
                if(mensaje!=null){
                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al tratar de registrar la materia", "Error Registrar Materia");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Materia registrada correctamente", "Registrando Materia", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar la materia", "Error Registrar Materia");  
            }
        }
    }
    
    public void registarMateria() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarMateria(?,?) }");

            con.getProcedimiento().setString("materia", campoNuevaMateria.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje=con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar la nueva materia", "Error Registrar Materia");  

        } finally {
            con.desconectar();
        }
    }
    
    public void validarCampos() {

        validarMateria = new ValidarMaterial();
        validarMateria.validarNuevaMateria(campoNuevaMateria.getText());
        validarNuevaMateria.setText(validarMateria.getErrorNombreMateria());
    }
    
    @FXML
    public void cancelar(ActionEvent evento){    
        campoNuevaMateria.clear();
        this.dialogStage.close();    
    }
    
    public void initialize(URL url, ResourceBundle rb) {
    }
}
