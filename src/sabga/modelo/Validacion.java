
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
           errorCodigoMaterial, errorNumeroClasificacion, errorTitulo, errorAnioPublicacion, errorPublicacion, errorNumeroPaginas,
           errorEjemplares, errorEditorial;
    
   private Calendar calendario;
   private int anio, numeroDePaginas, numeroDeEjemplares;
   private boolean fechaPublicacion = false, paginas = false, numeroEjemplares = false;
   
   public Validacion(String codigoMaterial, String numeroClasificacion, String titulo, String anioPublicacion, String publicacion,
                     String numeroPaginas, String ejemplares, String editorial){
       
       
       this.codigoMaterial = codigoMaterial;
       this.numeroClasificacion = numeroClasificacion;
       this.titulo = titulo;
       this.anioPublicacion = anioPublicacion;
       this.publicacion = publicacion;
       this.numeroPaginas = numeroPaginas;
       this.ejemplares = ejemplares;
       this.editorial = editorial;
       
       this.calendario = Calendar.getInstance();
       this.calendario = new GregorianCalendar();
              
   
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
         errorAnioPublicacion = e.getMessage();           
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
                
                this.errorNumeroPaginas = e.getMessage();
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
                
                this.errorEjemplares = e.getMessage();
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
          
   /*
   public void setPrueba(String dato){
        
        prueba = dato;
        
        System.out.println(prueba);
     
    }
    */
   /*
    public String setLo(){
        
        return prueba;             
    }
    */

 }
