
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
import sabga.controlador.dialogos.RegistrarAutoresController;

public class Sabga extends Application {
    
    
    public static String paginaInicioId = "paginaInicial";
    public static String paginaInicioArchivo = "vista/PaginaInicio.fxml";
    public static String paginaRegistroMaterialId = "paginaRegistroMaterial";
    public static String paginaRegistroMaterialArchivo = "vista/RegistroMaterial.fxml";
    
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
        rootLayout.setCenter(root);
        controladorVistas = new ScreensController();
        controller = (ScreensController) controladorVistas.getMyScreenControler();
        controladorVistas.setVentanaPrincipal(this);
                  
    }
  
    public void mostrarRegistroAutor() {
    
        try {
    
            FXMLLoader loader = new FXMLLoader(Sabga.class.getResource("vista/dialogos/RegistrarAutores.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Registrar Autor");
            dialogStage.initModality(Modality.WINDOW_MODAL);
           // dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            RegistrarAutoresController controller = loader.getController();
            controller.setDialogStage(dialogStage);
               // Show the dialog and wait until the user closes it
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