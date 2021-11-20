/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clases.Docente;
import clases.Usuario;
import interfaces.IDocente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class DocenteService implements IDocente {

    @Override
    public void agregarProfesionGrado(Docente docente, String grado, String profesion) {
        docente.setGrado(grado);
        docente.setProfesion(profesion);
    }

    @Override
    public boolean ingresarUsuario(Usuario usuario) {
        boolean creado = false;
        if(!rutExiste(usuario.getRut())){
            try {
                UsuarioService.listaUsuarios.add(usuario);
                String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
                File file = new File(path);
                file.delete();
                ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
                fobj.writeObject(UsuarioService.listaUsuarios);
                fobj.close();
                creado = true;
            } catch (NotSerializableException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }else{
            System.out.println("El rut " + usuario.getRut() + " ya existe en el sistema ");
        }
        
        return creado;
    }

    @Override
    public boolean eliminarUsuario(String rut) {
        boolean eliminado = false;
        for(int i=0; i < UsuarioService.listaUsuarios.size(); ++i){
            Usuario usuario = UsuarioService.listaUsuarios.get(i);
            if(usuario.getRut().equals(rut)){
                UsuarioService.listaUsuarios.remove(i);
                eliminado = true;
            }
        }
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios.dat");
        File file = new File(path);
        file.delete();
        try {
            ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
            fobj.writeObject(UsuarioService.listaUsuarios);
            fobj.close();
        } catch (NotSerializableException ex) {
            System.out.println(ex.toString());
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        return eliminado;
    }
    
    @Override
    public boolean rutExiste(String rut) {
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
