/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicios;

import clases.Docente;
import clases.Usuario;
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
public class DocenteService extends UsuarioService {

    @Override
    public boolean ingresarUsuario(Usuario usuario) {
        boolean creado = false;
        if(!Usuario.rutExiste(usuario.getRut())){
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
