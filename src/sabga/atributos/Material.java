
package sabga.atributos;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author Elk1n
 */

public class Material {
    
    private final StringProperty titulo;
    private final StringProperty codigo;
    private final StringProperty tipo;
    private final StringProperty clase;
    private final StringProperty id;
    
    public Material(String titulo, String codigo, String tipo, String clase, String id){
        
          this.titulo = new SimpleStringProperty(titulo);
          this.codigo = new SimpleStringProperty(codigo);
          this.tipo = new SimpleStringProperty(tipo);
          this.clase = new SimpleStringProperty(clase);
          this.id = new SimpleStringProperty(id);
    }
        
    public String getTitulo(){    
        return titulo.get();
    }
    
    public  String getCodigo(){    
        return codigo.get();
    }
    
    public String getClase(){
        return clase.get();
    }
    
    public String getId(){
        return id.get();
    }
    
    public String getTipo(){
        return tipo.get();
    }
    
    @Override
    public String toString() {
        return this.getTitulo()+" "+this.getCodigo()+" "+ this.getClase();
    }
    
}
