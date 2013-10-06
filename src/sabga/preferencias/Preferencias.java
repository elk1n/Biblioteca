
package sabga.preferencias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Preferencias {
    
    private String correo, contrasenia, puerto, host;
    private Properties propiedades;
    
    public Preferencias(){
        
        try {

         propiedades = new Properties();
         propiedades.load(new FileInputStream("src/sabga/preferencias/preferencias.properties"));

        } catch (FileNotFoundException e) {
            Utilidades.mensajeError(null, "El archivo de preferencias no existe", "Archivo no encontrado", "Error");
        } catch (IOException e) {
            Utilidades.mensajeError(null, "El archivo de preferencias no puede ser leído", "Archivo no leído", "Error");
        }    
    }

    public String getCorreo(){ 
       return correo = propiedades.getProperty("correo");
    }
    
    public String getContrasenia(){
        return contrasenia = propiedades.getProperty("contrasenia");
    }
    
    public String getHost(){
        return host = propiedades.getProperty("host");
    }
    
    public String getPuerto(){
        return puerto = propiedades.getProperty("puerto");
    }


}
