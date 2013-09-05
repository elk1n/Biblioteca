
package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    private int idTipoMaterial, idClaseMaterial, idEditorial, material;
    private String mensaje;
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private Dialogo dialogo;
    private AutoFillTextBox buscarAutor, buscarMateria, buscarMateriaOM, buscarEditorial;
    private Validacion validar;
    private ValidarMaterial validarMaterial;
    private ConfirmarMaterial confirmar;
    
    private Conexion con;
    
    private ObservableList listaAutores;
    private ObservableList<Autor> obtenerAutores;
    private ObservableList listaMaterias;
    private ObservableList listaEditoriales;
    private ObservableList<Autor> autores;
    private ObservableList<Materia> materias;
    private ObservableList<Materia> materiasOM;
    private ObservableList listaClaseMaterial;
    private ObservableList listaClaseMaterialOM;
    private ObservableList listaTipoMaterial;
    private ObservableList<Integer> idAutores;
     
        
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
        validar = new Validacion();
   
    }
    
    
    private void guardarLibro(){
    
        validarCampos();
        confirmar = new ConfirmarMaterial();
        
        if(confirmar.confirmarNuevoLibro(comboClaseMaterial.getSelectionModel().getSelectedItem(), txtfCodigo.getText(), txtfTitulo.getText(), 
                                         txtfAnioPublicacion.getText(), txtfPublicacion.getText(), txtfPaginas.getText(),txtfEjemplares.getText(),
                                         buscarEditorial.getTextbox().getText(), autores, materias) && listaEditoriales.indexOf(buscarEditorial.getText()) !=-1){
        
            obtenerId();
            idEditorial();
            procedimientoGuardarLibro();
            
                            
        }
          
    }
   
    private void procedimientoGuardarLibro(){
        
        try {
                registrarLibro();               
            if(mensaje!=null){                   
                     Utilidades.mensajeError(null, mensaje, "Error al tratar de registrar el libro", "Error Guardar Libro");
                }
                else{
                    Utilidades.mensaje(null, "El libro se ha registrado correctamente", "Registrando Libro", "Registro Exitoso");
                }
            } catch (SQLException ex) {              
                Utilidades.mensajeError(null, ex.getMessage(), "Error al tratar de registrar el libro", "Error Guardar Libro");  
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
            con.getConexion().commit();
            
        } catch (SQLException e) {
            con.getConexion().rollback();
            Utilidades.mensajeError(null, e.getMessage(), "Error al tratar de registrar el libro", "Error Guardar Libro");  
        } finally {
            con.desconectar();
        }
    
    }
    
    private void obtenerId(){
    
        idTipoMaterial = obtenerId("SELECT id_tipo_material FROM tbl_TIPO_MATERIAL WHERE ", "tipo_material LIKE('%libro%')", "id_tipo_material");
        idClaseMaterial = obtenerId("SELECT id_clase_material FROM tbl_CLASE_MATERIAL WHERE clase_material =", "'"+comboClaseMaterial.getSelectionModel().getSelectedItem().toString()+"'",
                                    "id_clase_material");     
    }
    
    private void idEditorial(){
        
        String editorial = buscarEditorial.getText();
        if(listaEditoriales.indexOf(editorial)!=-1){
           idEditorial = obtenerId("SELECT id_editorial FROM tbl_EDITORIAL WHERE nombre_editorial =",  "'"+editorial+"'", "id_editorial"); 
        }
        else {
             Utilidades.mensaje(null, "Debe seleccionar una editorial de la lista", "Para agregar una editorial a un libro", "Seleccionar Editorial");
             idEditorial=0;
        }         
    }
    
    private void llenarTablaDetalle(){
    
        for(Integer id : idAutores){
        
          try {            
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_CURSO"));

            while (con.getResultado().next()) {
                
             //   grado.add(con.getResultado().getObject("curso"));
                
            }
         //   comboGrupo.setItems(grado);
            con.desconectar(); 
        } catch (SQLException ex) {
            
             Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error"); 
        }
        
        }
    
    }
    
    private void obtenerIdAutores(){
    
        for (Autor dato : autores) {

            try {
                con.conectar();
                con.setResultado(con.getStatement().executeQuery("SELECT id_autor FROM tbl_AUTOR WHERE nombre_autor= '" + dato.getNombreAutor() + "' AND apellidos_autor= '" + dato.getApellidosAutor() + "'"));
                if (con.getResultado().first()) {
                    idAutores.add(con.getResultado().getInt("id_autor"));
                }
            } catch (SQLException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
            } finally {
                con.desconectar();
            }
        }
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
        } finally {
            con.desconectar();
        }
        return id;
    }
        
    public void llenarAutores() {

        try {

            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_AUTOR ORDER BY nombre_autor, apellidos_autor"));

            while (con.getResultado().next()) {

                obtenerAutores.add(new Autor(con.getResultado().getString("nombre_autor"), con.getResultado().getString("apellidos_autor")));
            }
            con.desconectar();
            
            for(Autor datos : obtenerAutores){
            
                listaAutores.add(datos.toString());
            }
                       
        } catch (SQLException ex) {
            
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
        
        if(listaMaterias.indexOf(buscarMateriaOM.getText())!=-1){
            
        materiasOM.add(new Materia(listaMaterias.get(listaMaterias.indexOf(buscarMateriaOM.getText())).toString()));
        contenedorMateriasOM.setPrefHeight(contenedorMateriasOM.getPrefHeight() + 25);
        tablaMateriasOM.setPrefHeight(tablaMateriasOM.getPrefHeight() + 25);
        
        }
        else {
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
               
        if(listaMaterias.indexOf(buscarMateria.getText())!=-1){
            
        materias.add(new Materia(listaMaterias.get(listaMaterias.indexOf(buscarMateria.getText())).toString()));
        contenedorMaterias.setPrefHeight(contenedorMaterias.getPrefHeight()+25);
        tablaMaterias.setPrefHeight(tablaMaterias.getPrefHeight()+25);     
        
        }
        else {
            Utilidades.mensaje(null, "La materia debe ser una de la lista", "Para adicionar una materia a la lista", "Seleccionar Materia");
        }
    }
    
    public void removerMateria(){
        
       if(tablaMaterias.getSelectionModel().getSelectedItem()!=null){
           
            materias.remove(tablaMaterias.getSelectionModel().getSelectedIndex());
            contenedorMaterias.setPrefHeight(contenedorMaterias.getPrefHeight()-25);
            tablaMaterias.setPrefHeight(tablaMaterias.getPrefHeight()-25);  
       }
       else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar uno de la lista", "Pare remover una materia", "Remover Materia");
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
     
    public void obtenerAutor(){
        
        if(listaAutores.indexOf(buscarAutor.getText())!=-1){
            
            autores.add(new Autor(obtenerAutores.get(listaAutores.indexOf(buscarAutor.getText())).getNombreAutor(), obtenerAutores.get(listaAutores.indexOf(buscarAutor.getText())).getApellidosAutor()));
            contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight()+25);
            tablaAutores.setPrefHeight(tablaAutores.getPrefHeight()+25);
            
         } else {
            Utilidades.mensaje(null, "El autor debe ser uno de la lista", "Para adicionar un autor a la lista", "Seleccionar Autor");
        }     
  
    }
    
    public void removerAutor(){
        
       if(tablaAutores.getSelectionModel().getSelectedItem()!=null){
           
           autores.remove(tablaAutores.getSelectionModel().getSelectedIndex());
           contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight()-25);
           tablaAutores.setPrefHeight(tablaAutores.getPrefHeight()-25); 
       }
       else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar uno de la lista", "Pare remover un autor", "Remover Autor");
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
        
        validarCamposOM();
    
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
        dialogo.mostrarDialogo("vista/dialogos/NuevoAutor.fxml", "Nuevo Autor", ventanaPrincipal.getPrimaryStage(), null, 1);     
    }
    
    @FXML
    public void dialogoNuevaMateria (ActionEvent evento){
         
        ventanaPrincipal = new Sabga();    
        dialogo.mostrarDialogo("vista/dialogos/NuevaMateria.fxml", "Nueva Materia", ventanaPrincipal.getPrimaryStage(), null, 2);
    }
    
    @FXML
    public void dialogoNuevaEditorial(ActionEvent evento){
         
         ventanaPrincipal = new Sabga();
         dialogo.mostrarDialogo("vista/dialogos/NuevaEditorial.fxml", "Nueva Editorial", ventanaPrincipal.getPrimaryStage(), null,3);
     }
          
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
             
        prepararTablas();
        listarDatos(listaMaterias, "SELECT nombre_materia FROM tbl_MATERIA", "nombre_materia");
        listarDatos(listaEditoriales, "SELECT nombre_editorial FROM tbl_EDITORIAL", "nombre_editorial");
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
