
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Ejemplar {
    
    private SimpleStringProperty titulo;
    private final SimpleStringProperty ejemplar;
    private SimpleStringProperty fecha;
    private SimpleStringProperty fechaEntrega;
    private SimpleStringProperty codigo;
    private final SimpleStringProperty estado;
    private SimpleStringProperty tipo;
    private SimpleStringProperty clase;
    private SimpleStringProperty disponibilidad;
   
   
    public Ejemplar(String ejemplar, String estado, String disponibilidad){
         
          this.ejemplar = new SimpleStringProperty(ejemplar);
          this.estado = new SimpleStringProperty(estado);
          this.disponibilidad = new SimpleStringProperty(disponibilidad);   
    }
    
    public Ejemplar(String titulo, String ejemplar, String fecha, String codigo, String estado, String tipo, String clase){
        
        this.titulo = new SimpleStringProperty(titulo);
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.fecha = new SimpleStringProperty(fecha);
        this.codigo = new SimpleStringProperty(codigo);
        this.estado = new SimpleStringProperty(estado);
        this.tipo = new SimpleStringProperty(tipo);
        this.clase = new SimpleStringProperty(clase);        
    }
    
    public Ejemplar(String titulo, String ejemplar, String codigo, String estado, String tipo, String clase){
        
        this.titulo = new SimpleStringProperty(titulo);
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.codigo = new SimpleStringProperty(codigo);
        this.estado = new SimpleStringProperty(estado);
        this.tipo = new SimpleStringProperty(tipo);
        this.clase = new SimpleStringProperty(clase);        
    }
    
    public Ejemplar(String titulo, String ejemplar, String fechaDevolucion, String fechaEntrega, String codigo, String estado,
                    String tipo, String clase){
    
        this.titulo = new SimpleStringProperty(titulo);
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.fecha = new SimpleStringProperty(fechaDevolucion);
        this.fechaEntrega = new SimpleStringProperty(fechaEntrega);
        this.codigo = new SimpleStringProperty(codigo);
        this.estado = new SimpleStringProperty(estado);
        this.tipo = new SimpleStringProperty(tipo);
        this.clase = new SimpleStringProperty(clase);
    }
    
    public String getFechaEntrega(){
        return this.fechaEntrega.get();
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
    
    public String getEjemplar(){
        return ejemplar.get();
    }
    
    public String getTipo(){
        return this.tipo.get();
    }
    
    public String getClase(){
        return this.clase.get();
    }
    
    public String getEstado(){
        return this.estado.get();
    }
    
    public String getDisponibilidad(){
        return this.disponibilidad.get();
    }

}
