package com.capstone.WekaDBFilePackage;

import com.capstone.JTablePackage.DisplayJTable;
import com.capstone.addSample.DisplayAddSample;
import com.capstone.CheckThisFile.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
/** The class containing the main() method that calls the UI class 
 * @author Vithia An
 * @version 13
 * */

public class Driver15 {
 
    public static void main(String[] args) {	

    		new DisplayJTable();
       	
    		//Evaluate e = new Evaluate();
       	//CompareTwoArrays c = new CompareTwoArrays();
    		Predict p = new Predict();
       	//System.out.println( e.evaluateSVM());
       	
       	//e.evaluateTree();
       	//c.compareArraysNumbersTree();
       	//System.out.println( e.evaluateNN());
    		//System.out.println( p.predictSVM( ));
    		//System.out.print( p.predictNN( ));
    		//System.out.print( p.predictDTree( ));    		
    
    }
}