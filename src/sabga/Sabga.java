
package sabga;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Sabga extends Application {
    
     private Stage ventanaPrincipal;
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("vista/PaginaPrincipal.fxml"));
        ventanaPrincipal = stage;
        Scene scene = new Scene(root);
        stage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/Libraries.png" ))); 
        stage.setTitle("SABGA");
        
       // stage.getIcons().add(new Image("file:Libraries.png"));
       
        stage.setScene(scene);
        stage.show();
    
    }

    public Stage getStage(){
        
     return ventanaPrincipal; 
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