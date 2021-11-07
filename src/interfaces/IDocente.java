/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Docente;

/**
 *
 * @author Joseto
 */
public interface IDocente extends IUsuario {
    
    public void agregarProfesionGrado(Docente docente, String grado, String profesion);
    
}
