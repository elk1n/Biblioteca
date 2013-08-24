
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */

public class Materia {
    
    private final StringProperty nombreMateria;
    
    public Materia(String materia){
        
          this.nombreMateria = new SimpleStringProperty(materia);  
    }
    
    public String getNombreMateria(){    
        return this.nombreMateria.get();
    }
    
    public void  setNombreMateria(String materia){
        this.nombreMateria.set(materia);
    }       
}
