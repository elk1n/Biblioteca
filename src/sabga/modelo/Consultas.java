package sabga.modelo;

import java.sql.SQLException;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sabga.atributos.Autor;
import sabga.atributos.Ejemplar;
import sabga.atributos.Materia;
import sabga.atributos.Material;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Consultas {

    private final Conexion con;
    private final ObservableList<Material> listaMaterial;
    private ObservableList<Autor> obtenerAutores;
    private String titulo, clasificacion, publicacion, editorial, tipoMaterial, claseMaterial, mensaje;
    private int paginas, anio;

    public Consultas() {
        con = new Conexion();
        listaMaterial = FXCollections.observableArrayList();
    }

    public ObservableList llenarLista(String consulta, String dato) {

        ObservableList lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta));
            while (con.getResultado().next()) {
                lista.add(con.getResultado().getString(dato));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intente más tarde", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList listaAutores() {

        obtenerAutores = FXCollections.observableArrayList();
        ObservableList listaAutores = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_AUTOR ORDER BY nombre_autor, apellidos_autor"));
            while (con.getResultado().next()) {
                obtenerAutores.add(new Autor(con.getResultado().getString("nombre_autor"), con.getResultado().getString("apellidos_autor"),
                                             con.getResultado().getString("id_autor")));
            }
            for (Autor datos : obtenerAutores) {
                listaAutores.add(datos.toString());
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaAutores;
    }

    public ObservableList<Material> getListaMaterialBusqueda(String parametroBusqueda) {

        listaMaterial.clear();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarMaterial(?) }");
            con.getProcedimiento().setString("parametro", parametroBusqueda);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                listaMaterial.add(new Material(con.getResultado().getString("titulo"), con.getResultado().getString("codigo"),
                        con.getResultado().getString("clase"), con.getResultado().getString("id")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del material", "Error Consulta");
        } finally {
            con.desconectar();
        }
        return listaMaterial;
    }

    public ObservableList<Material> getListaMaterial(String tipo) {

        listaMaterial.clear();
        String consultaMaterial = "SELECT M.id_material AS 'id', M.titulo AS 'titulo', M.codigo_clasificacion AS 'codigo', C.clase_material AS 'clase'"
                + " FROM tbl_MATERIAL AS M"
                + " JOIN tbl_CLASE_MATERIAL AS C ON C.id_clase_material = M.id_clase_material"
                + " JOIN tbl_TIPO_MATERIAL AS T ON T.id_tipo_material = M.id_tipo_material"
                + " WHERE T.tipo_material =" + "'" + tipo + "'";
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consultaMaterial));
            while (con.getResultado().next()) {
                listaMaterial.add(new Material(con.getResultado().getString("titulo"), con.getResultado().getString("codigo"),
                        con.getResultado().getString("clase"), con.getResultado().getString("id")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intente más tarde", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return listaMaterial;
    }

    public ObservableList<Materia> listaMaterias(int id) {

        ObservableList<Materia> listaMaterias = FXCollections.observableArrayList();
        String consulta = "SELECT MA.nombre_materia AS 'materia' "
                + "FROM tbl_MATERIA AS MA "
                + "JOIN tbl_MATERIAL_MATERIA AS MM ON MA.id_materia = MM.id_materia "
                + "JOIN tbl_MATERIAL AS M ON MM.id_material = M.id_material "
                + "WHERE M.id_material =" + "'" + id + "'";
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta));
            while (con.getResultado().next()) {
                listaMaterias.add(new Materia(con.getResultado().getString("materia")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaMaterias;
    }

    public ObservableList<Ejemplar> listaEjemplares(int id) {

        ObservableList<Ejemplar> listaEjemplares = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL listarEjemplares(?) }");
            con.getProcedimiento().setInt("id", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                listaEjemplares.add(new Ejemplar(con.getResultado().getString("ejemplar"), con.getResultado().getString("estado"),
                        con.getResultado().getString("disponibilidad")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaEjemplares;
    }

    public ObservableList<Autor> listaAutores(int id) {

        ObservableList<Autor> listaAutores = FXCollections.observableArrayList();
        String consulta = "SELECT A.id_autor as 'id', A.nombre_autor AS 'nombre', A.apellidos_autor AS 'apellido'"
                + "FROM tbl_AUTOR AS A "
                + "JOIN tbl_AUTOR_MATERIAL AS AM ON A.id_autor = AM.id_autor "
                + "JOIN tbl_MATERIAL AS M ON AM.id_material = M.id_material "
                + "WHERE M.id_material = " + "'" + id + "'";
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta));
            while (con.getResultado().next()) {
                listaAutores.add(new Autor(con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                           con.getResultado().getString("id")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaAutores;
    }

    public ObservableList listaAutoresMaterial(int id) {

        ObservableList autores = FXCollections.observableArrayList();
        ObservableList<Autor> listaAutores = FXCollections.observableArrayList();
        listaAutores.addAll(listaAutores(id));
        for (Autor datos : listaAutores) {
            autores.add(datos.toString());
        }
        return autores;
    }

    public void mapearMaterial(int codigo) {

        try {
            con.conectar();
            con.procedimiento("{ CALL mapearMaterial(?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("id", codigo);
            con.getProcedimiento().registerOutParameter("tituloMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("clasificacion", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("publicacionMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("anio", Types.DATE);
            con.getProcedimiento().registerOutParameter("paginas", Types.INTEGER);
            con.getProcedimiento().registerOutParameter("editorial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("tipoMaterial", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("claseMaterial", Types.VARCHAR);
            con.getProcedimiento().execute();

            titulo = con.getProcedimiento().getString("tituloMaterial");
            clasificacion = con.getProcedimiento().getString("clasificacion");
            publicacion = con.getProcedimiento().getString("publicacionMaterial");
            anio = con.getProcedimiento().getInt("anio");
            paginas = con.getProcedimiento().getInt("paginas");
            editorial = con.getProcedimiento().getString("editorial");
            tipoMaterial = con.getProcedimiento().getString("tipoMaterial");
            claseMaterial = con.getProcedimiento().getString("claseMaterial");

        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del material", "Error Consulta");
        } finally {
            con.desconectar();
        }
    }

    public void eliminarEjemplar(int codigo, int idMaterial) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL eliminarEjemplar(?,?,?) }");
            con.getProcedimiento().setInt("codigo", codigo);
            con.getProcedimiento().setInt("material", idMaterial);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            if (mensaje != null) {
            } else {
                con.getConexion().commit();
            }
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = String.valueOf(e.getErrorCode());
                Utilidades.mensajeError(null, e.getMessage(), "Error al eliminar el ejemplar", "Error Eliminar Ejemplar");
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al eliminar el ejemplar", "Error Eliminar Ejemplar");
            }
        } finally {
            con.desconectar();
        }

    }

    public void editarEjemplar(int opcion, int material, int ejemplar, int cantidad, String disponi) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarEjemplar(?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("material", material);
            con.getProcedimiento().setInt("ejemplar", ejemplar);
            con.getProcedimiento().setInt("cantidad", cantidad);
            con.getProcedimiento().setString("disponi", disponi);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible editar el ejemplar.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void editarMaterialMateria(int opcion, int material, String materia){
    
         try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarMaterialMateria(?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("material", material);
            con.getProcedimiento().setString("materia", materia);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible realizar la operación solicitada.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }         
    }
    
    public void editarAutorMaterial(int opcion, int material, int autor){
    
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarAutorMaterial(?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("material", material);
            con.getProcedimiento().setInt("autor", autor);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible realizar la operación solicitada.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    
    }
    
    public void editarMaterial(int opcion, int material, String clase, String tipo, String editorial, String codigo, 
                               String titulo, String publicacion, int anio, int paginas ){
    
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarMaterial(?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("material", material);
            con.getProcedimiento().setString("clase", clase);
            con.getProcedimiento().setString("tipo", tipo);
            con.getProcedimiento().setString("editorial", editorial);
            con.getProcedimiento().setString("codigo", codigo);
            con.getProcedimiento().setString("tituloMaterial", titulo);
            con.getProcedimiento().setString("publicacionMaterial", publicacion);
            con.getProcedimiento().setInt("anio", anio);
            con.getProcedimiento().setInt("paginas", paginas);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible editar el material.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    
    }

    public ObservableList<Autor> getListaAutores() {
        return obtenerAutores;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getClasificacion() {
        return this.clasificacion;
    }

    public String getPublicacion() {
        return this.publicacion;
    }

    public int getAnio() {
        return this.anio;
    }

    public int getPaginas() {
        return this.paginas;
    }

    public String getEditorial() {
        return this.editorial;
    }

    public String getTipoMaterial() {
        return this.tipoMaterial;
    }

    public String getClaseMaterial() {
        return this.claseMaterial;
    }

    public String getMensaje() {
        return mensaje;
    }

}
