
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;


public class PaginaPrincipalController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private final ObservableList parametroMaterial = FXCollections.observableArrayList();
    private final ObservableList parametroUsuario = FXCollections.observableArrayList();
    private final Dialogo dialogo;
    private final  Atributos atributos;
    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private RadioButton radioUsuario, radioMaterial;
    @FXML 
    private Button botonBorrarBusqueda;
    @FXML
    private MenuBar barraMenu;   
    @FXML
    private MenuItem menuNuevoAutor;
    @FXML
    private MenuButton menuAdmin;
    @FXML
    private HBox hboxAdmin;
    
    public MenuItem getMenu(){  
        return menuNuevoAutor;
    }
    
    public PaginaPrincipalController(){
        
        dialogo = new Dialogo();
        atributos = new Atributos();
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
        ventanaPrincipal.cargarVista("paginaRegistroMaterial", "vista/RegistroMaterial.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroMaterial");        
    }
    
    @FXML
    public void ventanaInicio(){
        ventanaPrincipal.cargarVista("paginaInicio", "vista/PaginaInicio.fxml");
        ventanaPrincipal.cambiarVista("paginaInicio");
    }
    
    @FXML
    public void ventanaActualizarMaterial(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaActualizarMaterial", "vista/EditarMaterial.fxml");
        ventanaPrincipal.cambiarVista("paginaActualizarMaterial");
    }
    
    @FXML
    public void ventanaActualizarEMA(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaActualizarEMA", "vista/EditarEMA.fxml");
        ventanaPrincipal.cambiarVista("paginaActualizarEMA");
    }
    
    @FXML
    public void ventanaPrestamo(ActionEvent evento){          
        ventanaPrincipal.cargarVista("paginaPrestamo", "vista/Prestamo.fxml");
        ventanaPrincipal.cambiarVista("paginaPrestamo");
    }
    
    @FXML
    public void ventanaRegistroUsuarios(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaRegistroUsuarios", "vista/RegistroUsuario.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroUsuarios");
    }
    
    @FXML
    public void ventanaEstadoUsuario(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaEstadoUsuario", "vista/EditarUsuario.fxml");
        ventanaPrincipal.cambiarVista("paginaEstadoUsuario");
    }
    
    @FXML
    public void ventanaRegistroAdmin(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaRegistroBibliotecario", "vista/RegistroBibliotecario.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroBibliotecario");
    }
    
    @FXML
    public void ventanaEditarAdmin(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaEditarBibliotecario", "vista/EditarBibliotecario.fxml");
        ventanaPrincipal.cambiarVista("paginaEditarBibliotecario");
    }
    
    @FXML
    public void ventanaDevolucion(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaDevolucion", "vista/Devolucion.fxml");
        ventanaPrincipal.cambiarVista("paginaDevolucion");
    }
    
    @FXML
    public void ventanaBuscar(ActionEvent evento){

        atributos.setDatoBusqueda(campoBusqueda.getText());
        ventanaPrincipal.cargarVista("paginaBuscar", "vista/Buscar.fxml");
        ventanaPrincipal.cambiarVista("paginaBuscar");
    }
    
    @FXML
    public void ventanaCuenta(ActionEvent evento){
        ventanaPrincipal.cargarVista("paginaCuenta", "vista/Cuenta.fxml");
        ventanaPrincipal.cambiarVista("paginaCuenta");
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
    public void dialogoNuevaClaseMaterial(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/NuevaClaseMaterial.fxml", "Nueva Clase de Material", ventanaPrincipal.getPrimaryStage(), null, 9);
    }
    
    @FXML
    public void dialogoNuevoGrado(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/NuevoGrado.fxml", "Nuevo Grado", ventanaPrincipal.getPrimaryStage(), null, 10);
    }
    
    @FXML
    public void dialogoNuevoCurso(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/NuevoCurso.fxml", "Nuevo Curso", ventanaPrincipal.getPrimaryStage(), null, 11);
    }
    
    @FXML
    public void dialogoNuevaJornada(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/NuevaJornada.fxml", "Nueva Jornada", ventanaPrincipal.getPrimaryStage(), null, 12);
    }
    
    @FXML
    public void dialogoNuevoTipoUsuario(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/NuevoTipoUsuario.fxml", "Nuevo Tipo de Usuario", ventanaPrincipal.getPrimaryStage(), null, 13);
    }
     
    @FXML
    public void dialogoEditarOpcionesUsuario(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/EditarOpcionesUsuario.fxml", "Editar Opciones de Usuario", ventanaPrincipal.getPrimaryStage(), null, 14);
    }
    
    @FXML
    public void dialogoPreferencias(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/Preferencias.fxml", "Preferencias", ventanaPrincipal.getPrimaryStage(), null, 15);
    }
    
    @FXML
    public void dialogoAyuda(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/Ayuda.fxml", "Ayuda", ventanaPrincipal.getPrimaryStage(), null, 16);
    }
    
   @FXML
    private void opcionesBusqueda(ActionEvent evento) {
        
       
    }
    
    @FXML
    private void salir(ActionEvent evento){

        Utilidades.mensajeConfirmacion(ventanaPrincipal.getPrimaryStage(), "Los cambios no guardados se perderan", "Realmente desea salir?","Salir de SABGA");
         if(Utilidades.getMensajeConfimacion() == Dialogs.DialogResponse.OK){             
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
    
    public String getCampoBusqueda(){
        return campoBusqueda.getText();
    }
    
    public void setUsuario(String usuario){
          menuAdmin.setText(atributos.getUsuarioAdmin());
    }
    
    @FXML
    public void prueba(ActionEvent evento){           
           atributos.setDatoBusqueda("Esto es una prueba..... de");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
         botonBorrarBusqueda.setVisible(false);
         barraMenu.setPrefWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
       
    }    

    
}