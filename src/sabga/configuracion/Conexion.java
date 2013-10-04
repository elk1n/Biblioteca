package sabga.configuracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Elk1n
 */
public class Conexion {

    private final String puerto = "8889/";
    private final String nombreBaseDatos = "SABGAB";
    private final String usuario = "root";
    private final String contrasenia = "root";
    private final String url = "jdbc:mysql://localhost:";
    private final String driver = "com.mysql.jdbc.Driver";
    private Connection conexion = null;
    private Statement stm = null;
    private ResultSet resultado = null;
    private final String cadenaConexion = url + puerto + nombreBaseDatos;
    private CallableStatement procedimiento; 

    public void conectar() {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(cadenaConexion, usuario, contrasenia);
            stm = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    }

    public void desconectar() {

        try {
            this.conexion.close();
            this.stm.close();
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al desconectarse de la base de datos", "Error");
        }
    }
    
    public void procedimiento(String procedimientoAlmacenado){
        
        try {
            this.procedimiento = conexion.prepareCall(procedimientoAlmacenado);
        } catch (SQLException ex) {
             Utilidades.mensajeError(null, ex.getMessage(), "Error al ejecutar el procedimiento", "Error");
        } 
    }
    
    public CallableStatement getProcedimiento(){
        return this.procedimiento;
    }
    
    public Statement getStatement() {
        return stm;
    }

    public ResultSet getResultado() {
        return resultado;
    }

    public Connection getConexion() {

        return conexion;
    }

    public void setResultado(ResultSet resultado) {

        this.resultado = resultado;
    }
}
