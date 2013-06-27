

package sabga.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Elk1n
 * 
 */

public class ValidarUsuario {
    
    private String  mensajeError, nombreUsuario, apellidosUsuario, correoUsuario, documentoUsuario, telefonoUsuario,  direccionUsuario, usuario, contrasenia, multa,
            
                    errorNombreUsuario, errorApellidosUsuario, errorCursoUsuario, errorGrupoUsuario, errorCorreoUsuario, errorDocumentoUsuario,
                    errorJornadaUsuario, errorTelefonoUsuario, errorDireccionUsuario, errorEstadoUsuario, errorContrasenia, errorUsuario, errorMulta;

    private Object tipoUsuario, cursoUsuario, grupoUsuario, jornadaUsuario, estadoUsuario;
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String correoUsuario, Object cursoUsuario, Object grupoUsuario, 
                          Object jornadaUsuario, String documentoUsuario, String telefonoUsuario, String direccionUsuario){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.correoUsuario = correoUsuario;
        this.cursoUsuario = cursoUsuario;
        this.grupoUsuario = grupoUsuario;
        this.jornadaUsuario = jornadaUsuario;
        this.documentoUsuario = documentoUsuario;       
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
         
    }
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String correoUsuario, String documentoUsuario, String telefonoUsuario,
                          String direccionUsuario){
                
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
  
    }
    
    public ValidarUsuario (){
        
    }
    
    public ValidarUsuario(String nombreUsuario, String apellidosUsuario, String documentoUsuario, String correoUsuario, String telefonoUsuario,
                          String direccionUsuario, String multa){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.direccionUsuario = direccionUsuario;
        this.multa = multa;
        
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
    
    public void validarNuevoEmpleado(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = getMensajeError();
          
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = getMensajeError();
            
        }
        
         if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = getMensajeError();           
            
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = getMensajeError();
            
        }
        
        if (validarNumero(this.telefonoUsuario, 12) == false){
            this.errorTelefonoUsuario = getMensajeError();            
    
        }
        
        if (validarCampoTextoNull(this.direccionUsuario, 45) == false){
            this.errorDireccionUsuario = getMensajeError();        
      
        }
        
       
    }
    
    public void validarNuevoUsuario(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = getMensajeError();
            
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = getMensajeError();
           
        }
               
        if (validarCorreo(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = getMensajeError();           
          
        }
        
        if (this.cursoUsuario == null){
            this.errorCursoUsuario = "Debe seleccionar una opción";
    
        }
         
        if (this.grupoUsuario == null){
            this.errorGrupoUsuario = "Debe seleccionar una opción";
         
        }
        
        if (this.jornadaUsuario == null){
            this.errorJornadaUsuario = "Debe seleccionar una opción";            
     
        }
        
        if (validarNumero(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = getMensajeError();
        
        }
        
               
        if (validarNumero(this.telefonoUsuario, 12) == false){
            this.errorTelefonoUsuario = getMensajeError();            
    
        }
        
        if (validarCampoTexto(this.direccionUsuario, 45) == false){
            this.errorDireccionUsuario = getMensajeError();        
      
        }
     
       
    }
    
    public void  validarACUsuario(){
        
        if (validarCampoTexto(this.nombreUsuario, 45) == false){
           this.errorNombreUsuario = getMensajeError();
         
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
           this.errorApellidosUsuario = getMensajeError();
           
        }
        
         if (validarNumeroNull(this.documentoUsuario, 15) == false){
            this.errorDocumentoUsuario = getMensajeError();
        
        }
               
        if (validarCorreoNull(this.correoUsuario, 40) == false){
            this.errorCorreoUsuario = getMensajeError();           
      
        }
                              
        if (validarNumero(this.telefonoUsuario, 12) == false){
            this.errorTelefonoUsuario = getMensajeError();            
    
        }
        
        if (validarCampoTexto(this.direccionUsuario, 45) == false){
            this.errorDireccionUsuario = getMensajeError();        
      
        }        
           
        if (validarNumero(this.multa, 8) == false){
            
            this.errorMulta = getMensajeError();
        }
    }
     
    public boolean validarCampoTexto(String campoTexto, int numeroCaracteres){
                   
        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty()){
            
            this.mensajeError = "Debe rellenar este campo"; 
            return false;        
        }
        
        else if ( campoTexto.length() > numeroCaracteres){
            
            this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
            return false;
        }
        
        else if(campoTexto.trim().equals("")){
            
            this.mensajeError = "No unicamente espacios en blanco";
            return false;
        }
        
        else {
            
            this.mensajeError = "";
            return true;          
        }
          
        
    }
    
    public boolean validarCampoTextoNull(String campoTexto, int numeroCaracteres){
        
        if ( campoTexto.isEmpty()==false){
            
            if (campoTexto.length()>numeroCaracteres){
                
                this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
                return false;        
            }
            
            else if(campoTexto.trim().equals("")){
            
                this.mensajeError = "No unicamente espacios en blanco";
                return false;
            }
               
            else {
            
                this.mensajeError = "";
                return true;
            }            
        }
       
        else {
            
            this.mensajeError = "";
            return true;
        }
    }
        
    public boolean validarCorreo(String correoElectronico, int numeroCaracteres){
        
        Pattern patron = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");
        Matcher matcher = patron.matcher(correoElectronico);
        
        if (correoElectronico == null || correoElectronico.equals("") || correoElectronico.isEmpty()){
            
            this.mensajeError = "Debe rellenar este campo";
            return false;            
        }
        
        else if(correoElectronico.length()>numeroCaracteres){
            
            this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
            return false;            
        }
        
        else if(!matcher.matches()){
            
            this.mensajeError = "No es un E-mail";
            return false;            
        }
        
        else{
            
            this.mensajeError = "";
            return true;
        }   
                
    }
    
    public boolean validarCorreoNull(String correoElectronico, int numeroCaracteres){
        
        Pattern patron = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");
        Matcher matcher = patron.matcher(correoElectronico);
        
        if ( correoElectronico.isEmpty()==false){
            
            if(correoElectronico.length()>numeroCaracteres){
            
            this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
            return false;            
            
            }
            
            else if(!matcher.matches()){
            
            this.mensajeError = "No es un E-mail";
            return false;            
        
            }
            
            else {
                this.mensajeError = "";
                return true;
            }                      
        }
       
        else {
            
            this.mensajeError = "";
            return true;
        }
        
    }
    
    public boolean validarNumero(String campoTexto, int numeroCaracteres){
        
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(campoTexto);
        
        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty()){
            
            this.mensajeError = "Debe rellenar este campo";
            return false;
        }
        else if(campoTexto.length()>numeroCaracteres){
            
            this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
            return false;
        }
        
        else if(!matcher.matches()){
            
            this.mensajeError = "No es un número";
            return false;
            
        }else{
            this.mensajeError = "";
            return true;
        }           
        
    }
    
    public boolean validarNumeroNull(String campoTexto, int numeroCaracteres){
        
         Pattern patron = Pattern.compile("[0-9]+");
         Matcher matcher = patron.matcher(campoTexto);
         
         if (campoTexto.isEmpty()==false){
        
                if(campoTexto.length()>numeroCaracteres){
            
                    this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
                    return false;
            
                }
             
                else if(!matcher.matches()){
            
                    this.mensajeError = "No es un número";
                    return false;
            
                }
                else{
                    this.mensajeError = "";
                    return true;       
                }
         }
         else {
            
            this.mensajeError = "";
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
    
    public String getErrorMulta(){
        return this.errorMulta;
    }
    
    public String getMensajeError(){
        return this.mensajeError;
    }
 }
