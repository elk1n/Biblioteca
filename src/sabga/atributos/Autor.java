
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
  * @author Elk1n
 */

public class Autor {
    
    private final SimpleIntegerProperty idAutor;
    private final SimpleStringProperty nombreAutor;
    private final SimpleStringProperty apellidosAutor;
    
    
    public Autor(int id, String nombre, String apellido){
        
        this.idAutor = new SimpleIntegerProperty(id);
        this.nombreAutor = new SimpleStringProperty(nombre);
        this.apellidosAutor = new SimpleStringProperty(apellido);          
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
    
    @Override
    public String toString() {
        return this.getNombreAutor()+" "+this.getApellidosAutor();
    }
    
}
