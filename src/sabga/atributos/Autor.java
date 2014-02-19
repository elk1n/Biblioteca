
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
  * @author Elk1n
 */

public class Autor {
    
    private SimpleIntegerProperty idAutor;
    private SimpleStringProperty nombreAutor;
    private SimpleStringProperty datosAutor;
    private SimpleStringProperty apellidosAutor;
    
    
    public Autor(int id, String nombre, String apellido){
        
        this.idAutor = new SimpleIntegerProperty(id);
        this.nombreAutor = new SimpleStringProperty(nombre);
        this.apellidosAutor = new SimpleStringProperty(apellido);          
    }
    
    public Autor(int id, String nombre){
        this.idAutor = new SimpleIntegerProperty(id);
        this.nombreAutor = new SimpleStringProperty(nombre);
    }
    
    public Autor(String datos){
        this.datosAutor = new SimpleStringProperty(datos);
    }
    
    public String getNombreAutor(){    
        return nombreAutor.get();
    }
    
    public  String getApellidosAutor(){    
        return apellidosAutor.get();
    }
    
    public int getIdAutor(){        
        return this.idAutor.get();
    }
    
    public String getDatosAutor(){
        return this.datosAutor.get();
    }
        
}
