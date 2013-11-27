
package sabga.controlador;

import eu.schudt.javafx.controls.calendar.DatePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialogs;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Ejemplar;
import sabga.atributos.Material;
import sabga.atributos.Prestamo;
import sabga.atributos.Reserva;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Consultas;
import sabga.modelo.Seleccion;
import sabga.modelo.ValidarMaterial;


public class PrestamoController implements Initializable, ControlledScreen{
        
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML 
    private ComboBox comboListar, comboListaUsuario;
    @FXML 
    private TextField txtfBuscar, txtfBuscarUsuario, txtfBuscarReserva;
    @FXML 
    private Label lblNombre, lblDocumento, lblCorreo, lblGrado, lblCurso, lblJornada, lblTelefono, lblDireccion, lblMulta,
                  lblValidarDocumento, lblValidarFecha, lblValidarEjemplar;
    @FXML
    private Button btnDetalle;
    @FXML
    private HBox hboxFecha;
    @FXML
    private TableView tablaMaterial, tablaEjemplar, tablaUsuarios, tablaReserva, tablaDetalleReserva, tablaPrestamo;
    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase, clmnTipo, clmnEjemplar, clmnEstado, clmnDispo, clmnDocumento,
                        clmnNombre, clmnApellido, clmnCorreo, clmnTipoUsuario, clmnEstadoUsuario, clmnDocumentoRe, clmnNombreRe,
                        clmnApellidoRe, clmnFecha, clmnEstadoRe, clmnTituloDe, clmnCodigoDe, clmnAutor, clmnEditorial, clmnMateria,
                        clmnEjemplarRe, clmnEjemplarPr, clmnTituloPr, clmnCodigoPr, clmnCorreoRe;
    private final DatePicker fechaDevolucion;
    private final Consultas consulta;
    private final Seleccion select;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Reserva> listaReservas;
    private final ObservableList<Material> listaDetalleRe;
    private final ObservableList<Prestamo> listaPrestamo;
    private final Dialogo dialogos;
    private String titulo, codigoClasificacion, tipoUsuario;  
    private boolean reserva = false;
    private ValidarMaterial validarPrestamo;
    private ConfirmarMaterial confirmarMaterial;
    private Calendar calendario;
    
    public PrestamoController(){
       
       fechaDevolucion = new DatePicker();
       fechaDevolucion.setDateFormat(new SimpleDateFormat("YYYY-MM-dd"));       
       fechaDevolucion.getCalendarView().showTodayButtonProperty().setValue(Boolean.FALSE);
       fechaDevolucion.getStylesheets().add("sabga/vista/css/DatePicker.css");
       listaMaterial = FXCollections.observableArrayList();
       listaEjemplares = FXCollections.observableArrayList();
       listaUsuarios = FXCollections.observableArrayList();
       listaReservas = FXCollections.observableArrayList();
       listaDetalleRe = FXCollections.observableArrayList();
       listaPrestamo = FXCollections.observableArrayList();
       consulta = new Consultas();
       select = new Seleccion();
       dialogos = new Dialogo();
       
    }
    
    @FXML
    public void listarMaterial(ActionEvent evento){                        
        prepararTablaMaterial();
        listar();    
    }
    
    public void cargarEjemlar(){    
        mapearEjemplar();
    }
    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento){        
       detalleMaterialEjemplar();
    }
    
    @FXML
    public void dialogoDetalleMaterialRe(ActionEvent evento){    
        detalleMaterialRe();
    }
    
    @FXML
    public void buscarMaterial(ActionEvent evento){
        buscarMaterial();
    }
    
    @FXML
    public void listarUsuarios(ActionEvent evento){
        listarUsuario();  
    }
    
    @FXML
    public void buscarUsuario(ActionEvent evento){    
        buscarUsuario();
    }
    
    @FXML
    public void listarReservas(ActionEvent evento){
        listarReservas();
    }
    
    @FXML
    public void buscarReserva(ActionEvent evento){
        buscarReserva();        
    }
    
    @FXML
    public void removerEjemplar(ActionEvent evento){
        removerEjemplar();
    }
    
    public void cargarDetalleReserva(){
        detalleReserva();
        seleccionarReserva();
    }
    
    public void addEjemplar(){
        sumarEjemplar();
    }
    
    public void addUsuario(){
        seleccionarUsuario();
    }
    
    @FXML
    public void prestar(ActionEvent evento){    
        prestar();
    }
    
    private void prestar(){
        
        confirmarMaterial = new ConfirmarMaterial();
        calendario = Calendar.getInstance();
        calendario = new GregorianCalendar();
        SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
        mensajesError();
        if(confirmarMaterial.confirmarPrestamo(listaPrestamo, fechaDevolucion.getSelectedDate(), lblDocumento.getText())){
          //  consulta.registrarPrestamo(opcion, titulo, titulo, reserva, null, null, listaPrestamo);
        }        
    }
    
    private void seleccionarReserva(){
        
        if (tablaReserva.getSelectionModel().getSelectedItem() != null && !listaDetalleRe.isEmpty()) {
            if (listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getEstado().equals("Vigente")) {
                String nombre = listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getNombre();
                String apellido = listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getApellido();
                lblNombre.setText(nombre + " " + apellido);
                lblDocumento.setText(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getDocumento());
                lblCorreo.setText(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getCorreo());
                tipoUsuario = listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getTipoUsuario().toLowerCase();
                listaPrestamo.clear();
                prepararTablaPrestamo();
                for (Material de : listaDetalleRe) {
                    listaPrestamo.add(new Prestamo(de.getEjemplar(), de.getTitulo(), de.getCodigo()));
                }
                reserva = true;
            }else{
                limpiarCamposReserva();
                listaPrestamo.clear();
                Utilidades.mensaje(null, "La reserva se ecuentra cancelada.","", "Reserva Cancelada");
            } 
        }
    }
    
    private void seleccionarUsuario() {

        if (tablaUsuarios.getSelectionModel().getSelectedItem() != null) {
            consulta.mapearUsuarios(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
            lblGrado.setText(consulta.getGrado());
            lblCurso.setText(consulta.getCurso());
            lblJornada.setText(consulta.getJornada());
            lblDireccion.setText(consulta.getDireccion());
            lblTelefono.setText(consulta.getTelefono());
            lblMulta.setText(String.valueOf(consulta.getMulta()));
            if (listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getEstado().equals("Habilitado")) {
                if (consulta.getMulta() > 0) {
                    Utilidades.mensajeOpcion(null, "Desea continuar con el prestamo?", "El usuario se encuentra multado.", "Seleccionar Usuario");
                    if (Utilidades.getMensajeOpcion() == Dialogs.DialogResponse.YES) {
                        String nombre = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre();
                        String apellido = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido();
                        lblNombre.setText(nombre + " " + apellido);
                        lblDocumento.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
                        lblCorreo.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCorreo());
                        tipoUsuario = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getTipo().toLowerCase();
                    }else{
                        limpiarCamposReserva();
                    }
                } else {
                    String nombre = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre();
                    String apellido = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido();
                    lblNombre.setText(nombre + " " + apellido);
                    lblDocumento.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
                    lblCorreo.setText(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCorreo());
                    tipoUsuario = listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getTipo().toLowerCase();
                }
            } else {
                limpiarCamposReserva();
                Utilidades.mensaje(null, "El usuario se encuentra inhabilitado.", "", "Seleccionar Ususario");
            }
        }
    }

    private void sumarEjemplar(){
        
        if(tablaEjemplar.getSelectionModel().getSelectedItem() != null){
            prepararTablaPrestamo();
            String ejemplar = listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEjemplar();
            if(!verificarDuplicados(listaPrestamo, ejemplar)){
                if(listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEstado().equals("Disponible") &&
                   listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getDisponibilidad().equals("Habilitado")){
                        listaPrestamo.add(new Prestamo(ejemplar, titulo, codigoClasificacion));
                }
                else{
                    Utilidades.mensaje(null, "El ejemplar se encuentra reservado, prestado, inhabilitado o en mantenimiento y no puede prestarse.",
                                             "", "Seleccionar Ejemplar");
                }
            }else{
                Utilidades.mensaje(null, "El ejemplar seleccionado ya se ecuentra en la lista.", "", "Seleccionar Ejemplar");
            }
        }    
    }
    
    private void removerEjemplar(){
         
        if(!listaPrestamo.isEmpty()){
            if(tablaPrestamo.getSelectionModel().getSelectedItem() != null){
                listaPrestamo.remove(tablaPrestamo.getSelectionModel().getSelectedIndex());
            } 
        }    
    }
        
    private void prepararTablaPrestamo(){
    
        clmnEjemplarPr.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("ejemplar"));
        clmnTituloPr.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("titulo"));
        clmnCodigoPr.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("codigo"));
        tablaPrestamo.setItems(listaPrestamo);       
    }
    
    private void detalleReserva(){
        
        if(tablaReserva.getSelectionModel().getSelectedItem() != null){
            prepararTablaDetalleRe();
            listaDetalleRe.addAll(consulta.getListaDetalleReserva(listaReservas.get(tablaReserva.getSelectionModel().getSelectedIndex()).getId()));
            tablaDetalleReserva.setItems(listaDetalleRe);
        }
    }
    
    private void prepararTablaDetalleRe(){
       
        clmnTituloDe.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));
        clmnEjemplarRe.setCellValueFactory(new PropertyValueFactory<Material, String>("ejemplar"));
        clmnCodigoDe.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnAutor.setCellValueFactory(new PropertyValueFactory<Material, String>("autores"));
        clmnEditorial.setCellValueFactory(new PropertyValueFactory<Material, String>("editorial"));      
        clmnMateria.setCellValueFactory(new PropertyValueFactory<Material, String>("materias"));
        tablaDetalleReserva.setEditable(true);
        listaDetalleRe.clear();  
    }
    
    private void buscarReserva(){
        
        if (!"".equals(txtfBuscarReserva.getText())) {
            prepararTablaReserva();
            listaReservas.addAll(consulta.getListaReservaBusqueda(txtfBuscarReserva.getText().trim()));
            tablaReserva.setItems(listaReservas);
            listaDetalleRe.clear();
        }
    }
    
    private void listarReservas(){
        
        prepararTablaReserva();
        listaReservas.addAll(consulta.getListaReservas());
        tablaReserva.setItems(listaReservas);
    }
    
    private void prepararTablaReserva(){
        
        clmnDocumentoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("documento"));
        clmnNombreRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("nombre"));
        clmnApellidoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("apellido"));
        clmnCorreoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("correo"));
        clmnFecha.setCellValueFactory(new PropertyValueFactory<Reserva, String>("fecha"));
        clmnEstadoRe.setCellValueFactory(new PropertyValueFactory<Reserva, String>("estado"));
        tablaReserva.setEditable(true);
        listaReservas.clear();
        
    }
    
    private void buscarUsuario(){
    
         if (!"".equals(txtfBuscarUsuario.getText())) {
            prepararTablaUsuario();
            listaUsuarios.addAll(consulta.getListaUsuarioBusqueda(txtfBuscarUsuario.getText().trim()));
            tablaUsuarios.setItems(listaUsuarios);
        }   
    }
    
    private void listarUsuario(){
        
        prepararTablaUsuario();
        if(comboListaUsuario.getSelectionModel().getSelectedItem().toString().contains("Todos")){ 
            listaUsuarios.addAll(consulta.getListaUsuarios(2, null));
        }else {
            listaUsuarios.addAll(consulta.getListaUsuarios(1, comboListaUsuario.getSelectionModel().getSelectedItem().toString()));            
        }
        tablaUsuarios.setItems(listaUsuarios);
    }
     
    private void prepararTablaUsuario(){
        
        clmnDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        clmnCorreo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("correo"));
        clmnTipoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        clmnEstadoUsuario.setCellValueFactory(new PropertyValueFactory<Usuario, String>("estado"));
        tablaUsuarios.setEditable(true);
        listaUsuarios.clear();      
    }
    
    private void buscarMaterial() {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterialBusqueda(txtfBuscar.getText().trim()));
            tablaMaterial.setItems(listaMaterial);
        }
    }
    
    private void detalleMaterialRe(){   
        
        if (tablaDetalleReserva.getSelectionModel().getSelectedItem() != null) {           
            detalleMaterial(listaDetalleRe.get(tablaDetalleReserva.getSelectionModel().getSelectedIndex()).getIdMaterial());
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }    
    }
    
    private void detalleMaterialEjemplar(){
        
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {           
            detalleMaterial(Integer.parseInt(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));            
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }         
    }
    
    private void detalleMaterial(int id) {
        
        ventanaPrincipal = new Sabga();
        btnDetalle.setDisable(true);
        dialogos.setId(id);
        dialogos.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", ventanaPrincipal.getPrimaryStage(), null, 4);        
        btnDetalle.setDisable(false);        
    }
    
    private void mapearEjemplar(){
        
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            listaEjemplares.clear();
            clmnEjemplar.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("ejemplar"));
            clmnEstado.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("estado"));
            clmnDispo.setCellValueFactory(new PropertyValueFactory<Ejemplar, String>("disponibilidad"));
            tablaEjemplar.setEditable(true);
            listaEjemplares.addAll(consulta.listaEjemplares(Integer.parseInt(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId())));
            tablaEjemplar.setItems(listaEjemplares);
            titulo = listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getTitulo();
            codigoClasificacion = listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getCodigo();
         }
    }
    
    private void prepararTablaMaterial(){   
        
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));        
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Material, String>("tipo"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));        
        tablaMaterial.setEditable(true);
        listaMaterial.clear();
        listaEjemplares.clear();
           
    }
    
    private void listar(){    
        
        listaMaterial.addAll(consulta.getListaMaterial(comboListar.getSelectionModel().getSelectedItem().toString()));
        tablaMaterial.setItems(listaMaterial);
    }
    
    private void mensajesError() {

        validarPrestamo = new ValidarMaterial();
        validarPrestamo.validarPrestamo(listaPrestamo, fechaDevolucion.getSelectedDate(), lblDocumento.getText());
        lblValidarDocumento.setText(validarPrestamo.getErrorDocumento());
        lblValidarFecha.setText(validarPrestamo.getErrorFecha());
        lblValidarEjemplar.setText(validarPrestamo.getErrorEjemplares());
    }
        
    private Boolean verificarDuplicados(ObservableList<Prestamo> lista ,String ejemplar){
        
        for(Prestamo dato: lista){
           if(dato.getEjemplar().equals(ejemplar)){
               return true;
           }            
        }
        return false;  
    }
    
    private void inicio(){
        
        comboListar.setItems(consulta.llenarLista(select.getListaTipoMaterial(), select.getTipoMaterial()));
        comboListaUsuario.setItems(consulta.llenarLista(select.getListaUsuarios(), select.getUsuarios()));
        comboListaUsuario.getItems().add("Todos");      
    }
    
    private void limpiarCamposReserva() {
        lblNombre.setText(null);
        lblDocumento.setText(null);
        lblCorreo.setText(null);
        tipoUsuario = null;
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) { 
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {       
        hboxFecha.getChildren().add(fechaDevolucion);
        inicio();
    }    
}
