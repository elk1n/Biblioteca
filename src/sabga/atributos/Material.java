
package sabga.atributos;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author Elk1n
 */

public class Material {
    
   private SimpleStringProperty titulo;
   private SimpleStringProperty codigo;
   private SimpleStringProperty tipo;
   private SimpleStringProperty clase;
   private SimpleStringProperty id;
   private SimpleIntegerProperty idMaterial;
   private SimpleStringProperty autores;
   private SimpleStringProperty editorial;
   private SimpleStringProperty materias;
   private SimpleStringProperty ejemplar;
   private SimpleStringProperty publicacion;
   private SimpleStringProperty anio;
   private SimpleIntegerProperty paginas;
   private SimpleStringProperty codigoIsbn;
    
    
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
    
    public Material(int id, String titulo, String codigo, String ISBN, String tipo, String clase, String editorial, String publicacion,
                    String anioPublicacion, int numeroPaginas){
    
        this.idMaterial = new SimpleIntegerProperty(id);
        this.titulo = new SimpleStringProperty(titulo);
        this.codigo = new SimpleStringProperty(codigo);
        this.codigoIsbn = new SimpleStringProperty(ISBN);
        this.tipo = new SimpleStringProperty(tipo);
        this.clase = new SimpleStringProperty(clase);
        this.editorial = new SimpleStringProperty(editorial);
        this.publicacion = new SimpleStringProperty(publicacion);
        this.anio = new SimpleStringProperty(anioPublicacion);
        this.paginas = new SimpleIntegerProperty(numeroPaginas);    
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
    
    public String getPublicacion(){
        return this.publicacion.get();
    }
    
    public String getAnioPublicacion(){
        return this.anio.get();
    }
    
    public int getNumeroPaginas(){
        return this.paginas.get();
    }
    
    public String getISBN(){
        return this.codigoIsbn.get();
    }
    
    @Override
    public String toString() {
        return this.getTitulo()+" "+this.getCodigo()+" "+ this.getClase();
    }
    
}
