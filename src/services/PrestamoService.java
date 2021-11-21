/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clases.Libro;
import clases.Prestamo;
import clases.Devolucion;
import clases.Usuario;
import clases.Estudiante;
import interfaces.IPrestamo;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class PrestamoService implements IPrestamo, Serializable {
    
    public static List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();

    @Override
    public boolean ingresarPrestamo(String isbn, String rut, int cantidadDias) {
        boolean ingresado = false;
        if(!LibroService.isbnExiste(isbn)) return ingresado;
        
        Libro libro = LibroService.buscarLibro(isbn);
        if(libro.getCantidadBiblioteca() < 1) return ingresado;
        
        if(!UsuarioService.rutExiste(rut)) return ingresado;
        
        Usuario usuario = UsuarioService.buscarUsuario(rut);
        if(!usuario.getPrestamo().equals("0")) return ingresado;
        
        usuario.setPrestamo(isbn);
        libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setCantidadDias(cantidadDias);
        
        Devolucion devolucion = new Devolucion(LocalDate.now().plusDays(cantidadDias));
        prestamo.setDevolucion(devolucion);
//        prestamo.setFechaDevolucion(LocalDate.now().plusDays(cantidadDias));
        
        listaPrestamos.add(prestamo);
        try {
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\prestamos.dat");
            File file = new File(path);
            file.delete();
            ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
            fobj.writeObject(listaPrestamos);
            fobj.close();
            ingresado = true;
        } catch (NotSerializableException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
        if(ingresado) generarTarjeta(prestamo);
        return ingresado;
    }
    
    public static void generarTarjeta(Prestamo prestamo) {
        // System.out.println(prestamo.toString());
        String tipoUsuario = prestamo.getUsuario() instanceof Estudiante ? "Estudiante" : "Docente";
        
        System.out.println("    TARJETA DE PRÉSTAMO DE LIBRO");
        System.out.println("          ISBN          : " + prestamo.getLibro().getIsbn());
        System.out.println("          TIPO USUARIO  : " + tipoUsuario);
        System.out.println("          CANTIDAD DÍAS : " + prestamo.getCantidadDias());
        
        System.out.println("FECHA                  CLIENTE                    LIBRO                          FECHA DEVOLUCION");
        System.out.println(".......................................................................................");
        System.out.println(prestamo.getFechaPrestamo() + "          " + prestamo.getUsuario().getNombre() + "         " + prestamo.getLibro().getTitulo() + "              " + prestamo.getDevolucion().getFechaDevolucion());
        System.out.println("......\n");
        System.out.println("                                                     _______________");
        System.out.println("                                                      FIRMA CLIENTE");
    }
    
    public static Prestamo buscarPrestamo(String isbn, String rut){
        Prestamo encontrado = null;
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\prestamos.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Prestamo> prestamos = (List<Prestamo>) obje.readObject();
            obje.close();
            
            for(Prestamo prestamo: prestamos) {
                if(prestamo.getLibro().getIsbn().equals(isbn) && prestamo.getUsuario().getRut().equals(rut)){
                    encontrado = prestamo;
                }
            }
        }catch (FileNotFoundException e) {
            System.out.println("¡ERROR 1!:¡Fichero no existe!");
        }catch (IOException e) {
            System.out.println("¡ERROR 2!:"+e.getMessage());
        }catch (ClassNotFoundException e) {
            System.out.println("¡ERROR 3!:"+e.getMessage());
        }catch (NullPointerException e) {
            System.out.println("¡ERROR 4!:"+e.getMessage());
        }
        
        return encontrado;
    }
    
}
