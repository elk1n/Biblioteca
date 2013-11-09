/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nanny
 */
public class Administrador {
    
    public SimpleStringProperty contrasenia2 = new SimpleStringProperty();
    public SimpleStringProperty usuarioo = new SimpleStringProperty(); 
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido = new SimpleStringProperty();
    public SimpleStringProperty mail = new SimpleStringProperty();
    public SimpleStringProperty telefono = new SimpleStringProperty();
    public SimpleStringProperty estado = new SimpleStringProperty();
    public SimpleStringProperty documento_bibliotecario= new SimpleStringProperty();
    public SimpleStringProperty tipoAdmin = new SimpleStringProperty();

  
   public Administrador(String documento, String usuario, String tipo, String nombre, String apellidos, String correo, String telefono, String estado) 
 {
    
    this.documento_bibliotecario = new SimpleStringProperty(documento); 
    this.usuarioo = new SimpleStringProperty(usuario); 
    this.tipoAdmin = new SimpleStringProperty(tipo);  
    this.nombre = new SimpleStringProperty(nombre);
    this.apellido = new SimpleStringProperty(apellidos); 
    this.mail = new SimpleStringProperty(correo); 
    this.telefono = new SimpleStringProperty(telefono); 
    if ("1".equals(estado)){
        
    this.estado = new SimpleStringProperty("Habilitado"); 
    }
    else{
    this.estado = new SimpleStringProperty("Inhabilitado"); 
    }
}
  
    public String getDocumento_bibliotecario() {
        return documento_bibliotecario.get();
    }

    public void setDocumento_bibliotecario(SimpleStringProperty documento_bibliotecario) {
        this.documento_bibliotecario = documento_bibliotecario;
    }
     public String getUsuarioo() {
        return usuarioo.get();
    }
      
    public String getTipoAdmin() {
        return tipoAdmin.get();
    }

   public String getEstado() {
        return estado.get();
    }
   

    public String getNombre() {
        return  nombre.get();
    }

    public String getApellido() {
        return apellido.get();
    }
   
    public String getMail() {
        return mail.get();
    }

   
    public String getTelefono() {
        return telefono.get();
    }
    
}
