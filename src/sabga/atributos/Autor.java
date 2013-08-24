
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
  * @author Elk1n
 */

public class Autor {
    
    private final StringProperty nombreAutor;
    private final StringProperty apellidosAutor;
    
    public Autor(String nombre, String apellidos){
        
          this.nombreAutor = new SimpleStringProperty(nombre);
          this.apellidosAutor = new SimpleStringProperty(apellidos);     
    }
    
    public String getNombreAutor(){    
        return nombreAutor.get();
    }
    
    public  String getApellidosAutor(){    
        return apellidosAutor.get();
    }
    
    public void  setNombreAutor(String nombre){
        this.nombreAutor.set(nombre);
    }
    
    public void setApellidos(String apellidos){    
        this.apellidosAutor.set(apellidos);
    }
    
    @Override
    public String toString() {
        return this.getNombreAutor() + this.getApellidosAutor();
    }
    
}
