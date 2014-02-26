
package sabga;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sabga.atributos.Atributos;
import sabga.configuracion.Dialogo;
import sabga.configuracion.Utilidades;
import sabga.controlador.PaginaPrincipalController;

/**
 * @author Elk1n
 */

public class Sabga extends Application {
          
    private Stage ventanaPrincipal, primaryStage;
    private BorderPane rootLayout;
    private final ScreensController pantallas;           
    private ScreensController controller;
    private ScreensController controladorVistas;
    private final Dialogo dialogo;
    private final Atributos atributos;
    private final Group root;
    
    public Sabga(){    
        
        pantallas = new ScreensController();
        dialogo = new Dialogo();
        root =  new Group();   
        atributos = new Atributos();
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;        
        this.primaryStage.setTitle("SABGA");
        this.primaryStage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/LogoBiblioteca1.png" )));
        this.primaryStage.setMinHeight(680);
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.centerOnScreen();

//        FXMLLoader cargador =  new FXMLLoader(Sabga.class.getResource("vista/PaginaPrincipal.fxml"));
//        rootLayout = (BorderPane) cargador.load();
//        Scene scene = new Scene(rootLayout);        
//        primaryStage.setScene(scene);

        dialogoInicioSesion();        
//        primaryStage.show();
        
//        PaginaPrincipalController controller = cargador.getController();
//        controller.setVentanaPrincipal(this);
//        controladores();
//        mostrarVistas();        
    }
    
    public void controladores(){       
        controladorVistas = new ScreensController();
        controller = (ScreensController) controladorVistas.getMyScreenControler();
        controladorVistas.setVentanaPrincipal(this);
    }
    
    public void mostrarVistas(){
        
        root.getChildren().clear();
        root.setLayoutY(140);
        root.getChildren().addAll(pantallas);
        
        rootLayout.setCenter(root);        
    }
    
    public void vistaInicial(Pane panel){
        root.getChildren().clear();
        root.getChildren().add(panel);        
        rootLayout.setCenter(panel);              
    }
    
    public void cargarVista(String nombre, String archivo){
        pantallas.loadScreen(nombre, archivo);
    }
    
    public void descargarPantalla(String pantallaId){       
        pantallas.unloadScreen(pantallaId);
    }
    
    public void cambiarVista(String pantalla){     
        pantallas.setScreen(pantalla);
    }
    
    public void inciarSesion(){  
        
        try {
            FXMLLoader cargador =  new FXMLLoader(Sabga.class.getResource("vista/PaginaPrincipal.fxml"));
            rootLayout = (BorderPane) cargador.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            PaginaPrincipalController controller = cargador.getController();
            controller.setVentanaPrincipal(this);
            controladores();
            dialogo.getDialogStage().close();            
        } catch (IOException ex) {
            Utilidades.mensajeError(null, ex.getMessage(), "No ha sido posible iniciar la aplicación.", "Error Inicio Aplicación");
        }
    }
    
    public void cerrarSesion(){      
        primaryStage.close();
        dialogo.getDialogStage().show();        
    }
        
    public void dialogoInicioSesion(){
        
        dialogo.mostrarDialogo("vista/dialogos/InicioSesion.fxml","Inicio De Sesión", this.primaryStage, this, 6);
        if(atributos.getEstadoBaseDatos() == 0){
            dialogo.getDialogStage().close();
            dialogo.mostrarDialogo("vista/dialogos/Preferencias.fxml", "Preferencias", null, null, 15);            
        }        
    }
    
    public void dialogoRestablecerContrasenia(){        
        dialogo.getDialogStage().hide();
        dialogo.mostrarDialogo("vista/dialogos/RestablecerContrasenia.fxml","Restablecer Contraseña", this.primaryStage, this, 7);        
     }
    
    public void setTitle(String titulo){
        this.primaryStage.setTitle(titulo);
    }
    
    public void ocultarDialogo(){
        dialogo.getDialogStage().hide();
    }
    
    public Stage getPrimaryStage(){        
        return this.primaryStage;
    }
        
    public ScreensController getController() {        
        return controller;
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