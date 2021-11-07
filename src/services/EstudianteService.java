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

/**
 *
 * @author Joseto
 */
public class EstudianteService implements IEstudiante, Serializable {

    @Override
    public void agregarCarrera(Estudiante estudiante, String carrera) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean crearUsuario(Usuario usuario) {
        boolean creado = false;
        if(!rutExiste(usuario.getRut())){
            try {
                String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios\\" + usuario.getRut() + ".dat");
                File file = new File(path);
                ObjectOutputStream fobj = new ObjectOutputStream(new FileOutputStream(file));
                fobj.writeObject(usuario);
                fobj.flush();
                fobj.close();
                creado = true;
            } catch (NotSerializableException ex) {
                System.out.println(ex.toString());
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
        }
        
        return creado;
    }

    @Override
    public boolean eliminarUsuario(String rut) {
        boolean eliminado = false;
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios\\" + rut + ".dat");
        File file = new File(path);
        if(file.exists()){
            file.delete();
            eliminado = true;
        }
        
        return eliminado;
    }
    
    @Override
    public boolean rutExiste(String rut) {
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios\\" + rut + ".dat");
        File file = new File(path);
        return file.exists();
    }

    public static void mostrar(String rut) throws FileNotFoundException, IOException, ClassNotFoundException {
        String path = Paths.get("").toAbsolutePath().toString().concat("\\src\\database\\usuarios\\" + rut + ".dat");
        ObjectInputStream obje = new ObjectInputStream(new FileInputStream(path));
        Estudiante estudiante = (Estudiante) obje.readObject();
        System.out.println(estudiante);
    }
}
