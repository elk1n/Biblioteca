
package sabga.controlador;

import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sabga.configuracion.Dialogo;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.configuracion.Utilidades;
import sabga.modelo.Autor;
import sabga.modelo.Materia;
import sabga.modelo.Validacion;

/**
 * @author Elk1n
 */

public class RegistroMaterialController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private Dialogo dialogo;
    private AutoFillTextBox buscarAutor, buscarMateria, buscarMateriaOM, buscarEditorial;
    private Validacion validar;
    
    private ObservableList data; 
    private ObservableList<Autor> autores;
    private ObservableList<Materia> materias;
    private ObservableList<Materia> materiasOM;
     
    @FXML
    private HBox hboxAutor, hboxMaterias, hboxMateriasOM, hboxEditorial;
    @FXML
    private AnchorPane contenedorAutores, contenedorMaterias, contenedorMateriasOM;
    @FXML
    private TableView tablaAutores, tablaMaterias, tablaMateriasOM;    
    @FXML
    private TableColumn clmnNombre, clmnApellidos, clmnNombreMateria, clmnNombreMateriaOM;    
       
    @FXML private Label validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas, validarEjemplares, 
                        validarEditorial, validarClaseMaterial, validarAutor, validarMateria, validarTipoMaterialOM, validarClaseMaterialOM, 
                        validarCodigoMaterialOM, validarNumeroClasificacionOM, validarTituloOM, validarMateriaOM, validarNumeroCopiasOM;
    
    @FXML private TextField campoNumeroClasificacion, campoTitulo, campoAnioPublicacion, campoPublicacion, 
                            campoNumeroPaginas, campoEjemplares, campoEditorial, campoCodigoMaterialOM, campoAutor, campoMateria,
                            campoNumeroClasificacionOM, campoTituloOM, campoMateriaOM, campoNumeroCopias;
    
    @FXML private ComboBox comboClaseMaterial, comboClaseMaterialOM, comboTipoMaterial;
       
    
    String[] s = new String[]{"apple","ball","cat","doll","elephant","arbol","amazonas","arcade","años","apesta","animal","añora","alejar",
            "fight","georgeous","height","ice","jug","apuesta","acortar","alcanzar","Alicante","Arroz",
             "apologize","bank","call","done","ego",
             "finger","giant","hollow","internet","jumbo",
             "kilo","lion","for","length","primary","stage",
             "scene","zoo","jumble","auto","text",
            "root","box","items","hip-hop","himalaya","nepal","Archivo",
            "kathmandu","kirtipur","everest","buddha","epic","hotel"};
    
    public RegistroMaterialController(){
        
        dialogo = new Dialogo();
        buscarAutor = new AutoFillTextBox();
        buscarMateria = new AutoFillTextBox();
        buscarMateriaOM = new AutoFillTextBox();
        buscarEditorial = new AutoFillTextBox();
        
        data = FXCollections.observableArrayList();
        autores = FXCollections.observableArrayList();
        materias = FXCollections.observableArrayList();
        materiasOM = FXCollections.observableArrayList();
        validar = new Validacion();
   
    }
    
    public void cargarNombreMateriaOM() {

        if (validar.validarCampoTexto(buscarMateriaOM.getText(), 90)) {

            obtenerMateriaOM();

        } else {
            Utilidades.mensajeAdvertencia(null, "Debe buscar y seleccionar una materia", "Para adicionar una materia a la lista", "Seleccionar Materia");
        }

    }

    public void obtenerMateriaOM() {

        materiasOM.add(new Materia(buscarMateriaOM.getText()));
        contenedorMateriasOM.setPrefHeight(contenedorMateriasOM.getPrefHeight() + 25);
        tablaMateriasOM.setPrefHeight(tablaMateriasOM.getPrefHeight() + 25);

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

        materias.add(new Materia(buscarMateria.getText()));
        contenedorMaterias.setPrefHeight(contenedorMaterias.getPrefHeight()+25);
        tablaMaterias.setPrefHeight(tablaMaterias.getPrefHeight()+25);     
  
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

        autores.add(new Autor(buscarAutor.getText(), buscarAutor.getText()));
        contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight()+25);
        tablaAutores.setPrefHeight(tablaAutores.getPrefHeight()+25);     
  
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
    public void  prepararTablas(){
    
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
    public void validarCampos(ActionEvent evento){
                        
       ValidarMaterial validarMaterial = new ValidarMaterial(campoNumeroClasificacion.getText(), campoNumeroClasificacion.getText(), campoTitulo.getText(),
                                                    campoAnioPublicacion.getText(), campoPublicacion.getText(), campoNumeroPaginas.getText(),
                                                    campoEjemplares.getText(), campoEditorial.getText(),campoAutor.getText(),
                                                    campoMateria.getText(), comboClaseMaterial.getSelectionModel().getSelectedItem());
       
       validarMaterial.validarNuevoMaterial();
       validarClasificacion.setText(validarMaterial.getErrorCodigoMaterial());
       validarClasificacion.setText(validarMaterial.getErrorCodigoClasificacion());
       validarTitulo.setText(validarMaterial.getErrorTitulo());
       validarAnioPublicacion.setText(validarMaterial.getErrorAnioPublicacion());
       validarPublicacion.setText(validarMaterial.getErrorPublicacion());
       validarPaginas.setText(validarMaterial.getErrorNumeroPaginas());
       validarEjemplares.setText(validarMaterial.getErrorNumeroEjemplares());
       validarEditorial.setText(validarMaterial.getErrorEditorial());
       validarMateria.setText(validarMaterial.getErrorMateria());
       validarAutor.setText(validarMaterial.getErrorAutor());
       validarClaseMaterial.setText(validarMaterial.getErrorClaseMaterial());
       
   }
   
    @FXML
    public void validarCamposOM(ActionEvent evento){
            
        ValidarMaterial validarMaterialOM = new ValidarMaterial(campoCodigoMaterialOM.getText(), campoNumeroClasificacionOM.getText(), campoTituloOM.getText(),
                                                      campoMateriaOM.getText(), comboTipoMaterial.getSelectionModel().getSelectedItem(),
                                                      comboClaseMaterialOM.getSelectionModel().getSelectedItem(), campoNumeroCopias.getText());
        
        validarMaterialOM.validarMaterialOM();
        validarCodigoMaterialOM.setText(validarMaterialOM.getErrorCodigoMaterial());
        validarNumeroClasificacionOM.setText(validarMaterialOM.getErrorCodigoClasificacion());
        validarTituloOM.setText(validarMaterialOM.getErrorTitulo());
        validarMateriaOM.setText(validarMaterialOM.getErrorMateria());
        validarTipoMaterialOM.setText(validarMaterialOM.getErrorTipoMaterial());
        validarClaseMaterialOM.setText(validarMaterialOM.getErrorClaseMaterial());
        validarNumeroCopiasOM.setText(validarMaterialOM.getErrorNumeroEjemplares());
    
    }
    
    @FXML
    public void validarNumeros(KeyEvent evento){
      
        if(validar.getDesencadenador(evento).equals(campoAnioPublicacion.getId())){
            
              validar.validarNumeros(campoAnioPublicacion.getText());
              validarAnioPublicacion.setText(validar.getMensajeError());
        }
        
        if(validar.getDesencadenador(evento).equals(campoNumeroPaginas.getId())){
              validar.validarNumeros(campoNumeroPaginas.getText());
              validarPaginas.setText(validar.getMensajeError()); 
        }
        
        if(validar.getDesencadenador(evento).equals(campoEjemplares.getId())){
               validar.validarNumeros(campoEjemplares.getText());
              validarEjemplares.setText(validar.getMensajeError()); 
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
        

        for(int j=0; j<s.length; j++){
                data.add(s[j]);
            }
        data.add("Fabián");
        data.add("Áreas");
        data.add("área");
        data.add("aárea");
        data.add("AÁreas");
        buscarAutor.setPrefSize(350, 30);
        buscarMateria.setPrefSize(350, 30);
        buscarMateriaOM.setPrefSize(350, 30);
        buscarEditorial.setPrefSize(350, 30);
        buscarAutor.getTextbox().setPromptText("Buscar Autor");
        buscarMateria.getTextbox().setPromptText("Buscar Materias");
        buscarMateriaOM.getTextbox().setPromptText("Buscar Materias");
        buscarEditorial.getTextbox().setPromptText("Buscar Editorial");
        
        buscarAutor.setData(data);
        buscarMateria.setData(data);
        buscarMateriaOM.setData(data);
        buscarEditorial.setData(data);
      
        hboxAutor.getChildren().add(buscarAutor);
        hboxMaterias.getChildren().add(buscarMateria);
        hboxMateriasOM.getChildren().add(buscarMateriaOM);
        hboxEditorial.getChildren().add(buscarEditorial);
 
    }    
    
}
