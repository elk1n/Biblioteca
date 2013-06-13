
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
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;


public class PaginaPrincipalController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private ObservableList parametroMaterial = FXCollections.observableArrayList();
    private ObservableList parametroUsuario = FXCollections.observableArrayList();
    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private RadioButton radioUsuario, radioMaterial;
    @FXML
    private ChoiceBox parametroBusqueda;
    @FXML 
    private Button botonBorrarBusqueda;
    
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
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
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
        
        ventanaPrincipal = new Sabga();
         
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
   
    public void ventanaRegistroMaterial(){
    
        ventanaPrincipal.cambiarVista("paginaRegistroMaterial");        
    }
    
    public void ventanaInicio(){
        
        ventanaPrincipal.cambiarVista("paginaInicial");
    }
    
    public void ventanaActualizarMaterial(){
        
        ventanaPrincipal.cambiarVista("paginaActualizarMaterial");
    }
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
         parametroBusqueda.setItems(parametroMaterial);
         botonBorrarBusqueda.setVisible(false);
         
    }    

    
}