
package sabga.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import sabga.Sabga;
import sabga.ScreensController;
import sabga.configuracion.ControlledScreen;
import sabga.modelo.ValidarMaterial;

/**
 *
 * @author Elk1n
 * 
 */

public class EditarMaterialController implements Initializable, ControlledScreen {
    
    private Sabga ventanaPrincipal;
    private ScreensController controlador;
    
    @FXML private Label validarCodigoClasificacionAC, validarTituloAC, validarAnioPublicacionAC, validarPublicacionAC, validarNumeroPaginasAC,
                        validarEditorialAC, validarEstadoAC, validarAutoresAC, validarMateriasAC;
    
    @FXML private TextField campoCodigoClasificacionAC, campoTituloAC, campoAnioPublicacionAC, campoPublicacionAC, campoNumeroPaginasAC,
                            campoEditorialAC, campoEjemplaresDisponiblesAC, campoHabilitadoAC, campoDeshabilitadoAC, campoMantenimientoAC,
                            campoAutor1AC, campoAutor2AC, campoAutor3AC, campoAutor4AC, campoAutor5AC, campoAutor6AC, campoAutor7AC, campoAutor8AC,
                            campoAutor9AC, campoAutor10AC, campoMateria1AC , campoMateria2AC, campoMateria3AC, campoMateria4AC, campoMateria5AC,
                            campoMateria6AC, campoMateria7AC, campoMateria8AC, campoMateria9AC, campoMateria10AC;
    
    
    @FXML private Button botonNuevaEditorial;
    
    @FXML private ComboBox comboTipoMaterial, comboClaseMaterial;
    
    @FXML private TitledPane acordeonGeneral, acordeonAutores, acordeonMaterias;
    
    @FXML private  Tooltip est;
    
    public EditarMaterialController(){

     
    }
    
     @Override
    public void setScreenParent(ScreensController screenParent) {
      
         controlador = screenParent;
    }
     
    public void setVentanaPrincipal(Sabga ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;
    }
    
    @FXML
     public void dialogoNuevaEditorial(ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaEditorial();
     }
    
    @FXML
    public void dialogoNuevoAutor(ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevoAutor();
    }
    
    @FXML
    public void dialogoNuevaMateria(ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.mostrarNuevaMateria();
    }
    
    @FXML
    public void dialogoDetalleMaterial(ActionEvent evento){
        
        ventanaPrincipal = new Sabga();
        ventanaPrincipal.dialogoDetalleMaterial();        
    }
    
    @FXML
    public void validarCamposAC(ActionEvent evento){
        
        if(comboTipoMaterial.getSelectionModel().getSelectedIndex()==0){
            
            ValidarMaterial validarActualizacion = new ValidarMaterial(campoCodigoClasificacionAC.getText(), campoTituloAC.getText(), campoAnioPublicacionAC.getText(),
                                                         campoPublicacionAC.getText(), campoNumeroPaginasAC.getText(), campoEditorialAC.getText(),
                                                         campoEjemplaresDisponiblesAC.getText(), campoHabilitadoAC.getText(), campoDeshabilitadoAC.getText(),
                                                         campoMantenimientoAC.getText(),campoAutor1AC.getText(), campoAutor2AC.getText(),campoAutor3AC.getText(),
                                                         campoAutor4AC.getText(),campoAutor5AC.getText(),campoAutor6AC.getText(),
                                                         campoAutor7AC.getText(),campoAutor8AC.getText(), campoAutor9AC.getText(), campoAutor10AC.getText(),
                                                         campoMateria1AC.getText(), campoMateria2AC.getText(), campoMateria3AC.getText(), campoMateria4AC.getText(),
                                                         campoMateria5AC.getText(), campoMateria6AC.getText(), campoMateria7AC.getText(), campoMateria8AC.getText(),
                                                         campoMateria9AC.getText(), campoMateria10AC.getText()); 

            validarActualizacion.validarActualizacionMaterial();
            validarCodigoClasificacionAC.setText(validarActualizacion.getErrorCodigoClasificacion());
            validarTituloAC.setText(validarActualizacion.getErrorTitulo());
            validarAnioPublicacionAC.setText(validarActualizacion.getErrorAnioPublicacion());
            validarPublicacionAC.setText(validarActualizacion.getErrorPublicacion());
            validarNumeroPaginasAC.setText(validarActualizacion.getErrorNumeroPaginas());
            validarEditorialAC.setText(validarActualizacion.getErrorEditorial());
            validarEstadoAC.setText(validarActualizacion.getErrorEstado());
            validarAutoresAC.setText(validarActualizacion.getErrorAutor());
            validarMateriasAC.setText(validarActualizacion.getErrorMateria());
            

            if (acordeonMaterias.isExpanded()==false && validarMateriasAC.getText()!=null){

                acordeonMaterias.setText("Materias"+"                se ha encontrado un error!");
            }       
            else{

                acordeonMaterias.setText("Materias");
            }
            if(acordeonAutores.isExpanded()==false && validarAutoresAC.getText()!=null){

                acordeonAutores.setText("Autores"+"                 se ha encontrado un error!");         
            }
            else{
                acordeonAutores.setText("Autores");

           }
            if(acordeonGeneral.isExpanded()==false && errorAcordeon() == false){

                acordeonGeneral.setText("General"+"                 se ha encontrado un error!");        
            }
            else{
                acordeonGeneral.setText("General");
            }

        }
       
        if (comboTipoMaterial.getSelectionModel().getSelectedIndex()==1 || comboTipoMaterial.getSelectionModel().getSelectedIndex()==2 ||
            comboTipoMaterial.getSelectionModel().getSelectedIndex()==3){
            
            ValidarMaterial validarActualizacionOM = new ValidarMaterial(campoCodigoClasificacionAC.getText(), campoTituloAC.getText(),campoEjemplaresDisponiblesAC.getText(),
                                                               campoHabilitadoAC.getText(), campoDeshabilitadoAC.getText(), campoMantenimientoAC.getText(), campoMateria1AC.getText(), 
                                                               campoMateria2AC.getText(), campoMateria3AC.getText(), campoMateria4AC.getText(), campoMateria5AC.getText(), campoMateria6AC.getText(), 
                                                               campoMateria7AC.getText(), campoMateria8AC.getText(),campoMateria9AC.getText(), campoMateria10AC.getText());
            
            validarActualizacionOM.validarActualizacionOM();
            validarCodigoClasificacionAC.setText(validarActualizacionOM.getErrorCodigoClasificacion());
            validarTituloAC.setText(validarActualizacionOM.getErrorTitulo());
            validarEstadoAC.setText(validarActualizacionOM.getErrorEstado());
            validarMateriasAC.setText(validarActualizacionOM.getErrorMateria());
            
            if (acordeonMaterias.isExpanded()==false && validarMateriasAC.getText()!=null){
            
            acordeonMaterias.setText("Materias"+"                se ha encontrado un error!");
            }       
            else {

                acordeonMaterias.setText("Materias");
            }
            if(acordeonGeneral.isExpanded()==false && errorAcordeon() == false){
            
            acordeonGeneral.setText("General"+"                 se ha encontrado un error!");        
            }
            else{
                acordeonGeneral.setText("General");
            }

        }
             
    }
    
  
    /**
     * 
     * Initializes the controller class.
     *
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        
        Platform.runLater(new Runnable() {public void run() { 
        est= new Tooltip("Esto es una prueba de un Tooltip, esto es otra prueba, esto es otra otra prueba");
         botonNuevaEditorial.setTooltip(est); 
            MenuItem h = new MenuItem("Esto es una prueba de un menú contextual ");
            ContextMenu es = new ContextMenu(h);            
            botonNuevaEditorial.setContextMenu(es);
                    
        }});
                
    }    
   
     public boolean errorAcordeon(){
        
        boolean retorno = false; 
        String control = "";
        
        if (validarCodigoClasificacionAC.getText() != null){           
            control += "N";
        }        
        else {
            control += "S";
        }
        if(validarTituloAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarNumeroPaginasAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarEditorialAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarEstadoAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarPublicacionAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        if(validarAnioPublicacionAC.getText() != null){
            control += "N";
        }
        else{
            control += "S";
        }
        
        int auxiliar = control.indexOf("N");
        
       if (auxiliar == -1){
           
           retorno = true;
       }
       else{
           retorno = false;
       }
        return retorno;
    }
    
}
