
package sabga.modelo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nanny
 * 
 */

public class usuarios {
    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido = new SimpleStringProperty();
    public SimpleStringProperty grado = new SimpleStringProperty();
    public SimpleStringProperty grupo = new SimpleStringProperty();
    public SimpleStringProperty mail = new SimpleStringProperty();
    public SimpleStringProperty documento = new SimpleStringProperty();
    public SimpleStringProperty jornada = new SimpleStringProperty();
    public SimpleStringProperty telefono = new SimpleStringProperty();
    public SimpleStringProperty direccion = new SimpleStringProperty();
    public SimpleStringProperty estado = new SimpleStringProperty();

    public String getEstado() {
        return estado.get();
    }
   
    
    public String getTipo() {
        return tipo.get();
    }

    public String getNombre() {
        return nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }

    public String getGrado() {
        return grado.get();
    }

    public String getGrupo() {
        return grupo.get();
    }

    public String getMail() {
        return mail.get();
    }

    public String getDocumento() {
        return documento.get();
    }

    public String getTelefono() {
        
        return telefono.get();
    }

  
    public String getJornada() {
        return jornada.get();
    }

   
    public String getDireccion() {
        return direccion.get();
    }
    
    
}
