
package sabga.configuracion;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sabga.Sabga;
import sabga.controlador.dialogos.CodigoBarrasController;
import sabga.controlador.dialogos.DetalleMaterialController;
import sabga.controlador.dialogos.DetalleUsuarioController;
import sabga.controlador.dialogos.InicioSesionController;
import sabga.controlador.dialogos.NuevaClaseMaterialController;
import sabga.controlador.dialogos.NuevaEditorialController;
import sabga.controlador.dialogos.NuevaJornadaController;
import sabga.controlador.dialogos.NuevaMateriaController;
import sabga.controlador.dialogos.NuevoAutorController;
import sabga.controlador.dialogos.NuevoCursoController;
import sabga.controlador.dialogos.NuevoGradoController;
import sabga.controlador.dialogos.NuevoTipoMaterialController;
import sabga.controlador.dialogos.NuevoTipoUsuarioController;
import sabga.controlador.dialogos.RestablecerContraseniaController;
import sabga.controlador.dialogos.EditarOpcionesUsuarioController;
import sabga.controlador.dialogos.PreferenciasController;

/**
 * @author Elk1n
 */

public class Dialogo {
    
    private FXMLLoader loader;
    private AnchorPane page;
    private Stage dialogStage;
    private Scene scene;
    private int id;

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
    
    public void dialogoCodigoBarras(Stage owner, String codigo, String titulo, String mensaje, int copias){
        
        try {
            loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/CodigoBarras.fxml"));
            page = (AnchorPane) loader.load();
            dialogStage = new Stage();
            dialogStage.setTitle("Código de Barras");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
            dialogStage.getIcons().add(new Image("file:vista/Imagenes/CodigoBarras.png"));
            dialogStage.initOwner(owner);
            scene = new Scene(page);
            dialogStage.setScene(scene);
                        
            CodigoBarrasController controller = loader.getController();
            controller.setCodigoBarras(codigo);
            controller.pintarCodigo(copias);
            controller.setTitulo(titulo);
            controller.setMensaje(mensaje);
            controller.setDialogStage(dialogStage);        
            dialogStage.showAndWait();
                                   
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
               controladorNuevoTipoMaterial();
               break;
               
           case 9:
               controladorNuevaClaseMaterial();
               break;
               
           case 10:
               controladorNuevoGrado();
               break;
               
           case 11:
               controladorNuevoCurso();
               break;
               
           case 12:
               controladorNuevaJornada();
               break;
               
           case 13:
               controladorNuevoTipoUsu(); 
               break;
               
           case 14:
               controladorEditarOpcionesUsuario();
               break;
               
           case 15:
               controladorPreferencias();
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
        controller.detalleMaterial(id);
        dialogStage.showAndWait();
    }
    
    private void controladorDetalleUsuario(){
        DetalleUsuarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorNuevoTipoMaterial(){    
        NuevoTipoMaterialController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
     
    private void controladorNuevaClaseMaterial(){
        NuevaClaseMaterialController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorNuevoGrado(){
        NuevoGradoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorNuevoCurso(){
        NuevoCursoController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorNuevaJornada(){    
        NuevaJornadaController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorNuevoTipoUsu(){
        NuevoTipoUsuarioController controller = loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.showAndWait();
    }
    
    private void controladorEditarOpcionesUsuario(){
        EditarOpcionesUsuarioController controller= loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.show();
    }
    
     private void controladorPreferencias(){
        PreferenciasController controller= loader.getController();
        controller.setDialogStage(dialogStage);
        dialogStage.show();
    }
    
    private void controladorInicioSesion(Sabga sabga) {
        dialogStage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/LogoBiblioteca1.png" )));
        InicioSesionController controller = loader.getController();
        controller.setVentanaPrincipal(sabga);
        dialogStage.show();
    }
    
    private void controladorRestablecerCont(Sabga sabga) {
        dialogStage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/LogoBiblioteca1.png" )));
        RestablecerContraseniaController controller = loader.getController();
        controller.setVentanaPrincipal(sabga);
        dialogStage.show();
    }
     
    public void setId(int codigo){
        this.id = codigo;
    }
    
      
} 

