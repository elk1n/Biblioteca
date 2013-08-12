
package sabga.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Elk1n 
 */

public class Conexion {
    
  private String puerto = "8889/"; 
  private String nombreBaseDatos= "SABGAB";
  private String usuario= "root";
  private String contrasenia="";
  private String url="jdbc:mysql://localhost:";
  private String driver= "com.mysql.jdbc.Driver";
  
  private Connection conexion=null;
  private Statement stm =null;     
  private ResultSet resultado = null ;
  
  private String cadenaConexion=url+puerto+nombreBaseDatos;
  
  public void conectar(){
  
      try{
           Class.forName(driver);
           conexion = DriverManager.getConnection(cadenaConexion, usuario, contrasenia);
           stm = conexion.createStatement();
      
      }catch(SQLException | ClassNotFoundException ex){
      
          Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");      
      } 
  }

    public void desconectar() {

        try {

            this.conexion.close();
            this.resultado.close();
            this.stm.close();
            
        } catch (Exception e) {
            
           Utilidades.mensajeError(null, e.getMessage(), "Error al desconectarse de la base de datos", "Error");          
        }
    }

    public Statement getStatement() {
        return stm;
    }

    public ResultSet getResultado() {
        return resultado;
    }

    public void setResultado(ResultSet resultado) {
        this.resultado = resultado;
    }
}
