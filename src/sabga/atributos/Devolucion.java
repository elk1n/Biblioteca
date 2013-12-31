
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Devolucion {

    private SimpleStringProperty ejemplar;
    private SimpleStringProperty titulo;
    private SimpleStringProperty codigo;
    private final SimpleStringProperty fecha;
    private SimpleStringProperty estado;
    private SimpleIntegerProperty prestamo;
    private SimpleIntegerProperty devolucion;
    private SimpleStringProperty estadoPrestamo;
    private SimpleStringProperty documento;
    private SimpleStringProperty nombres;
            
    public Devolucion(String ejemplar, String titulo, String codigo, String fecha, String estado){
        
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);        
    }
    
    public Devolucion(String ejemplar, String fecha){
        
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.fecha = new SimpleStringProperty(fecha);
    }

    public Devolucion (int idDevolucion, int idPrestamo, String fecha, String estadoPrestamo, String estadoDevolucion,
                       String documento, String nombres){
    
        this.devolucion = new SimpleIntegerProperty(idDevolucion);
        this.prestamo = new SimpleIntegerProperty(idPrestamo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estadoPrestamo = new SimpleStringProperty(estadoPrestamo);
        this.estado = new SimpleStringProperty(estadoDevolucion);
        this.documento = new SimpleStringProperty(documento);
        this.nombres = new SimpleStringProperty(nombres);
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
    public int getIdPrestamo(){
        return this.prestamo.get();
    }
    public int getIdDevolucion(){
        return this.devolucion.get();
    }
    public String getEstadoPrestamo(){
        return this.estadoPrestamo.get();
    }
    public String getDocumento(){
        return this.documento.get();
    }
    public String getNombre(){
        return this.nombres.get();
    }
}
