/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import model.Escuela;
import model.Ingrediente;
import model.Pocion;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class AgregarObjecto extends JFrame{
    private JButton cancelar, agregar;
    private Listen listener = new Listen();
    private Color textColor = new Color(252,161,54);
    private Color fondoColor = new Color(86,0,35);
    private Color textFieldColor = new Color(166,0,66);
    private JPanel contenedor;
    private JFormattedTextField txtDate;
    private GUI gui;
    public AgregarObjecto(String[] espacios, GUI gui){
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        contenedor = new JPanel();
        GridLayout layout = new GridLayout(espacios.length+1,2);
        layout.setVgap(10);
        layout.setHgap(10);
        contenedor.setLayout(layout);
        
        agregarEspacios(espacios);
        
        cancelar = boton("Cancelar");
        cancelar.addMouseListener(listener);
        agregar = boton("Agregar");
        agregar.addMouseListener(listener);
        
        contenedor.add(cancelar);
        contenedor.add(agregar);
        contenedor.setBackground(fondoColor);
        
        this.add(contenedor);
        this.setSize(450,500);
        this.setUndecorated(true);
        this.getContentPane().setBackground(fondoColor);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    public AgregarObjecto(String[] espacios, Object[] datos,GUI gui){
        this.gui = gui;
        this.setLayout(new GridBagLayout());
        contenedor = new JPanel();
        GridLayout layout = new GridLayout(espacios.length+1,2);
        layout.setVgap(10);
        layout.setHgap(10);
        contenedor.setLayout(layout);
        
        agregarEspacios(espacios, datos);
        
        cancelar = boton("Cancelar");
        cancelar.addMouseListener(listener);
        agregar = boton("Modificar");
        agregar.addMouseListener(listener);
        
        contenedor.add(cancelar);
        contenedor.add(agregar);
        contenedor.setBackground(fondoColor);
        
        this.add(contenedor);
        this.setSize(450,500);
        this.setUndecorated(true);
        this.getContentPane().setBackground(fondoColor);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }
    
    
    public void agregarEspacios(String[] espacios) {
        for (String nombre : espacios) {
            if (nombre == "<html><p align='center'>Años<br>Servicios</p></html>") {
                nombre = "Años Servicios";
            }else if(nombre == "id_Escuela"){
                nombre = "Escuela";
            }else if(nombre == "<html><p align='center'>Existe en<br>Bodega</p></html>"){
                nombre = "Existe en Bodega";
            }

            JLabel texto = new JLabel(nombre);
            texto.setFont(GUI.font.deriveFont(20f));
            texto.setForeground(textColor);
            contenedor.add(texto);

            if (nombre == "Fecha") {
                System.out.println("Fecha");
                MaskFormatter formato;
                try {
                    formato = new MaskFormatter("####-##-##");
                    formato.setPlaceholderCharacter('_');
                    txtDate = new JFormattedTextField(formato);
                    txtDate.setFont(GUI.font.deriveFont(20f));
                    txtDate.setForeground(textColor);
                    txtDate.setBorder(null);
                    txtDate.setBackground(textFieldColor);
                    contenedor.add(txtDate);
                } catch (ParseException ex) {
                    Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(nombre == "Escuela"){
                String[] nombreEscuelas = null;
                try {
                    Object[][] datos = Escuela.consultar();
                    nombreEscuelas = new String[datos.length];
                    for(int i = 0; i < datos.length; i++ ){
                        nombreEscuelas[i] = (String)datos[i][1];
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                JComboBox escuelas = new JComboBox(nombreEscuelas);
                escuelas.setBackground(textFieldColor);
                escuelas.setForeground(textColor);
                escuelas.setFont(GUI.font.deriveFont(20f));
                contenedor.add(escuelas);
                
            }else if(nombre =="Existe en Bodega"){
                String[] nombreEscuelas = {"true","false"};
                JComboBox escuelas = new JComboBox(nombreEscuelas);
                escuelas.setBackground(textFieldColor);
                escuelas.setForeground(textColor);
                escuelas.setFont(GUI.font.deriveFont(20f));
                contenedor.add(escuelas);
            }
            else {
                JTextField escribir = new JTextField(15);
                if (nombre == "Codigo" || nombre == "Años Servicios" || nombre == "Peso Promedio" || nombre == "Uso") {
                    escribir.addKeyListener(listener);
                }
                escribir.setFont(GUI.font.deriveFont(20f));
                escribir.setForeground(textColor);
                escribir.setBorder(null);
                escribir.setBackground(textFieldColor);
                contenedor.add(escribir);
            }

        }
    }
    
     public void agregarEspacios(String[] espacios, Object[] datos) {
        int dato = 0;
        for (String nombre : espacios) {
            if (nombre == "<html><p align='center'>Años<br>Servicios</p></html>") {
                nombre = "Años Servicios";
            }else if(nombre == "id_Escuela"){
                nombre = "Escuela";
            }else if(nombre == "<html><p align='center'>Existe en<br>Bodega</p></html>"){
                nombre = "Existe en Bodega";
            }

            JLabel texto = new JLabel(nombre);
            texto.setFont(GUI.font.deriveFont(20f));
            texto.setForeground(textColor);
            contenedor.add(texto);

            if (nombre == "Fecha") {
                System.out.println("Fecha");
                MaskFormatter formato;
                try {
                    formato = new MaskFormatter("####-##-##");
                    formato.setPlaceholderCharacter('_');
                    txtDate = new JFormattedTextField(formato);
                    txtDate.setText(String.valueOf(datos[dato]));
                    txtDate.setFont(GUI.font.deriveFont(20f));
                    txtDate.setForeground(textColor);
                    txtDate.setBorder(null);
                    txtDate.setBackground(textFieldColor);
                    contenedor.add(txtDate);
                } catch (ParseException ex) {
                    Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(nombre == "Escuela"){
                String[] nombreEscuelas = null;
                try {
                    Object[][] datos2 = Escuela.consultar();
                    nombreEscuelas = new String[datos2.length];
                    for(int i = 0; i < datos2.length; i++ ){
                        nombreEscuelas[i] = (String)datos2[i][1];
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                }
             
                JComboBox escuelas = new JComboBox(nombreEscuelas);
                escuelas.setBackground(textFieldColor);
                escuelas.setForeground(textColor);
                escuelas.setFont(GUI.font.deriveFont(20f));
                contenedor.add(escuelas);
                
            }else if(nombre =="Existe en Bodega"){
                String[] nombreEscuelas = {"true","false"};
                JComboBox escuelas = new JComboBox(nombreEscuelas);
                escuelas.setBackground(textFieldColor);
                escuelas.setForeground(textColor);
                escuelas.setFont(GUI.font.deriveFont(20f));
                contenedor.add(escuelas);
            } else {
                JTextField escribir = new JTextField(15);
                escribir.setText(String.valueOf(datos[dato]));
                if(nombre == "Codigo"){
                    escribir.setEditable(false);
                }
                if (nombre == "Años Servicios") {
                    escribir.addKeyListener(listener);
                }
                escribir.setFont(GUI.font.deriveFont(20f));
                escribir.setForeground(textColor);
                escribir.setBorder(null);
                escribir.setBackground(textFieldColor);
                contenedor.add(escribir);
            }
            dato++;
        }
    }

    
    public void getData(boolean agregar){
        Component[] comps = contenedor.getComponents();
        int posicion = 0;
        boolean completed = true;
        ArrayList<Object> guardarInfo = new ArrayList<>();
        for (Component comp : comps) {
            if(comp instanceof JTextField){
                JTextField info = (JTextField)comp;
                if(info.getText().equals("")){
                    comps[posicion-1].setForeground(Color.RED);
                    completed = false;
                }else{
                    guardarInfo.add(info.getText());
                    comps[posicion-1].setForeground(textColor);
                }
            }
            if (comp.equals(txtDate)) {
                JFormattedTextField fecha = (JFormattedTextField) comp;
                System.out.println(fecha.getText());
                if (fecha.getText().equals("____-__-__")) {
                    comps[posicion-1].setForeground(Color.RED);
                    completed = false;
                }else{
                    guardarInfo.add(fecha.getText());
                    comps[posicion-1].setForeground(textColor);
                }
            }
            if(comp instanceof JComboBox){
                JComboBox opcion = (JComboBox)comp;
                if(opcion.getSelectedItem() == "true" || opcion.getSelectedItem() == "false"){
                    Boolean existe = Boolean.parseBoolean((String)opcion.getSelectedItem());
                    guardarInfo.add(existe);
                }else{
                    int id_escuela = Escuela.getId((String)opcion.getSelectedItem());
                    guardarInfo.add(id_escuela);
                }
                
            }
            
            posicion++;
        }
        if(completed){
            Set<Object> set = new LinkedHashSet<>(guardarInfo);
            guardarInfo.clear();
            guardarInfo.addAll(set);      
            int id =  Integer.valueOf((String) guardarInfo.get(0));
            String nombre = String.valueOf(guardarInfo.get(1));

            switch (gui.nombreTabla) {
                case "Escuelas":
                    String habilidad = String.valueOf(guardarInfo.get(2));
                    int anios = Integer.valueOf((String) guardarInfo.get(3));
                    String creador = String.valueOf(guardarInfo.get(4));
                    String fecha = String.valueOf(guardarInfo.get(5));

                    Escuela escuela = new Escuela(id, nombre, habilidad, anios, creador, fecha);
                    if (agregar) {
                        try {
                            escuela.agregarEscuela();
                        } catch (SQLException ex) {
                            Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        escuela.actualizar();
                    }
                    gui.cambiarTabla("Escuelas");
                    gui.one = true;
                    dispose();
                    break;
                case "Ingredientes":
                    int peso_promedio = Integer.valueOf((String) guardarInfo.get(2));
                    double nivel_reaccion = Double.valueOf((String) guardarInfo.get(3));
                    Boolean existe = (Boolean) guardarInfo.get(4);
                    Ingrediente ingrediente = new Ingrediente(id, nombre, peso_promedio, nivel_reaccion, existe);
                    if (agregar) {
                        try {
                            ingrediente.agregarIngrediente();

                        } catch (SQLException ex) {
                            Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        ingrediente.actualizar();
                    }
                    gui.cambiarTabla("Ingredientes");
                    gui.one = true;
                    dispose();
                    break;
                case "Pociones":
                    double registro_litros = Double.valueOf((String) guardarInfo.get(2));
                    int uso = Integer.valueOf((String) guardarInfo.get(3));
                    System.out.println("posicion 4: " + guardarInfo.get(4));
                    int codigo = (int) guardarInfo.get(4);
                    Pocion pocion = new Pocion(id, nombre, registro_litros, uso, codigo);
                    if (agregar) {
                        try {
                            pocion.agregarPocion();
                        } catch (SQLException ex) {
                            Logger.getLogger(AgregarObjecto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        pocion.actualizar();
                    }
                    gui.cambiarTabla("Pociones");
                    gui.one = true;
                    dispose();

                    break;
                    
            }

        }else{
            guardarInfo = new ArrayList<>();
        }
   
    }
     
   
    public JButton boton(String texto){
        JButton button = new JButton(texto);
        button.setBorderPainted(false); 
        button.setContentAreaFilled(false); 
        button.setFocusPainted(false); 
        button.setOpaque(false);
        button.setFont(GUI.font.deriveFont(24f));
        button.setForeground(new Color(252,161,54));
        return button;
    }
    
    class Listen implements MouseListener, KeyListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            if(me.getSource() == cancelar){
                gui.one = true;
                dispose();
            }else if(me.getSource() == agregar){
                JButton boton = (JButton)me.getSource();
                if(boton.getText() == "Agregar"){
                    getData(true);
                }else{
                    getData(false);
                }
                
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
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

        @Override
        public void keyTyped(KeyEvent ke) {
            char c = ke.getKeyChar();
            if (!(Character.isDigit(c)) && c != ' '){
                ke.consume();
            }
        }

        @Override
        public void keyPressed(KeyEvent ke) {
        }

        @Override
        public void keyReleased(KeyEvent ke) {
        }
        
    }
}
