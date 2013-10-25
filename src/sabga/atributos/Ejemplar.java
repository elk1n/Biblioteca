
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */

public class Ejemplar {
    
    private final StringProperty ejemplar;
    private final StringProperty estado;
    private final StringProperty disponibilidad;
    
    public Ejemplar(String ejemplar, String estado, String disponibilidad){
         
          this.ejemplar = new SimpleStringProperty(ejemplar);
          this.estado = new SimpleStringProperty(estado);
          this.disponibilidad = new SimpleStringProperty(disponibilidad);   
    }
    
    public String getEjemplar(){
        return ejemplar.get();
    }
    
    public String getEstado(){
        return estado.get();
    }
    
    public String getDisponibilidad(){
        return disponibilidad.get();
    }

}
