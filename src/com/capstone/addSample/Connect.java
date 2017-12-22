package com.capstone.addSample;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.capstone.JTablePackage.DisplayJTable;
import com.capstone.JTablePackage.TableTest;
/**This class makes connection to JDBC. 
 * It performs insertion using prepared statements.
 * @author Vithia An
 * @version 13
 */
public class Connect {

    private static final String URL = "jdbc:mysql://localhost:3306/diabetes";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private Connection connection = null;
    private PreparedStatement insertNewPerson = null;
    TableTest tableTest;
    //constructor
    public Connect() {
  
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            insertNewPerson = connection.prepareStatement("INSERT INTO PersonTest " +
                    "(agefirsthaddiabetes, takeinsulinnow, smokeatleast100ciginlife, agestartedsmokingcigregularly, questionnairemodeflag, doctortoldyouhavediabetes)" +
                    "VALUES (?,?,?,?,?,?)");     
        }// try
        catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(1);
        }// catch
    }//constructor
 
    //method to add sample
    //@ param the new sample to be added
    //@ return the sample
    public int addSample(SampleForDiabetes newSample)
    {
    		int result = 0;
    		
        try {
            insertNewPerson.setInt(1, newSample.getAge());
            insertNewPerson.setString(2, newSample.getTakeInsulin());
            insertNewPerson.setString(3, newSample.getSmokeCig());
            insertNewPerson.setInt(4, newSample.getAgeStart());
            insertNewPerson.setString(5, newSample.getQuestionFlag());
            insertNewPerson.setString(6, newSample.getDoctorTold());
            
            result = insertNewPerson.executeUpdate();
          

        }//try
        catch (SQLException sqle) {
            sqle.printStackTrace();
            close();
        }
        return result;
    }  
    
    //method to close connection
    public void close(){
        try{
            connection.close();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

}