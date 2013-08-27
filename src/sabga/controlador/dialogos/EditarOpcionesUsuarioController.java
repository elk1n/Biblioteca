
package sabga.controlador.dialogos;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Listar;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class EditarOpcionesUsuarioController implements Initializable {
    
    private Stage dialogStage;
    
    @FXML
    private ComboBox comboListar;
    @FXML
    private TableView tablaResultados;
    @FXML
    private TableColumn clmnNombre;
    @FXML
    private Button  botonEliminar;
    @FXML
    private TextField txtfNombre;
    @FXML
    private Label lblValidacion;
    
    private String nombre, mensaje;
    private int id;
    private ObservableList<Listar> listaDatos;    
     private Conexion con;
    
    public EditarOpcionesUsuarioController(){ 
        
        con = new Conexion();
        listaDatos = FXCollections.observableArrayList();
    }
     
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarCambios(ActionEvent evento){    
        edicion();
    }
    
    @FXML
    public void eliminar(ActionEvent evento){
    
    }
    
    public void edicion() {
         
        ConfirmarUsuario verificar = new ConfirmarUsuario();
        
        validarCampo();
       
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                obtenerId("SELECT id_grado FROM tbl_GRADO WHERE grado =", "'" + nombre + "'", "id_grado");
                editar(txtfNombre.getText().trim(), "{ CALL editarGrado(?,?,?,?) }", txtfNombre.getText().trim(), 1);               
                resetear();
            }            
        } 
        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                obtenerId("SELECT id_curso FROM tbl_CURSO WHERE curso=", "'" + nombre + "'", "id_curso");
                editar(txtfNombre.getText().trim(), "{ CALL editarCurso(?,?,?,?) }", txtfNombre.getText().trim(), 1);
                resetear();
            }
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                obtenerId("SELECT id_jornada FROM tbl_JORNADA WHERE jornada=", "'" + nombre + "'", "id_jornada");
                editar(txtfNombre.getText().trim(), "{ CALL editarJornada(?,?,?,?) }", txtfNombre.getText().trim(), 1);
                resetear();
            }
        } 
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                obtenerId("SELECT id_tipo_usuario FROM tbl_TIPO_USUARIO WHERE tipo_usuario=", "'" + nombre + "'", "id_tipo_usuario");
                editar(txtfNombre.getText().trim(), "{ CALL editarTipoUsuario(?,?,?,?) }", txtfNombre.getText().trim(), 1);
                resetear();
            }
        }
        
    }
    
    public void guardarEdicion(String procedimiento, String campo ,int seleccion) throws SQLException{
    
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento(procedimiento);

            con.getProcedimiento().setInt("id", id);
            con.getProcedimiento().setInt("opcion", seleccion);
            con.getProcedimiento().setString("nombre", campo);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de editar la selección ", "Error");  

        } finally {
            con.desconectar();
        }
    }
    
    public void editar(String campo, String procedimiento, String campos ,int seleccion ){
        
        if(!nombre.equalsIgnoreCase(campo)){
            try {
                guardarEdicion(procedimiento, campos , seleccion);
                if (mensaje != null) {

                    Utilidades.mensajeAdvertencia(null, mensaje, "Error al editar la selección", "Error Guardar Cambios");
                } else {
                    Utilidades.mensaje(null, "Los cambios se han guardado correctamente", "Editando Selección", "Actualización Exitosa");
                }
            } catch (SQLException ex) {

                Utilidades.mensajeError(null, ex.getMessage(), "Error al actualizar la información", "Error Guardar Cambios");
            }            
        }else {
            Utilidades.mensaje(null, "No se han presentado cambios", "Editando Selección", "Editar Selección");
        }
    
    }
    
    public void obtenerId(String consulta, String nombre, String columna) {

        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta + nombre));

            if (con.getResultado().first()) {
                id = con.getResultado().getInt(columna);
            }

        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }

    }
        
    private void validarCampo(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarNuevoCurso(txtfNombre.getText());
        lblValidacion.setText(validar.getErrorCurso());               
    }
       
    @FXML
    public void listar(ActionEvent evento){
        
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            listarDatos("SELECT grado FROM tbl_GRADO", "grado");
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            listarDatos("SELECT curso FROM tbl_CURSO", "curso");            
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            listarDatos("SELECT jornada FROM tbl_JORNADA", "jornada");
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            listarDatos("SELECT tipo_usuario FROM tbl_TIPO_USUARIO", "tipo_usuario");
        }
    }
    
    @FXML
    public void mapearDatos(){
        
     txtfNombre.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
     txtfNombre.setDisable(false);
     nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();
    
    }
    
    public void listarDatos(String tabla, String consulta) {

        listaDatos.removeAll(listaDatos);

        try {

            con.conectar();
            con.setResultado(con.getStatement().executeQuery(tabla));

            while (con.getResultado().next()) {

                listaDatos.add(new Listar(con.getResultado().getString(consulta)));
            }
            con.desconectar();
            clmnNombre.setCellValueFactory(new PropertyValueFactory<Listar, String>("nombre"));
            tablaResultados.setEditable(true);
            tablaResultados.setItems(listaDatos);

        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    }
    
    private void resetear() {
        
        txtfNombre.setText("");
        txtfNombre.setDisable(true);
        nombre = null;
        id = 0;
        listar(null);
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        txtfNombre.clear();
        dialogStage.close();
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        txtfNombre.setDisable(true);
    
    }    
}
