
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
import javafx.scene.input.KeyEvent;
import sabga.Sabga;
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;


public class PaginaPrincipalController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private ObservableList parametroMaterial = FXCollections.observableArrayList();
    private ObservableList parametroUsuario = FXCollections.observableArrayList();
    Dialogo dialogo;
    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private RadioButton radioUsuario, radioMaterial;
    @FXML
    private ChoiceBox parametroBusqueda;
    @FXML 
    private Button botonBorrarBusqueda;
    
    public PaginaPrincipalController(){
        
        dialogo = new Dialogo();
               
        parametroMaterial.add("General");
        parametroMaterial.add("Título");
        parametroMaterial.add("Autor");
        parametroMaterial.add("Materia");
        parametroMaterial.add("Editorial");
        
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
    public void ventanaRegistroMaterial(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaRegistroMaterial");        
    }
    
    @FXML
    public void ventanaInicio(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaInicio1");
    }
    
    @FXML
    public void ventanaActualizarMaterial(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaActualizarMaterial");
    }
    
    @FXML
    public void ventanaActualizarEMA(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaActualizarEMA");
    }
    
    @FXML
    public void ventanaPrestamo(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaPrestamo");
    }
    
    @FXML
    public void ventanaRegistroUsuarios(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaRegistroUsuarios");
    }
    
    @FXML
    public void ventanaEstadoUsuario(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaEstadoUsuario");
    }
    
    @FXML
    public void ventanaRegistroAdmin(ActionEvent evento){
       
        ventanaPrincipal.cambiarVista("paginaRegistroAdmin");
    }
    
    @FXML
    public void vantanaEditarAdmin(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaEditarAdministrador");
    }
    
    @FXML
    public void ventanaDevolucion(ActionEvent evento){
        
        ventanaPrincipal.cambiarVista("paginaDevolucion");
    }
    @FXML
    public void dialogoNuevoAutor(ActionEvent evento){
        
        dialogo.mostrarDialogo("vista/dialogos/NuevoAutor.fxml", "Nuevo Autor", ventanaPrincipal.getPrimaryStage(), null, 1);
    }
    
    @FXML
    public void dialogoNuevaMateria(ActionEvent evento){

         dialogo.mostrarDialogo("vista/dialogos/NuevaMateria.fxml", "Nueva Materia", ventanaPrincipal.getPrimaryStage(), null, 2);
    }
    
    @FXML
    public void dialogoNuevaEditorial(ActionEvent evento){
        
             dialogo.mostrarDialogo("vista/dialogos/NuevaEditorial.fxml", "Nueva Editorial", ventanaPrincipal.getPrimaryStage(), null, 3);
    }
    
    @FXML
    public void dialogoNuevoTipoMaterial(ActionEvent evento){
        
             dialogo.mostrarDialogo("vista/dialogos/NuevoTipoMaterial.fxml", "Nuevo Tipo de Material", ventanaPrincipal.getPrimaryStage(), null, 8);
    }
    
   @FXML
    private void opcionesBusqueda(ActionEvent evento) {
        
        if(radioUsuario.isSelected()){
            
            parametroBusqueda.setItems(parametroUsuario);
                   
        }
        else if(radioMaterial.isSelected()){
            
            parametroBusqueda.setItems(parametroMaterial);
        }
        
    }
    
    @FXML
    private void salir(ActionEvent evento){

        Utilidades.mensajeConfirmacion(ventanaPrincipal.getStage(), "Los cambios no guradados se perderan", "Realmente desea salir?","Salir de SABGA");
         if(Utilidades.getMensajeConfimacion().equals(Utilidades.getMensajeConfimacion().OK)){
             
             System.exit(0);         
         }     
    }
    
    @FXML
    public void cerrarSesion(ActionEvent evento){
        
        ventanaPrincipal.cerrarSesion();
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