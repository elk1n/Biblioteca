
package sabga;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sabga.controlador.PaginaPrincipalController;
import sabga.controlador.dialogos.DetalleMaterialController;
import sabga.controlador.dialogos.NuevaMateriaController;
import sabga.controlador.dialogos.NuevaEditorialController;
import sabga.controlador.dialogos.NuevoAutorController;

public class Sabga extends Application {
        
    public static String paginaInicioId = "paginaInicial";
    public static String paginaInicioArchivo = "vista/PaginaInicio.fxml";
    public static String paginaRegistroMaterialId = "paginaRegistroMaterial";
    public static String paginaRegistroMaterialArchivo = "vista/RegistroMaterial.fxml";
    public static String paginaActualizarMaterialId = "paginaActualizarMaterial";
    public static String paginaActualizarMaterialArchivo = "vista/ActualizarMaterial.fxml" ;
    
    private Stage ventanaPrincipal;
    private BorderPane rootLayout;
    private ScreensController pantallas;           
    private ScreensController controller;
    private ScreensController controladorVistas;
    private Stage primaryStage;
  
    public Sabga(){
           
        pantallas = new ScreensController();
        pantallas.loadScreen(Sabga.paginaInicioId, Sabga.paginaInicioArchivo);
        pantallas.loadScreen(Sabga.paginaRegistroMaterialId, Sabga.paginaRegistroMaterialArchivo);
        pantallas.loadScreen(Sabga.paginaActualizarMaterialId, Sabga.paginaActualizarMaterialArchivo);
        pantallas.setScreen(Sabga.paginaInicioId);   
         
    }
    
    public Stage getStage(){
        
        return ventanaPrincipal; 
    }
    
    public ScreensController getController() {
        
        return controller;
    }
    
    public void cambiarVista(String pantalla){
        
        pantallas.setScreen(pantalla);
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SABGA");
        this.primaryStage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/Libraries.png" )));
        this.primaryStage.setMinHeight(650);
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.centerOnScreen();
        FXMLLoader cargador =  new FXMLLoader(Sabga.class.getResource("vista/PaginaPrincipal.fxml"));
        rootLayout = (BorderPane) cargador.load();
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        
        primaryStage.show();        
          
        PaginaPrincipalController controller = cargador.getController();
        controller.setVentanaPrincipal(this);
        
        mostrarVistas();
    
    }
    
    public void mostrarVistas(){
            
        Group root = new Group();
        root.getChildren().addAll(pantallas);
        root.setLayoutY(140);
        
        rootLayout.setCenter(root);
        controladorVistas = new ScreensController();
        controller = (ScreensController) controladorVistas.getMyScreenControler();
        controladorVistas.setVentanaPrincipal(this);
                  
    }
  
    public void mostrarNuevoAutor() {
    
        try {
    
            FXMLLoader loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/NuevoAutor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nuevo Autor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NuevoAutorController controller = loader.getController();
            controller.setDialogStage(dialogStage);
               // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
          }
    }
    
    public void mostrarNuevaMateria() {
    
        try {
    
            FXMLLoader loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/NuevaMateria.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Materia");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NuevaMateriaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
               // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
          }
    }
    
    public void mostrarNuevaEditorial() {
    
        try {
    
            FXMLLoader loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/NuevaEditorial.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Nueva Editorial");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
           
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            NuevaEditorialController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
          }
    } 
    
    public void dialogoDetalleMaterial(){
        
          try {
    
            FXMLLoader loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/DetalleMaterial.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Detalle Material");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.centerOnScreen();
           
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DetalleMaterialController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
          }
        
    }
  
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
}