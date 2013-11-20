
package sabga.controlador;

import sabga.atributos.Usuario;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
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
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import np.com.ngopal.control.AutoFillTextBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Material;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.modelo.Consultas;
import sabga.modelo.ValidarMaterial;

/**
 *
 * @author Nanny
 */
public class ReservaEscritorioController implements Initializable, ControlledScreen {

    private Conexion con;
    private Dialogs.DialogResponse dialogoo;
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    private final Dialogo dialogo;
    @FXML
    private Label campoFecha, lbNombre, lbDocumento, lbApellidos;
    @FXML
    private TextField campoBuscar, txtfFiltrar, txtfBuscar;
    @FXML
    private Button btnBorrar, btnDetalle, btnEditorial, btnAutor, btnMateria, btnCodigoBarras, btnBorrarBusqueda;
    @FXML
    private ComboBox comboMaterial;
    @FXML
    private Tooltip est;
    @FXML
    private TableView tablaMaterial, tablaMaterial2;
    @FXML
    private TableColumn tDocumento, tTipo, tNombre, tApellido;

    @FXML
    private TableColumn clmnTitulo, clmnCodigo, clmnClase, clCodigo, clTitulo, clClaseMaterial, clmnEjemplar, clmnEstado;
    //private final AutoFillTextBox editorial, autores, materias ;    
    private final ObservableList<Material> filtrarMaterial;
    private final ObservableList<Material> listaMaterial;
    private final ObservableList<Material> listaMaterial2;
    // private final ObservableList listaBusquedaMaterias, listaBusquedaAutores, disponibilidad;
    private final Consultas consulta;
    private String nombre, documento, apellido;
    public int variable;

    public ReservaEscritorioController() {
        con = new Conexion();
        dialogo = new Dialogo();
        consulta = new Consultas();
//        editorial = new AutoFillTextBox();
//        autores = new AutoFillTextBox();
//        materias = new AutoFillTextBox();
//        listaBusquedaMaterias = FXCollections.observableArrayList();
        //       listaBusquedaAutores = FXCollections.observableArrayList();
        filtrarMaterial = FXCollections.observableArrayList();
        listaMaterial = FXCollections.observableArrayList();
//        listaMaterias = FXCollections.observableArrayList();
        listaMaterial2 = FXCollections.observableArrayList();
//        disponibilidad = FXCollections.observableArrayList();
        listaMaterial.addListener(new ListChangeListener<Material>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends Material> change) {
                updateFilteredData();
            }
        });
    }

    @FXML
    public void buscarUsu(ActionEvent evento){}
    @FXML
    public void guardarCambios(ActionEvent evento) {

    }

    @FXML
    public void buscarMaterial(ActionEvent evento) {

        if (!"".equals(txtfBuscar.getText())) {
            prepararTablaMaterial();
            filtrarMaterial.addAll(listaMaterial);
            listaMaterial.addAll(consulta.getListaMaterialBusqueda(txtfBuscar.getText().trim()));
            tablaMaterial.setItems(filtrarMaterial);
        }
    }

    @FXML
    private void listarMaterial(ActionEvent evento) {
        prepararTablaMaterial();
        listar();
    }

    public void prepararTablaMaterial() {

        clmnTitulo = new TableColumn("Titulo");
        clmnTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));
        clmnTitulo.setPrefWidth(150);
        tablaMaterial.getColumns().add(clmnTitulo);
        clmnCodigo = new TableColumn("Codigo");
        clmnCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
        clmnCodigo.setPrefWidth(150);
        tablaMaterial.getColumns().add(clmnCodigo);
        clmnClase = new TableColumn("Clase de material");
        clmnClase.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));
        clmnClase.setPrefWidth(150);
        tablaMaterial.getColumns().add(clmnClase);
        tablaMaterial.setEditable(true);
        filtrarMaterial.clear();
        listaMaterial.clear();
    }

    private void listar() {

        filtrarMaterial.addAll(listaMaterial);
        listaMaterial.addAll(consulta.getListaMaterial(comboMaterial.getSelectionModel().getSelectedItem().toString()));
        tablaMaterial.setItems(filtrarMaterial);
    }

    
    private void llenarComboBox() {
        comboMaterial.setItems(consulta.llenarLista("SELECT tipo_material FROM tbl_TIPO_MATERIAL", "tipo_material"));
    }

    private Boolean verificarDuplicados(ObservableList lista, String datoVefificar) {

        for (Object dato : lista) {
            if (dato.toString().equals(datoVefificar)) {
                return true;
            }
        }
        return false;
    }

//    @FXML
//     public void dialogoNuevaEditorial(ActionEvent evento){        
//         ventanaPrincipal = new Sabga();
//         btnEditorial.setDisable(true);
//         dialogo.mostrarDialogo("vista/dialogos/NuevaEditorial.fxml", "Nueva Editorial", ventanaPrincipal.getPrimaryStage(), null, 3);
//         btnEditorial.setDisable(false);
//     }    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento) {

        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {
            ventanaPrincipal = new Sabga();
            btnDetalle.setDisable(true);
            dialogo.setId(Integer.parseInt(filtrarMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
            dialogo.mostrarDialogo("vista/dialogos/DetalleMaterial.fxml", "Detalle Material", ventanaPrincipal.getPrimaryStage(), null, 4);
            btnDetalle.setDisable(false);
        } else {
            Utilidades.mensaje(null, "Debe seleccionar un material de la lista", "Para ver el detalle del material", "Detalle Material");
        }
    }

    private void updateFilteredData() {
        filtrarMaterial.clear();

        for (Material m : listaMaterial) {
            if (matchesFilter(m)) {
                filtrarMaterial.add(m);
            }
        }
        reapplyTableSortOrder();
    }

    @FXML
    private void borrarCampo(ActionEvent evento) {

        switch (Utilidades.getDesencadenador(evento)) {
            case "btnBorrar":
                txtfFiltrar.setText("");
                btnBorrar.setVisible(false);
                break;
            case "btnBorrarBusqueda":
                txtfBuscar.setText("");
                btnBorrarBusqueda.setVisible(false);
                break;
        }
    }

    private void mostrarBoton() {

        if (txtfFiltrar.getText() == null || txtfFiltrar.getText().isEmpty()) {
            btnBorrar.setVisible(false);
        } else {
            btnBorrar.setVisible(true);
        }
    }

    @FXML
    private void mostrarBoton(KeyEvent evento) {

        if (txtfBuscar.getText() == null || txtfBuscar.getText().isEmpty()) {
            btnBorrarBusqueda.setVisible(false);
        } else {
            btnBorrarBusqueda.setVisible(true);
        }
    }

    private boolean matchesFilter(Material material) {
        String filterString = txtfFiltrar.getText();
        if (filterString == null || filterString.isEmpty()) {
            return true;
        }

        String lowerCaseFilterString = filterString.toLowerCase();

        if (material.getTitulo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (material.getCodigo().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        } else if (material.getClase().toLowerCase().indexOf(lowerCaseFilterString) != -1) {
            return true;
        }
        return false;
    }

    private void reapplyTableSortOrder() {
        ArrayList<TableColumn<Material, ?>> sortOrder = new ArrayList<>(tablaMaterial.getSortOrder());
        tablaMaterial.getSortOrder().clear();
        tablaMaterial.getSortOrder().addAll(sortOrder);
    }

    public void setScreenParent(ScreensController screenParent) {
        controlador = screenParent;
    }

    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    public void initialize(URL url, ResourceBundle rb) {

        llenarComboBox();
        //Formato fecha del día      
        java.util.Date fecha = new Date();
        int dia = fecha.getDate();
        int mes = fecha.getMonth() + 1;
        int anio = fecha.getYear() + 1900;
        String d = Integer.toString(dia);
        String m = Integer.toString(mes);
        String a = Integer.toString(anio);
        campoFecha.setDisable(true);
        campoFecha.setText(a + "-" + m + "-" + d);
        txtfFiltrar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                mostrarBoton();
                updateFilteredData();
            }
        });

        // PRUEBA DE TOOLTIP.....WORKS BY THE WAY :)
        Platform.runLater(new Runnable() {
            private Tooltip detalle;

            @Override
            public void run() {
                detalle = new Tooltip("Muestra en detalle la información del material.\nDebe seleccionarlo de la lista.");
                btnDetalle.setTooltip(detalle);
           // MenuItem h = new MenuItem("Esto es una prueba de un menÃº contextual ");
                // ContextMenu es = new ContextMenu(h);            
                // botonNuevaEditorial.setContextMenu(es);                 
            }
        });

    }

    //----------
    public void listarUsuarios() {

        Conexion conex = new Conexion();
        conex.conectar();
        try {

            conex.setResultado(conex.getStatement().executeQuery("SELECT  documento_usuario,nombre,apellidos  FROM tbl_USUARIO WHERE documento_usuario= '" + campoBuscar.getText() + "'OR nombre= '" + campoBuscar.getText() + "'"));

            if (conex.getResultado().next()) {
                documento = conex.getResultado().getString("documento_usuario");
                nombre = conex.getResultado().getString("nombre");
                apellido = conex.getResultado().getString("apellidos");
                campoBuscar.setDisable(true);
                Dialogs.showInformationDialog(null, "Los datos ingresados son correctos", "Mensaje");
                tablaMaterial2.setDisable(false);
                tablaMaterial.setDisable(false);
                lbNombre.setText(nombre);
                lbDocumento.setText(documento);
                lbApellidos.setText(apellido);
            } else {
                dialogoo = Dialogs.showErrorDialog(null, "Los datos ingresados no existen en la base de datos", "Mesaje de error", "Mensaje", Dialogs.DialogOptions.OK);
            }

        } catch (SQLException ex) {

            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");

        } finally {
            conex.desconectar();
        }
    }

    public void aceptar(ActionEvent evento) {
        listarUsuarios();
    }

    @FXML
    public void removerMateria(ActionEvent evento) {
        if (tablaMaterial2.getSelectionModel().getSelectedItem() != null) {
            listaMaterial.remove(tablaMaterial2.getSelectionModel().getSelectedIndex());
        } else {
            Utilidades.mensajeAdvertencia(null, "Debe seleccionar al menos una de la lista", "Pare remover una materia", "Remover Materia");
        }
    }

    //Mapear de una tabla a otra
    @FXML
    public void mapearDatos() {

        //if(campoBuscar.equals(nombre)||campoBuscar.equals(documento)){
        if (tablaMaterial.getSelectionModel().getSelectedItem() != null) {

            clTitulo.setCellValueFactory(new PropertyValueFactory<Material, String>("titulo"));
            clCodigo.setCellValueFactory(new PropertyValueFactory<Material, String>("codigo"));
            clClaseMaterial.setCellValueFactory(new PropertyValueFactory<Material, String>("clase"));
            tablaMaterial2.setEditable(true);
//            listaMaterial2.add(new Material(listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getTitulo(),
//                    listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getCodigo(),
//                    listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getClase(),
//                    listaMaterial.get(tablaMaterial.getSelectionModel().getSelectedIndex()).getId()));
//            tablaMaterial2.setItems(listaMaterial2);

        } else {
            Dialogs.showErrorDialog(null, "Debe ingresar primero el dato del usuario", "Mensaje");
        }
    }

    @FXML
    private void eliminarMaterial() throws SQLException {
        
        if(!listaMaterial2.isEmpty()){        
            listaMaterial2.remove(tablaMaterial2.getSelectionModel().getSelectedIndex());
        }
       
    }

    public void eliminarM(ActionEvent evento) throws SQLException {
        eliminarMaterial();
    }

    public void tablaUsuario() {
        tDocumento = new TableColumn("Documento");
        tDocumento.setCellValueFactory(new PropertyValueFactory<Usuario, String>("documento"));
        tDocumento.setPrefWidth(150);
        tablaMaterial.getColumns().add(tDocumento);
        tTipo = new TableColumn("Tipo de usuario");
        tTipo.setCellValueFactory(new PropertyValueFactory<Usuario, String>("tipo"));
        tTipo.setPrefWidth(150);
        tablaMaterial.getColumns().add(tTipo);
        tNombre = new TableColumn("Nombre");
        tNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));
        tNombre.setPrefWidth(150);
        tablaMaterial.getColumns().add(tNombre);
        tApellido = new TableColumn("Apellidos");
        tApellido.setCellValueFactory(new PropertyValueFactory<Usuario, String>("apellido"));
        tApellido.setPrefWidth(150);
        tablaMaterial.getColumns().add(tApellido);

        //tablaPersonas.setEditable(true);
        //tablaPersonas.setItems(listaUsuario);
    }
}
