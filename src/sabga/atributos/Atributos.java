
package sabga.atributos;

/**
 * @author Elkin
 */

public class Atributos {

    private static String datoBusqueda = "";
    private static String usuarioAdmin = "";
    
    public void setDatoBusqueda(String parametro){
        datoBusqueda = parametro;
    }
    
    public String getDatoBusqueda(){
        return datoBusqueda;
    }

    public String getUsuarioAdmin() {
        return usuarioAdmin;
    }

    public  void setUsuarioAdmin(String usuario) {
        usuarioAdmin = usuario;
    }
   
}
