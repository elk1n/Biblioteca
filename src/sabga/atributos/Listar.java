
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */
public class Listar {
    private final SimpleStringProperty nombre;
    private SimpleIntegerProperty id;
    
    public Listar(String nombre){    
        this.nombre = new SimpleStringProperty(nombre);    
    }
    
    public Listar(int id, String nombre){
    
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public int getId(){
        return this.id.get();
    }
    
    public void setNombre(String nombre){    
        this.nombre.set(nombre);    
    }
    
    @Override
    public String toString() {
        return this.getNombre();
    }
}
