package sabga.modelo;

/**
 * @author Elk1n
 */

public class ValidarUsuario extends Validacion {

    private String errorJornada, errorGrado, errorCurso, errorNuevoTipoUsuario, errorCorreo, errorDocumento, errorNombre, errorApellido,
                   errorTelefono, errorDireccion, errorTipo, errorUsuario,  errorContrasenia, errorNombreUsuario;

    public ValidarUsuario() {
    
    }
    //
    public void validarNuevaJornada(String jornada) {
        if (!validarCampoTexto(jornada, 32)) {
            this.errorJornada = getMensajeError();
        }
    }
    //
    public void validarNuevoCurso(String curso) {
        if (!validarCampoTexto(curso, 32)) {
            this.errorCurso = getMensajeError();
        }
    }
    // 
    public void validarNuevoGrado(String grado) {
        if (!validarCampoTexto(grado, 32)) {
            this.errorGrado = getMensajeError();
        }
    }
    //
    public void validarNuevoTipoUsuario(String usuario) {
        if (!validarCampoTexto(usuario, 32)) {
            this.errorNuevoTipoUsuario = getMensajeError();
        }
    }
    //
    public void validarUsuario(String usuario, String contrasenia) {
        if (!validarCampoTexto(usuario, 20)) {
            this.errorNombreUsuario = getMensajeError();
        }
        if (!validarCampoTexto(contrasenia, 20)) {
            this.errorContrasenia = getMensajeError();
        }
    }
    //
    public void validarNuevoEstudiante(String nombre, String apellido, String correo, Object grado, Object curso, Object jornada,
                                       String documento, String telefono, String direccion) {

        if (!validarCampoTexto(nombre, 90)) {
            this.errorNombre = getMensajeError();
        }
        if (!validarCampoTexto(apellido, 90)) {
            this.errorApellido = getMensajeError();
        }
        if (!validarCorreo(correo, 90)) {
            this.errorCorreo = getMensajeError();
        }
        if (grado == null) {
            this.errorGrado = "Debe seleccionar una opción.";
        }
        if (curso == null) {
            this.errorCurso = "Debe seleccionar una opción.";
        }
        if (jornada == null) {
            this.errorJornada = "Debe seleccionar una opción.";
        }
        if (!validarNumero(documento, 15)) {
            this.errorDocumento= getMensajeError();
        }
        if (!validarNumeroNull(telefono, 15)) {
            this.errorTelefono = getMensajeError();
        }
        if (!validarCampoTextoNull(direccion, 45)) {
            this.errorDireccion= getMensajeError();
        }
    }   
    //
    public void validarNuevoFuncionario(String nombre, String apellido, String correo, String documento, String telefono, String direccion){
          
        if (!validarCampoTexto(nombre, 90)) {
            this.errorNombre = getMensajeError();
        }
        if (!validarCampoTexto(apellido, 90)) {
            this.errorApellido = getMensajeError();
        }
        if (!validarCorreo(correo, 90)) {
            this.errorCorreo = getMensajeError();
        }
        if (!validarNumero(documento, 15)) {
            this.errorDocumento = getMensajeError();
        }
        if (!validarNumeroNull(telefono, 15)) {
            this.errorTelefono = getMensajeError();
        }
        if (!validarCampoTextoNull(direccion, 45)) {
            this.errorDireccion = getMensajeError();
        }
    }
    //
    public void validarNuevoBibliotecario(Object tipo, String nombre, String apellido, String usuario, String contrasenia, String confirmar,
                                          String correo, String documento, String telefono){
        
        if(tipo == null){
            this.errorTipo = "Debe seleccionar una opción.";
        }
        if(!validarCampoTexto(nombre, 90)){
            this.errorNombre = getMensajeError();
        }
        if(!validarCampoTexto(apellido, 90)){
            this.errorApellido = getMensajeError();
        }
        if(!validarCampoTexto(usuario, 20)){
            this.errorUsuario = getMensajeError();
        }
        if(!validarNuevaContrasenia(contrasenia, confirmar, 20)){
            this.errorContrasenia = getMensajeError();
        }        
        if(!validarCampoTexto(correo, 90)){
            this.errorCorreo = getMensajeError();
        }
        if(!validarNumero(documento, 15)){
            this.errorDocumento = getMensajeError();
        }
        if(!validarNumeroNull(telefono, 15)){
            this.errorTelefono = getMensajeError();
        }
    }
    // 
    public void validarRecuperarContrasenia(String documento, String correo) {

        if (!validarNumero(documento, 15)) {
            this.errorDocumento = getMensajeError();
        }
        if (!validarCorreo(correo, 90)) {
            this.errorCorreo = getMensajeError();
        }
    }

    //
    public String getErrorJornada() {
        return this.errorJornada;
    }
    //
    public String getErrorCurso() {
        return this.errorCurso;
    }
    //
    public String getErrorGrado() {
        return this.errorGrado;
    }
    //
    public String getErrorTipoUsuario() {
        return this.errorNuevoTipoUsuario;
    }
    //
    public String getErrorCorreo() {
        return this.errorCorreo;
    }
    //
    public String getErrorDocumento() {
        return this.errorDocumento;
    }
    //
    public String getErrorNombreUsuario() {
        return this.errorNombreUsuario;
    }
    //
    public String getErrorNombre(){
        return this.errorNombre;
    }
    //
    public String getErrorApellido(){
        return this.errorApellido;
    }
    //
    public String getErrorDireccion(){
        return this.errorDireccion;
    }
    //
    public String getErrorTelefono(){
        return this.errorTelefono;
    }
    //
    public String getErrorUsuario() {
        return this.errorUsuario;
    }
    //
    public String getErrorContrasenia() {
        return this.errorContrasenia;
    }
    //
    public String getErrorTipoAdmin(){
        return this.errorTipo;
    }
}
