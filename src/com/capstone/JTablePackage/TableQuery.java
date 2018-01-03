package com.capstone.JTablePackage;
import java.sql.*;
import java.sql.SQLException;

import javax.swing.BorderFactory;
/**This class executes queries. 
 * It serves as a component of JTable that displays results of SVM, NN, and Decision Tree.
 * @author Vithia An
 * @version 13
 */

public class TableQuery {

	private static final String URL = "jdbc:mysql://localhost:portNumber/diabetes";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
	
	private Connection connection = null;	//connection to db
    private PreparedStatement selectPerson = null;
    
	private String[][] data;					//table data
	private String[] colNames;				//column name

	//constructor
	public TableQuery(String query)
	{
		getDBConnection();
		try
		{
			//make statement object for the query
			Statement statement = connection.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);		
			
			//execute query
			ResultSet resultSet = statement.executeQuery(query);
			
			//get number of rows
			resultSet.last(); 					//go to last row	
			int numRows = resultSet.getRow();	//get the row number
			resultSet.first();					//go to first row
			
			//retrieve metadata object for the result set
			ResultSetMetaData mData = resultSet.getMetaData();
			
			//creates array of strings to hold column names
			colNames= new String[mData.getColumnCount()];
			
			//Store names of columns in array
			for (int i=0; i<mData.getColumnCount(); i++)
			{	//get column name
				colNames[i] = mData.getColumnLabel(i+1);				
			}
			//create 2D string array to hold data as table
			data = new String[numRows][mData.getColumnCount()];
			
			//store columns in data array
			for(int row =0; row<numRows; row++)
			{
				for (int col = 0; col<mData.getColumnCount(); col++)
				{
					data[row][col]= resultSet.getString(col+1);	
					if (resultSet.getString(col+1).equals("2")){
					}
					if (resultSet.getString(col+1).equals("1")){
					}
				}
				//go to next row in resultset
				resultSet.next();
			}
			//close the statement and connection objects
			statement.close();
			connection.close();
		}
		catch(SQLException sqlException)
		{	sqlException.printStackTrace();
		}
	}
	//method to load the database and acquire connection
	private void getDBConnection()
	{
		try
		{	//connects to database
			connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			selectPerson = connection.prepareStatement("SELECT * FROM Person");
		}
		catch(Exception ex)
		{	ex.printStackTrace();
			System.exit(0);
		}
	}
	//method to get column names
	public String[] getColumnNames()
	{	return colNames;
	}
	//method to return data from table
	public String[][] getTableData()
	{	return data;
	}

}
