
package sabga.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Elk1n
 *
 */

public class Validacion {

   private String codigoMaterial, numeroClasificacion, titulo, anioPublicacion, publicacion, numeroPaginas, ejemplares, editorial,
           autor, materia,errorCodigoMaterial, errorNumeroClasificacion, errorTitulo, errorAnioPublicacion, errorPublicacion, errorNumeroPaginas,
           errorEjemplares, errorEditorial, errorAutor, errorMateria, codigoMaterialOM, numeroClasificacionOM, tituloOM, materiasOM, 
           errorCodigoMaterialOM, errorNumeroClasificacionOM, errorTituloOM, errorMateriasOM, errorTipoMaterial, errorClaseMaterialOM, errorClaseMaterial;
    
   private Calendar calendario;
   private int anio, numeroDePaginas, numeroDeEjemplares;
   private boolean fechaPublicacion = false, paginas = false, numeroEjemplares = false;
   private Object tipoMaterial, claseMaterial, claseMaterialOM;
   
   public Validacion(String codigoMaterial, String numeroClasificacion, String titulo, String anioPublicacion, String publicacion,
                     String numeroPaginas, String ejemplares, String editorial, String autor, String materia , Object claseMaterial){
       
       
       this.codigoMaterial = codigoMaterial;
       this.numeroClasificacion = numeroClasificacion;
       this.titulo = titulo;
       this.anioPublicacion = anioPublicacion;
       this.publicacion = publicacion;
       this.numeroPaginas = numeroPaginas;
       this.ejemplares = ejemplares;
       this.editorial = editorial;
       this.autor = autor;
       this.materia = materia;
       this.claseMaterial = claseMaterial;
       this.calendario = Calendar.getInstance();
       this.calendario = new GregorianCalendar();
                 
   }
   
   public Validacion (String codigoMaterial, String numeroClasificacion, String titulo, String materia, Object tipoMaterial,
                      Object claseMaterial){
       
       this.codigoMaterialOM = codigoMaterial;
       this.numeroClasificacionOM = numeroClasificacion;
       this.tituloOM = titulo;
       this.materiasOM = materia;
       this.tipoMaterial = tipoMaterial;
       this.claseMaterialOM = claseMaterial;
          
   }
   
  public void validarMaterialOM(){
      
           
      if (this.codigoMaterialOM == null || this.codigoMaterialOM.equals("") || this.codigoMaterialOM.length()>32){
          
          this.errorCodigoMaterialOM = "Debe rellenar este campo";
      }
      
      if (this.numeroClasificacionOM == null || this.numeroClasificacionOM.equals("") || this.numeroClasificacionOM.length()>45){
          
          this.errorNumeroClasificacionOM = "Debe rellenar este campo";
      }
      
      if(this.tituloOM == null || this.tituloOM.equals("") || this.tituloOM.length()>300){
          
          this.errorTituloOM = "El material debe llevar un título";
      }
  
      if (this.materiasOM == null || this.materiasOM.equals("") || this.materiasOM.length()>45){
          
          this.errorMateriasOM = "Debe rellenar este campo";
          
      }
      
      if (this.tipoMaterial == null){
          
          this.errorTipoMaterial = "Debe seleccionar una opción";
      }
      
      if (this.claseMaterialOM == null ){
          
          this.errorClaseMaterialOM = "Debe seleccionar una opción";
      }
  }
  
  public void validarMaterial(){
      
     if (this.codigoMaterial == null || this.codigoMaterial.equals("") || this.codigoMaterial.length()>32 || this.codigoMaterial.isEmpty()){
         
         this.errorCodigoMaterial="Este dato es obligatorio";
         
     } 
     
     if (this.numeroClasificacion == null || this.numeroClasificacion.equals("") || this.numeroClasificacion.length()>45){
     
        this.errorNumeroClasificacion = "Debe ingresar un número de clasificación";
     
     }
     
     if (this.titulo == null || this.titulo.equals("") || this.titulo.length()>300){
     
        this.errorTitulo = "El libro debe llevar un título";
     
     }
     
    if (validarAnio()==false){
                 
        this.errorAnioPublicacion = "El formato de año es incorrecto";
     
     }
    
    if (this.publicacion.length()>64){
        
        this.errorPublicacion = "Máximo 64 caracteres";
    }
    
    if (validarNumeroPaginas()==false){
    
        this.errorNumeroPaginas = "Debe rellenar este campo";
    }
    
    if (validarEjemplares()==false){
        
        this.errorEjemplares = "Debe rellenar este campo";
    }
    
    if (this.editorial.length()>32 || this.editorial == null || this.editorial.equals("") ){
        
        this.errorEditorial = "Debe rellenar este campo";
                
    }
    
    if (this.autor.length()>90 || this.autor == null || this.autor.equals("") ){
        
        this.errorAutor = "Debe rellenar este campo";
                
    }
    
    if (this.materia.length()>45 || this.materia == null || this.materia.equals("") ){
        
        this.errorMateria = "Debe rellenar este campo";
                
    }
    
    if (this.claseMaterial == null){
        
        this.errorClaseMaterial = "Debe seleccionar una opción";
    }
        
  }  
   
  public boolean validarAnio(){
      
     if (this.anioPublicacion.length()==4){
         
       try{
           
       this.anio = Integer.parseInt(this.anioPublicacion);
        
       if (this.anio>calendario.get(Calendar.YEAR)){
          
           this.fechaPublicacion = false;
           this.errorAnioPublicacion = "Al parecer el libro aún no se publicado";
        }
       
       }
       catch(NumberFormatException e){   
         errorAnioPublicacion = "Debe ser un número";           
       }      
        this.fechaPublicacion=true;
       }
       else if (this.anioPublicacion==null || this.anioPublicacion.equals("")){
                      
       this.fechaPublicacion= true;
       }
       
       else if (this.anioPublicacion.length()>4 || this.anioPublicacion.length()<4){
         
            this.errorAnioPublicacion = "Solo se permiten 4 digitos";     
            this.fechaPublicacion=false;  
     }  
  
   return this.fechaPublicacion;
  }
  
  public boolean validarNumeroPaginas(){
     
       if (this.numeroPaginas!=null && !this.numeroPaginas.equals("") && this.numeroPaginas.length()>=0){
                         
            try{
                this.numeroDePaginas = Integer.parseInt(this.numeroPaginas);
                
                if (this.numeroDePaginas<0){
                    this.paginas = false;
                    this.errorNumeroPaginas = "Debe ser un número positivo";
                 }                
                 this.paginas=true;
                }
            catch(NumberFormatException e){
                
                this.errorNumeroPaginas = "Debe ser un número";
            }            
         this.paginas = true;
       }
       
     else {               
       this.paginas=false;       
       }    
   return this.paginas;
  }
  
  public boolean validarEjemplares(){
      
       if (this.ejemplares!=null && !this.ejemplares.equals("") && this.ejemplares.length()>=0){
                         
            try{
                this.numeroDeEjemplares = Integer.parseInt(this.ejemplares);
                
                if (this.numeroDeEjemplares<0){
                    this.numeroEjemplares = false;
                    this.errorEjemplares = "Debe ser un número positivo";
                 }                
                 this.numeroEjemplares=true;
                }
            catch(NumberFormatException e){
                
                this.errorEjemplares = "Debe ser un número";
            }            
         this.numeroEjemplares = true;
       }
       
     else {               
       this.numeroEjemplares=false;       
       }       
      return this.numeroEjemplares;
  }
  
  public String getErrorCodigoMaterial(){
      
      return this.errorCodigoMaterial;
  }
  
  public String getErrorNumeroClasificacion(){
      
      return this.errorNumeroClasificacion;
  }
  
  public String getErrorTitulo(){
      
      return this.errorTitulo;
  }
  
  public String getErrorAnioPublicacion(){
      
      return this.errorAnioPublicacion;
  }
  
  public String getErrorPublicacion(){
      
      return this.errorPublicacion;
  }
  
  public String getErrorNumeroPaginas(){
      
      return this.errorNumeroPaginas;
  }
  
  public String getErrorNumeroEjemplares(){
      
      return this.errorEjemplares;
  }
  
  public String getErrorEditorial(){
      
      return this.errorEditorial;
  }
  
  public String getErrorAutor(){
      
      return this.errorAutor;
  }
  
  public String getErrorMateria(){
      
      return this.errorMateria;
  }
  
  public String getErrorClaseMaterial(){
      
      return this.errorClaseMaterial;
  }
  public String getErrorCodigoMaterialOM(){
      
      return this.errorCodigoMaterialOM;
  }
  
  public String getErrorNumeroClasificacionOM(){
      
      return this.errorNumeroClasificacionOM;
  }
  
  public String getErrorTituloOM(){
      
      return this.errorTituloOM;
  }
  
  public String getErrorMateriaOM(){
      
      return this.errorMateriasOM;
  }
  
  public String getErrorTipoMaterial(){
      
      return this.errorTipoMaterial;
  }
  
  public String getErrorClaseMaterialOM(){
      
      return this.errorClaseMaterialOM;
  }
  
 
 }
