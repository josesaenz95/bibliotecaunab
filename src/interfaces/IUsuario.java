/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Usuario;

/**
 *
 * @author Joseto
 */
public interface IUsuario {

    public boolean ingresarUsuario(Usuario usuario);
    public boolean eliminarUsuario(String rut);
    public boolean rutExiste(String rut);
    
}
