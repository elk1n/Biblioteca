package sabga.modelo;

/**
 * @author Elk1n
 */
public class ValidarUsuario extends Validacion {

    private String errorJornada, errorGrado, errorCurso, errorNuevoTipoUsuario, errorCorreo, errorDocumento, errorNombre, errorApellido,
                   errorTelefono, errorDireccion,
            mensajeError,
            
            nombreUsuario, apellidosUsuario, correoUsuario, documentoUsuario, telefonoUsuario, direccionUsuario, usuario,
            contrasenia, nuevaContrasenia, confirmacion, multa,
            errorNombreUsuario, errorApellidosUsuario, errorCursoUsuario, errorGrupoUsuario, errorCorreoUsuario, errorDocumentoUsuario,
            errorJornadaUsuario, errorTelefonoUsuario, errorDireccionUsuario, errorEstadoUsuario, errorContrasenia, errorUsuario, errorMulta,
            errorTipoAdmin, errorConfirmacion, errorNuevaContrasenia;

    private Object tipoAdmin, cursoUsuario, grupoUsuario, jornadaUsuario, estado;

    public ValidarUsuario() {
    }
    
    //      --  CONSTRUCTOR PARA EL ADMINISTRADOR       --
    public ValidarUsuario(Object tipoAdmin, String nombreUsuario, String apellidosUsuario, String usuario, String contrasenia,
            String confirmacion, String correoUsuario, String documentoUsuario, String telefonoUsuario) {

        this.tipoAdmin = tipoAdmin;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.confirmacion = confirmacion;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;

    }

    //     --   CONSTRUCTOR PARA EDITAR DATOS DEL AMINISTRADOR      --
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String usuario, String correoUsuario, String documentoUsuario,
            String telefonoUsuario, Object estado) {

        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuario = usuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estado = estado;

    }

    //      --  CONSTRUCTOR PARA VALIDAR LA NUEVA CONTRASENIA       --
    public ValidarUsuario(String contrasenia, String nuevaContrasenia, String confirmacion) {

        this.contrasenia = contrasenia;
        this.nuevaContrasenia = nuevaContrasenia;
        this.confirmacion = confirmacion;
    }

    public ValidarUsuario(String contrasenia, String confirmacion) {

        this.nuevaContrasenia = contrasenia;
        this.confirmacion = confirmacion;

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
   
    public void editarEstudiante(){
    
        
    
    }
    
    public void validarContrasenia() {
        if (!validarContrasenia(this.nuevaContrasenia, this.confirmacion, 20)) {
            this.errorNuevaContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
        }

    }

    public void validarRecuperarContrasenia(String documento, String correo) {

        if (!validarNumero(documento, 15)) {
            this.errorDocumento = getMensajeError();
        }
        if (!validarCorreo(correo, 90)) {
            this.errorCorreo = getMensajeError();
        }

    }

    public void validarNuevaContrasenia() {

        if (validarCampoTexto(this.contrasenia, 20) == false) {

            this.errorContrasenia = getMensajeError();
        }

        if (validarContrasenia(this.nuevaContrasenia, this.confirmacion, 20) == false) {

            this.errorNuevaContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
        }
    }

    public void validarEdicionAdmin() {

        if (validarCampoTexto(this.nombreUsuario, 45) == false) {
            this.errorNombreUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.apellidosUsuario, 45) == false) {
            this.errorApellidosUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.usuario, 20) == false) {
            this.errorUsuario = getMensajeError();

        }

        if (validarCorreo(this.correoUsuario, 40) == false) {
            this.errorCorreoUsuario = getMensajeError();

        }

        if (validarNumero(this.documentoUsuario, 15) == false) {
            this.errorDocumentoUsuario = getMensajeError();

        }

        if (validarNumero(this.telefonoUsuario, 12) == false) {
            this.errorTelefonoUsuario = getMensajeError();
        }
    }

    public void validarAdminAxiliar() {

        if (this.tipoAdmin == null) {
            this.errorTipoAdmin = "Debe seleccionar una opción";
        }

        if (validarCampoTexto(this.nombreUsuario, 45) == false) {
            this.errorNombreUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.apellidosUsuario, 45) == false) {
            this.errorApellidosUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.usuario, 20) == false) {
            this.errorUsuario = getMensajeError();

        }

        if (validarContrasenia(this.contrasenia, this.confirmacion, 20) == false) {
            this.errorContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
        }

        if (validarCorreo(this.correoUsuario, 40) == false) {
            this.errorCorreoUsuario = getMensajeError();

        }

        if (validarNumero(this.documentoUsuario, 15) == false) {
            this.errorDocumentoUsuario = getMensajeError();

        }

        if (validarNumero(this.telefonoUsuario, 12) == false) {
            this.errorTelefonoUsuario = getMensajeError();
        }

    }

    public void validarACUsuario() {

        if (validarCampoTexto(this.nombreUsuario, 45) == false) {
            this.errorNombreUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.apellidosUsuario, 45) == false) {
            this.errorApellidosUsuario = getMensajeError();

        }

        if (validarNumeroNull(this.documentoUsuario, 15) == false) {
            this.errorDocumentoUsuario = getMensajeError();

        }

        if (validarCorreoNull(this.correoUsuario, 40) == false) {
            this.errorCorreoUsuario = getMensajeError();

        }

        if (validarNumero(this.telefonoUsuario, 12) == false) {
            this.errorTelefonoUsuario = getMensajeError();

        }

        if (validarCampoTexto(this.direccionUsuario, 45) == false) {
            this.errorDireccionUsuario = getMensajeError();

        }

        if (validarNumero(this.multa, 8) == false) {

            this.errorMulta = getMensajeError();
        }
    }

    public boolean validarContrasenia(String campoTexto, String confirmacion, int numeroCaracteres) {

        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty() || confirmacion == null || confirmacion.equals("") || confirmacion.isEmpty()) {

            this.mensajeError = "Debe rellenar este campo";
            return false;
        } else if (!campoTexto.equals(confirmacion)) {

            this.mensajeError = "Las contraseñas no coinciden";
            return false;
        } else if (campoTexto.length() > numeroCaracteres || confirmacion.length() > numeroCaracteres) {

            this.mensajeError = "Máximo " + numeroCaracteres + " caracteres";
            return false;
        } else if (campoTexto.trim().equals("") || confirmacion.trim().equals("")) {

            this.mensajeError = "No deben ser sólo espacios en blanco";
            return false;
        } else {

            this.mensajeError = "";
            return true;
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
    
    public String getErrorApellidosUsuario() {
        return this.errorApellidosUsuario;
    }

    public String getErrorCursoUsuario() {
        return this.errorCursoUsuario;
    }

    public String getErrorGrupoUsuario() {
        return this.errorGrupoUsuario;
    }

    public String getErrorCorreoUsuario() {
        return this.errorCorreoUsuario;
    }

    public String getErrorDocumentoUsuario() {
        return this.errorDocumentoUsuario;
    }

    public String getErrorJornadaUsuario() {
        return this.errorJornadaUsuario;
    }

    public String getErrorTelefonoUsuario() {
        return this.errorTelefonoUsuario;
    }

    public String getErrorDireccionUsuario() {
        return this.errorDireccionUsuario;
    }

    public String getErrorEstadoUsuario() {
        return this.errorEstadoUsuario;
    }

    public String getErrorUsuario() {
        return this.errorUsuario;
    }
    //
    public String getErrorContrasenia() {
        return this.errorContrasenia;
    }

    public String getErrorConfirmacion() {
        return this.errorConfirmacion;
    }

    public String getErrorNuevaContrasenia() {
        return this.errorNuevaContrasenia;
    }

    public String getErrorMulta() {
        return this.errorMulta;
    }

    public String getErrorTipoAdmin() {
        return this.errorTipoAdmin;
    }

}
