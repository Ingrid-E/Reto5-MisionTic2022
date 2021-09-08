/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import static java.awt.PageAttributes.MediaType.E;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.E;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class GUI extends JFrame{
    private GridBagConstraints c = new GridBagConstraints();
    public static Font HenryP = Aplicacion_Hogwarts.loadFont("font/HarryP-Font.ttf");
    public static Font font = Aplicacion_Hogwarts.loadFont("font/RobotoMono-Regular.ttf");
    
    public GUI(){
        this.setLayout(new GridBagLayout());
        
        initGUI(getColumnas());
        tabla(getColumnas());
        
        this.setTitle("Java Aplication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(85,48,32));
    }
    
    public String[] getColumnas(){
        String[] columnas = {"Nombre", "Cantidad", "Disponible"};
        return columnas;
    }
    
    public void initGUI(String[] opcionesTabla){
        JLabel titulo = new JLabel("Howgarts");
        titulo.setFont(HenryP.deriveFont(96f));
        titulo.setForeground(new Color(252,161,54));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets=new Insets(10,0,80,0);
        this.add(titulo,c);
        OpcionMultiple tablas = new OpcionMultiple(opcionesTabla);
        tablas.setFocusable(false);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets=new Insets(0,50,0,140);
        this.add(tablas,c);
        JButton sumarFila = boton("+");
        c.gridx = 1;
        c.gridy = 1;
        c.insets=new Insets(0,0,0,0);
        this.add(sumarFila,c);
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets=new Insets(0,0,0,35);
        this.add(buscar(),c);
    }
    
    public JPanel buscar(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
        panel.setOpaque(false);

        
        JLabel buscarTxt = texto("Buscar");
        buscarTxt.setForeground(new Color(252,161,54));
        Escribir buscar = new Escribir(20);
        buscar.setFont(GUI.font.deriveFont(22f));
        
        

        panel.add(buscarTxt);
        panel.add(buscar);
        
        return panel;
    }
    
    public JLabel texto(String text){
        JLabel label = new JLabel(text);
        label.setFont(GUI.font.deriveFont(30f));
        return label;
    }
    
    public JButton boton(String texto){
        JButton button = new JButton(texto);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setFont(GUI.font.deriveFont(40f));
        button.setForeground(new Color(240,114,3));
        return button;
    }
    
    public void tabla(String[] columnas){
        JPanel contenedor = new JPanel();
        Border border = new LineBorder(new Color(58,33,36), 5, true);
        contenedor.setLayout(new GridBagLayout());
        contenedor.setBackground(new Color(125,53,28));
        contenedor.setBorder(border);
        contenedor.setOpaque(true);
        JButton probando = new JButton("X");
        Object[][] datos = {
            {"Ingrid",11,true},
            {"Jhonatan", 22, false},
            {"Cindy", 27, false},
            {"Cindy", 27, false}
        };

        Tabla tabla = new Tabla(datos, columnas);
        tabla.setRowHeight(30);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setFocusable(false);
        tabla.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        scrollPane.setPreferredSize(new Dimension(820, 300));
        contenedor.setPreferredSize(new Dimension(880, 350));
        contenedor.add(scrollPane);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets=new Insets(0,50,50,50);
        this.add(contenedor,c);
    }
    
    public class Escribir extends JTextField{
        private Shape shape;
        public Escribir(int size) {
            super(size);
            this.setForeground(new Color(74,0,1));
            this.setBackground(new Color(195,118,76));
            this.setOpaque(false);
        }
        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
           g.setColor(getForeground());
           g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        public boolean contains(int x, int y) {
           if (shape == null || !shape.getBounds().equals(getBounds())) {
              shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
           }
           return shape.contains(x, y);
        }
    }
    
    public class OpcionMultiple extends JComboBox{
        public OpcionMultiple(Object[] opciones){
            super(opciones);
            this.setFont(GUI.font.deriveFont(20f));
            this.setPreferredSize(new Dimension(250, 35));
            this.setForeground(new Color(74,0,1));
            this.setBackground(new Color(195,118,76)); 
            this.setOpaque(false);
            this.setUI(new MyComboBoxUI());
            
            Object child = this.getAccessibleContext().getAccessibleChild(0);
            BasicComboPopup popup = (BasicComboPopup)child;
            JList list = popup.getList();
            list.setSelectionBackground(new Color(255,189,115));
            list.setSelectionForeground(new Color(90,19,15));
            
        }

    }

    public class MyComboBoxUI extends BasicComboBoxUI {
        @Override
        protected void installDefaults() {
            super.installDefaults();
            LookAndFeel.uninstallBorder(comboBox); //Uninstalls the LAF border for both button and label of combo box.
        }

        @Override
        protected JButton createArrowButton() {
            //Feel free to play with the colors:
            final Color background = new Color(151,81,30);     //Default is UIManager.getColor("ComboBox.buttonBackground").
            final Color pressedButtonBorderColor = new Color(217,106,24); //Default is UIManager.getColor("ComboBox.buttonShadow"). The color of the border of the button, while it is pressed.
            final Color triangle = Color.BLACK;               //Default is UIManager.getColor("ComboBox.buttonDarkShadow"). The color of the triangle.
            final Color highlight = pressedButtonBorderColor;               //Default is UIManager.getColor("ComboBox.buttonHighlight"). Another color to show the button as highlighted.
            final JButton button = new BasicArrowButton(BasicArrowButton.SOUTH, background, pressedButtonBorderColor, triangle, highlight);
            button.setName("ComboBox.arrowButton"); //Mandatory, as per BasicComboBoxUI#createArrowButton().
            return button;
        }
    }
 
    public class Tabla extends JTable{
        //Attributes
        private int rows, columns;
        private JTableHeader tableHeader = this.getTableHeader();
        //Methods
        public Tabla(int rows, int columns) {
            super(new DefaultTableModel(rows, columns));
            this.rows = rows;
            this.columns = columns;
            this.setFont(GUI.font.deriveFont(36f));
            this.setBackground(Color.RED);
            tableHeader.setFont(GUI.font.deriveFont(22f));
            setCells();
        }
        public Tabla(Object[][] rowData,Object[] columnNames){
            super(new DefaultTableModel(rowData, columnNames));
            this.rows = rowData.length;
            this.columns = rowData[1].length;
            this.setForeground(Color.BLACK);
            this.setFont(GUI.font.deriveFont(20f));
            this.setBackground(new Color(225,176,109));
            tableHeader.setFont(GUI.font.deriveFont(22f));
            tableHeader.setBackground(new Color(195,118,76));
            
            setCells();
        }
        private void setCells(){
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(SwingConstants.CENTER);
            //cellRender.setVerticalAlignment(SwingConstants.BOTTOM);
            //cellRender.setOpaque(false);
            this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            for(int i=0; i < this.columns; i++) {
                this.getColumnModel().getColumn(i).setCellRenderer(cellRender);
            }
        }
   
    }
}
