/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class GUI extends JFrame{
    private GridBagConstraints c = new GridBagConstraints();
    public static Font HenryP = Aplicacion_Hogwarts.loadFont("font/HarryP-Font.ttf");
    
    
    public GUI(){
        this.setLayout(new GridBagLayout());
        initGUI();
        tabla();
        this.setTitle("Java Aplication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.decode("#D7BA99"));
    }
    
    public void initGUI(){
        JLabel titulo = new JLabel("Java Aplication");
        titulo.setFont(HenryP.deriveFont(96f));
        titulo.setForeground(Color.decode("#492D0E"));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 7;
        c.insets=new Insets(10,1,80,1);
        this.add(titulo,c);
        JComboBox tablas = new JComboBox();
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets=new Insets(1,40,1,40);
        this.add(tablas,c);
        JButton sumarFila = new JButton("+");
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(sumarFila,c);
        JLabel buscarTxt = new JLabel("Buscar");
        c.gridx = 3;
        c.gridy = 1;
        c.insets=new Insets(1,1,1,1);
        this.add(buscarTxt,c);
        JTextField buscar = new JTextField(20);
        c.gridx = 5;
        c.gridy = 1;
        c.gridwidth = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets=new Insets(1,20,1,1);
        this.add(buscar,c);
    }
    
    
    public void tabla(){
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new GridBagLayout());
        String[] columnas = {"Nombre", "Cantidad","Disponible"};
        JButton probando = new JButton("X");
        Object[][] datos = {
            {"Ingrid",11,true},
            {"Jhonatan", 22, false},
            {"Cindy", 27, false},
            {"Cindy", 27, false}
        };

        Tabla tabla = new Tabla(datos, columnas);
        tabla.setRowHeight(40);
        System.out.println("Table Font: " + tabla.getFont());
        JScrollPane scrollPane = new JScrollPane(tabla);
        tabla.setFillsViewportHeight(true);
        scrollPane.setPreferredSize(new Dimension(820, 300));
        contenedor.setPreferredSize(new Dimension(880, 350));
        contenedor.add(scrollPane);
        c.gridy = 2;
        c.gridx = 1;
        c.gridwidth = 5;
        c.gridheight = 1;
        c.insets=new Insets(1,1,1,1);
        this.add(contenedor,c);
    }
    private static Font loadFont(String file) {
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
