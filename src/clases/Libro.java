/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class Libro implements Serializable {
    
    private String isbn;
    private String titulo;
    private String autor;
    private int cantidadBiblioteca;
    private int cantidadDisponible;
    private String imagen;
    
    public Libro(String isbn, String titulo, String autor, int cantidadBiblioteca, String imagen){
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.cantidadBiblioteca = cantidadBiblioteca;
        this.cantidadDisponible = this.cantidadBiblioteca;
        this.imagen = imagen;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        if(!isbnExiste(isbn)){
            this.isbn = isbn;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getCantidadBiblioteca() {
        return cantidadBiblioteca;
    }

    public void setCantidadBiblioteca(int cantidadBiblioteca) {
        if(validarCantidadBiblioteca(cantidadBiblioteca)){        
            this.cantidadBiblioteca = cantidadBiblioteca;
        }
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        if(validarCantidadDisponbile(this.cantidadBiblioteca, cantidadDisponible)){
            this.cantidadDisponible = cantidadDisponible;
        }
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", titulo=" + titulo + ", autor=" + autor + ", cantidadBiblioteca=" + cantidadBiblioteca + ", cantidadDisponible=" + cantidadDisponible + ", imagen=" + imagen + '}';
    }
    
    public static boolean isbnExiste(String isbn) {
        boolean existe = false;
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Libro> libros = (List<Libro>) obje.readObject();
            obje.close();
            
            for(Libro libro : libros) {
                if(libro.getIsbn().equals(isbn)){
                    existe = true;
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
        
        return existe;
    }
    
    public static boolean validarCantidadBiblioteca(int cantidadBiblioteca){
        return cantidadBiblioteca > 0;
    }
    
    public static boolean validarCantidadDisponbile(int cantidadBiblioteca, int cantidadDisponible){
        return cantidadDisponible <= cantidadBiblioteca;
    }
    
}
