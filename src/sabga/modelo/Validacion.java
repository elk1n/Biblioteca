
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
           errorCodigoMaterialOM, errorNumeroClasificacionOM, errorTituloOM, errorMateriasOM, errorTipoMaterial, errorClaseMaterialOM, errorClaseMaterial,
           errorCodigoClasificacionAC, errorTituloAC, errorAnioPublicacionAC, errorPublicacionAC, errorNumeroPaginasAC, errorEditorialAC, errorEstadoAC,
           errorAutorAC, errorMateriaAC,
           codigoClasificacionAC, tituloAC, anioPublicacionAC, publicacionAC, numeroPaginasAC, editorialAC, ejemplaresDisponiblesAC, habilitadoAC,
           deshabilitadoAC, mantenimientoAC, autor1AC, autor2AC, autor3AC, autor4AC, autor5AC, autor6AC, autor7AC, autor8AC, autor9AC, autor10AC,
           materia1AC, materia2AC, materia3AC, materia4AC, materia5AC, materia6AC, materia7AC, materia8AC, materia9AC, materia10AC,
           
           nombreAutor, apellidosAutor, nombreEditorial, nombreMateria,
           errorNombreAutor, errorApellidosAutor, errorNombreEditorial, errorNombreMateria;

        
   private Calendar calendario;
   private int anioPubli, numeroDePaginas, numeroDeEjemplares, anioAC, paginasAC, numeroEjemplaresAC, habilitado, deshabilitado, mantenimiento;
   private boolean fechaPublicacion = false, paginas = false, numeroEjemplares = false, fechaPublicacionAC = false, paginasbAC = false,
                    numeroDeEjemplaresAC = false, estadoAC = false;
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
   
   public Validacion(String codigoClasificacionAC, String tituloAC, String anioPublicacionAC, String publicacionAC, String numeroPaginasAC,
                     String editorialAC, String ejemplaresDisponiblesAC, String habilitadoAC, String deshabilitadoAC, String mantenimientoAC,
                     String autor1AC, String autor2AC, String autor3AC, String autor4AC, String autor5AC, String autor6AC, String autor7AC,
                     String autor8AC, String autor9AC, String autor10AC, String materia1AC, String materia2AC, String materia3AC, String materia4AC,
                     String materia5AC,String materia6AC, String materia7AC, String materia8AC, String materia9AC, String materia10AC ){
       
       this.codigoClasificacionAC = codigoClasificacionAC;
       this.tituloAC = tituloAC;
       this.anioPublicacionAC = anioPublicacionAC;
       this.publicacionAC = publicacionAC;
       this.numeroPaginasAC = numeroPaginasAC;
       this.editorialAC = editorialAC;
       this.ejemplaresDisponiblesAC = ejemplaresDisponiblesAC;
       this.habilitadoAC = habilitadoAC;
       this.deshabilitadoAC = deshabilitadoAC;
       this.mantenimientoAC = mantenimientoAC;
       
       this.autor1AC = autor1AC;
       this.autor2AC = autor2AC;
       this.autor3AC = autor3AC;
       this.autor4AC = autor4AC;
       this.autor5AC = autor5AC;
       this.autor6AC = autor6AC;
       this.autor7AC = autor7AC;
       this.autor8AC = autor8AC;
       this.autor9AC = autor9AC;
       this.autor10AC = autor10AC;
       
       this.materia1AC = materia1AC;
       this.materia2AC = materia2AC;
       this.materia3AC = materia3AC;
       this.materia4AC = materia4AC;
       this.materia5AC = materia5AC;
       this.materia6AC = materia6AC;
       this.materia7AC = materia7AC;
       this.materia8AC = materia8AC;
       this.materia9AC = materia9AC;
       this.materia10AC = materia10AC;
       
       this.calendario = Calendar.getInstance();
       this.calendario = new GregorianCalendar();
                  
   }
   
   public Validacion(String nombreAutor, String apellidosAutor, String nombreEditorial, String nombreMateria){
       
       this.nombreAutor = nombreAutor;
       this.apellidosAutor = apellidosAutor;
       this.nombreEditorial = nombreEditorial;
       this.nombreMateria = nombreMateria;
       
   }
  
    public void validarActualizacionMaterial(){
      
      if(this.codigoClasificacionAC == null || this.codigoClasificacionAC.equals("") || this.codigoClasificacionAC.length()>45 ){
          
          this.errorCodigoClasificacionAC = "Debe rellenar este campo";
      }
      
      if (this.tituloAC == null || this.tituloAC.equals("") || this.tituloAC.length()>300){
          
          this.errorTituloAC = "Debe rellenar este campo";
      }
      
      if (validarAnioAC() == false){
          
           this.errorAnioPublicacionAC = "El formato de año es incorrecto";
      }
      
      if(this.publicacionAC.length()>64){
          
          this.errorPublicacionAC = "Máximo 64 Caracteres";
      }
      
      if(validarNumeroPaginasAC() == false){
          
           this.errorNumeroPaginasAC = "Debe rellenar este campo";
      }
      
      if (this.editorialAC.length()>32 || this.editorialAC == null || this.editorialAC.equals("") ){
        
        this.errorEditorialAC = "Debe rellenar este campo";              
      }
      
      if (validarEstadoEjemplaresAC() == false){
          
          this.errorEstadoAC = "Debe rellenar estos campos";
      }
      
      if (validarMultiplesCampos(this.autor1AC, this.autor2AC, this.autor3AC, this.autor4AC, this.autor5AC, this.autor6AC,
                                 this.autor7AC, this.autor8AC, this.autor9AC, this.autor10AC, 90)==false ){
      
          this.errorAutorAC = "Debe seleccionar al menos un autor";
      }
      
      
      if(validarMultiplesCampos(this.materia1AC, this.materia2AC, this.materia3AC, this.materia4AC, this.materia5AC, this.materia6AC,
                                this.materia7AC, this.materia8AC, this.materia9AC, this.materia10AC, 45)==false){
      
        this.errorMateriaAC = "Debe seleccionar al menos una metaria";
      
      }
      
   
  }
  
    public void validarMateriaAC(){
      
      if (this.nombreMateria == null || this.nombreMateria.equals("") || this.nombreMateria.length()>45 || this.nombreMateria.isEmpty()){
          
          this.errorNombreMateria = "Debe rellenar este campo";
         
      }
        
  }
    public void validarAutorAC(){
        
         if (this.nombreAutor == null || this.nombreAutor.equals("") || this.nombreAutor.length()>45 || this.nombreAutor.isEmpty()){
          
          this.errorNombreAutor = "Debe rellenar este campo";
      }
      
      if(this.apellidosAutor == null || this.apellidosAutor.equals("") || this.apellidosAutor.length()>45 || this.apellidosAutor.isEmpty()){
          
          this.errorApellidosAutor = "Debe rellenar este campo";
      }
        
    }
    
    public void validarEditorialAC(){
        
         if(this.nombreEditorial == null || this.nombreEditorial.equals("") || this.nombreEditorial.length()>45 || this.nombreEditorial.isEmpty()){
          
          this.errorNombreEditorial = "Debe rellenar este campo";
      }
    
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
  
    public boolean validarAnioAC(){
      
     if (this.anioPublicacionAC.length()==4){
         
       try{
           
        this.anioAC = Integer.parseInt(this.anioPublicacionAC);
        
       if (this.anioAC>calendario.get(Calendar.YEAR)){
           
           this.fechaPublicacionAC = false;
           this.errorAnioPublicacionAC = "Al parecer el libro aún no se publicado";
        }
       
       }
       catch(NumberFormatException e){
           
         this.errorAnioPublicacionAC = "Debe ser un número";           
       }      
        this.fechaPublicacionAC = true;
       }
       else if (this.anioPublicacionAC == null || this.anioPublicacionAC.equals("")){
                      
       this.fechaPublicacionAC= true;
       }
       
       else if (anioPublicacionAC.length()>4 || anioPublicacionAC.length()<4){
         
            this.errorAnioPublicacionAC = "Solo se permiten 4 digitos";     
            fechaPublicacionAC=false;  
     }  
  
   return this.fechaPublicacionAC;
  }
   
    public boolean validarAnio(){
      
     if (this.anioPublicacion.length()==4){
         
       try{
           
       this.anioPubli = Integer.parseInt(this.anioPublicacion);
        
       if (this.anioPubli>calendario.get(Calendar.YEAR)){
          
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
  
    public boolean validarNumeroPaginasAC(){
     
       if (this.numeroPaginasAC!=null && !this.numeroPaginasAC.equals("") && this.numeroPaginasAC.length()>=0){
                         
            try{
                this.paginasAC = Integer.parseInt(this.numeroPaginasAC);
                
                if (this.paginasAC<0){
                    this.paginasbAC = false;
                    this.errorNumeroPaginasAC = "Debe ser un número positivo";
                 }                
                 this.paginasbAC=true;
                }
            catch(NumberFormatException e){
                
                this.errorNumeroPaginasAC = "Debe ser un número";
            }            
         this.paginasbAC = true;
       }
       
     else {               
       this.paginasbAC=false;       
       }    
   return this.paginasbAC;
  }
  
    public boolean validarEstadoEjemplaresAC(){
      
       if ( this.ejemplaresDisponiblesAC!=null && !this.ejemplaresDisponiblesAC.equals("") && this.ejemplaresDisponiblesAC.length()>=0 
               && this.habilitadoAC!=null && !this.habilitadoAC.equals("") && this.habilitadoAC.length()>=0 
               && this.deshabilitadoAC!=null && !this.deshabilitadoAC.equals("") && this.deshabilitadoAC.length()>=0 
               && this.mantenimientoAC!=null && !this.mantenimientoAC.equals("") && this.mantenimientoAC.length()>=0 ){
                         
            try{
                
                this.numeroEjemplaresAC = Integer.parseInt(this.ejemplaresDisponiblesAC);
                this.habilitado = Integer.parseInt(this.habilitadoAC);
                this.deshabilitado = Integer.parseInt(this.deshabilitadoAC);
                this.mantenimiento = Integer.parseInt(this.mantenimientoAC);
                
                if (this.numeroEjemplaresAC<0 || this.habilitado<0 || this.deshabilitado<0 || this.mantenimiento<0 ){
                    
                    this.estadoAC = false;
                    this.errorEstadoAC = "Debe ser un número positivo";
                 
                }
                
                if (this.habilitado+this.deshabilitado+this.mantenimiento<this.numeroEjemplaresAC || 
                   this.habilitado+this.deshabilitado+this.mantenimiento>this.numeroEjemplaresAC){
             
                    this.estadoAC = false;
                    this.errorEstadoAC = "El número de ejemplares no coinciden";
             
                    
                }
                
                 this.estadoAC = true;
                }
            
                catch(NumberFormatException e){
                
                this.errorEstadoAC = "Debe ser un número";
                
                }            
                this.estadoAC = true;      
       }
       
     else {           
       this.estadoAC=false;       
       
       }       
      return this.estadoAC;
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
  
    public String getErrorCodigoClasificacionAC() {
        return this.errorCodigoClasificacionAC;
    }

    public String getErrorTituloAC() {
        return this.errorTituloAC;
    }

    public String getErrorAnioPublicacionAC() {
        return this.errorAnioPublicacionAC;
    }

    public String getErrorPublicacionAC() {
        return this.errorPublicacionAC;
    }

    public String getErrorNumeroPaginasAC() {
        return this.errorNumeroPaginasAC;
    }

    public String getErrorEditorialAC() {
        return this.errorEditorialAC;
    }

    public String getErrorEstadoAC() {
        return this.errorEstadoAC;
    }

    public String getErrorAutorAC() {
        return this.errorAutorAC;
    }

    public String getErrorMateriaAC() {      
        return this.errorMateriaAC;
    }
    
    public String getErrorNombreAutor() {
        return errorNombreAutor;
    }

    public String getErrorApellidosAutor() {
        return errorApellidosAutor;
    }

    public String getErrorNombreEditorial() {
        return errorNombreEditorial;
    }

    public String getErrorNombreMateria() {
        return errorNombreMateria;
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
   
  
  }
