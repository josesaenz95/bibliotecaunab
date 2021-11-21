/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Prestamo;

/**
 *
 * @author Joseto
 */
public interface IPrestamo {
    
    public boolean ingresarPrestamo(String isbn, String rut, int cantidadDias);
    
}
