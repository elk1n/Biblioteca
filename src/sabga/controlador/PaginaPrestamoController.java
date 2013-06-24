
package sabga.controlador;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Dialogs;
import javafx.stage.Stage;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;

/**
 *
 * @author MIGUEL
 * 
 */
public class PaginaPrestamoController implements Initializable, ControlledScreen{
    
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Label validacion;
    @FXML private TextField campocodigobarras;
    @FXML private TextField campotitulo;
    @FXML private TextField campomateria;
    @FXML private TextField campoautor;
    @FXML private TextField campodisponibilidad;
    @FXML private TextField campoejemplares;
    @FXML private Label prestamo1;
    @FXML private Label prestamo2;
    @FXML private Label prestamo3;
    @FXML private Label fecha1;
    @FXML private Label fecha2;
    @FXML private Label fecha3;
    @FXML private Button botoneliminar1;
    @FXML private Button botoneliminar2;
    @FXML private Button botoneliminar3;
    
    private String cont;
    private String codigobarras,titulo="100 Años de Soledad",materias="Literatura",autor="Gabriel Garcia Marquez",
            disponobilidad="No Dispinible",ejemplar="1";
    
    private String titulo2="La guerra de los Mundos",materias2="Literatura",autor2="H.G Wells",
            disponobilidad2="Dispinible",ejemplar2="1";
    
    private String titulo3="Romeo y Julieta",materias3="Literatura Contemporanea",autor3="W. Shakespeare",
            disponobilidad3="Dispinible",ejemplar3="6";
    
    private String titulo4="Poesia y Amor",materias4="Poesia",autor4="W. Shakespeare",
            disponobilidad4="Dispinible",ejemplar4="3";
    private Stage stage;
    private int con = 0;
    
    @Override
    public void setScreenParent(ScreensController screenParent) {
        
        controlador = screenParent;        
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal){
        
        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        getcampocodigobarras();
        if(codigobarras.equals("M01"))
        {
            validacion.setVisible(false);
            campocodigobarras.setText("");
            campotitulo.setText(titulo);
            campomateria.setText(materias);
            campoautor.setText(autor);
            campodisponibilidad.setText(disponobilidad);
            campoejemplares.setText(ejemplar);
            cont=titulo;
        }else if(codigobarras.equals("M02"))
        {
            validacion.setVisible(false);
            campocodigobarras.setText("");
            campotitulo.setText(titulo2);
            campomateria.setText(materias2);
            campoautor.setText(autor2);
            campodisponibilidad.setText(disponobilidad2);
            campoejemplares.setText(ejemplar2);
            cont=titulo2;
        }else if(codigobarras.equals("M03"))
        {
            validacion.setVisible(false);
            campocodigobarras.setText("");
            campotitulo.setText(titulo3);
            campomateria.setText(materias3);
            campoautor.setText(autor3);
            campodisponibilidad.setText(disponobilidad3);
            campoejemplares.setText(ejemplar3); 
            cont=titulo3;
        }else if(codigobarras.equals("M04"))
        {
            validacion.setVisible(false);
            campocodigobarras.setText("");
            campotitulo.setText(titulo4);
            campomateria.setText(materias4);
            campoautor.setText(autor4);
            campodisponibilidad.setText(disponobilidad4);
            campoejemplares.setText(ejemplar4); 
            cont=titulo4;
        }else 
        {
            validacion.setText("No se pudo encontrar '" + codigobarras +"'");
        }
   }
    
    public String getcampocodigobarras()
    {
        codigobarras=campocodigobarras.getText();
        return codigobarras;
    }
        
    public void asignar()
    {
        
        if(prestamo1.getText().equals(""))
        {
           prestamo1.setText(cont);
           con++;
           botoneliminar1.setVisible(true);
           fecha1.setVisible(true);
           fecha1.setText("01/07/2013");
        }else if (prestamo2.getText().equals(""))
        {
            prestamo2.setText(cont);
            con++;
            botoneliminar2.setVisible(true);
            fecha2.setVisible(true);
            fecha2.setText("01/07/2013");
        }else if (prestamo3.getText().equals(""))
        {
            prestamo3.setText(cont);
            con++;
            botoneliminar3.setVisible(true);
            fecha3.setVisible(true);
            fecha3.setText("14/06/2013");
        }
        if(con>=3)
        {
            Dialogs.showWarningDialog(stage, "No puede Adicionar mas Libros", "Advertencia", "Advertencia");  
        }
        
    }

    
    @FXML
     public void adicionar(ActionEvent even)
    {
            if(campotitulo.getText().equals("") && campomateria.getText().equals("") 
                    && campoautor.getText().equals("") && campodisponibilidad.getText().equals("") 
                    && campoejemplares.getText().equals(""))
            {
              Dialogs.showWarningDialog(stage, "No ahy Busqueda Realizada", "Advertencia", "Advertencia");  
            }else
            {
            campotitulo.setText("");
            campomateria.setText("");
            campoautor.setText("");
            campodisponibilidad.setText("");
            campoejemplares.setText("");
            asignar();
            }
        
        
    }
    @FXML public void realizarPrestamo()
    {
        botoneliminar1.setVisible(false);
        fecha1.setVisible(false);
        prestamo1.setText("");
        botoneliminar2.setVisible(false);
        fecha2.setVisible(false);
        prestamo2.setText("");
        botoneliminar3.setVisible(false);
        fecha3.setVisible(false);
        prestamo3.setText("");
        Dialogs.showInformationDialog(stage, "Prestamo Realizado Satisfactoriamente","Exito", "Prestamo");
    }
    @FXML
    public void CancelarPrestamo()
    {
        Dialogs.showWarningDialog(stage, "¿ Desea Cancelar el Prestamo ?\nSe Perderan los Libros Adicionados", "Advertencia", "Advertencia");
        botoneliminar1.setVisible(false);
        fecha1.setVisible(false);
        prestamo1.setText("");
        botoneliminar2.setVisible(false);
        fecha2.setVisible(false);
        prestamo2.setText("");
        botoneliminar3.setVisible(false);
        fecha3.setVisible(false);
        prestamo3.setText("");
        con=0;
    }
    @FXML
    public void botoneliminar1()
    {
        Dialogs.showWarningDialog(stage, "¿ Desea Eliminar Este Libro ?", "Advertencia", "Advertencia");
        botoneliminar1.setVisible(false);
        fecha1.setVisible(false);
        prestamo1.setText("");
        con--;
    }
    
    @FXML
    public void botoneliminar2()
    {
        Dialogs.showWarningDialog(stage, "¿ Desea Eliminar Este Libro ?", "Advertencia", "Advertencia");
        botoneliminar2.setVisible(false);
        fecha2.setVisible(false);
        prestamo2.setText("");
        con--;
    }
    
    @FXML
    public void botoneliminar3()
    {
        Dialogs.showWarningDialog(stage, "¿ Desea Eliminar Este Libro ?", "Advertencia", "Advertencia");
        botoneliminar3.setVisible(false);
        fecha3.setVisible(false);
        prestamo3.setText("");
        con--;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
