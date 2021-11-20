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
public class Usuario implements Serializable {
    
    private String nombre;
    private String rut;
    private char genero;
    private String prestamo;
    
    public Usuario(String nombre, String rut, char genero){
        this.nombre = nombre;
        this.rut = rut;
        this.genero = genero;
        this.prestamo = "0";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", rut=" + rut + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }
    
    
    
}
