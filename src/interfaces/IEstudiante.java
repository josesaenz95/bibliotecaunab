/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Estudiante;

/**
 *
 * @author Joseto
 */
public interface IEstudiante extends IUsuario{
    
    public void agregarCarrera(Estudiante estudiante, String carrera);
    
}
