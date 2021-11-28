/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import clases.Libro;
import clases.Usuario;
import clases.Prestamo;

/**
 *
 * @author Joseto
 */
public class DevolucionService {

    public boolean ingresarDevolucion(String isbn, String rut) {
        boolean realizado = false;
        if(!Libro.isbnExiste(isbn)) return realizado;
        if(!Usuario.rutExiste(rut)) return realizado;
        
        Usuario usuario = UsuarioService.buscarUsuario(rut);
        if(!usuario.getPrestamo().equals(isbn)) return realizado;
        
        usuario.setPrestamo("");
        Libro libro = LibroService.buscarLibro(isbn);
        libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
        
        Prestamo prestamo = PrestamoService.buscarPrestamo(isbn, rut);
        prestamo.getDevolucion().setMulta(prestamo.getDevolucion().calcularMulta(prestamo));
        realizado = true;
        System.out.println(prestamo.getDevolucion().toString());
        
        return realizado;
    }
    
}
