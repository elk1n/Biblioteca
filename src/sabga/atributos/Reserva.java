
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
  * @author Elk1n
 */

public class Reserva {
    
    public SimpleIntegerProperty id;
    public SimpleStringProperty documento;
    public SimpleStringProperty nombre;
    public SimpleStringProperty apellido;
    public SimpleStringProperty fecha;
    public SimpleStringProperty estado;
    
    public Reserva (int id, String documento, String nombre, String apellido, String fecha, String estado ){
        
        this.id = new SimpleIntegerProperty(id);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);  
    }

    public int getId() {
        return this.id.get();
    }
    
    public String getDocumento(){
        return this.documento.get();
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public String getApellido() {
        return this.apellido.get();
    }
    
    public String getFecha(){
        return this.fecha.get();   
    }
    
    public String getEstado(){
        return this.estado.get();
    }
   
}

