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
public class Estudiante extends Usuario {
    
    private String carrera;
    
    public Estudiante(String nombre, String rut, char genero){
        super(nombre, rut, genero);
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Estudiante{" + super.toString() + "carrera=" + carrera + '}';
    }
    
    
    
}
