/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;

/**
 *
 * @author Joseto
 */
public class Estudiante extends Usuario implements Serializable{

    private String carrera;
    
    public Estudiante(String nombre, String rut, char genero, String carrera){
        super(nombre, rut, genero);
        this.carrera = carrera;
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
