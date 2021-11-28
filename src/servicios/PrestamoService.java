/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import clases.Libro;
import clases.Prestamo;
import clases.Devolucion;
import clases.Usuario;
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
public class PrestamoService implements Serializable {
    
    public static List<Prestamo> listaPrestamos = new ArrayList<Prestamo>();

    public boolean ingresarPrestamo(String isbn, String rut, int cantidadDias) {
        boolean ingresado = false;
        if(!Libro.isbnExiste(isbn)) return ingresado;
        
        Libro libro = LibroService.buscarLibro(isbn);
        if(libro.getCantidadBiblioteca() < 1) return ingresado;
        
        if(!Usuario.rutExiste(rut)) return ingresado;
        
        Usuario usuario = UsuarioService.buscarUsuario(rut);
        if(!usuario.getPrestamo().equals("")) return ingresado;
        
        usuario.setPrestamo(isbn);
        libro.setCantidadDisponible(libro.getCantidadDisponible() - 1);
        Prestamo prestamo = new Prestamo();
        prestamo.setLibro(libro);
        prestamo.setUsuario(usuario);
        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setCantidadDias(cantidadDias);
        
        Devolucion devolucion = new Devolucion(LocalDate.now().plusDays(cantidadDias));
        prestamo.setDevolucion(devolucion);
        
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
        
        if(ingresado) Prestamo.generarTarjeta(prestamo);
        return ingresado;
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
