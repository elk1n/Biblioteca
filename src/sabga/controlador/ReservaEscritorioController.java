
package sabga.controlador;

import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Atributos;
import sabga.atributos.Ejemplar;
import sabga.atributos.Material;
import sabga.atributos.Prestamo;
import sabga.atributos.Usuario;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.ConfirmarMaterial;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 * @author Nanny
 */

public class ReservaEscritorioController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private ComboBox<String> comboListarMaterial, comboListarUsuario;
    @FXML
    private TextField txtfBuscar, txtfBuscarUsuario;
    @FXML
    private Button  btnBorrarMaterial, btnBorrarUsuario, btnDetalle;
    @FXML
    private TableView<Material> tablaMaterial;
    @FXML
    private TableView<Ejemplar> tablaEjemplar;
    @FXML
    private TableView<Usuario> tablaUsuarios;
    @FXML
    private TableView<Prestamo> tablaReserva;
    @FXML
    private TableColumn<Material, String> clmnTitulo, clmnCodigo, clmnClase, clmnTipo;
    @FXML
    private TableColumn<Ejemplar, String> clmnEjemplar, clmnEstado, clmnDispo;
    @FXML
    private TableColumn<Usuario, String>  clmnDocumento, clmnNombre, clmnApellido, clmnCorreo, clmnTipoUsuario, clmnEstadoUsuario;
    @FXML
    private TableColumn<Prestamo, String> clmnEjemplarRe, clmnTituloRe, clmnCodigoRe;
    @FXML
    private Label lblNombre, lblDocumento, lblCorreo, lblGrado, lblCurso, lblJornada, lblTelefono, lblDireccion, lblMulta,
                  lblValidarDocumento, lblValidarEjemplar, lblBuscarUsuario, lblBuscarMaterial;
    @FXML
    private MenuItem detalleUsuario, multasUsuario, detalleUsuario2, multasUsuario2;
    private String titulo, codigoClasificacion, tipoUsuario;  
    private final Consultas consulta;
    private final Dialogo dialogos;
    private final Atributos atributo;
    private ValidarMaterial validarReserva;
    private ConfirmarMaterial confirmarMaterial;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Ejemplar> listaEjemplares;
    private final ObservableList<Usuario> listaUsuarios;
    private final ObservableList<Prestamo> listaReserva;
    
    public ReservaEscritorioController() {
        
        consulta = new Consultas();
        dialogos = new Dialogo();
        atributo = new Atributos();
        listaReserva = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
        listaEjemplares = FXCollections.observableArrayList();
        listaUsuarios = FXCollections.observableArrayList();
    }

    @FXML
    public void listarMaterial(ActionEvent evento){                        
        listar();    
    }
    
    @FXML
    public void buscarMaterial(ActionEvent evento){
        buscarMaterial();
    }
    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento){        
       detalleMaterialEjemplar();
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
    public void verDetalleUsuario(){
        dialogoDetalleUsuario();
    }
    
    @FXML
    public void verMultasUsuario(){
        dialogoMultasUsuario();
    }
    
    @FXML
    public void removerEjemplar(ActionEvent evento){
        removerEjemplar();
    }
    
    public void cargarEjemplar(){    
        mapearEjemplar();
    }
    
    public void seleccionarUsuario(){
        cargarUsuario();
    }
    
    public void addEjemplar(){
        sumarEjemplar();
    }
    
    @FXML
    public void reservar(ActionEvent evento){    
        reservarMaterial();
    }
    
    private void reservarMaterial(){
        
        confirmarMaterial = new ConfirmarMaterial();
        mensajesError();
        if(confirmarMaterial.confirmarReserva(listaReserva, lblDocumento.getText(), tipoUsuario, listaReserva.size())){           
              consulta.registrarReserva(lblDocumento.getText(), listaReserva);           
              if(consulta.getMensaje() == null){
               Utilidades.mensaje(null, "La reserva se ha registrado correctamente.", "", "Registrar Reserva");
               limpiarCamposReserva();
               limpiarCampos();
           }else{
               Utilidades.mensajeError(null, consulta.getMensaje(), "La reserva no ha sido registrada.", "Error Registro Reserva");
           }  
        }        
    }
    
    private void sumarEjemplar(){
        
        if (tablaEjemplar.getSelectionModel().getSelectedItem() != null) {
            prepararTablaPrestamo();
            String ejemplar = listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEjemplar();
            if (!verificarDuplicados(listaReserva, ejemplar)) {
                if (!listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getClase().toLowerCase().contains("referencia")) {
                    if (listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getEstado().equals("Disponible")
                     && listaEjemplares.get(tablaEjemplar.getSelectionModel().getSelectedIndex()).getDisponibilidad().equals("Habilitado")) {
                        listaReserva.add(new Prestamo(ejemplar, titulo, codigoClasificacion));
                    } else {
                        Utilidades.mensaje(null, "El ejemplar se encuentra reservado, prestado, inhabilitado o en mantenimiento y no puede reservarse.",
                                "", "Seleccionar Ejemplar");
                    }
                } else {
                    Utilidades.mensaje(null, "El ejemplar seleccionado es de referencia y no puede prestarse.", "", "Seleccionar Ejemplar");
                }
            } else {
                Utilidades.mensaje(null, "El ejemplar seleccionado ya se ecuentra en la lista.", "", "Seleccionar Ejemplar");
            }
        }   
    }
    
    private void cargarUsuario(){
    
        if (tablaUsuarios.getSelectionModel().getSelectedItem() != null) {
            mapearDatosUsuario();
            if (listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getEstado().equals("Habilitado")) {
                if (consulta.getMulta() > 0) {
                    Utilidades.mensajeOpcion(null, "Desea continuar con la reserva?", "El usuario se encuentra multado.", "Seleccionar Usuario");
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
    
    private void prepararTablaPrestamo(){
    
        clmnEjemplarRe.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("ejemplar"));
        clmnTituloRe.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("titulo"));
        clmnCodigoRe.setCellValueFactory(new PropertyValueFactory<Prestamo, String>("codigo"));
        tablaReserva.setItems(listaReserva);       
    }
    
    private void removerEjemplar(){
         
        if(!listaReserva.isEmpty()){
            if(tablaReserva.getSelectionModel().getSelectedItem() != null){
                listaReserva.remove(tablaReserva.getSelectionModel().getSelectedIndex());
            } 
        }    
    }
    
    private void mapearDatosUsuario(){
    
        consulta.mapearUsuarios(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
        lblGrado.setText(consulta.getGrado());
        lblCurso.setText(consulta.getCurso());
        lblJornada.setText(consulta.getJornada());
        lblDireccion.setText(consulta.getDireccion());
        lblTelefono.setText(consulta.getTelefono());
        lblMulta.setText(String.valueOf(consulta.getMulta()));
        atributo.setDocumentoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getDocumento());
        atributo.setNombreUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getNombre());
        atributo.setApellidoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getApellido());
        atributo.setCorreoUsuario(listaUsuarios.get(tablaUsuarios.getSelectionModel().getSelectedIndex()).getCorreo());
    }
    
    private void buscarUsuario(){
    
         limpiarDatos();   
         if (!"".equals(txtfBuscarUsuario.getText())) {
            prepararTablaUsuario();
            listaUsuarios.addAll(consulta.getListaUsuarioBusqueda(txtfBuscarUsuario.getText().trim()));
            tablaUsuarios.setItems(listaUsuarios);
            if(listaUsuarios.isEmpty()){
                comboListarUsuario.getSelectionModel().clearSelection();
                lblBuscarUsuario.setText("No se han encontrado resultados.");
            }else{
                lblBuscarUsuario.setText(null);
                comboListarUsuario.getSelectionModel().clearSelection();
            }
        }   
    }
    
    private void listarUsuario(){
        
        if (!comboListarUsuario.getSelectionModel().isEmpty()) {            
            limpiarDatos();
            prepararTablaUsuario();
            if (comboListarUsuario.getSelectionModel().getSelectedItem().toString().contains("Todos")) {
                listaUsuarios.addAll(consulta.getListaUsuarios(2, null));
            } else {
                listaUsuarios.addAll(consulta.getListaUsuarios(1, comboListarUsuario.getSelectionModel().getSelectedItem().toString()));
            }
            tablaUsuarios.setItems(listaUsuarios);
            lblBuscarUsuario.setText(null);
        }
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
            if(listaMaterial.isEmpty()){
                comboListarMaterial.getSelectionModel().clearSelection();
                lblBuscarMaterial.setText("No se han encontrado resultados.");
            }else{
                lblBuscarMaterial.setText(null);
                comboListarMaterial.getSelectionModel().clearSelection();
            }
        }
    }
    
    private void listar(){    
        
        if (!comboListarMaterial.getSelectionModel().isEmpty()) {
            prepararTablaMaterial();
            listaMaterial.addAll(consulta.getListaMaterial(comboListarMaterial.getSelectionModel().getSelectedItem().toString()));
            tablaMaterial.setItems(listaMaterial);
            lblBuscarMaterial.setText(null);
        }
    }
    
    private void prepararTablaMaterial(){   
        
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));        
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnTipo.setCellValueFactory(new PropertyValueFactory<Material, String>("tipo"));
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));        
        listaMaterial.clear();
        listaEjemplares.clear();           
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
    
    private void dialogoDetalleUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            detalleUsuario.setDisable(true);
            detalleUsuario2.setDisable(true);
            dialogos.mostrarDialogo("vista/dialogos/DetalleUsuario.fxml", "Información del Usuario", null , null, 5);           
            detalleUsuario.setDisable(false);
            detalleUsuario2.setDisable(false);
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void dialogoMultasUsuario(){
        
        if(tablaUsuarios.getSelectionModel().getSelectedItem() != null){            
            multasUsuario.setDisable(true);
            multasUsuario2.setDisable(true);
            dialogos.mostrarDialogo("vista/dialogos/Multa.fxml", "Detalle Multas", null , null, 17);           
            multasUsuario.setDisable(false);
            multasUsuario2.setDisable(false);
        }else{
            Utilidades.mensaje(null, "Debe seleccionar un usuario. ", "", "Selección Usuario");
        }
    }
    
    private void mensajesError() {

        validarReserva = new ValidarMaterial();
        validarReserva.validarReserva(listaReserva, lblDocumento.getText(), tipoUsuario, listaReserva.size());
        lblValidarDocumento.setText(validarReserva.getErrorDocumento());
        lblValidarEjemplar.setText(validarReserva.getErrorEjemplares()+"\n"+ validarReserva.getErrorTipoUsuario());
    }
    
    private Boolean verificarDuplicados(ObservableList<Prestamo> lista ,String ejemplar){
        
        for(Prestamo dato: lista){
           if(dato.getEjemplar().equals(ejemplar)){
               return true;
           }            
        }
        return false;  
    }
    
    private void limpiarCamposReserva() {
        lblNombre.setText(null);
        lblDocumento.setText(null);
        lblCorreo.setText(null);
        tipoUsuario = null;
    }
    
    private void inicio(){
        
        comboListarMaterial.setItems(consulta.llenarLista(2));
        comboListarUsuario.setItems(consulta.llenarLista(6));
        comboListarUsuario.getItems().add("Todos");
        btnBorrarMaterial.setVisible(false);
        btnBorrarUsuario.setVisible(false);
    }
    
    private void limpiarDatos(){
        
        lblGrado.setText(null);
        lblCurso.setText(null);
        lblJornada.setText(null);
        lblTelefono.setText(null);
        lblDireccion.setText(null);
        lblMulta.setText(null);   
    }
    
    private void limpiarCampos(){        
        tipoUsuario = null;
        listaReserva.clear();
    }  
    
    @FXML
    public void mostrarBotonMaterial(KeyEvent evento) {

        if ("".equals(txtfBuscar.getText())){            
            btnBorrarMaterial.setVisible(false);      
        }
        else {
           btnBorrarMaterial.setVisible(true); 
        }          
    }
    
    @FXML
    public void mostrarBotonUsuario(KeyEvent evento) {

        if ("".equals(txtfBuscarUsuario.getText())){            
            btnBorrarUsuario.setVisible(false);      
        }
        else {
           btnBorrarUsuario.setVisible(true); 
        }          
    }
   
    @FXML
    public void borrarCampoMaterial(ActionEvent evento){
        
        txtfBuscar.setText(null);
        btnBorrarMaterial.setVisible(false);
        lblBuscarMaterial.setText(null);
    }
    
    @FXML
    public void borrarCampoUsuario(ActionEvent evento){
        
        txtfBuscarUsuario.setText(null);
        btnBorrarUsuario.setVisible(false);
        lblBuscarUsuario.setText(null);
        
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent) { 
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){        
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();     
    }
    
}
