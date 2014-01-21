package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import sabga.Sabga;
import sabga.configuracion.ControlledScreen;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.atributos.Autor;
import sabga.atributos.Devolucion;
import sabga.atributos.Ejemplar;
import sabga.atributos.Materia;
import sabga.atributos.Material;
import sabga.atributos.Reserva;
import sabga.atributos.Usuario;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarUsuario;

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
    private Button botonBorrarBusqueda, btnDetalleUsuario, btnMultas, btnCodigoBarras;
    @FXML
    private MenuBar barraMenu;   
    @FXML
    private Label lblResultadoUsuario, lblResultadoMaterial;
    @FXML
    private MenuButton menuAdmin;
    @FXML
    private ComboBox<String> comboListarUsuario, comboListarMaterial, comboListarxMateria, comboListarxAutor;
    @FXML
    private Pane panelInicio, panelBusqueda, panelBuscarUsuario, panelBuscarMaterial;
    @FXML
    private TableView<Devolucion> tablaDevolucion;
     @FXML
    private TableView<Reserva> tablaReservas; 
    @FXML
    private TableView<Usuario>tablaUsuarios;
    @FXML
    private TableView<Material>tablaMaterial;
    @FXML
    private TableView<Ejemplar>tablaEjemplar;
    @FXML
    private TableView<Autor>tablaAutores;
    @FXML
    private TableColumn<Devolucion, String> clmnDocumentoD, clmnNombreD, clmnTituloD, clmnEjemplarD, clmnCodigoD, clmnFechaD;
    @FXML
    private TableColumn<Reserva, String> clmnDocumentoR, clmnNombreR, clmnEjemplarR, clmnFechaR, clmnTituloR, clmnCodigoR;
    @FXML
    private TableColumn<Usuario, String> clmnTipo, clmnDocumento, clmnNombre, clmnApellido, clmnCorreo, clmnTelefono, clmnGrado,
                                         clmnCurso, clmnJornada, clmnEstado;
    @FXML
    private TableColumn<Material, String> clmnTituloM, clmnCodigoM, clmnTipoM, clmnClaseM, clmnEditorialM, clmnPublicacionM,
                                          clmnAnioM, clmnNumeroM;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplarE,clmnEstadoE, clmnDispoE;
    @FXML
    private TableColumn<Autor, String> clmnNombreA, clmnApellidoA;
    @FXML
    private ListView<Materia>  listaMaterias;
    @FXML
    private Menu menuAuxiliar;
    @FXML
    private MenuItem menuPazysalvo, menuPreferencias, menuMultas, menuDetalleUsuario, menuCodigoBarras;
    private final ObservableList<Usuario> listaCorreos;
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<Autor> listaAutor;
    private final ValidarUsuario validar;

    public PaginaPrincipalController(){       
        dialogo = new Dialogo();
        atributos = new Atributos();
        consulta = new Consultas();        
        validar = new ValidarUsuario();
        listaCorreos = FXCollections.observableArrayList();
        listaUsuarios = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        listaAutor = FXCollections.observableArrayList();
    }
    
    @Override   
    public void setScreenParent(ScreensController screenParent) {       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {      
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    @FXML
    public void busqueda(ActionEvent evento){    
        ventanaBuscar();
        buscar();
    }
    
    @FXML
    public void listarLosUsuarios(ActionEvent event){
        listarUsuarios();
    }
    
    @FXML
    public void listarElMaterial(ActionEvent evento){
        listarMaterial();
    }
    
    @FXML
    public void listarMaterialxMateria(ActionEvent evento){    
        listarPorMaterias();
    }
    
    @FXML
    public void listarMaterialxAutor(ActionEvent evento){    
        listarPorAutor();
    }
    
    @FXML
    public void dialogoDetalleUsuario(ActionEvent evento){
        detalleUsuario();
    }
    
    @FXML
    public void dialogoCodigoDeBarras(ActionEvent evento){
        codigoDeBarras();
    }
    
    @FXML
    public void dialogoMultasUsuario(){
        multasUsuario();
    }
    
    public void seleccionDeUsuario(){
        seleccionarUsuario();
    }
    
    public void listarEjemplar(){
        listarEjemplares();
    }
    
    @FXML
    public void verUsuarios(ActionEvent evento){       
        radioUsuario.setSelected(true);
        ventanaBuscar();
        buscar();    
    }
    
    @FXML
    public void verMaterial(ActionEvent evento){       
        radioMaterial.setSelected(true);
        ventanaBuscar();
        buscar();    
    }
    
    private void codigoDeBarras(){
        
        if(tablaMaterial.getSelectionModel().getSelectedItem() != null){
            menuCodigoBarras.setDisable(true);
            btnCodigoBarras.setDisable(true);
            dialogo.dialogoCodigoBarras(ventanaPrincipal.getPrimaryStage(), completarConCeros(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getIdMaterial()),
                                    Utilidades.initCap(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getTitulo())
                                    ,"Puede guardar el código de barras para imprimirlo posteriormente o imprimirlo directamente.", 1); 
            menuCodigoBarras.setDisable(false);
            btnCodigoBarras.setDisable(false);
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un material de la lista. ", "", "Selección Material");
        }        
    }
    
    private void listarPorAutor(){
    
        if (!comboListarxAutor.getSelectionModel().isEmpty()) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialInicio(4, comboListarxAutor.getSelectionModel().getSelectedItem().toString()));
            tablaMaterial.setItems(listaMaterial);
            lblResultadoMaterial.setText(null);
            listaEjemplares.clear();
            listaMaterias.getItems().clear();
            listaAutor.clear();
        }
        
    }
    
    private void listarPorMaterias(){
    
        if (!comboListarxMateria.getSelectionModel().isEmpty()) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialInicio(3, comboListarxMateria.getSelectionModel().getSelectedItem().toString()));
            tablaMaterial.setItems(listaMaterial);
            lblResultadoMaterial.setText(null);
            listaEjemplares.clear();
            listaMaterias.getItems().clear();
            listaAutor.clear();
        }
        
    }
    
    private void listarEjemplares(){
    
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            listaEjemplares.clear();
            clmnEjemplarE.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
            clmnEstadoE.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
            clmnDispoE.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("disponibilidad"));
            listaEjemplares.addAll(consulta.listaEjemplares(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getIdMaterial()));
            tablaEjemplar.setItems(listaEjemplares);
            listaMaterias.setItems(consulta.listaMaterias(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getIdMaterial()));
            listaAutores();
        }    
    }
    
    private void listaAutores(){
        
        listaAutor.clear();
        clmnNombreA.setCellValueFactory(new PropertyValueFactory<Autor, String>("nombreAutor"));
        clmnApellidoA.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
        listaAutor.addAll(consulta.listaAutores(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getIdMaterial()));
        tablaAutores.setItems(listaAutor);     
    }
    
    private void listarMaterial(){    
        
        if (!comboListarMaterial.getSelectionModel().isEmpty()) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialInicio(2,comboListarMaterial.getSelectionModel().getSelectedItem().toString()));
            tablaMaterial.setItems(listaMaterial);
            lblResultadoMaterial.setText(null);
            listaEjemplares.clear();
            listaMaterias.getItems().clear();
            listaAutor.clear();
        }
    }
    
    private void buscarMaterial(){
    
         if (!"".equals(campoBusqueda.getText())) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialInicio(1, campoBusqueda.getText().trim()));
            tablaMaterial.setItems(listaMaterial);
            listaEjemplares.clear();
            listaMaterias.getItems().clear();
            listaAutor.clear();
            if(listaMaterial.isEmpty()){
                comboListarMaterial.getSelectionModel().clearSelection();
                comboListarxMateria.getSelectionModel().clearSelection();
                comboListarxAutor.getSelectionModel().clearSelection();
                lblResultadoMaterial.setText("No se han encontrado resultados.");
            }else{
                lblResultadoMaterial.setText(null);
                comboListarMaterial.getSelectionModel().clearSelection();
                comboListarxMateria.getSelectionModel().clearSelection();
                comboListarxAutor.getSelectionModel().clearSelection();
            }
        }   
    }
    
    private void prepararTablaMaterial(){
    
        clmnTituloM.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));
        clmnCodigoM.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnTipoM.setCellValueFactory(new PropertyValueFactory<Material, String>("tipo"));
        clmnClaseM.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));
        clmnEditorialM.setCellValueFactory(new PropertyValueFactory<Material, String>("editorial"));
        clmnPublicacionM.setCellValueFactory(new PropertyValueFactory<Material, String>("publicacion"));
        clmnAnioM.setCellValueFactory(new PropertyValueFactory<Material, String>("anioPublicacion"));
        clmnNumeroM.setCellValueFactory(new PropertyValueFactory<Material, String>("numeroPaginas"));
        listaMaterial.clear();
    }
    
    private void prepararTablaUsuarios(){
    
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        clmnTelefono.setCellValueFactory(new PropertyValueFactory<Usuario, String>("telefono"));
        clmnGrado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("grado"));
        clmnCurso.setCellValueFactory(new PropertyValueFactory<Usuario, String>("curso"));
        clmnJornada.setCellValueFactory(new PropertyValueFactory<Usuario, String>("jornada"));
        clmnEstado.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        listaUsuarios.clear();
    
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
    
    private void ventanaBuscar(){
        
        if (!ventanaBusqueda) {
            ventanaBusqueda = true;
            ventanaInicio = false;
            panelInicio.setDisable(true);
            panelInicio.setVisible(false);
            panelBusqueda.setDisable(false);
            panelBusqueda.setVisible(true);
            ventanaPrincipal.vistaInicial(panelBusqueda);
            ventanaPrincipal.setTitle("Buscar");            
        }
    }
    
    private void buscar(){
        
        if(radioUsuario.isSelected()){
            panelBuscarMaterial.setVisible(false);
            panelBuscarMaterial.setDisable(true);
            panelBuscarUsuario.setVisible(true);
            panelBuscarUsuario.setDisable(false);
            buscarUsuario();
            
        }else{
            panelBuscarUsuario.setVisible(false);
            panelBuscarUsuario.setDisable(true);
            panelBuscarMaterial.setVisible(true);
            panelBuscarMaterial.setDisable(false);
            buscarMaterial();
            
        }
    }
    
    private void listarUsuarios(){
        
         if (!comboListarUsuario.getSelectionModel().isEmpty()) {            
            prepararTablaUsuarios();
            if (comboListarUsuario.getSelectionModel().getSelectedItem().toString().contains("Todos")) {
                listaUsuarios.addAll(consulta.getListaUsuariosRecibo(4, null));
            } else {
                listaUsuarios.addAll(consulta.getListaUsuariosRecibo(3, comboListarUsuario.getSelectionModel().getSelectedItem().toString()));
            }
            tablaUsuarios.setItems(listaUsuarios);
            lblResultadoUsuario.setText(null);
        }
    }
    
    private void buscarUsuario(){
    
         if (!"".equals(campoBusqueda.getText())) {
            prepararTablaUsuarios();
            listaUsuarios.addAll(consulta.getListaUsuariosRecibo(5, campoBusqueda.getText().trim()));
            tablaUsuarios.setItems(listaUsuarios);
            if(listaUsuarios.isEmpty()){
                comboListarUsuario.getSelectionModel().clearSelection();
                lblResultadoUsuario.setText("No se han encontrado resultados.");
            }else{
                lblResultadoUsuario.setText(null);
                comboListarUsuario.getSelectionModel().clearSelection();
            }
        }   
    }
      
    private void detalleUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            menuDetalleUsuario.setDisable(true);
            btnDetalleUsuario.setDisable(true);
            dialogo.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            menuDetalleUsuario.setDisable(false);  
            btnDetalleUsuario.setDisable(false); 
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void multasUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            menuMultas.setDisable(true);
            btnMultas.setDisable(true);
            dialogo.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            menuMultas.setDisable(false); 
            btnMultas.setDisable(false);
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
      
    private void seleccionarUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            atributos.setDocumentoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            atributos.setNombreUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre());
            atributos.setApellidoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido());
            atributos.setCorreoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCorreo());   
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
    public void ventanaVerPrestamos(ActionEvent evento){
        ventanaPrincipal.mostrarVistas();
        ventanaPrincipal.cargarVista("paginaVerPrestamos", "vista/VerPrestamos.fxml");
        ventanaPrincipal.cambiarVista("paginaVerPrestamos");
        ventanaPrincipal.setTitle("Listado de Préstamos");
        ventanaInicio = false;
        ventanaBusqueda = false;
    }
    
     @FXML
    public void ventanaVerMultas(ActionEvent evento){
        ventanaPrincipal.mostrarVistas();
        ventanaPrincipal.cargarVista("paginaVerMultas", "vista/VerMultas.fxml");
        ventanaPrincipal.cambiarVista("paginaVerMultas");
        ventanaPrincipal.setTitle("Multas");
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
    public void salir(ActionEvent evento){

        Utilidades.mensajeOpcion(ventanaPrincipal.getPrimaryStage(), "Los cambios no guardados se perderán", "Realmente desea salir?","Salir de SABGA");
         if(Utilidades.getMensajeOpcion() == Dialogs.DialogResponse.YES){             
             System.exit(0);         
         }     
    }
    
    @FXML
    public void cerrarSesion(ActionEvent evento){
        ventanaPrincipal.cerrarSesion();
        ventanaInicio();
    }
    
    @FXML
    public void borrarCampo(ActionEvent event){        
        campoBusqueda.setText("");
        lblResultadoUsuario.setText(null);
        lblResultadoMaterial.setText(null);
        botonBorrarBusqueda.setVisible(false);        
    }
    
    @FXML
    public void mostrarBoton(KeyEvent event){
       
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
        consulta.enviarCorreoUsuarios();
    }
    
    private void enviarCorreos() {
        
        if (consulta.getEstadoCorreos() == 1) {
            listaCorreos.clear();
            listaCorreos.addAll(consulta.enviarCorreoUsuarios());
            if (!listaCorreos.isEmpty()) {
                boolean resultado = false;
                for (Usuario u : listaCorreos) {
                    String mensaje = "Señor(a) " + u.getNombre() + " recuerde que tiene pendiente la devolución de: \n\n"
                                    + consulta.getDetalleCorreoUsuario(u.getDocumento());
                    if (validar.validarCorreo(u.getCorreo(), 90)) {
                      resultado = Utilidades.enviarCorreo(u.getCorreo(), "Entrega de material bibliográfico", mensaje);
                    }
                }
                if(resultado){
                    consulta.setEstadoCorreos(0);
                }
                
            }
        }  
    }
    
    private void cancelarReservas(){
        consulta.cancelarReserva();
    }
    
    private String completarConCeros(int numero){
    
        String dato = String.valueOf(numero);
        int longitud = 10-dato.length();
        
            if(longitud<10){
                for(int i=0; i<longitud; i++){
                    dato="0"+dato;
                }            
            }            
        return dato;
    }
    
    private void tipoUsuario(){
        
        if(atributos.getTipoUsuario()!= null){            
            if(atributos.getTipoUsuario().contains("auxiliar")){
                menuAuxiliar.setDisable(true);
                menuPazysalvo.setDisable(true);
                menuPreferencias.setDisable(true);
            }
        }
    
    }
    
    private void inicio(){
        
        //cancelarReservas();
        // ventanaInicio();
        devolucionesDia();
        reservasPendientes();
        comboListarUsuario.setItems(consulta.llenarLista(6));        
        comboListarUsuario.getItems().add("Todos");
        comboListarMaterial.setItems(consulta.llenarLista(2));
        comboListarxMateria.setItems(consulta.llenarLista(3));
        comboListarxAutor.setItems(consulta.llenarLista(12));
        botonBorrarBusqueda.setVisible(false);
        barraMenu.setPrefWidth(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        panelInicio.setDisable(false);
        panelInicio.setVisible(true);
        panelBusqueda.setDisable(true);
        panelBusqueda.setVisible(false);
        enviarCorreos();
        tipoUsuario();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        inicio();
    }    
    
}