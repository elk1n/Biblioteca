
package sabga.modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;

/**
 * @author Elk1n
 */

public class ValidarMaterial extends Validacion{

   private String errorNombreAutor, errorApellidosAutor, errorEditorial, errorNombreMateria, errorCodigoClasificacion, errorClaseMaterial, 
                  errorTitulo, errorAnioPublicacion, errorPublicacion, errorNumeroPaginas, errorEjemplares, errorAutor, errorMateria,
                  errorTipoMaterial,
               
           titulo, anioPublicacion, publicacion, numeroPaginas, ejemplares, 
           editorial, autor, materia, habilitado, inhabilitado, mantenimiento, nombreAutor, apellidosAutor, nombreEditorial,
           nombreMateria, nuevoTipoMaterial, nuevaClaseMaterial, 
                     
           errorEstado, 
           errorNombreEditorial,  errorNuevoTipoMaterial,
            errorCantidadEjemplares, errorAnio;
        
   private Calendar calendario;
   private Object tipoMaterial, claseMaterial;
   
   public ValidarMaterial(){
       
       this.calendario = Calendar.getInstance();
       this.calendario = new GregorianCalendar();       
   }
   
   //       ---         CONSTRUCTOR PARA ACTUALIZAR LA INFORMACIÓN DE LOS LIBROS            ---
   
   public ValidarMaterial(String codigoClasificacion, String titulo, String anioPublicacion, String publicacion, String numeroPaginas,
                     String editorial, String ejemplares, String habilitado, String inhabilitado, String mantenimiento,
                     String autor0, String autor1, String autor2, String autor3, String autor4, String autor5, String autor6,
                     String autor7, String autor8, String autor9, String materia0, String materia1, String materia2, String materia3,
                     String materia4, String materia5, String materia6, String materia7, String materia8, String materia9 ){
       
//       this.codigoClasificacion = codigoClasificacion;
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
       
//       this.codigoClasificacion = codigoClasificacion;
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
   public void validarClaseMaterial(String material){
   
       if(!validarCampoTexto(material, 45)){
           this.errorClaseMaterial = getMensajeError();      
       }
   }
  
   // VALIDAR NUEVO TIPO MATERIAL
   public void validarTipoMaterial(String material){
       
       if(!validarCampoTexto(material, 90)){
           this.errorTipoMaterial = getMensajeError();      
       }
   }
   
  //  VALIDAR NUEVO LIBRO
    public void validarNuevoLibro(Object claseMaterial, String codigo, String titulo, String anioPublicacion, String publicacion,
                                  String paginas, String ejemplares, String editorial, ObservableList autores, ObservableList materias) {

        if (claseMaterial == null) {
            this.errorClaseMaterial = "Debe seleccionar una opción";
        }

        if (!validarCampoTexto(codigo, 45)) {
            this.errorCodigoClasificacion = getMensajeError();
        }

        if (!validarCampoTexto(titulo, 255)) {
            this.errorTitulo = getMensajeError();
        }

        if (!validarAnio(anioPublicacion, 4)) {
            this.errorAnioPublicacion = getMensajeError();
        }

        if (!validarCampoTextoNull(publicacion, 255)) {
            this.errorPublicacion = getMensajeError();
        }

        if (!validarNumero(paginas, 10)) {
            this.errorNumeroPaginas = getMensajeError();
        }

        if (!validarNumero(ejemplares, 10)) {
            this.errorEjemplares = getMensajeError();
        }

        if (!validarCampoTexto(editorial, 90)) {
            this.errorEditorial = getMensajeError();
        }

        if (autores.isEmpty()) {
            this.errorAutor = "Debe seleccionar al menos un autor";
        }

        if (materias.isEmpty()) {
            this.errorMateria = "Debe seleccionar al menos una materia";
        }


    }

   // VALIDAR NUEVO MATERIAL (LOS CDS, LOS FOLLETOS Y ESO...)
   public void validarMaterialOM(Object tipoMaterial, Object claseMaterial, String codigo, String titulo, String copias, ObservableList materias){
                 
      if (tipoMaterial == null){          
          this.errorTipoMaterial = "Debe seleccionar una opción";
      }
       
      if (claseMaterial == null){
          this.errorClaseMaterial = "Debe seleccionar una opción";
      }
     
      if (!validarCampoTexto(codigo, 45)){          
          this.errorCodigoClasificacion = getMensajeError();
      }

      if(!validarCampoTexto(titulo, 255)){          
          this.errorTitulo = getMensajeError();
      }
  
      if (!validarNumero(copias, 10)){          
          this.errorEjemplares = getMensajeError();         
      }
      
      if(materias.isEmpty()){
          this.errorMateria = "Debe seleccionar al menos una materia";        
      }
      
  }
   
 
   
   
    public void validarActualizacionOM(){
        /*
        if(validarCampoTexto(this.codigoClasificacion, 45)==false ){
          
          this.errorCodigoClasificacion = getMensajeError();
        }
      */
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
      /*
      if (validarCampoTexto(this.codigoClasificacion, 45) == false ){          
          this.errorCodigoClasificacion = getMensajeError();
      }
      */
      if (validarCampoTexto(this.titulo, 300) == false){          
          this.errorTitulo = getMensajeError();
      }
      /*
      if (validarUnAnio(this.anioPublicacion, 4) == false){
          
           this.errorAnioPublicacion = getMensajeError();
      }
      */
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
     
    
    // 
    public String getErrorCodigoClasificacion(){      
      return this.errorCodigoClasificacion;
    }
    //
    public String getErrorTitulo(){      
      return this.errorTitulo;
    }
   //
    public String getErrorAnioPublicacion(){      
      return this.errorAnioPublicacion;
    }
    //
    public String getErrorPublicacion(){      
      return this.errorPublicacion;
    }
    //
    public String getErrorNumeroPaginas(){      
      return this.errorNumeroPaginas;
    }
    //
    public String getErrorNumeroEjemplares(){     
      return this.errorEjemplares;
    }
    //
    public String getErrorEditorial(){      
      return this.errorEditorial;
    }
   // 
    public String getErrorAutor(){      
      return this.errorAutor;
    }
   //
    public String getErrorMateria(){      
      return this.errorMateria;
    }
   //
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
    public String getErrorEstado(){
        return this.errorEstado;
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
      
}
