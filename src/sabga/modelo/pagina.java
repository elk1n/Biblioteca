
package sabga.modelo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nanny
 */
public class pagina {
    
 public SimpleStringProperty documento = new SimpleStringProperty();
    public SimpleStringProperty tipo = new SimpleStringProperty();    
    public SimpleStringProperty titulo = new SimpleStringProperty();
    public SimpleStringProperty autor = new SimpleStringProperty();
    public SimpleStringProperty fechaDev = new SimpleStringProperty();
    public SimpleStringProperty estadoPrestamo = new SimpleStringProperty();

    public String getDocumento() {
        return documento.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getTitulo() {
        return titulo.get();
    }

    public String getAutor() {
        return autor.get();
    }

    public String getFechaDev() {
        return fechaDev.get();
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo.get();
    }
    
    
}
