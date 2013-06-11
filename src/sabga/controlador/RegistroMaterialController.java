
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    @FXML private AnchorPane autores, materias;
    
    @FXML private Group grupoAutor, grupoAutor2, grupoAutor3, grupoAutor4, grupoAutor5, grupoAutor6, grupoAutor7, grupoAutor8,
                        grupoAutor9, grupoAutor10, grupoMateria, grupoMateria2, grupoMateria3, grupoMateria4, grupoMateria5, grupoMateria6,
                        grupoMateria7, grupoMateria8, grupoMateria9, grupoMateria10;
    
    @FXML private Label validarCodigo, validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas,
                        validarEjemplares, validarEditorial, validarClaseMaterial, validarAutor, validarAutor10, validarMateria, validarMateria10;
    
    @FXML private TextField campoCodigoMaterial, campoNumeroClasificacion, campoTitulo, campoAnioPublicacion, campoPublicacion, 
                            campoNumeroPaginas, campoEjemplares, campoEditorial, campoAutor, campoAutor2, campoAutor3, campoAutor4, campoAutor5,
                            campoAutor6, campoAutor7, campoAutor8, campoAutor9, campoAutor10, campoMateria, campoMateria2, campoMateria3, campoMateria4,
                            campoMateria5, campoMateria6, campoMateria7, campoMateria8, campoMateria9, campoMateria10;
    
    @FXML private ComboBox comboClaseMaterial;
    
    
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
    public void dialogoGuardarMateria (ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarGuardarMateria();
    
    }
               
    @FXML
    public void validarCampos(ActionEvent evento){
        
       validarClaseMaterial();                    
       
       Validacion validarMaterial = new Validacion(campoCodigoMaterial.getText(), campoNumeroClasificacion.getText(), campoTitulo.getText(),
                                                    campoAnioPublicacion.getText(), campoPublicacion.getText(), campoNumeroPaginas.getText(),
                                                    campoEjemplares.getText(), campoEditorial.getText(),campoAutor.getText(),
                                                    campoMateria.getText());
       
       validarMaterial.validarMaterial();
       validarCodigo.setText(validarMaterial.getErrorCodigoMaterial());
       validarClasificacion.setText(validarMaterial.getErrorNumeroClasificacion());
       validarTitulo.setText(validarMaterial.getErrorTitulo());
       validarAnioPublicacion.setText(validarMaterial.getErrorAnioPublicacion());
       validarPublicacion.setText(validarMaterial.getErrorPublicacion());
       validarPaginas.setText(validarMaterial.getErrorNumeroPaginas());
       validarEjemplares.setText(validarMaterial.getErrorNumeroEjemplares());
       validarEditorial.setText(validarMaterial.getErrorEditorial());
       validarMateria.setText(validarMaterial.getErrorMateria());
       validarAutor.setText(validarMaterial.getErrorAutor());
       
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
       
   @FXML
  private void validarClaseMaterial() {
       

        if(comboClaseMaterial.getSelectionModel().getSelectedItem()==null){
           
           validarClaseMaterial.setText("Debe seleccionar una opción");
        }
        
        else{
            
           validarClaseMaterial.setText("");
        }
   }
   
    
   ///      ----     METODOS PARA MOSTRAR U OCULTAR LOS CAMPOS DE AUTOR Y MATERIAS      ----
   
   
    @FXML
    public void mostrarCampoAutores2(){   
         
        grupoAutor2.setLayoutY(grupoAutor.getLayoutY()+55);
        grupoAutor2.setLayoutX(grupoAutor.getLayoutX()+82);           
        grupoAutor2.setVisible(true); 
       
    }
    
   @FXML
   public void mostrarCampoAutores3(){
  
        autores.setPrefHeight(autores.getHeight()+50);
        grupoAutor3.setLayoutY(grupoAutor2.getLayoutY()+50);
        grupoAutor3.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor3.setVisible(true); 
          
   }
    
   @FXML
   public void mostrarCampoAutores4(){
        
        autores.setPrefHeight(autores.getHeight()+50);
        grupoAutor4.setLayoutY(grupoAutor3.getLayoutY()+50);
        grupoAutor4.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor4.setVisible(true); 

   }
   
   @FXML
   public void mostrarCampoAutores5(){
    
        grupoAutor5.setLayoutY(grupoAutor4.getLayoutY()+50);
        grupoAutor5.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor5.setVisible(true); 
               
   }
   
   @FXML
   public void mostrarCampoAutores6(){
   
        grupoAutor6.setLayoutY(grupoAutor5.getLayoutY()+50);
        grupoAutor6.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor6.setVisible(true); 
               
   }
   
   @FXML
   public void mostrarCampoAutores7(){
   
        grupoAutor7.setLayoutY(grupoAutor6.getLayoutY()+50);
        grupoAutor7.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor7.setVisible(true); 
                  
   }
    
   @FXML
   public void mostrarCampoAutores8(){
   
        grupoAutor8.setLayoutY(grupoAutor7.getLayoutY()+50);
        grupoAutor8.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor8.setVisible(true); 

   }
   
   @FXML
   public void mostrarCampoAutores9(){

        autores.setPrefHeight(autores.getHeight()+50);
        grupoAutor9.setLayoutY(grupoAutor8.getLayoutY()+50);
        grupoAutor9.setLayoutX(grupoAutor.getLayoutX()+82);
        grupoAutor9.setVisible(true); 

   }
   
   @FXML
   public void mostrarCampoAutores10(){
   
        grupoAutor10.setLayoutY(grupoAutor9.getLayoutY()+50);
        grupoAutor10.setLayoutX(grupoAutor.getLayoutX()+82);
        validarAutor10.setText("Máximo 10 Autores");
        grupoAutor10.setVisible(true); 

   }
   
   @FXML
   public void mostrarCampoMateria2(){
       
        grupoMateria2.setLayoutY(grupoMateria.getLayoutY()+55);
        grupoMateria2.setLayoutX(grupoMateria.getLayoutX()+82);           
        grupoMateria2.setVisible(true);        
   }
   
   @FXML
   public void mostrarCampoMateria3(){
 
        grupoMateria3.setLayoutY(grupoMateria2.getLayoutY()+50);
        grupoMateria3.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria3.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria4(){
 
        materias.setPrefHeight(materias.getHeight()+50);
        grupoMateria4.setLayoutY(grupoMateria3.getLayoutY()+50);
        grupoMateria4.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria4.setVisible(true); 
   }
    
   @FXML
   public void mostrarCampoMateria5(){
 
        grupoMateria5.setLayoutY(grupoMateria4.getLayoutY()+50);
        grupoMateria5.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria5.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria6(){
   
        grupoMateria6.setLayoutY(grupoMateria5.getLayoutY()+50);
        grupoMateria6.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria6.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria7(){
 
        materias.setPrefHeight(materias.getHeight()+50);
        grupoMateria7.setLayoutY(grupoMateria6.getLayoutY()+50);
        grupoMateria7.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria7.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria8(){
 
        grupoMateria8.setLayoutY(grupoMateria7.getLayoutY()+50);
        grupoMateria8.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria8.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria9(){
 
        materias.setPrefHeight(materias.getHeight()+50);
        grupoMateria9.setLayoutY(grupoMateria8.getLayoutY()+50);
        grupoMateria9.setLayoutX(grupoMateria.getLayoutX()+82);
        grupoMateria9.setVisible(true); 
   }
   
   @FXML
   public void mostrarCampoMateria10(){
 
        grupoMateria10.setLayoutY(grupoMateria9.getLayoutY()+50);
        grupoMateria10.setLayoutX(grupoMateria.getLayoutX()+82);
        validarMateria10.setText("Máximo 10 Materias");
        grupoMateria10.setVisible(true); 
   }
    
    /**
     * Initializes the controller class.
     */
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        grupoAutor2.setVisible(false);
        grupoAutor3.setVisible(false);
        grupoAutor4.setVisible(false);
        grupoAutor5.setVisible(false);
        grupoAutor6.setVisible(false);
        grupoAutor7.setVisible(false);
        grupoAutor8.setVisible(false);
        grupoAutor9.setVisible(false);
        grupoAutor10.setVisible(false);
        grupoMateria2.setVisible(false);
        grupoMateria3.setVisible(false);
        grupoMateria4.setVisible(false);
        grupoMateria5.setVisible(false);
        grupoMateria6.setVisible(false);
        grupoMateria7.setVisible(false);
        grupoMateria8.setVisible(false);
        grupoMateria9.setVisible(false);
        grupoMateria10.setVisible(false);
           
    }    

    
}
