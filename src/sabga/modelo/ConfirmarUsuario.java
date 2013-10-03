
package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarUsuario extends ValidarUsuario{

    private String nombreUsuario, documento, correo;
    
     public boolean confirmarJornada(String jornada) {
        return validarCampoTexto(jornada, 32);
    }
    
      public boolean confirmarCurso(String curso) {
        return validarCampoTexto(curso, 32);
    }
      
    public boolean confirmarGrado(String grado) {
        return validarCampoTexto(grado, 32);      
    }
    
    public boolean confirmarTipoUsuario(String tipoUsuario) {
        return validarCampoTexto(tipoUsuario, 32);      
    }
    
    public boolean confirmarUsuario(String nombre, String contrasenia){
        return validarCampoTexto(nombre, 20) && validarCampoTexto(contrasenia, 20);
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
