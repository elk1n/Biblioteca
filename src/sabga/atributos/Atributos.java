
package sabga.atributos;

/**
 * @author Elkin
 */

public class Atributos {

    private static String datoBusqueda = "";
    private static String nombreUsuario = "admin";
    private static String documento;
    private static String nombre;
    private static String apellido;
    
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
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre) {
        nombreUsuario = nombre;
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
   
    
}
