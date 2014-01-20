
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Prestamo {
    
    private SimpleStringProperty ejemplar;
    private SimpleStringProperty titulo;
    private SimpleStringProperty codigo;
    private SimpleIntegerProperty idPrestamo;
    private SimpleStringProperty documento;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty fecha;
    private SimpleStringProperty estado;
    private SimpleStringProperty correo;
    private SimpleStringProperty bibliotecario;
    private SimpleStringProperty grado;
    private SimpleStringProperty curso;
    private SimpleStringProperty jornada;
    private SimpleStringProperty fechaReserva;
        
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
    
    public Prestamo(int id, String documento, String nombre, String apellido, String bibliotecario, String grado, String curso,
                    String jornada, String fechaReserva, String fechaPrestamo, String estado){
        
        this.idPrestamo = new SimpleIntegerProperty(id);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.bibliotecario = new SimpleStringProperty(bibliotecario);
        this.grado = new SimpleStringProperty(grado);
        this.curso = new SimpleStringProperty(curso);
        this.jornada = new SimpleStringProperty(jornada);
        this.fechaReserva = new SimpleStringProperty(fechaReserva);
        this.fecha = new SimpleStringProperty(fechaPrestamo);
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
    
    public String getBibliotecario(){
        return  this.bibliotecario.get();
    }
    
    public String getGrado(){
        return this.grado.get();
    }
    
    public String getCurso(){
        return this.curso.get();
    }
    
    public String getJornada(){
        return this.jornada.get();
    }
    
    public String getFechaReserva(){
        return this.fechaReserva.get();
    }
    
}
