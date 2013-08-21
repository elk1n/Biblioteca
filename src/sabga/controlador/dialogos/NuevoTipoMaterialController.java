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
public class NuevoTipoMaterialController implements Initializable {

    private Stage dialogStage;
    @FXML
    private Label validarNuevoTipoM;
    @FXML
    private TextField campoNuevoTipoM;
    private ValidarMaterial validarTipoMaterial;
    private ConfirmarMaterial nuevoTipoMaterial;
    private Conexion con;
    private String mensaje;

    public NuevoTipoMaterialController() {

        con = new Conexion();
    }

    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }

    @FXML
    public void guardarNuevoTipoMaterial(ActionEvent evento) {

        procesarNuevoTipoMaterial();
    }

    public void procesarNuevoTipoMaterial() {

        validarCampos();
        nuevoTipoMaterial = new ConfirmarMaterial();
        if (nuevoTipoMaterial.confirmarNuevoTipoMaterial(campoNuevoTipoM.getText())) {

            try {
                registarNuevoTipoMaterial();
                if (mensaje != null) {

                    Utilidades.mensajeAdvertencia(null, mensaje, "Error al registrar  nuevo tipo de material", "Error Registrar Tipo Material");
                } else {
                    //dialogStage.setOpacity(0);
                    Utilidades.mensaje(null, "Tipo material registrado correctamente", "Registrando Tipo Material", "Registro Exitoso");
                    dialogStage.close();
                }
            } catch (SQLException ex) {

                Utilidades.mensajeError(null, ex.getMessage(), "Error al registrar nuevo tipo material", "Error Registrar Tipo Material");
            }
        }
    }

    public void registarNuevoTipoMaterial() throws SQLException {

        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarTipoMaterial(?,?) }");

            con.getProcedimiento().setString("tipoMaterial", campoNuevoTipoM.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al registrar el nuevo tipo de material", "Error Registrar Tipo Material");

        } finally {
            con.desconectar();
        }
    }

    public void validarCampos() {

        validarTipoMaterial = new ValidarMaterial();
        validarTipoMaterial.validarTipoMaterial(campoNuevoTipoM.getText());
        validarNuevoTipoM.setText(validarTipoMaterial.getErrorTipoMaterial());
    }

    @FXML
    public void cancelar(ActionEvent evento) {
        campoNuevoTipoM.clear();
        this.dialogStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
