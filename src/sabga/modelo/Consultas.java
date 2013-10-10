
package sabga.modelo;

import java.sql.SQLException;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sabga.atributos.Material;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Consultas {

    private final Conexion con;
    private final ObservableList lista;
    private final ObservableList<Material> listaMaterial;
    private String titulo, clasificacion, publicacion, editorial, tipoMaterial, claseMaterial;
    private int paginas, ejemplares, anio; 
        
    public Consultas(){
        con = new Conexion();
        lista = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();       
    }
       
    public ObservableList llenarComboBox(String consulta, String dato){
    
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta));
            while (con.getResultado().next()) {
                lista.add(con.getResultado().getString(dato));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intente más tarde", "Error Acceso");
        }
        finally{
            con.desconectar();
        }
        return lista;
    }
    
    public ObservableList<Material> getListaMaterial(String tipo){
       
        listaMaterial.clear();
        String consultaMaterial= "SELECT M.id_material AS 'id', M.titulo AS 'titulo', M.codigo_clasificacion AS 'codigo', C.clase_material AS 'clase'" +
                                    " FROM tbl_MATERIAL AS M" +
                                    " JOIN tbl_CLASE_MATERIAL AS C ON C.id_clase_material = M.id_clase_material" +
                                    " JOIN tbl_TIPO_MATERIAL AS T ON T.id_tipo_material = M.id_tipo_material" +
                                    " WHERE T.tipo_material ="+ "'"+tipo+"'";         
       try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consultaMaterial));
            while (con.getResultado().next()) {
               listaMaterial.add(new Material(con.getResultado().getString("titulo"), con.getResultado().getString("codigo"), con.getResultado().getString("clase"), con.getResultado().getString("id")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intente más tarde", "Error Acceso");
        }
       finally{
           con.desconectar();
       }
        return listaMaterial;       
   }

    public void mapearMaterial(int codigo) {
    
         try {

            con.conectar();
            con.procedimiento("{ CALL mapearMaterial(?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("id", codigo);
            con.getProcedimiento().registerOutParameter("tituloMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("clasificacion", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("publicacionMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("anio", Types.DATE);
            con.getProcedimiento().registerOutParameter("paginas", Types.INTEGER);
            con.getProcedimiento().registerOutParameter("ejemplares", Types.INTEGER);
            con.getProcedimiento().registerOutParameter("editorial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("tipoMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("claseMaterial", Types.VARCHAR);
            con.getProcedimiento().execute();
            
            titulo = con.getProcedimiento().getString("tituloMaterial");
            clasificacion = con.getProcedimiento().getString("clasificacion");
            publicacion = con.getProcedimiento().getString("publicacionMaterial");
            anio = con.getProcedimiento().getInt("anio");
            paginas = con.getProcedimiento().getInt("paginas");
            ejemplares = con.getProcedimiento().getInt("ejemplares");
            editorial = con.getProcedimiento().getString("editorial");
            tipoMaterial = con.getProcedimiento().getString("tipoMaterial");
            claseMaterial = con.getProcedimiento().getString("claseMaterial");

        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de eliminar el autor", "Error Eliminar Autor");  
        } finally {
            con.desconectar();
        }
    }
    
    public String getTitulo(){
        return this.titulo;
    }
    
    public String getClasificacion(){
        return this.clasificacion;
    }
    
    public String getPublicacion(){
        return this.publicacion;
    }
    
    public int getAnio(){
        return this.anio;
    }
    
    public int getPaginas(){
        return this.paginas;
    }
    
    public int getEjemplares(){
        return this.ejemplares;
    }
    
    public String getEditorial(){
        return this.editorial;
    }
    
    public String getTipoMaterial(){
        return this.tipoMaterial;
    }
    
    public String getClaseMaterial(){
        return this.claseMaterial;
    }
    
}
