/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import clases.Devolucion;
import clases.Docente;
import clases.Estudiante;
import clases.Libro;
import clases.Prestamo;
import clases.Usuario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import services.DevolucionService;
import services.DocenteService;
import services.EstudianteService;
import services.LibroService;
import services.PrestamoService;
import services.UsuarioService;
import services.UtilService;

/**
 *
 * @author Joseto
 */
public class Biblioteca {
    
    private static Scanner sc = new Scanner(System.in);
    
    /* * * * * * * * * * * * * * * * * */
    /*           OPERACIONES           */
    /*           DEL SISTEMA           */
    /* * * * * * * * * * * * * * * * * */
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
        verListaLibros();
        return ingresado;
    }
    
    public static boolean eliminarLibro(){
        String isbn = "B00A9HSR2M";
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
    
    public static boolean ingresarPrestamo(){
        String rut = "13334356-3",
               isbn = "B00A9HSR2M";
        int cantidadDias = 7;
        
        PrestamoService ps = new PrestamoService();
        return ps.ingresarPrestamo(isbn, rut, cantidadDias);
    }
    
    public static boolean ingresarDevolucion(){
        String rut = "16494491-3",
               isbn = "9789873832277";
        DevolucionService ds = new DevolucionService();
        
        return ds.ingresarDevolucion(isbn, rut);
    }
    
    /* * * * * * * * * * * * * * * * * */
    /*             UTILS               */
    /* * * * * * * * * * * * * * * * * */
    public static boolean validarSiBDExiste(){
        String usuarios = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat"),
               libros = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
        File usuariosArch = new File(usuarios);
        File librosArch = new File(libros);
        
        return  usuariosArch.exists() && librosArch.exists();
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
        Docente docentePruebaPrestamo = new Docente("Luis Sanchez", "16494491-3", 'M', "Pedagogía Matemática", "Licenciado");
        UsuarioService.listaUsuarios.add(docentePruebaPrestamo);
        UsuarioService.listaUsuarios.add(new Docente("Josefa Paredes", "15769071-k", 'F', "Ingeniería en informática", "Magíster"));
        UsuarioService.listaUsuarios.add(new Docente("Evelin Campos", "12899754-7", 'F', "Ingeniería civil industrial", "Doctorado"));
        UsuarioService.listaUsuarios.add(new Docente("Hector Sanchez", "15098319-3", 'M', "Ingeniería en informática", "Licenciado"));
        UsuarioService.listaUsuarios.add(new Estudiante("Hector Muñoz", "12950312-2", 'M', "Informática"));
        UsuarioService.listaUsuarios.add(new Estudiante("Claudio Jimenez", "25340646-1", 'F', "Ingeniería civil industrial"));
        UsuarioService.listaUsuarios.add(new Estudiante("Cristian Sandoval", "20789375-7", 'M', "Analista programador"));
        UsuarioService.listaUsuarios.add(new Estudiante("Mauricio Carvajal", "12005297-7", 'M', "Ingeniería civil industrial"));
        
        Libro libroPruebaPrestamo = new Libro("9789873832277", "Programacion Orientada A Objetos Y Estructura De Datos A Fondo", "Pablo Sznajdleder", 3, "https://www.librosyeditores.com/10382-thickbox_default/programacion-orientada-a-objetos-y-estructura-de-datos-a-fondo-implementacion-de-algoritmos-en-java.jpg");
        LibroService.listaLibros.add(new Libro("B00A9HSR2M", "Java Design Pattern Essentials", "Tony Bevis", 10, "https://images-na.ssl-images-amazon.com/images/I/4195qFwJVVL._SX382_BO1,204,203,200_.jpg"));
        LibroService.listaLibros.add(libroPruebaPrestamo);
        
        Prestamo prestamoPrueba = new Prestamo();
        docentePruebaPrestamo.setPrestamo(libroPruebaPrestamo.getIsbn());
        libroPruebaPrestamo.setCantidadDisponible(libroPruebaPrestamo.getCantidadDisponible() - 1);
        prestamoPrueba.setLibro(libroPruebaPrestamo);
        prestamoPrueba.setUsuario(docentePruebaPrestamo);
        prestamoPrueba.setFechaPrestamo(LocalDate.of(2021, 11, 01));
        prestamoPrueba.setCantidadDias(12);
        
        Devolucion devolucion = new Devolucion(prestamoPrueba.getFechaPrestamo().plusDays(12));
        prestamoPrueba.setDevolucion(devolucion);
        PrestamoService.listaPrestamos.add(prestamoPrueba);
        
        try{ 
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
            File file = new File(path);
            FileOutputStream fil = new FileOutputStream(file);
            ObjectOutputStream obj = new ObjectOutputStream(fil);
            obj.writeObject(UsuarioService.listaUsuarios);
            obj.close();
            
            path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
            File fileLibros = new File(path);
            FileOutputStream filLibros = new FileOutputStream(fileLibros);
            obj = new ObjectOutputStream(filLibros);
            
            obj.writeObject(LibroService.listaLibros);
            obj.close();
            
            path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\prestamos.dat");
            File filePrestamos = new File(path);
            FileOutputStream filPrestamos = new FileOutputStream(filePrestamos);
            obj = new ObjectOutputStream(filPrestamos);
            
            obj.writeObject(PrestamoService.listaPrestamos);
            obj.close();
            
        }catch (IOException e){
            System.out.println("ERROR 1 al escribir usuarios.dat:" + e.getMessage());
        }catch (Exception e){
            System.out.println("ERROR 2 al escribir usuarios.dat:" + e.getMessage());
        }
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
        
    public static void verListaLibros() {
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\libros.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Libro> libros = (List<Libro>) obje.readObject();
            obje.close();
            
            for(Libro libro : libros) {
                System.out.println(libro.toString());
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
    
    public static void verListaPrestamos() {
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\prestamos.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Prestamo> prestamos = (List<Prestamo>) obje.readObject();
            obje.close();
            
            for(Prestamo prestamo : prestamos) {
                System.out.println(prestamo.toString());
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
        System.out.println("7. Ingresar Préstamo:");
        System.out.println("8. Ingresar Devolución:");
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
                    verListaLibros();
                }else{
                    System.out.println("Hubo un error al eliminar el Libro");
                }
                break;
            case "7": 
                if(ingresarPrestamo()){
                    System.out.println("Nuevo prestamo ingresado exitosamente");
//                    verListaLibros();
                }else{
                    System.out.println("Hubo un error al ingresar el prestamo");
                }
                break;
            case "8": 
                if(ingresarDevolucion()){
                    System.out.println("Devolución realizada exitosamente");
//                    verListaLibros();
                }else{
                    System.out.println("Hubo un error al realizar la devolución");
                }
                break;
            case "0":
                continuar = false;
                break;
            default: System.out.println("Ingrese una opción válida");
        }  
        return continuar;
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if(!validarSiBDExiste()) cargarArchivos();
        verListaPrestamos();
//        verListaLibros();
        while(menu());
        
    }
    
}
