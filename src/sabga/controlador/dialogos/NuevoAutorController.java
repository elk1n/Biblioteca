package sabga.controlador.dialogos;


import java.sql.SQLException;
import java.sql.Types;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sabga.Sabga;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.controlador.RegistroMaterialController;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class NuevoAutorController {

    private Stage dialogStage;
    @FXML
    private Label validarNombreAutor, validarApellidosAutor;
    @FXML
    private TextField campoNombreNuevoAutor, campoApellidosNuevoAutor;
    
    private ValidarMaterial validarNuevoAutor;
    private ConfirmarMaterial nuevoAutor;
    private final Conexion con;
    private String mensaje;
        
    public NuevoAutorController(){
    
         con = new Conexion();
    }
    
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevoAutor(ActionEvent evento) {

        procesarNuevoAutor();
          
    }
    
    public void procesarNuevoAutor(){
    
        validarCampos();
        nuevoAutor = new ConfirmarMaterial();
        if(nuevoAutor.confirmarNuevoAutor(campoNombreNuevoAutor.getText(), campoApellidosNuevoAutor.getText())){
            
            try {
                registarAutor();
                if(mensaje!=null){                    
                     Utilidades.mensajeAdvertencia(null, mensaje, "Error al tratar de registrar el autor", "Error Guardar Autor");
                }
                else{
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "El autor se ha registrado correctamente", "Registrando Autor", "Registro Exitoso");
                    RegistroMaterialController actualizarAutor= new RegistroMaterialController();
                    actualizarAutor.llenarAutores();
                    dialogStage.close();
                }
            } catch (SQLException ex) {
                
                Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar el autor", "Error Guardar Autor");  
            }
        } 
    }
    
    public void registarAutor() throws SQLException {
  
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarAutor(?,?,?) }");

            con.getProcedimiento().setString("nombreAutor", campoNombreNuevoAutor.getText().trim());
            con.getProcedimiento().setString("apellidosAutor", campoApellidosNuevoAutor.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar el autor", "Error Guardar Autor");  

        } finally {
            con.desconectar();
        }
    }
        
    public void validarCampos() {
        
        validarNuevoAutor = new ValidarMaterial();
        validarNuevoAutor.validarNuevoAutor(campoNombreNuevoAutor.getText(),campoApellidosNuevoAutor.getText());
        validarNombreAutor.setText(validarNuevoAutor.getErrorNombreAutor());
        validarApellidosAutor.setText(validarNuevoAutor.getErrorApellidosAutor());

    }
    
    @FXML
    public void cancelar(ActionEvent evento){
    
        campoApellidosNuevoAutor.clear();
        campoNombreNuevoAutor.clear();
        this.dialogStage.close();    
    }

    @FXML
    public void initialize() {
            
    }
}
