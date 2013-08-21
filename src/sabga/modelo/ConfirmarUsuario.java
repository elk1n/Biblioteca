
package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarUsuario extends ValidarUsuario{

    private String nombreUsuario, contrasenia, documento, correo;
    
     public boolean confirmarJornada(String jornada) {

        if (!validarCampoTexto(jornada, 32)) {
            return false;
        } else {
            return true;
        }
    }
    
      public boolean confirmarCurso(String curso) {

        if (!validarCampoTexto(curso, 32)) {
            return false;
        } else {
            return true;
        }
    }
      
    public boolean confirmarGrado(String grado) {

        if (!validarCampoTexto(grado, 32)) {
            return false;
        } else {
            return true;
        }      
    }
    
    public boolean confirmarTipoUsuario(String tipoUsuario) {

        if (!validarCampoTexto(tipoUsuario, 32)) {
            return false;
        } else {
            return true;
        }      
    }
    
    public boolean confirmarUsuario(String nombre, String contrasenia){
        
        this.nombreUsuario = nombre;
        this.contrasenia = contrasenia;
        
        if (validarCampoTexto(this.nombreUsuario, 20) == false){
            return false;
        }
        
        if (validarCampoTexto(this.contrasenia, 20) == false){
            return false;
        }
        
        else {
            return true;
        }
    }

    public boolean confirmarDatosUsuario(String usuario, String documeto, String correo){
        
        this.nombreUsuario = usuario;
        this.documento = documeto;
        this.correo = correo;
        
        if (validarCampoTexto(this.nombreUsuario, 20) == false){
            return false;
        }
        
        if (validarNumero(this.documento, 12) == false){
            return false;
        }
        
        if (validarCorreo(this.correo, 45) == false){
            return false;
        }
        
        else {
            return true;
        }
        
    }
}
