/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import clases.Docente;
import clases.Estudiante;
import clases.Libro;
import clases.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import services.DocenteService;
import services.EstudianteService;
import services.LibroService;
import services.UsuarioService;
import services.UtilService;

/**
 *
 * @author Joseto
 */
public class Biblioteca {
    
    private static Scanner sc = new Scanner(System.in);
//    private static final List<Usuario> listaUsuarios = new ArrayList<Usuario>();
//    private static final List<Docente> listaDocentes = new ArrayList<Docente>();
//    private static final List<Estudiante> listaEstudiantes = new ArrayList<Estudiante>();
//    private static final List<Libro> listaLibros = new ArrayList<Libro>();
    
    public static boolean ingresarLibro(){
        boolean ingresado = false;
        String isbn = "1234-B",
               titulo = "Java",
               autor = "Joyanes",
               imagen = "path1";
        int cantidadBiblioteca = 10;
        Libro libro = new Libro(isbn, titulo, autor, cantidadBiblioteca, imagen);
        LibroService ls = new LibroService();
        ingresado = ls.ingresarLibro(libro);
        
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
               rut = "13334356-3",
               carrera = "Informática";
        char genero = 'M';
        if(UtilService.validarRut(rut)){
            EstudianteService es = new EstudianteService();
            Estudiante estudiante = new Estudiante(nombre, rut, genero, carrera);
            ingresado = es.ingresarUsuario(estudiante);
        }else{
            ingresado = false;
        }
        verListaUsuarios();
        return ingresado;
    }
    
    public static boolean eliminarEstudiante(){
        String rut = "13334356-3";
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
            ingresado = ds.ingresarUsuario(docente);
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
    
    public static boolean validarSiBDExiste(){
        String docentes = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\docentes.dat"),
               estudiantes = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\estudiantes.dat"),
               libros = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
        File docentesArch = new File(docentes);
        File estudiantesArch = new File(estudiantes);
        File librosArch = new File(libros);
        
        return docentesArch.exists() && estudiantesArch.exists() && librosArch.exists();
    }
    
    public static void vaciarBD(){
        String usuarios = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat"),
               libros = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
        File usuariosArch = new File(usuarios);
        File librosArch = new File(libros);
        
        usuariosArch.delete();
        librosArch.delete();
    }
    
    public static void cargarArchivos() throws IOException {
//        Docente[] docentes = new Docente[4];
//        Estudiante[] estudiantes = new Estudiante[4];
//        Libro[] libros = new Libro[4];
        
//        docentes[0] = new Docente("Luis Sanchez", "12899754-7", 'M', "Pedagogía Matemática", "Licenciado");
//        docentes[1] = new Docente("Josefa Paredes", "15769071-k", 'F', "Ingeniería en informática", "Magíster");
//        docentes[2] = new Docente("Evelin Campos", "16494491-3", 'F', "Ingeniería civil industrial", "Doctorado");
//        docentes[3] = new Docente("Hector Sanchez", "15098319-3", 'M', "Ingeniería en informática", "Licenciado");
//        
//        estudiantes[0] = new Estudiante("Hector Muñoz", "12950312-2", 'M', "Informática");
//        estudiantes[1] = new Estudiante("Claudio Jimenez", "25340646-1", 'F', "Ingeniería civil industrial");
//        estudiantes[2] = new Estudiante("Cristian Sandoval", "20789375-7", 'M', "Analista programador");
//        estudiantes[3] = new Estudiante("Mauricio Carvajal", "12005297-7", 'M', "Ingeniería civil industrial");
//        
//        libros[0] = new Libro("B00A9HSR2M", "Java Design Pattern Essentials", "Tony Bevis", 10, "https://images-na.ssl-images-amazon.com/images/I/4195qFwJVVL._SX382_BO1,204,203,200_.jpg");
        UsuarioService.listaUsuarios.add(new Docente("Luis Sanchez", "12899754-7", 'M', "Pedagogía Matemática", "Licenciado"));
        UsuarioService.listaUsuarios.add(new Docente("Josefa Paredes", "15769071-k", 'F', "Ingeniería en informática", "Magíster"));
        UsuarioService.listaUsuarios.add(new Docente("Evelin Campos", "16494491-3", 'F', "Ingeniería civil industrial", "Doctorado"));
        UsuarioService.listaUsuarios.add(new Docente("Hector Sanchez", "15098319-3", 'M', "Ingeniería en informática", "Licenciado"));
        UsuarioService.listaUsuarios.add(new Estudiante("Hector Muñoz", "12950312-2", 'M', "Informática"));
        UsuarioService.listaUsuarios.add(new Estudiante("Claudio Jimenez", "25340646-1", 'F', "Ingeniería civil industrial"));
        UsuarioService.listaUsuarios.add(new Estudiante("Cristian Sandoval", "20789375-7", 'M', "Analista programador"));
        UsuarioService.listaUsuarios.add(new Estudiante("Mauricio Carvajal", "12005297-7", 'M', "Ingeniería civil industrial"));
        LibroService.listaLibros.add(new Libro("B00A9HSR2M", "Java Design Pattern Essentials", "Tony Bevis", 10, "https://images-na.ssl-images-amazon.com/images/I/4195qFwJVVL._SX382_BO1,204,203,200_.jpg"));
        try{ 
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
            File file = new File(path);
            FileOutputStream fil = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(fil);
            obj.writeObject(UsuarioService.listaUsuarios);
            obj.close();
            
//            path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\estudiantes.dat");
//            File fileEstudiante = new File(path);
//            FileOutputStream filEstudiante = new FileOutputStream(fileEstudiante);
//            obj = new ObjectOutputStream(filEstudiante);
//            
//            obj.writeObject(listaEstudiantes);
//            obj.close();
            
            path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
            File fileLibros = new File(path);
            FileOutputStream filLibros = new FileOutputStream(fileLibros);
            obj = new ObjectOutputStream(filLibros);
            
            obj.writeObject(LibroService.listaLibros);
            obj.close();
            
        }catch (IOException e){
            System.out.println("ERROR 1 al escribir usuarios.dat:" + e.getMessage());
        }catch (Exception e){
            System.out.println("ERROR 2 al escribir usuarios.dat:" + e.getMessage());
        }
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
                    verListaUsuarios();
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
            default: System.out.println("Ingrese una opción válida");
        }  
        return continuar;
    }
    
    public static void verListaUsuarios() {
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Usuario> usuarios = (List<Usuario>) obje.readObject();
            obje.close();
            
            for(Usuario usuario : usuarios) {
                System.out.println(usuario.toString());
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
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if(!validarSiBDExiste()) cargarArchivos();
        verListaUsuarios();
        while(menu());

    }
    
}
