
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Multa {
    
    private SimpleIntegerProperty id;
    private SimpleIntegerProperty prestamo;
    private SimpleIntegerProperty documento;
    private SimpleStringProperty nombre;
    private SimpleStringProperty fecha;
    private SimpleStringProperty estado;
    private SimpleIntegerProperty valor;
    private SimpleIntegerProperty valorPagado;
    private SimpleIntegerProperty valorTotal;
    private SimpleStringProperty fechaReserva;
    private SimpleStringProperty identificacion;
    private SimpleStringProperty apellido;
    private SimpleStringProperty estadoPrestamo;
    private SimpleStringProperty fechaPago;
    
    public Multa(int id, int prestamo, int documento, String nombre, String fecha, String estado, int valor){
        
        this.id = new SimpleIntegerProperty(id);
        this.prestamo = new SimpleIntegerProperty(prestamo);
        this.documento = new SimpleIntegerProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.fecha = new SimpleStringProperty(fecha);
        this.estado = new SimpleStringProperty(estado);
        this.valor = new SimpleIntegerProperty(valor);        
    }
    
    public Multa(int id, int prestamo, String documento, String nombre, String apellido, String fechaReserva,
                 String fechaPrestamo,
                 String estadoPrestamo, int valorTotal, int valorPagado, int valorDeuda, String fechaPago,
                 String estadoMulta){
    
        this.id = new SimpleIntegerProperty(id);
        this.prestamo = new SimpleIntegerProperty(prestamo);
        this.identificacion = new SimpleStringProperty(documento);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.fechaReserva = new SimpleStringProperty(fechaReserva);
        this.fecha = new SimpleStringProperty(fechaPrestamo);
        this.estadoPrestamo = new SimpleStringProperty(estadoPrestamo);
        this.valorTotal = new SimpleIntegerProperty(valorTotal);
        this.valorPagado = new SimpleIntegerProperty(valorPagado);
        this.valor = new SimpleIntegerProperty(valorDeuda);
        this.fechaPago = new SimpleStringProperty(fechaPago);
        this.estado = new SimpleStringProperty(estadoMulta);
        
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
    
    public int getValorTotal(){
        return this.valorTotal.get();
    }
    
    public int getValorPagado() {
        return this.valorPagado.get();
    }

    public String getFechaReserva() {
        return this.fechaReserva.get();
    }

    public String getIdentificacion() {
        return this.identificacion.get();
    }

    public String getApellido() {
        return this.apellido.get();
    }

    public String getEstadoPrestamo() {
        return this.estadoPrestamo.get();
    }

    public String getFechaPago() {
        return this.fechaPago.get();
    }
}
