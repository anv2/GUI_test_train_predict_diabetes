package com.capstone.JTablePackage;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
/*JTable for data used to perform testing */
import javax.swing.border.TitledBorder;
/** This class prepares the JTable that displays the data set used for testing.
 * @author Vithia An
 * @version 13
 * */

public class TableTest extends JPanel{
	
	private final int WIDTH = 500;
	private final int HEIGHT = 400;
	TableQuery tablequery;
	
	Object[][] data;
	Object[] columnNames;
	
	//constructor 
	public TableTest(){

		makeJTableTest();
		JTable table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);	
		add(scrollPane, BorderLayout.CENTER);
		setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                    "Testing dataset",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));
		table.setPreferredScrollableViewportSize( new Dimension(WIDTH,HEIGHT) );
		setVisible(true);

	}
	
	//method to make the JTable that holds data used for testing
	public void makeJTableTest(){
		String userStatement = "SELECT * From PersonTest";
		//make table query object 
		tablequery = new TableQuery(userStatement);
		//get column names
		columnNames = tablequery.getColumnNames();
		//get data
		data = tablequery.getTableData();
		//display results

	}
}
