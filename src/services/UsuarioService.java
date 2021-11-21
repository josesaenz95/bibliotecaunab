/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clases.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class UsuarioService {
    public static List<Usuario> listaUsuarios = new ArrayList<Usuario>();
    
    public static boolean rutExiste(String rut) {
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
    
    public static Usuario buscarUsuario(String rut){
        Usuario encontrado = null;
        try{
            String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
            FileInputStream fis = new FileInputStream(path);
            ObjectInputStream obje = new ObjectInputStream(fis);
            List<Usuario> usuarios = (List<Usuario>) obje.readObject();
            obje.close();
            
            for(Usuario usuario : usuarios) {
                if(usuario.getRut().equals(rut)){
                    encontrado = usuario;
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
