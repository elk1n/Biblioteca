
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Autor;
import sabga.atributos.Ejemplar;
import sabga.atributos.Materia;
import sabga.atributos.Material;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
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
    private Label lblEditorial, lblValidarCodigo, lblValidarTitulo, lblValidarAnio, lblValidarPublicacion, 
                  lblValidarEditorial, lblValidarEjemplares, lblValidarPaginas, lblValidarAutor, lblValidarMateria,
                  lblTipoMaterial, lblResultado, lblIsbn;    
    @FXML 
    private TextField txtfCodigoClasificacion, txtfTitulo, txtfAnio, txtfPublicacion, txtfPaginas, txtfFiltrar, 
                      txtfEjemplar, txtfBuscar, txtfIsbn;
    @FXML 
    private Button  btnBorrar, btnDetalle, btnEditorial, btnAutor, btnMateria, btnCodigoBarras;    
    @FXML 
    private ComboBox<String> comboClaseMaterial, comboMaterial, comboDispo;    
    @FXML
    private HBox hboxEditorial, hboxAutores, hboxMaterias;
    @FXML
    private RadioButton radioBuscar, radioFiltrar;
    @FXML    
    private Tooltip est;
    @FXML
    private TableView<Material> tablaMaterial;
    @FXML
    private TableView<Materia> tablaMaterias;
    @FXML
    private TableView<Autor> tablaAutores;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private TableColumn<Material, String> clmnTitulo, clmnCodigo, clmnClase;
    @FXML
    private TableColumn<Materia, String> clmnMateria;
    @FXML
    private TableColumn<Autor, String>clmnNombre;
    @FXML
    private TableColumn<Ejemplar, String>clmnEjemplar, clmnEstado;
    private final AutoFillTextBox<String> editorial, autores, materias ;    
    private final ObservableList<Material> filtrarMaterial;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Autor> listaAutores;
    private final ObservableList<Materia> listaMaterias;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<String> listaBusquedaMaterias, listaBusquedaAutores, disponibilidad, listaEditorial;
    private final Consultas consulta;

    public EditarMaterialController(){
        dialogo = new Dialogo();       
        consulta = new Consultas();
        editorial = new AutoFillTextBox<>();
        autores = new AutoFillTextBox<>();
        materias = new AutoFillTextBox<>();
        listaBusquedaMaterias = FXCollections.observableArrayList();
        listaBusquedaAutores = FXCollections.observableArrayList();
        filtrarMaterial = FXCollections.observableArrayList();
        listaAutores = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
        listaMaterias = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        disponibilidad = FXCollections.observableArrayList();
        listaEditorial = FXCollections.observableArrayList();
        listaMaterial.addListener(new ListChangeListener<Material>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Material> change) {
                updateFilteredData();
            }
        });
    }
       
    @FXML
    public void guardarCambios(ActionEvent evento){        
        editarMaterial();
    }
    
    @FXML
    public void adicionarEjemplar(ActionEvent evento){
        adicionarEjemplar();       
    }
    
    @FXML
    public void cambiarEstado(ActionEvent evento){
        cambiarEstadoEjemplar();        
    }
   
    @FXML
    public void adicionarAutor(ActionEvent evento){        
        sumarAutor();        
    }
    
    @FXML
    public void adicionarMateria(ActionEvent evento){
        sumarMateria();
    }
    
    @FXML
    public void removerAutor(ActionEvent evento){
        removerAutor();
    }
    
    @FXML
    public void removerMateria(ActionEvent evento){        
        removerMateria();            
    }
    
    @FXML
    public void buscarMaterial(ActionEvent evento){
        buscarMaterial();            
    }
    
    @FXML
    public void removerEjemplar(ActionEvent evento){
    
        eliminarEjemplar();
    }
    
    @FXML
    public void verCodigoBarras(ActionEvent evento){
        codigoBarras();
    }
   
    @FXML
    public void listarMaterial(ActionEvent evento){                        
        prepararTablaMaterial();
        listar();    
    }
    
    @FXML
    public void mapearDatosMaterial(){
        mapearDatos();
    }
            
    @FXML
    public void buscarFiltrar(){
        
        if(radioBuscar.isSelected()){
            txtfFiltrar.setText("");
            txtfFiltrar.setDisable(true);
            txtfFiltrar.setVisible(false); 
            txtfBuscar.setText("");
            txtfBuscar.setDisable(false);
            txtfBuscar.setVisible(true);
        }
        else if(radioFiltrar.isSelected()){
            txtfBuscar.setText("");
            txtfBuscar.setDisable(true);
            txtfBuscar.setVisible(false);
            txtfFiltrar.setText("");
            txtfFiltrar.setDisable(false);
            txtfFiltrar.setVisible(true);
        }
    }
    
    @FXML
    public void disponibilidadMaterial(){
        
        if(!listaEjemplares.isEmpty() && tablaEjemplar.getSelectionModel().getSelectedItem() != null){
            comboDispo.getSelectionModel().select(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getDisponibilidad());
        }
    }

    private void buscarMaterial() {

        limpiarDetalle();
        if (!"".equals(txtfBuscar.getText()) && radioBuscar.isSelected()) {
            prepararTablaMaterial();
            filtrarMaterial.addAll(listaMaterial);
            listaMaterial.addAll(consulta.getListaMaterialBusqueda(txtfBuscar.getText().trim()));
            tablaMaterial.setItems(filtrarMaterial);
            if(filtrarMaterial.isEmpty()){
                comboMaterial.getSelectionModel().clearSelection();
                lblResultado.setText("No se han encontrado resultados.");
            }else{
                lblResultado.setText(null);
                comboMaterial.getSelectionModel().clearSelection();
            }
        }
    }
    
    private void mapearDatos(){
      
        if (tablaMaterial.getSelectionModel().getSelectedItem()!= null) {
            consulta.mapearMaterial(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
            txtfTitulo.setText(consulta.getTitulo());
            txtfCodigoClasificacion.setText(consulta.getClasificacion());
            txtfIsbn.setText(consulta.getISBN());
            txtfAnio.setText(consulta.getAnio());
            txtfPublicacion.setText(consulta.getPublicacion());
            txtfPaginas.setText(String.valueOf(consulta.getPaginas()));
            lblTipoMaterial.setText(consulta.getTipoMaterial());
            comboClaseMaterial.getSelectionModel().select(consulta.getClaseMaterial());
            lblEditorial.setText(consulta.getEditorial());
            mapearEjemplares();
            mapearMateriasAutores();
            editorial.getTextbox().setText("");
            lblValidarMateria.setText("");
            lblValidarAutor.setText("");
            lblValidarEjemplares.setText("");            
        }              
    }
        
    private void eliminarEjemplar() {
        
        if (!listaEjemplares.isEmpty() && tablaEjemplar.getSelectionModel().getSelectedItem() != null) {
            if (listaEjemplares.size() > 1) {
                int idEjemplar = Integer.parseInt(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEjemplar());
                int idMaterial = Integer.parseInt(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId());
                consulta.eliminarEjemplar(idEjemplar, idMaterial);
                lblValidarEjemplares.setText("");
                if(consulta.getMensaje() != null){
                    Utilidades.mensajeError(null, consulta.getMensaje(),"No se ha eliminado el ejemplar", "Error Eliminar Ejemplar");
                }else{
                     listaEjemplares.remove(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()));
                     txtfEjemplar.clear();
                }            
            }else{
                lblValidarEjemplares.setText("El material debe tener como mínimo un ejemplar.");
            }
        }else{
            lblValidarEjemplares.setText("Debe seleccionar un material de la lista.");
        }       
    }

    private void codigoBarras() {
        
        if(tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            ventanaPrincipal = new Sabga();
            btnCodigoBarras.setDisable(true);
            
            dialogo.dialogoCodigoBarras(ventanaPrincipal.getPrimaryStage(), 
                                        filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId(),
                                        filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getTitulo(), "", 1);
            btnCodigoBarras.setDisable(false);
        }else{
            Utilidades.mensaje(null,"Debe seleccionar un material de la lista.", "Antes de volver a crear el código de barras.", "Código Barras");
        }
    }
      
    private void cambiarEstadoEjemplar(){
    
        if(tablaEjemplar.getSelectionModel().getSelectedItem()!=null && tablaMaterial.getSelectionModel().getSelectedItem()!=null){
            int idEjemplar = Integer.parseInt(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEjemplar());
            int idMaterial = Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId());
            consulta.editarEjemplar(2, idMaterial, idEjemplar, 0, comboDispo.getSelectionModel().getSelectedItem().toString());
            if(consulta.getMensaje()==null){
               Utilidades.mensaje(null, "La disponibilidad se ha actualizado correctamento.", "Cambiar la disponibilidad del ejemplar.", "Cambiar Disponibilidad");
               mapearEjemplares();
           }else{
               Utilidades.mensajeError(null, "No se ha actualizado la disponibilidad.", "Error al actualizar la disponibilidad.", "Error Editar Disponibilidad");
           }
        }
    }
    
    private void adicionarEjemplar(){
        
        ValidarMaterial mensajes = new ValidarMaterial();
        mensajes.validarNumero(txtfEjemplar.getText(), 10);
        lblValidarEjemplares.setText(mensajes.getMensajeError());        
        if(mensajes.validarNumero(txtfEjemplar.getText(), 10) && tablaMaterial.getSelectionModel().getSelectedItem()!=null){
           int idMaterial = Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId());
           consulta.editarEjemplar(1, idMaterial, 0, Integer.parseInt(txtfEjemplar.getText()), null);
           if(consulta.getMensaje()==null){
               Utilidades.mensaje(null, "Los nuevos ejemplares se han registrado correctamente.", "Registro de nuevos ejemplares.", "Registrar Ejemplares");
               mapearEjemplares();
           }else{
               Utilidades.mensajeError(null, "No se han registrado los ejemplares.", "Error al registrar nuevos ejemplares.", "Error Registro Ejemplar");
           }
        }else{
            lblValidarEjemplares.setText(consulta.getMensaje());
        }
    }
    
    private void sumarAutor(){

        if (lblTipoMaterial.getText() != null && !"".equals(lblTipoMaterial.getText())) {
            if (!listaMaterial.isEmpty() && lblTipoMaterial.getText().toLowerCase().contains("libro")) {
                if (listaBusquedaAutores.indexOf(autores.getText()) != -1) {
                    if (!verificarDuplicadosAutor(listaAutores, autores.getText())) {
                        consulta.editarAutorMaterial(2, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()),
                                                     autores.getText());
                        if (consulta.getMensaje() == null) {
                            Utilidades.mensaje(null, "El autor se ha añadido correctamente.", "Adición exitosa.", "Añadir Autor");
                            listaAutores.clear();
                            listaAutores.addAll(consulta.getListaAutoresMaterial(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
                            autores.getTextbox().setText("");
                        } else {
                            Utilidades.mensajeAdvertencia(null, "No ha sido posible añadir el autor.", "Error al añadir el autor.", "Añadir Autor");
                            autores.getTextbox().setText("");
                        }
                    } else {
                        Utilidades.mensaje(null, "El autor seleccionado ya se encuentra presente en la lista.", "El autor ya se encuentra en la lista.", "Seleccionar Autor");
                        autores.getTextbox().setText("");
                    }
                } else {
                    Utilidades.mensaje(null, "El autor debe estar registrado.", "Para adicionar un autor a la lista.", "Seleccionar Autor");
                    autores.getTextbox().setText("");
                }
            }
        }     
    }
    
    private void removerAutor(){
        
         if(tablaAutores.getSelectionModel().getSelectedItem()!= null && tablaMaterial.getSelectionModel().getSelectedItem() != null){
             if(listaAutores.size() >1){
                 consulta.editarAutorMaterial(1, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()),
                                                 listaAutores.get(tablaAutores.getSelectionModel().getSelectedIndex()).getNombreAutor());
                 if(consulta.getMensaje() == null){
                       Utilidades.mensaje(null, "El autor ha sido removido correctamente.", "Remover un  autor.", "Remover Autor");
                       listaAutores.remove(tablaAutores.getSelectionModel().getSelectedIndex());
                 }else{
                    Utilidades.mensajeAdvertencia(null, "No ha sido posible remover el autor.", "Error al remover el autor.", "Remover Autor");
                }            
             }else{
                 lblValidarAutor.setText("El libro debe estar asociado al menos a un autor.");
             }      
       }else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar un autor de la lista.", "Pare remover un autor.", "Remover Autor");
       }     
    }
    
    private void sumarMateria(){
        
        if (!listaMaterial.isEmpty()) {
            if (listaBusquedaMaterias.indexOf(materias.getText()) != -1) {
                if (!verificarDuplicados(listaMaterias, materias.getText())) {
                    consulta.editarMaterialMateria(2, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()),
                                                   materias.getText());
                    if(consulta.getMensaje()==null){                       
                        Utilidades.mensaje(null,  "La materia se ha añadido correctamente.", "Adición exitosa.", "Añadir Materia");
                        listaMaterias.add(new Materia(listaBusquedaMaterias.get(listaBusquedaMaterias.indexOf(materias.getText())).toString()));
                        materias.getTextbox().setText("");
                    }else{
                         Utilidades.mensajeAdvertencia(null, "No ha sido posible añadir la materia.", "Error al añadir la materia.", "Añadir Materia");
                         materias.getTextbox().setText("");
                    }
                } else {
                    Utilidades.mensaje(null, "La materia ya se encuentra en la lista.", "Una materia no se puede encontrar más de una vez.", "Seleccionar Materia ");
                    materias.getTextbox().setText("");
                }
            } else {
                Utilidades.mensaje(null, "La materia se debe encontrar en la lista.", "Para adicionar una materia a la lista.", "Seleccionar Materia");
                materias.getTextbox().setText("");
            }
        }  
    }
    
    private void removerMateria(){
        
        if(tablaMaterias.getSelectionModel().getSelectedItem()!=null && tablaMaterial.getSelectionModel().getSelectedItem() != null){
            if(listaMaterias.size() >1){
                consulta.editarMaterialMateria(1, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()),
                                               listaMaterias.get(tablaMaterias.getSelectionModel().getSelectedIndex()).getNombreMateria());
                if(consulta.getMensaje() == null){
                    Utilidades.mensaje(null, "La materia ha sido removida correctamente.", "Remover una materia.", "Remover Materia");
                    listaMaterias.remove(tablaMaterias.getSelectionModel().getSelectedIndex());
                }else{
                    Utilidades.mensajeAdvertencia(null, "No ha sido posible remover la materia.", "Error al remover la materia.", "Remover Materia");
                } 
            }else{
                lblValidarMateria.setText("El material debe estar asociado al menos a una materia.");
            }         
       }else{
           Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos una materia de la lista.", "Pare remover una materia.", "Remover Materia");
       }
    }
    
    private void editarMaterial(){
        
        if(lblTipoMaterial.getText() != null && !"".equals(lblTipoMaterial.getText())){
            String tipo = lblTipoMaterial.getText().toLowerCase();
            if(tipo.contains("libro")){
                validarEdicionLibro();
                ConfirmarMaterial validar = new ConfirmarMaterial();
                if(validar.confirmarEdicionLibro(txtfCodigoClasificacion.getText(), txtfIsbn.getText(),
                                                 txtfTitulo.getText(), txtfAnio.getText(), txtfPublicacion.getText(),
                                                 txtfPaginas.getText(), editorial.getText(), editorial.getData())){
                    
                    consulta.editarMaterial(1, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()), 
                                            comboClaseMaterial.getSelectionModel().getSelectedItem().toString(),  
                                            editorial.getTextbox().getText(), txtfCodigoClasificacion.getText(), 
                                            txtfIsbn.getText(), txtfTitulo.getText(), txtfPublicacion.getText(),
                                            Utilidades.setNumero(txtfAnio.getText()), Utilidades.setNumero(txtfPaginas.getText()));
                    if(consulta.getMensaje() == null){
                        limpiarCampos();
                        Utilidades.mensaje(null, "Los cambios se han guardado correctamente.", "", "Guardar Cambios");                        
                    }else {
                        Utilidades.mensajeAdvertencia(null, consulta.getMensaje() ,"No se han guadado los cambios.", "Error Guardar");
                    }
                }                                      
            }
            else{
                validarEdicionOM();
                ConfirmarMaterial validar = new ConfirmarMaterial();
                if(validar.confirmarEdicionOM(txtfCodigoClasificacion.getText(), txtfTitulo.getText())){
                    consulta.editarMaterial(2, Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()),
                                            comboClaseMaterial.getSelectionModel().getSelectedItem().toString(),
                                            null, txtfCodigoClasificacion.getText().trim(), null, txtfTitulo.getText(), null, 0, 0);
                    if(consulta.getMensaje()==null){
                        limpiarCampos();
                        Utilidades.mensaje(null, "Los cambios se han guardado correctamente.", "", "Guardar Cambios");                        
                    }else {
                        Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "No se han guadado los cambios.", "Error Guardar");                                                 
                    }           
                }
            }
        }  
    }
     
    private void mapearMateriasAutores(){
        
         listaAutores.clear();
         listaMaterias.clear();
         clmnMateria.setCellValueFactory(new PropertyValueFactory<Materia, String>("nombreMateria"));
         clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
         tablaMaterias.setEditable(true);
         tablaAutores.setEditable(true);
         listaAutores.addAll(consulta.getListaAutoresMaterial(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
         listaMaterias.addAll(consulta.listaMaterias(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
         tablaAutores.setItems(listaAutores);
         tablaMaterias.setItems(listaMaterias);     
    }
    
    private void mapearEjemplares(){
        
        listaEjemplares.clear();
        clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
        tablaEjemplar.setEditable(true);
        listaEjemplares.addAll(consulta.listaEjemplares(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
        tablaEjemplar.setItems(listaEjemplares);
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
        
        limpiarDetalle();
        if(!comboMaterial.getSelectionModel().isEmpty()){
            filtrarMaterial.addAll(listaMaterial);
            listaMaterial.addAll(consulta.getListaMaterial(comboMaterial.getSelectionModel().getSelectedItem().toString()));
            tablaMaterial.setItems(filtrarMaterial);
        }        
    }
    
    private void llenarComboBox(){
        
        comboClaseMaterial.setItems(consulta.llenarLista(1));
        comboMaterial.setItems(consulta.llenarLista(2));           
    }
    
    private Boolean verificarDuplicadosAutor(ObservableList<Autor> lista, String datos){
    
        for(Autor dato: lista){
           if(dato.getNombreAutor().equals(datos)){
               return true;
           }            
        }
        return false;
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
         listaEditorial.clear();
         listaEditorial.addAll(consulta.llenarLista(4));
         btnEditorial.setDisable(false);
     }
    
    @FXML
    public void dialogoNuevoAutor(ActionEvent evento){        
        ventanaPrincipal = new Sabga();
        btnAutor.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevoAutor.fxml", "Nuevo Autor", ventanaPrincipal.getPrimaryStage(), null, 1);
        listaBusquedaAutores.clear();
        listaBusquedaAutores.addAll(consulta.llenarLista(12));
        btnAutor.setDisable(false);
    }
    
    @FXML
    public void dialogoNuevaMateria(ActionEvent evento){  
        
        ventanaPrincipal = new Sabga();
        btnMateria.setDisable(true);
        dialogo.mostrarDialogo("vista/dialogos/NuevaMateria.fxml", "Nueva Materia", ventanaPrincipal.getPrimaryStage(), null, 2);
        listaBusquedaMaterias.clear();
        listaBusquedaMaterias.addAll(consulta.llenarLista(3));
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
            libro.validarEdicionLibro(txtfCodigoClasificacion.getText(), txtfIsbn.getText(), txtfTitulo.getText(), txtfAnio.getText(), 
                                      txtfPublicacion.getText(),txtfPaginas.getText(), editorial.getText(), editorial.getData());
            lblValidarCodigo.setText(libro.getErrorCodigoClasificacion());
            lblValidarTitulo.setText(libro.getErrorTitulo());
            lblValidarAnio.setText(libro.getErrorAnioPublicacion());
            lblValidarPublicacion.setText(libro.getErrorPublicacion());
            lblValidarPaginas.setText(libro.getErrorNumeroPaginas());
            lblValidarEditorial.setText(libro.getErrorEditorial());
            lblIsbn.setText(libro.getErrorIsbn());
        }     
    }
    
    public void validarEdicionOM(){     
        
         if(!listaMaterial.isEmpty()){             
             ValidarMaterial om = new ValidarMaterial();
             om.validarEdicionOM(txtfCodigoClasificacion.getText(), txtfTitulo.getText());
             lblValidarCodigo.setText(om.getErrorCodigoClasificacion());
             lblValidarTitulo.setText(om.getErrorTitulo());       
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
    public void borrarCampo(ActionEvent evento) {
        
        txtfFiltrar.setText("");
        txtfBuscar.setText("");
        btnBorrar.setVisible(false);
        lblResultado.setText(null);
    }
    
    @FXML
    private void mostrarBoton(KeyEvent evento) {

        if ("".equals(txtfBuscar.getText()) && "".equals(txtfFiltrar.getText())){            
            btnBorrar.setVisible(false);      
        }else {
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
    
    private void limpiarCampos(){
        
        lblTipoMaterial.setText(null);
        comboClaseMaterial.getSelectionModel().clearSelection();
        txtfCodigoClasificacion.clear();
        txtfIsbn.clear();
        txtfTitulo.clear();
        txtfAnio.clear();
        txtfPublicacion.clear();
        txtfPaginas.clear();
        editorial.getTextbox().clear();   
    }
    
    private void limpiarDetalle(){
        
        lblTipoMaterial.setText(null);
        txtfCodigoClasificacion.clear();
        txtfTitulo.clear();
        txtfPublicacion.clear();
        txtfIsbn.clear();
        txtfAnio.clear();
        comboClaseMaterial.getSelectionModel().clearSelection();
        txtfPaginas.clear();
        lblEditorial.setText(null);
        listaEjemplares.clear();
        listaAutores.clear();
        listaMaterias.clear();    
    }
    
     public void llenarListaEditoriales(){
        listaEditorial.addAll(consulta.llenarLista(4));
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {     
         controlador = screenParent;
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }
     
    public void iniciar(){
        
        buscarFiltrar();
        llenarComboBox();
        llenarListaEditoriales();
        editorial.setPrefSize(250, 30);
        materias.setPrefSize(350, 30);
        autores.setPrefSize(350, 30);
        materias.getTextbox().setPromptText("Buscar Materia");
        autores.getTextbox().setPromptText("Buscar Autor");
        editorial.getTextbox().setPromptText("Buscar Editorial");
        listaBusquedaMaterias.addAll(consulta.llenarLista(3));
        listaBusquedaAutores.addAll(consulta.llenarLista(12));
        editorial.setData(listaEditorial);
        materias.setData(listaBusquedaMaterias);
        autores.setData(listaBusquedaAutores);        
        hboxEditorial.getChildren().add(editorial);
        hboxMaterias.getChildren().add(materias);
        hboxAutores.getChildren().add(autores);
        btnBorrar.setVisible(false);
        disponibilidad.add("Habilitado");
        disponibilidad.add("Inhabilitado");
        disponibilidad.add("Mantenimiento");
        comboDispo.setItems(disponibilidad);
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
                mostrarBoton(null);
                updateFilteredData();
            }
        });
                 
        // PRUEBA DE TOOLTIP.....WORKS BY THE WAY :)
        Platform.runLater(new Runnable() {private Tooltip detalle; 
        @Override 
        public void run() { 
        detalle = new Tooltip("Muestra en detalle la información del material.\nDebe seleccionarlo de la lista.");
        // btnDetalle.setTooltip(detalle); 
           // MenuItem h = new MenuItem("Esto es una prueba de un menú contextual ");
           // ContextMenu es = new ContextMenu(h);            
           // botonNuevaEditorial.setContextMenu(es);                 
        }});
                
    }    
  
}
