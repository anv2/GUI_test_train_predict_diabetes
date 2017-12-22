package com.capstone.CheckThisFile;

import com.capstone.WekaDBFilePackage.Evaluate;
import com.capstone.CheckThisFile.ReadFile;

import java.awt.Color;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;
/**takes array className from the class Evaluate.java 
 * to compare to arrays from ReadFile.java class
 * @author Vithia An
 * @version 13
 * */

public class CompareTwoArrays {	

	static ReadFile readFile = new ReadFile();	
	static Evaluate evaluate = new Evaluate();
	static ArrayList<String> key ;				//holds answerKey ArrayList from ReadFile.java class
	static ArrayList<String> evalValueTree;		//holds predicted values from Evaluate.java class for Decision Tree
	static ArrayList<String> evalValueSVM;		//holds predicted values from Evaluate.java class for SVM
	static ArrayList<String> evalValueNN;		//holds predicted values from Evaluate.java class for NN
	static String interpretation;
	static ArrayList<String> carryInterpretation;
	private double countCorrect;					//keeps tab of the number of predictions made correctly
	private double countIncorrect;				//keeps tab of the number of predictions made INcorrectly
	private String accuracy;						//measures number of correct and incorrect predictions

	
	private static String diagnosis;				//value of arraylist that is returned when two arrays are compared. 
	static ArrayList<String> arrayDiagnosis;
	DecimalFormat numberFormat = new DecimalFormat("#.00");
		

	//method to get accuracy for SVM
	public String getAccuracySVM(){
		countCorrect=0; 
		countIncorrect=0; 
		accuracy = "";
		
		key = readFile.read();	
		evalValueSVM = evaluate.evaluateSVM();
		
		for(int i=0; i < key.size(); i++){
			//Both elements equal. Therefore, correct 
			if(key.get(i).equals( evalValueSVM.get(i)) )
			{	
				countCorrect++;
			}
			//elements NOT equal, not correct 
			else{
				countIncorrect++;
			}//else	
		}//for	
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		accuracy = numberFormat.format(countCorrect/ (countIncorrect+ countCorrect)*100);
		return accuracy;
	}//method
	
	//method to get accuracy for neural network
	public String getAccuracyNN(){
		countCorrect=0; 
		countIncorrect=0; 
		accuracy = "";
		key = readFile.read();	
		evalValueNN = evaluate.evaluateNN();
		
		for(int i=0; i < key.size(); i++){
			//Both elements equal. Therefore, correct. 
			if(key.get(i).equals( evalValueNN.get(i)) )
			{	
				countCorrect++;
			}
			//elements NOT equal, not correct 
			else{
				countIncorrect++;
			}//else	
		}//for	
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		accuracy = numberFormat.format(countCorrect/ (countIncorrect+ countCorrect)*100);
		return accuracy;
	}//method
	
	//method to get accuracy for decision tree
	public String getAccuracyTree(){
		
		countCorrect=0; 
		countIncorrect=0; 
		accuracy = "";
		key = readFile.read();	
		evalValueSVM = evaluate.evaluateTree();
		for(int i=0; i < key.size(); i++){
			//both elements equal, correct 
			if(key.get(i).equals( evalValueTree.get(i)) )
			{	
				countCorrect++;
			}
			//elements NOT equal, not correct 
			else{
				countIncorrect++;
			}//else	
		}//for	
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		accuracy = numberFormat.format(countCorrect/ (countIncorrect+ countCorrect)*100);
		return accuracy;
	}//method

	//method to compare two arrays for SVM
	public static ArrayList<String> compareArraysSVM(){
		interpretation = "";
		carryInterpretation = new ArrayList<String>();
		key = readFile.read();	
		evalValueSVM = evaluate.evaluateSVM();
		for(int i=0; i < key.size(); i++){

				if(key.get(i).equals( evalValueSVM.get(i)) )
				{	//if both elements are equal, one element is 1, yes diabetes
					if(key.get(i).equals("1")){
						interpretation = "Person" + Integer.toString(i+1) + " has diabetes\n";
					}
					//if both elements are equal, one element is 2, no diabetes
					else if (key.get(i).equals("2")){
						interpretation = "Person " + Integer.toString(i+1) + " does not have diabetes\n";
					}
				}
				else{
					interpretation = "Test is inconclusive\n";
				}//else
				carryInterpretation.add(interpretation);
			}//for
		return carryInterpretation;
	}//method
	
	//method to compare two arrays for SVM
	public static ArrayList<String> compareArraysNumbersSVM(){
		diagnosis = "";
		arrayDiagnosis = new ArrayList<String>();
		
		key = readFile.read();	
		evalValueSVM = evaluate.evaluateSVM();	

		for(int i=0; i < key.size(); i++){
			//if both elements are equal
			if(key.get(i).equals( evalValueSVM.get(i)) )
			{	// if one element is 1, yes diabetes
				if(key.get(i).equals("1")){
					diagnosis = "1";
				}
				//if both are 2, don't have diabetes
				else if (key.get(i).equals("2")){
					diagnosis = "2";		
				}
			}//outer if	
				//two numbers not equal. 
			else	{ diagnosis = "0";
				
			}
			arrayDiagnosis.add(diagnosis);
		}//for
		System.out.println("The size of the key is" + key.size() );
		System.out.println("The element in key is" + key);
		
		return arrayDiagnosis;

	}//method
	
	//method to compare two arrays for decision tree
		public static ArrayList<String> compareArraysTree(){
			interpretation = "";
			carryInterpretation = new ArrayList<String>();
			key = readFile.read();	
			evalValueTree = evaluate.evaluateTree();
			for(int i=0; i < key.size(); i++){

					if(key.get(i).equals( evalValueTree.get(i)) )
					{	//if both elements are equal, one element is 1, yes diabetes
						if(key.get(i).equals("1")){
							interpretation = "Person" + Integer.toString(i+1) + " has diabetes\n";
						}
						//if both elements are equal, one element is 2, no diabetes
						else if (key.get(i).equals("2")){
							interpretation = "Person " + Integer.toString(i+1) + " does not have diabetes\n";
						}
					}
					else{
						interpretation = "Test is inconclusive\n";
					}//else
					carryInterpretation.add(interpretation);
				}//for
			return carryInterpretation;
		}//method
		
		//method to compare two arrays for Tree
		public static ArrayList<String> compareArraysNumbersTree(){
			diagnosis = "";
			arrayDiagnosis = new ArrayList<String>();
			
			key = readFile.read();	
			evalValueTree = evaluate.evaluateTree();	

			for(int i=0; i < key.size(); i++){
				//if both elements are equal
				if(key.get(i).equals( evalValueTree.get(i)) )
				{	// if one element is 1, yes diabetes
					if(key.get(i).equals("1")){
						diagnosis = "1";
					}
					//if both are 2, don't have diabetes
					else if (key.get(i).equals("2")){
						diagnosis = "2";		
					}
				}//outer if	
					//two numbers not equal. 
				else	{ diagnosis = "0";
					
				}
				arrayDiagnosis.add(diagnosis);
			}//for
			return arrayDiagnosis;

		}//method
	
	//method to compare two arrays for neural network
	public static ArrayList<String> compareArraysNumbersNN(){
		diagnosis = "";
		arrayDiagnosis = new ArrayList<String>();
		
		key = readFile.read();	
		evalValueNN = evaluate.evaluateNN();	

		for(int i=0; i < key.size(); i++){
			//if both elements are equal
			if(key.get(i).equals( evalValueNN.get(i)) )
			{	// if one element is 1, yes diabetes
				if(key.get(i).equals("1")){
					diagnosis = "1";
				}
				//if both are 2, don't have diabetes
				else if (key.get(i).equals("2")){
					diagnosis = "2";		
				}
			}//outer if	
				//two numbers not equal. 
			else	{ diagnosis = "0";
				
			}
			arrayDiagnosis.add(diagnosis);
		}//for
		return arrayDiagnosis;
	}//method
	//method to compare two arrays for neural network
	public static ArrayList<String> compareArraysNN(){
		interpretation = "";
		carryInterpretation = new ArrayList<String>();
		key = readFile.read();	
		evalValueNN = evaluate.evaluateNN();
		for(int i=0; i < key.size(); i++){

				if(key.get(i).equals( evalValueNN.get(i)) )
				{	//if both elements are equal, one element is 1, yes diabetes
					if(key.get(i).equals("1")){
						interpretation = "Person" + Integer.toString(i+1) + " has diabetes\n";
					}
					//if both elements are equal, one element is 2, no diabetes
					else if (key.get(i).equals("2")){
						interpretation = "Person " + Integer.toString(i+1) + " does not have diabetes\n";
					}
				}
				else{
					interpretation = "Test is inconclusive\n";
				}//else
				carryInterpretation.add(interpretation);
			}//for
		return carryInterpretation;
	}//method
	
	
}//end class
