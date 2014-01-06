package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.atributos.Devolucion;
import sabga.atributos.Reserva;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;

public class PaginaPrincipalController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    private final Dialogo dialogo;
    private final  Atributos atributos;
    private boolean ventanaInicio = false;
    private boolean ventanaBusqueda = false;
    private final Consultas consulta;
    
    @FXML
    private TextField campoBusqueda;
    @FXML
    private RadioButton radioUsuario, radioMaterial;
    @FXML 
    private Button botonBorrarBusqueda;
    @FXML
    private MenuBar barraMenu;   
    @FXML
    private MenuButton menuAdmin;
    @FXML
    private AnchorPane panelCentral;
    @FXML
    private Pane panelInicio, panelBusqueda;
    @FXML
    private TableView tablaDevolucion, tablaReservas;
    @FXML
    private TableColumn clmnDocumentoD, clmnNombreD, clmnTituloD, clmnEjemplarD, clmnCodigoD, clmnFechaD, clmnDocumentoR,
                        clmnNombreR, clmnEjemplarR, clmnFechaR, clmnTituloR, clmnCodigoR;

    public PaginaPrincipalController(){       
        dialogo = new Dialogo();
        atributos = new Atributos();
        consulta = new Consultas();
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) {       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {      
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    @FXML
    public void ventanaInicio(){
        
        if (!ventanaInicio) {
            ventanaInicio = true;
            ventanaBusqueda = false;
            panelInicio.setDisable(false);
            panelInicio.setVisible(true);
            panelBusqueda.setDisable(true);
            panelBusqueda.setVisible(false);
            ventanaPrincipal.vistaInicial(panelInicio);
            ventanaPrincipal.setTitle("SABGA");
            reservasPendientes();
        }
    }
    
    @FXML
    public void ventanaBuscar(ActionEvent evento){
        
        if (!ventanaBusqueda) {
            ventanaBusqueda = true;
            ventanaInicio = false;
            panelInicio.setDisable(true);
            panelInicio.setVisible(false);
            panelBusqueda.setDisable(false);
            panelBusqueda.setVisible(true);
            ventanaPrincipal.vistaInicial(panelCentral);
            ventanaPrincipal.setTitle("Buscar");
        }
    }
    
    @FXML
    public void ventanaRegistroMaterial(){        
        ventanaPrincipal.cargarVista("paginaRegistroMaterial", "vista/RegistroMaterial.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroMaterial");
        ventanaPrincipal.setTitle("Registro de Material");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
       
    @FXML
    public void ventanaActualizarMaterial(ActionEvent evento){        
        ventanaPrincipal.cargarVista("paginaActualizarMaterial", "vista/EditarMaterial.fxml");
        ventanaPrincipal.cambiarVista("paginaActualizarMaterial");
        ventanaPrincipal.setTitle("Editar Material");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaActualizarEMA(ActionEvent evento){        
        ventanaPrincipal.cargarVista("paginaActualizarEMA", "vista/EditarEMA.fxml");
        ventanaPrincipal.cambiarVista("paginaActualizarEMA");
        ventanaPrincipal.setTitle("Editar Opciones de Material");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaPrestamo(){        
        ventanaPrincipal.cargarVista("paginaPrestamo", "vista/Prestamo.fxml");
        ventanaPrincipal.cambiarVista("paginaPrestamo");
        ventanaPrincipal.setTitle("Préstamo");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaRegistroUsuarios(){        
        ventanaPrincipal.cargarVista("paginaRegistroUsuarios", "vista/RegistroUsuario.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroUsuarios");
        ventanaPrincipal.setTitle("Registro de Usuarios");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaEstadoUsuario(){        
        ventanaPrincipal.cargarVista("paginaEstadoUsuario", "vista/EditarUsuario.fxml");
        ventanaPrincipal.cambiarVista("paginaEstadoUsuario");
        ventanaPrincipal.setTitle("Cuentas de Usuario");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaRegistroAdmin(ActionEvent evento){        
        ventanaPrincipal.cargarVista("paginaRegistroBibliotecario", "vista/RegistroBibliotecario.fxml");
        ventanaPrincipal.cambiarVista("paginaRegistroBibliotecario");
        ventanaPrincipal.setTitle("Registrar Bibliotecario");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaEditarAdmin(ActionEvent evento){        
        ventanaPrincipal.cargarVista("paginaEditarBibliotecario", "vista/EditarBibliotecario.fxml");
        ventanaPrincipal.cambiarVista("paginaEditarBibliotecario");
        ventanaPrincipal.setTitle("Editar Datos del Bibliotecario");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaDevolucion(){        
        ventanaPrincipal.cargarVista("paginaDevolucion", "vista/Devolucion.fxml");
        ventanaPrincipal.cambiarVista("paginaDevolucion");
        ventanaPrincipal.setTitle("Devolución o Renovación");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaReservaEscritorio(){        
        ventanaPrincipal.cargarVista("paginaReserva", "vista/ReservaEscritorio.fxml");
        ventanaPrincipal.cambiarVista("paginaReserva");
        ventanaPrincipal.setTitle("Reservar");
        ventanaPrincipal.mostrarVistas();
        ventanaInicio = false;
        ventanaBusqueda = false;
    } 
        
    @FXML
    public void ventanaCuenta(ActionEvent evento){
        ventanaPrincipal.mostrarVistas();
        ventanaPrincipal.cargarVista("paginaCuenta", "vista/Cuenta.fxml");
        ventanaPrincipal.cambiarVista("paginaCuenta");
        ventanaPrincipal.setTitle("Ajustes de la Cuenta");
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
    @FXML
    public void ventanaPazySalvo(ActionEvent evento){
        ventanaPrincipal.mostrarVistas();
        ventanaPrincipal.cargarVista("paginaPazySalvo", "vista/PazySalvo.fxml");
        ventanaPrincipal.cambiarVista("paginaPazySalvo");
        ventanaPrincipal.setTitle("Expedición de Paz y Salvo");
        ventanaInicio = false;
        ventanaBusqueda = false;
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
    public void dialogoAcercaDe(ActionEvent evento){
        dialogo.mostrarDialogo("vista/dialogos/AcercaDe.fxml", "Acerca de SABGA", ventanaPrincipal.getPrimaryStage(), null, 18);
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
        }else {
           botonBorrarBusqueda.setVisible(true); 
        }    
    }
    
    public void entregasDia(){
        if(tablaDevolucion.getSelectionModel().getSelectedItem() != null){
            ventanaDevolucion();
        }    
    }
    
    public void reservasDia(){
        if(tablaReservas.getSelectionModel().getSelectedItem() != null){
            ventanaPrestamo();
        }
    }
    
    public String getCampoBusqueda(){
        return campoBusqueda.getText();
    }
    
    public void setUsuario(String usuario){
          menuAdmin.setText(atributos.getUsuarioAdmin());
    }
    
    private void devolucionesDia(){
        
        clmnDocumentoD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("documento"));
        clmnNombreD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("nombre"));
        clmnTituloD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("titulo"));
        clmnEjemplarD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("ejemplar"));
        clmnCodigoD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("codigo"));
        clmnFechaD.setCellValueFactory(new PropertyValueFactory<Devolucion, String>("fecha")); 
        tablaDevolucion.setItems(consulta.getListaDevolucionDia());
    }
    
    private void reservasPendientes(){
        
        clmnDocumentoR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("documento"));
        clmnNombreR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombre"));
        clmnTituloR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido"));
        clmnEjemplarR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("correo"));
        clmnCodigoR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        clmnFechaR.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fecha"));
        tablaReservas.setItems(consulta.getListaReservasDia());
    }
    
    private void inicio(){
        
        devolucionesDia();
        reservasPendientes();
        botonBorrarBusqueda.setVisible(false);
        barraMenu.setPrefWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        panelInicio.setDisable(false);
        panelInicio.setVisible(true);
        panelBusqueda.setDisable(true);
        panelBusqueda.setVisible(false);  
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        inicio();
    }    
    
}