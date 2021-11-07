/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.io.File;

/**
 *
 * @author Joseto
 */
public class UtilService {
    
   public static boolean archivoExiste(File file){
       return file.exists();
   }
   
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
    
}
