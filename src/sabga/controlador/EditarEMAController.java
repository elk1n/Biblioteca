package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Autor;
import sabga.atributos.Listar;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */
public class EditarEMAController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private TableView tablaResultados;
    @FXML
    private TableColumn clmnNombre, clmnApellido;
    @FXML
    private ComboBox comboListar;
    @FXML
    private Label validarNombreAutor, validarApellidosAutor, validarEditorial, validarMateria, validarClaseMaterial, validarTipoMaterial;
    @FXML
    private TextField campoNombreAutor, campoApellidosAutor, campoEditorial, campoMateria, campoTipoMaterial, campoClaseMaterial, campoFiltrar;
    @FXML
    private TitledPane acordeonAutor, acordeonEditorial, acordeonMateria, acordeonTipo, acordeonClase;
    @FXML
    private Button btnBorrar, btnEliminar;
    private String nombre, apellido, mensaje;
    private int id;
    private final ObservableList<Listar> listaDatos;
    private final ObservableList<Listar> filtrarDatos;
    private final ObservableList<Autor> listaAutores;
    private final ObservableList<Autor> filtrarAutores;
    private final Conexion con;

    public EditarEMAController() {

        con = new Conexion();
        filtrarDatos = FXCollections.observableArrayList();
        listaDatos = FXCollections.observableArrayList();
        listaDatos.addListener(new ListChangeListener<Listar>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Listar> change) {
                updateFilteredData();
            }
        });

        filtrarAutores = FXCollections.observableArrayList();
        listaAutores = FXCollections.observableArrayList();
        listaAutores.addListener(new ListChangeListener<Autor>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Autor> change) {
                updateFilteredData();
            }
        });

    }

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;
    }

    public void setVentanaPrincipal(Sabga ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
    }

    @FXML
    public void guardarCambios(ActionEvent evento) {
        
        edicion();
    }
    
    @FXML
    public void eliminar(ActionEvent evento){
    
       eliminar();
    }
    
    private void eliminar(){
        
         if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                eliminacionAutor();
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                obtenerId("SELECT id_editorial FROM tbl_EDITORIAL WHERE nombre_editorial=", "'" + nombre + "'", "id_editorial");
                eliminarOtros("{ CALL editarEditorial(?,?,?,?) }", null, 2);
                campoEditorial.setText(null);
                listarDatos("SELECT * FROM tbl_EDITORIAL", "nombre_editorial");
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                obtenerId("SELECT id_materia FROM tbl_MATERIA WHERE nombre_materia=", "'" + nombre + "'", "id_materia");
                eliminarOtros("{ CALL editarMateria(?,?,?,?) }", null, 2);
                campoMateria.setText(null);
                listarDatos("SELECT * FROM tbl_MATERIA", "nombre_materia");
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
                obtenerId("SELECT id_tipo_material FROM tbl_TIPO_MATERIAL WHERE tipo_material=", "'" + nombre + "'", "id_tipo_material");
                eliminarOtros("{ CALL editarTipoMaterial(?,?,?,?) }", null, 2);
                campoTipoMaterial.setText(null);
                listarDatos("SELECT * FROM tbl_TIPO_MATERIAL", "tipo_material");
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
                obtenerId("SELECT id_clase_material FROM tbl_CLASE_MATERIAL WHERE clase_material=", "'" + nombre + "'", "id_clase_material");
                eliminarOtros("{ CALL editarClaseMaterial(?,?,?,?) }", null, 2);
                campoClaseMaterial.setText(null);
                listarDatos("SELECT * FROM tbl_CLASE_MATERIAL", "clase_material");
            }
            resetear();
       }
       else{
             Utilidades.mensaje(null, "Debe seleccionar un item de la lista", "Para eliminar un item", "Seleccionar");
       }    
    
        
    }
    
    private void eliminacionAutor(){
      
        try {
            eliminarAutor();
            if (mensaje != null) {
                Utilidades.mensajeAdvertencia(null, mensaje, "Error al eliminar el autor", "Error Eliminar Autor");
            } else {
                campoNombreAutor.setText(null);
                campoApellidosAutor.setText(null);
                llenarAutores();
                Utilidades.mensaje(null, "El autor se ha eliminado correctamente", "Eliminado Autor", "Eliminación Exitosa");
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error eliminar autor", "Error Eliminar Autor");
        }
    }
    
    private void eliminarAutor() throws SQLException{
    
        obtenerIdAutor();
        
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarAutor(?,?,?,?,?) }");

            con.getProcedimiento().setInt("id", id);
            con.getProcedimiento().setInt("opcion", 4);
            con.getProcedimiento().setString("nombre", null);
            con.getProcedimiento().setString("apellidos", null);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de eliminar el autor", "Error Eliminar Autor");  

        } finally {
            con.desconectar();
        }
        
    }
   
    private void eliminarOtros(String procedimiento, String campos, int seleccion){
        
        try {
                guardarEdicion(procedimiento, campos , seleccion);
                if (mensaje != null) {
                    Utilidades.mensajeAdvertencia(null, mensaje, "Error al eliminar la selección", "Error Guardar Cambios");
                } else {                    
                    Utilidades.mensaje(null, "La selección se ha eliminado correctamente", "Eliminado Selección", "Actualización Exitosa");
                }
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al actualizar la información", "Error Guardar Cambios");
            }        
    }
    
    private void edicionAutor(int seleccion){
      
        try {
            guardarEdicion(seleccion);
            if (mensaje != null) {

                Utilidades.mensajeAdvertencia(null, mensaje, "Error al editar el autor", "Error Guardar Cambios Autor");
            } else {
                Utilidades.mensaje(null, "Los cambios se han guardado correctamente", "Editando Autor", "Actualizacion Exitosa");
            }
        } catch (SQLException ex) {

            Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de actualizar la información del autor", "Error Guardar Cambios Autor");
        }
    }
    
     public void edicion() {
         
        ConfirmarMaterial verificar = new ConfirmarMaterial();
        
        validarCampos();
       
        if (acordeonAutor.isExpanded()) {
            if (verificar.confirmarNuevoAutor(campoNombreAutor.getText(), campoApellidosAutor.getText())) {
                obtenerIdAutor();
                editar();                
            }
        } else if (acordeonEditorial.isExpanded()) {
            if (verificar.confirmarNuevaEditorial(campoEditorial.getText())) {
                obtenerId("SELECT id_editorial FROM tbl_EDITORIAL WHERE nombre_editorial=", "'" + nombre + "'", "id_editorial");
                editarOtros(campoEditorial.getText().trim(), "{ CALL editarEditorial(?,?,?,?) }",campoEditorial.getText().trim(), 1);               
            }

        } else if (acordeonMateria.isExpanded()) {
            if (verificar.confirmarNuevaMateria(campoMateria.getText())) {
                obtenerId("SELECT id_materia FROM tbl_MATERIA WHERE nombre_materia=", "'" + nombre + "'", "id_materia");
                editarOtros(campoMateria.getText().trim(), "{ CALL editarMateria(?,?,?,?) }",campoMateria.getText().trim(), 1);  
            }

        } else if (acordeonTipo.isExpanded()) {
            if (verificar.confirmarNuevoTipoMaterial(campoTipoMaterial.getText())) {
                obtenerId("SELECT id_tipo_material FROM tbl_TIPO_MATERIAL WHERE tipo_material=", "'" + nombre + "'", "id_tipo_material");
                editarOtros(campoTipoMaterial.getText().trim(), "{ CALL editarTipoMaterial(?,?,?,?) }",campoTipoMaterial.getText().trim(), 1);  
            }

        } else if (acordeonClase.isExpanded()) {
            if (verificar.confirmarNuevaClaseMaterial(campoClaseMaterial.getText())) {
                obtenerId("SELECT id_clase_material FROM tbl_CLASE_MATERIAL WHERE clase_material=", "'" + nombre + "'", "id_clase_material");
                editarOtros(campoClaseMaterial.getText().trim(), "{ CALL editarClaseMaterial(?,?,?,?) }",campoClaseMaterial.getText().trim(), 1);
            }
        }
        resetear();
        listar(null);
    }
    
    public void editarOtros(String campo, String procedimiento, String campos ,int seleccion ){
        
           if(!nombre.equalsIgnoreCase(campo)){
                try {
                    guardarEdicion(procedimiento, campos , seleccion);
                    if (mensaje != null) {

                        Utilidades.mensajeAdvertencia(null, mensaje, "Error al editar la selección", "Error Guardar Cambios");
                    } else {
                        Utilidades.mensaje(null, "Los cambios se han guardado correctamente", "Editando Selección", "Actualización Exitosa");
                    }
                } catch (SQLException ex) {

                    Utilidades.mensajeError(null, ex.getMessage(), "Error al actualizar la información", "Error Guardar Cambios");
                }            
            }else {
                Utilidades.mensaje(null, "No se han presentado cambios", "Editando Selección", "Editar Selección");
            }
  
    }
    
    public void editar() {

        int opcion;

        if (!nombre.equalsIgnoreCase(campoNombreAutor.getText().trim()) && !apellido.equalsIgnoreCase(campoApellidosAutor.getText().trim())) {
            opcion = 3;
            edicionAutor(opcion);
        } else if (!nombre.equalsIgnoreCase(campoNombreAutor.getText().trim())) {
            opcion = 1;
            edicionAutor(opcion);
        } else if (!apellido.equalsIgnoreCase(campoApellidosAutor.getText().trim())) {
            opcion = 2;
            edicionAutor(opcion);
        } else {

            Utilidades.mensaje(null, "No se han presentado cambios", "Editando Autor", "Ediatar Autor");
        }

    }
    
    public void guardarEdicion(String procedimiento, String campo ,int seleccion) throws SQLException{
    
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento(procedimiento);

            con.getProcedimiento().setInt("id", id);
            con.getProcedimiento().setInt("opcion", seleccion);
            con.getProcedimiento().setString("nombre", campo);
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de editar la selección ", "Error");  

        } finally {
            con.desconectar();
        }
    }
    
    public void guardarEdicion(int seleccion) throws SQLException{
    
        try {

            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL editarAutor(?,?,?,?,?) }");

            con.getProcedimiento().setInt("id", id);
            con.getProcedimiento().setInt("opcion", seleccion);
            con.getProcedimiento().setString("nombre", campoNombreAutor.getText().trim());
            con.getProcedimiento().setString("apellidos", campoApellidosAutor.getText().trim());
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);

            con.getProcedimiento().execute();
            con.getConexion().commit();
            mensaje = con.getProcedimiento().getString("mensaje");

        } catch (SQLException e) {

            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de editar el autor", "Error Editar Autor");  

        } finally {
            con.desconectar();
        }    
    }

    public void obtenerId(String consulta, String nombre, String columna) {

        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta + nombre));
            
            if (con.getResultado().first()) {
                id = con.getResultado().getInt(columna);
            }

        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } finally {
            con.desconectar();
        }

    }

    public void obtenerIdAutor() {

        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT id_autor FROM tbl_AUTOR WHERE nombre_autor= '" + nombre + "' AND apellidos_autor= '" + apellido + "'"));

            if (con.getResultado().first()) {

                id = con.getResultado().getInt("id_autor");
            }

        } catch (SQLException ex) {

            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");

        } finally {
            con.desconectar();
        }

    }

    public void validarCampos() {

        ValidarMaterial validar = new ValidarMaterial();

        if (acordeonAutor.isExpanded()) {
            validar.validarNuevoAutor(campoNombreAutor.getText(), campoApellidosAutor.getText());
            validarNombreAutor.setText(validar.getErrorNombreAutor());
            validarApellidosAutor.setText(validar.getErrorApellidosAutor());

        } else if (acordeonEditorial.isExpanded()) {
            validar.validarNuevaEditorial(campoEditorial.getText());
            validarEditorial.setText(validar.getErrorEditorial());

        } else if (acordeonMateria.isExpanded()) {
            validar.validarNuevaMateria(campoMateria.getText());
            validarMateria.setText(validar.getErrorNombreMateria());

        } else if (acordeonTipo.isExpanded()) {
            validar.validarTipoMaterial(campoTipoMaterial.getText());
            validarTipoMaterial.setText(validar.getErrorTipoMaterial());

        } else if (acordeonClase.isExpanded()) {
            validar.validarClaseMaterial(campoClaseMaterial.getText());
            validarClaseMaterial.setText(validar.getErrorClaseMaterial());
        }
    }

    @FXML
    public void listar(ActionEvent evento) {

        campoFiltrar.setText(null);
        resetear();
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            tablaAutores();
            llenarAutores();
            campoFiltrar.setPromptText("Buscar Autor");
            btnEliminar.setText("Eliminar Autor");
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            listarDatos("SELECT * FROM tbl_EDITORIAL", "nombre_editorial");
            campoFiltrar.setPromptText("Buscar Editorial");
            btnEliminar.setText("Eliminar Editorial");
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            listarDatos("SELECT * FROM tbl_MATERIA", "nombre_materia");
            campoFiltrar.setPromptText("Buscar Materia");
            btnEliminar.setText("Eliminar Materia");
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            listarDatos("SELECT * FROM tbl_TIPO_MATERIAL", "tipo_material");
            campoFiltrar.setPromptText("Buscar Tipo de Material");
            btnEliminar.setText("Eliminar Tipo");
        }
         
        if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
            listarDatos("SELECT * FROM tbl_CLASE_MATERIAL", "clase_material");
            campoFiltrar.setPromptText("Buscar Clase de Material");
            btnEliminar.setText("Eliminar Clase");
        }
      
    }

    public void listarDatos(String tabla, String consulta) {

        filtrarDatos.removeAll(filtrarDatos);
        listaDatos.removeAll(listaDatos);
        filtrarDatos.addAll(listaDatos);

        try {

            con.conectar();
            con.setResultado(con.getStatement().executeQuery(tabla));

            while (con.getResultado().next()) {

                listaDatos.add(new Listar(con.getResultado().getString(consulta)));
            }

            con.desconectar();
            clmnNombre.setCellValueFactory(new PropertyValueFactory<Listar, String>("nombre"));
            tablaResultados.getColumns().remove(clmnApellido);
            clmnNombre.setPrefWidth(510);
            tablaResultados.setEditable(true);
            tablaResultados.setItems(filtrarDatos);

        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    }

    @FXML
    public void mapearDatos() {

        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            acordeonAutor.setExpanded(true);
            campoNombreAutor.setText(filtrarAutores.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor());
            campoApellidosAutor.setText(filtrarAutores.get(tablaResultados.getSelectionModel().getSelectedIndex()).getApellidosAutor());
            campoNombreAutor.setDisable(false);
            campoApellidosAutor.setDisable(false);
            
            nombre = filtrarAutores.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor();
            apellido = filtrarAutores.get(tablaResultados.getSelectionModel().getSelectedIndex()).getApellidosAutor();
            
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            acordeonEditorial.setExpanded(true);
            campoEditorial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
            campoEditorial.setDisable(false);
            
            nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();         
            // campoEditorial.setText(listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).toString());
            //  System.out.println(listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()));
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            acordeonMateria.setExpanded(true);
            campoMateria.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
            campoMateria.setDisable(false);
            
            nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            acordeonTipo.setExpanded(true);
            campoTipoMaterial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
            campoTipoMaterial.setDisable(false);
            
            nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
            acordeonClase.setExpanded(true);
            campoClaseMaterial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
            campoClaseMaterial.setDisable(false);
            
            nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();
        }
    }

    public void tablaAutores() {

        clmnNombre.setCellValueFactory(new PropertyValueFactory<Listar, String>("nombreAutor"));
        clmnNombre.setPrefWidth(240);
        clmnApellido = new TableColumn("Apellido");
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
        clmnApellido.setPrefWidth(270);
        tablaResultados.getColumns().add(clmnApellido);
        tablaResultados.setEditable(true);
        tablaResultados.setItems(filtrarAutores);

    }

    public void llenarAutores() {

        filtrarAutores.removeAll(filtrarAutores);
        listaAutores.removeAll(listaAutores);
        filtrarAutores.addAll(listaAutores);

        try {

            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_AUTOR"));

            while (con.getResultado().next()) {

                listaAutores.add(new Autor(con.getResultado().getString("nombre_autor"), con.getResultado().getString("apellidos_autor")));
            }
            con.desconectar();
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }

    }

    private void updateFilteredData() {

        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {

            filtrarAutores.clear();

            for (Autor a : listaAutores) {

                if (matchesFilterAutor(a)) {
                    filtrarAutores.add(a);
                }
            }

            reapplyTableSortOrderAutor();
        } else {
            filtrarDatos.clear();

            for (Listar p : listaDatos) {

                if (matchesFilter(p)) {
                    filtrarDatos.add(p);
                }
            }
            reapplyTableSortOrder();
        }
    }

    private boolean matchesFilter(Listar nombre) {

        String filterString = campoFiltrar.getText();

        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (nombre.getNombre().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false;
    }

    private boolean matchesFilterAutor(Autor autor) {

        String filterString = campoFiltrar.getText();

        if (filterString == null || filterString.isEmpty()) {
            // No filter --> Add all.
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (autor.getNombreAutor().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (autor.getApellidosAutor().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false; // Does not match
    }

    private void reapplyTableSortOrder() {

        ArrayList<TableColumn<Listar, ?>> sortOrder = new ArrayList<>(tablaResultados.getSortOrder());
        tablaResultados.getSortOrder().clear();
        tablaResultados.getSortOrder().addAll(sortOrder);
    }

    private void reapplyTableSortOrderAutor() {

        ArrayList<TableColumn<Autor, ?>> sortOrder = new ArrayList<>(tablaResultados.getSortOrder());
        tablaResultados.getSortOrder().clear();
        tablaResultados.getSortOrder().addAll(sortOrder);
    }

    @FXML
    public void cancelar(ActionEvent evento) {

        campoNombreAutor.clear();
        campoApellidosAutor.clear();
        campoMateria.clear();
        campoTipoMaterial.clear();
        campoClaseMaterial.clear();

    }

    @FXML
    private void borrarCampo(ActionEvent event) {
        campoFiltrar.setText("");
        btnBorrar.setVisible(false);
    }

    private void mostrarBoton() {

        if (campoFiltrar.getText() == null || campoFiltrar.getText().isEmpty()) {
            btnBorrar.setVisible(false);
        } else {
            btnBorrar.setVisible(true);
        }
    }
    
    private void resetear(){
        
        campoNombreAutor.setText(null);
        campoNombreAutor.setDisable(true);
        campoApellidosAutor.setText(null);
        campoApellidosAutor.setDisable(true);
        campoEditorial.setText(null);
        campoEditorial.setDisable(true);
        campoMateria.setText(null);
        campoMateria.setDisable(true);
        campoTipoMaterial.setText(null);
        campoTipoMaterial.setDisable(true);
        campoClaseMaterial.setText(null);
        campoClaseMaterial.setDisable(true);
    
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnBorrar.setVisible(false);
        resetear();
        campoFiltrar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                mostrarBoton();
                updateFilteredData();
            }
        });
    }
}
