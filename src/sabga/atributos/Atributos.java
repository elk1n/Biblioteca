
package sabga.atributos;

/**
 * @author Elkin
 */

public class Atributos {

    private static String datoBusqueda = "";
    private static String nombreUsuario = "admin6";
    
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
   
}
