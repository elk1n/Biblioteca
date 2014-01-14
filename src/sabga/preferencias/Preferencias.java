
package sabga.preferencias;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
    private File archivo;
    private File carpeta;
    private final String ruta = System.getenv("APPDATA")+"/Sabga/Preferencias.properties";

    public Preferencias(){
     
        try {
            archivo = new File(ruta);
            carpeta = new File(System.getenv("APPDATA")+"/Sabga");
            if(archivo.exists() && carpeta.exists()){
                entrada = new FileInputStream(ruta);     
                propiedades = new Properties();
                propiedades.load(entrada);
                entrada.close();
            }else{
                carpeta.mkdir();
                archivo.createNewFile();               
                propiedades = new Properties();
                propiedades.load(new FileInputStream(archivo));
                propiedades.setProperty("direccion", "localhost");
                propiedades.setProperty("numeroMaximoEjemplares", "3");
                propiedades.setProperty("rutamysqldump", "C\\:/Wamp/bin/mysql/mysql5.6.12/bin/mysqldump.exe");
                propiedades.setProperty("puerto", "587");
                propiedades.setProperty("usuario", "root");
                propiedades.setProperty("puertoBase", "8889");
                propiedades.setProperty("correo", "bibliotecagilbertoalzate@outlook.com");
                propiedades.setProperty("baseDatos", "SABGA");
                propiedades.setProperty("clave", "");
                propiedades.setProperty("host", "smtp-mail.outlook.com");
                propiedades.setProperty("contrasenia", "biblioteca1958");                
                propiedades.store(new FileWriter(archivo), null);
            }
            
        } catch (FileNotFoundException e) {
            Utilidades.mensajeError(null, e.getMessage(), "El archivo de preferencias no existe", "Error");
        } catch (IOException e) {
            Utilidades.mensajeError(null, e.getMessage(), "El archivo de preferencias no puede ser leído", "Error");
        }
       
    }
    
    public void setPreferencia(String key, String valor){
        try {
            salida = new FileOutputStream(ruta);
            propiedades.setProperty(key, valor);
            propiedades.store(salida, null);
            salida.close();
        } catch (FileNotFoundException ex) {
            Utilidades.mensajeError(null, ex.getMessage() , "El archivo de preferencias no existe", "Error");
        } catch (IOException e) {
            Utilidades.mensajeError(null, e.getMessage(), "El archivo de preferencias no puede ser leído", "Error");
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
