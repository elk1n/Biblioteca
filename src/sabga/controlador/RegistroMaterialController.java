
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
import java.util.regex.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.HBox;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sabga.configuracion.Dialogo;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.configuracion.Utilidades;
import sabga.modelo.Autor;
import sabga.modelo.Validacion;


/**
 * @author Elk1n
 */

public class RegistroMaterialController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private Dialogo dialogo;
    private AutoFillTextBox buscarAutor, buscarMateria, buscarMateriaOM;
    private Validacion validar;
    
    private ObservableList data; 
    private ObservableList <Autor> autores;
     
    @FXML
    public HBox hboxAutor, hboxMaterias, hboxMateriasOM;
    @FXML
    private AnchorPane contenedorAutores;
    @FXML
    public TableView tablaAutores;    
    @FXML
    public TableColumn clmnNombre, clmnApellidos;    
       
    @FXML private Label validarCodigo, validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas,
                        validarEjemplares, validarEditorial, validarClaseMaterial, validarAutor, validarAutor10, validarMateria, validarMateria10,
                        validarMateriaOM10, validarTipoMaterialOM, validarClaseMaterialOM, validarCodigoMaterialOM, validarNumeroClasificacionOM,
                        validarTituloOM, validarMateriaOM, validarNumeroCopiasOM;
    
    @FXML private TextField campoCodigoMaterial, campoNumeroClasificacion, campoTitulo, campoAnioPublicacion, campoPublicacion, 
                            campoNumeroPaginas, campoEjemplares, campoEditorial, campoCodigoMaterialOM, campoAutor, campoMateria,
                            campoNumeroClasificacionOM, campoTituloOM, campoMateriaOM, campoNumeroCopias;
    
    @FXML private ComboBox comboClaseMaterial, comboClaseMaterialOM, comboTipoMaterial;
       
    
    String[] s = new String[]{"apple","ball","cat","doll","elephant","arbol","amazonas","arcade","años","apesta","animal","añora","alejar",
            "fight","georgeous","height","ice","jug","apuesta","acortar",
             "aplogize","bank","call","done","ego",
             "finger","giant","hollow","internet","jumbo",
             "kilo","lion","for","length","primary","stage",
             "scene","zoo","jumble","auto","text",
            "root","box","items","hip-hop","himalaya","nepal",
            "kathmandu","kirtipur","everest","buddha","epic","hotel"};
    
    public RegistroMaterialController(){
        
        dialogo = new Dialogo();
        buscarAutor = new AutoFillTextBox();
        buscarMateria = new AutoFillTextBox();
        buscarMateriaOM = new AutoFillTextBox();
        data = FXCollections.observableArrayList();
        autores = FXCollections.observableArrayList();
        validar = new Validacion();
   
    }
        
    @FXML
    public void cargarNombreApellidoAutor(ActionEvent evento){
            
        if (validar.validarCampoTexto(buscarAutor.getText(), 300)) {

            obtenerAutor();

        } else {
            Utilidades.mensajeAdvertencia(null, "Debe buscar y seleccionar un autor", "Para adicionar un autor a la lista", "Seleccionar Autor");
        }
    }
     
    public void obtenerAutor(){

        autores.add(new Autor(buscarAutor.getText(), buscarAutor.getText()));
        contenedorAutores.setPrefHeight(contenedorAutores.getPrefHeight()+40);
        tablaAutores.setPrefHeight(tablaAutores.getPrefHeight()+40);     
  
    }
    
    public void removerAutor(){
        
       if(tablaAutores.getSelectionModel().getSelectedItem()!=null){
           
           autores.remove(tablaAutores.getSelectionModel().getSelectedIndex());  
       }
       else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar uno de la lista", "Pare remover un autor", "Remover Autor");
       }
                                                   
    }
    
    @FXML 
    public void  preparaTablaAutores(){
    
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor,String>("nombreAutor"));
        clmnApellidos.setCellValueFactory(new PropertyValueFactory<Autor,String>("apellidosAutor"));
        tablaAutores.setEditable(true);	
        tablaAutores.setItems(autores);

    }
    
    @FXML
    public void validarCampos(ActionEvent evento){
                        
       ValidarMaterial validarMaterial = new ValidarMaterial(campoCodigoMaterial.getText(), campoNumeroClasificacion.getText(), campoTitulo.getText(),
                                                    campoAnioPublicacion.getText(), campoPublicacion.getText(), campoNumeroPaginas.getText(),
                                                    campoEjemplares.getText(), campoEditorial.getText(),campoAutor.getText(),
                                                    campoMateria.getText(), comboClaseMaterial.getSelectionModel().getSelectedItem());
       
       validarMaterial.validarNuevoMaterial();
       validarCodigo.setText(validarMaterial.getErrorCodigoMaterial());
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
           
        preparaTablaAutores();

        for(int j=0; j<s.length; j++){
                data.add(s[j]);
            }
       
        buscarAutor.setPrefSize(350, 30);
        buscarMateria.setPrefSize(350, 30);
        buscarMateriaOM.setPrefSize(350, 30);
        buscarAutor.setData(data);
        buscarMateria.setData(data);
        buscarMateriaOM.setData(data);
        
        buscarAutor.setListLimit(10);     
        buscarMateria.setListLimit(10);
        
        buscarMateriaOM.setListLimit(10);
        
        hboxAutor.getChildren().add(buscarAutor);
        hboxMaterias.getChildren().add(buscarMateria);
        hboxMateriasOM.getChildren().add(buscarMateriaOM);
  
    }    
    
}
