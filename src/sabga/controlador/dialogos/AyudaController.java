
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
import javafx.stage.Stage;
import sabga.atributos.Atributos;

/**
 * @author Elk1n
 */
 
public class AyudaController implements Initializable {
   
    private final Atributos atributo;
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
    private final TreeItem<String> editarAutores;
    private final TreeItem<String> editarMaterias;
    private final TreeItem<String> nuevoAutor;
    private final TreeItem<String> nuevaEditorial;
    private final TreeItem<String> nuevaMateria;
    private final TreeItem<String> nuevoTipo;
    private final TreeItem<String> nuevaClase;
    private final TreeItem<String> editarOpciones;
    private final TreeItem<String> detalleMaterial;
    private final TreeItem<String> codigoBarras;
    private final TreeItem<String> usuarios;
    private final TreeItem<String> registrarUsuario;
    private final TreeItem<String> editarUsuario;
    private final TreeItem<String> nuevoGrado;
    private final TreeItem<String> nuevoCurso;
    private final TreeItem<String> nuevaJornda;
    private final TreeItem<String> editarOpcionesUsuario;
    private final TreeItem<String> nuevoUsuario;
    private final TreeItem<String> prestamo;
    private final TreeItem<String> devolucion;
    private final TreeItem<String> reserva;
    private final TreeItem<String> recibo;
    
    public AyudaController(){
        
        atributo = new Atributos();
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
        editarAutores = new TreeItem<>("Editar Autores");
        editarMaterias = new TreeItem<>("Editar Materias");
        nuevoAutor = new TreeItem<>("Registrar Nuevo Autor");
        nuevaEditorial = new TreeItem<>("Registrar Nueva Editorial");
        nuevaMateria = new TreeItem<>("Registrar Nueva Materia");
        nuevoTipo = new TreeItem<>("Nuevo Tipo de Material");
        nuevaClase = new TreeItem<>("Nueva Clase de Material");
        editarOpciones = new TreeItem<>("Editar Opciones de Material");
        detalleMaterial = new TreeItem<>("Ver Detalle del Material");
        codigoBarras = new TreeItem<>("Código de Barras");
        usuarios = new TreeItem<>("Usuarios");
        registrarUsuario = new TreeItem<>("Registrar Usuario");
        editarUsuario = new TreeItem<>("Editar Información del Usuario");
        nuevoGrado = new TreeItem<>("Nuevo Grado");
        nuevoCurso = new TreeItem<>("Nuevo Curso");
        nuevaJornda = new TreeItem<>("Nueva Jornada");
        nuevoUsuario = new TreeItem<>("Nuevo Tipo de Usuario");
        editarOpcionesUsuario = new TreeItem<>("Editar Opciones Usuario");
        prestamo = new TreeItem<>("Préstamo de Material");
        devolucion = new TreeItem<>("Devolución de Material");
        reserva = new TreeItem<>("Reserva de Material");
        recibo = new TreeItem<>("Generar Paz y Salvo");
        ingreso.getChildren().add(ingresar);
        ingreso.getChildren().add(restablecer);
        ingreso.getChildren().add(inicioSesion);         
        administrar.getChildren().add(miCuenta);
        administrar.getChildren().add(contrasenia);
        auxiliar.getChildren().add(registrarAuxiliar);
        auxiliar.getChildren().add(editarAuxiliar);
        materialBibliotecario.getChildren().add(registrarLibro);
        materialBibliotecario.getChildren().add(registrarOtros);
        materialBibliotecario.getChildren().add(listarMaterial);
        materialBibliotecario.getChildren().add(buscarMaterial);
        materialBibliotecario.getChildren().add(filtrarMaterial);
        materialBibliotecario.getChildren().add(edicionGeneral);
        materialBibliotecario.getChildren().add(editarEjemplares);
        materialBibliotecario.getChildren().add(editarAutores);
        materialBibliotecario.getChildren().add(editarMaterias);
        materialBibliotecario.getChildren().add(nuevoAutor);
        materialBibliotecario.getChildren().add(nuevaEditorial);
        materialBibliotecario.getChildren().add(nuevaMateria);
        materialBibliotecario.getChildren().add(nuevoTipo);
        materialBibliotecario.getChildren().add(nuevaClase);
        materialBibliotecario.getChildren().add(editarOpciones);
        materialBibliotecario.getChildren().add(detalleMaterial);
        materialBibliotecario.getChildren().add(codigoBarras);
        usuarios.getChildren().add(registrarUsuario);
        usuarios.getChildren().add(editarUsuario);
        usuarios.getChildren().add(nuevoGrado);
        usuarios.getChildren().add(nuevoCurso);
        usuarios.getChildren().add(nuevaJornda);
        usuarios.getChildren().add(nuevoUsuario);
        usuarios.getChildren().add(editarOpcionesUsuario);
        ayuda.getChildren().add(ingreso);
        ayuda.getChildren().add(administrar);
        ayuda.getChildren().add(auxiliar);
        ayuda.getChildren().add(materialBibliotecario);
        ayuda.getChildren().add(usuarios);
        ayuda.getChildren().add(prestamo);
        ayuda.getChildren().add(devolucion);
        ayuda.getChildren().add(reserva);
        ayuda.getChildren().add(recibo);
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
            }else if (seleccion.getValue().equals(editarAutores.getValue())) {
                lblTitulo.setText("Editar Autores");
                lblContenido.setText(contenido(19));
            }else if (seleccion.getValue().equals(editarMaterias.getValue())) {
                lblTitulo.setText("Editar Materias");
                lblContenido.setText(contenido(20));
            }else if (seleccion.getValue().equals(nuevoAutor.getValue())) {
                lblTitulo.setText("Registrar Nuevo Autor");
                lblContenido.setText(contenido(21));
            }else if (seleccion.getValue().equals(nuevaEditorial.getValue())) {
                lblTitulo.setText("Registrar Nueva Editorial");
                lblContenido.setText(contenido(22));
            }else if (seleccion.getValue().equals(nuevaMateria.getValue())) {
                lblTitulo.setText("Registrar Nueva Materia");
                lblContenido.setText(contenido(23));
            }else if (seleccion.getValue().equals(nuevoTipo.getValue())) {
                lblTitulo.setText("Registrar Nuevo Tipo de Material");
                lblContenido.setText(contenido(24));
            }else if (seleccion.getValue().equals(nuevaClase.getValue())) {
                lblTitulo.setText("Registrar Nueva Clase de Material");
                lblContenido.setText(contenido(25));
            }else if (seleccion.getValue().equals(editarOpciones.getValue())) {
                lblTitulo.setText("Editar Opciones de Material");
                lblContenido.setText(contenido(26));
            }else if (seleccion.getValue().equals(detalleMaterial.getValue())) {
                lblTitulo.setText("Ver Detalle del Material");
                lblContenido.setText(contenido(27));
            }else if (seleccion.getValue().equals(codigoBarras.getValue())) {
                lblTitulo.setText("Código de Barras");
                lblContenido.setText(contenido(28));
            }else if (seleccion.getValue().equals(usuarios.getValue())) {
                lblTitulo.setText("Usuarios");
                lblContenido.setText(contenido(29));
            }else if (seleccion.getValue().equals(registrarUsuario.getValue())) {
                lblTitulo.setText("Registrar Usuario");
                lblContenido.setText(contenido(30));
            }else if (seleccion.getValue().equals(editarUsuario.getValue())) {
                lblTitulo.setText("Editar Información del Usuario");
                lblContenido.setText(contenido(31));
            }else if (seleccion.getValue().equals(nuevoGrado.getValue())) {
                lblTitulo.setText("Registrar Nuevo Grado");
                lblContenido.setText(contenido(32));
            }else if (seleccion.getValue().equals(nuevoCurso.getValue())) {
                lblTitulo.setText("Registrar Nuevo Curso");
                lblContenido.setText(contenido(33));
            }else if (seleccion.getValue().equals(nuevaJornda.getValue())) {
                lblTitulo.setText("Registrar Nueva Jornada");
                lblContenido.setText(contenido(34));
            }else if (seleccion.getValue().equals(nuevoUsuario.getValue())) {
                lblTitulo.setText("Registrar Nuevo Tipo de Usuario");
                lblContenido.setText(contenido(35));
            }else if (seleccion.getValue().equals(editarOpcionesUsuario.getValue())) {
                lblTitulo.setText("Editar Opciones de Usuario");
                lblContenido.setText(contenido(36));
            }else if (seleccion.getValue().equals(prestamo.getValue())) {
                lblTitulo.setText("Préstamo de Material Bibliográfico");
                lblContenido.setText(contenido(37));
            }else if (seleccion.getValue().equals(devolucion.getValue())) {
                lblTitulo.setText("Devolución o Renovación de Material Bibliográfico");
                lblContenido.setText(contenido(38));
            }else if (seleccion.getValue().equals(reserva.getValue())) {
                lblTitulo.setText("Reserva de Material Bibliográfico");
                lblContenido.setText(contenido(39));
            }else if (seleccion.getValue().equals(recibo.getValue())) {
                lblTitulo.setText("Generar Paz y Salvo");
                lblContenido.setText(contenido(40));
            }
        } catch (Exception e) {
           
        }
        
    }
    
    private void tipoUsuario(){
        
        if(atributo.getTipoUsuario()!= null){            
            if(atributo.getTipoUsuario().contains("auxiliar")){
                ayuda.getChildren().remove(auxiliar);
                ayuda.getChildren().remove(recibo);
            }
        }   
    }
    
   public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    
    private void inicio(){
        
        menu.setRoot(ayuda);
        ayuda.setExpanded(true);
        menu.getSelectionModel().selectedItemProperty().addListener( new ChangeListener<TreeItem<String>>() {            
            @Override
            public void changed(ObservableValue<? extends TreeItem<String>> ov, TreeItem<String> t, TreeItem<String> t1) {
                TreeItem<String> selectedItem = (TreeItem<String>) t1;
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
        tipoUsuario();
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
            case 19:
                contenido = "Permite asociar y/o desasociar libros y autores.\n\n"
                        +   "•	Asociar autor.\n" +
                            "       o	Ingresar el nombre del autor en el campo de búsqueda.\n" +
                            "       o	Seleccionar el autor.\n" +
                            "       o	Hacer clic en el botón Añadir Autor.\n\n" +
                            "Nota: si el autor no se encuentra en la lista de resultados, hacer clic en el botón Nuevo Autor.\n\n" +
                            "•	Desasociar Autor.\n" +
                            "       o	Seleccionar un autor de la tabla de autores.\n" +
                            "       o	Hacer clic en el botón Remover Autor\n";
                break;
            case 20:
                contenido = "Permite asociar y/o materias y material bibliotecario.\n\n"+
                            "•	Asociar Materia.\n" +
                            "       o	Ingresar el nombre de la materia en el campo de búsqueda.\n" +
                            "       o	Seleccionar la materia.\n" +
                            "       o	Hacer clic en el botón Añadir Materia.\n\n" +
                            "Nota: si la materia no se encuentra en la lista de resultados, hacer clic en el botón Nueva Materia.\n\n" +
                            "•	Desasociar Materia.\n" +
                            "       o	Seleccionar una materia de la lista.\n" +
                            "       o	Hacer clic en el botón Remover Materia.";
                break;
            case 21:
                contenido = "Permite el registro de un autor. \n\n"+
                            "•	Hacer clic en la opción Nuevo Autor del menú Material y el submenú Opciones de "+
                            "\n        Material o hacer clic en el botón Nuevo Autor que se encuentra en las ventanas "+
                            "\n        Registrar Material y Editar Material.\n" +
                            "•	Ingresar el o los nombres del autor.\n" +
                            "•	Ingresar el o los apellidos del autor.\n" +
                            "•	Hacer clic en el botón Guardar Autor.\n";
                break;
            case 22:
                contenido = "Permite el registro de una nueva editorial.\n\n"+
                            "•	Hacer clic en la opción Nueva Editorial del menú Material y el submenú Opciones "+
                            "\n        de Material o hacer clic en el botón Nueva Editorial que se encuentra en las "+
                            "\n        ventanas Registrar Material y Editar Material.\n" +
                            "•	Ingresar el nombre de la editorial.\n" +
                            "•	Hacer clic en el botón Guardar Editorial.";
                break;
            case 23:
                contenido = "Permite el registro de una nueva materia.\n\n"+
                            "•	Hacer clic en la opción Nueva Materia del menú Material y el submenú Opciones de "+
                            "\n        Material o hacer clic en el botón Nueva Materia que se encuentra en las ventanas"+
                            "\n        Registrar Material y Editar Material.\n" +
                            "•	Ingresar el nombre de la materia.\n" +
                            "•	Hacer clic en el botón Guardar Materia.";
                break;
            case 24:
                contenido = "Permite registra un nuevo tipo de material.\n\n"+
                            "•	Hacer clic en la opción Nuevo Tipo de Material, que se encuentra en el menú"+
                            "\n        Materia y el submenú Opciones de Material.\n" +
                            "•	Ingresar el nombre del nuevo tipo de material.\n" +
                            "•	Hacer clic en el botón Guardar Tipo Material.";
                break;
            case 25:
                contenido = "Permite el registro de una nueva clase de material.\n\n"+
                            "•	Hacer clic en la opción Nueva Clase de Material que se encuentra en el menú"+
                            "\n        Materia y el submenú Opciones de Material.\n" +
                            "•	Ingresar el nombre de la nueva clase de material.\n" +
                            "•	Hacer clic en el botón Guardar Clase Material.";
                break;
            case 26:
                contenido = "Permite editar la información de los autores, editoriales, materias, tipos y clases de material.\n\n"+
                            "•	Hacer clic en el botón listar y seleccionar una opción.\n" +
                            "•	Seleccionar un autor, materia, editorial, tipo o clase de material de la tabla de\n        resultados.\n" +
                            "•	La información se carga en el panel de la derecha, donde puede modificar la\n        información.\n" +
                            "•	Hacer clic en el botón Guardar Cambios.\n" +
                            "•	Eliminar un autor, materia, editorial, tipo o clase de material.\n" +
                            "       o	Seleccionar un autor, materia, editorial, tipo o clase de material de la tabla de\n                resultados.\n" +
                            "       o	Hacer clic en el botón Eliminar.\n\n" +
                            "Nota: el ítem solo puede ser eliminado si no se encuentra asociado a un material.\n";
                break;
            case 27:
                contenido = "Visualiza la información detallada del material se accede mediante el botón Detalle Material,"
                            + " se encuentra en la ventana Editar Material.";
                break;
            case 28:
                contenido = "Visualiza una imagen del código de barra del material bibliotecario, permite guardarlo en formato "
                             + "de imagen o imprimirlo, se accede mediante el botón Código de Barras que se encuentra en la "
                             + "ventana Editar Material o después de registrar un nuevo material siguiendo los pasos en la ventana de dialogo.";
                break;
            case 29:
                contenido = "En este menú se puede visualizar diferentes sub-menús en los cuales podrá registrar y editar "
                            + "los diferentes datos del usuario.";
                break;
            case 30:
                contenido = "Es uno de los  módulos de la aplicación SABGA que ayuda al Administrador a sistematizar la información."
                            + " Este registro permite  buscar, modificar o inhabilitar algún dato por medio del documento de identidad"
                            + " para que los  usuarios de la Institución Educativa puedan hacer uso de los recursos de la biblioteca.\n\n"+
                            "•	Diríjase al menú ubicado bajo de la barra del Buscador.\n" +
                            "•	Seleccionar el menú Usuario.\n" +
                            "•	Seleccionar el submenú Registro de usuario.\n" +
                            "•	Elegir la opción del rol que desea registrar.\n" +
                            "•	Llenar los campos necesarios del formulario.\n" +
                            "•	Presionar el botón Guardar nuevo usuario.\n\n" +
                            "Nota: Si los datos ingresados son correctos se puede observar un mensaje donde se afirma que el registro fue exitoso.";
                break;
            case 31:
                contenido = "Una vez que el usuario se encuentre registrado, el Bibliotecario podrá modificar los "
                            + "datos y el estado del usuario.\n\n"+
                            "Luego de elegir la opción deseada, automáticamente se carga una tabla con información, los pasos a seguir son:\n\n" +
                            "•	Seleccionar la fila que desea modificar.\n" +
                            "•	En el panel derecho se cargará toda la información. \n" +
                            "•	Modificar el dato del usuario.\n" +
                            "•	Presionar el botón Actualizar Información.";
                break;
            case 32:
                contenido = "Esta opción permite ingresar un nuevo grado a la aplicación.\n\n"+
                            "•	Hacer clic en el menú Usuarios.\n"+
                            "•	Seleccionar Opciones de Usuario.\n"+
                            "•	Seleccionar Registrar Grado.\n"+
                            "•	Ingresar un nombre o identificador para el grado.\n"+
                            "•	Hacer clic en el botón Guardar Grado.";
                break;
            case 33:
                contenido = "Esta opción permite ingresar un nuevo curso a la aplicación.\n\n"+
                            "•	Hacer clic en el menú Usuarios.\n"+
                            "•	Seleccionar Opciones de Usuario.\n"+
                            "•	Seleccionar Registrar Curso.\n"+
                            "•	Ingresar un nombre o identificador para el curso.\n"+
                            "•	Hacer clic en el botón Guardar Curso.";
                break;
            case 34:
                contenido = "Esta opción permite ingresar una nueva jornada a la aplicación.\n\n"+
                            "•	Hacer clic en el menú Usuarios.\n"+
                            "•	Seleccionar Opciones de Usuario.\n"+
                            "•	Seleccionar Registrar Jornada.\n"+
                            "•	Ingresar un nombre para la nueva jornada.\n"+
                            "•	Hacer clic en el botón Guardar Jornada.";                            
                break;
            case 35:
                contenido = "Esta opción permite ingresar un nuevo tipo de usuario a la aplicación.\n\n"+
                            "•	Hacer clic en el menú Usuarios.\n"+
                            "•	Seleccionar Opciones de Usuario.\n"+
                            "•	Seleccionar Registrar Tipo de Usuario.\n"+
                            "•	Ingresar un nombre para el nuevo tipo de usuario.\n"+
                            "•	Hacer clic en el botón Guardar Tipo Usuario.";  
                break;
            case 36:
                contenido = "Este sub-menú permite modificar y eliminar  el grado, curso, jornada y tipo del usuario.\n\n"+
                            "•	Seleccionar la opción que desea modificar.\n" +
                            "•	Automáticamente se cargan los datos en la tabla, seleccionar uno.\n" +
                            "•	El dato aparece en un campo, allí se modifica.\n" +
                            "•	Hacer clic en el botón Guardar Cambios.\n" +
                            "•	Si desea eliminarlo hacer clic en el botón Eliminar.\n\n" +
                            "Nota: Solo podrá eliminar una selección si no se encuetra asociada a un usuario.";
                break;
            case 37:
                contenido = "Permite registrar el prestamo de material bibliográfico a los estudiantes de la institución"
                        + " registrados en la biblioteca.\n\n"+
                            "•	Hacer clic en el botón Listar Material o realizar una búsqueda a través del campo\n        Buscar Material.\n" +
                            "•	Seleccionar un material de la lista.\n" +
                            "•	Seleccionar un ejemplar.\n" +
                            "•	Hacer clic en el botón Listar Usuarios o realizar una búsqueda a través del campo\n        Buscar Usuario.\n" +
                            "•	Seleccionar un usuario.\n" +
                            "•	Hacer clic en el campo Fecha Devolución y seleccionar una fecha para entregar el\n        material.\n" +
                            "•	Hacer clic en el botón Prestar.\n\n" +
                            "Para quitar un ejemplar del préstamo.\n\n" +
                            "•	Seleccionar el ejemplar de la lista.\n" +
                            "•	Hacer clic en el botón Remover Ejemplar.\n\n" +
                            "Si se ha realizado una reserva.\n\n" +
                            "•	Hacer clic en el botón Listar Reservas o realizar una búsqueda a través del campo\n        Buscar Reservas.\n" +
                            "•	Seleccionar una reserva.\n" +
                            "•	Hacer clic en el campo Fecha Devolución y seleccionar una fecha para entregar el\n        material.\n" +
                            "•	Hacer clic en el botón Prestar.";
                break;
            case 38:
                contenido = "Permite registrar la devolución o renovación del material prestado a los usuarios de la "
                            + "institución que se encuentran registrados en la biblioteca.\n\n" +
                            "•	Hacer clic en el  botón Listar Préstamos o buscar un préstamo mediante el campo\n        Buscar Préstamo.\n" +
                            "•	Seleccionar un préstamo.\n\n"+ 
                            "Realizar una devolución de todo el material prestado.\n\n" +
                            "•	Seleccionar la opción Devolver Todo.\n" +
                            "•	Hacer clic en el botón Aceptar.\n\n" +
                            "Devolver un ejemplar.\n\n" +
                            "•	Seleccionar un ejemplar de la lista.\n" +
                            "•	Seleccionar la opción Devolver Ejemplar.\n" +
                            "•	Hacer clic en el botón Aceptar.\n\n" +
                            "Renovar todos los ejemplares del préstamo.\n\n"+ 
                            "•	Seleccionar una fecha de devolución.\n" +
                            "•	Seleccionar la opción Renovar Todo.\n" +
                            "•	Hacer clic en el botón Aceptar.\n\n" +
                            "Renovar un ejemplar.\n\n" +
                            "•	Seleccionar un ejemplar de la lista.\n" +
                            "•	Seleccionar una fecha de devolución.\n" +
                            "•	Seleccionar la opción Renovar Ejemplar.\n" +
                            "•	Hacer clic en el botón Aceptar.";
                break;
            case 39:
                contenido = "Permite reservar ejemplares del material bibliográfico con el fin de realizar un préstamo.\n\n"+
                            "•	Hacer clic en el botón Listar Material o realizar una búsqueda a través del campo\n        Buscar Material.\n" +
                            "•	Seleccionar un material de la lista.\n" +
                            "•	Seleccionar un ejemplar.\n" +
                            "•	Hacer clic en el botón Listar Usuarios o realizar una búsqueda a través del campo\n        Buscar Usuario.\n" +
                            "•	Seleccionar un usuario.\n" +
                            "•	Hacer clic en el botón Prestar.\n\n"+
                            "Para quitar un ejemplar de la reserva.\n\n" +
                            "•	Seleccionar el ejemplar de la lista.\n" +
                            "•	Hacer clic en el botón Remover Ejemplar.";
                break;
            case 40:
                contenido = "Permite generar un paz y salvo, indica que el usuario no tiene multas o prestamos por devolver.\n\n"+
                            "•	Hacer clic en el botón Listar Usuarios o realizar una búsqueda a través del campo\n        Buscar Usuario.\n" +
                            "•	Seleccionar un usuario.\n" +
                            "•	Hacer clic en el botón Imprimir o\n"+
                            "•	Hacer clic en el Guardar para salvar una imagen del paz y salvo.\n\n"+
                            "También puede ingresar los datos manualmente en los campos requiridos.";
                break;
                
        }
        return contenido;
    }
    
    
}
