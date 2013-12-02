
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Devolucion {

    private final SimpleStringProperty ejemplar;
    private final SimpleStringProperty titulo;
    private final SimpleStringProperty codigo;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty estado;
        
    public Devolucion(String ejemplar, String titulo, String codigo, String fecha, String estado){
        
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);        
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
    public String getFecha(){
        return this.fecha.get();
    }
    public String getEstado(){
        return this.estado.get();
    }
    
    
}
