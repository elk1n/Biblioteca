
package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class RegistroUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    private ScreensController controlador;
    
    @FXML private GridPane gridRegistrar1, gridRegistrar2;
    
    @FXML private TextField campoNombre, campoApellidos, campoCorreo, campoDocumento, campoTelefono,
                            campoDireccion;
   
    @FXML private ComboBox comboTipoUsuario, comboCurso, comboGrupo, comboJornada;
    
    @FXML private Button botonCancelar, botonGuardarUsuario;
    
    @FXML private Label validarNombre, validarApellidos, validarCurso, validarGrupo, validarCorreo, validarDocumento, validarJornada,
                        validarTelefono, validarDireccion, etiquetaCurso, etiquetaGrupo, etiquetaJornada;
    
    private ObservableList grado;
    
    public RegistroUsuarioController() {
        
        this.grado = FXCollections.observableArrayList();
        
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;

     }
    
    public void setVentanaPrincipal(Sabga principal) {

        this.paginaPrincipal = principal;

    }
    
    @FXML
    public void registrarNuevoUsuario(ActionEvent evento){
        
        if(comboTipoUsuario.getSelectionModel().getSelectedIndex() == 0){
            
            ValidarUsuario validarNuevoUsuario = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoCorreo.getText(),
                                                                    comboCurso.getSelectionModel().getSelectedItem(), comboGrupo.getSelectionModel().getSelectedItem(),
                                                                    comboJornada.getSelectionModel().getSelectedItem(), campoDocumento.getText(), campoTelefono.getText(),
                                                                    campoDireccion.getText());
            validarNuevoUsuario.validarNuevoUsuario();
            validarNombre.setText(validarNuevoUsuario.getErrorNombreUsuario());
            validarApellidos.setText(validarNuevoUsuario.getErrorApellidosUsuario());
            validarCorreo.setText(validarNuevoUsuario.getErrorCorreoUsuario());
            validarCurso.setText(validarNuevoUsuario.getErrorCursoUsuario());
            validarGrupo.setText(validarNuevoUsuario.getErrorGrupoUsuario());
            validarJornada.setText(validarNuevoUsuario.getErrorJornadaUsuario());
            validarDocumento.setText(validarNuevoUsuario.getErrorDocumentoUsuario());
            validarTelefono.setText(validarNuevoUsuario.getErrorTelefonoUsuario());
            validarDireccion.setText(validarNuevoUsuario.getErrorDireccionUsuario());
        }
        
        else if(comboTipoUsuario.getSelectionModel().getSelectedIndex() == 1){
            
            ValidarUsuario validarNuevoEmpleado = new ValidarUsuario(campoNombre.getText(), campoApellidos.getText(), campoCorreo.getText(),
                                                                     campoDocumento.getText(), campoTelefono.getText(), campoDireccion.getText());
            
            validarNuevoEmpleado.validarNuevoEmpleado();
            validarNombre.setText(validarNuevoEmpleado.getErrorNombreUsuario());
            validarApellidos.setText(validarNuevoEmpleado.getErrorApellidosUsuario());
            validarCorreo.setText(validarNuevoEmpleado.getErrorCorreoUsuario());
            validarDocumento.setText(validarNuevoEmpleado.getErrorDocumentoUsuario());
            validarTelefono.setText(validarNuevoEmpleado.getErrorTelefonoUsuario());
            validarDireccion.setText(validarNuevoEmpleado.getErrorDireccionUsuario());
                     
        }
        
    }
    
    @FXML
    public void mostrarGrid(ActionEvent evento){
        
        if (comboTipoUsuario.getSelectionModel().getSelectedIndex()==0){
            
            gridRegistrar1.setVisible(true);
            gridRegistrar2.setVisible(true);
            gridRegistrar1.setLayoutY(comboTipoUsuario.getLayoutY()+110);
            gridRegistrar2.setLayoutY(comboTipoUsuario.getLayoutY()+110);
            etiquetaCurso.setVisible(true);
            comboCurso.setVisible(true);
            validarCurso.setVisible(true);
            etiquetaGrupo.setVisible(true);
            comboGrupo.setVisible(true);
            validarGrupo.setVisible(true);
            etiquetaJornada.setVisible(true);
            comboJornada.setVisible(true);
            validarJornada.setVisible(true);
            botonCancelar.setVisible(true);
            botonGuardarUsuario.setVisible(true);
        
            
                       
        }
        else if (comboTipoUsuario.getSelectionModel().getSelectedIndex()==1){
            
            gridRegistrar1.setVisible(true);
            gridRegistrar2.setVisible(true);           
            etiquetaCurso.setVisible(false);
            comboCurso.setVisible(false);
            validarCurso.setVisible(false);
            etiquetaGrupo.setVisible(false);
            comboGrupo.setVisible(false);
            validarGrupo.setVisible(false);
            etiquetaJornada.setVisible(false);
            comboJornada.setVisible(false);
            validarJornada.setVisible(false);
            gridRegistrar1.setLayoutY(comboTipoUsuario.getLayoutY()+150);
            gridRegistrar2.setLayoutY(comboTipoUsuario.getLayoutY()+82);
            botonCancelar.setVisible(true);
            botonGuardarUsuario.setVisible(true);            
        }
    }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        gridRegistrar1.setVisible(false);
        gridRegistrar2.setVisible(false);
        botonCancelar.setVisible(false);
        botonGuardarUsuario.setVisible(false);
        cargarCombo();
     
        }    
    
    public void cargarCombo() {
            
        try {   
         
            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_CURSO"));

            while (con.getResultado().next()) {
                
                grado.add(con.getResultado().getObject("curso"));
                
            }
            comboGrupo.setItems(grado);
            con.desconectar(); 
        } catch (SQLException ex) {
            
             Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error"); 
        }
    }

      
    }
        

