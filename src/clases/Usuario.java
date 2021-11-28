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
public abstract class Usuario implements Serializable {
    
    private String nombre;
    private String rut;
    private char genero;
    private String prestamo;
    
    public Usuario(String nombre, String rut, char genero){
        this.nombre = nombre;
        this.rut = rut;
        this.genero = genero;
        this.prestamo = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        if(validarRut(rut) && !rutExiste(rut)){
            this.rut = rut;
        }
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

    public String getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(String prestamo) {
        this.prestamo = prestamo;
    }    

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", rut=" + rut + ", genero=" + genero + ", prestamo=" + prestamo + '}';
    }
    
    public abstract String tipoUsuario();
    
    public static boolean validarRut(String rut) {
        boolean esValido = false;
        rut =  rut.toUpperCase();
        rut = rut.replace(".", "");
        rut = rut.replace("-", "");
        String digitoEnviado = String.valueOf(rut.charAt(rut.length() - 1)).toUpperCase();
        digitoEnviado = digitoEnviado.equals("K") ? "10" : digitoEnviado.equals("0") ? "11" : digitoEnviado;
        rut = rut.substring(0, rut.length() - 1);
        String rutInvertido = "";
        
        int serie = 2;
        int sumatoria = 0;
        for(int i=0; i < rut.length(); ++i){
            char letra = rut.charAt(i);
            rutInvertido = letra + rutInvertido;
        }        
        for(int i=0; i < rutInvertido.length(); ++i){
            int numero = Character.getNumericValue(rutInvertido.charAt(i));
            int mult = (numero * serie);
            sumatoria = sumatoria + mult;
            if(serie < 7){
                ++serie;
            }else{
                serie = 2;
            }
        }
        String digito = String.valueOf(11 - (sumatoria % 11));
        if(digitoEnviado.equals(digito)){
            esValido = true;
        }
        
        return esValido;
    }
    
    public static boolean rutExiste(String rut){
        boolean existe = false;
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Usuario> estudiantes = (List<Usuario>) obje.readObject();
            obje.close();
            
            for(Usuario estudiante : estudiantes) {
                if(estudiante.getRut().equals(rut)){
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
    
}
