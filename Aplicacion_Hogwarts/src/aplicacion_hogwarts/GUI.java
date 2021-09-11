/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.BorderLayout;
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
import java.awt.List;
import static java.awt.PageAttributes.MediaType.E;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.E;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.BorderFactory.createEmptyBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import model.Escuela;
import model.Ingrediente;
import model.Pocion;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class GUI extends JFrame{
    private GridBagConstraints c = new GridBagConstraints();
    public static Font HenryP = Aplicacion_Hogwarts.loadFont("font/HarryP-Font.ttf");
    public static Font font = Aplicacion_Hogwarts.loadFont("font/RobotoMono-Regular.ttf");
    private String[] columnas;
    private Object[][] datos;
    private static String[] TABLAS = {"Escuelas", "Pociones", "Ingredientes"};
    private Listen listener;
    private JPanel contenedor;
    private Tabla tabla;
    private JButton sumarFila;
    private GUI main, gui;
    public boolean one = true;
    protected String nombreTabla;
    
    public GUI(){
        this.main = this;
        this.gui = this;
        this.setLayout(new GridBagLayout());
        listener = new Listen();
        String[] escuela = {"Codigo", "Nombre", "Habilidad", "<html><p align='center'>Años<br>Servicios</p></html>", "Creador", "Fecha"};
        columnas = escuela;
        this.nombreTabla = "Escuelas";
        try {
            datos = Escuela.consultar();
        } catch (SQLException ex) {
            Logger.getLogger(Aplicacion_Hogwarts.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        initGUI();
        tabla();
        
        this.setTitle("Java Aplication");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Color(85,48,32));
    }
    
    public void cambiarTabla(String nombre){
        switch(nombre){
            case "Escuelas":
               String[] escuela = {"Codigo", "Nombre", "Habilidad", "<html><p align='center'>Años<br>Servicios</p></html>", "Creador", "Fecha"};
                columnas = escuela;
                this.nombreTabla = "Escuelas";
                try {
                    datos = Escuela.consultar();
                } catch (SQLException ex) {
                    Logger.getLogger(Aplicacion_Hogwarts.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Pociones":
                String[] pocion = {"Codigo", "Nombre", "<html><p align='center'>Registro<br>Litros</p></html>", "Uso", "Escuela"};
                columnas = pocion;
                this.nombreTabla = "Pociones";
                try {
                    datos = Pocion.consultar();
                } catch (SQLException ex) {
                    Logger.getLogger(Aplicacion_Hogwarts.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Ingredientes":
                String[] ingrediente = {"Codigo", "Nombre", "Peso Promedio", "Nivel Reaccion", "<html><p align='center'>Existe en<br>Bodega</p></html>"};
                columnas = ingrediente;
                 this.nombreTabla = "Ingredientes";
                try {
                    datos = Ingrediente.consultar();
                } catch (SQLException ex) {
                    Logger.getLogger(Aplicacion_Hogwarts.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                System.out.println("Nombre Incorrecto");
                break;
        }

        this.remove(contenedor);
        this.remove(tabla);
        tabla();
        this.revalidate();
	this.repaint();
        
    }
    
    public void initGUI(){
        JLabel titulo = new JLabel("Howgarts");
        titulo.setFont(HenryP.deriveFont(96f));
        titulo.setForeground(new Color(252,161,54));
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.insets=new Insets(10,0,80,0);
        this.add(titulo,c);
        OpcionMultiple tablas = new OpcionMultiple(GUI.TABLAS);
        tablas.setFocusable(false);
        tablas.addActionListener(listener);
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.insets=new Insets(0,50,0,220);
        this.add(tablas,c);
        sumarFila = boton("+");
        sumarFila.addMouseListener(listener);
        c.gridx = 1;
        c.gridy = 1;
        c.insets=new Insets(0,0,0,0);
        this.add(sumarFila,c);
        c.gridx = 2;
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.insets=new Insets(0,0,0,40);
        this.add(buscar(),c);
    }
    
    public JPanel buscar(){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.LEFT,10,0));
        panel.setOpaque(false);

        
        JLabel buscarTxt = texto("Buscar Nombre:");
        buscarTxt.setForeground(new Color(252,161,54));
        Escribir buscar = new Escribir(20);
        buscar.setFont(GUI.font.deriveFont(22f));
        
        

        panel.add(buscarTxt);
        panel.add(buscar);
        
        return panel;
    }
    
    public JLabel texto(String text){
        JLabel label = new JLabel(text);
        label.setFont(GUI.font.deriveFont(24f));
        return label;
    }
    
    public JButton boton(String texto){
        JButton button = new JButton(texto);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setFont(GUI.font.deriveFont(40f));
        button.setForeground(new Color(252,161,54));
        button.addMouseListener(listener);
        return button;
    }
    
    
    public void tabla(){
        contenedor = new JPanel();
        Border border = new LineBorder(new Color(58,33,36), 5, true);
        contenedor.setLayout(new GridBagLayout());
        contenedor.setBackground(new Color(125,53,28));
        contenedor.setBorder(border);
        contenedor.setOpaque(true);

        tabla = new Tabla(datos, columnas);
        tabla.setRowHeight(30);
        tabla.setIntercellSpacing(new Dimension(0, 0));
        tabla.setFocusable(false);
        tabla.setFillsViewportHeight(true);
        
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());


        scrollPane.setPreferredSize(new Dimension(1190, 300));
        contenedor.setPreferredSize(new Dimension(1200, 350));
        contenedor.add(scrollPane);
        c.gridy = 2;
        c.gridx = 0;
        c.gridwidth = 3;
        c.insets=new Insets(0,50,50,50);
        this.add(contenedor,c);
    }
    
    public class Listen implements ActionListener,MouseListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            if(ae.getSource() instanceof OpcionMultiple){
                OpcionMultiple opciones = (OpcionMultiple)ae.getSource();
                cambiarTabla(String.valueOf(opciones.getSelectedItem()));
            }else if(ae.getSource() instanceof Escribir){
                Escribir texto = (Escribir)ae.getSource();
                switch(nombreTabla){
                    case "Escuelas":
                        datos = Escuela.buscar(texto.getText());
                        break;
                    case "Ingredientes":
                        datos = Ingrediente.buscar(texto.getText());
                        break;
                    case "Pociones":
                        datos = Pocion.buscar(texto.getText());
                        break;
                      
                }
                
                
                gui.remove(contenedor);
                gui.remove(tabla);
                tabla();
                gui.revalidate();
                gui.repaint();
            }
        }

        @Override
        public void mouseClicked(MouseEvent me) {
            if(me.getSource() == sumarFila && one){
                AgregarObjecto popout = new AgregarObjecto(columnas, main);
                one = false;
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            if(me.getSource() instanceof JButton){
               JButton boton = (JButton)me.getSource();
               boton.setForeground(Color.WHITE);
            }
        }

        @Override
        public void mouseExited(MouseEvent me) {
            if(me.getSource() instanceof JButton){
               JButton boton = (JButton)me.getSource();
               boton.setForeground(new Color(252,161,54));
            }
        }
        
    }
    
    public class PopOut extends JFrame{
        private JButton borrar = boton("Eliminar");
        private JButton actualizar = boton("Modificar");
        private JLabel nombre = new JLabel("", SwingConstants.CENTER);
        private Tabla tabla;
        int fila;
        public JFrame popout;
        public PopOut(int x,int y,int fila, Tabla tabla){
            this.popout = this;
            this.tabla = tabla;
            this.fila = fila;
            JPanel botones = new JPanel();
            botones.setOpaque(false);
            botones.setLayout(new FlowLayout());
            botones.add(borrar);
            botones.add(actualizar);
            
            nombre.setText("Fila "+ fila);
            nombre.setFont(GUI.font.deriveFont(20f));
            nombre.setForeground(new Color(252,161,54));
            this.add(nombre, BorderLayout.PAGE_START);
            this.getRootPane().setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(252,161,54)));
            this.getContentPane().setBackground((new Color(125,53,28)));
            this.add(botones);
            this.setLocation(x,y);
            this.setUndecorated(true);
            this.pack();
            this.setVisible(true);
        }
        
        private JButton boton(String texto){
            JButton button = new JButton(texto);
            button.setBorderPainted(false); 
            button.setContentAreaFilled(false); 
            button.setFocusPainted(false); 
            button.setOpaque(false);
            button.setFont(GUI.font.deriveFont(18f));
            button.setForeground(new Color(252,161,54));
            
            if(texto == "Eliminar"){
                button.addMouseListener(new MouseAdapter(){
                    
                    public void mouseClicked(MouseEvent me) {
                        System.out.println("Click Eliminar");
                        int id = (int)tabla.getModel().getValueAt(fila, 0);
                        if(nombreTabla == "Escuelas"){
                            Escuela.eliminar(id);
                            gui.cambiarTabla("Escuelas");
                        }else if(nombreTabla == "Ingredientes"){
                            Ingrediente.eliminar(id);
                            gui.cambiarTabla("Ingredientes");
                        }else{
                            Pocion.eliminar(id);
                            gui.cambiarTabla("Pociones");
                        }
                        
                        popout.dispose();
                    }
                });
            };
            
            if(texto == "Modificar"){
                button.addMouseListener(new MouseAdapter(){
                    
                    public void mouseClicked(MouseEvent me) {
                        try {
                            int id = (int)tabla.getModel().getValueAt(fila, 0);
                            Object[] datos = null;
                            if(nombreTabla == "Escuelas"){
                                datos = Escuela.consultar()[fila];
                            }else if(nombreTabla == "Ingredientes"){
                                datos = Ingrediente.consultar()[fila];
                            }else{
                                datos = Pocion.consultar()[fila];
                            }
                            AgregarObjecto modificar = new AgregarObjecto(columnas, datos,gui);
                            popout.dispose();
                        } catch (SQLException ex) {
                            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    }
                });
            };
            
            
            //int id = (int)main.getModel().getValueAt(fila, 0);
            //Escuela.eliminar(id);
            //gui.cambiarTabla("Escuelas");
            return button;
        }
        
    }
    
    public class Escribir extends JTextField{
        private Shape shape;
        public Escribir(int size) {
            super(size);
            this.setForeground(new Color(74,0,1));
            this.setBackground(new Color(195,118,76));
            this.setOpaque(false);
            this.addActionListener(listener);
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
        private Tabla main;
        private int x,y;

        public Tabla(Object[][] rowData,Object[] columnNames){
            super(new DefaultTableModel(rowData, columnNames) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            this.main = this;
            this.rows = rowData.length;
            this.columns = rowData[0].length;
            this.setForeground(Color.BLACK);
            this.setFont(GUI.font.deriveFont(18f));
            this.setBackground(new Color(225,176,109));
            tableHeader.setPreferredSize(new Dimension(100, 60));
            tableHeader.setFont(GUI.font.deriveFont(22f));
            tableHeader.setBackground(new Color(195,118,76));
            
            this.addMouseListener(new MouseAdapter(){
                private boolean exists = false;
                private PopOut popout;
                public void mouseClicked(MouseEvent me) {
                    int fila = main.getSelectedRow();
                    if(!exists && fila >= 0){
                        int x = (int)me.getLocationOnScreen().getX();
                        int y = (int)me.getLocationOnScreen().getY();
                        popout = new PopOut(x,y, fila, main);
                        exists = true;
                        System.out.println(fila);
                    }else if(fila >= 0){
                        popout.dispose();
                        exists = false;
                    }
              
                }

            });
            
            setCells();
        }
        private void setCells(){
            DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
            cellRender.setHorizontalAlignment(SwingConstants.CENTER);
            this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
            for(int i=0; i < this.columns; i++) {
                for(int j=0; j<this.rows; j++){
                    if(this.getModel().getValueAt(j, i) instanceof Integer || this.getModel().getValueAt(j, i) instanceof Double){
                         this.getColumnModel().getColumn(i).setMinWidth(200);
                         this.getColumnModel().getColumn(i).setMaxWidth(500);
                    }
                }
                this.getColumnModel().getColumn(i).setCellRenderer(cellRender);
            }
        }
    }
}
