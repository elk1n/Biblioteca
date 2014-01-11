package sabga.modelo;

import java.sql.SQLException;
import java.sql.Types;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sabga.atributos.Autor;
import sabga.atributos.Devolucion;
import sabga.atributos.Ejemplar;
import sabga.atributos.Materia;
import sabga.atributos.Material;
import sabga.atributos.Multa;
import sabga.atributos.Prestamo;
import sabga.atributos.Reserva;
import sabga.atributos.Usuario;
import sabga.configuracion.Conexion;
import sabga.configuracion.Utilidades;

/**
 * @author Elk1n
 */

public class Consultas {

    private final Conexion con;
    private String titulo, clasificacion, publicacion, editorial, tipoMaterial, claseMaterial, mensaje, tipoUsuario, grado,
                   curso, jornada, nombre, apellido, correo, telefono, direccion, estado, documento, usuario;
    private int paginas, anio, idPrestamo, idDevolucion;
    private double multa;

    public Consultas() {
        con = new Conexion();
    }

    public ObservableList llenarLista(int opcion){
            
        ObservableList lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getLista(?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(con.getResultado().getString("columna"));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;
    }
   
    public ObservableList<Material> getListaMaterialBusqueda(String parametroBusqueda) {

        ObservableList<Material> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarMaterial(?) }");
            con.getProcedimiento().setString("parametro", parametroBusqueda);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Material(con.getResultado().getString("titulo"), con.getResultado().getString("codigo"),
                                       con.getResultado().getString("tipo"), con.getResultado().getString("clase"),
                                       con.getResultado().getString("id")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del material", "Error Consulta");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Material> getListaMaterial(String tipo) {

        ObservableList<Material> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaMaterial(?)}");
            con.getProcedimiento().setString("parametro", tipo);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Material(con.getResultado().getString("titulo"), con.getResultado().getString("codigo"),
                                       con.getResultado().getString("tipo"), con.getResultado().getString("clase"),
                                       con.getResultado().getString("id")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Materia> listaMaterias(int id) {

        ObservableList<Materia> listaMaterias = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaMaterias(?) }");
            con.getProcedimiento().setInt("id", id);
            con.setResultado(con.getProcedimiento().executeQuery());
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

        ObservableList<Autor> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaAutoresMaterial(?) }");
            con.getProcedimiento().setInt("id", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Autor(con.getResultado().getInt("id"), con.getResultado().getString("nombre"),
                                    con.getResultado().getString("apellido")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Autor> getListaAutores() {
        
        ObservableList<Autor> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaAutores() }");
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Autor(con.getResultado().getInt("id"), con.getResultado().getString("nombre"),
                                    con.getResultado().getString("apellido")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Usuario> getListaUsuarioBusqueda(String parametroBusqueda) {

        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarUsuario(?) }");
            con.getProcedimiento().setString("parametro", parametroBusqueda);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Usuario(con.getResultado().getString("tipo"), con.getResultado().getString("id"),
                        con.getResultado().getString("nombres"), con.getResultado().getString("apellido"),
                        con.getResultado().getString("email"), con.getResultado().getString("estado")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del usuario.", "Error Consulta Usuario");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Usuario> getListaBibliotecarios() {

        ObservableList<Usuario> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaAdmin() }");
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Usuario(con.getResultado().getString("id"), con.getResultado().getString("nombres"),
                        con.getResultado().getString("apellido"), con.getResultado().getString("usuario"),
                        con.getResultado().getString("email"), con.getResultado().getString("phone"),
                        con.getResultado().getString("estado"), con.getResultado().getString("tipo")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos de los bibliotecarios.", "Error Consulta Bibliotecario");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Usuario> getListaUsuarios(int opcion, String tipo) {

        ObservableList<Usuario> lista = FXCollections.observableArrayList();

        try {
            con.conectar();
            con.procedimiento("{ CALL getListaUsuarios(?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("parametro", tipo);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Usuario(con.getResultado().getString("tipo"), con.getResultado().getString("documento"),
                                      con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                      con.getResultado().getString("correo"), con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }
    
    public ObservableList<Usuario> getListaUsuariosRecibo(int opcion, String tipo) {

        ObservableList<Usuario> lista = FXCollections.observableArrayList();

        try {
            con.conectar();
            con.procedimiento("{ CALL getListaUsuarios(?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("parametro", tipo);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Usuario(con.getResultado().getString("tipo"), con.getResultado().getString("documento"),
                                      con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                      con.getResultado().getString("correo"), con.getResultado().getString("telefono"), 
                                      con.getResultado().getString("grado"), con.getResultado().getString("curso"),
                                      con.getResultado().getString("jornada"), con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Reserva> getListaReservas(int estado) {

        ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaReservas(?) }");
            con.getProcedimiento().setInt("estado", estado);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                listaReservas.add(new Reserva(con.getResultado().getInt("id"), con.getResultado().getString("documento"),
                                              con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                              con.getResultado().getString("email"), con.getResultado().getString("fecha"),
                                              con.getResultado().getString("estado"), con.getResultado().getString("tipoUsuario")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaReservas;
    }

    public ObservableList<Reserva> getListaReservaBusqueda(String parametroBusqueda) {

        ObservableList<Reserva> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarReserva(?) }");
            con.getProcedimiento().setString("parametro", parametroBusqueda);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Reserva(con.getResultado().getInt("id"), con.getResultado().getString("documento"),
                        con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                        con.getResultado().getString("email"), con.getResultado().getString("fecha"), 
                        con.getResultado().getString("estado"), con.getResultado().getString("tipoUsuario")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del usuario.", "Error Consulta Usuario");
        } finally {
            con.desconectar();
        }
        return lista;
    }

    public ObservableList<Material> getListaDetalleReserva(int id) {

        ObservableList<Material> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetalleReserva(?)}");
            con.getProcedimiento().setInt("idReserva", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Material(con.getResultado().getInt("id"), con.getResultado().getString("titulo"),
                        con.getResultado().getString("ejemplar"), con.getResultado().getString("codigo"),
                        con.getResultado().getString("autor"), con.getResultado().getString("editorial"),
                        con.getResultado().getString("materia")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }
    
    public ObservableList<Prestamo> getListaPrestamo(int estado){
    
        
        ObservableList<Prestamo> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaPrestamo(?) }");
            con.getProcedimiento().setInt("estado", estado);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                    lista.add(new Prestamo(con.getResultado().getInt("id"), con.getResultado().getString("documento"),
                                              con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                              con.getResultado().getString("fecha"), con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;
        
    }
    
    public ObservableList<Prestamo> buscarPrestamo(String parametro){
       
        ObservableList<Prestamo> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarPrestamo(?) }");
            con.getProcedimiento().setString("parametro", parametro);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                    lista.add(new Prestamo(con.getResultado().getInt("id"), con.getResultado().getString("documento"),
                                              con.getResultado().getString("nombre"), con.getResultado().getString("apellido"),
                                              con.getResultado().getString("fecha"), con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;       
    }
    
    public ObservableList<Devolucion> getListaDetallePrestamo(int id) {

        ObservableList<Devolucion> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetallePrestamo(?)}");
            con.getProcedimiento().setInt("idPrestamo", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Devolucion(con.getResultado().getString("ejemplar"), con.getResultado().getString("titulo"),
                                            con.getResultado().getString("codigo"), con.getResultado().getString("fecha"),
                                            con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }
    
    public ObservableList<Multa> getMulta(int id){
    
        ObservableList<Multa> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaMultas(?) }");
            con.getProcedimiento().setInt("id", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                    lista.add(new Multa(con.getResultado().getInt("id"), con.getResultado().getInt("prestamo"), 
                                        con.getResultado().getInt("documento"), con.getResultado().getString("nombre"), 
                                        con.getResultado().getString("fecha"), con.getResultado().getString("estado"),
                                        con.getResultado().getInt("valor")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;   
    }
    
    public ObservableList<Devolucion> getListaDetalleDevolucion(int id){
    
        ObservableList<Devolucion> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetalleDevolucion(?)}");
            con.getProcedimiento().setInt("prestamo", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Devolucion(con.getResultado().getString("ejemplar"), con.getResultado().getString("fecha")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
    }
    
    public ObservableList<Prestamo> getListaPrestamoUsusario(String id){
       
        ObservableList<Prestamo> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaPrestamoUsuario(?) }");
            con.getProcedimiento().setString("id", id);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                    lista.add(new Prestamo(con.getResultado().getInt("id"), con.getResultado().getString("fecha"),
                                           con.getResultado().getString("estado"), con.getResultado().getString("reserva"),
                                           con.getResultado().getString("documento"), con.getResultado().getString("nombres")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return lista;       
    }
    
    public ObservableList<Ejemplar> getListaDetallePrestamoUsuario(int prestamo){
    
            ObservableList<Ejemplar> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetallePrestamoUsuario(?)}");
            con.getProcedimiento().setInt("prestamo", prestamo);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Ejemplar(con.getResultado().getString("titulo"), con.getResultado().getString("ejemplar"),
                                       con.getResultado().getString("fecha"), con.getResultado().getString("codigo"), 
                                       con.getResultado().getString("estado"),
                                       con.getResultado().getString("tipo"), con.getResultado().getString("clase")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
    }
    
    public ObservableList<Reserva> getListaReservaUsuario(String documento){
        
            ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaReservaUsuario(?) }");
            con.getProcedimiento().setString("documento", documento);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                listaReservas.add(new Reserva(con.getResultado().getInt("id"), con.getResultado().getString("fecha"),
                                              con.getResultado().getString("estado")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }
        return listaReservas;  
    }
    
    public ObservableList<Ejemplar> getListaDetalleReservaUsuario(int reserva){
    
            ObservableList<Ejemplar> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetalleReservaUsuario(?)}");
            con.getProcedimiento().setInt("reserva", reserva);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Ejemplar(con.getResultado().getString("titulo"), con.getResultado().getString("ejemplar"),
                                       con.getResultado().getString("codigo"), con.getResultado().getString("estado"),
                                       con.getResultado().getString("tipo"), con.getResultado().getString("clase")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
    }
    
    public ObservableList<Devolucion> getListaDevolucionUsuario(String documento){
    
        ObservableList<Devolucion> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDevolucionUsuario(?)}");
            con.getProcedimiento().setString("documento", documento);
                con.setResultado(con.getProcedimiento().executeQuery());
                while (con.getResultado().next()) {
                    lista.add(new Devolucion(con.getResultado().getInt("idD"), con.getResultado().getInt("id"),
                                             con.getResultado().getString("fecha"), con.getResultado().getString("estado"),
                                             con.getResultado().getString("estadoD"), con.getResultado().getString("documento"),
                                             con.getResultado().getString("nombres")));
                }
            } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
    }
    
    public ObservableList<Ejemplar> getListaDetalleDevolucionUsuario(int devolucion, int prestamo){
    
            ObservableList<Ejemplar> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDetalleDevolucionUsuario(?, ?)}");
            con.getProcedimiento().setInt("devolucion", devolucion);
            con.getProcedimiento().setInt("prestamo", prestamo);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Ejemplar(con.getResultado().getString("titulo"), con.getResultado().getString("ejemplar"),
                                       con.getResultado().getString("fecha"), con.getResultado().getString("fechaD"), 
                                       con.getResultado().getString("codigo"), con.getResultado().getString("estado"),
                                       con.getResultado().getString("tipo"), con.getResultado().getString("clase")));
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;      
    }
    
    public ObservableList<Devolucion> getListaDevolucionDia(){
    
        ObservableList<Devolucion> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaDevolucionesDia()}");
                con.setResultado(con.getProcedimiento().executeQuery());
                while (con.getResultado().next()) {
                    lista.add(new Devolucion(con.getResultado().getString("id"), con.getResultado().getString("nombre"),
                                             con.getResultado().getString("titulo"), con.getResultado().getString("ejemplar"),
                                             con.getResultado().getString("codigo"), con.getResultado().getString("fecha")));
                }
            } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
        
    }
    
    public ObservableList<Reserva> getListaReservasDia(){
    
        ObservableList<Reserva> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL getListaReservasDia()}");
                con.setResultado(con.getProcedimiento().executeQuery());
                while (con.getResultado().next()) {
                    lista.add(new Reserva(con.getResultado().getString("id"), con.getResultado().getString("nombre"),
                                          con.getResultado().getString("titulo"), con.getResultado().getString("ejemplar"),
                                          con.getResultado().getString("codigo"), con.getResultado().getString("fecha")));
                }
            } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;    
        
    }
    
    public ObservableList<Material> getListaMaterialInicio(int opcion, String parametroBusqueda) {

         ObservableList<Material> lista = FXCollections.observableArrayList();
        try {
            con.conectar();
            con.procedimiento("{ CALL buscarMaterialInicio(?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("parametro", parametroBusqueda);
            con.setResultado(con.getProcedimiento().executeQuery());
            while (con.getResultado().next()) {
                lista.add(new Material(con.getResultado().getInt("id"), con.getResultado().getString("titulo"),
                                       con.getResultado().getString("codigo"), con.getResultado().getString("tipo"),
                                       con.getResultado().getString("clase"), con.getResultado().getString("editorial"),
                                       con.getResultado().getString("publicacion"), con.getResultado().getString("anio_publicacion"),
                                       con.getResultado().getInt("numero_paginas")));
            }
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar el material. ", "Error Consulta");
        } finally {
            con.desconectar();
        }        
     return lista;
    }
    
    public void mapearInfoAdmin(int id){
    
        try {
            con.conectar();
            con.procedimiento("{ CALL getInfoAdmin(?,?,?) }");
            con.getProcedimiento().setInt("idPrestamo", id);
            con.getProcedimiento().registerOutParameter("nombre", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("id", Types.VARCHAR);
            con.getProcedimiento().execute();
  
            nombre = con.getProcedimiento().getString("nombre");
            documento = con.getProcedimiento().getString("id");
           
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del biblitecario", "Error Consulta");
        } finally {
            con.desconectar();
        }
    }
    
    public void mapearBibliotecario(String nombreUsuario) {

        try {
            con.conectar();
            con.procedimiento("{ CALL mapearBibliotecario(?,?,?,?,?,?,?) }");
            con.getProcedimiento().setString("usuario", nombreUsuario);
            con.getProcedimiento().registerOutParameter("id", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("nombres", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("apellido", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("email", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("phone", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("nombreUsuario", Types.VARCHAR);
            con.getProcedimiento().execute();

            documento = con.getProcedimiento().getString("id");
            nombre = con.getProcedimiento().getString("nombres");
            apellido = con.getProcedimiento().getString("apellido");
            correo = con.getProcedimiento().getString("email");
            telefono = con.getProcedimiento().getString("phone");
            usuario = con.getProcedimiento().getString("nombreUsuario");

        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del biblitecario", "Error Consulta");
        } finally {
            con.desconectar();
        }
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

    public void editarMaterialMateria(int opcion, int material, String materia) {

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

    public void editarAutorMaterial(int opcion, int material, int autor) {

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

    public void editarMaterial(int opcion, int material, String clase, String editorial, String codigo,
                               String titulo, String publicacion, int anio, int paginas) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarMaterial(?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("material", material);
            con.getProcedimiento().setString("clase", clase);
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

    public void registrarUsuario(int opcion, String tipo, String nombre, String apellido, String correo, String documento,
                                 String grado, String curso, String jornada, String telefono, String direccion) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarUsuario(?,?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("tipo", tipo);
            con.getProcedimiento().setString("nombres", nombre);
            con.getProcedimiento().setString("apellido", apellido);
            con.getProcedimiento().setString("email", correo);
            con.getProcedimiento().setString("documento", documento);
            con.getProcedimiento().setString("grado", grado);
            con.getProcedimiento().setString("curso", curso);
            con.getProcedimiento().setString("jornada", jornada);
            con.getProcedimiento().setString("phone", telefono);
            con.getProcedimiento().setString("address", direccion);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado el usuario.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }

    }

    public void mapearUsuarios(String id) {

        try {
            con.conectar();
            con.procedimiento("{ CALL mapearUsuario(?,?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setString("id", id);
            con.getProcedimiento().registerOutParameter("tipo", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("grado", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("curso", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("jornada", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("nombres", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("apellido", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("multa", Types.DOUBLE);
            con.getProcedimiento().registerOutParameter("email", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("phone", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("address", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("estado", Types.VARCHAR);
            con.getProcedimiento().execute();

            tipoUsuario = con.getProcedimiento().getString("tipo");
            grado = con.getProcedimiento().getString("grado");
            curso = con.getProcedimiento().getString("curso");
            jornada = con.getProcedimiento().getString("jornada");
            nombre = con.getProcedimiento().getString("nombres");
            apellido = con.getProcedimiento().getString("apellido");
            multa = con.getProcedimiento().getDouble("multa");
            correo = con.getProcedimiento().getString("email");
            telefono = con.getProcedimiento().getString("phone");
            direccion = con.getProcedimiento().getString("address");
            estado = con.getProcedimiento().getString("estado");

        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del material", "Error Consulta");
        } finally {
            con.desconectar();
        }

    }

    
    public void eliminarMaterial(int codigo) throws SQLException {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL eliminarMaterial(?) }");
            con.getProcedimiento().setInt("codigo", codigo);

            con.getProcedimiento().execute();
           // mensaje = con.getProcedimiento().getString("mensaje");
        } catch (SQLException e) {
            con.getConexion().rollback();
        } finally {
            con.desconectar();
        }
    }

    public void editarUsuario(int opcion, String id, String tipo, String nombre, String apellido, String correo, String documento,
                              String grado, String curso, String jornada, String telefono, String direccion, int estado) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarUsuario(?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("id", id);
            con.getProcedimiento().setString("tipo", tipo);
            con.getProcedimiento().setString("nombres", nombre);
            con.getProcedimiento().setString("apellido", apellido);
            con.getProcedimiento().setString("email", correo);
            con.getProcedimiento().setString("documento", documento);
            con.getProcedimiento().setString("grado", grado);
            con.getProcedimiento().setString("curso", curso);
            con.getProcedimiento().setString("jornada", jornada);
            con.getProcedimiento().setString("phone", telefono);
            con.getProcedimiento().setString("address", direccion);
            con.getProcedimiento().setInt("estado", estado);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible editar la información del usuario.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }

    public void registrarBibliotecario(String documento, String usuario, String contrasenia, String tipo, String nombre, String apellido,
                                       String correo, String telefono) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarBibliotecario(?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setString("documento", documento.trim());
            con.getProcedimiento().setString("usuario", usuario.trim());
            con.getProcedimiento().setString("pass", Utilidades.encriptar(contrasenia.trim()));
            con.getProcedimiento().setString("tipo", tipo.trim());
            con.getProcedimiento().setString("nombres", nombre.trim());
            con.getProcedimiento().setString("apellido", apellido.trim());
            con.getProcedimiento().setString("email", correo.trim());
            con.getProcedimiento().setString("phone", telefono.trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado el bibliotecario.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }

    }

    public void editarBibliotecario(int opcion, String id, int estado, String tipo, String nombre, String apellido, String telefono,
                                    String correo, String usuario, String documento) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarBibliotecario(?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("id", id.trim());
            con.getProcedimiento().setInt("estadoB", estado);
            con.getProcedimiento().setString("tipo", tipo);
            con.getProcedimiento().setString("nombres", nombre.trim());
            con.getProcedimiento().setString("apellido", apellido.trim());
            con.getProcedimiento().setString("telephone", telefono);
            con.getProcedimiento().setString("email", correo.trim());
            con.getProcedimiento().setString("usuario", usuario.trim());
            con.getProcedimiento().setString("documento", documento.trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible editar la información del bibliotecario.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }

    public void cambiarContrasenia(String id, String antigua, String nueva) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL cambiarContrasenia(?,?,?,?) }");
            con.getProcedimiento().setString("id", id);
            con.getProcedimiento().setString("oldPass", antigua);
            con.getProcedimiento().setString("newPass", nueva);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No ha sido posible cambiar la contraseña.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void getDatosBibliotecario(String usuario){
        
         try {
            con.conectar();
            con.procedimiento("{ CALL getDatosBibliotecario(?,?,?,?,?) }");
            con.getProcedimiento().setString("usuario", usuario);
            con.getProcedimiento().registerOutParameter("id", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("nombres", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("apellido", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();           
            documento = con.getProcedimiento().getString("id");
            nombre = con.getProcedimiento().getString("nombres");
            apellido = con.getProcedimiento().getString("apellido");
            mensaje = con.getProcedimiento().getString("mensaje");
        } catch (SQLException e) {
            mensaje = "No se pudo obtener la información del bibliotecario.";             
        } finally {
            con.desconectar();
        }        
    }
    
    public void registrarPrestamo(int opcion, String usuario, String bibliotecario, int reserva, String fecha, String fechaEntrega,
                                  ObservableList<Prestamo> ejemplares) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarPrestamo(?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("idUsuario", usuario);
            con.getProcedimiento().setString("idBibliotecario", bibliotecario);
            con.getProcedimiento().setInt("reserva", reserva);
            con.getProcedimiento().setString("fecha", fecha);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("id", Types.INTEGER);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            idPrestamo = con.getProcedimiento().getInt("id");

            for (Prestamo p : ejemplares) {
                con.procedimiento("{ CALL registrarDetallePrestamo(?,?,?,?) }");
                con.getProcedimiento().setInt("ejemplar", Integer.parseInt(p.getEjemplar()));
                con.getProcedimiento().setInt("prestamo", idPrestamo);
                con.getProcedimiento().setString("fecha", fechaEntrega);
                con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
                con.getProcedimiento().execute();
                mensaje = con.getProcedimiento().getString("mensaje");
            }            
            con.getConexion().commit();
            if(mensaje!=null){
                con.getConexion().rollback();
            }
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado el prestamo.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void registrarReserva(String usuario, ObservableList<Prestamo> ejemplares) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarReserva(?,?,?) }");
            con.getProcedimiento().setString("documento", usuario);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("id", Types.INTEGER);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            idPrestamo = con.getProcedimiento().getInt("id");

            for (Prestamo p : ejemplares) {
                con.procedimiento("{ CALL registrarDetalleReserva(?,?,?) }");
                con.getProcedimiento().setInt("ejemplar", Integer.parseInt(p.getEjemplar()));
                con.getProcedimiento().setInt("reserva", idPrestamo);
                con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
                con.getProcedimiento().execute();
                mensaje = con.getProcedimiento().getString("mensaje");
            }
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado la reserva.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void registrarDevolucion(int opcion, int prestamo, ObservableList<Devolucion> lista, String fecha ){
    
         try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarDevolucion(?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("prestamo", prestamo);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("id", Types.INTEGER);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");
            idDevolucion = con.getProcedimiento().getInt("id");

            for (Devolucion p : lista) {
                con.procedimiento("{ CALL registrarDetalleDevolucion(?,?,?,?,?) }");
                con.getProcedimiento().setInt("devolucion", idDevolucion);
                con.getProcedimiento().setInt("prestamo", prestamo);
                con.getProcedimiento().setInt("ejemplar", Integer.parseInt(p.getEjemplar()));
                con.getProcedimiento().setString("fechaDevolucion", fecha);
                con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
                con.getProcedimiento().execute();
                mensaje = con.getProcedimiento().getString("mensaje");
            }
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado la devolución.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }        
    }
    
    public void registrarDetalleDevolucion(int devolucion, int prestamo,  ObservableList<Devolucion> lista, String fecha){
    
          try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            for(Devolucion p : lista){
                con.procedimiento("{ CALL registrarDetalleDevolucion(?,?,?,?,?) }");
                con.getProcedimiento().setInt("devolucion", devolucion);
                con.getProcedimiento().setInt("prestamo", prestamo);
                con.getProcedimiento().setInt("ejemplar", Integer.parseInt(p.getEjemplar()));
                con.getProcedimiento().setString("fechaDevolucion", fecha);
                con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
                con.getProcedimiento().execute();
                mensaje = con.getProcedimiento().getString("mensaje");
            }
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha registrado la devolución.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }        
    }
       
    public void registrarRenovacion(int opcion, int prestamo, ObservableList<Devolucion> lista, String fecha) {

        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            for(Devolucion p: lista){
                con.procedimiento("{ CALL registrarRenovacion(?,?,?,?,?) }"); 
                con.getProcedimiento().setInt("opcion", opcion);
                con.getProcedimiento().setInt("prestamo", prestamo);
                con.getProcedimiento().setInt("ejemplar", Integer.parseInt(p.getEjemplar()));
                con.getProcedimiento().setString("fecha", fecha);
                con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
                con.getProcedimiento().execute();
                mensaje = con.getProcedimiento().getString("mensaje");
            }            
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se ha realizado la renovación.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void pagarMultas(int opcion, int id, int valor){
      
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL pagarMulta(?,?,?,?) }");
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setInt("id", id);
            con.getProcedimiento().setInt("valor", valor);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().execute();
            mensaje = con.getProcedimiento().getString("mensaje");

            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
                mensaje = "No se realizó el pago de la multa.";
            } catch (SQLException ex) {
                mensaje = ex.getMessage();
            }
            mensaje = e.getMessage();
        } finally {
            con.desconectar();
        }
    }
    
    public void cancelarReserva(){
    
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL cancelarReserva() }");
            con.getProcedimiento().execute();
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al cancelar la reserva. ", "Error");
            }
            Utilidades.mensajeError(null, e.getMessage(), "Error al cancelar la reserva. ", "Error");
        } finally {
            con.desconectar();
        }
    }
    
    public void setValorMulta(int valor){
    
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarValorMulta(?) }");
            con.getProcedimiento().setInt("valor", valor);
            con.getProcedimiento().execute();
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar el valor. ", "Error Guardar Valor");
            }
            Utilidades.mensajeError(null, e.getMessage(), "Error al guardar el valor. ", "Error Guardar Valor");
        } finally {
            con.desconectar();
        }
    }
    
    public void setEstadoCorreos(int estado){
    
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL setEstadoCorreos(?) }");
            con.getProcedimiento().setInt("estado", estado);
            con.getProcedimiento().execute();
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar el estado. ", "Error Guardar Estado");
            }
            Utilidades.mensajeError(null, e.getMessage(), "Error al guardar el estado. ", "Error Guardar Estado");
        } finally {
            con.desconectar();
        }
    }
    
    public void guardarContrasenia(String documento, String correo, String clave){
    
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL nuevaContrasenia(?,?,?) }");
            con.getProcedimiento().setString("documento", documento);
            con.getProcedimiento().setString("email", correo);
            con.getProcedimiento().setString("clave", Utilidades.encriptar(clave));
            con.getProcedimiento().execute();
            con.getConexion().commit();
        } catch (SQLException e) {
            try {
                con.getConexion().rollback();
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar la nueva contraseña.", "Error Guardar Contraseña");
            }
            Utilidades.mensajeError(null, e.getMessage(), "Error al guardar la nueva contraseña.", "Error Guardar Contraseña");
        } finally {
            con.desconectar();
        } 
    }
    
    public ObservableList<Usuario> enviarCorreoUsuarios(){
        
        ObservableList<Usuario> lista = FXCollections.observableArrayList();        
        try {
            con.conectar();
            con.procedimiento("{ CALL getCorreoUsuario() }");
            con.setResultado(con.getProcedimiento().executeQuery());            
            while (con.getResultado().next()) {
                lista.add(new Usuario(con.getResultado().getString("documento"), con.getResultado().getString("email"),
                                      con.getResultado().getString("nombre")));                      
            }
                                
        } catch (SQLException e) {            
            Utilidades.mensajeError(null, e.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
        return lista;
    }
    
    public String getDetalleCorreoUsuario(String documento){
    
        String contenido = "";
        
         try {
            con.conectar();
            con.procedimiento("{ CALL getCorreoDetalleUsuario(?) }");                 
            con.getProcedimiento().setString("id", documento);
            con.setResultado(con.getProcedimiento().executeQuery());          
            while (con.getResultado().next()) { 
                
                contenido += "El "+con.getResultado().getString("tipo")+" " +con.getResultado().getString("titulo")+
                           " para entregar el día "+con.getResultado().getString("fecha")+". \n\n";    
            }
                                
        } catch (SQLException e) {            
            Utilidades.mensajeError(null, e.getMessage(), "No ha sido posible acceder a la base de datos\nFavor intentar más tarde.", "Error Acceso");
        } finally {
            con.desconectar();
        }
         return contenido;
    }
    
    public int getDevolucion(int prestamo){
        
        int devolucion=0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getDevolucion(?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setInt("prestamo", prestamo);
            con.getProcedimiento().execute();
            devolucion = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar la devolución.", "Error Consulta Devolución");
        } finally {
            con.desconectar();
        }
        return devolucion;    
    }
    
    public int getIdDevolucion(int prestamo){
        
        int devolucion = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getIdDevolucion(?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setInt("prestamo", prestamo);            
            con.getProcedimiento().execute();
            devolucion = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar la devolución.", "Error Consulta Devolución");  
        } finally {
            con.desconectar();
        }
        return devolucion;
    }
    
    public int getValorMulta(String documento){
    
        int valor = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getMultaUsuario(?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setString("id", documento);            
            con.getProcedimiento().execute();
            valor = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar la multa", "Error Consulta Multa");  
        } finally {
            con.desconectar();
        }
        return valor;
    }
   
    public int getEstadoPrestamos(String documento){
    
        int cantidad = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getEstadoPrestamos(?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setString("id", documento);            
            con.getProcedimiento().execute();
            cantidad = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los prestamos", "Error Consulta Prestamos");  
        } finally {
            con.desconectar();
        }
        return cantidad;
    }
    
    public int getValorPorMulta() {

        int valorMulta = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getValorMulta() }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().execute();
            valorMulta = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar el valor de la multa.", "Error Consulta Multa");
        } finally {
            con.desconectar();
        }
        return valorMulta;

    }
    
    public int getEstadoCorreos() {

        int estadoCorreos = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getEstadoCorreos() }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().execute();
            estadoCorreos = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar el estado del envió de correos.", "Error Consulta Estado");
        } finally {
            con.desconectar();
        }
        return estadoCorreos;

    }
    
    public int getId(int opcion, String nombre) {

        int id = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getId(?,?) }");
            con.getProcedimiento().registerOutParameter(1, Types.INTEGER);
            con.getProcedimiento().setInt("opcion", opcion);
            con.getProcedimiento().setString("nombre", nombre);
            con.getProcedimiento().execute();
            id = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "No ha sido posible realizar la operación solicitada.", "Error");
        } finally {
            con.desconectar();
        }
        return id;
    }
    
    public int consultarUsuario(String usuario, String contrasenia, String tipo){
        
        int ingreso = 0;
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL inicioSesion(?,?,?) }");
            con.getProcedimiento().registerOutParameter(1, Types.TINYINT);
            con.getProcedimiento().setString("usuario", usuario );
            con.getProcedimiento().setString("clave", contrasenia);            
            con.getProcedimiento().setString("tipo", tipo );            
            con.getProcedimiento().execute();
            ingreso = con.getProcedimiento().getInt(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al inciar sesión", "Error Iniciar Sesión");  
        } finally {
            con.desconectar();
        }
        return ingreso;
    }
    
    public String validarUsuario(String documento, String correo){
    
        String nombreUsuario = null;
         try {
            con.conectar();
            con.procedimiento("{ ? = CALL verificarBibliotecario(?,?) }");
            con.getProcedimiento().registerOutParameter(1, Types.VARCHAR);
            con.getProcedimiento().setString("documento", documento );
            con.getProcedimiento().setString("email", correo);                   
            con.getProcedimiento().execute();
            nombreUsuario = con.getProcedimiento().getString(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al verificar el usuario", "Error Vefificar Usuario");  
        } finally {
            con.desconectar();
        }
        return nombreUsuario; 
    }
               
    public String getNombreBibliotecario(String usuario){
    
        String nombreBibliotecario = "";
        try {
            con.conectar();
            con.procedimiento("{ ? = CALL getNombreBiblitecario(?) }");
            con.getProcedimiento().registerOutParameter(1, Types.VARCHAR);
            con.getProcedimiento().setString("usuario", usuario);            
            con.getProcedimiento().execute();
            nombreBibliotecario = con.getProcedimiento().getString(1);
        } catch (SQLException e) {
            Utilidades.mensajeError(null, e.getMessage(), "Error al consultar los datos del bibliotecario", "Error Consulta Datos");  
        } finally {
            con.desconectar();
        }
        return nombreBibliotecario;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public String getGrado() {
        return grado;
    }

    public String getCurso() {
        return curso;
    }

    public String getJornada() {
        return jornada;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEstado() {
        return estado;
    }

    public String getDocumento() {
        return documento;
    }

    public String getUsuario() {
        return usuario;
    }
    
    public int getIdDevolucion(){
        return idDevolucion;
    }
    
    public double getMulta() {
        return multa;
    }
}
