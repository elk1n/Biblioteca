
package sabga.controlador;

import sabga.atributos.Usuario;
import java.awt.HeadlessException;
import java.awt.Panel;
import java.awt.image.BufferedImage;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.Conexion;
import sabga.configuracion.ControlledScreen;
import sabga.configuracion.Utilidades;
import javafx.scene.control.Dialogs;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 * FXML Controller class
 * @author Nanny
 */

public class PazySalvoController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    private Conexion con;
    public Usuario us;
    @FXML
    private Insets x1;
    @FXML
    private Label validar;
    @FXML
    public Panel panel1;
    private Stage dialogStage;
    private String codigoBarras;
    private BufferedImage symbol;
    private int nCopias;
    private BufferedImage ima;
    private Dialogs.DialogResponse dialogoo;
    @FXML
    private GridPane pazysalvoGrid;
    @FXML
    private TextField txtBuscar;
    private ObservableList<Usuario> listaUsuario;
    private ObservableList<Usuario> usuarios;
    private ObservableList<Integer> idUsuarios;
    public int variable;
    private String idU;
    @FXML
    private TextField campoNombre, campoApellido, campoBuscar, campoDocumento, campoFecha;
    @FXML
    private ComboBox comboCurso, comboGrupo, comboJornada;
    private String nombre, apellido, documento, jornada, curso, grupo, tipo;

    public ObservableList curso1;
    public ObservableList grado1;
    public ObservableList jornada1;

    //Constructor
    public PazySalvoController() {
        con = new Conexion();
        listaUsuario = FXCollections.observableArrayList();
        usuarios = FXCollections.observableArrayList();
        idUsuarios = FXCollections.observableArrayList();

        this.grado1 = FXCollections.observableArrayList();
        this.jornada1 = FXCollections.observableArrayList();
        this.curso1 = FXCollections.observableArrayList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void setScreenParent(ScreensController screenParent) {

        controlador = screenParent;
    }
    public double contTotal, contSubtotal, Total;
    SimpleDateFormat formatoFecha = new SimpleDateFormat("YYYY-MM-DD");

    public void setVentanaPrincipal(Sabga ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
    }

    public void cargarCombo() {

        try {
            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_CURSO"));

            while (con.getResultado().next()) {
                curso1.add(con.getResultado().getObject("curso"));
            }
            comboCurso.setItems(curso1);

            con.desconectar();
        } catch (SQLException ex) {

            Logger.getLogger(RegistroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarJornada() {

        try {
            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_JORNADA"));

            while (con.getResultado().next()) {
                jornada1.add(con.getResultado().getObject("jornada"));
            }
            comboJornada.setItems(jornada1);
            con.desconectar();
        } catch (SQLException ex) {

            Logger.getLogger(RegistroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargarCurso() {

        try {
            Conexion con = new Conexion();
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT * FROM tbl_GRADO"));

            while (con.getResultado().next()) {
                grado1.add(con.getResultado().getObject("grado"));
            }
            comboGrupo.setItems(grado1);
            con.desconectar();
        } catch (SQLException ex) {

            Logger.getLogger(RegistroUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void listarUsuarios() {

        //listaUsuario.removeAll(listaUsuario);
        Conexion conex = new Conexion();
        conex.conectar();
        try {

            //String uno=campoBuscar.getText();
            conex.setResultado(conex.getStatement().executeQuery("SELECT  u.documento_usuario,u.nombre, u.apellidos,g.grado,c.curso,j.jornada,t.tipo_usuario  FROM tbl_USUARIO u join tbl_grado g on u.id_grado=g.grado join tbl_curso c on u.id_curso=c.id_curso join tbl_jornada j on u.id_jornada=j.id_jornada join tbl_tipo_usuario t on u.id_tipo_usuario=t.id_tipo_usuario  WHERE u.documento_usuario= '" + campoBuscar.getText() + "'OR u.nombre= '" + campoBuscar.getText() + "'"));

            if (conex.getResultado().next()) {
                documento = conex.getResultado().getString("u.documento_usuario");
                nombre = conex.getResultado().getString("u.nombre");
                apellido = conex.getResultado().getString("u.apellidos");
                grupo = conex.getResultado().getString("g.grado");
                curso = conex.getResultado().getString("c.curso");
                jornada = conex.getResultado().getString("j.jornada");
                tipo = conex.getResultado().getString("t.tipo_usuario");

            } else {
                dialogoo = Dialogs.showErrorDialog(null, "Los datos ingresados no existen en la base de datos", "Mesaje de error", "Mensaje", Dialogs.DialogOptions.OK);
            }

        } catch (SQLException ex) {

            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");

        } finally {
            conex.desconectar();
        }
    }

    public void obtenerIdUsuario() {

        try {
            con.conectar();
            con.setResultado(con.getStatement().executeQuery("SELECT documento_usuario FROM tbl_USUARIO WHERE documento_usuario= '" + campoBuscar.getText() + "'"));

            if (con.getResultado().first()) {

                idU = con.getResultado().getString("documento_usuario");
            }
            System.out.print(idU);
        } catch (SQLException ex) {

            Utilidades.mensajeError(null, ex.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");

        } finally {
            con.desconectar();
        }

    }
    
     /*  public void mapearDatos() {                               
     listarUsuarios();
     //obtenerIdUsuario();
     // if(idU.equals(campoDocumento.getText())){
     campoDocumento.setText(documento);
     campoNombre.setText(nombre);
     campoApellido.setText(apellido);
     comboCurso.setValue(curso);
     comboGrupo.setValue(grupo);
     comboJornada.setValue(jornada);            
     } */

    //Metodo para mapear los datos de una consulta en diferentes campos de textos
    public void aceptar(ActionEvent evento) {

        try {
            listarUsuarios();
            //obtenerIdUsuario();
            // if(idU.equals(campoDocumento.getText())){
            campoDocumento.setText(documento);
            campoNombre.setText(nombre);
            campoApellido.setText(apellido);
            comboCurso.setValue(curso);
            comboGrupo.setValue(grupo);
            comboJornada.setValue(jornada);
            /*  }                   
             else{
             campoDocumento.setText("");
             campoNombre.setText("");
             campoApellido.setText("");
             comboCurso.setValue("");
             comboGrupo.setValue("");
             comboJornada.setValue("");
             validar.setVisible(true); 
             }*/
        } catch (Exception e) {
            Utilidades.mensajeError(null, e.getMessage(), "No se pudo acceder a la base de datos\nFavor intente más tarde", "Error");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pazysalvoGrid.setGridLinesVisible(true);
        cargarCombo();
        cargarCurso();
        cargarJornada();

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

    }

    //Metodo para guardar el contenido de un gridPanel como imagen 
    public void CrearImagen() {
        
        WritableImage snapshot = pazysalvoGrid.snapshot(new SnapshotParameters(), null);
        
        ima = javafx.embed.swing.SwingFXUtils.fromFXImage(snapshot, null);
       }

    @FXML
    public void guardar(ActionEvent evento) {
        CrearImagen();
        guardar();
    }

    //Metodo para guardar el certificado
    private void guardar() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar ");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files (*.png)", "*.png", "*.jpg", "*.gif", "*.jpeg"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            String name = file.getName();
            if (name.indexOf(".") == -1) {
                name += ".png";
                file = new File(file.getParentFile(), name);
            }
            try {
                ImageIO.write(ima, "png", file);
            } catch (IOException ex) {
                Utilidades.mensajeError(null, ex.getMessage(), "Error al guardar el código de barras", "Error Código Barras");
            }
        }
    }

    public void imprimirCertificado() {

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(ima, "PNG", baos);
            byte[] data = baos.toByteArray();
            ByteArrayInputStream bais = new ByteArrayInputStream(data);
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            DocFlavor flavor = DocFlavor.INPUT_STREAM.PNG;
            PrinterJob pj = PrinterJob.getPrinterJob();
            boolean okay = pj.printDialog(pras);
            // pj.setCopies(copias);        
            if (okay) {
                // lblMensajes.setText("Imprimiendo...");
                PrintService service = pj.getPrintService();
                DocPrintJob job = service.createPrintJob();
                DocAttributeSet das = new HashDocAttributeSet();
                Doc doc = new SimpleDoc(bais, flavor, das);
                job.print(doc, pras);
                //lblMensajes.setText("Completado: " + service.getName());         
            }
        } catch (HeadlessException | IOException | PrintException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "Error al imprimir el Certificado", "Error Imprimir");
        }
    }

    public void imprimir(ActionEvent evento) {
        CrearImagen();
        imprimirCertificado();
    }
}
