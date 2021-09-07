/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.*;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class GUI extends JFrame{
    private GridBagConstraints c = new GridBagConstraints();
    
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
        this.getContentPane().setBackground(Color.PINK);
    }
    
    public void initGUI(){
        JLabel titulo = new JLabel("Java Aplication");
        titulo.setFont(titulo.getFont().deriveFont(30f));
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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 5;
        c.gridheight = 4;
        c.insets=new Insets(1,40,1,1);
        String[] columnas = {"Nombre", "Cantidad","Disponible"};
        JButton probando = new JButton("X");
        Object[][] datos = {
            {"Ingrid",11,true},
            {"Jhonatan", 22, false},
            {"Cindy", 27, false},
            {"Cindy", 27, false}
        };
        JTable tabla = new JTable(datos, columnas);
        tabla.setRowHeight(28);
        this.add(tabla,c);
        
        int y = 2;
        c.gridx = 6;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.insets=new Insets(1,1,1,40);
        for(Object[] fila: datos){
            JButton eliminar = new JButton("x");
            c.gridy = y;
            this.add(eliminar,c);
            y++;
        }
        
        JPanel empty = new JPanel();
        c.gridy = 6;
        c.gridx = 4;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.insets=new Insets(40,40,40,40);
        this.add(empty,c);
      
    }
}
