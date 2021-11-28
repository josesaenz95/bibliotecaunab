/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import clases.Libro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class LibroService {
    
    public static List<Libro> listaLibros = new ArrayList<Libro>();
    
    public boolean ingresarLibro(Libro libro) {
        boolean creado = false;
        if(!Libro.isbnExiste(libro.getIsbn()) && libro.getCantidadBiblioteca() > 0 && (libro.getCantidadDisponible() > 0 && libro.getCantidadDisponible() <= libro.getCantidadBiblioteca())){
            try {
                LibroService.listaLibros.add(libro);
                String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
                File file = new File(path);
                file.delete();
                ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
                fobj.writeObject(LibroService.listaLibros);
                fobj.close();
                creado = true;
            } catch (NotSerializableException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }else{
            System.out.println("El ISBN " + libro.getIsbn()+ " ya existe en el sistema ");
        }
        return creado;
    }

    public boolean eliminarLibro(String isbn) {
        boolean eliminado = false;
        for(int i=0; i < LibroService.listaLibros.size(); ++i){
            Libro libro = LibroService.listaLibros.get(i);
            if(libro.getIsbn().equals(isbn)){
                LibroService.listaLibros.remove(i);
                eliminado = true;
            }
        }
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
        File file = new File(path);
        file.delete();
        try {
            ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
            fobj.writeObject(LibroService.listaLibros);
            fobj.close();
        } catch (NotSerializableException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return eliminado;
    }
    
    public static Libro buscarLibro(String isbn){
        Libro encontrado = null;
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Libro> libros = (List<Libro>) obje.readObject();
            obje.close();
            
            for(Libro libro : libros) {
                if(libro.getIsbn().equals(isbn)){
                    encontrado = libro;
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
