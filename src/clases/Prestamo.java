/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Joseto
 */
public class Prestamo implements Serializable {
    
    private Libro libro;
    private Usuario usuario;
    private int cantidadDias;
    private LocalDate fechaPrestamo;
    private Devolucion devolucion;
    
    public Prestamo(){}
//   public Prestamo(int id, Libro libro, Usuario usuario, int cantidadDias, Date fechaDevolucion){
//       this.id = id;
//       this.libro = libro;
//       this.usuario = usuario;
//       this.cantidadDias = cantidadDias;
//       this.fechaDevolucion = fechaDevolucion;
//   }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public LocalDate getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(LocalDate fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Devolucion getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(Devolucion devolucion) {
        this.devolucion = devolucion;
    }

//    public LocalDate getFechaDevolucion() {
//        return fechaDevolucion;
//    }
//
//    public void setFechaDevolucion(LocalDate fechaDevolucion) {
//        this.fechaDevolucion = fechaDevolucion;
//    }

    @Override
    public String toString() {
        return "Prestamo{" + "libro=" + libro.toString() + ",\n usuario=" + usuario.toString() + ",\n cantidadDias=" + cantidadDias + ", fechaPrestamo=" + fechaPrestamo + ",\n devolucion=" + devolucion.toString() + '}';
    }

    public static void generarTarjeta(Prestamo prestamo) {
        String tipoUsuario = prestamo.getUsuario() instanceof Estudiante ? "Estudiante" : "Docente";
        
        System.out.println("    TARJETA DE PR??STAMO DE LIBRO");
        System.out.println("          ISBN          : " + prestamo.getLibro().getIsbn());
        System.out.println("          TIPO USUARIO  : " + tipoUsuario);
        System.out.println("          CANTIDAD D??AS : " + prestamo.getCantidadDias());
        
        System.out.println("FECHA                  CLIENTE                    LIBRO                          FECHA DEVOLUCION");
        System.out.println(".......................................................................................");
        System.out.println(prestamo.getFechaPrestamo() + "          " + prestamo.getUsuario().getNombre() + "         " + prestamo.getLibro().getTitulo() + "              " + prestamo.getDevolucion().getFechaDevolucion());
        System.out.println("......\n");
        System.out.println("                                                     _______________");
        System.out.println("                                                      FIRMA CLIENTE");
    }
    
}
