
package sabga.controlador;

import com.sun.javafx.scene.control.WeakEventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.Validacion;

/**
 *
 * @author Elk1n
 *
 */

public class RegistroMaterialController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;
    public String material;
    @FXML private Button prueba = new  Button("prueba");
    @FXML private AnchorPane autoresMaterias;
    
    @FXML private Label validarCodigo, validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas,
                        validarEjemplares, validarEditorial;
    @FXML private TextField campoCodigoMaterial, campoNumeroClasificacion, campoTitulo, campoAnioPublicacion, campoPublicacion, 
                        campoNumeroPaginas, campoEjemplares, campoEditorial;
    
    
    public RegistroMaterialController(){
    
    }
       
    @Override
    public void setScreenParent(ScreensController screenParent) {
       
         controlador = screenParent;         
    }
    
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {
        
	this.ventanaPrincipal = ventanaPrincipal;
    } 
    
    @FXML
    public void dialogoRegistrarAutor (ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarRegistroAutor();
    
    }
      
    
    @FXML
    public void mostrarCampos(){
        
        Label autorL = new Label("Autor");
        TextField autorT = new TextField();
        autorT.setPrefHeight(30);
        autorT.setPrefWidth(255);
        Button mas = new Button("+");
        HBox autores = new HBox(45);
        autores.setAlignment(Pos.CENTER);
        autores.getChildren().add(autorL);
        autores.getChildren().add(autorT);
        autores.getChildren().add(mas);
        autores.setLayoutX(16);
        autores.setLayoutY(138);
        autoresMaterias.getChildren().add(autores);
   
        
        
        /*
        autoresMaterias.setPrefHeight(500);
        materias.setLayoutX(22);
        materias.setLayoutY(180);
        autores = new HBox(25);
        autores.setLayoutX(22);
        autores.setLayoutY(108);
             
      //  autores.getChildren().add(prueba);
          */    
    }
   
    
    @FXML
    public void validarCampos(ActionEvent evento){
                     
       
       Validacion validarMaterial = new Validacion(campoCodigoMaterial.getText(), campoNumeroClasificacion.getText(), campoTitulo.getText(),
                                                    campoAnioPublicacion.getText(), campoPublicacion.getText(), campoNumeroPaginas.getText(),
                                                    campoEjemplares.getText(), campoEditorial.getText());
       
       validarMaterial.validarMaterial();
       validarCodigo.setText(validarMaterial.getErrorCodigoMaterial());
       validarClasificacion.setText(validarMaterial.getErrorNumeroClasificacion());
       validarTitulo.setText(validarMaterial.getErrorTitulo());
       validarAnioPublicacion.setText(validarMaterial.getErrorAnioPublicacion());
       validarPublicacion.setText(validarMaterial.getErrorPublicacion());
       validarPaginas.setText(validarMaterial.getErrorNumeroPaginas());
       validarEjemplares.setText(validarMaterial.getErrorNumeroEjemplares());
       validarEditorial.setText(validarMaterial.getErrorEditorial());
       
             
   }
  
   @FXML
    private void validarNumero(KeyEvent evento){
               
       String digito = evento.getText();
       String recurso=evento.getSource().toString();
          
       String[] campoTexto = recurso.split("=");     
       String id = campoTexto[1];
       String[] id2= id.split(",");      
       String id3 = id2[0];
       
               
       if (KeyCode.getKeyCode(digito)!=null || evento.getCode()==KeyCode.BACK_SPACE || evento.getCode()==KeyCode.RIGHT || evento.getCode()==KeyCode.LEFT ||
               evento.getCode()==KeyCode.SHIFT || evento.getCode()==KeyCode.TAB){
       
          if (evento.getCode()==KeyCode.DIGIT0 || evento.getCode()==KeyCode.DIGIT1 || evento.getCode()==KeyCode.DIGIT2 || evento.getCode()==KeyCode.DIGIT3
               || evento.getCode()==KeyCode.DIGIT4 || evento.getCode()==KeyCode.DIGIT5 || evento.getCode()==KeyCode.DIGIT6 || evento.getCode()==KeyCode.DIGIT7
               || evento.getCode()==KeyCode.DIGIT8 || evento.getCode()==KeyCode.DIGIT9){
                          
              try {
               
                    int numero = Integer.parseInt(digito);

                    if(id3.equals(campoAnioPublicacion.getId())){

                    validarAnioPublicacion.setText("");

                    }

                    if(id3.equals(campoNumeroPaginas.getId())){

                    validarPaginas.setText("");

                    }

                    if(id3.equals(campoEjemplares.getId())){

                    validarEjemplares.setText("");

                    }             
                
                }
                catch(NumberFormatException e){
                    
                    if(id3.equals(campoAnioPublicacion.getId())){
                    
                        validarAnioPublicacion.setText(e.getMessage());
         
                    }
                    if(id3.equals(campoNumeroPaginas.getId())){
                    
                        validarPaginas.setText(e.getMessage());
                
                    }
                    if(id3.equals(campoEjemplares.getId())){
                    
                        validarEjemplares.setText(e.getMessage());
                
                    }                                   
                }          
          }                               
       }
       
       else{
           if(id3.equals(campoAnioPublicacion.getId())){
                                   
               validarAnioPublicacion.setText("Debe ser un número");
       
            }
           
           if(id3.equals(campoNumeroPaginas.getId())){
                    
                validarPaginas.setText("Debe ser un número");
                
           }
           
           if(id3.equals(campoEjemplares.getId())){
                    
                validarEjemplares.setText("Debe ser un número");
                
           }
                
               
       }
        
   } 
   
   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //prueba.setLayoutX(22);
        //prueba.setLayoutY(204);   
    
    }    

    
}
