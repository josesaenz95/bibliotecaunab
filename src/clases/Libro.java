/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author Joseto
 */
public class Libro {
    
    private String isbn;
    private String titulo;
    private String autor;
    private int cantidadBiblioteca;
    private int cantidadDisponible;
    private String imagen;
    
    public Libro(){}

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
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
        this.cantidadBiblioteca = cantidadBiblioteca;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
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
    
    
    
}