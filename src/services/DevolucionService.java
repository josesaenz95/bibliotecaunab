/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clases.Libro;
import clases.Usuario;
import clases.Prestamo;
import interfaces.IDevolucion;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Joseto
 */
public class DevolucionService implements IDevolucion {

    @Override
    public boolean ingresarDevolucion(String isbn, String rut) {
        boolean realizado = false;
        if(!LibroService.isbnExiste(isbn)) return realizado;
        if(!UsuarioService.rutExiste(rut)) return realizado;
        
        Usuario usuario = UsuarioService.buscarUsuario(rut);
        if(!usuario.getPrestamo().equals(isbn)) return realizado;
        
        usuario.setPrestamo("0");
        Libro libro = LibroService.buscarLibro(isbn);
        libro.setCantidadDisponible(libro.getCantidadDisponible() + 1);
        
        Prestamo prestamo = PrestamoService.buscarPrestamo(isbn, rut);
        prestamo.getDevolucion().setMulta(calcularMulta(prestamo));
        realizado = true;
        System.out.println(prestamo.getDevolucion().toString());
        
        return realizado;
    }

    @Override
    public int calcularMulta(Prestamo prestamo) {
        int multa = 0;
        if(LocalDate.now().isAfter(prestamo.getDevolucion().getFechaDevolucion())){
            LocalDate fechaActual = LocalDate.now();
            multa = ((int) ChronoUnit.DAYS.between(fechaActual, prestamo.getDevolucion().getFechaDevolucion())) * 1000;
        }
        return multa;
    }
    
}
