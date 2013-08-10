
package sabga.configuracion;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sabga.Sabga;
import sabga.controlador.dialogos.DetalleMaterialController;
import sabga.controlador.dialogos.DetalleUsuarioController;
import sabga.controlador.dialogos.InicioSesionController;
import sabga.controlador.dialogos.NuevaEditorialController;
import sabga.controlador.dialogos.NuevaMateriaController;
import sabga.controlador.dialogos.NuevoAutorController;
import sabga.controlador.dialogos.NuevoTipoMaterialController;
import sabga.controlador.dialogos.RestablecerContraseniaController;

/**
 * @author Elk1n
 */

public class Dialogo {
    
    private FXMLLoader loader;
    private AnchorPane page;
    private Stage dialogStage;
    private Scene scene;

    public Stage getDialogStage() {

        return dialogStage;
    }
   
   public void mostrarDialogo(String ruta, String titulo, Stage owner, Sabga sabga, int controlador){
          
       try {

            loader = new FXMLLoader(Sabga.class.getResource(ruta));
            page = (AnchorPane) loader.load();
            dialogStage = new Stage();
            dialogStage.setTitle(titulo);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(owner);
            scene = new Scene(page);
            dialogStage.setScene(scene);

            seleccionControlador(sabga, controlador);
            
        } catch (IOException e) {
            
             Utilidades.mensajeError(owner, e.getMessage(), "Error al cargar la ventana de dialogo\nIntente nuevamente", "Error al cargar la ventana");
          }
    }
    
    private void seleccionControlador(Sabga sabga, int opcion){
              
       switch(opcion){
       
           case 1:
               controladorAutor();
               break;
               
           case 2:
               controladorMateria();
               break;
               
           case 3:
               controladorEditorial();
               break;
               
           case 4:
               controladorDetalleMaterial();
               break;
               
           case 5:
               controladorDetalleUsuario();
               break;
               
           case 6:
               controladorInicioSesion(sabga);
               break;
               
           case 7:
               controladorRestablecerCont(sabga);
               break;
               
           case 8:
               controladorNuevoTipoMateril();
               break;
       }
   }
   
    private void controladorAutor() {

        NuevoAutorController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }

    private void controladorMateria(){
    
        NuevaMateriaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorEditorial(){
    
        NuevaEditorialController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorDetalleMaterial(){
    
        DetalleMaterialController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorDetalleUsuario(){
    
        DetalleUsuarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
     private void controladorNuevoTipoMateril(){
    
        NuevoTipoMaterialController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorInicioSesion(Sabga sabga) {

        InicioSesionController controller = loader.getController();
        controller.setVentanaPrincipal(sabga);
        dialogStage.show();
    }
   
    private void controladorRestablecerCont(Sabga sabga) {

        RestablecerContraseniaController controller = loader.getController();
        controller.setVentanaPrincipal(sabga);
        dialogStage.show();

    }
    
   
} 

