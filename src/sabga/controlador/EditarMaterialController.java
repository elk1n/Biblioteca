
package sabga.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Autor;
import sabga.atributos.Listar;
import sabga.atributos.Material;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 *
 * @author Elk1n
 * 
 */

public class EditarMaterialController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    private final Dialogo dialogo;
    
    @FXML
    private Label validarCodigoClasificacionAC, validarTituloAC, validarAnioPublicacionAC, validarPublicacionAC, validarNumeroPaginasAC,
                        validarEditorialAC, validarEstadoAC, validarAutoresAC, validarMateriasAC;    
    @FXML 
    private TextField txtfCodigoClasificacion, txtfTitulo, txtfAnio, txtfPublicacion, txtfEjemplares, txtfPaginas, txtfHabilitado, txtfInhabilitado,
                        txtfReparacion, txtfFiltrar;
    @FXML 
    private Button  btnBorrar, btnDetalle, btnEditorial, btnAutor, btnMateria;    
    @FXML 
    private ComboBox comboTipoMaterial, comboClaseMaterial, comboMaterial;    
    @FXML 
    private TitledPane acordeonGeneral, acordeonAutores, acordeonMaterias;
    @FXML
    private HBox hboxEditorial, hboxAutores, hboxMaterias;
    @FXML    
    private  Tooltip est;
    @FXML
    private TableView tablaMaterial;
    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase;
    private final AutoFillTextBox editorial, autores, materias ;
    
    private final ObservableList<Material> filtrarMaterial;
    private final ObservableList<Material> listaMaterial;
    private final Consultas consulta;

    
    public EditarMaterialController(){
        dialogo = new Dialogo();       
        consulta = new Consultas();
        editorial = new AutoFillTextBox();
        autores = new AutoFillTextBox();
        materias = new AutoFillTextBox();
        filtrarMaterial = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
        listaMaterial.addListener(new ListChangeListener<Material>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Material> change) {
                updateFilteredData();
            }
        });
    }
    
    public void prueba(){
        consulta.mapearMaterial(1);
    }
    
    @FXML
    private void mapearDatos(){
      
        if (comboMaterial.getSelectionModel().getSelectedItem() != null  && tablaMaterial.getSelectionModel().getSelectedItem()!=null) {

            consulta.mapearMaterial(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
            txtfTitulo.setText(consulta.getTitulo());
            txtfCodigoClasificacion.setText(consulta.getClasificacion());
            txtfAnio.setText(String.valueOf(consulta.getAnio()));
            txtfPublicacion.setText(consulta.getPublicacion());
            txtfEjemplares.setText(String.valueOf(consulta.getEjemplares()));
            txtfPaginas.setText(String.valueOf(consulta.getPaginas()));
            txtfHabilitado.setText(String.valueOf(consulta.getHabilitado()));
            txtfInhabilitado.setText(String.valueOf(consulta.getInhabilitado()));
            txtfReparacion.setText(String.valueOf(consulta.getReparacion()));
            comboTipoMaterial.getSelectionModel().select(consulta.getTipoMaterial());
            comboClaseMaterial.getSelectionModel().select(consulta.getClaseMaterial());
            try {
                editorial.getTextbox().setText(consulta.getEditorial());
            } catch (NullPointerException e) {
            }

        }
        
       
    }
        
    @FXML
    private void listarMaterial(ActionEvent evento){                        
        listar();    
    }
   
    private void listar(){
        
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));        
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));        
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));
        tablaMaterial.setEditable(true);
        filtrarMaterial.clear();
        listaMaterial.clear();
        filtrarMaterial.addAll(listaMaterial);
        listaMaterial.addAll(consulta.getListaMaterial(comboMaterial.getSelectionModel().getSelectedItem().toString()));        
        tablaMaterial.setItems(filtrarMaterial);
                       
    }
    
    private void llenarComboBox(){        
        comboClaseMaterial.setItems(consulta.llenarLista("SELECT clase_material FROM tbl_CLASE_MATERIAL", "clase_material"));
        comboMaterial.setItems(consulta.llenarLista("SELECT tipo_material FROM tbl_TIPO_MATERIAL", "tipo_material"));
        comboTipoMaterial.setItems(comboMaterial.getItems());               
    }
    
     @Override
    public void setScreenParent(ScreensController screenParent) {     
         controlador = screenParent;
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
     public void dialogoNuevaEditorial(ActionEvent evento){        
        ventanaPrincipal = new Sabga();
         btnEditorial.setDisable(true);
         dialogo.mostrarDialogo("vista/dialogos/NuevaEditorial.fxml", "Nueva Editorial", ventanaPrincipal.getPrimaryStage(), null, 3);
         btnEditorial.setDisable(false);
     }
    
    @FXML
    public void dialogoNuevoAutor(ActionEvent evento){        
        ventanaPrincipal = new Sabga();
        btnAutor.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevoAutor.fxml", "Nuevo Autor", ventanaPrincipal.getPrimaryStage(), null, 1);
        btnAutor.setDisable(false);
    }
    
    @FXML
    public void dialogoNuevaMateria(ActionEvent evento){        
        ventanaPrincipal = new Sabga();
        btnMateria.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevaMateria.fxml", "Nueva Materia",ventanaPrincipal.getPrimaryStage(), null, 2);
        btnMateria.setDisable(false);
    }
    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento){        
        ventanaPrincipal = new Sabga();
        btnDetalle.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", ventanaPrincipal.getPrimaryStage(), null, 4);
        btnDetalle.setDisable(false);
    }
    
    @FXML
    public void validarCamposAC(ActionEvent evento){
        /*
        if(comboTipoMaterial.getSelectionModel().getSelectedIndex()==0){
            
            ValidarMaterial validarActualizacion = new ValidarMaterial(campoCodigoClasificacionAC.getText(), campoTituloAC.getText(), campoAnioPublicacionAC.getText(),
                                                         campoPublicacionAC.getText(), campoNumeroPaginasAC.getText(), campoEditorialAC.getText(),
                                                         campoEjemplaresDisponiblesAC.getText(), campoHabilitadoAC.getText(), campoDeshabilitadoAC.getText(),
                                                         campoMantenimientoAC.getText(),campoAutor1AC.getText(), campoAutor2AC.getText(),campoAutor3AC.getText(),
                                                         campoAutor4AC.getText(),campoAutor5AC.getText(),campoAutor6AC.getText(),
                                                         campoAutor7AC.getText(),campoAutor8AC.getText(), campoAutor9AC.getText(), campoAutor10AC.getText(),
                                                         campoMateria1AC.getText(), campoMateria2AC.getText(), campoMateria3AC.getText(), campoMateria4AC.getText(),
                                                         campoMateria5AC.getText(), campoMateria6AC.getText(), campoMateria7AC.getText(), campoMateria8AC.getText(),
                                                         campoMateria9AC.getText(), campoMateria10AC.getText()); 

            validarActualizacion.validarActualizacionMaterial();
            validarCodigoClasificacionAC.setText(validarActualizacion.getErrorCodigoClasificacion());
            validarTituloAC.setText(validarActualizacion.getErrorTitulo());
            validarAnioPublicacionAC.setText(validarActualizacion.getErrorAnioPublicacion());
            validarPublicacionAC.setText(validarActualizacion.getErrorPublicacion());
            validarNumeroPaginasAC.setText(validarActualizacion.getErrorNumeroPaginas());
            validarEditorialAC.setText(validarActualizacion.getErrorEditorial());
            validarEstadoAC.setText(validarActualizacion.getErrorEstado());
            validarAutoresAC.setText(validarActualizacion.getErrorAutor());
            validarMateriasAC.setText(validarActualizacion.getErrorMateria());
            

            if (acordeonMaterias.isExpanded()==false && validarMateriasAC.getText()!=null){

                acordeonMaterias.setText("Materias"+"                se ha encontrado un error!");
            }       
            else{

                acordeonMaterias.setText("Materias");
            }
            if(acordeonAutores.isExpanded()==false && validarAutoresAC.getText()!=null){

                acordeonAutores.setText("Autores"+"                 se ha encontrado un error!");         
            }
            else{
                acordeonAutores.setText("Autores");

           }
            if(acordeonGeneral.isExpanded()==false && errorAcordeon() == false){

                acordeonGeneral.setText("General"+"                 se ha encontrado un error!");        
            }
            else{
                acordeonGeneral.setText("General");
            }

        }
       
        if (comboTipoMaterial.getSelectionModel().getSelectedIndex()==1 || comboTipoMaterial.getSelectionModel().getSelectedIndex()==2 ||
            comboTipoMaterial.getSelectionModel().getSelectedIndex()==3){
            
            ValidarMaterial validarActualizacionOM = new ValidarMaterial(campoCodigoClasificacionAC.getText(), campoTituloAC.getText(),campoEjemplaresDisponiblesAC.getText(),
                                                               campoHabilitadoAC.getText(), campoDeshabilitadoAC.getText(), campoMantenimientoAC.getText(), campoMateria1AC.getText(), 
                                                               campoMateria2AC.getText(), campoMateria3AC.getText(), campoMateria4AC.getText(), campoMateria5AC.getText(), campoMateria6AC.getText(), 
                                                               campoMateria7AC.getText(), campoMateria8AC.getText(),campoMateria9AC.getText(), campoMateria10AC.getText());
            
            validarActualizacionOM.validarActualizacionOM();
            validarCodigoClasificacionAC.setText(validarActualizacionOM.getErrorCodigoClasificacion());
            validarTituloAC.setText(validarActualizacionOM.getErrorTitulo());
            validarEstadoAC.setText(validarActualizacionOM.getErrorEstado());
            validarMateriasAC.setText(validarActualizacionOM.getErrorMateria());
            
            if (acordeonMaterias.isExpanded()==false && validarMateriasAC.getText()!=null){
            
            acordeonMaterias.setText("Materias"+"                se ha encontrado un error!");
            }       
            else {

                acordeonMaterias.setText("Materias");
            }
            if(acordeonGeneral.isExpanded()==false && errorAcordeon() == false){
            
            acordeonGeneral.setText("General"+"                 se ha encontrado un error!");        
            }
            else{
                acordeonGeneral.setText("General");
            }

        }
         */    
    }
   
    private void updateFilteredData() {
      filtrarMaterial.clear();
          
      for (Material m : listaMaterial) {
          if (matchesFilter(m)) {
              filtrarMaterial.add(m);
          }
      }     
      reapplyTableSortOrder();
  }
    
    @FXML
    private void borrarCampo(ActionEvent event) {
        txtfFiltrar.setText("");
        btnBorrar.setVisible(false);
    }
    
    private void mostrarBoton() {

        if (txtfFiltrar.getText() == null || txtfFiltrar.getText().isEmpty()) {
            btnBorrar.setVisible(false);
        } else {
            btnBorrar.setVisible(true);
        }
    }

    private boolean matchesFilter(Material material) {
      String filterString = txtfFiltrar.getText();
      if (filterString == null || filterString.isEmpty()) {
          return true;
      }
      
      String lowerCaseFilterString = filterString.toLowerCase();
      
      if (material.getTitulo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      } else if (material.getCodigo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      } else if (material.getClase().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
          return true;
      }      
      return false;
  }   
    
    private void reapplyTableSortOrder() {
      ArrayList<TableColumn<Material, ?>> sortOrder = new ArrayList<>(tablaMaterial.getSortOrder());
      tablaMaterial.getSortOrder().clear();
      tablaMaterial.getSortOrder().addAll(sortOrder);
  }
     
    public void iniciar(){
        
        llenarComboBox();
        editorial.setPrefSize(250, 30);
        materias.setPrefSize(350, 30);
        autores.setPrefSize(350, 30);
        materias.getTextbox().setPromptText("Materia");
        autores.getTextbox().setPromptText("Autor");
        editorial.getTextbox().setPromptText("Editorial");
        editorial.setData(consulta.llenarLista("SELECT nombre_editorial FROM tbl_EDITORIAL", "nombre_editorial"));
        materias.setData(consulta.llenarLista("SELECT nombre_materia FROM tbl_MATERIA", "nombre_materia"));
        autores.setData(consulta.listaAutores());
        hboxEditorial.getChildren().add(editorial);
        hboxMaterias.getChildren().add(materias);
        hboxAutores.getChildren().add(autores);
        btnBorrar.setVisible(false);
    }
    
    /** 
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        iniciar();
         txtfFiltrar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                mostrarBoton();
                updateFilteredData();
            }
        });
        
        // PRUEBA DE TOOLTIP.....WORKS BY THE WAY :)
        /* Platform.runLater(new Runnable() {@Override 
        public void run() { 
        est= new Tooltip("Esto es una prueba de un Tooltip, esto es otra prueba, esto es otra otra prueba");
         botonNuevaEditorial.setTooltip(est); 
            MenuItem h = new MenuItem("Esto es una prueba de un menú contextual ");
            ContextMenu es = new ContextMenu(h);            
            botonNuevaEditorial.setContextMenu(es);
                    
        }});*/
                
    }    
   
     public boolean errorAcordeon(){
        
        boolean retorno = false; 
        String control = "";
        
        if (validarCodigoClasificacionAC.getText() != null){           
            control += "N";
        }        
        else {
            control += "S";
        }
        if(validarTituloAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarNumeroPaginasAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarEditorialAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarEstadoAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarPublicacionAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarAnioPublicacionAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        
        int auxiliar = control.indexOf("N");
        
       if (auxiliar == -1){
           
           retorno = true;
       }
       else{
           retorno = false;
       }
        return retorno;
    }   
}
