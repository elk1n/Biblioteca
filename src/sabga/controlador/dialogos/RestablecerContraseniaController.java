
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

public class RestablecerContraseniaController implements Initializable {
    
    private Sabga ventanaPrincipal; 

    @FXML 
    private Label lblValidarDocumento, lblValidarCorreo, lblValidacion;    
    @FXML 
    private TextField txtfDocumento, txtfCorreo;
    @FXML 
    private Pane panelAyuda;
    @FXML
    private ToggleButton btnAyuda;    
    private String usuario = null, clave;
    private final Consultas consulta;
    public RestablecerContraseniaController(){
        
        consulta = new Consultas();
    }
           
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {       
	this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    public void nuevaContrasenia(ActionEvent evento){        
         verificarUsuario();
    }
    
    private void verificarUsuario(){
        
        mensajesError();
        ConfirmarUsuario confirmar = new ConfirmarUsuario();
        if(confirmar.confirmarRecuperarContrasenia(txtfDocumento.getText(), txtfCorreo.getText())){           
            cambiarContrasenia();
        }
    }
    
    private void cambiarContrasenia(){
       
        usuario = consulta.validarUsuario(txtfDocumento.getText().trim(), txtfCorreo.getText().trim());
        if (usuario != null) {
            guardarContrasenia();
        } else {
            lblValidacion.setText("No se ha encontrado el documento o correo del usuario.");
        }    
    }
    
    private void guardarContrasenia(){
          
        clave = Utilidades.claveAleatoria();
        String asunto = "Nueva Contraseña";
        String mensaje= "Usted a solicitado una nueva clave para el ingreso al sistema SABGA. \n"+
                        "Usuario: "+ usuario+" \n"+
                        "Contraseña: "+clave+" \n\n"+
                        "Despues de ingresar, si lo desea puede cambiar la contraseña por una que pueda recordar fácilmente en el "+
                        "botón Menú opción Mi Cuenta.";
             
        if (Utilidades.enviarCorreo(txtfCorreo.getText().trim(), asunto, mensaje)) {
            consulta.guardarContrasenia(txtfDocumento.getText().trim(), txtfCorreo.getText().trim(), clave);
            Utilidades.mensaje(null, "La nueva clave se ha generado correctamente", "La contraseña se ha enviado a su correo", "Cotraseña Generada");
            cancelar(null);
        } else {
            lblValidacion.setText("No se ha realizado el cambio de contraseña, favor intente nuevamente");
        }
    }
              
    private void mensajesError(){
        
         ValidarUsuario validarCampos = new ValidarUsuario();
         validarCampos.validarRecuperarContrasenia(txtfDocumento.getText(), txtfCorreo.getText());
         lblValidarDocumento.setText(validarCampos.getErrorDocumento());
         lblValidarCorreo.setText(validarCampos.getErrorCorreo());      
    }
    
    @FXML
    public void cancelar(ActionEvent evento){        
        this.ventanaPrincipal.ocultarDialogo();
        this.ventanaPrincipal.dialogoInicioSesion();       
    }
    
    @FXML
    public void ayuda(ActionEvent evento){    
        
        if(btnAyuda.isSelected()){         
            panelAyuda.setVisible(true);         
        }else{
            panelAyuda.setVisible(false);
        }         
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }    
}
