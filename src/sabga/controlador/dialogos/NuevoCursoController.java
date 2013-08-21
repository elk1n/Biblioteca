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

/*
 * @author Elk1n
 */
public class NuevoCursoController implements Initializable {

    private Stage dialogStage;
    @FXML
    private TextField campoNuevoCurso;
    @FXML
    private Label validarNuevoCurso;
    
    private ValidarUsuario validarCurso;
    private ConfirmarUsuario nuevaCurso;
    private Conexion con;
    private String mensaje;

    public NuevoCursoController() {

        con = new Conexion();
    }

    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarCurso(){
    
        procesarNuevoCurso();
    }
    
    public void procesarNuevoCurso() {

        validarCampos();
        nuevaCurso = new ConfirmarUsuario();
        if (nuevaCurso.confirmarCurso(campoNuevoCurso.getText())) {

            try {
                registarCurso();
                if (mensaje != null) {

                    Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar el curso", "Error Registrar Curso");
                } else {
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Curso registrado correctamente", "Registrando Curso", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {

                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar el curso", "Error Registrar Curso");
            }
        }
    }

    public void registarCurso() throws SQLException {

        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarCurso(?,?) }");

            con.getProcedimiento().setString("nuevoCurso", campoNuevoCurso.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar el nuevo curso", "Error Registrar Curso");

        } finally {
            con.desconectar();
        }
    }

    public void validarCampos() {

        validarCurso = new ValidarUsuario();
        validarCurso.validarNuevoCurso(campoNuevoCurso.getText());
        validarNuevoCurso.setText(validarCurso.getErrorCurso());

    }

    @FXML
    public void cancelar(ActionEvent evento) {
        campoNuevoCurso.clear();
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
