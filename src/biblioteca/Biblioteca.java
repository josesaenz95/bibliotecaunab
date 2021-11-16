/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import clases.Docente;
import clases.Estudiante;
import clases.Libro;
import java.util.Scanner;
import services.DocenteService;
import services.EstudianteService;
import services.LibroService;
import services.UtilService;

/**
 *
 * @author Joseto
 */
public class Biblioteca {
    
    private static Scanner sc = new Scanner(System.in);
    // probando git
    public static boolean ingresarLibro(){
        boolean ingresado = false;
        String isbn = "1234-B",
               titulo = "Java",
               autor = "Joyanes",
               imagen = "path1";
        int cantidadBiblioteca = 10,
            cantidadDisponible = 9;
        Libro libro = new Libro();
        LibroService ls = new LibroService();
        
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autor);
        libro.setImagen(imagen);
        libro.setCantidadBiblioteca(cantidadBiblioteca);
        libro.setCantidadDisponible(cantidadDisponible);
        ingresado = ls.crearLibro(libro);
        
        return ingresado;
    }
    
    public static boolean eliminarLibro(){
        String isbn = "1234-B";
        LibroService ls = new LibroService();
        return ls.eliminarLibro(isbn);
    }
    
    public static boolean ingresarEstudiante(){
        boolean ingresado = false;
        String nombre = "Juan Perez 1",
               rut = "17160999-2",
               carrera = "Informática";
        char genero = 'M';
        if(UtilService.validarRut(rut)){
            EstudianteService es = new EstudianteService();
            Estudiante estudiante = new Estudiante(nombre, rut, genero, carrera);
            ingresado = es.crearUsuario(estudiante);
        }else{
            ingresado = false;
        }
        return ingresado;
    }
    
    public static boolean eliminarEstudiante(){
        String rut = "17160999-2";
        EstudianteService es = new EstudianteService();
        return es.eliminarUsuario(rut);
    }
    
    public static boolean ingresarDocente(){
        boolean ingresado = false;
        String nombre = "Juan Perez 2",
               rut = "11844792-1",
               profesion = "Informática",
               grado = "Magister";
        char genero = 'M';
        if(UtilService.validarRut(rut)){
            DocenteService ds = new DocenteService();
            Docente docente = new Docente(nombre, rut, genero, profesion, grado);
            ingresado = ds.crearUsuario(docente);
        }else{
            ingresado = false;
        }
        return ingresado;
    }
    
    public static boolean eliminarDocente(){
        String rut = "11844792-1";
        DocenteService ds = new DocenteService();
        return ds.eliminarUsuario(rut);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO: Menu
        if(ingresarDocente()){
                System.out.println("Docente ingresado exitosamente");
        }else{
            System.out.println("Hubo un error al ingresar al docente");
        }

//        if(eliminarDocente()){
//            System.out.println("Docente eliminado exitosamente");
//        }else{
//            System.out.println("Hubo un error al eliminar al docente");
//        }

        if(ingresarEstudiante()){
            System.out.println("Estudiante ingresado exitosamente");
        }else{
            System.out.println("Hubo un error al ingresar al Estudiante");
        }

//        if(eliminarEstudiante()){
//            System.out.println("Estudiante eliminado exitosamente");
//        }else{
//            System.out.println("Hubo un error al eliminado al Estudiante");
//        }

        if(ingresarLibro()){
            System.out.println("Libro ingresado exitosamente");
        }else{
            System.out.println("Hubo un error al ingresar el Libro");
        }

//        if(eliminarLibro()){
//            System.out.println("Libro eliminado exitosamente");
//        }else{
//            System.out.println("Hubo un error al eliminar el Libro");
//        }
    }
    
}
