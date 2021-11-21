/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author Joseto
 */
public class Devolucion implements Serializable {
    
    private LocalDate fechaDevolucion;
    private int multa;
    
    public Devolucion(LocalDate fechaDevolucion){
        this.fechaDevolucion = fechaDevolucion;
        this.multa = 0;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "multa=" + multa + ", fechaDevolucion=" + fechaDevolucion + '}';
    }
    
}
