/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aplicacion_hogwarts;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 * @author Ingrid Echeverri ()
 * @version
 * Description....
 */
public class Tabla extends JTable{
    int rows, columns;
    public static Font font = Aplicacion_Hogwarts.loadFont("font/EnchantedLand.ttf");
    public Tabla(int rows, int columns) {
	super(new DefaultTableModel(rows, columns));
	this.rows = rows;
	this.columns = columns;
	this.setForeground(Color.RED);
	this.setFont(font.deriveFont(40f));
    }
    public Tabla(Object[][] rowData,Object[] columnNames){
        super(new DefaultTableModel(rowData, columnNames));
        this.rows = rowData.length;
	this.columns = rowData[1].length;
	this.setForeground(Color.decode("#492D0E"));
	this.setFont(font.deriveFont(40f));
        setCells();
    }
    private void setCells(){
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();
	cellRender.setHorizontalAlignment(JLabel.CENTER);
        //cellRender.setOpaque(false);
	this.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
	for(int i=0; i < this.columns; i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(cellRender);
	}
    }
   
}
