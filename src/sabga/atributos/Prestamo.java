
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */

public class Prestamo {
    
    private StringProperty ejemplar;
    private StringProperty titulo;
    private StringProperty codigo;
    private SimpleIntegerProperty idPrestamo;
    private StringProperty documento;
    private StringProperty nombre;
    private StringProperty apellido;
    private StringProperty fecha;
    private StringProperty estado;
    private StringProperty correo;
    
    
    public Prestamo(String ejemplar, String titulo, String codigo) {

        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
    }

    public Prestamo(int prestamo, String documento, String nombre, String apellido, String correo, String fechaPrestamo, String estadoPrestamo) {
        
        this.idPrestamo = new SimpleIntegerProperty(prestamo);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.fecha = new SimpleStringProperty(fechaPrestamo);
        this.estado = new SimpleStringProperty(estadoPrestamo);
    }

    public Prestamo(String ejemplar, String titulo, String codigo, String fechaDevolucion, String estado){
        
        this.ejemplar = new SimpleStringProperty(estado);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
        this.fecha = new SimpleStringProperty(fechaDevolucion);
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
    
    public int getIdPrestamo(){
        return this.idPrestamo.get();
    }
    
    public String getDocumento(){
        return this.documento.get();
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public String getApellido(){
        return this.apellido.get();
    }
    
    public String getFecha(){
        return this.fecha.get();
    }
    
    public String getEstado(){
        return this.estado.get();
    }
    
    public String getCorreo(){
        return this.correo.get();
    }
    
}
