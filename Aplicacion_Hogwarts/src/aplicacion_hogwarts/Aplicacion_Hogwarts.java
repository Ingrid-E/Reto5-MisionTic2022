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
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.io.IOException;

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
    
        public static Font loadFont(String file) {
        Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File(GUI.class.getResource(file).getFile()));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(font);
		} catch (FontFormatException | IOException e) {
                    // TODO Auto-generated catch block
		}
		return font;
	}
}
