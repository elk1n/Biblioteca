
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Autor;
import sabga.atributos.Materia;
import sabga.atributos.Material;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class EditarMaterialController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    private final Dialogo dialogo; 
    @FXML
    private Label lblEditorial, lblValidarCodigo, lblValidarTitulo, lblValidarAnio, lblValidarPublicacion, lblValidarEditorial, lblValidarEjemplares,
                    lblValidarPaginas, lblValidarAutor, lblValidarMateria;    
    @FXML 
    private TextField txtfCodigoClasificacion, txtfTitulo, txtfAnio, txtfPublicacion, txtfEjemplares, txtfPaginas, txtfHabilitado, txtfInhabilitado,
                        txtfReparacion, txtfFiltrar, txtfBuscar;
    @FXML 
    private Button  btnBorrar, btnDetalle, btnEditorial, btnAutor, btnMateria, btnCodigoBarras, btnBorrarBusqueda;    
    @FXML 
    private ComboBox comboTipoMaterial, comboClaseMaterial, comboMaterial;    
    @FXML 
    private TitledPane acordeonGeneral, acordeonAutores, acordeonMaterias;
    @FXML
    private HBox hboxEditorial, hboxAutores, hboxMaterias;
    @FXML    
    private Tooltip est;
    @FXML
    private TableView tablaMaterial, tablaMaterias, tablaAutores;
    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase, clmnMateria, clmnNombre, clmnApellidos;
    private final AutoFillTextBox editorial, autores, materias ;    
    private final ObservableList<Material> filtrarMaterial;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Autor> listaAutores;
    private final ObservableList<Autor> obtenerAutores;
    private final ObservableList<Materia> listaMaterias;
    private final ObservableList listaBusquedaMaterias, listaBusquedaAutores;
    
    private final Consultas consulta;

    
    public EditarMaterialController(){
        dialogo = new Dialogo();       
        consulta = new Consultas();
        editorial = new AutoFillTextBox();
        autores = new AutoFillTextBox();
        materias = new AutoFillTextBox();
        obtenerAutores = FXCollections.observableArrayList();
        listaBusquedaMaterias = FXCollections.observableArrayList();
        listaBusquedaAutores = FXCollections.observableArrayList();
        filtrarMaterial = FXCollections.observableArrayList();
        listaAutores = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
        listaMaterias = FXCollections.observableArrayList();
        listaMaterial.addListener(new ListChangeListener<Material>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Material> change) {
                updateFilteredData();
            }
        });
    }
       
    @FXML
    public void guardarCambios(ActionEvent evento){
        validarEdicionLibro();
    }
    
    @FXML
    public void adicionarAutor(ActionEvent evento){
        if (!listaMaterial.isEmpty()) {
            if (listaBusquedaAutores.indexOf(autores.getText()) != -1) {
                if (!verificarDuplicados(listaAutores, autores.getText())) {
                    listaAutores.add(new Autor(obtenerAutores.get(listaBusquedaAutores.indexOf(autores.getText())).getNombreAutor(),
                            obtenerAutores.get(listaBusquedaAutores.indexOf(autores.getText())).getApellidosAutor()));
                    autores.getTextbox().setText("");
                } else {
                    Utilidades.mensaje(null, "El autor seleccionado ya se encuentra presente en la lista", "El autor ya se encuentra en la lista", "Seleccionar Autor");
                    autores.getTextbox().setText("");
                }
            } else {
                Utilidades.mensaje(null, "El autor debe estar registrado", "Para adicionar un autor a la lista", "Seleccionar Autor");
                autores.getTextbox().setText("");
            }
        }
    }
    
    @FXML
    public void adicionarMateria(ActionEvent evento){
        if (!listaMaterial.isEmpty()) {
            if (listaBusquedaMaterias.indexOf(materias.getText()) != -1) {
                if (!verificarDuplicados(listaMaterias, materias.getText())) {
                    listaMaterias.add(new Materia(listaBusquedaMaterias.get(listaBusquedaMaterias.indexOf(materias.getText())).toString()));
                    materias.getTextbox().setText("");
                } else {
                    Utilidades.mensaje(null, "La materia ya se encuentra en la lista", "No se puede repetir una materia", "Seleccionar Materia ");
                    materias.getTextbox().setText("");
                }
            } else {
                Utilidades.mensaje(null, "La materia debe ser una de la lista", "Para adicionar una materia a la lista", "Seleccionar Materia");
                materias.getTextbox().setText("");
            }
        }
    }
    
    @FXML
    public void removerAutor(ActionEvent evento){
        if(tablaAutores.getSelectionModel().getSelectedItem()!=null){           
           listaAutores.remove(tablaAutores.getSelectionModel().getSelectedIndex());
       }else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos uno de la lista", "Pare remover un autor", "Remover Autor");
       }        
    }
    
    @FXML
    public void removerMateria(ActionEvent evento){
        if(tablaMaterias.getSelectionModel().getSelectedItem()!=null){           
            listaMaterias.remove(tablaMaterias.getSelectionModel().getSelectedIndex());
       }else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos una de la lista", "Pare remover una materia", "Remover Materia");
       }    
   }
    
    @FXML
    public void buscarMaterial(ActionEvent evento){
      
        if(!"".equals(txtfBuscar.getText())){
            prepararTablaMaterial();
            filtrarMaterial.addAll(listaMaterial);
            listaMaterial.addAll(consulta.getListaMaterialBusqueda(txtfBuscar.getText().trim()));        
            tablaMaterial.setItems(filtrarMaterial);
        }      
    }
    
    @FXML
    private void mapearDatos(){
      
        if (tablaMaterial.getSelectionModel().getSelectedItem()!=null) {
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
            lblEditorial.setText(consulta.getEditorial());
            mapearMateriasAutores();
        }        
       
    }
  
    @FXML
    private void codigoBarras(ActionEvent evento) {
        
        if(tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            ventanaPrincipal = new Sabga();
            btnCodigoBarras.setDisable(true);
            
            dialogo.dialogoCodigoBarras(ventanaPrincipal.getPrimaryStage(), 
                                        filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId(),
                                        filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getTitulo(),
                                        "",1);
            btnCodigoBarras.setDisable(false);
        }else{
            Utilidades.mensaje(null,"Debe seleccionar un material de la lista", "Antes de volver a crear el código de barras", "Código Barras");
        }
    }
  
    @FXML
    private void listarMaterial(ActionEvent evento){                        
        prepararTablaMaterial();
        listar();    
    }
   
    private void mapearMateriasAutores(){
        
         listaAutores.clear();
         listaMaterias.clear();
         clmnMateria.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
         clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
         clmnApellidos.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
         tablaMaterias.setEditable(true);
         tablaAutores.setEditable(true);
         listaAutores.addAll(consulta.listaAutores(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
         listaMaterias.addAll(consulta.listaMaterias(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
         tablaAutores.setItems(listaAutores);
         tablaMaterias.setItems(listaMaterias);     
    }
    
    public void prepararTablaMaterial(){
        
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));        
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));        
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));
        tablaMaterial.setEditable(true);
        filtrarMaterial.clear();
        listaMaterial.clear();
    }
    
    private void listar(){  
        
        filtrarMaterial.addAll(listaMaterial);
        listaMaterial.addAll(consulta.getListaMaterial(comboMaterial.getSelectionModel().getSelectedItem().toString()));        
        tablaMaterial.setItems(filtrarMaterial);                       
    }
    
    private void llenarComboBox(){
        
        comboClaseMaterial.setItems(consulta.llenarLista("SELECT clase_material FROM tbl_CLASE_MATERIAL", "clase_material"));
        comboMaterial.setItems(consulta.llenarLista("SELECT tipo_material FROM tbl_TIPO_MATERIAL", "tipo_material"));
        comboTipoMaterial.setItems(comboMaterial.getItems());               
    }
    
    private Boolean verificarDuplicados(ObservableList lista, String datoVefificar){
        
        for(Object dato: lista){
           if(dato.toString().equals(datoVefificar)){
               return true;
           }            
        }
        return false; 
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
  
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            ventanaPrincipal = new Sabga();
            btnDetalle.setDisable(true);
            dialogo.setId(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
            dialogo.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", ventanaPrincipal.getPrimaryStage(), null, 4);           
            btnDetalle.setDisable(false);
        }
        else{
            Utilidades.mensaje(null,"Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }
    }
    
    public void validarEdicionLibro(){
        
        if(!listaMaterial.isEmpty()){
            ValidarMaterial libro = new ValidarMaterial();
            libro.validarEdicionLibro(txtfCodigoClasificacion.getText(), txtfTitulo.getText(), txtfAnio.getText(), txtfPublicacion.getText(),
                                      txtfPaginas.getText(), txtfEjemplares.getText(), txtfHabilitado.getText(), txtfInhabilitado.getText(), 
                                      txtfReparacion.getText(), editorial.getText(), listaAutores, listaMaterias);
            lblValidarCodigo.setText(libro.getErrorCodigoClasificacion());
            lblValidarTitulo.setText(libro.getErrorTitulo());
            lblValidarAnio.setText(libro.getErrorAnioPublicacion());
            lblValidarPublicacion.setText(libro.getErrorPublicacion());
            lblValidarPaginas.setText(libro.getErrorNumeroPaginas());
            lblValidarEjemplares.setText(libro.getErrorNumeroEjemplares());
            lblValidarEditorial.setText(libro.getErrorEditorial());
            lblValidarAutor.setText(libro.getErrorAutor());
            lblValidarMateria.setText(libro.getErrorMateria());
            errorAcordeon();
        }      
    }
    
    public void validarEdicionOM(){
         if(!listaMaterial.isEmpty()){
             ValidarMaterial om = new ValidarMaterial();
             om.validarEdicionOM(txtfCodigoClasificacion.getText(), txtfTitulo.getText(), txtfEjemplares.getText(), txtfHabilitado.getText(), 
                                 txtfInhabilitado.getText(), txtfReparacion.getText(), listaMaterias);
             lblValidarCodigo.setText(om.getErrorCodigoClasificacion());
             lblValidarTitulo.setText(om.getErrorTitulo());
             lblValidarEjemplares.setText(om.getErrorNumeroEjemplares());
             errorAcordeonOM();             
         }
    }
   
    private void errorAcordeonOM(){
          ValidarMaterial om = new ValidarMaterial();
        if (!acordeonMaterias.isExpanded() && lblValidarMateria.getText() != null){         
            acordeonMaterias.setText("Materias"+"                se ha encontrado un error!");
            }       
            else {
                acordeonMaterias.setText("Materias");
            }
            if(!acordeonGeneral.isExpanded() && !om.validarAcordeon(lblValidarCodigo.getText(), lblValidarTitulo.getText(), 
                                                                    lblValidarEjemplares.getText())){ 
            acordeonGeneral.setText("General"+"                 se ha encontrado un error!");        
            }
            else{
                acordeonGeneral.setText("General");
            }
    }
    
    private void errorAcordeon(){
        ValidarMaterial libro = new ValidarMaterial();
        if (!acordeonMaterias.isExpanded() && lblValidarMateria.getText() != null) {
            acordeonMaterias.setText("Materias" + "                se ha encontrado un error!");
        } else {
            acordeonMaterias.setText("Materias");
        }
        if (!acordeonAutores.isExpanded() && lblValidarAutor.getText() != null) {
            acordeonAutores.setText("Autores" + "                 se ha encontrado un error!");
        } else {
            acordeonAutores.setText("Autores");
        }
        if (!acordeonGeneral.isExpanded() && !libro.validarAcordeon(lblValidarCodigo.getText(), lblValidarTitulo.getText(), 
                                                                    lblValidarPaginas.getText(), lblValidarEditorial.getText(),
                                                                    lblValidarEjemplares.getText(), lblValidarPublicacion.getText(),
                                                                    lblValidarAnio.getText())) {
            acordeonGeneral.setText("General" + "                 se ha encontrado un error!");
        } else {
            acordeonGeneral.setText("General");
        }
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
    private void borrarCampo(ActionEvent evento) {
        
        switch (Utilidades.getDesencadenador(evento)) {
            case "btnBorrar":
                txtfFiltrar.setText("");
                btnBorrar.setVisible(false);
                break;
            case "btnBorrarBusqueda":
                txtfBuscar.setText("");
                btnBorrarBusqueda.setVisible(false);
                break;
        }    
    }
    
    private void mostrarBoton() {

        if (txtfFiltrar.getText() == null || txtfFiltrar.getText().isEmpty()) {
            btnBorrar.setVisible(false);
        } else {
            btnBorrar.setVisible(true);
        }
    }
    
    @FXML
    private void mostrarBoton(KeyEvent evento) {

        if (txtfBuscar.getText() == null || txtfBuscar.getText().isEmpty()) {
            btnBorrarBusqueda.setVisible(false);
        } else {
            btnBorrarBusqueda.setVisible(true);
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
    
    @Override
    public void setScreenParent(ScreensController screenParent) {     
         controlador = screenParent;
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
     
    public void iniciar(){
        
        llenarComboBox();
        editorial.setPrefSize(250, 30);
        materias.setPrefSize(350, 30);
        autores.setPrefSize(350, 30);
        materias.getTextbox().setPromptText("Materia");
        autores.getTextbox().setPromptText("Autor");
        editorial.getTextbox().setPromptText("Buscar Editorial");
        listaBusquedaMaterias.addAll(consulta.llenarLista("SELECT nombre_materia FROM tbl_MATERIA", "nombre_materia"));
        listaBusquedaAutores.addAll(consulta.listaAutores());
        obtenerAutores.addAll(consulta.getListaAutores());
        editorial.setData(consulta.llenarLista("SELECT nombre_editorial FROM tbl_EDITORIAL", "nombre_editorial"));
        materias.setData(listaBusquedaMaterias);
        autores.setData(listaBusquedaAutores);        
        hboxEditorial.getChildren().add(editorial);
        hboxMaterias.getChildren().add(materias);
        hboxAutores.getChildren().add(autores);
        btnBorrar.setVisible(false);
        btnBorrarBusqueda.setVisible(false);
    }
    
    /** 
     * Initializes the controller class.
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
        Platform.runLater(new Runnable() {private Tooltip detalle; 
        @Override 
        public void run() { 
        detalle = new Tooltip("Muestra en detalle la información del material.\nDebe seleccionarlo de la lista.");
         btnDetalle.setTooltip(detalle); 
           // MenuItem h = new MenuItem("Esto es una prueba de un menú contextual ");
           // ContextMenu es = new ContextMenu(h);            
           // botonNuevaEditorial.setContextMenu(es);                 
        }});
                
    }    
  
}
