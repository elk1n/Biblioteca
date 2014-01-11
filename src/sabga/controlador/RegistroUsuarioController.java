
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
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
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class RegistroUsuarioController implements Initializable, ControlledScreen {
    
    private Sabga paginaPrincipal;
    private ScreensController controlador;
    
    @FXML 
    private GridPane gridRegistrar1, gridRegistrar2;    
    @FXML 
    private TextField txtfNombre, txtfApellido, txtfCorreo, txtfDocumento, txtfTelefono, txtfDireccion;  
    @FXML 
    private ComboBox comboTipoUsuario, comboGrado, comboCurso, comboJornada;    
    @FXML 
    private Button botonCancelar, botonGuardarUsuario;   
    @FXML 
    private Label lblNombre, lblApellido, lblGrado, lblCurso, lblCorreo, lblDocumento, lblJornada,
                  lblTelefono, lblDireccion, etiquetaCurso, etiquetaGrupo, etiquetaJornada;
    private final Consultas consulta;
         
    public RegistroUsuarioController() {
        consulta = new Consultas();
    }
    
   @FXML
    public void registrarUsuario(ActionEvent evento){
        registrarUsuario();
    }
    
    @FXML
    public void cancelar(ActionEvent evento){
        cancelar();
    }
    
    private void registrarUsuario(){ 
        mensajes();
        if (comboTipoUsuario.getSelectionModel().getSelectedItem().toString().toLowerCase().contains("estudiante")){
            ConfirmarUsuario estudiante = new ConfirmarUsuario();
            if(estudiante.nuevoEstudiante(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                          comboGrado.getSelectionModel().getSelectedItem(), comboCurso.getSelectionModel().getSelectedItem(),
                                          comboJornada.getSelectionModel().getSelectedItem(), txtfDocumento.getText(), txtfTelefono.getText(),
                                          txtfDireccion.getText())){
                    consulta.registrarUsuario(1, comboTipoUsuario.getSelectionModel().getSelectedItem().toString(), txtfNombre.getText(),
                                              txtfApellido.getText(), txtfCorreo.getText(), txtfDocumento.getText(), 
                                              comboGrado.getSelectionModel().getSelectedItem().toString(), comboCurso.getSelectionModel().getSelectedItem().toString(),
                                              comboJornada.getSelectionModel().getSelectedItem().toString(), txtfTelefono.getText(), txtfDireccion.getText());
                    if(consulta.getMensaje()== null){
                        cancelar();
                        Utilidades.mensaje(null, "El usuario se ha registrado correctamente.","", "Registrar Usuario");                        
                    }
                    else{
                        Utilidades.mensajeError(null, consulta.getMensaje(),"El usuario no ha sido registrado.", "Error Registro");
                    }
            }     
        }else{
            ConfirmarUsuario funcionario = new ConfirmarUsuario();
            if(funcionario.nuevoFuncionario(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),txtfDocumento.getText(), 
                                            txtfTelefono.getText(), txtfDireccion.getText())){
                consulta.registrarUsuario(2, comboTipoUsuario.getSelectionModel().getSelectedItem().toString(), txtfNombre.getText(),
                                             txtfApellido.getText(), txtfCorreo.getText(), txtfDocumento.getText(), null, null, null,
                                             txtfTelefono.getText(), txtfDireccion.getText());                
                if(consulta.getMensaje()== null){
                        cancelar();
                        Utilidades.mensaje(null, "El usuario se ha registrado correctamente.","", "Registrar Usuario");                        
                    }
                else{
                    Utilidades.mensajeError(null, consulta.getMensaje(),"El usuario no ha sido registrado.", "Error Registro");
                }
            }            
        }        
    }
        
    public void mensajes(){
        
        if(comboTipoUsuario.getSelectionModel().getSelectedItem().toString().toLowerCase().contains("estudiante")){
            
            ValidarUsuario estudiante = new ValidarUsuario();
            estudiante.validarNuevoEstudiante(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                             comboGrado.getSelectionModel().getSelectedItem(), comboCurso.getSelectionModel().getSelectedItem(),
                                             comboJornada.getSelectionModel().getSelectedItem(), txtfDocumento.getText(), txtfTelefono.getText(),
                                             txtfDireccion.getText());
            lblNombre.setText(estudiante.getErrorNombre());
            lblApellido.setText(estudiante.getErrorApellido());
            lblCorreo.setText(estudiante.getErrorCorreo());
            lblGrado.setText(estudiante.getErrorGrado());
            lblCurso.setText(estudiante.getErrorCurso());
            lblJornada.setText(estudiante.getErrorJornada());
            lblDocumento.setText(estudiante.getErrorDocumento());
            lblTelefono.setText(estudiante.getErrorTelefono());
            lblDireccion.setText(estudiante.getErrorDireccion());
        }       
        else{            
            ValidarUsuario funcionario = new ValidarUsuario();
            funcionario.validarNuevoFuncionario(txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                                txtfDocumento.getText(), txtfTelefono.getText(), txtfDireccion.getText());
            
            lblNombre.setText(funcionario.getErrorNombre());
            lblApellido.setText(funcionario.getErrorApellido());
            lblCorreo.setText(funcionario.getErrorCorreo());
            lblDocumento.setText(funcionario.getErrorDocumento());
            lblTelefono.setText(funcionario.getErrorTelefono());
            lblDireccion.setText(funcionario.getErrorDireccion());                     
        } 
    }
    
    public void cancelar(){
        
        txtfNombre.clear();
        txtfApellido.clear();
        txtfCorreo.clear();
        comboGrado.getSelectionModel().clearSelection();
        comboCurso.getSelectionModel().clearSelection();
        comboJornada.getSelectionModel().clearSelection();
        txtfDocumento.clear();
        txtfTelefono.clear();
        txtfDireccion.clear();
    }
    
    @FXML
    public void mostrarGrid(ActionEvent evento){
        
        if (comboTipoUsuario.getSelectionModel().getSelectedItem().toString().toLowerCase().contains("estudiante")){
            
            gridRegistrar1.setVisible(true);
            gridRegistrar2.setVisible(true);
            gridRegistrar1.setLayoutY(comboTipoUsuario.getLayoutY()+110);
            gridRegistrar2.setLayoutY(comboTipoUsuario.getLayoutY()+110);
            etiquetaCurso.setVisible(true);
            comboCurso.setVisible(true);
            lblGrado.setVisible(true);
            etiquetaGrupo.setVisible(true);
            comboGrado.setVisible(true);
            lblCurso.setVisible(true);
            etiquetaJornada.setVisible(true);
            comboJornada.setVisible(true);
            lblJornada.setVisible(true);
            botonCancelar.setVisible(true);
            botonGuardarUsuario.setVisible(true);                       
        }
        else{
            
            gridRegistrar1.setVisible(true);
            gridRegistrar2.setVisible(true);           
            etiquetaCurso.setVisible(false);
            comboCurso.setVisible(false);
            lblGrado.setVisible(false);
            etiquetaGrupo.setVisible(false);
            comboGrado.setVisible(false);
            lblCurso.setVisible(false);
            etiquetaJornada.setVisible(false);
            comboJornada.setVisible(false);
            lblJornada.setVisible(false);
            gridRegistrar1.setLayoutY(comboTipoUsuario.getLayoutY()+150);
            gridRegistrar2.setLayoutY(comboTipoUsuario.getLayoutY()+82);
            botonCancelar.setVisible(true);
            botonGuardarUsuario.setVisible(true);            
        }
    }
    
     @Override
    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
     }
    
    public void setVentanaPrincipal(Sabga principal) {
        this.paginaPrincipal = principal;
    }
    
    private void inicio(){
        
        comboTipoUsuario.setItems(consulta.llenarLista(6)); 
        comboGrado.setItems(consulta.llenarLista(7));
        comboCurso.setItems(consulta.llenarLista(8));
        comboJornada.setItems(consulta.llenarLista(9));
        gridRegistrar1.setVisible(false);
        gridRegistrar2.setVisible(false);
        botonCancelar.setVisible(false);
        botonGuardarUsuario.setVisible(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            inicio(); 
        }         
    }
        

