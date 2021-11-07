/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import interfaces.ILibro;
import clases.Libro;
import java.io.EOFException;
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
public class LibroService implements ILibro {

    @Override
    public boolean crearLibro(Libro libro) {
        boolean creado = false;
        if(!isbnExiste(libro.getIsbn())){
            try {
                String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros\\" + libro.getIsbn() + ".dat");
                File file = new File(path);
                ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
                fobj.writeObject(libro);
                fobj.flush();
                fobj.close();
                creado = true;
            } catch (NotSerializableException ex) {
                System.out.println(ex.toString());
                creado = false;
            } catch (IOException ex) {
                System.out.println(ex.toString());
                creado = false;
            }
        }
        
        return creado;
    }

    @Override
    public boolean eliminarLibro(String isbn) {
        boolean eliminado = false;
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros\\" + isbn + ".dat");
        File file = new File(path);
        if(file.exists()){
            file.delete();
            eliminado = true;
        }
        
        return eliminado;
    }
    
    public static void mostrar(String isbn) throws FileNotFoundException, IOException, ClassNotFoundException, EOFException {
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros\\" + isbn + ".dat");
        ObjectInputStream obje = new ObjectInputStream(new FileInputStream(path));
        Libro libro = (Libro) obje.readObject();
        System.out.println(libro);
        obje.close();
    }
    
    @Override
    public boolean isbnExiste(String isbn) {
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros\\" + isbn + ".dat");
        File file = new File(path);
        return file.exists();
    }
    
}
