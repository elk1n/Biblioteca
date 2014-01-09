
package sabga.atributos;

/**
 * @author Elkin
 */

public class Atributos {

    private static String datoBusqueda = "";
    private static String nombreUsuario;
    private static String documento;
    private static String nombre;
    private static String apellido;
    private static String nombresUsuario;
    private static String apellidoUsuario;
    private static String documentoUsuario;
    private static String correoUsuario;
 
    
    public void setDatoBusqueda(String parametro){
        datoBusqueda = parametro;
    }
    
    public String getDatoBusqueda(){
        return datoBusqueda;
    }

    public String getUsuarioAdmin() {
        return nombreUsuario;
    }

    public  void setUsuarioAdmin(String usuario) {
        nombreUsuario = usuario;
    }

    public String getNombreUsuario() {
        return nombre;
    }

    public void setNombreUsuario(String nombres) {
        nombre = nombres;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String id) {
        documento = id;
    }

    public  String getNombre() {
        return nombre;
    }

    public void setNombre(String name) {
        nombre = name;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String lastName) {
        apellido = lastName;
    }

    public String getNombresUsuario() {
        return nombresUsuario;
    }

    public void setNombresUsuario(String nombres) {
        Atributos.nombresUsuario = nombres;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellido) {
        apellidoUsuario = apellido;
    }

    public String getDocumentoUsuario() {
        return documentoUsuario;
    }

    public void setDocumentoUsuario(String documento) {
        documentoUsuario = documento;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correo) {
        correoUsuario = correo;
    }
   
    
    
}
