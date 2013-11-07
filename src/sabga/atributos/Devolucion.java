
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Devolucion {

    private final SimpleIntegerProperty ejemplar;
    private final SimpleStringProperty titulo;
    private final SimpleStringProperty codigo;
    
    public Devolucion(int ejemplar, String titulo, String codigo){
        
        this.ejemplar = new SimpleIntegerProperty(ejemplar);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
    }

    public int getEjemplar(){
        return this.ejemplar.get();
    }
    public String getTitulo(){
        return this.titulo.get();
    }
    public String getCodigo(){
        return this.codigo.get();
    }
    public void setEjemplar(int ejemplar){
        this.ejemplar.set(ejemplar);
    }
    public void setTitulo(String titulo){
        this.titulo.set(titulo);
    }
    public void setCodigo(String codigo){
        this.codigo.set(codigo);
    }
}
