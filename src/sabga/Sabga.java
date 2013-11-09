
package sabga;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sabga.configuracion.Dialogo;
import sabga.controlador.PaginaPrincipalController;

/**
 * @author Elk1n
 */

public class Sabga extends Application {
        
    public static String paginaInicialId = "paginaInicio";
    public static String paginaInicialArchivo = "vista/PaginaInicio.fxml";
    public static String paginaRegistroMaterialId = "paginaRegistroMaterial";
    public static String paginaRegistroMaterialArchivo = "vista/RegistroMaterial.fxml";
    public static String paginaActualizarMaterialId = "paginaActualizarMaterial";
    public static String paginaActualizarMaterialArchivo = "vista/EditarMaterial.fxml" ;
    public static String paginaActualizarEMAId = "paginaActualizarEMA";
    public static String paginaActualizarEMAArchivo = "vista/EditarEMA.fxml";
    public static String paginaPrestamoId = "paginaPrestamo";
    public static String paginaPrestamoArchivo = "vista/Prestamo.fxml";
    public static String paginaRegistroUsuariosId = "paginaRegistroUsuarios";
    public static String paginaRegistroUsuariosArchivo = "vista/RegistroUsuario.fxml";
    public static String paginaEstadoUsuarioId = "paginaEstadoUsuario";
    public static String paginaEstadoUsuarioArchivo = "vista/EditarUsuario.fxml";
    public static String paginaRegistroAdminId = "paginaRegistroAdmin";
    public static String paginaRegistroAdminArchivo = "vista/RegistroAdministrador.fxml";
    public static String paginaEdicionAdminId = "paginaEditarBibliotecario";
    public static String paginaEdicionAdminArchivo = "vista/EditarBibliotecario.fxml";
    public static String paginaDevolucionId = "paginaDevolucion";
    public static String paginaDevolucionArchivo = "vista/Devolucion.fxml";
    public static String paginaBuscarId = "paginaBuscar";
    public static String paginaBusacarArchivo = "vista/Buscar.fxml";
    public static String paginaCuentaId = "paginaCuenta";
    public static String paginaCuentaArchivo = "vista/Cuenta.fxml";
    public static String paginaRegistroBibliotecarioId = "paginaRegistroBibliotecario";
    public static String paginaRegistroBiblitotecarioArchivo = "vista/RegistroBibliotecario.fxml";
    
    private Stage ventanaPrincipal, primaryStage;
    private BorderPane rootLayout;
    private final ScreensController pantallas;           
    private ScreensController controller;
    private ScreensController controladorVistas;
    private final Dialogo dialogo;
    
    public Sabga(){
           
        pantallas = new ScreensController();
        pantallas.loadScreen(Sabga.paginaInicialId, Sabga.paginaInicialArchivo);
        pantallas.setScreen(Sabga.paginaInicialId);
        dialogo = new Dialogo();
                 
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        this.primaryStage = primaryStage;        
        this.primaryStage.setTitle("SABGA");
        this.primaryStage.getIcons().add(new Image(Sabga.class.getResourceAsStream( "vista/Imagenes/LogoBiblioteca1.png" )));
        this.primaryStage.setMinHeight(680);
        this.primaryStage.setMinWidth(1000);
        this.primaryStage.centerOnScreen();

        FXMLLoader cargador =  new FXMLLoader(Sabga.class.getResource("vista/PaginaPrincipal.fxml"));
        rootLayout = (BorderPane) cargador.load();
        Scene scene = new Scene(rootLayout);
        
        primaryStage.setScene(scene);
        //Quitar el comentario para habilitar la pàgina de login o inicio de sesión
        //dialogoInicioSesion();        
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
        cargarVista("paginaInicio","vista/PaginaInicio.fxml");
        cambiarVista("paginaInicio");  
        primaryStage.show(); 
        
        dialogo.getDialogStage().close();   
    }
    
    public void cerrarSesion(){      
        primaryStage.close();
        dialogo.getDialogStage().show();        
    }
        
    public void dialogoInicioSesion(){        
        dialogo.mostrarDialogo("vista/dialogos/InicioSesion.fxml","Inicio De Sesión", this.primaryStage, this, 6);
    }
    
    public void dialogoRestablecerContrasenia(){        
        dialogo.getDialogStage().hide();
        dialogo.mostrarDialogo("vista/dialogos/RestablecerContrasenia.fxml","Restablecer Contraseña", this.primaryStage, this, 7);        
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
    public void pruebaDato() throws IOException{
      
    }
   
    public void prueba(){    
         System.out.println("Esto es una prueba");  
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