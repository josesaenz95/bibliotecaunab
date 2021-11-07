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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

/**
 *
 * @author Joseto
 */
public class DocenteService implements IDocente {

    @Override
    public void agregarProfesionGrado(Docente docente, String grado, String profesion) {
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
    
}
