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
    
    public Docente(String nombre, String rut, char genero){
        super(nombre, rut, genero);
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    @Override
    public String toString() {
        return "Docente{" + super.toString() + "profesion=" + profesion + '}';
    }
    
    
    
}
