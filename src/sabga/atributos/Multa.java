
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Multa {
    
    private final SimpleIntegerProperty id;
    private final SimpleIntegerProperty prestamo;
    private final SimpleIntegerProperty documento;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty fecha;
    private final SimpleStringProperty estado;
    private final SimpleIntegerProperty valor;
    
    public Multa(int id, int prestamo, int documento, String nombre, String fecha, String estado, int valor){
        this.id = new SimpleIntegerProperty(id);
        this.prestamo = new SimpleIntegerProperty(prestamo);
        this.documento = new SimpleIntegerProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);
        this.valor = new SimpleIntegerProperty(valor);        
    }
    
    public int getIdMulta(){
        return this.id.get();
    }
    
    public int getPrestamo(){
        return this.prestamo.get();
    }
    
    public int getDocumento(){
        return this.documento.get();
    }
    
    public String getNombre(){
        return this.nombre.get();
    }
    
    public String getFecha(){
        return this.fecha.get();
    }
    
    public String getEstado(){
        return this.estado.get();
    }
    
    public int getValor(){
        return this.valor.get();
    }
}
