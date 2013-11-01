
package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sabga.configuracion.Dialogo;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.configuracion.Utilidades;
import sabga.atributos.Autor;
import sabga.atributos.Materia;
import sabga.configuracion.Conexion;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Validacion;

/**
 * @author Elk1n
 */

public class RegistroMaterialController implements Initializable, ControlledScreen {

    @FXML
    private HBox hboxAutor, hboxMaterias, hboxMateriasOM, hboxEditorial;
    @FXML
    private AnchorPane contenedorAutores, contenedorMaterias, contenedorMateriasOM;
    @FXML
    private TableView tablaAutores, tablaMaterias, tablaMateriasOM;    
    @FXML
    private TableColumn clmnNombre, clmnApellidos, clmnNombreMateria, clmnNombreMateriaOM;           
    @FXML 
    private Label validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas, validarEjemplares, 
                  validarEditorial, validarClaseMaterial, validarAutor, validarMateria, validarTipoMaterialOM, validarClaseMaterialOM, 
                  validarNumeroClasificacionOM, validarTituloOM, validarMateriaOM, validarNumeroCopiasOM;
    @FXML 
    private TextField txtfCodigo, txtfTitulo, txtfAnioPublicacion, txtfPublicacion, txtfPaginas, txtfEjemplares, txtfCodigoOM,
                            txtfTituloOM, txtfCopias;    
    @FXML 
    private ComboBox comboClaseMaterial, comboClaseMaterialOM, comboTipoMaterial;
    @FXML
    private TitledPane acordeonAutor, acordeonMateria;
    @FXML
    private Button btnNuevoAutor, btnNuevaEditorial, btnNuevaMateria, btnNuevaMateriaOM;
    
    private int idTipoMaterial, idClaseMaterial, idEditorial, material, idClaseMaterialOM, idTipoMaterialOM, materialOM;
    private String mensaje;
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private final Dialogo dialogo;
    private final AutoFillTextBox buscarAutor, buscarMateria, buscarMateriaOM, buscarEditorial;
    private final Validacion validar;
    private ValidarMaterial validarMaterial;
    private ConfirmarMaterial confirmar;
    
    private final Conexion con;
    
    private final ObservableList listaAutores;
    private final ObservableList<Autor> obtenerAutores;
    private final ObservableList listaMaterias;
    private final ObservableList listaEditoriales;
    private final ObservableList<Autor> autores;
    private final ObservableList<Materia> materias;
    private final ObservableList<Materia> materiasOM;
    private final ObservableList listaClaseMaterial;
    private final ObservableList listaClaseMaterialOM;
    private final ObservableList listaTipoMaterial;
    private final ObservableList<Integer> idAutores;
    private final ObservableList<Integer> idMaterias;
    private final ObservableList<Integer> idMateriasOM;
         
    public RegistroMaterialController(){
        
        con = new Conexion();
        dialogo = new Dialogo();
        buscarAutor = new AutoFillTextBox();
        buscarMateria = new AutoFillTextBox();
        buscarMateriaOM = new AutoFillTextBox();
        buscarEditorial = new AutoFillTextBox();
        
        listaMaterias = FXCollections.observableArrayList();
        autores = FXCollections.observableArrayList();
        materias = FXCollections.observableArrayList();
        materiasOM = FXCollections.observableArrayList();
        listaEditoriales = FXCollections.observableArrayList();
        listaAutores = FXCollections.observableArrayList();
        obtenerAutores = FXCollections.observableArrayList();
        listaClaseMaterial = FXCollections.observableArrayList();
        listaClaseMaterialOM = FXCollections.observableArrayList();
        listaTipoMaterial = FXCollections.observableArrayList();
        idAutores = FXCollections.observableArrayList();
        idMaterias = FXCollections.observableArrayList();
        idMateriasOM = FXCollections.observableArrayList();
        validar = new Validacion();
   
    }
    
    private void registroOtroMaterial(){
        
        validarCamposOM();
        confirmar = new ConfirmarMaterial();
        if(confirmar.confirmarOtroMaterial(comboTipoMaterial.getSelectionModel().getSelectedItem(), comboClaseMaterialOM.getSelectionModel().getSelectedItem(),
                                           txtfCodigoOM.getText(), txtfTituloOM.getText(), txtfCopias.getText(), materiasOM)){        
           procedimientoGuardarOtroMaterial();                 
        }
    }
    
    private void guardarLibro(){
    
        validarCampos();
        confirmar = new ConfirmarMaterial();     
        if(confirmar.confirmarNuevoLibro(comboClaseMaterial.getSelectionModel().getSelectedItem(), txtfCodigo.getText(), txtfTitulo.getText(), 
                                         txtfAnioPublicacion.getText(), txtfPublicacion.getText(), txtfPaginas.getText(),txtfEjemplares.getText(),
                                         buscarEditorial.getTextbox().getText(), autores, materias) && listaEditoriales.indexOf(buscarEditorial.getText()) !=-1){                         
            procedimientoGuardarLibro();                    
        }          
    }
   
    private void procedimientoGuardarLibro(){
        
        if (obtenerIdLibro()) {
            try {
                registrarLibro();
                if (mensaje != null) {
                    Utilidades.mensajeError(null, mensaje, "Error al tratar de registrar el libro", "Error Guardar Libro");
                } else {
                    Utilidades.mensaje(null, "El libro se ha registrado correctamente", "Registrando Libro", "Registro Exitoso");
                    codigoBarras(material);
                    resetearVariables();
                    limpiarCamposLibro();
                }
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar el libro", "Error Guardar Libro");
            }
        } else {
            Utilidades.mensajeError(null, "No se ha registrado el libro", "Error al registrar el libro", "Error Guardar Libro");
        }       
    }
    
    private void procedimientoGuardarOtroMaterial(){
        
        if (obtenerIdOM()) {
            try {
                registrarOtroMaterial();
                if (mensaje != null) {
                    Utilidades.mensajeError(null, mensaje, "Error al registrar el material", "Error Guardar Material");
                } else {                    
                    Utilidades.mensaje(null, "El Material se ha registrado correctamente", "Registrando Material", "Registro Exitoso");
                    codigoBarras(materialOM);
                    resetearVariables();
                    limpiarCamposOtros();
                }
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar el material", "Error Guardar Material");
            }
        } else {
            Utilidades.mensajeError(null, "No se ha registrado el material", "Error al registrar el material", "Error Guardar Material");
        }
    }
    
    private void registrarOtroMaterial() throws SQLException{
        
        try {           
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarOtroMaterial(?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("claseMaterial", idClaseMaterialOM);
            con.getProcedimiento().setInt("tipoMaterial", idTipoMaterialOM);
            con.getProcedimiento().setString("codigo", txtfCodigoOM.getText().trim());
            con.getProcedimiento().setString("titulo", txtfTituloOM.getText().trim());
            con.getProcedimiento().setInt("numeroCopias", Integer.parseInt(txtfCopias.getText().trim()));
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("material", Types.INTEGER);
            con.getProcedimiento().execute();           
            mensaje = con.getProcedimiento().getString("mensaje");
            materialOM = con.getProcedimiento().getInt("material");
            
            if(obtenerIdMateriasOM() && llenarTablaDetalleOM()){               
                con.getConexion().commit();
            }
            else{
                con.getConexion().rollback();
                mensaje = "No se ha guardado el material";
            }
        } catch (SQLException e) {
            con.getConexion().rollback();
            mensaje = String.valueOf(e.getErrorCode());
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar el material", "Error Guardar Material");  
        } finally {
            con.desconectar();
        }
    }
    
    private void registrarLibro() throws SQLException{
       
        try {
            con.conectar();
            con.getConexion().setAutoCommit(false);
            con.procedimiento("{ CALL registrarMaterial(?,?,?,?,?,?,?,?,?,?,?) }");
            con.getProcedimiento().setInt("claseMaterial", idClaseMaterial);
            con.getProcedimiento().setInt("tipoMaterial", idTipoMaterial);
            con.getProcedimiento().setInt("editorial", idEditorial);            
            con.getProcedimiento().setString("codigo", txtfCodigo.getText().trim());
            con.getProcedimiento().setString("titulo", txtfTitulo.getText().trim());
            con.getProcedimiento().setString("publicacion", txtfPublicacion.getText().trim());
            con.getProcedimiento().setInt("anioPublicacion", Integer.parseInt(txtfAnioPublicacion.getText().trim()));
            con.getProcedimiento().setInt("numeroPaginas", Integer.parseInt(txtfPaginas.getText().trim()));
            con.getProcedimiento().setInt("cantidadEjemplares", Integer.parseInt(txtfEjemplares.getText().trim()));
            con.getProcedimiento().registerOutParameter("mensaje", Types.VARCHAR);
            con.getProcedimiento().registerOutParameter("material", Types.INTEGER);
            con.getProcedimiento().execute();           
            mensaje = con.getProcedimiento().getString("mensaje");
            material = con.getProcedimiento().getInt("material");
            if(obtenerIdAutoresMaterias() && llenarTablaDetalle()){               
                con.getConexion().commit();                
            }
            else{
                con.getConexion().rollback();
                mensaje = "No se ha guardado el libro";
            }         
        } catch (SQLException e) {
            con.getConexion().rollback();
            mensaje = String.valueOf(e.getErrorCode());
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar el libro", "Error Guardar Libro");  
        } finally {
            con.desconectar();            
        }    
    }
    
    private Boolean obtenerIdLibro(){
        
        String editorial = buscarEditorial.getText();
        idTipoMaterial = obtenerId("SELECT id_tipo_material FROM tbl_TIPO_MATERIAL WHERE ", "tipo_material LIKE('%libro%')", "id_tipo_material");
        idClaseMaterial = obtenerId("SELECT id_clase_material FROM tbl_CLASE_MATERIAL WHERE clase_material =",
                                    "'"+comboClaseMaterial.getSelectionModel().getSelectedItem().toString()+"'","id_clase_material");
        
        if(listaEditoriales.indexOf(editorial)!=-1){
           idEditorial = obtenerId("SELECT id_editorial FROM tbl_EDITORIAL WHERE nombre_editorial =",  "'"+editorial+"'", "id_editorial"); 
        }
        else {
             Utilidades.mensaje(null, "Debe seleccionar una editorial de la lista", "Para agregar una editorial a un libro", "Seleccionar Editorial");
             idEditorial=0;
        }        
       return idTipoMaterial != 0 && idClaseMaterial != 0 && idEditorial !=0;
    }
       
    private Boolean llenarTablaDetalleOM() throws SQLException{
              
          try {            
            con.getConexion().setAutoCommit(false);
            for(Integer id: idMateriasOM){
            con.getStatement().executeUpdate("INSERT INTO tbl_MATERIAL_MATERIA (id_material, id_materia) VALUES ("+materialOM+" ,"+id+")");
            }
            return true;
        } catch (SQLException ex) {
             con.getConexion().rollback();
             Utilidades.mensajeError(null, ex.getMessage(), "No se ha registrado el material\nFavor intente más tarde", "Error");
             return false;              
        }                  
    }
    
    private Boolean llenarTablaDetalle() throws SQLException{
    
       try {
            con.getConexion().setAutoCommit(false);
            for (Integer id : idAutores) {
                con.getStatement().executeUpdate("INSERT INTO tbl_AUTOR_MATERIAL (id_autor, id_material) VALUES (" + id + " ," + material + ")");
            }
            for (Integer idMat : idMaterias) {
                con.getStatement().executeUpdate("INSERT INTO tbl_MATERIAL_MATERIA (id_material, id_materia) VALUES (" + material + " ," + idMat + ")");
            }
            return true;
        } catch (SQLException ex) {
            con.getConexion().rollback();
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
            return false;
        }       
    }
    
    private Boolean obtenerIdMateriasOM() throws SQLException{
            
        try {
            for(Materia mateOM : materiasOM){
                con.setResultado(con.getStatement().executeQuery("SELECT id_materia FROM tbl_MATERIA WHERE nombre_materia= '" + mateOM.getNombreMateria()+ "'"));
                if (con.getResultado().first()) {
                    idMateriasOM.add(con.getResultado().getInt("id_materia"));
                }
            }
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        } 
        return idMateriasOM.size() == materiasOM.size();
    }
    
    private Boolean obtenerIdAutoresMaterias(){
    
            try {
                for (Autor dato : autores) {
                    con.setResultado(con.getStatement().executeQuery("SELECT id_autor FROM tbl_AUTOR WHERE nombre_autor= '" + dato.getNombreAutor() + "' AND apellidos_autor= '" + dato.getApellidosAutor() + "'"));
                    if (con.getResultado().first()) {
                        idAutores.add(con.getResultado().getInt("id_autor"));
                    }
                }
                for (Materia mate : materias) {
                    con.setResultado(con.getStatement().executeQuery("SELECT id_materia FROM tbl_MATERIA WHERE nombre_materia= '" + mate.getNombreMateria() + "'"));
                    if (con.getResultado().first()) {
                        idMaterias.add(con.getResultado().getInt("id_materia"));
                    }
                }
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "No se han obtenido los identificadores de autores o materias\nFavor intente más tarde", "Error");
        }
            return idAutores.size() == autores.size() && idMaterias.size() == materias.size();
    }
    
    private Boolean obtenerIdOM(){
         
        idTipoMaterialOM = obtenerId("SELECT id_tipo_material FROM tbl_TIPO_MATERIAL WHERE tipo_material = ",
                                    "'"+comboTipoMaterial.getSelectionModel().getSelectedItem().toString()+"'", "id_tipo_material");
        idClaseMaterialOM = obtenerId("SELECT id_clase_material FROM tbl_CLASE_MATERIAL WHERE clase_material =", 
                                    "'"+comboClaseMaterialOM.getSelectionModel().getSelectedItem().toString()+"'", "id_clase_material");
        return idTipoMaterialOM != 0 && idClaseMaterialOM != 0;
    }
        
    private int obtenerId(String consulta, String nombre,String columna) {

        int id=0;
        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta + nombre));            
            if (con.getResultado().first()) {
                id = con.getResultado().getInt(columna);                
            }            
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
        return id;
    }
        
    public void llenarAutores() {

        try {

            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_AUTOR ORDER BY nombre_autor, apellidos_autor"));
            while (con.getResultado().next()) {
                obtenerAutores.add(new Autor(con.getResultado().getString("nombre_autor"), con.getResultado().getString("apellidos_autor"),
                                             con.getResultado().getString("id_autor")));
            }
            con.desconectar();            
            for(Autor datos : obtenerAutores){            
                listaAutores.add(datos.toString());
            }                       
        } catch (SQLException ex){         
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    }
    
    public void listarDatos(ObservableList lista, String tabla, String consulta) {

          try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(tabla));
            while (con.getResultado().next()) {
                lista.add(con.getResultado().getString(consulta));
            }
            con.desconectar();     
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }       
    }  
    
    public void cargarNombreMateriaOM() {

        if (validar.validarCampoTexto(buscarMateriaOM.getText(), 90)) {
            obtenerMateriaOM();
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe buscar y seleccionar una materia", "Para adicionar una materia a la lista", "Seleccionar Materia");
        }
    }

    public void obtenerMateriaOM() {
        
        if (listaMaterias.indexOf(buscarMateriaOM.getText()) != -1) {

            if (!verificarDuplicados(materiasOM, buscarMateriaOM.getText())) {
                materiasOM.add(new Materia(listaMaterias.get(listaMaterias.indexOf(buscarMateriaOM.getText())).toString()));
                contenedorMateriasOM.setPrefHeight(contenedorMateriasOM.getPrefHeight() + 25);
                tablaMateriasOM.setPrefHeight(tablaMateriasOM.getPrefHeight() + 25);
                buscarMateriaOM.getTextbox().setText("");
            } else {
                Utilidades.mensaje(null, "La materia ya se encuentra en la lista", "No se puede repetir una materia", "Seleccionar Materia ");
            }
        } else {
            Utilidades.mensaje(null, "La materia debe ser una de la lista", "Para adicionar una materia a la lista", "Seleccionar Materia");
        }
    }
    
    public void removerMateriaOM(){
        
       if(tablaMateriasOM.getSelectionModel().getSelectedItem()!= null){           
            materiasOM.remove(tablaMateriasOM.getSelectionModel().getSelectedIndex());
            contenedorMateriasOM.setPrefHeight(contenedorMateriasOM.getPrefHeight() - 25);
            tablaMateriasOM.setPrefHeight(tablaMateriasOM.getPrefHeight() - 25);
       }
       else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar uno de la lista", "Pare remover una materia", "Remover Materia");
       }                                                   
    } 
    
    @FXML
    public void cargarNombreMateria(ActionEvent evento){
        
         if (validar.validarCampoTexto(buscarMateria.getText(), 90)) {
            obtenerMateria();
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe buscar y seleccionar una materia", "Para adicionar una materia a la lista", "Seleccionar Materia");
        }
    }
       
    public void obtenerMateria(){
               
        if (listaMaterias.indexOf(buscarMateria.getText()) != -1) {
            
            if (!verificarDuplicados(materias, buscarMateria.getText())) {
                materias.add(new Materia(listaMaterias.get(listaMaterias.indexOf(buscarMateria.getText())).toString()));
                contenedorMaterias.setPrefHeight(contenedorMaterias.getPrefHeight() + 25);
                tablaMaterias.setPrefHeight(tablaMaterias.getPrefHeight() + 25);
                buscarMateria.getTextbox().setText("");
            } else {
                Utilidades.mensaje(null, "La materia ya se encuentra en la lista", "No se puede repetir una materia", "Seleccionar Materia ");
                buscarMateria.getTextbox().setText("");
            }
        } else {
            Utilidades.mensaje(null, "La materia debe ser una de la lista", "Para adicionar una materia a la lista", "Seleccionar Materia");
            buscarMateria.getTextbox().setText("");
        }
    }
    
    public void removerMateria(){
        
       if(tablaMaterias.getSelectionModel().getSelectedItem()!=null){
           
            materias.remove(tablaMaterias.getSelectionModel().getSelectedIndex());
            contenedorMaterias.setPrefHeight(contenedorMaterias.getPrefHeight()-25);
            tablaMaterias.setPrefHeight(tablaMaterias.getPrefHeight()-25);  
       }
       else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos una de la lista", "Pare remover una materia", "Remover Materia");
       }
                                                   
    }
     
    @FXML
    public void cargarNombreApellidoAutor(ActionEvent evento) {

        if (validar.validarCampoTexto(buscarAutor.getText(), 300)) {
            obtenerAutor();                      
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe buscar y seleccionar un autor", "Para adicionar un autor a la lista", "Seleccionar Autor");
        }
    }
         
    private Boolean verificarDuplicados(ObservableList<Materia> lista ,String materia){
        
        for(Materia dato: lista){
           if(dato.getNombreMateria().equals(materia)){
               return true;
           }            
        }
        return false;  
    }
    
    private Boolean verificarDuplicados(String datoDe){
    
        for(Autor dato: autores){
           if(dato.toString().equals(datoDe)){
               return true;
           }            
        }
        return false;
    }
        
    public void obtenerAutor(){
        
        if (listaAutores.indexOf(buscarAutor.getText()) != -1) {
            if (!verificarDuplicados(buscarAutor.getText())) {
                autores.add(new Autor(obtenerAutores.get(listaAutores.indexOf(buscarAutor.getText())).getNombreAutor(), 
                                      obtenerAutores.get(listaAutores.indexOf(buscarAutor.getText())).getApellidosAutor(),"0"));
                contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight() + 25);
                tablaAutores.setPrefHeight(tablaAutores.getPrefHeight() + 25);
                buscarAutor.getTextbox().setText("");
            } else {
                Utilidades.mensaje(null,"El autor seleccionado ya se encuentra presente en la lista", "El autor ya se encuentra en la lista", "Seleccionar Autor");
                buscarAutor.getTextbox().setText("");
            }
        } else {
            Utilidades.mensaje(null, "El autor debe estar registrado", "Para adicionar un autor a la lista", "Seleccionar Autor");
            buscarAutor.getTextbox().setText("");
        }    
    }
   
    public void removerAutor(){
        
       if(tablaAutores.getSelectionModel().getSelectedItem()!=null){           
           autores.remove(tablaAutores.getSelectionModel().getSelectedIndex());
           contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight()-25);
           tablaAutores.setPrefHeight(tablaAutores.getPrefHeight()-25); 
       }else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos uno de la lista", "Pare remover un autor", "Remover Autor");
       }                                                
    }
    
    @FXML 
    public void prepararTablas(){
    
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor,String>("nombreAutor"));
        clmnApellidos.setCellValueFactory(new PropertyValueFactory<Autor,String>("apellidosAutor"));
        tablaAutores.setEditable(true);	
        tablaAutores.setItems(autores);
        
        clmnNombreMateria.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
        tablaMaterias.setEditable(true);	
        tablaMaterias.setItems(materias);
        
        clmnNombreMateriaOM.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
        tablaMateriasOM.setEditable(true);	
        tablaMateriasOM.setItems(materiasOM);
        

    }
    
    @FXML
    public void guardarLibro(ActionEvent evento){                              
        guardarLibro();       
    }
    
    @FXML
    public void guardarOtroMaterial(ActionEvent evento){        
        registroOtroMaterial();
    }
    
    public void validarCampos(){
        
         validarMaterial = new ValidarMaterial();
         
         validarMaterial.validarNuevoLibro(comboClaseMaterial.getSelectionModel().getSelectedItem(), txtfCodigo.getText(), txtfTitulo.getText(),
                                           txtfAnioPublicacion.getText(), txtfPublicacion.getText(), txtfPaginas.getText(), txtfEjemplares.getText(),
                                           buscarEditorial.getTextbox().getText(), autores, materias);
         
       validarClaseMaterial.setText(validarMaterial.getErrorClaseMaterial());
       validarClasificacion.setText(validarMaterial.getErrorCodigoClasificacion());
       validarTitulo.setText(validarMaterial.getErrorTitulo());
       validarAnioPublicacion.setText(validarMaterial.getErrorAnioPublicacion());
       validarPublicacion.setText(validarMaterial.getErrorPublicacion());
       validarPaginas.setText(validarMaterial.getErrorNumeroPaginas());
       validarEjemplares.setText(validarMaterial.getErrorNumeroEjemplares());
       validarEditorial.setText(validarMaterial.getErrorEditorial());
       validarMateria.setText(validarMaterial.getErrorMateria());
       validarAutor.setText(validarMaterial.getErrorAutor());
       
       if(!acordeonMateria.isExpanded() && materias.isEmpty()){
           acordeonMateria.setText("Materias   se ha presentado un error!");
       }
       else{
           acordeonMateria.setText("Materias");       
       }       
       if(!acordeonAutor.isExpanded() && autores.isEmpty()){
           acordeonAutor.setText("Autores   se ha presentado un error!");
       }
       else{
           acordeonAutor.setText("Autores");       
       }
            
   }
   
    public void validarCamposOM(){
        
        validarMaterial = new ValidarMaterial();
        
        validarMaterial.validarMaterialOM(comboTipoMaterial.getSelectionModel().getSelectedItem(), comboClaseMaterialOM.getSelectionModel().getSelectedItem(),
                                          txtfCodigoOM.getText(), txtfTituloOM.getText(), txtfCopias.getText(), materiasOM);

        validarTipoMaterialOM.setText(validarMaterial.getErrorTipoMaterial());
        validarClaseMaterialOM.setText(validarMaterial.getErrorClaseMaterial());
        validarNumeroClasificacionOM.setText(validarMaterial.getErrorCodigoClasificacion()); 
        validarTituloOM.setText(validarMaterial.getErrorTitulo());
        validarNumeroCopiasOM.setText(validarMaterial.getErrorNumeroEjemplares());
        validarMateriaOM.setText(validarMaterial.getErrorMateria());
      
    }
    
    @FXML
    public void validarNumeros(KeyEvent evento){
      
        if(validar.getDesencadenador(evento).equals(txtfAnioPublicacion.getId())){     
              validar.validarNumeros(txtfAnioPublicacion.getText());
              validarAnioPublicacion.setText(validar.getMensajeError());
        }
        
        if(validar.getDesencadenador(evento).equals(txtfPaginas.getId())){
              validar.validarNumeros(txtfPaginas.getText());
              validarPaginas.setText(validar.getMensajeError()); 
        }
        
        if(validar.getDesencadenador(evento).equals(txtfEjemplares.getId())){
               validar.validarNumeros(txtfEjemplares.getText());
              validarEjemplares.setText(validar.getMensajeError()); 
        }
        
         if(validar.getDesencadenador(evento).equals(txtfCopias.getId())){
               validar.validarNumeros(txtfCopias.getText());
              validarNumeroCopiasOM.setText(validar.getMensajeError()); 
        }
        
    }
    
    public void cargarCombo(String consulta, String columna, ObservableList lista, ComboBox combo) {
            
        try {   
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(consulta));
            while (con.getResultado().next()) {                
                lista.add(con.getResultado().getObject(columna));               
            }
            combo.setItems(lista);
            con.desconectar(); 
        } catch (SQLException ex) {            
             Utilidades.mensajeError(null, ex.getMessage(), "No se pudo cargar la información de la base de datos\nFavor intente más tarde", "Error"); 
        }
    }
     
    @FXML
    public void dialogoNuevoAutor (ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        btnNuevoAutor.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevoAutor.fxml", "Nuevo Autor", ventanaPrincipal.getPrimaryStage(), null, 1); 
        listaAutores.clear();
        llenarAutores();
        btnNuevoAutor.setDisable(false);
    }
    
    @FXML
    public void dialogoNuevaMateria (ActionEvent evento){
         
        ventanaPrincipal = new Sabga();
        btnNuevaMateria.setDisable(true);
        btnNuevaMateriaOM.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevaMateria.fxml", "Nueva Materia", ventanaPrincipal.getPrimaryStage(), null, 2);
        btnNuevaMateria.setDisable(false);
        btnNuevaMateriaOM.setDisable(false);
        listaMaterias.clear();
        llenarListaMaterias();
    }
    
    @FXML
    public void dialogoNuevaEditorial(ActionEvent evento){
         
         ventanaPrincipal = new Sabga();
         btnNuevaEditorial.setDisable(true);
         dialogo.mostrarDialogo("vista/dialogos/NuevaEditorial.fxml", "Nueva Editorial", ventanaPrincipal.getPrimaryStage(), null,3);
         listaEditoriales.clear();
         llenarListaEditoriales();
         btnNuevaEditorial.setDisable(false);
    }
    
    private void codigoBarras(int id){
        
        ventanaPrincipal = new Sabga();   
        dialogo.dialogoCodigoBarras(ventanaPrincipal.getPrimaryStage(), completarConCeros(id),Utilidades.initCap(txtfTitulo.getText())
                                    ,"Puede guardar el código de barras para imprimirlo, imprimirlo directamento o cerrar este dialogo"
                                            + " y finalizar.", 1);   
    }
          
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    }
    
    public void llenarListaMaterias(){
        listarDatos(listaMaterias, "SELECT nombre_materia FROM tbl_MATERIA", "nombre_materia");
    }
    
    public void llenarListaEditoriales(){
        listarDatos(listaEditoriales, "SELECT nombre_editorial FROM tbl_EDITORIAL", "nombre_editorial");
    }
    
    private String completarConCeros(int numero){
    
        String dato = String.valueOf(numero);
        int longitud = 10-dato.length();
        
            if(longitud<10){
                for(int i=0; i<longitud; i++){
                    dato="0"+dato;
                }            
            }            
        return dato;
    }
       
    private void limpiarCamposLibro(){
    
        txtfCodigo.setText(null);
        txtfTitulo.setText(null);
        txtfAnioPublicacion.setText(null);
        txtfPublicacion.setText(null);
        txtfPaginas.setText(null);
        txtfEjemplares.setText(null);
        buscarEditorial.getTextbox().setText("");
    }
    
    private void limpiarCamposOtros(){
    
            txtfCodigoOM.setText(null);
            txtfTituloOM.setText(null);
            txtfCopias.setText(null);
                    
    }
    
    private void resetearVariables(){
        
        mensaje = null;
        idClaseMaterial = 0;
        idClaseMaterialOM = 0;
        idEditorial = 0;
        idTipoMaterial = 0;
        idTipoMaterialOM = 0;
        idAutores.clear();
        idMaterias.clear();
        idMateriasOM.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            
        prepararTablas();
        llenarListaMaterias();
        llenarListaEditoriales();
        llenarAutores();
        buscarAutor.setPrefSize(350, 30);
        buscarMateria.setPrefSize(350, 30);
        buscarMateriaOM.setPrefSize(350, 30);
        buscarEditorial.setPrefSize(350, 30);
        buscarAutor.getTextbox().setPromptText("Buscar Autor");
        buscarMateria.getTextbox().setPromptText("Buscar Materias");
        buscarMateriaOM.getTextbox().setPromptText("Buscar Materias");
        buscarEditorial.getTextbox().setPromptText("Buscar Editorial");
        
        buscarAutor.setData(listaAutores);
        buscarMateria.setData(listaMaterias);
        buscarMateriaOM.setData(listaMaterias);
        buscarEditorial.setData(listaEditoriales);
      
        hboxAutor.getChildren().add(buscarAutor);
        hboxMaterias.getChildren().add(buscarMateria);
        hboxMateriasOM.getChildren().add(buscarMateriaOM);
        hboxEditorial.getChildren().add(buscarEditorial);
        
        cargarCombo("SELECT * FROM tbl_CLASE_MATERIAL", "clase_material", listaClaseMaterial, comboClaseMaterial);
        cargarCombo("SELECT * FROM tbl_CLASE_MATERIAL", "clase_material", listaClaseMaterialOM, comboClaseMaterialOM);
        cargarCombo("SELECT tipo_material FROM tbl_TIPO_MATERIAL WHERE tipo_material NOT LIKE('%libro%')", "tipo_material", listaTipoMaterial, comboTipoMaterial);
    }    
    
}
