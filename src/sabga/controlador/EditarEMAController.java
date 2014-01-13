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
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */

public class EditarEMAController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private TableView<Autor> tablaResultados;
    @FXML
    private TableColumn<Autor, String> clmnNombre;
    @FXML
    private TableColumn<Autor, String> clmnApellido;
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private Label validarNombreAutor, validarApellidosAutor, validarEditorial, validarMateria, validarClaseMaterial, validarTipoMaterial;
    @FXML
    private TextField campoNombreAutor, campoApellidosAutor, campoEditorial, campoMateria, campoTipoMaterial, campoClaseMaterial, campoFiltrar;
    @FXML
    private TitledPane acordeonAutor, acordeonEditorial, acordeonMateria, acordeonTipo, acordeonClase;
    @FXML
    private Button btnBorrar, btnEliminar;
    private String nombre, apellido;
    private final ObservableList<Autor> listaDatos;
    private final ObservableList<Autor> filtrarDatos;
    private final ObservableList<Autor> listaAutores;
    private final ObservableList<Autor> filtrarAutores;
    private final Consultas consulta;

    public EditarEMAController() {

        consulta = new Consultas();
        filtrarDatos = FXCollections.observableArrayList();
        listaDatos = FXCollections.observableArrayList();
        listaDatos.addListener(new ListChangeListener<Autor>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Autor> change) {
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
        editarEditorilTipoClaseMateria();
    }
    
    @FXML
    public void eliminar(ActionEvent evento){    
       eliminar();
    }
    
    @FXML
    public void listarDatos(ActionEvent evento){    
        listar();
    }
    
    @FXML
    public void setDatos(){    
        mapearDatos();
    }
    
    private void eliminar(){
        
         if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
            
             if (comboListar.getSelectionModel().getSelectedIndex() == 0) {                
                edicionAutor(4);
                campoNombreAutor.setText(null);
                campoApellidosAutor.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                eliminarOtros(5, filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 4, "Filtrar Editorial", "Eliminar Editorial");               
                campoEditorial.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                eliminarOtros( 6, filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 3, "Filtrar Materia", "Eliminar Materia");                                 
                campoMateria.setText(null);                
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
                eliminarOtros(7, filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 2, "Filtrar Tipo de Material", "Eliminar Tipo");  
                campoTipoMaterial.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
                eliminarOtros(8, filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 1, "Filtrar Clase de Material", "Eliminar Clase");                  
                campoClaseMaterial.setText(null);
            }
            resetear();
       }
       else{
             Utilidades.mensaje(null, "Debe seleccionar un item de la lista", "Para eliminar un item", "Seleccionar");
       }                
    }    
    
    private void eliminarOtros(int opcion, int codigo, int seleccion, String textF, String boton){
    
        if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
                  consulta.editarAME(opcion, codigo, "");
                if (consulta.getMensaje() != null) {
                    Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al editar la selección.", "Error Guardar Cambios");                   
                } else {
                    Utilidades.mensaje(null, "Los cambios se han guardado correctamente.", "Editando Selección", "Actualización Exitosa");
                    listaDatos.clear();
                    listarOtros(seleccion, textF, boton);
                }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un item de la lista.", "", "Editar");
        }
    }
    
    private void editarEditorilTipoClaseMateria() {
         
        ConfirmarMaterial verificar = new ConfirmarMaterial();        
        mensajesError();       
        if (acordeonAutor.isExpanded()) {
            if (verificar.confirmarNuevoAutor(campoNombreAutor.getText(), campoApellidosAutor.getText())) {
                editarAutor();                
            }
        } else if (acordeonEditorial.isExpanded()) {
            if (verificar.confirmarNuevaEditorial(campoEditorial.getText())) {
                editarOtros(campoEditorial.getText().trim(), 1,
                        filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 4, "Filtrar Editorial", "Eliminar Editorial");               
            }

        } else if (acordeonMateria.isExpanded()) {
            if (verificar.confirmarNuevaMateria(campoMateria.getText())) {
                editarOtros(campoMateria.getText().trim(), 2,
                            filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 3, "Filtrar Materia", "Eliminar Materia");                 
            }

        } else if (acordeonTipo.isExpanded()) {
            if (verificar.confirmarNuevoTipoMaterial(campoTipoMaterial.getText())) {
                editarOtros(campoTipoMaterial.getText().trim(), 3, 
                            filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 2, "Filtrar Tipo de Material", "Eliminar Tipo");  
            }

        } else if (acordeonClase.isExpanded()) {
            if (verificar.confirmarNuevaClaseMaterial(campoClaseMaterial.getText())) {
                editarOtros(campoClaseMaterial.getText().trim(), 4, 
                           filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(), 1, "Filtrar Clase de Material", "Eliminar Clase");  
            }
        }
        resetear();
    }
    
    private void editarOtros(String campo, int opcion, int codigo, int seleccion, String textF, String boton){
        
        if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
            if (!nombre.equalsIgnoreCase(campo)) {
                  consulta.editarAME(opcion, codigo, campo);
                if (consulta.getMensaje() != null) {
                    Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al editar la selección.", "Error Guardar Cambios");                   
                } else {
                    Utilidades.mensaje(null, "Los cambios se han guardado correctamente.", "Editando Selección", "Actualización Exitosa");
                    listaDatos.clear();
                    listarOtros(seleccion, textF, boton);
                }
            } else {
                Utilidades.mensaje(null, "No se han presentado cambios.", "Editando Selección", "Editar Selección");
            }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un item de la lista.", "", "Editar");
        }         
    }
    
    private void editarAutor() {

        if (tablaResultados.getSelectionModel().getSelectedItem() != null) {

            if (!nombre.equalsIgnoreCase(campoNombreAutor.getText().trim()) && !apellido.equalsIgnoreCase(campoApellidosAutor.getText().trim())) {
                edicionAutor(3);
            } else if (!nombre.equalsIgnoreCase(campoNombreAutor.getText().trim())) {
                edicionAutor(1);
            } else if (!apellido.equalsIgnoreCase(campoApellidosAutor.getText().trim())) {
                edicionAutor(2);
            } else {
                Utilidades.mensaje(null, "No se han presentado cambios.", "Editando Autor", "Editar Autor");
            }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un autor de la lista.", "Editando Autor", "Editar Autor");
        }  
    }
    
    private void edicionAutor(int seleccion){
        
        consulta.editarAutor(filtrarAutores.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(),
                            seleccion, campoNombreAutor.getText().trim(), campoApellidosAutor.getText().trim());
        if (consulta.getMensaje() != null) {
            Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al editar el autor", "Error Guardar Cambios Autor");           
        } else {
            Utilidades.mensaje(null, "Los cambios se han guardado correctamente.", "Editando Autor", "Actualización Exitosa");
            listaAutores.clear();
            listaAutores.addAll(consulta.getListaAutores());   
        }
    }
        
    private void mensajesError() {

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
    
    private void listar() {
        
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            listarAutores();        
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            listarOtros(4, "Filtrar Editorial", "Eliminar Editorial");
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            listarOtros(3, "Filtrar Materia", "Eliminar Materia");
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            listarOtros(2, "Filtrar Tipo de Material", "Eliminar Tipo");
        }
         
        if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
            listarOtros(1, "Filtrar Clase de Material", "Eliminar Clase");           
        } 
    }
    
    private void mapearDatos() {

        if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
            
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
                campoEditorial.setText(filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor());
                campoEditorial.setDisable(false);
                nombre = filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor();
            }

            if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                acordeonMateria.setExpanded(true);
                campoMateria.setText(filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor());
                campoMateria.setDisable(false);
                nombre = filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor();
            }

            if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
                acordeonTipo.setExpanded(true);
                campoTipoMaterial.setText(filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor());
                campoTipoMaterial.setDisable(false);
                nombre = filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor();
            }

            if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
                acordeonClase.setExpanded(true);
                campoClaseMaterial.setText(filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor());
                campoClaseMaterial.setDisable(false);
                nombre = filtrarDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getNombreAutor();
            }
        }
    }
    
    private void listarAutores() {

        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
        clmnNombre.setPrefWidth(240);
        clmnApellido = new TableColumn<>("Apellido");
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
        clmnApellido.setPrefWidth(270);
        tablaResultados.getColumns().add(clmnApellido);
        tablaResultados.setItems(filtrarAutores);
        
        filtrarAutores.removeAll(filtrarAutores);
        listaAutores.removeAll(listaAutores);
        filtrarAutores.addAll(listaAutores);
        listaAutores.addAll(consulta.getListaAutores());   
        campoFiltrar.setPromptText("Filtrar Autor");
        btnEliminar.setText("Eliminar Autor");

    }
    
    private void listarOtros(int opcion, String text, String boton){
    
        campoFiltrar.setText(null);
        resetear();
        filtrarDatos.removeAll(filtrarDatos);
        listaDatos.removeAll(listaDatos);
        filtrarDatos.addAll(listaDatos);
        listaDatos.addAll(consulta.llenarLista2(opcion));
        campoFiltrar.setPromptText(text);
        btnEliminar.setText(boton);
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
        tablaResultados.getColumns().remove(clmnApellido);
        clmnNombre.setPrefWidth(510);
        tablaResultados.setEditable(true);
        tablaResultados.setItems(filtrarDatos);
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

            for (Autor p : listaDatos) {
                if (matchesFilter(p)) {
                    filtrarDatos.add(p);
                }
            }
            reapplyTableSortOrder();
        }
    }

    private boolean matchesFilter(Autor nombre) {

        String filterString = campoFiltrar.getText();

        if (filterString == null || filterString.isEmpty()) {
            return true;
        }
        String lowerCaseFilterString = filterString.toLowerCase();

        if (nombre.getNombreAutor().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    private boolean matchesFilterAutor(Autor autor) {

        String filterString = campoFiltrar.getText();

        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (autor.getNombreAutor().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (autor.getApellidosAutor().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }

        return false;
    }

    private void reapplyTableSortOrder() {

        ArrayList<TableColumn<Autor, ?>> sortOrder = new ArrayList<>(tablaResultados.getSortOrder());
        tablaResultados.getSortOrder().clear();
        tablaResultados.getSortOrder().addAll(sortOrder);
    }

    private void reapplyTableSortOrderAutor() {

        ArrayList<TableColumn<Autor, ?>> sortOrder = new ArrayList<>(tablaResultados.getSortOrder());
        tablaResultados.getSortOrder().clear();
        tablaResultados.getSortOrder().addAll(sortOrder);
    }

    @FXML
    public void borrarCampo(ActionEvent event) {
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
