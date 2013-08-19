
package sabga.configuracion;

import javafx.scene.control.Dialogs;
import javafx.stage.Stage;

/**
 * @author Elk1n
 */

public class Utilidades {
    
   private static Dialogs.DialogResponse mensajeError;
   private static Dialogs.DialogResponse mensajeConfimacion;
   private static Dialogs.DialogResponse mensajeInformacion;

   public static Dialogs.DialogResponse getMensajeConfimacion() {
       
        return mensajeConfimacion;
    }

    public static String initCap(String string) {

        char[] letras = string.toLowerCase().toCharArray();

        boolean found = false;

        for (int i = 0; i < letras.length; i++) {

            if (!found && Character.isLetter(letras[i])) {

                letras[i] = Character.toUpperCase(letras[i]);

                found = true;
            } else if (Character.isWhitespace(letras[i]) || letras[i] == '.' || letras[i] == '\'') { // adicionar otros caracteres aquí

                found = false;
            }
        }
        return String.valueOf(letras);
    }
    
    public static void mensajeError(Stage propietario, String mensanje, String encabezado, String titulo){
                        
         mensajeError = Dialogs.showErrorDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);        
    }
    
    public static void mensajeConfirmacion(Stage propietario, String mensanje, String encabezado, String titulo){
                        
         mensajeConfimacion = Dialogs.showConfirmDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK_CANCEL);        
    }
    
    public static void mensajeAdvertencia(Stage propietario, String mensanje, String encabezado, String titulo){
    
        mensajeInformacion = Dialogs.showWarningDialog(propietario, mensanje, encabezado, titulo, Dialogs.DialogOptions.OK);
    }
    
    public static void mensaje(Stage propietario, String mensanje, String encabezado, String titulo){
    
       Dialogs.showInformationDialog(propietario, mensanje, encabezado, titulo);
        
    }
    
}
