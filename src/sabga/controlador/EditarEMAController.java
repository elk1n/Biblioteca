package sabga.controlador;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.atributos.Autor;
import sabga.atributos.Listar;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import sabga.modelo.ValidarMaterial;

/**
 * @author Elk1n
 */
public class EditarEMAController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    @FXML
    private TableView tablaResultados;
    @FXML
    private TableColumn clmnNombre, clmnApellido;
    @FXML
    private ComboBox comboListar;
    @FXML
    private Label validarNombreAutor, validarApellidosAutor, validarEditorial, validarMateria, validarClaseMaterial, validarTipoMaterial;
    @FXML
    private TextField campoNombreAutor, campoApellidosAutor, campoEditorial, campoMateria, campoTipoMaterial, campoClaseMaterial;
    @FXML
    private TitledPane acordeonAutor, acordeonEditorial, acordeonMateria, acordeonTipo, acordeonClase;
    
    private ObservableList<Listar> listaDatos;
    private ObservableList<Autor> autores;

    public EditarEMAController() {

        listaDatos = FXCollections.observableArrayList();
        autores = FXCollections.observableArrayList();
    }

    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;
    }

    public void setVentanaPrincipal(Sabga ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
    }

    @FXML
    public void listar(ActionEvent evento) {
        
        if(comboListar.getSelectionModel().getSelectedIndex() == 0){
            
            tablaAutores();
            llenarAutores();
        }

        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {

            listarDatos(listaDatos, "SELECT * FROM tbl_EDITORIAL", "nombre_editorial");
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {

            listarDatos(listaDatos, "SELECT * FROM tbl_MATERIA", "nombre_materia");
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {

            listarDatos(listaDatos, "SELECT * FROM tbl_CLASE_MATERIAL", "clase_material");
        }
        if (comboListar.getSelectionModel().getSelectedIndex() == 4) {

            listarDatos(listaDatos, "SELECT * FROM tbl_TIPO_MATERIAL", "tipo_material");
        }
    }

    public void listarDatos(ObservableList lista, String tabla, String consulta) {
        
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Listar, String>("nombre"));
        tablaResultados.getColumns().remove(clmnApellido);
        clmnNombre.setPrefWidth(510);
        tablaResultados.setEditable(true);
        tablaResultados.setItems(listaDatos);

        try {

            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery(tabla));
            lista.removeAll(lista);
            while (con.getResultado().next()) {

                lista.add(new Listar(con.getResultado().getString(consulta)));
            }
            con.desconectar();
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    }

    @FXML
    public void mapearDatos() {

        if (comboListar.getSelectionModel().getSelectedIndex() == 1) {
            acordeonEditorial.setExpanded(true);
            campoEditorial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());            
            // campoEditorial.setText(listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()).toString());
            //  System.out.println(listaDatos.get(tablaResultados.getSelectionModel().getSelectedIndex()));
        }
        
        if (comboListar.getSelectionModel().getSelectedIndex() == 2) {
            acordeonMateria.setExpanded(true);
            campoMateria.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
        }
        
        if (comboListar.getSelectionModel().getSelectedIndex() == 3) {
            acordeonTipo.setExpanded(true);
            campoTipoMaterial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());
        }
        
        if (comboListar.getSelectionModel().getSelectedIndex() == 4) {
            acordeonClase.setExpanded(true);
            campoClaseMaterial.setText(tablaResultados.getSelectionModel().getSelectedItem().toString());

        }
    }

    @FXML
    public void validarActualizarEMA(ActionEvent evento) {

        ValidarMaterial validar = new ValidarMaterial(campoNombreAutor.getText(), campoApellidosAutor.getText(), campoEditorial.getText(),
                campoMateria.getText(), campoTipoMaterial.getText(), campoClaseMaterial.getText());

        if (acordeonAutor.isExpanded()) {
            validar.validarAutorAC();
            validarNombreAutor.setText(validar.getErrorNombreAutor());
            validarApellidosAutor.setText(validar.getErrorApellidosAutor());
        } else if (acordeonEditorial.isExpanded()) {
            validar.validarEditorialAC();
//           validarEditorial.setText(validar.getErrorNombreEditorial());
        } else if (acordeonMateria.isExpanded()) {
            validar.validarMateriaAC();
            validarMateria.setText(validar.getErrorNombreMateria());
        } else if (acordeonTipo.isExpanded()) {
            //    validar.validarNuevoTipoMaterial();
            //  validarTipoMaterial.setText(validar.getErrorNuevoTipoMaterial());
        } else if (acordeonClase.isExpanded()) {
            // validar.validarNuevaClaseMaterial();
            validarClaseMaterial.setText(validar.getErrorNuevaClaseMaterial());
        }
    }

    @FXML
    public void cancelar(ActionEvent evento) {

        campoNombreAutor.clear();
        campoApellidosAutor.clear();
        campoMateria.clear();
        campoTipoMaterial.clear();
        campoClaseMaterial.clear();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
    }
    
    public void tablaAutores(){
        
        clmnNombre.setCellValueFactory(new PropertyValueFactory<Listar, String>("nombreAutor"));
        clmnNombre.setPrefWidth(240);
        clmnApellido = new TableColumn("Apellido");
        clmnApellido.setCellValueFactory(new PropertyValueFactory<Autor, String>("apellidosAutor"));
        clmnApellido.setPrefWidth(270);
        tablaResultados.getColumns().add(clmnApellido);
        tablaResultados.setEditable(true);
        tablaResultados.setItems(autores);
    
    }
    
    public void llenarAutores(){
    
     try {

            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery( "SELECT * FROM tbl_AUTOR"));
            autores.removeAll(autores);
            while (con.getResultado().next()) {

                autores.add(new Autor(con.getResultado().getString("nombre_autor"),con.getResultado().getString("apellidos_autor") ));
            }
            con.desconectar();
        } catch (SQLException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }
    
    }
}
