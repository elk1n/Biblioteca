
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class CuentaController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    @FXML
    private TextField txtfDocumento, txtfNombre, txtfApellido, txtfCorreo, txtfTelefono, txtfUsuario;
    @FXML
    private PasswordField passContrasenia, passNuevaContra, passConfirmacion;
    @FXML
    private Label lblDocumento, lblNombre, lblApellido, lblCorreo, lblTelefono, lblUsuario, lblContrasenia, lblNuevaContrasenia;
    private final Atributos atributos;
    private final Consultas consultar;
    
    public CuentaController (){
        atributos = new Atributos();
        consultar = new Consultas();
    }
    
    @FXML
    public void guardarCambios(){
        editarDatos();
    }
    
    @FXML
    public void cambiarContrasenia(ActionEvent evento){
        cambiarContrasenia();   
    }
    private void editarDatos(){
        mensajes();
        ConfirmarUsuario usuario = new ConfirmarUsuario();
        if(usuario.editarBibliotecario(txtfDocumento.getText(), txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                       txtfTelefono.getText(), txtfUsuario.getText())){
            consultar.editarBibliotecario(2, consultar.getDocumento(), 0, null, txtfNombre.getText(), txtfApellido.getText(),
                                           txtfTelefono.getText(), txtfCorreo.getText(), txtfUsuario.getText() ,txtfDocumento.getText());
             if (consultar.getMensaje() == null) {
                 mapearDatos();
                Utilidades.mensaje(null, "La información se ha actualizado correctamente.", "", "Editar Datos Bibliotecario");
            } else {
                Utilidades.mensajeError(null, consultar.getMensaje(), "La información no ha sido actualizada.", "Error Edición");
            }
        }
    }
    
    private void cambiarContrasenia(){
         mensajesContrasenia();
        ConfirmarUsuario usuario = new ConfirmarUsuario();
        if (usuario.cambioContrasenia(passContrasenia.getText(), passNuevaContra.getText(), passConfirmacion.getText())) {
            consultar.cambiarContrasenia(consultar.getDocumento(), Utilidades.encriptar(passContrasenia.getText()),
            Utilidades.encriptar(passNuevaContra.getText()));
            if (consultar.getMensaje() == null) {
                limpiarCampos();
                Utilidades.mensaje(null, "La contraseña se modifico correctamente.", "", "Editar Contraseña");
            } else {
                limpiarCampos();
                Utilidades.mensajeError(null, consultar.getMensaje(), "La contraseña no se ha modificado.", "Error Cambio Contraseña");
            }
        }
    }
    
    private void mensajesContrasenia(){
        ValidarUsuario usuario = new ValidarUsuario();
            usuario.validarCambioContrasenia(passContrasenia.getText(), passNuevaContra.getText(), passConfirmacion.getText());
            lblContrasenia.setText(usuario.getErrorContrasenia());
            lblNuevaContrasenia.setText(usuario.getErrorNuevaContrasenia());  
    }
    
    private void mapearDatos(){
        
        consultar.mapearBibliotecario(atributos.getUsuarioAdmin());
        txtfDocumento.setText(consultar.getDocumento());
        txtfNombre.setText(consultar.getNombre());
        txtfApellido.setText(consultar.getApellido());
        txtfCorreo.setText(consultar.getCorreo());
        txtfTelefono.setText(consultar.getTelefono());
        txtfUsuario.setText(consultar.getUsuario());
    }
    
    private void mensajes(){
        ValidarUsuario usuario = new ValidarUsuario();
        usuario.validarEditarBibliotecario(txtfDocumento.getText(), txtfNombre.getText(), txtfApellido.getText(), txtfCorreo.getText(),
                                           txtfTelefono.getText(), txtfUsuario.getText());
        lblDocumento.setText(usuario.getErrorDocumento());
        lblNombre.setText(usuario.getErrorNombre());
        lblApellido.setText(usuario.getErrorApellido());
        lblCorreo.setText(usuario.getErrorCorreo());
        lblTelefono.setText(usuario.getErrorTelefono());
        lblUsuario.setText(usuario.getErrorUsuario());        
    }
    
    public void limpiarCampos(){
        passContrasenia.clear();
        passNuevaContra.clear();
        passConfirmacion.clear();
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {       
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapearDatos();
    }    

    
    
}
