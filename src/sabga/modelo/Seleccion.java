
package sabga.modelo;

/**
 * @author Elk1n
 */

public class Seleccion {
    
    private final String listaClaseMaterial = "SELECT clase_material FROM tbl_CLASE_MATERIAL";
    private final String claseMaterial = "clase_material";
    private final String listaTipoMaterial = "SELECT tipo_material FROM tbl_TIPO_MATERIAL";
    private final String tipoMaterial = "tipo_material";
    private final String listaMateria = "SELECT nombre_materia FROM tbl_MATERIA";
    private final String materia = "nombre_materia";
    private final String listaEditorial = "SELECT nombre_editorial FROM tbl_EDITORIAL";
    private final String editorial = "nombre_editorial";
    private final String listaTipoLibro = "SELECT tipo_material FROM tbl_TIPO_MATERIAL WHERE tipo_material NOT LIKE('%libro%')";
    private final String tipoLibro =  "tipo_material";
    private final String listaTipoUsuario ="SELECT tipo_usuario FROM tbl_TIPO_USUARIO";
    private final String tipoUsuario = "tipo_usuario";
    private final String listaGrado = "SELECT grado FROM tbl_GRADO";
    private final String grado = "grado";
    private final String listaCurso = "SELECT curso FROM tbl_CURSO";
    private final String curso = "curso";
    private final String listaJornada = "SELECT jornada FROM tbl_JORNADA";
    private final String jornada = "jornada";

    public String getListaJornada() {
        return listaJornada;
    }
    public String getJornada() {
        return jornada;
    }

    public String getListaClaseMaterial() {
        return listaClaseMaterial;
    }    
    public String getListaCurso() {
        return listaCurso;
    }
    public String getCurso() {
        return curso;
    }
    public String getListaGrado() {
        return listaGrado;
    }
    public String getGrado() {
        return grado;
    }
    public String getListaTipoUsuario() {
        return listaTipoUsuario;
    }
    public String getTipoUsuario() {
        return tipoUsuario;
    }
    public String getClaseMaterial() {
        return claseMaterial;
    }    
    public String getListaTipoMaterial(){
        return listaTipoMaterial;
    }    
    public String getTipoMaterial(){
        return tipoMaterial;
    }
    public String getListaMateria(){
        return listaMateria;
    }    
    public String getMateria(){
        return materia;
    }
    public String getListaEditorial(){
        return listaEditorial;
    }
    public String getEditorial(){
        return editorial;
    }
    public String getListaTipoLibro(){
        return listaTipoLibro;
    }
    public String getTipoLibro(){
        return tipoLibro;
    }
}
