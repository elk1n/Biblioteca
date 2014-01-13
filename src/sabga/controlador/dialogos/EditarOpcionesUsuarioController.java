
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sabga.atributos.Autor;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarUsuario;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

/**
 * @author Elk1n
 */

public class EditarOpcionesUsuarioController implements Initializable {
    
    private Stage dialogStage;
    
    @FXML
    private ComboBox<String> comboListar;
    @FXML
    private TableView<Autor> tablaResultados;
    @FXML
    private TableColumn<Autor, String> clmnNombre;
    @FXML
    private TextField txtfNombre;
    @FXML
    private Label lblValidacion;
    
    private String nombre;
    private final ObservableList<Autor> listaDatos; 
    private final Consultas consulta;
    
    public EditarOpcionesUsuarioController(){ 
        
        consulta = new Consultas();
        listaDatos = FXCollections.observableArrayList();
    }
     
    public void setDialogStage(Stage dialogStage) {

        this.dialogStage = dialogStage;
    }
    
    @FXML
    public void guardarCambios(ActionEvent evento){    
        edicion();
    }
    
    @FXML
    public void eliminar(ActionEvent evento){
    
        eliminar();
    }
    
    private void eliminar(){
                    
            if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
                 eliminar(5, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor() , 6);
                 txtfNombre.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
                eliminar(6, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor() , 7);
                txtfNombre.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
                eliminar(7, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor() , 8);
                txtfNombre.setText(null);
            }
            if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
                eliminar(8, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor() , 5);
                txtfNombre.setText(null);            
            }                
    }
     
    private void eliminar(int opcion, int codigo, int lista){
        
         if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
                consulta.editarGCJT(opcion, codigo, "");
            if (consulta.getMensaje() != null) {
                Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al eliminar la selección", "Error Guardar Cambios");
            } else {
                Utilidades.mensaje(null, "La selección se ha eliminado correctamente", "Eliminado Selección", "Actualización Exitosa");
                listaDatos.addAll(consulta.llenarLista2(lista));
            }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un item de la lista", "Eliminando Selección", "Eliminar Selección");
        }      
    }
    
    public void edicion() {
         
        ConfirmarUsuario verificar = new ConfirmarUsuario();        
        validarCampo();
       
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {            
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                editar(1, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(),
                       txtfNombre.getText(), 6);
                resetear();
            }            
        } 
        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {            
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                editar(2, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(),
                       txtfNombre.getText(), 7);
                resetear();
            }
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                editar(3, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(),
                       txtfNombre.getText(), 8);
                resetear();
            }
        } 
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            if (verificar.confirmarCurso(txtfNombre.getText())) {
                editar(4, listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).getIdAutor(),
                       txtfNombre.getText(), 5);
                resetear();
            }
        }        
    }
        
    public void editar(int opcion, int codigo, String campo, int lista){
        
        if (tablaResultados.getSelectionModel().getSelectedItem() != null) {
            if (!nombre.equalsIgnoreCase(campo)) {
                consulta.editarGCJT(opcion, codigo, campo);
                if (consulta.getMensaje() != null) {
                    Utilidades.mensajeAdvertencia(null, consulta.getMensaje(), "Error al editar la selección", "Error Guardar Cambios");
                } else {
                    Utilidades.mensaje(null, "Los cambios se han guardado correctamente", "Editando Selección", "Actualización Exitosa");
                    listaDatos.addAll(consulta.llenarLista2(lista));
                }
            } else {
                Utilidades.mensaje(null, "No se han presentado cambios", "Editando Selección", "Editar Selección");
            }
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un item de la lista", "Editando Selección", "Editar Selección");
        }      
    }
            
    private void validarCampo(){
        
        ValidarUsuario validar = new ValidarUsuario();
        validar.validarNuevoCurso(txtfNombre.getText());
        lblValidacion.setText(validar.getErrorCurso());               
    }
       
    @FXML
    public void listar(ActionEvent evento){
        
        listaDatos.clear();
        if (comboListar.getSelectionModel().getSelectedIndex() == 0) {
            listaDatos.addAll(consulta.llenarLista2(6));
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            listaDatos.addAll(consulta.llenarLista2(7));          
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            listaDatos.addAll(consulta.llenarLista2(8));
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            listaDatos.addAll(consulta.llenarLista2(5));
        }
        txtfNombre.setText("");
        txtfNombre.setDisable(true);
    }
    
    @FXML
    public void mapearDatos(){
        
     txtfNombre.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
     txtfNombre.setDisable(false);
     nombre = tablaResultados.getSelectionModel().getSelectedItem().toString();
    
    }
        
    private void resetear() {
        
        txtfNombre.setText("");
        txtfNombre.setDisable(true);
        nombre = null;    
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        txtfNombre.clear();
        dialogStage.close();
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        txtfNombre.setDisable(true);
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
        tablaResultados.setItems(listaDatos);    
    }    
}
