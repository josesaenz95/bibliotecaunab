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
    
    public static boolean menu(){
        boolean continuar = true;
        String respuesta;
        System.out.println("       MENU       ");
        System.out.println("1. Ingresar Docente:");
        System.out.println("2. Eliminar Docente:");
        System.out.println("3. Ingresar Estudiante:");
        System.out.println("4. Eliminar Estudiante:");
        System.out.println("5. Ingresar Libro:");
        System.out.println("6. Eliminar Libro:");
        System.out.print("> ");
        respuesta = sc.nextLine();
        switch(respuesta){
            case "1": 
                if(ingresarDocente()){
                    System.out.println("Docente ingresado exitosamente");
                }else{
                    System.out.println("Hubo un error al ingresar al docente");
                }
                break;
            case "2": 
                if(eliminarDocente()){
                    System.out.println("Docente eliminado exitosamente");
                }else{
                    System.out.println("Hubo un error al eliminar al docente");
                }
                break;
            case "3":
                if(ingresarEstudiante()){
                    System.out.println("Estudiante ingresado exitosamente");
                }else{
                    System.out.println("Hubo un error al ingresar al Estudiante");
                }
                break;
            case "4": 
                if(eliminarEstudiante()){
                    System.out.println("Estudiante eliminado exitosamente");
                }else{
                    System.out.println("Hubo un error al eliminado al Estudiante");
                }
                break;
            case "5": 
                if(ingresarLibro()){
                    System.out.println("Libro ingresado exitosamente");
                }else{
                    System.out.println("Hubo un error al ingresar el Libro");
                }
                break;
            case "6": 
                if(eliminarLibro()){
                    System.out.println("Libro eliminado exitosamente");
                }else{
                    System.out.println("Hubo un error al eliminar el Libro");
                }
                break;
            case "0":
                continuar = false;
                break;
            default: System.out.println("Ingrese opción válida");
        }  
        return continuar;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          while(menu());
    }
    
}
