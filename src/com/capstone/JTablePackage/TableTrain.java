package com.capstone.JTablePackage;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
/** This class prepares the JTable that displays the data set used for training.
 * @author Vithia An
 * @version 13
 * */

public class TableTrain extends JPanel{
	private final int WIDTH = 500;
	private final int HEIGHT = 400;
	TableQuery tablequery;
	Object[][] data;
	Object[] columnNames;
	
	//constructor 
	public TableTrain(){

		makeJTableTrain();
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);	
		add(scrollPane, BorderLayout.CENTER);
		setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                "Training dataset",
                TitledBorder.CENTER,
                TitledBorder.TOP));
		table.setPreferredScrollableViewportSize( new Dimension(WIDTH,HEIGHT) );
		setVisible(true);
	}
	//holds training data 
	public TableTrain(Object[][] data, Object[] columnNames)
	{	
		JPanel panelHoldTable = new JPanel();
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);	
		panelHoldTable.add(scrollPane, BorderLayout.CENTER);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
	}
	
	//method to make the JTable that holds data used for training
	public void makeJTableTrain(){
		String userStatement = "SELECT * From Person";
		tablequery = new TableQuery(userStatement);
		columnNames = tablequery.getColumnNames();
		data = tablequery.getTableData();	
	}
}
