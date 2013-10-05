
package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarUsuario extends ValidarUsuario{
    
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

    public boolean confirmarRecuperarContrasenia(String documeto, String correo){
        return validarNumero(documeto, 15) && validarCorreo(correo, 90);
    }
    
}
