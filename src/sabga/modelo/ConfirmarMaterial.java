
package sabga.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;
import sabga.preferencias.Preferencias;

/**
 * @author Elk1n
 */

public class ConfirmarMaterial extends ValidarMaterial {
    private Calendar fechas;
    private Preferencias pref;
    public boolean confirmarNuevoAutor(String nombre, String apellidos) {

        if (!validarCampoTexto(nombre, 90)) {
            return false;
        } else if (!validarCampoTexto(apellidos, 90)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean confirmarNuevaMateria(String materia) {

        if (!validarCampoTexto(materia, 90)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean confirmarNuevaEditorial(String editorial) {

        if (!validarCampoTexto(editorial, 90)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean confirmarNuevaClaseMaterial(String material) {

        if (!validarCampoTexto(material, 45)) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean confirmarNuevoTipoMaterial(String material) {

        if (!validarCampoTexto(material, 90)) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean confirmarNuevoLibro(Object claseMaterial, String codigo, String titulo, String anioPublicacion, 
                                       String publicacion, String paginas, String ejemplares, String editorial, 
                                       ObservableList autores, ObservableList materias) {

        if (claseMaterial == null) {
            return false;
        } else if (!validarCampoTexto(codigo, 45)) {
            return false;
        } else if (!validarCampoTexto(titulo, 255)) {
            return false;
        } else if (!validarAnio(anioPublicacion, 4)) {
            return false;
        } else if (!validarCampoTextoNull(publicacion, 255)) {
            return false;
        } else if (!validarNumero(paginas, 10)) {
            return false;
        } else if (!validarNumero(ejemplares, 10)) {
            return false;
        } else if (!validarCampoTexto(editorial, 90)) {
            return false;
        } else if (autores.isEmpty()) {
            return false;
        } else if (materias.isEmpty()) {
            return false;
        } else {
            return true;
        }

    }
 
    public boolean confirmarOtroMaterial(Object tipoMaterial, Object claseMaterial, String codigo, String titulo, 
                                         String copias, ObservableList materias) {

        if (tipoMaterial == null) {
            return false;
        } else if (claseMaterial == null) {
            return false;
        } else if (!validarCampoTexto(codigo, 45)) {
            return false;
        } else if (!validarCampoTexto(titulo, 255)) {
            return false;
        } else if (!validarNumero(copias, 10)) {
            return false;
        } else if (materias.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public boolean confirmarEdicionLibro(String codigo, String titulo, String anioPublicacion, String publicacion, 
                                         String paginas, String editorial, ObservableList editoriales){
        
        if (!validarCampoTexto(codigo, 45)) {
            return false;
        }else if (!validarCampoTexto(titulo, 255)) {
            return false;
        }else if (!validarAnio(anioPublicacion, 4)) {
            return false;
        }else if (!validarCampoTextoNull(publicacion, 255)) {
            return false;
        }else if (!validarNumero(paginas, 10)) {
            return false;
        }else if (!validarDatoArryList(editorial, editoriales)) {
            return false;
        }else{
            return true;
        }     
    }
    
    public boolean confirmarEdicionOM(String codigo, String titulo){
  
        if (!validarCampoTexto(codigo, 45)) {
            return false;
        }else if (!validarCampoTexto(titulo, 255)) {
            return false;
        }else{
            return true;
        }
    }

    public boolean confirmarPrestamo(ObservableList lista, Date fecha, String id, String tipoUsuario, int numeroEjemplares) {

        fechas = Calendar.getInstance();
        fechas = new GregorianCalendar();
        pref = new Preferencias();

        if (lista.isEmpty()) {
            return false;
        } else if (fecha == null || fecha.before(fechas.getTime()) || fecha.equals(fechas.getTime())) {
            return false;
        } else if (!validarCampoTexto(id, 15)) {
            return false;
        } else if(!validarCampoTexto(tipoUsuario, 32) || tipoUsuario.contains("estudiante") && numeroEjemplares > pref.getNumeroEjemplares()){
            return false;
        }else {
            return true;
        }
    }
    
    public boolean confirmarRenovacion(Date fecha) {

        fechas = Calendar.getInstance();
        fechas = new GregorianCalendar();

        if (fecha != null) {
            if (fecha.before(fechas.getTime()) || fecha.equals(fechas.getTime())) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    
    public boolean confirmarReserva(ObservableList lista, String documento, String tipoUsuario, int numeroEjemplares){
    
        pref = new Preferencias();
        if (lista.isEmpty()) {
            return false;
        }else if (!validarCampoTexto(documento, 15)) {
            return false;
        } else if(!validarCampoTexto(tipoUsuario, 32) || tipoUsuario.contains("estudiante") && numeroEjemplares > pref.getNumeroEjemplares()){
            return false;
        }else {
            return true;
        }    
    }
}
