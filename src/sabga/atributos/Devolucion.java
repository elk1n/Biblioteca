
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
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty bibliotecario;
    private SimpleStringProperty fechaReserva;
    private SimpleStringProperty correo;
            
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
                       String documento, String bibliotecario){
    
        this.devolucion = new SimpleIntegerProperty(idDevolucion);
        this.prestamo = new SimpleIntegerProperty(idPrestamo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estadoPrestamo = new SimpleStringProperty(estadoPrestamo);
        this.estado = new SimpleStringProperty(estadoDevolucion);
        this.documento = new SimpleStringProperty(documento);
        this.bibliotecario = new SimpleStringProperty(bibliotecario);
    }
    
    public Devolucion(String documento, String nombre, String titulo, String ejemplar, String codigo, String fecha){
    
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.titulo = new SimpleStringProperty(titulo);
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.codigo = new SimpleStringProperty(codigo);        
        this.fecha = new SimpleStringProperty(fecha);
    }
    
    public Devolucion(int id, int idPrestamo,String documento, String nombre, String apellido, String nombreB, String fechaReserva,
                      String prestamo, String estadoPrestamo, String estado, String correo){
    
        this.devolucion = new SimpleIntegerProperty(id);
        this.prestamo = new SimpleIntegerProperty(idPrestamo);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.bibliotecario = new SimpleStringProperty(nombreB);
        this.fechaReserva = new SimpleStringProperty(fechaReserva);
        this.fecha = new SimpleStringProperty(prestamo);
        this.estadoPrestamo = new SimpleStringProperty(estadoPrestamo);
        this.estado = new SimpleStringProperty(estado);
        this.correo = new SimpleStringProperty(correo);    
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
    public String getBibliotecario(){
        return this.bibliotecario.get();
    }
    public String getNombre(){
        return this.nombre.get();
    }
    public String getApellido(){
        return this.apellido.get();
    }
    public String getFechaReserva(){
        return this.fechaReserva.get();
    }
    public String getCorreo(){
        return this.correo.get();
    }
}
