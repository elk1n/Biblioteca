
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
    
    
    public Material(String titulo, String codigo, String tipo, String clase, String id){
        
          this.titulo = new SimpleStringProperty(titulo);
          this.codigo = new SimpleStringProperty(codigo);
          this.tipo = new SimpleStringProperty(tipo);
          this.clase = new SimpleStringProperty(clase);
          this.id = new SimpleStringProperty(id);
    }
    
    public Material(int id, String titulo, String codigo, String autor, String editorial, String materia){
        
        this.idMaterial = new SimpleIntegerProperty(id);
        this.codigo = new SimpleStringProperty(codigo);
        this.titulo = new SimpleStringProperty(titulo);
        this.autores = new SimpleStringProperty(autor);
        this.editorial = new SimpleStringProperty(editorial);
        this.materias = new SimpleStringProperty(materia);
               
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

    @Override
    public String toString() {
        return this.getTitulo()+" "+this.getCodigo()+" "+ this.getClase();
    }
    
}
