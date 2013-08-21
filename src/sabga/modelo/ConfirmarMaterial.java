
package sabga.modelo;

/**
 * @author Elk1n
 */

public class ConfirmarMaterial extends ValidarMaterial {

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
    
    
}
