/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sabga.modelo;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Nanny
 */
public class EstadoUsuarios {
    public SimpleStringProperty cambio = new SimpleStringProperty();
    public SimpleStringProperty documento1 = new SimpleStringProperty();
    public SimpleStringProperty admin = new SimpleStringProperty();
    public SimpleStringProperty tipo = new SimpleStringProperty();    
    public SimpleStringProperty titulo = new SimpleStringProperty();
    public SimpleStringProperty materia = new SimpleStringProperty();
    public SimpleStringProperty autor = new SimpleStringProperty();
    public SimpleStringProperty fechaDev = new SimpleStringProperty();
    public SimpleStringProperty estadoPrestamo = new SimpleStringProperty();
    public SimpleStringProperty multa = new SimpleStringProperty();
    
    public SimpleStringProperty nom = new SimpleStringProperty();
    public SimpleStringProperty tip = new SimpleStringProperty();
    public SimpleStringProperty ti = new SimpleStringProperty();
    public SimpleStringProperty ma = new SimpleStringProperty();
    public SimpleStringProperty au = new SimpleStringProperty();
    public SimpleStringProperty fe = new SimpleStringProperty();
    public SimpleStringProperty es = new SimpleStringProperty();

    public String getNom() {
        return nom.get();
    }

    public String getTip() {
        return tip.get();
    }

    public String getTi() {
        return ti.get();
    }

    public String getMa() {
        return ma.get();
    }

    public String getAu() {
        return au.get();
    }

    public String getFe() {
        return fe.get();
    }

    public String getEs() {
        return es.get();
    }
     
    
    public String getCambio() {
        return cambio.get();
    }

    public String getDocumento1() {
        return documento1.get();
    }

    public String getAdmin() {
        return admin.get();
    }

    public String getTipo() {
        return tipo.get();
    }

    public String getTitulo() {
        return titulo.get();
    }

    public String getMateria() {
        return materia.get();
    }

    public String getAutor() {
        return autor.get();
    }

    public String getFechaDev() {
        return fechaDev.get();
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo.get();
    }

    public String getMulta() {
        return multa.get();
    }

    
   
}
