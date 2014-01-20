package sabga.configuracion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sabga.preferencias.Preferencias;

/**
 * @author Elk1n
 */

public class Conexion {

    private final Preferencias pref;
    private final String puerto, nombreBaseDatos, usuario, contrasenia, direccion, url, driver, cadenaConexion;
    private Connection conexion = null;
    private Statement stm = null;
    private ResultSet resultado = null;
    private CallableStatement procedimiento;

    public Conexion(){
        
        pref = new Preferencias();
        puerto = pref.getPuertoBaseDatos();
        nombreBaseDatos = pref.getNombreBaseDatos();
        usuario = pref.getUsuarioBase();  // Windows
        contrasenia = pref.getContraseniaBase(); //root
        direccion = pref.getDireccionBase();
        url = "jdbc:mysql://"+direccion+":"; // localhost, 192.168.1.3
        driver = "com.mysql.jdbc.Driver";
        cadenaConexion = url + puerto +"/"+ nombreBaseDatos;
            
    }
    
    public void conectar() {

        try {
            Class.forName(driver);
            conexion = DriverManager.getConnection(cadenaConexion, usuario, contrasenia);
            stm = conexion.createStatement();
        } catch (SQLException | ClassNotFoundException ex) {            
            Utilidades.mensajeError(null,"Recuerde que la base de datos es indispensable para el correcto funcionamiento de la aplicación "
                    + "" ,"No ha sido posible conectarse a la base de datos, verifique su conexión a Internet o"
                    + " contacte con su administrador de servidor de base de datos.", "Error Acceso Base Datos");
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
