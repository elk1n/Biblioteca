
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Usuario;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;

/**
 * @author Elk1n 
 */

public class EditarBibliotecarioController implements Initializable, ControlledScreen {
    public Conexion con;
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    
    @FXML 
    private ComboBox comboEstado, comboTipo;  
    @FXML 
    private TableView tablaAdmin;    
    @FXML 
    private TableColumn tDocumento, tNombre, tApellido, tUsuario, tCorreo, tTelefono, tEstado, clmnTipo;
    private final ObservableList<Usuario> listaAdmin;
    private final ObservableList estados, tipo;
    private final Consultas consulta;
   
    public EditarBibliotecarioController(){ 
        
        listaAdmin = FXCollections.observableArrayList();
        estados = FXCollections.observableArrayList();
        tipo = FXCollections.observableArrayList();
        consulta = new Consultas();        
    }
    
    @FXML
    public void cambiarEstado(ActionEvent evento){   
        cambiarEstado();
    }
    
    public void mapear(){
        mapearDatos();
    }
    
    private void mapearDatos(){
        
        if(tablaAdmin.getSelectionModel().getSelectedItem() != null){
            comboEstado.getSelectionModel().select(listaAdmin.get(tablaAdmin.getSelectionModel().getSelectedIndex()).getEstado());
            comboTipo.getSelectionModel().select(listaAdmin.get(tablaAdmin.getSelectionModel().getSelectedIndex()).getTipo());
        }
    }   
    
    private void cambiarEstado(){
        
        if(tablaAdmin.getSelectionModel().getSelectedItem() != null && comboEstado.getSelectionModel().getSelectedItem() != null &&
           comboTipo.getSelectionModel().getSelectedItem() != null){
            int estado = comboEstado.getSelectionModel().getSelectedItem().toString().trim().equals("Habilitado") ? 1: 2;
            consulta.editarBibliotecario(1, listaAdmin.get(tablaAdmin.getSelectionModel().getSelectedIndex()).getDocumento(), estado, 
                                         comboTipo.getSelectionModel().getSelectedItem().toString().trim(), null, null, null, null,null, null);
            if (consulta.getMensaje() == null) {
                 listaAdmin.clear();
                 listaAdmin.addAll(consulta.getListaBibliotecarios());
                Utilidades.mensaje(null, "La información se ha actualizado correctamente.", "", "Editar Información Bibliotecario");
            } else {
                Utilidades.mensajeError(null, consulta.getMensaje(), "La información no ha sido actualizada.", "Error Edición");
            }
        }
    }
    
    private void inicio(){
        
        tDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));        
        tNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        tUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("usuario"));
        tCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        tTelefono.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefono"));
        tEstado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        listaAdmin.addAll(consulta.getListaBibliotecarios());
        tablaAdmin.setItems(listaAdmin);
        estados.add("Habilitado");
        estados.add("Inhabilitado");
        tipo.add("Bibliotecario");
        tipo.add("Auxiliar");
        comboTipo.setItems(tipo);         
        comboEstado.setItems(estados);
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {        
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }
}
