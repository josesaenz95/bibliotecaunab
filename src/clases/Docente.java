/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Joseto
 */
public class Docente extends Usuario {
    
    private String profesion;
    private String grado;
    
    public Docente(String nombre, String rut, char genero, String profesion, String grado){
        super(nombre, rut, genero);
        this.profesion = profesion;
        this.grado = grado;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    @Override
    public String toString() {
        return "Docente{" + super.toString() + " profesion=" + profesion + ", grado=" + grado + '}';
    }
    
    
    
}
