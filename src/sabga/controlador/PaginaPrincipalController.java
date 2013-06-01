
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Dialogs.DialogOptions;
import javafx.scene.control.Dialogs.DialogResponse;
import javafx.scene.input.KeyEvent;
import sabga.Sabga;


public class PaginaPrincipalController implements Initializable {
    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private RadioButton radioUsuario, radioMaterial;
    @FXML
    private ChoiceBox parametroBusqueda;
    @FXML 
    private Button botonBorrarBusqueda;
    
    private ObservableList parametroMaterial = FXCollections.observableArrayList();
    private ObservableList parametroUsuario = FXCollections.observableArrayList();
    
    public PaginaPrincipalController(){
               
        parametroMaterial.add("General");
        parametroMaterial.add("Título");
        parametroMaterial.add("Autor");
        parametroMaterial.add("Materia");
        
        parametroUsuario.add("General");
        parametroUsuario.add("Documento");
        parametroUsuario.add("Nombre");
        parametroUsuario.add("Apellidos");
    
    }
    
    @FXML
    private void opcionesBusqueda(ActionEvent event) {
        
        if(radioUsuario.isSelected()){
            
            parametroBusqueda.setItems(parametroUsuario);
                   
        }
        else if(radioMaterial.isSelected()){
            
            parametroBusqueda.setItems(parametroMaterial);
        }
        
    }
    
    @FXML
    private void salir(){
        
         Sabga ventanaPrincipal = new Sabga();
         
         DialogResponse responder = Dialogs.showConfirmDialog(ventanaPrincipal.getStage(), "Los cambios no guradados se perderan","Realmente desea salir?","Salir de SABGA", DialogOptions.OK_CANCEL);
         
         if(responder.equals(responder.OK)){
             
             System.exit(0);
         
         } 
    
    }
    
    @FXML
    private void borrarCampo(ActionEvent event){
        
        campoBusqueda.setText("");
        botonBorrarBusqueda.setVisible(false);
        
    }
    
    @FXML
    private void mostrarBoton(KeyEvent event){
       
        if ("".equals(campoBusqueda.getText())){
            
            botonBorrarBusqueda.setVisible(false);
       
        }
        else {
            
           botonBorrarBusqueda.setVisible(true); 
        }
    
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         parametroBusqueda.setItems(parametroMaterial);
         botonBorrarBusqueda.setVisible(false);
         
    }    
}