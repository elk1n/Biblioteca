
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */

public class Prestamo {
    
    private final StringProperty ejemplar;
    private final StringProperty titulo;
    private final StringProperty codigo;
    
    public Prestamo (String ejemplar, String titulo, String codigo){
    
          this.ejemplar = new SimpleStringProperty(ejemplar);
          this.titulo = new SimpleStringProperty(titulo);
          this.codigo = new SimpleStringProperty(codigo);    
    }
    
    public String getEjemplar(){
        return this.ejemplar.get();
    }
    
    public String getTitulo(){
        return this.titulo.get();
    }
    
    public String getCodigo(){
        return this.codigo.get();
    }
}
