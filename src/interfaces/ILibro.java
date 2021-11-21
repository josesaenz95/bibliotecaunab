/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Libro;

/**
 *
 * @author Joseto
 */
public interface ILibro {

    public boolean ingresarLibro(Libro libro);
    public boolean eliminarLibro(String isbn);
    
}
