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
public class Devolucion {
    
    private Prestamo prestamo;
    private int multa;
    
    public Devolucion(){}

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    public int getMulta() {
        return multa;
    }

    public void setMulta(int multa) {
        this.multa = multa;
    }

    @Override
    public String toString() {
        return "Devolucion{" + "prestamo=" + prestamo + ", multa=" + multa + '}';
    }
    
    
    
}
