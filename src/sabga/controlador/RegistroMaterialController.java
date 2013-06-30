
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
import javafx.scene.input.KeyEvent;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;
import java.util.regex.*;

/**
 *
 * @author Elk1n
 *
 */

public class RegistroMaterialController implements Initializable, ControlledScreen {

    private Sabga ventanaPrincipal;  
    private ScreensController controlador;

    @FXML private AnchorPane autores, materias, materiasOM;
    
    @FXML private Group grupoAutor, grupoAutor2, grupoAutor3, grupoAutor4, grupoAutor5, grupoAutor6, grupoAutor7, grupoAutor8,
                        grupoAutor9, grupoAutor10, grupoMateria, grupoMateria2, grupoMateria3, grupoMateria4, grupoMateria5, grupoMateria6,
                        grupoMateria7, grupoMateria8, grupoMateria9, grupoMateria10, grupoMateriaOM, grupoMateriaOM2, grupoMateriaOM3, grupoMateriaOM4,
                        grupoMateriaOM5, grupoMateriaOM6, grupoMateriaOM7, grupoMateriaOM8, grupoMateriaOM9, grupoMateriaOM10;
    
    @FXML private Label validarCodigo, validarClasificacion, validarTitulo, validarAnioPublicacion, validarPublicacion, validarPaginas,
                        validarEjemplares, validarEditorial, validarClaseMaterial, validarAutor, validarAutor10, validarMateria, validarMateria10,
                        validarMateriaOM10, validarTipoMaterialOM, validarClaseMaterialOM, validarCodigoMaterialOM, validarNumeroClasificacionOM,
                        validarTituloOM, validarMateriaOM, validarNumeroCopiasOM;
    
    @FXML private TextField campoCodigoMaterial, campoNumeroClasificacion, campoTitulo, campoAnioPublicacion, campoPublicacion, 
                            campoNumeroPaginas, campoEjemplares, campoEditorial, campoAutor, campoAutor2, campoAutor3, campoAutor4, campoAutor5,
                            campoAutor6, campoAutor7, campoAutor8, campoAutor9, campoAutor10, campoMateria, campoMateria2, campoMateria3, campoMateria4,
                            campoMateria5, campoMateria6, campoMateria7, campoMateria8, campoMateria9, campoMateria10, campoCodigoMaterialOM, 
                            campoNumeroClasificacionOM, campoTituloOM, campoMateriaOM, campoMateriaOM2, campoMateriaOM3, campoMateriaOM4, campoMateriaOM5,
                            campoMateriaOM6, campoMateriaOM7, campoMateriaOM8, campoMateriaOM9, campoMateriaOM10, campoNumeroCopias;
    
    @FXML private ComboBox comboClaseMaterial, comboClaseMaterialOM, comboTipoMaterial;
    
    
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
    public void dialogoNuevoAutor (ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevoAutor();
    }
    
     @FXML
    public void dialogoNuevaMateria (ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaMateria();
    }
    
     @FXML
     public void dialogoNuevaEditorial(){
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaEditorial();
     }
               
    @FXML
    public void validarCampos(ActionEvent evento){
                        
       ValidarMaterial validarMaterial = new ValidarMaterial(campoCodigoMaterial.getText(), campoNumeroClasificacion.getText(), campoTitulo.getText(),
                                                    campoAnioPublicacion.getText(), campoPublicacion.getText(), campoNumeroPaginas.getText(),
                                                    campoEjemplares.getText(), campoEditorial.getText(),campoAutor.getText(),
                                                    campoMateria.getText(), comboClaseMaterial.getSelectionModel().getSelectedItem());
       
       validarMaterial.validarNuevoMaterial();
       validarCodigo.setText(validarMaterial.getErrorCodigoMaterial());
       validarClasificacion.setText(validarMaterial.getErrorCodigoClasificacion());
       validarTitulo.setText(validarMaterial.getErrorTitulo());
       validarAnioPublicacion.setText(validarMaterial.getErrorAnioPublicacion());
       validarPublicacion.setText(validarMaterial.getErrorPublicacion());
       validarPaginas.setText(validarMaterial.getErrorNumeroPaginas());
       validarEjemplares.setText(validarMaterial.getErrorNumeroEjemplares());
       validarEditorial.setText(validarMaterial.getErrorEditorial());
       validarMateria.setText(validarMaterial.getErrorMateria());
       validarAutor.setText(validarMaterial.getErrorAutor());
       validarClaseMaterial.setText(validarMaterial.getErrorClaseMaterial());
       
   }
   
    @FXML
    public void validarCamposOM(){
            
        ValidarMaterial validarMaterialOM = new ValidarMaterial(campoCodigoMaterialOM.getText(), campoNumeroClasificacionOM.getText(), campoTituloOM.getText(),
                                                      campoMateriaOM.getText(), comboTipoMaterial.getSelectionModel().getSelectedItem(),
                                                      comboClaseMaterialOM.getSelectionModel().getSelectedItem(), campoNumeroCopias.getText());
        
        validarMaterialOM.validarMaterialOM();
        validarCodigoMaterialOM.setText(validarMaterialOM.getErrorCodigoMaterial());
        validarNumeroClasificacionOM.setText(validarMaterialOM.getErrorCodigoClasificacion());
        validarTituloOM.setText(validarMaterialOM.getErrorTitulo());
        validarMateriaOM.setText(validarMaterialOM.getErrorMateria());
        validarTipoMaterialOM.setText(validarMaterialOM.getErrorTipoMaterial());
        validarClaseMaterialOM.setText(validarMaterialOM.getErrorClaseMaterial());
        validarNumeroCopiasOM.setText(validarMaterialOM.getErrorNumeroEjemplares());
    
    }
    
    @FXML
    public void validarNumeros(KeyEvent evento){
        
        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher;
       // String objeto = evento.getSource().toString();
      
        if(getDesencadenador(evento).equals(campoAnioPublicacion.getId())){
            
              matcher = patron.matcher(campoAnioPublicacion.getText());
            
             if(matcher.matches() || campoAnioPublicacion.getText().equals("")){
                 
                 validarAnioPublicacion.setText("");
             }
             else{
                 validarAnioPublicacion.setText("Debe ser un número");
             }           
        }
        
        if(getDesencadenador(evento).equals(campoNumeroPaginas.getId())){
             
            matcher = patron.matcher(campoNumeroPaginas.getText());
             
             if(matcher.matches() || campoNumeroPaginas.getText().equals("")){
                 
                 validarPaginas.setText("");
             }
             else{
                 validarPaginas.setText("Debe ser un número");
             }
        }
        
        if(getDesencadenador(evento).equals(campoEjemplares.getId())){
             
            matcher = patron.matcher(campoEjemplares.getText());
             
             if(matcher.matches() || campoEjemplares.getText().equals("")){
                 
                 validarEjemplares.setText("");
             }
             else{
                 validarEjemplares.setText("Debe ser un número");
             }
        } 
        
    }
        
   ///      ----     METODOS PARA MOSTRAR U OCULTAR LOS CAMPOS DE AUTOR Y MATERIAS      ----
   
   @FXML
   public void mostrarCampos(KeyEvent evento){
       
       if (getDesencadenador(evento).equals(campoAutor.getId())){
            grupoAutor2.setLayoutY(grupoAutor.getLayoutY()+55);
            grupoAutor2.setLayoutX(grupoAutor.getLayoutX()+82);           
            grupoAutor2.setVisible(true);  
       }
       
       if(getDesencadenador(evento).equals(campoAutor2.getId())){
            autores.setPrefHeight(autores.getHeight()+50);
            grupoAutor3.setLayoutY(grupoAutor2.getLayoutY()+50);
            grupoAutor3.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor3.setVisible(true);            
       }
       
      if (getDesencadenador(evento).equals(campoAutor3.getId())){
            autores.setPrefHeight(autores.getHeight()+50);
            grupoAutor4.setLayoutY(grupoAutor3.getLayoutY()+50);
            grupoAutor4.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor4.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoAutor4.getId())){
            autores.setPrefHeight(autores.getHeight()+50);
            grupoAutor5.setLayoutY(grupoAutor4.getLayoutY()+50);
            grupoAutor5.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor5.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoAutor5.getId())){
            autores.setPrefHeight(autores.getHeight()+50);
            grupoAutor6.setLayoutY(grupoAutor5.getLayoutY()+50);
            grupoAutor6.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor6.setVisible(true);   
      }
      
      if(getDesencadenador(evento).equals(campoAutor6.getId())){          
            grupoAutor7.setLayoutY(grupoAutor6.getLayoutY()+50);
            grupoAutor7.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor7.setVisible(true);  
      }
      
      if(getDesencadenador(evento).equals(campoAutor7.getId())){
            grupoAutor8.setLayoutY(grupoAutor7.getLayoutY()+50);
            grupoAutor8.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor8.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoAutor8.getId())){
            autores.setPrefHeight(autores.getHeight()+50);
            grupoAutor9.setLayoutY(grupoAutor8.getLayoutY()+50);
            grupoAutor9.setLayoutX(grupoAutor.getLayoutX()+82);
            grupoAutor9.setVisible(true); 
     }
      
      if(getDesencadenador(evento).equals(campoAutor9.getId())){
            grupoAutor10.setLayoutY(grupoAutor9.getLayoutY()+50);
            grupoAutor10.setLayoutX(grupoAutor.getLayoutX()+82);
            validarAutor10.setText("Máximo 10 Autores");
            grupoAutor10.setVisible(true); 
      }
      
        //      ----        CAMPOS PARA LA MATERIA          ----
      
      if(getDesencadenador(evento).equals(campoMateria.getId())){
            grupoMateria2.setLayoutY(grupoMateria.getLayoutY()+55);
            grupoMateria2.setLayoutX(grupoMateria.getLayoutX()+82);           
            grupoMateria2.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateria2.getId())){
            grupoMateria3.setLayoutY(grupoMateria2.getLayoutY()+50);
            grupoMateria3.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria3.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateria3.getId())){
            materias.setPrefHeight(materias.getHeight()+50);
            grupoMateria4.setLayoutY(grupoMateria3.getLayoutY()+50);
            grupoMateria4.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria4.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoMateria4.getId())){
            grupoMateria5.setLayoutY(grupoMateria4.getLayoutY()+50);
            grupoMateria5.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria5.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoMateria5.getId())){
            grupoMateria6.setLayoutY(grupoMateria5.getLayoutY()+50);
            grupoMateria6.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria6.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoMateria6.getId())){
            materias.setPrefHeight(materias.getHeight()+50);
            grupoMateria7.setLayoutY(grupoMateria6.getLayoutY()+50);
            grupoMateria7.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria7.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateria7.getId())){
            grupoMateria8.setLayoutY(grupoMateria7.getLayoutY()+50);
            grupoMateria8.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria8.setVisible(true); 
      }
      
      if (getDesencadenador(evento).equals(campoMateria8.getId())){
            materias.setPrefHeight(materias.getHeight()+50);
            grupoMateria9.setLayoutY(grupoMateria8.getLayoutY()+50);
            grupoMateria9.setLayoutX(grupoMateria.getLayoutX()+82);
            grupoMateria9.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateria9.getId())){
            grupoMateria10.setLayoutY(grupoMateria9.getLayoutY()+50);
            grupoMateria10.setLayoutX(grupoMateria.getLayoutX()+82);
            validarMateria10.setText("Máximo 10 Materias");
            grupoMateria10.setVisible(true); 
      }
      
      //            ---- CAMPOS PARA LAS MATERIAS LA PESTAÑA OTRO MATERIAL  -----
      
      if(getDesencadenador(evento).equals(campoMateriaOM.getId())){
            grupoMateriaOM2.setLayoutY(grupoMateriaOM.getLayoutY()+55);
            grupoMateriaOM2.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM2.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM2.getId())){
            grupoMateriaOM3.setLayoutY(grupoMateriaOM2.getLayoutY()+55);
            grupoMateriaOM3.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM3.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM3.getId())){
            grupoMateriaOM4.setLayoutY(grupoMateriaOM3.getLayoutY()+55);
            grupoMateriaOM4.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM4.setVisible(true);
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM4.getId())){
            grupoMateriaOM5.setLayoutY(grupoMateriaOM4.getLayoutY()+55);
            grupoMateriaOM5.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM5.setVisible(true);           
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM5.getId())){
            grupoMateriaOM6.setLayoutY(grupoMateriaOM5.getLayoutY()+55);
            grupoMateriaOM6.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM6.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM6.getId())){
            materiasOM.setPrefHeight(materiasOM.getHeight()+50);
            grupoMateriaOM7.setLayoutY(grupoMateriaOM6.getLayoutY()+55);
            grupoMateriaOM7.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM7.setVisible(true);           
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM7.getId())){
            grupoMateriaOM8.setLayoutY(grupoMateriaOM7.getLayoutY()+55);
            grupoMateriaOM8.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM8.setVisible(true);           
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM8.getId())){
            materiasOM.setPrefHeight(materiasOM.getHeight()+50);
            grupoMateriaOM9.setLayoutY(grupoMateriaOM8.getLayoutY()+55);
            grupoMateriaOM9.setLayoutX(grupoMateriaOM.getLayoutX()+82);           
            grupoMateriaOM9.setVisible(true); 
      }
      
      if(getDesencadenador(evento).equals(campoMateriaOM9.getId())){
            grupoMateriaOM10.setLayoutY(grupoMateriaOM9.getLayoutY()+55);
            grupoMateriaOM10.setLayoutX(grupoMateriaOM.getLayoutX()+82);
            validarMateriaOM10.setText("Máximo 10 Materias");
            grupoMateriaOM10.setVisible(true);  
      }
   }

   //           ---         FIN MOSTRAR CAMPOS          -- 
   
    public String getDesencadenador(KeyEvent eventos){
        
        String objeto = eventos.getSource().toString();        
        Pattern patron = Pattern.compile("[id=]([a-zA-Z0-9]+)[,]");
        Matcher matcher = patron.matcher(objeto);
        matcher.find();        
        String source = matcher.group(1);        
        return source;      
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
        grupoMateriaOM2.setVisible(false);
        grupoMateriaOM3.setVisible(false);
        grupoMateriaOM4.setVisible(false);
        grupoMateriaOM5.setVisible(false);
        grupoMateriaOM6.setVisible(false);
        grupoMateriaOM7.setVisible(false);
        grupoMateriaOM8.setVisible(false);
        grupoMateriaOM9.setVisible(false);
        grupoMateriaOM10.setVisible(false);
           
    }    

    
}
