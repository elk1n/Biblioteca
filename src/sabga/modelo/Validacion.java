package sabga.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

/**
 * @author Elk1n
 */

public class Validacion {

    private String mensajeError;
    private Calendar calendario;

    public boolean validarCampoTexto(String campoTexto, int numeroCaracteres) {

        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty()) {
            this.mensajeError = "No puede dejar este campo en blanco.";
            return false;
        } else if (campoTexto.length() > numeroCaracteres) {
            this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
            return false;
        } else if (campoTexto.trim().equals("")) {
            this.mensajeError = "No deben ser sólo espacios en blanco.";
            return false;
        } else {
            this.mensajeError = "";
            return true;
        }
    }

    public boolean validarCampoTextoNull(String campoTexto, int numeroCaracteres) {

        if(campoTexto == null){
            campoTexto = "";
        }
        if (!campoTexto.isEmpty()) {            
            if (campoTexto.length() > numeroCaracteres) {
                this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
                return false;
            } else if (campoTexto.trim().equals("")) {
                this.mensajeError = "No deben ser sólo espacios en blanco.";
                return false;
            } else {
                this.mensajeError = "";
                return true;
            }
        } else {
            this.mensajeError = "";
            return true;
        }
    }

    public boolean validarNumero(String campoTexto, int numeroCaracteres) {

        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher = patron.matcher(campoTexto);

        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty()) {
            this.mensajeError = "No puede dejar este campo en blanco.";
            return false;
        } else if (campoTexto.length() > numeroCaracteres) {
            this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
            return false;
        } else if (!matcher.matches()) {
            this.mensajeError = "No es un número o no es positivo.";
            return false;
        }else if(matcher.matches() && !validarNumero(campoTexto)){
            this.mensajeError = "El número debe ser mayor o igual a 1.";
            return false;       
        }else {
            this.mensajeError = "";
            return true;
        }
    }
    
    public boolean validarNumeroNull(String campoTexto, int numeroCaracteres) {

        Pattern patron = Pattern.compile("[0-9]+");
        if(campoTexto == null){
            campoTexto = "";
        }
        Matcher matcher = patron.matcher(campoTexto);
        if (!campoTexto.isEmpty()) {
            if (campoTexto.length() > numeroCaracteres) {
                this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
                return false;
            } else if (!matcher.matches()) {
                this.mensajeError = "Debe ser un número.";
                return false;
            } else {
                this.mensajeError = "";
                return true;
            }
        } else {
            this.mensajeError = "";
            return true;
        }
    }
    
    public boolean validarTelefonoNull(String campoTexto, int numeroCaracteres) {

        Pattern patron = Pattern.compile("^(?!\\s)[0-9\\s\\-]+");
        if (campoTexto == null) {
            campoTexto = "";
        }
        Matcher matcher = patron.matcher(campoTexto);
        if (!campoTexto.isEmpty()) {
            if (campoTexto.length() > numeroCaracteres) {
                this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
                return false;
            } else if (!matcher.matches()) {
                this.mensajeError = "Unicamente admite números, espacios y guiones.";
                return false;
            } else {
                this.mensajeError = "";
                return true;
            }
        } else {
            this.mensajeError = "";
            return true;
        }

    }

    public boolean validarCorreo(String correoElectronico, int numeroCaracteres) {

        Pattern patron = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");
        Matcher matcher = patron.matcher(correoElectronico);

        if (correoElectronico == null || correoElectronico.equals("") || correoElectronico.isEmpty()) {
            this.mensajeError = "No puede dejar este campo en blanco.";
            return false;
        } else if (correoElectronico.length() > numeroCaracteres) {
            this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
            return false;
        } else if (!matcher.matches()) {
            this.mensajeError = "No es un E-mail.";
            return false;
        } else {
            this.mensajeError = "";
            return true;
        }
    }

    public boolean validarCorreoNull(String correoElectronico, int numeroCaracteres) {

        Pattern patron = Pattern.compile("[\\w-\\.]{3,}@([\\w-]{2,}\\.)*([\\w-]{2,}\\.)[\\w-]{2,4}");
        Matcher matcher = patron.matcher(correoElectronico);

        if (!correoElectronico.isEmpty()) {
            if (correoElectronico.length() > numeroCaracteres) {
                this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
                return false;
            } else if (!matcher.matches()) {
                this.mensajeError = "No es un E-mail.";
                return false;
            } else {
                this.mensajeError = "";
                return true;
            }
        } else {
            this.mensajeError = "";
            return true;
        }
    }

    public boolean validarNuevaContrasenia(String campoTexto, String confirmacion, int numeroCaracteres) {

        if (campoTexto == null || campoTexto.equals("") || campoTexto.isEmpty() || 
            confirmacion == null || confirmacion.equals("") || confirmacion.isEmpty()) {
            this.mensajeError = "No puede dejar este campo en blanco.";
            return false;
        } else if (!campoTexto.equals(confirmacion)) {
            this.mensajeError = "Las contraseñas no coinciden.";
            return false;
        } else if (campoTexto.length() > numeroCaracteres || confirmacion.length() > numeroCaracteres) {
            this.mensajeError = "Máximo " + numeroCaracteres + " caracteres.";
            return false;
        } else if (campoTexto.trim().equals("") || confirmacion.trim().equals("")) {
            this.mensajeError = "No deben ser sólo espacios en blanco.";
            return false;
        } else {
            this.mensajeError = "";
            return true;
        }
    }

    public boolean validarAnioNull(String campoTexto, int numeroCaracteres) {

        this.calendario = Calendar.getInstance();
        this.calendario = new GregorianCalendar();
        int anio;
        
        if (!campoTexto.isEmpty()) {
            if (validarNumero(campoTexto, numeroCaracteres)) {
                anio = Integer.parseInt(campoTexto);
                if (anio > calendario.get(Calendar.YEAR)) {
                    this.mensajeError = "El año ingresado es mayor al actual.";
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            this.mensajeError = "";
            return true;
        }    
    }
    
    public boolean validarAnioNull2(String campoTexto, int numeroCaracteres) {

        this.calendario = Calendar.getInstance();
        this.calendario = new GregorianCalendar();
        int anio;
        
        if (campoTexto == null) {
            campoTexto = "";
        }
        
        if (!campoTexto.isEmpty()) {
            if (validarNumero(campoTexto, numeroCaracteres)) {
                anio = Integer.parseInt(campoTexto);
                if (anio > calendario.get(Calendar.YEAR)) {
                    this.mensajeError = "El año ingresado es mayor al actual.";
                    return false;
                }
                return true;
            } else {
                return false;
            }
        } else {
            this.mensajeError = "";
            return true;
        }    
    }
    
    
    public boolean validarNumero(String texto){
        
        int numero;
        try {
            numero = Integer.parseInt(texto);
            return numero>=1;
        } catch (NumberFormatException e) {
            this.mensajeError = e.getMessage();
            return false;
        }
    }
     
    public String getDesencadenador(KeyEvent eventos) {
        String objeto = eventos.getSource().toString();
        Pattern patron = Pattern.compile("[id=]([a-zA-Z0-9]+)[,]");
        Matcher matcher = patron.matcher(objeto);
        matcher.find();
        String source = matcher.group(1);
        return source;
    }

    public void validarNumeros(String contenido) {

        Pattern patron = Pattern.compile("[0-9]+");
        Matcher matcher;
        matcher = patron.matcher(contenido);

        if (matcher.matches() || contenido.equals("")) {
            this.mensajeError = "";
        } else {
            this.mensajeError = "Debe ser un número.";
        }
    }

    public boolean validarDatoArryList(String dato, ObservableList lista) {
        
        String d = "";
        if (!dato.equals("") && !dato.isEmpty() && !dato.trim().equals("")) {
            for (Object data : lista) {
                if (data.toString().equals(dato)) {
                    d = data.toString();
                }
            }
            if (d.equals(dato)) {
                this.mensajeError = "";
                return true;
            } else {
                this.mensajeError = "La editorial debe ser seleccionada de la lista.";
                return false;
            }
        }else{
            return true;
        }
    }
            
    public String getMensajeError() {
        return this.mensajeError;
    }
}
