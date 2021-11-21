/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import clases.Estudiante;
import clases.Usuario;
import interfaces.IEstudiante;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Joseto
 */
public class EstudianteService implements IEstudiante, Serializable {

    @Override
    public void agregarCarrera(Estudiante estudiante, String carrera) {
        estudiante.setCarrera(carrera);
    }

    @Override
    public boolean ingresarUsuario(Usuario usuario) {
        boolean creado = false;
        if(!UsuarioService.rutExiste(usuario.getRut())){
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

}
