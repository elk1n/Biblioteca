
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Material {
    
   public SimpleStringProperty titulo;
   public SimpleStringProperty codigo;
   public SimpleStringProperty tipo;
   public SimpleStringProperty clase;
   public SimpleStringProperty id;
   public SimpleIntegerProperty idMaterial;
   public SimpleStringProperty autores;
   public SimpleStringProperty editorial;
   public SimpleStringProperty materias;
   public SimpleStringProperty ejemplar;
    
    
    public Material(String titulo, String codigo, String tipo, String clase, String id){
        
          this.titulo = new SimpleStringProperty(titulo);
          this.codigo = new SimpleStringProperty(codigo);
          this.tipo = new SimpleStringProperty(tipo);
          this.clase = new SimpleStringProperty(clase);
          this.id = new SimpleStringProperty(id);
    }
    
    public Material(int id, String titulo, String ejemplar, String codigo, String autores, String editorial, String materias){
        
        this.idMaterial = new SimpleIntegerProperty(id);
        this.titulo = new SimpleStringProperty(titulo);
        this.ejemplar = new SimpleStringProperty(ejemplar);
        this.codigo = new SimpleStringProperty(codigo);    
        this.autores = new SimpleStringProperty(autores);
        this.editorial = new SimpleStringProperty(editorial);
        this.materias = new SimpleStringProperty(materias);
               
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

    public String getAutores() {
        return autores.get();
    }

    public String getEditorial() {
        return editorial.get();
    }

    public String getMaterias() {
        return materias.get();
    }

    public int getIdMaterial() {
        return idMaterial.get();
    }
    
    public String getEjemplar(){
        return ejemplar.get();
    }
    
    @Override
    public String toString() {
        return this.getTitulo()+" "+this.getCodigo()+" "+ this.getClase();
    }
    
}
