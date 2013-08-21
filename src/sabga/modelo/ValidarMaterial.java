
package sabga.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * @author Elk1n
 */

public class ValidarMaterial extends Validacion{

   private String errorNombreAutor, errorApellidosAutor, errorEditorial, errorNombreMateria, errorNuevaClaseMaterial, errorMaterial,
           
           
     
           codigoMaterial, codigoClasificacion, titulo, anioPublicacion, publicacion, numeroPaginas, ejemplares, 
           editorial, autor, materia, habilitado, inhabilitado, mantenimiento, nombreAutor, apellidosAutor, nombreEditorial,
           nombreMateria, nuevoTipoMaterial, nuevaClaseMaterial, 
           
           errorCodigoMaterial, errorCodigoClasificacion, errorTitulo, errorAnioPublicacion, errorPublicacion, errorNumeroPaginas,
           errorEjemplares,  errorAutor, errorMateria, errorTipoMaterial, errorClaseMaterial, errorEstado, 
             errorNombreEditorial,  errorNuevoTipoMaterial,
            errorCantidadEjemplares, errorAnio;
        
   private Calendar calendario;
   private Object tipoMaterial, claseMaterial;
   
   public ValidarMaterial(){
       
   }
   
   //           ---         CONSTRUCTOR PARA VALIDAR NUEVO MATERIAL         -----
     
   public ValidarMaterial(String codigoMaterial, String codigoClasificacion, String titulo, String anioPublicacion, String publicacion,
                     String numeroPaginas, String ejemplares, String editorial, String autor, String materia , Object claseMaterial){
         
       this.codigoMaterial = codigoMaterial;
       this.codigoClasificacion = codigoClasificacion;
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
   
   //           ---         CONSTRUCTOR PARA VALIDAR OTRA CLASE DE MATERIAL         -----
   
   public ValidarMaterial (String codigoMaterial, String codigoClasificacion, String titulo, String materia, Object tipoMaterial,
                      Object claseMaterial, String numeroCopias){
       
       this.codigoMaterial = codigoMaterial;
       this.codigoClasificacion = codigoClasificacion;
       this.titulo = titulo;
       this.materia = materia;
       this.tipoMaterial = tipoMaterial;
       this.claseMaterial = claseMaterial;
       this.ejemplares = numeroCopias;
          
   }
   
   //       ---         CONSTRUCTOR PARA ACTUALIZAR LA INFORMACIÓN DE LOS LIBROS            ---
   
   public ValidarMaterial(String codigoClasificacion, String titulo, String anioPublicacion, String publicacion, String numeroPaginas,
                     String editorial, String ejemplares, String habilitado, String inhabilitado, String mantenimiento,
                     String autor0, String autor1, String autor2, String autor3, String autor4, String autor5, String autor6,
                     String autor7, String autor8, String autor9, String materia0, String materia1, String materia2, String materia3,
                     String materia4, String materia5, String materia6, String materia7, String materia8, String materia9 ){
       
       this.codigoClasificacion = codigoClasificacion;
       this.titulo = titulo;
       this.anioPublicacion = anioPublicacion;
       this.publicacion = publicacion;
       this.numeroPaginas = numeroPaginas;
       this.editorial = editorial;
       this.ejemplares = ejemplares;
       this.habilitado = habilitado;
       this.inhabilitado = inhabilitado;
       this.mantenimiento = mantenimiento;
       /*
       this.autor0 = autor0;
       this.autor1 = autor1;
       this.autor2 = autor2;
       this.autor3 = autor3;
       this.autor4 = autor4;
       this.autor5 = autor5;
       this.autor6 = autor6;
       this.autor7 = autor7;
       this.autor8 = autor8;
       this.autor9 = autor9;
       
       this.materia0 = materia0;
       this.materia1 = materia1;
       this.materia2 = materia2;
       this.materia3 = materia3;
       this.materia4 = materia4;
       this.materia5 = materia5;
       this.materia6 = materia6;
       this.materia7 = materia7;
       this.materia8 = materia8;
       this.materia9 = materia9;
       */
       this.calendario = Calendar.getInstance();
       this.calendario = new GregorianCalendar();
                  
   }
   
   //           CONSTRUCTOR PARA ACTUALIZAR LA INFORMACIÓN DE OTRA CLASE DE MATERIAL            ---
   
   public ValidarMaterial( String codigoClasificacion, String titulo, String ejemplaresDisponibles, String habilitado,
                           String inhabilitado, String mantenimiento, String materia0, String materia1, String materia2,
                           String materia3,String materia4,String materia5, String materia6, String materia7, 
                           String materia8, String materia9){
       
       this.codigoClasificacion = codigoClasificacion;
       this.titulo = titulo;
       this.ejemplares = ejemplaresDisponibles;
       this.habilitado = habilitado;
       this.inhabilitado = inhabilitado;
       this.mantenimiento = mantenimiento;
       
       /*
       this.materia0 = materia0;
       this.materia1 = materia1;
       this.materia2 = materia2;
       this.materia3 = materia3;
       this.materia4 = materia4;
       this.materia5 = materia5;
       this.materia6 = materia6;
       this.materia7 = materia7;
       this.materia8 = materia8;
       this.materia9 = materia9;
  */
   }
   
   //           CONSTRUCTOR PARA ACTUALIZAR LOS ATORES LAS EDITORIALES, LAS MATERIAS, LOS TIPOS DE MATERIALES Y LAS CLASE DE MATERIAL           ---
   
   public ValidarMaterial(String nombreAutor, String apellidosAutor, String nombreEditorial, String nombreMateria, String tipoMaterial, String claseMaterial){
       
       this.nombreAutor = nombreAutor;
       this.apellidosAutor = apellidosAutor;
       this.nombreEditorial = nombreEditorial;
       this.nombreMateria = nombreMateria;
       this.nuevoTipoMaterial = tipoMaterial;
       this.nuevaClaseMaterial = claseMaterial;
        
   } 
   
   //   METODO FINAL (ESO ESPEREO) PARA VALIDAR UN NUEVO AUTOR  
   public void validarNuevoAutor(String nombre, String apellidos){
       
       if(!validarCampoTexto(nombre, 90)){
           this.errorNombreAutor = getMensajeError();
       }
       if(!validarCampoTexto(apellidos, 90)){
           this.errorApellidosAutor = getMensajeError();
       }
   }
   
   //   METODO FINAL PARA VALIDAR UNA NUEVA MATERIA V.4
   public void validarNuevaMateria(String materia){
   
       if(!validarCampoTexto(materia, 90))
           this.errorNombreMateria = getMensajeError();
   }
   
   // METODO FINAL PARA VALIDAR UNA NUEVA EDITORIAL   
   public void validarNuevaEditorial(String editorial){
   
       if(!validarCampoTexto(editorial, 90))
           this.errorEditorial = getMensajeError();
   }
   
   // VALIDAR NUEVA CLASE DE MATERIAL
   public void validarNuevaClaseMaterial(String material){
   
       if(!validarCampoTexto(material, 45)){
           this.errorNuevaClaseMaterial = getMensajeError();      
       }
   }
 // VALIDAR NUEVO TIPO MATERIAL
   public void validarTipoMaterial(String material){
       
       if(!validarCampoTexto(material, 90)){
           this.errorTipoMaterial = getMensajeError();      
       }
   }
   
    public void validarActualizacionOM(){
        
        if(validarCampoTexto(this.codigoClasificacion, 45)==false ){
          
          this.errorCodigoClasificacion = getMensajeError();
        }
      
        if (validarCampoTexto(this.titulo, 300) == false){
          
          this.errorTitulo = getMensajeError();
        }
        
        if (validarEstadoEjemplaresAC(this.ejemplares, this.habilitado, this.inhabilitado, this.mantenimiento, 5) == false){
          
          this.errorEstado = getMensajeError();
        }
        /*
        if(validarMultiplesCampos(this.materia0, this.materia1, this.materia2, this.materia3, this.materia4, this.materia5,
                                this.materia6, this.materia7, this.materia8, this.materia9, 45)==false){
      
        this.errorMateria = "Debe seleccionar al menos una metaria";
      
      } 
    */
    }
  
    public void validarActualizacionMaterial(){
      
      if (validarCampoTexto(this.codigoClasificacion, 45) == false ){          
          this.errorCodigoClasificacion = getMensajeError();
      }
      
      if (validarCampoTexto(this.titulo, 300) == false){          
          this.errorTitulo = getMensajeError();
      }
      
      if (validarUnAnio(this.anioPublicacion, 4) == false){
          
           this.errorAnioPublicacion = getMensajeError();
      }
      
      if(validarCampoTextoNull(this.publicacion, 64) == false){          
          this.errorPublicacion = getMensajeError();
      }
      
      if(validarNumero(this.numeroPaginas, 10) == false){          
           this.errorNumeroPaginas = getMensajeError();
      }
      
      if (validarCampoTexto(this.editorial, 32 ) == false ){        
        this.errorEditorial = getMensajeError();              
      }
      
      if (validarEstadoEjemplaresAC(this.ejemplares, this.habilitado, this.inhabilitado, this.mantenimiento, 5) == false){
          
          this.errorEstado = getMensajeError();
      }
      /*
      if (validarMultiplesCampos(this.autor0, this.autor1, this.autor2, this.autor3, this.autor4, this.autor5,
                                 this.autor6, this.autor7, this.autor8, this.autor9, 90)==false ){
      
          this.errorAutor = "Debe seleccionar al menos un autor";
      }
      
      if(validarMultiplesCampos(this.materia0, this.materia1, this.materia2, this.materia3, this.materia4, this.materia5,
                                this.materia6, this.materia7, this.materia8, this.materia9, 45)==false){
      
        this.errorMateria = "Debe seleccionar al menos una metaria";
      
      }     
   */
  }
  
    public void validarMateriaAC(){
      
      if (validarCampoTexto(this.nombreMateria, 45) == false){          
          this.errorNombreMateria = getMensajeError();         
      }        
    }
       
    public void validarAutorAC(){
        
         if (validarCampoTexto(this.nombreAutor, 45) == false ){                    
             this.errorNombreAutor = getMensajeError();
      
         }
    
         if(validarCampoTexto(this.apellidosAutor, 45) == false ){                   
             this.errorApellidosAutor = getMensajeError();
      }        
    }
    
    public void validarEditorialAC(){
        
         if(validarCampoTexto(this.nombreEditorial ,45) == false){          
          this.errorNombreEditorial = getMensajeError();      
         }    
    }
  
    public void validarMaterialOM(){
                 
      if (validarCampoTexto(this.codigoMaterial, 32) == false){          
          this.errorCodigoMaterial = getMensajeError();
      }
      
      if (validarCampoTexto(this.codigoClasificacion, 45) == false){          
          this.errorCodigoClasificacion = getMensajeError();
      }
      
      if(validarCampoTexto(this.titulo, 300) == false){          
          this.errorTitulo = getMensajeError();
      }
  
      if (validarCampoTexto(this.materia, 45) == false){          
          this.errorMateria = getMensajeError();         
      }
      
      if (this.tipoMaterial == null){
          this.errorTipoMaterial = "Debe seleccionar una opción";
      }
      
      if (this.claseMaterial == null ){          
          this.errorClaseMaterial = "Debe seleccionar una opción";
      }
      
      if (validarNumero(this.ejemplares, 8)==false){       
        this.errorEjemplares = getMensajeError();
     }
      
  }
  
    public void validarNuevoMaterial(){
      
     if (validarCampoTexto(this.codigoMaterial, 32) == false){         
         this.errorCodigoMaterial = getMensajeError();         
     } 
     
     if (validarCampoTexto(this.codigoClasificacion, 45) == false){    
        this.errorCodigoClasificacion = getMensajeError();   
     }
     
     if (validarCampoTexto(this.titulo, 300) == false){
        this.errorTitulo = getMensajeError();     
     }
     
    if (validarUnAnio(this.anioPublicacion, 4)==false){                 
        this.errorAnioPublicacion = getMensajeError();     
     }
    
    if (validarCampoTextoNull(this.publicacion, 64) == false){        
        this.errorPublicacion = getMensajeError();
    }
    
    if (validarNumero(this.numeroPaginas, 8)==false){
        this.errorNumeroPaginas = getMensajeError();
    }
    
    if (validarNumero(this.ejemplares, 8)==false){       
        this.errorEjemplares = getMensajeError();
    }
    
    if (validarCampoTexto(this.editorial, 32) == false){      
        this.errorEditorial = getMensajeError();               
    }
    
    if (validarCampoTexto(this.autor, 90) == false){       
        this.errorAutor = getMensajeError();                
    }
    
    if (validarCampoTexto(this.materia, 45) == false ){        
        this.errorMateria = getMensajeError();               
    }
    
    if (this.claseMaterial == null){        
        this.errorClaseMaterial = "Debe seleccionar una opción";
    }
        
  } 
         
    public boolean validarEstadoEjemplaresAC(String ejemplares, String habilitado, String inhabilitado, String mantenimineto, int numeroCaracteres){
        
        int numeroTotalEjemplares, ejemplatesHabilitados, ejemplaresInhabilitados, ejemplarsMantenimiento;
    
        if(validarNumero(ejemplares, numeroCaracteres) && validarNumero(habilitado, numeroCaracteres) &&
                         validarNumero(inhabilitado, numeroCaracteres) && validarNumero(mantenimineto, numeroCaracteres)){
                         
                numeroTotalEjemplares = Integer.parseInt(ejemplares);
                ejemplatesHabilitados = Integer.parseInt(habilitado);
                ejemplaresInhabilitados = Integer.parseInt(inhabilitado);
                ejemplarsMantenimiento = Integer.parseInt(mantenimineto);
                
                if (ejemplatesHabilitados + ejemplaresInhabilitados + ejemplarsMantenimiento < numeroTotalEjemplares || 
                    ejemplatesHabilitados + ejemplaresInhabilitados + ejemplarsMantenimiento > numeroTotalEjemplares ){
                    
                    this.errorCantidadEjemplares = "El número de ejemplares no coincide";
                    
                    return false;
                }
          return true;
        }
        else{
            
            return false;
        }
    }
    
    public String getErrorCodigoMaterial(){      
      return this.errorCodigoMaterial;
    }
  
    public String getErrorCodigoClasificacion(){      
      return this.errorCodigoClasificacion;
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
    //
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
   //
    public String getErrorTipoMaterial(){      
      return this.errorTipoMaterial;
    }
    //
    public String getErrorNombreAutor() {
        return this.errorNombreAutor;
    }
    //
    public String getErrorApellidosAutor() {
        return this.errorApellidosAutor;
    }
    
    //
    public String getErrorNombreMateria() {
        return this.errorNombreMateria;
    }
    
    //
    public String getErrorNuevaClaseMaterial(){
        return this.errorNuevaClaseMaterial;
    }
    
    public String getErrorEstado(){
        return this.errorEstado;
    }
      
    public boolean validarMultiplesCampos( String campo0, String campo1, String campo2, String campo3, String campo4, String campo5, String campo6,
                                          String campo7, String campo8, String campo9, int longitud ){
      
       String control="";
       boolean estado = false;
       
       if (campo0 == null || campo0.equals("") || campo0.length()>longitud){
           
           control+="S";      
       }
       else {
           control+="N";
       }
       if (campo1 == null || campo1.equals("") || campo1.length()>longitud){
           
           control+="S"; 
       }     
       else {
           control+="N";
       }
       if(campo2 == null || campo2.equals("") || campo2.length()>longitud){
           
           control+="S"; 
       }
       else {
           control+="N";
       }
       if( campo3 == null || campo3.equals("") || campo3.length()>longitud){
           
           control+="S"; 
       }
       else {
           control+="N";
       }
       if( campo4 == null || campo4.equals("") || campo4.length()>longitud){
           
           control+="S";
       }
       else {
           control+="N";
       }
       if( campo5 == null || campo5.equals("") || campo5.length()>longitud){
           
            control+="S";  
       }
       else {
           control+="N";
       }
       if( campo6 == null || campo6.equals("") || campo6.length()>longitud){
            
           control+="S";  
       }
       else {
           control+="N";
       }
       if(campo7 == null || campo7.equals("") || campo7.length()>longitud){
          
           control+="S"; 
       }
       else {
           control+="N";
       }
       if ( campo8 == null || campo8.equals("") || campo8.length()>longitud){
          
           control+="S";          
       }
       else {
           control+="N";
       }
       if( campo9 == null || campo9.equals("") || campo9.length()>longitud){
          
           control+="S"; 
       }
       else{
           
           control+="N"; 
       }
       
      int auxiliar = control.indexOf("N");
      
       if (auxiliar == -1){
           
           estado = false;
       }
       else{
           estado = true;
       }
  
     return estado;
  }
   
    public boolean validarUnAnio(String campoTexto, int numeroCaracteres){
         
        int anio;
        
        if (validarNumero(campoTexto, numeroCaracteres)){
        
            anio = Integer.parseInt(campoTexto);
            
            if (anio>calendario.get(Calendar.YEAR)){
           
                this.errorAnio = "El año es mayor al actual";
                return false;
            }
            
         return true;
        }
        else{
            
           return false;
        }
        
    }

}
