
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
  * @author Elk1n
 */

public class Reserva {
    
    private SimpleIntegerProperty id;
    private SimpleStringProperty documento;
    private SimpleStringProperty nombre;
    private SimpleStringProperty apellido;
    private SimpleStringProperty correo;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty estado;
    private SimpleStringProperty tipoUsuario;
    private SimpleStringProperty grado;
    private SimpleStringProperty curso;
    private SimpleStringProperty jornada;
    
    
    public Reserva (int id, String documento, String nombre, String apellido, String correo, String fecha, String estado, String tipoUsuario){
        
        this.id = new SimpleIntegerProperty(id);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);
        this.tipoUsuario = new SimpleStringProperty(tipoUsuario);
    }

    public Reserva(int id, String fecha, String estado){
    
        this.id = new SimpleIntegerProperty(id);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);
    }
    
    public Reserva(String documento, String nombre, String titulo, String ejemplar, String codigo, String fecha ){
    
            this.documento = new SimpleStringProperty(documento);
            this.nombre = new SimpleStringProperty(nombre);
            this.apellido = new SimpleStringProperty(titulo);
            this.correo = new SimpleStringProperty(ejemplar);
            this.estado = new SimpleStringProperty(codigo);
            this.fecha = new SimpleStringProperty(fecha);
    }
    
    public Reserva(int id, String documento, String nombre, String apellido, String correo, String grado, String curso,
                   String jornada, String fecha, String estado){
    
        this.id = new SimpleIntegerProperty(id);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.grado = new SimpleStringProperty(grado);
        this.curso = new SimpleStringProperty(curso);
        this.jornada = new SimpleStringProperty(jornada);
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
    public String getCorreo(){
        return this.correo.get();
    }    
    public String getFecha(){
        return this.fecha.get();   
    }    
    public String getEstado(){
        return this.estado.get();
    }   
    public String getTipoUsuario(){
        return this.tipoUsuario.get();
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
}

