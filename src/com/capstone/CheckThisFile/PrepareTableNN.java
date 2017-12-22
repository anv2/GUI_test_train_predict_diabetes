package com.capstone.CheckThisFile;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.capstone.WekaDBFilePackage.Evaluate;
/**Makes the JTable and cells to be colored for Neural Network. If a cell is colored green, the test is correct. 
 * Red cells indicate incorrect results
 * @author Vithia An
 * @version 13
 * */

public class PrepareTableNN extends JPanel {

	ArrayList<String> arrayNN;				//holds the results from test when neural network is applied			
	ArrayList<String> arrayInterpretNN;		//holds interpretation of results
	
	JTable table;
	DefaultTableModel model ;
	private int WIDTH =500;
	private int HEIGHT=190;
	Evaluate eval = new Evaluate();
	CompareTwoArrays compare = new CompareTwoArrays();
	
	//constructor
	public PrepareTableNN(){
		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[]{"Diagnosis", "Interpretation"});
		makeArrayListNN();
		listToTableNumbersNN();
		fillColor();
		table.setPreferredScrollableViewportSize( new Dimension(WIDTH,HEIGHT) );
	    add(new JScrollPane(table));
	    setVisible(true);
	}
	
	//method to make NN array list
	public void makeArrayListNN(){
		
		arrayNN= new ArrayList<String>();		//numbers stored in list
		arrayInterpretNN= new ArrayList<String>();
		
		for (int i =0; i<CompareTwoArrays.compareArraysNumbersNN().size(); i++){
			arrayNN.add(CompareTwoArrays.compareArraysNumbersNN().get(i) );	
			arrayInterpretNN.add(CompareTwoArrays.compareArraysNN().get(i) );
		}
	}

	//method to populate the JTable
	public DefaultTableModel listToTableNumbersNN(){
		compare= new CompareTwoArrays();
		for(int i =0; i<arrayNN.size(); i++ ){
			model.addRow(new String[] {arrayNN.get(i), arrayInterpretNN.get(i) });	
	
		}//for	
		model.addRow(new String[]{"The accuracy rate is " + 
				compare.getAccuracyNN() + " %"});
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
