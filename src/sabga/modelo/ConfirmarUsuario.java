package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarUsuario extends ValidarUsuario {

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

    public boolean confirmarUsuario(String nombre, String contrasenia) {
        return validarCampoTexto(nombre, 20) && validarCampoTexto(contrasenia, 20);
    }

    public boolean confirmarRecuperarContrasenia(String documeto, String correo) {
        return validarNumero(documeto, 15) && validarCorreo(correo, 90);
    }

    public boolean nuevoEstudiante(String nombre, String apellido, String correo, Object grado, Object curso, 
                                   Object jornada, String documento, String telefono, String direccion){
        
        if (!validarCampoTexto(nombre, 90)) {
            return false;
        }
        else if (!validarCampoTexto(apellido, 90)) {
           return false;
        }
        else if (!validarCorreo(correo, 90)) {
            return false;
        }
        else if (grado == null) {
            return false;
        }
        else if (curso == null) {
            return false;
        }
        else if (jornada == null) {
            return false;
        }
        else if (!validarNumero(documento, 15)) {
            return false;
        }
        else if (!validarNumeroNull(telefono, 15)) {
            return false;
        }
        else if (!validarCampoTextoNull(direccion, 45)) {
            return false;
        }
        else{
            return true;
        }    
    }
    
    public boolean  nuevoFuncionario(String nombre, String apellido, String correo, String documento, String telefono, String direccion){
            
        if (!validarCampoTexto(nombre, 90)) {
            return false;
        }
        else if (!validarCampoTexto(apellido, 90)) {
                return false;
        }
        else if (!validarCorreo(correo, 90)) {
               return false;
        }
        else if (!validarNumero(documento, 15)) {
                return false;
        }
        else if (!validarNumeroNull(telefono, 15)) {
              return false;
        }
        else if (!validarCampoTextoNull(direccion, 45)) {
               return false;
        }
        else{
            return true;
        }  
    }

    public boolean  nuevoBibliotecario(Object tipo, String nombre, String apellido, String usuario, String contrasenia, String confirmar,
                                          String correo, String documento, String telefono){
        
        if(tipo == null){
            return false;
        }
        else if(!validarCampoTexto(nombre, 90)){
            return false;
        }
        else if(!validarCampoTexto(apellido, 90)){
            return false;
        }
        else if(!validarCampoTexto(usuario, 20)){
           return false;
        }
        else if(!validarNuevaContrasenia(contrasenia, confirmar, 20)){
            return false;
        }        
        else if(!validarCampoTexto(correo, 90)){
            return false;
        }
        else if(!validarNumero(documento, 15)){
            return false;
        }
        else if(!validarNumeroNull(telefono, 15)){
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean editarBibliotecario(String documento, String nombre, String apellido, String correo, String telefono, String usuario){
    
        if(!validarNumero(documento, 15)){
           return false;
        }
        else if(!validarCampoTexto(nombre, 90)){
            return false;
        }
        else if(!validarCampoTexto(apellido, 90)){
            return false;
        }
        else if(!validarCampoTexto(correo, 90)){
           return false;
        }
        else if(!validarNumeroNull(telefono, 15)){
            return false;
        } 
        else if(!validarCampoTexto(usuario, 20)){
            return false;
        }
        else {
            return true;
        }
    }

    public boolean cambioContrasenia(String contrasenia, String nueva, String confirmacion){
        
        if(!validarCampoTexto(contrasenia, 20)){
            return false;
        }
        else if(!validarNuevaContrasenia(nueva, confirmacion, 20)){
            return false;
        }
        else{
            return true;
        }
    }
    
    public boolean confirmarPagoMulta(String valor) {
        return validarNumero(valor, 10);
    }
}
