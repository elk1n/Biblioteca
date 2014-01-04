
package sabga.controlador.dialogos;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Elk1n
 */
 
public class AyudaController implements Initializable {
   
    private Stage dialogStage;
    @FXML
    private TextArea lblContenido;
    @FXML
    private Label lblTitulo;
    @FXML
    private TreeView<String>menu;
    private final TreeItem<String> ayuda;
    private final TreeItem<String> ingreso;
    private final TreeItem<String> ingresar;
    private final TreeItem<String> restablecer;
    private final TreeItem<String> inicioSesion;
    private final TreeItem<String> administrar;
    private final TreeItem<String> miCuenta;
    private final TreeItem<String> contrasenia;
    private final TreeItem<String> auxiliar;
    private final TreeItem<String> registrarAuxiliar;
    private final TreeItem<String> editarAuxiliar;
    private final TreeItem<String> materialBibliotecario;
    private final TreeItem<String> registrarLibro;
    private final TreeItem<String> registrarOtros;
    private final TreeItem<String> listarMaterial;
    private final TreeItem<String> buscarMaterial;
    private final TreeItem<String> filtrarMaterial;
    private final TreeItem<String> edicionGeneral;
    private final TreeItem<String> editarEjemplares;
    
    public AyudaController(){
        
        ayuda = new TreeItem<>("Menú Ayuda SABGA");
        ingreso = new TreeItem<>("Ingreso al Sistema");
        ingresar = new TreeItem<>("Ingresar al Sistema");
        restablecer = new TreeItem<>("Restablecer Contraseña");
        inicioSesion = new TreeItem<>("Iniciar Sesión");
        administrar = new TreeItem<>("Adminitrar Cuenta");
        miCuenta = new TreeItem<>("Modificar Datos Personales"); 
        contrasenia = new TreeItem<>("Cambiar Contraseña");
        auxiliar = new TreeItem<>("Bibliotecario Auxiliar");
        registrarAuxiliar = new TreeItem<>("Registrar Bibliotecario Auxiliar");
        editarAuxiliar = new TreeItem<>("Editar Información del Auxiliar");
        materialBibliotecario = new TreeItem<>("Material Bibliotecario");
        registrarLibro = new TreeItem<>("Registrar Libro");
        registrarOtros = new TreeItem<>("Registrar Otro Material");
        listarMaterial = new TreeItem<>("Listar Material");
        buscarMaterial = new TreeItem<>("Buscar Material");
        filtrarMaterial = new TreeItem<>("Filtrar Material");
        edicionGeneral = new TreeItem<>("Editar Información General");
        editarEjemplares = new TreeItem<>("Editar Ejemplares");
        ingreso.getChildren().addAll(ingresar, restablecer, inicioSesion);         
        administrar.getChildren().addAll(miCuenta, contrasenia);
        auxiliar.getChildren().addAll(registrarAuxiliar, editarAuxiliar);
        materialBibliotecario.getChildren().addAll(registrarLibro, registrarOtros, listarMaterial, buscarMaterial, filtrarMaterial,
                                                   edicionGeneral, editarEjemplares);
        ayuda.getChildren().addAll(ingreso, administrar, auxiliar, materialBibliotecario);
    }
        
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    private void menuAyuda(TreeItem<String> seleccion){
        
        try {
            if (seleccion.getValue().equals(ingresar.getValue())) {
                lblTitulo.setText("Ingreso al Sistema");
                lblContenido.setText(contenido(1));
            } else if (seleccion.getValue().equals(restablecer.getValue())) {
                lblTitulo.setText("Restablecer Contraseña");
                lblContenido.setText(contenido(2));
            } else if (seleccion.getValue().equals(inicioSesion.getValue())) {
                lblTitulo.setText("Iniciar Sesión");
                lblContenido.setText(contenido(3));
            } else if (seleccion.getValue().equals(miCuenta.getValue())) {
                lblTitulo.setText("Datos de la Cuenta");
                lblContenido.setText(contenido(4));
            } else if (seleccion.getValue().equals(contrasenia.getValue())) {
                lblTitulo.setText("Cambiar Contraseña");
                lblContenido.setText(contenido(5));
            } else if (seleccion.getValue().equals(registrarAuxiliar.getValue())) {
                lblTitulo.setText("Registrar Bibliotecario Auxiliar");
                lblContenido.setText(contenido(6));
            } else if (seleccion.getValue().equals(editarAuxiliar.getValue())) {
                lblTitulo.setText("Editar Información del Auxiliar");
                lblContenido.setText(contenido(7));
            } else if (seleccion.getValue().equals(registrarLibro.getValue())) {
                lblTitulo.setText("Registrar Libros");
                lblContenido.setText(contenido(8));
            } else if (seleccion.getValue().equals(ingreso.getValue())) {
                lblTitulo.setText("Ingreso al Sistema");
                lblContenido.setText(contenido(9));
            } else if (seleccion.getValue().equals(administrar.getValue())) {
                lblTitulo.setText("Administrar Cuenta");
                lblContenido.setText(contenido(10));
            }else if (seleccion.getValue().equals(auxiliar.getValue())) {
                lblTitulo.setText("Bibliotecario Auxiliar");
                lblContenido.setText(contenido(11));
            }else if (seleccion.getValue().equals(materialBibliotecario.getValue())) {
                lblTitulo.setText("Material Bibliotecario");
                lblContenido.setText(contenido(12));
            }else if (seleccion.getValue().equals(registrarOtros.getValue())) {
                lblTitulo.setText("Registrar Otro Tipo de Material");
                lblContenido.setText(contenido(13));
            }else if (seleccion.getValue().equals(listarMaterial.getValue())) {
                lblTitulo.setText("Listar Material Bibliográfico");
                lblContenido.setText(contenido(14));
            }else if (seleccion.getValue().equals(buscarMaterial.getValue())) {
                lblTitulo.setText("Buscar Material Bibliográfico");
                lblContenido.setText(contenido(15));
            }else if (seleccion.getValue().equals(filtrarMaterial.getValue())) {
                lblTitulo.setText("Filtrar Material Bibliográfico");
                lblContenido.setText(contenido(16));
            }else if (seleccion.getValue().equals(edicionGeneral.getValue())) {
                lblTitulo.setText("Editar Información General del Material Bibliográfico");
                lblContenido.setText(contenido(17));
            }else if (seleccion.getValue().equals(editarEjemplares.getValue())) {
                lblTitulo.setText("Editar Número de Ejemplares");
                lblContenido.setText(contenido(18));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
    }
    
    private void inicio(){
        
        menu.setRoot(ayuda);
        ayuda.setExpanded(true);
        menu.getSelectionModel().selectedItemProperty().addListener( new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                menuAyuda(selectedItem);
            }
        });        
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicio();
    }
    
    private String contenido(int opcion){
    
        String contenido = "";
        switch (opcion) {
            case 1:
                contenido = "Hacer clic en el Menú Inicio, Todos programas y seleccionar el ítem SABGA o "
                          + "hacer doble clic en el icono de acceso directo al aplicativo, disponible en el Escritorio de Windows.";
                break;
            case 2:
                contenido = "En caso de olvidar la contraseña, SABGA facilita su restablecimiento y para ello se deben realizar los siguientes "
                            + "pasos:\n\n"+
                            "•	Hacer clic en Restablecer contraseña.\n" +
                            "•	Se visualiza el formulario para ingresar la información necesaria.\n"+
                            "•	Ingresar de manera correcta y completa los datos solicitados.\n" +
                            "•	Oprimir Enviar Contraseña.\n" +
                            "•	Oprimir Cancelar para regresar a la pantalla Inicio de sesión.\n\n" +       
                            "Nota: puede hacerse uso del botón Ayuda para obtener las instrucciones paso a paso.";
                break;
            case 3:
                contenido = "Es acceder a la funcionalidad de la aplicación dependiendo del tipo de administrador.\n"
                            + "Luego de ingresar como Bibliotecario, se puede observar en la pantalla  el área de trabajo, "
                            + "un menú principal y un botón Administrar cuenta, con  el nombre del bibliotecario.";
                break;
            case 4:
                contenido = "Se visualizan los datos personales y el nombre de usuario del bibliotecario que ha iniciado sesión. "
                            + "Algunos de estos datos se pueden modificar  con  el fin de realizar correcciones.\n\n" +
                            "•	Ingresar el documento de identidad.\n" +
                            "•	Ingresar el nombre de usuario (se utiliza para iniciar sesión).\n" +
                            "•	Ingresar el nombre.\n" +
                            "•	Ingresar el apellido.\n" +
                            "•	Ingresar el correo electrónico (se utiliza para recuperar la  contraseña "+
                            "y recordar el\n        nombre de usuario).\n" +
                            "•	Ingresar un número de teléfono móvil o fijo (opcional).\n\n"+ 
                            "Nota: el documento del usuario no podrá cambiarse si este ya se encuentra asociado a un préstamo.";
                break;
            case 5:
                contenido = "Permite el cambio de la contraseña que da acceso al sistema SABGA.\n\n" +
                            "•	Ingresar la contraseña actual.\n" +
                            "•	Ingresar la nueva contraseña.\n" +
                            "•	Digitar de nuevo la nueva contraseña.\n" +
                            "•	Hacer clic en el botón Cambiar Contraseña.\n";
                break;
            case 6:
                contenido = "Permite el registro de un bibliotecario, tanto auxiliar como administrador.\n\n"+
                            "•	Hacer clic en el botón Tipo de Bibliotecario, para seleccionar el tipo de"+
                            " bibliotecario\n        que le será asignado.\n" +
                            "•	Ingresar el nombre.\n" +
                            "•	Ingresar el apellido.\n" +
                            "•	Ingresar el nombre de usuario que será usado para ingresar al sistema.\n" +
                            "•	Ingresar una contraseña; la cual será usada para ingresar al sistema.\n" +
                            "•	Ingresar de nuevo la contraseña.\n" +
                            "•	Ingresar el correo electrónico.\n" +
                            "•	Ingresar el documento de identidad.\n" +
                            "•	Ingresar un número de teléfono (opcional).\n" +
                            "•	Hacer clic en el botón Guardar Nuevo Bibliotecario.";
                break;
            case 7:
                contenido = "Permite cambiar el tipo y el estado del bibliotecario.\n\n"+
                            "•	Seleccionar el bibliotecario.\n" +
                            "•	Hacer clic en el botón Tipo Bibliotecario y seleccionar un tipo.\n" +
                            "•	Hacer clic en el botón Estado Bibliotecario y seleccionar un estado.\n" +
                            "•	Hacer clic en el botón Aceptar.";
                break;
            case 8:
                contenido = "Permite realizar el registro de los libros.\n\n" +
                            "•	Hacer clic en el botón Clase de material para seleccionar la categoría del libro.\n" +
                            "•	Ingresar el código de clasificación que se le asignará al libro. Es un código"+
                            " que se\n        asigna según las tablas de Cutter y el sistema de clasificación Dewey.\n" +
                            "•	Ingresar el título del libro.\n" +
                            "•	Ingresar el año de publicación del libro.\n" +
                            "•	Ingresar la publicación. Ciudad y país de publicación y otros detalles.\n" +
                            "•	Ingresar el número de páginas del libro.\n" +
                            "•	Ingresar el número de ejemplares o copias del material que se requiere registrar.\n" +
                            "•	Buscar editorial. La editorial debe estar previamente registrada, por "+
                            "lo tanto solo\n        debe digitar el nombre para que se despliegue una lista"+
                            " con las coincidencias y\n        seleccionar la que corresponda al material. Si"+
                            " la editorial no se encuentra en la\n        lista hacer clic en el botón Nueva "+
                            "Editorial y registrarla, a continuación la podrá\n        seleccionar.\n\n" +
                            "•	Autor o autores:\n" +
                            "       o	Digitar el nombre del autor.\n" +
                            "       o	Seleccionar el autor de la lista.\n" +
                            "       o	Hacer clic en el botón Seleccionar para llevar el autor a la lista de autores.\n\n" +
                            "Nota: Si el autor no se encuentra en la lista de búsqueda puede hacer clic en el "+
                            "botón Nuevo Autor para registrarlo.\n"+
                            "Si se requiere remover un autor de la lista de autores, debe seleccionarlo y hacer clic en el botón Remover.\n\n" +
                            "•	Materias:\n" +
                            "       o	Digitar el nombre de la materia en el campo búsqueda.\n" +
                            "       o	Seleccionar la materia de la lista.\n" +
                            "       o	Hacer clic en el botón Seleccionar para llevar la materia a la lista de materias.\n" +
                            "Nota: Si la materia no se encuentra en la lista de búsqueda, hacer clic en el botón Nueva Materia para registrarla.\n\n" +
                            "Si se requiere remover una materia de la lista de materias, debe seleccionarla y hacer clic en el botón Remover.\n\n" +
                            "•	Hacer clic en el botón Registrar Libro.\n" +
                            "•	Se despliega una ventana que confirma el registro, esta permite abrir la ventana\n        del código de barras.\n";
                break;
            case 9:
                contenido = "Permite ejecutar la aplicación, iniciar sesión o recuperar el nombre de usuario y la contraseña";
                break;
            case 10:
                contenido = "Permite al administrador, tanto bibliotecario como auxiliar, acceder a los dato de la cuenta."
                            + " Al bibliotecario le permite acceder al menú, en el cual puede registrar y modificar datos de bibliotecarios auxiliares.";
                break;
            case 11:
                contenido = "Menú disponible solo para el administrador bibliotecario. Permite el registro, cambio de estado y de tipo "
                            + "de bibliotecario de bibliotecarios auxiliares.";
                break;
            case 12:
                contenido = "Permite al bibliotecario registrar y editar los libros, videos, folletos, CD-ROM y/o cualquier otro tipo de material "
                            + "que la biblioteca de la Institución Educativa, Gilberto Alzate Avendaño, tenga a disposición de los usuarios.";
                break;
            case 13:
                contenido = "Permite el registro de folletos, videos y CD-ROM.\n"+
                            "•	Hacer clic en el botón Tipo de Material para seleccionarlo.\n" +
                            "•	Hacer clic en el botón Clase de Material para seleccionar la categoría.\n" +
                            "•	Ingresar el código que se le asignará al material o código de clasificación. "+
                            "Es un\n        código que se asigna según las tablas de Cutter y el sistema de clasificación Dewey.\n" +
                            "•	Ingresar el título del material.\n" +
                            "•	Ingresar el número de copias del material.\n" +
                            "•	Materias:\n\n" +  
                            "       o	Digitar el nombre de la materia en el campo de búsqueda.\n" +
                            "       o	Seleccionar la materia de la lista.\n" +
                            "       o	Hacer clic en el botón Seleccionar para llevar la materia a la lista de materias.\n\n" +
                            "Nota: Si la materia no se encuentra en la lista hacer clic en el botón Nueva Materia para registrarla.\n\n" +
                            "Si desea remover una materia de la lista, debe seleccionarla y hacer clic en el botón Remover.\n\n" +
                            "•	Hacer clic en el botón Registrar Material.\n" +
                            "•	Se despliega una ventana que confirma el registro, esta permite abrir la ventana del\n        código de barras.";
                break;
            case 14:
                contenido = "Visualiza una tabla con la lista del material ordenado por tipo de material.\n\n" +
                            "•	Hacer clic en el botón Listar Material.\n" +
                            "•	Seleccionar un tipo de material y hacer clic.\n" +
                            "•	Seleccionar un material de la lista.\n\n" +
                            "Al seleccionar un material de la lista, la información completa se carga "
                            + "en el panel derecho para visualizarla o editarla, puede además, al tener un material "
                            + "seleccionado hacer clic en el botón Detalle Material para ver la información detallada.";
                break;
            case 15:
                contenido = "Permite buscar material bibliotecario y mostrarlo en la tabla de resultados, "
                        + "puede hacerse por título, código de clasificación, nombre de editorial, clase de material,"
                        + " tipo de material, publicación, año de publicación, nombre de materia, nombre de autor, o apellido del autor.\n\n" +
                            "•	Verificar que el botón Buscar este seleccionado.\n" +
                            "•	Ingresar un dato en el campo de búsqueda.\n" +
                            "•	Pulsar la tecla Enter o hacer clic en el botón Buscar.\n" +
                            "•	Seleccionar un material de la lista.\n\n" +
                        "Al seleccionar un material de la lista, la información completa se carga en el"
                        + " panel derecho para visualizarla o editarla, puede además, al tener un material "
                        + "seleccionado hacer clic en el botón Detalle Material para ver la información detallada.";
                break;
            case 16:
                contenido = "Cuando la tabla de resultados es extensa puede filtrarlos para encontrar rápidamente el material buscado:\n\n" +
                            "•	Hacer clic en el botón Filtrar.\n" +
                            "•	Ingresar un dato en el campo Filtrar Material.\n" +
                            "•	Seleccionar un material de la lista.\n\n" +
                            "Al seleccionar un material de la lista, la información completa se carga en el panel derecho "
                        + "para visualizarla o editarla, puede además, al tener un material seleccionado hacer clic en el "
                        + "botón Detalle Material para ver la información detallada.";
                break;
            case 17:
                contenido = "Permite editar la información básica o general, previamente la información debe estar cargada.\n\n"+
                            "•	Hacer clic en el botón Clase de Material para seleccionar la categoría del libro.\n" +
                            "•	Modificar el código que se le asignará al material o código de clasificación.\n" +
                            "•	Modificar el título del material.\n" +
                            "•	Modificar el año de publicación del libro (solo libros).\n" +
                            "•	Modificar la publicación. Ciudad y país de publicación y otros detalles cualquiera\n        (solo libros).\n" +
                            "•	Modificar el número de páginas del libro (solo libros).\n" +
                            "•	Modificar editorial. La editorial debe estar previamente registrada por "
                        + "lo tanto solo\n        debe digitar el nombre, se despliega una lista con las coincidencias "
                        + "y se\n        selecciona la que corresponda al material. Si la editorial no se encuentra en la "
                        + "lista\n        hacer clic en el botón Nueva Editorial y registrar una nueva, en seguida\n        seleccionarla.\n" +
                            "•	Hace clic en el botón Guardar Cambios.";
                break;
            case 18:
                contenido = "Permite modificar los ejemplares del material, puede cambiar la disponibilidad, "
                        + "eliminar un ejemplar y adicionar ejemplares.\n\n"
                        +   "•	Modificar Disponibilidad.\n" +
                            "       o	Seleccionar un ejemplar del Listado de Ejemplares.\n" +
                            "       o	Hacer clic en el botón Disponibilidad Ejemplar y seleccionar una opción.\n" +
                            "       o	Hacer clic en el botón Cambiar.\n" +
                            "•	Adicionar Ejemplar.\n" +
                            "       o	Ingresar la cantidad de ejemplares a sumar en el campo Adicionar Ejemplar.\n" +
                            "       o	Hacer clic en el botón Añadir.\n" +
                            "•	Eliminar Ejemplar.\n" +
                            "       o	Seleccionar un ejemplar del Listado de Ejemplares.\n" +
                            "       o	Hacer clic en el botón Eliminar Ejemplar.";
                break;
                
                
        }
        return contenido;
    }
    
    
}
