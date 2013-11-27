
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
    public SimpleStringProperty correo;
    public SimpleStringProperty fecha;
    public SimpleStringProperty estado;
    public SimpleStringProperty tipoUsuario;
    
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
}

