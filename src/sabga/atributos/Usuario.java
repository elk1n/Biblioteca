package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;

/**
 * @author Nanny
 */

public class Usuario {

    public SimpleStringProperty tipo = new SimpleStringProperty();
    public SimpleStringProperty nombre = new SimpleStringProperty();
    public SimpleStringProperty apellido = new SimpleStringProperty();
    public SimpleStringProperty correo = new SimpleStringProperty();
    public SimpleStringProperty grado = new SimpleStringProperty();
    public SimpleStringProperty curso = new SimpleStringProperty();
    public SimpleStringProperty jornada = new SimpleStringProperty();
    public SimpleStringProperty documento = new SimpleStringProperty();
    public SimpleStringProperty telefono = new SimpleStringProperty();
    public SimpleStringProperty direccion = new SimpleStringProperty();
    public SimpleStringProperty estado = new SimpleStringProperty();
    public SimpleStringProperty multa = new SimpleStringProperty();

 //Usuarios Estudiante
    
    public Usuario(String tipo, String documento, String nombre, String apellido, String correo){
    
        this.tipo = new SimpleStringProperty(tipo);
        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
    
    }
    
    public Usuario(String tipo, String nombre, String apellido, String correo, String grado, String curso, String jornada,
                   String documento, String telefono, String direccion, String estado, String multa) {

        this.tipo = new SimpleStringProperty(tipo);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.grado = new SimpleStringProperty(grado);
        this.curso = new SimpleStringProperty(curso);
        this.jornada = new SimpleStringProperty(jornada);
        this.documento = new SimpleStringProperty(documento);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.estado = new SimpleStringProperty(estado);
        this.multa = new SimpleStringProperty(multa);

    }

    //Usuario Empleados
    public Usuario(String tipo, String nombre, String apellido, String correo, String documento, String telefono,
                   String direccion, String estado, String multa) {

        this.tipo = new SimpleStringProperty(tipo);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.correo = new SimpleStringProperty(correo);
        this.documento = new SimpleStringProperty(documento);
        this.telefono = new SimpleStringProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.estado = new SimpleStringProperty(estado);
        this.multa = new SimpleStringProperty(multa);

    }

//Usuarios Paz y salvo
    public Usuario(String documento, String nombre, String apellidos, String grado, String curso, String jornada) {

        this.documento = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellidos);
        this.grado = new SimpleStringProperty(grado);
        this.curso = new SimpleStringProperty(curso);
        this.jornada = new SimpleStringProperty(jornada);
    }

    public String getTipo() {
        return this.tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getApellido() {
        return this.apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public String getCorreo() {
        return this.correo.get();
    }

    public void setCorreo(String correo) {
        this.correo.set(correo);
    }

    public String getGrado() {
        return this.grado.get();
    }

    public void setGrado(String grado) {
        this.grado.set(grado);
    }

    public String getCurso() {
        return this.curso.get();
    }

    public void setCurso(String curso) {
        this.curso.set(curso);
    }

    public String getJornada() {
        return this.jornada.get();
    }

    public void setJornada(String jornada) {
        this.jornada.set(jornada);
    }

    public String getDocumento() {
        return this.documento.get();
    }

    public void setDocumento(String documento) {
        this.documento.set(documento);
    }

    public String getTelefono() {
        return this.telefono.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getDireccion() {
        return this.direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getEstado() {
        return this.estado.get();
    }

    public void setEstado(String estado) {
        this.estado.set(estado);
    }

    public String getMulta() {
        return this.multa.get();
    }

    public void setMulta(String multa) {
        this.multa.set(multa);
    }

}