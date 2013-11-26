
package sabga.modelo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javafx.collections.ObservableList;

/**
 * @author Elk1n
 */

public class ValidarMaterial extends Validacion{

    private String errorNombreAutor, errorApellidosAutor, errorEditorial, errorNombreMateria, errorCodigoClasificacion, 
                   errorClaseMaterial, errorTitulo, errorAnioPublicacion, errorPublicacion, errorNumeroPaginas, errorEjemplares,
                   errorAutor, errorMateria, errorTipoMaterial, errorFecha, errorDocumento;
    Calendar calendario;
                        
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
    //   METODO FINAL PARA VALIDAR UNA NUEVA EDITORIAL   
    public void validarNuevaEditorial(String editorial){
   
       if(!validarCampoTexto(editorial, 90))
           this.errorEditorial = getMensajeError();
    }   
    //   VALIDAR NUEVA CLASE DE MATERIAL
    public void validarClaseMaterial(String material){
   
       if(!validarCampoTexto(material, 45)){
           this.errorClaseMaterial = getMensajeError();      
       }
    }  
    //   VALIDAR NUEVO TIPO MATERIAL
    public void validarTipoMaterial(String material){
       
       if(!validarCampoTexto(material, 90)){
           this.errorTipoMaterial = getMensajeError();      
       }
    }  
    //   VALIDAR NUEVO LIBRO
    public void validarNuevoLibro(Object claseMaterial, String codigo, String titulo, String anioPublicacion, 
                                  String publicacion, String paginas, String ejemplares, String editorial, 
                                  ObservableList autores, ObservableList materias) {

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
    //   VALIDAR NUEVO MATERIAL (LOS CDS, LOS FOLLETOS Y ESO...)
    public void validarMaterialOM(Object tipoMaterial, Object claseMaterial, String codigo, 
                                  String titulo, String copias, ObservableList materias){
                 
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
  
    public void validarEdicionLibro(String codigo, String titulo, String anioPublicacion, String publicacion, 
                                    String paginas, String editorial, ObservableList editoriales) {

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
        if (!validarDatoArryList(editorial, editoriales)){
            this.errorEditorial = getMensajeError();
        }               
    }
            
    public void validarEdicionOM(String codigo, String titulo) {
        if (!validarCampoTexto(codigo, 45)) {
            this.errorCodigoClasificacion = getMensajeError();
        }
        if (!validarCampoTexto(titulo, 255)) {
            this.errorTitulo = getMensajeError();
        }
    }
    
    public void validarPrestamo(ObservableList lista, Date fecha, String id){
        
        this.calendario = Calendar.getInstance();
        this.calendario = new GregorianCalendar();
        
        if(lista.isEmpty()){
            this.errorEjemplares = "Debe seleccioanr al menos un ejemplar.";
        }
        else{
            this.errorEjemplares = "";
        }
        if( fecha == null || fecha.before(calendario.getTime()) || fecha.equals(calendario.getTime())){
            this.errorFecha = "La fecha de devolución debe ser posterior a la fecha del prestamo.";
        }
        else{
            this.errorFecha = "";
        }
        if(!validarCampoTexto(id, 15)){
            this.errorDocumento = getMensajeError();
        }
    
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

    public String getErrorEjemplares() {
        return this.errorEjemplares;
    }

    public String getErrorFecha() {
        return this.errorFecha;
    }

    public String getErrorDocumento() {
        return this.errorDocumento;
    }
     
    
}
