/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;


/**
 *
 * @author Morenofamily
=======

/**
 *
 * @author Edwin Sandoval
>>>>>>> c51e98591f823fcad86587d08662936a8a3934cc
 */
public class pruebas {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        ProductoDAO pro = new ProductoDAO();
        List<Producto> lis = pro.listar();
        
        
        for(int i=0; i<lis.size();i++){
            System.out.println("Nombres: "+lis.get(i).toString());
        }
              
    }
}