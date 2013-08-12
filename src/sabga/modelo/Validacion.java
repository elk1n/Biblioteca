
package sabga.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Elk1n
 */

public class Validacion {
    
    private String mensajeError;
    private Calendar calendario;
        
    public boolean validarCampoTexto(String campoTexto, int numeroCaracteres){
                   
        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty()){
            
            this.mensajeError = "Debe rellenar este campo"; 
            return false;        
        }
        
        else if (campoTexto.length() > numeroCaracteres){
            
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
        
        if ( campoTexto.isEmpty() == false){
            
            if (campoTexto.length() > numeroCaracteres){
                
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
    
    public boolean validarNuevaContrasenia(String campoTexto, String confirmacion, int numeroCaracteres){
        
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
    
    public boolean validarAnio(String campoTexto, int numeroCaracteres) {
        
        this.calendario = Calendar.getInstance();
        this.calendario = new GregorianCalendar();

        int anio;

        if (validarNumero(campoTexto, numeroCaracteres)) {

            anio = Integer.parseInt(campoTexto);

            if (anio > calendario.get(Calendar.YEAR)) {

                this.mensajeError = "El año es mayor al actual";
                return false;
            }

            return true;
        } else {
            return false;
        }
    }
    
    public String getMensajeError(){
        
        return this.mensajeError;
    }
}
