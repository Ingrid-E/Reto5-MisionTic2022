/*
 * @author Ingrid Echeverri (https://github.com/Ingrid-E)
 * @version
 * @file aplicacion_hogwarts
 * @date    
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.EventQueue;

/**
 * Main class
 * @returns
 */
public class Aplicacion_Hogwarts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                    GUI aplication = new GUI();
                    }
                });
            }		
	});
    }
}
