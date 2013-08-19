
package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarMaterial extends ValidarMaterial{
    
    public boolean confirmarNuevoAutor(String nombre, String apellidos){
       
       if(!validarCampoTexto(nombre, 90)){
           return false;          
       }
       else if(!validarCampoTexto(apellidos, 90)){
           return false;
       }
       else{
           return true;
       }
   }
    
}
