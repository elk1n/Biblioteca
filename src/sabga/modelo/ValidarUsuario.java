
package sabga.modelo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Elk1n
 * 
 */

public class ValidarUsuario {
    
    private String  mensajeError, nombreUsuario, apellidosUsuario, correoUsuario, documentoUsuario, telefonoUsuario,  direccionUsuario, usuario,
                    contrasenia, nuevaContrasenia ,confirmacion, multa,
            
                    errorNombreUsuario, errorApellidosUsuario, errorCursoUsuario, errorGrupoUsuario, errorCorreoUsuario, errorDocumentoUsuario,
                    errorJornadaUsuario, errorTelefonoUsuario, errorDireccionUsuario, errorEstadoUsuario, errorContrasenia, errorUsuario, errorMulta,
                    errorTipoAdmin, errorConfirmacion, errorNuevaContrasenia;

    private Object tipoAdmin, cursoUsuario, grupoUsuario, jornadaUsuario, estado;
    
    public ValidarUsuario (){
        
    }
    
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
    
    //      --  CONSTRUCTOR PARA EL ADMINISTRADOR       --
    
    public ValidarUsuario( Object tipoAdmin, String nombreUsuario, String apellidosUsuario, String usuario, String contrasenia,
                           String confirmacion, String correoUsuario, String documentoUsuario, String telefonoUsuario){
        
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
    
    public ValidarUsuario (String nombreUsuario, String apellidosUsuario, String usuario,String correoUsuario, String documentoUsuario, 
                           String telefonoUsuario, Object estado){
        
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.usuario = usuario;
        this.correoUsuario = correoUsuario;
        this.documentoUsuario = documentoUsuario;
        this.telefonoUsuario = telefonoUsuario;
        this.estado = estado;
    
    }
    
    //      --  CONSTRUCTOR PARA VALIDAR LA NUEVA CONTRASENIA       --
    
    public ValidarUsuario (String contrasenia, String nuevaContrasenia, String confirmacion){
        
        this.contrasenia = contrasenia;
        this.nuevaContrasenia = nuevaContrasenia;
        this.confirmacion = confirmacion;
    }
    
    public ValidarUsuario(String contrasenia, String confirmacion){
        
        this.nuevaContrasenia = contrasenia;
        this.confirmacion = confirmacion;
        
    }
    
    public void validarUsuario(String usuario, String contrasenia){
        
        this.nombreUsuario = usuario;
        this.contrasenia = contrasenia;
        
        if (validarCampoTexto(this.nombreUsuario, 20) == false){
            this.errorNombreUsuario = getMensajeError();
        }
        
        if (validarCampoTexto(this.contrasenia, 20) == false){
            this.errorContrasenia = getMensajeError();
        }
    
    }
    
    public void validarContrasenia(){
        
        if(validarContrasenia(this.nuevaContrasenia, this.confirmacion, 20) == false){
            
            this.errorNuevaContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
        }
    
    }
   
    public void validarRestablecer(){
        
                     
        if(validarCampoTexto(this.contrasenia, 20) == false){
            this.errorContrasenia = getMensajeError();
        }
        
        if(validarNumero(this.nuevaContrasenia, 12) == false){
            this.errorNuevaContrasenia = getMensajeError();
        }
        
        if(validarCorreo(this.confirmacion, 45) == false){
            this.errorConfirmacion = getMensajeError();
        }
        
    }     
    
    public void validarNuevaContrasenia(){
        
        if(validarCampoTexto(this.contrasenia, 20) == false){
            
            this.errorContrasenia = getMensajeError();            
        }
        
        if(validarContrasenia(this.nuevaContrasenia, this.confirmacion, 20) == false){
            
            this.errorNuevaContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
        }
    }
    
    public void validarEdicionAdmin(){
        
         if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = getMensajeError();
            
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = getMensajeError();
            
        }
        
        if (validarCampoTexto(this.usuario, 20) == false){
            this.errorUsuario = getMensajeError();

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
    }
    
    public void validarAdminAxiliar(){
        
            
        if (this.tipoAdmin == null){
            this.errorTipoAdmin = "Debe seleccionar una opción";
        }
        
         if (validarCampoTexto(this.nombreUsuario, 45) == false){
            this.errorNombreUsuario = getMensajeError();
            
        }    
        
        if (validarCampoTexto(this.apellidosUsuario, 45) == false){
            this.errorApellidosUsuario = getMensajeError();
            
        }
        
        if (validarCampoTexto(this.usuario, 20) == false){
            this.errorUsuario = getMensajeError();

        }
        
        if (validarContrasenia(this.contrasenia, this.confirmacion, 20) == false){
            this.errorContrasenia = getMensajeError();
            this.errorConfirmacion = getMensajeError();
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
            
            this.mensajeError = "No deben ser sólo espacios en blanco";
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
            
                this.mensajeError = "No deben ser sólo espacios en blanco";
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
    
    public boolean validarContrasenia(String campoTexto, String confirmacion, int numeroCaracteres){
        
        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty() || confirmacion == null || confirmacion.equals("") || confirmacion.isEmpty()){
            
            this.mensajeError = "Debe rellenar este campo"; 
            return false;        
        }
        
        else if (!campoTexto.equals(confirmacion)){
            
            this.mensajeError = "Las contraseñas no coinciden";
            return false;
        }
        
        else if ( campoTexto.length() > numeroCaracteres || confirmacion.length() > numeroCaracteres){
            
            this.mensajeError = "Máximo "+numeroCaracteres+" caracteres";
            return false;
        }
        
        else if(campoTexto.trim().equals("") || confirmacion.trim().equals("")){
            
            this.mensajeError = "No deben ser sólo espacios en blanco";
            return false;
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
    public String getErrorConfirmacion(){
        return this.errorConfirmacion;
    }
    
    public String getErrorNuevaContrasenia(){
        return this.errorNuevaContrasenia;
    }
    
    public String getErrorMulta(){
        return this.errorMulta;
    }
    
    public String getErrorTipoAdmin(){
        return this.errorTipoAdmin;
    }
        
    public String getMensajeError(){
        return this.mensajeError;
    }
    
    
 }
