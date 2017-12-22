package com.capstone.CheckThisFile;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.capstone.WekaDBFilePackage.Evaluate;
/**Makes the JTable and cells to be colored for Decision Tree. If a cell is colored green, the test is correct. 
 * Red cells indicate otherwise
 * @author Vithia An
 * @version 13
 * */

public class PrepareTableTree extends JPanel{
	
	ArrayList<String> arrayTree;
	ArrayList<String> arrayInterpretTree;
	
	JTable table;
	DefaultTableModel model ;
	private int WIDTH =500;
	private int HEIGHT=190;
	Evaluate eval = new Evaluate();
	CompareTwoArrays compare = new CompareTwoArrays();
	
	public PrepareTableTree(){
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[]{"Diagnosis", "Interpretation"});
		listToTableNumbersTree();
		fillColor();
		table.setPreferredScrollableViewportSize( new Dimension(WIDTH,HEIGHT) );
	    add(new JScrollPane(table));
	    setVisible(true);
	}
	
	//make tree array list
	public void makeArrayListTree(){
	
		arrayTree= new ArrayList<String>();		//numbers stored in list
		arrayInterpretTree= new ArrayList<String>();
	
		for (int i =0; i<CompareTwoArrays.compareArraysNumbersTree().size(); i++){
			arrayTree.add(CompareTwoArrays.compareArraysNumbersTree().get(i) );	
			arrayInterpretTree.add(CompareTwoArrays.compareArraysTree().get(i) );
			
		}
		
	}
	
	//method to populate the JTable
	public DefaultTableModel listToTableNumbersTree(){
		for(int i =0; i<arrayTree.size(); i++ ){
			model.addRow(new String[] {arrayTree.get(i), arrayInterpretTree.get(i) });	
		}//for	
		model.addRow(new String[]{"The accuracy rate is " + 
				compare.getAccuracyTree()  + " %"});
		return model;
	}

	//method to add color to cells in the JTable
	public void fillColor(){
		 table = new JTable(model) {
	     @Override
	     public Component prepareRenderer(TableCellRenderer renderer, 
	    		 int rowIndex, int columnIndex) {
	            JComponent component = (JComponent) super.prepareRenderer(
	            		renderer, rowIndex, columnIndex);  
	            //results match, 1, 1
	            if(getValueAt(rowIndex, 0).toString().equalsIgnoreCase("1")) {
	                component.setBackground(Color.GREEN); 
	            }  
	            //results match, 2, 2
	            else if(getValueAt(rowIndex, 0).toString().equalsIgnoreCase("2")) {
	                component.setBackground(Color.GREEN);
	            }
	            //results do not match, 1, 2 or 2, 1
	            else{
	                component.setBackground(Color.RED);
	            }
	            return component;
	        }
	    };
	}//method
}
