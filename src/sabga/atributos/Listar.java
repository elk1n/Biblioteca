
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */
public class Listar {
    private final StringProperty nombre;
    
    public Listar(String nombre){
    
        this.nombre = new SimpleStringProperty(nombre);    
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public void setNombre(String nombre){    
        this.nombre.set(nombre);    
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }
}
