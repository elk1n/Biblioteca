

package sabga.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Elk1n
 * 
 */

public class ValidarUsuario {
    
    private String nombreUsuario, apellidosUsuario, correoUsuario, documentoUsuario, telefonoUsuario,  direccionUsuario, usuario, contrasenia,
            
                    errorNombreUsuario, errorApellidosUsuario, errorCursoUsuario, errorGrupoUsuario, errorCorreoUsuario, errorDocumentoUsuario,
                    errorJornadaUsuario, errorTelefonoUsuario, errorDireccionUsuario, errorEstadoUsuario, errorContrasenia, errorUsuario;

    private Object tipoUsuario, cursoUsuario, grupoUsuario, jornadaUsuario, estadoUsuario;
    
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, Object cursoUsurario, Object grupoUsuario, String correoUsuario,
                          String documentoUsuario, Object jornadaUsuario, String telefonoUsuario, String direccionUsuario, Object estadoUsuario){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.cursoUsuario = cursoUsurario;
        this.grupoUsuario = grupoUsuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.jornadaUsuario = jornadaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.estadoUsuario = estadoUsuario;
         
    }
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String correoUsuario, String documentoUsuario, Object estadoUsuario){
                
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.estadoUsuario = estadoUsuario;    
    }
    
    public ValidarUsuario(){
        
    }
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String usuario, String contrasenia ,Object cursoUsurario, Object grupoUsuario, String correoUsuario,
                          String documentoUsuario, Object jornadaUsuario, String telefonoUsuario, String direccionUsuario, Object estadoUsuario){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.cursoUsuario = cursoUsurario;
        this.grupoUsuario = grupoUsuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.jornadaUsuario = jornadaUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.estadoUsuario = estadoUsuario;
         
    }
    
    public ValidarUsuario (String nombreUsuario, String apellidosUsuario, String usuario, String contrasenia , String correoUsuario,
                          String documentoUsuario, Object estadoUsuario){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.estadoUsuario = estadoUsuario;
    }
    
    public boolean validarAdminAxiliar(){
        
         if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = "Debe rellenar este campo";
            
            return false;
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.usuario, 20) == false){
            this.errorUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.contrasenia, 20) == false){
            this.errorContrasenia = "Debe rellenar este campo";
        }
        
        if (validarCampoTexto(this.cursoUsuario.toString(), 2) == false){
            this.errorCursoUsuario = "Debe seleccionar una opción";
            return false;
        }
         
        if (validarCampoTexto(this.grupoUsuario.toString(), 2) == false){
            this.errorGrupoUsuario = "Debe seleccionar una opción";
            return false;
        }
        
        if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = "Debe rellenar este campo";           
            return false;
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.jornadaUsuario.toString(), 16) == false){
            this.errorJornadaUsuario = "Debe seleccionar una opción";            
            return false;
        }
        
        if (validarNumero(this.telefonoUsuario, 12) == false){
            this.errorTelefonoUsuario = "Debe rellenar este campo";            
            return false;
        }
        
        if (validarCampoTexto(this.direccionUsuario, 45) == false){
            this.errorDireccionUsuario = "Debe rellenar este campo";        
            return false;
        }
        
        if (validarCampoTexto(this.estadoUsuario.toString(), 16) == false){
            this.errorEstadoUsuario = "Debe seleccionar una opción";
            return false;                   
        }
        
        return true;
        
        
    }
    
    public boolean validarAdmin(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = "Debe rellenar este campo";
            
            return false;
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.usuario, 20) == false){
            this.errorUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.contrasenia, 20) == false){
            this.errorContrasenia = "Debe rellenar este campo";
        }
        
        if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = "Debe rellenar este campo";           
            return false;
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = "Debe rellenar este campo";
            return false;
        }
               
        if (validarCampoTexto(this.estadoUsuario.toString(), 16) == false){
            this.errorEstadoUsuario = "Debe seleccionar una opción";
            return false;                   
        }
        
        return true;
        
    }
    
    public boolean validarNuevoEmpleado(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = "Debe rellenar este campo";
            
            return false;
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = "Debe rellenar este campo";
            return false;
        }
        
         if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = "Debe rellenar este campo";           
            return false;
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.estadoUsuario.toString(), 16) == false){
            this.errorEstadoUsuario = "Debe seleccionar una opción";
            return false;                   
        }
        
        return true;
    }
    
    public boolean validarNuevoUsuario(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = "Debe rellenar este campo";
            
            return false;
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.cursoUsuario.toString(), 2) == false){
            this.errorCursoUsuario = "Debe seleccionar una opción";
            return false;
        }
         
        if (validarCampoTexto(this.grupoUsuario.toString(), 2) == false){
            this.errorGrupoUsuario = "Debe seleccionar una opción";
            return false;
        }
        
        if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = "Debe rellenar este campo";           
            return false;
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = "Debe rellenar este campo";
            return false;
        }
        
        if (validarCampoTexto(this.jornadaUsuario.toString(), 16) == false){
            this.errorJornadaUsuario = "Debe seleccionar una opción";            
            return false;
        }
        
        if (validarNumero(this.telefonoUsuario, 12) == false){
            this.errorTelefonoUsuario = "Debe rellenar este campo";            
            return false;
        }
        
        if (validarCampoTexto(this.direccionUsuario, 45) == false){
            this.errorDireccionUsuario = "Debe rellenar este campo";        
            return false;
        }
        
        if (validarCampoTexto(this.estadoUsuario.toString(), 16) == false){
            this.errorEstadoUsuario = "Debe seleccionar una opción";
            return false;                   
        }
        
        return true;
    }
    
    public boolean validarCampoTexto(String campoTexto, int numeroCaracteres ){
        
        if (campoTexto == null || campoTexto.equals("") || campoTexto.length()>numeroCaracteres || campoTexto.isEmpty()){
            
            return false;
        
        }
        else {
            return true;
        }
               
    }
    
    public boolean validarCampoTextoNO(String campoTexto, int numeroCaracteres){
        
        if (campoTexto!=null || !"".equals(campoTexto)){
            
            if (campoTexto == null || campoTexto.equals("") || campoTexto.length()>numeroCaracteres || campoTexto.isEmpty()){
            
            return false;        
            }
            
            else {
                
            return true;
            }
            
        }
        else {
            return true;
        }
    }
    
    public boolean validarCorreo(String correoElectronico, int numeroCaracteres){
        
        Pattern patron = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");
        Matcher matcher = patron.matcher(correoElectronico);
        
        if(!matcher.matches() || correoElectronico.length()>40){
            
            return false;
            
        }else{
            
            return true;
        }   
                
    }
    
    public boolean validarNumero(String texto, int numeroCaracteres){
        
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(texto);
        
        if(!matcher.matches() || texto.length()>15){
            
            return false;
            
        }else{
            
            return true;
        }           
        
    }
    
    public String getErrorNombreUsuario() {
        return this.errorNombreUsuario;
    }
    
    public String getErrorApellidosUsuario(){
        return this.errorApellidosUsuario;
    }
    
    public String getErrorCursoUsuario(){
        return this.errorCursoUsuario;
    }
    
    public String getErrorGrupoUsuario(){
        return this.errorGrupoUsuario;
    }
    
    public String getErrorCorreoUsuario(){
        return this.errorCorreoUsuario;
    }
    
    public String getErrorDocumentoUsuario(){
        return this.errorDocumentoUsuario;
    }
    
    public String getErrorJornadaUsuario(){
        return this.errorJornadaUsuario;
    }
    
    public String getErrorTelefonoUsuario(){
        return this.errorTelefonoUsuario;
    }
    
    public String getErrorDireccionUsuario(){
        return this.errorDireccionUsuario;
    }
    
    public String getErrorEstadoUsuario(){
        return this.errorEstadoUsuario;
    }
    
    public String getErrorUsuario(){
        return this.errorUsuario;
    }
    
    public String getErrorContrasenia(){
        return this.errorContrasenia;
    }
 }
