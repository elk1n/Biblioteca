
package sabga.preferencias;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Preferencias {
    
    private Properties propiedades;
    private FileInputStream entrada;
    private FileOutputStream salida;

    public Preferencias(){
     
        try {
            entrada = new FileInputStream("src/sabga/preferencias/preferencias.properties");
            propiedades = new Properties();
            propiedades.load(entrada);
            entrada.close();
        } catch (FileNotFoundException e) {
            Utilidades.mensajeError(null, "El archivo de preferencias no existe", "Archivo no encontrado", "Error");
        } catch (IOException e) {
            Utilidades.mensajeError(null, "El archivo de preferencias no puede ser leído", "Archivo no leído", "Error");
        }
       
    }
    
    public void setPreferencia(String key, String valor){
        try {
            salida = new FileOutputStream("src/sabga/preferencias/preferencias.properties");
            propiedades.setProperty(key, valor);
            propiedades.store(salida, null);
            salida.close();
        } catch (FileNotFoundException ex) {
            Utilidades.mensajeError(null, "El archivo de preferencias no existe", "Archivo no encontrado", "Error");
        } catch (IOException e) {
            Utilidades.mensajeError(null, "El archivo de preferencias no puede ser leído", "Archivo no leído", "Error");
        }
    
    }

    public String getCorreo(){ 
       return propiedades.getProperty("correo");
    }
    
    public String getContrasenia(){
        return propiedades.getProperty("contrasenia");
    }
    
    public String getHost(){
        return propiedades.getProperty("host");
    }
    
    public String getPuerto(){
        return propiedades.getProperty("puerto");
    }
    
    public int getNumeroEjemplares(){
        return Integer.parseInt(propiedades.getProperty("numeroMaximoEjemplares"));
    }
    
    public void setCorreo(String mail){     
        setPreferencia("correo", mail);
    }
    
    public void setContrasenia(String clave){
        setPreferencia("contrasenia", clave);
    }
    
    public void setHost(String host){
        setPreferencia("host", host);
    }
    
    public void setPuerto(String puerto){
        setPreferencia("puerto", puerto);
    }
    
    public void setNuemeroEjemplares(String numeroEjemplares){
        setPreferencia("numeroMaximoEjemplares", numeroEjemplares);
    }

    public String getDireccionBase(){
        return propiedades.getProperty("direccion");
    }
    
    public String getPuertoBaseDatos(){
        return propiedades.getProperty("puertoBase");
    }
    
    public String getNombreBaseDatos(){
        return propiedades.getProperty("baseDatos");
    }
    
    public String getUsuarioBase(){
        return propiedades.getProperty("usuario");
    }
    
    public String getContraseniaBase(){
        return propiedades.getProperty("clave");
    }
    
    public void setDireccionBase(String urlBaseDatos){
        setPreferencia("direccion", urlBaseDatos);
    }
    
    public void setPuertoBase(String puertoBaseDatos){
        setPreferencia("puertoBase", puertoBaseDatos);
    }
    
    public void setNombreBaseDatos(String nombreBaseDatos){
        setPreferencia("baseDatos", nombreBaseDatos);
    }
    
    public void setUsuarioBaseDatos(String nombreBase){
        setPreferencia("usuario", nombreBase);
    }
    
    public void setContraseniaBase(String contrasenia){
        setPreferencia("clave", contrasenia);
    }
    
    public String getRutaAmysqldump(){
        return propiedades.getProperty("rutamysqldump");
    }
    
    public void setRutaAmysqldump(String ruta){
        setPreferencia("rutamysqldump", ruta);
    }
}
