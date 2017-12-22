package com.capstone.CheckThisFile;

import java.io.BufferedReader; 
import java.io.FileReader;
import com.capstone.addSample.*; 
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.IllegalStateException;
import java.io.IOException;

import com.capstone.addSample.SampleForDiabetes;
/**Class to read a file into an array list. 
 * File that is read in this class contains the answer key for the test value to be compared against. 
 * @author Vithia An
 * @version 13
 * */

public class ReadFile {
	private BufferedReader input;
	ArrayList<String> answerKey ;
	
	//constructor 
	public ReadFile()
	{	answerKey = new ArrayList<String>();
		openFile();
		read();
	}
	
	//method to open file
	public void openFile()
	{
		try{
			input = new BufferedReader(new FileReader("/Users/vithia/Documents/workspace/WekaDBTestTrain/checkForAccuracy"));
		}
		catch(FileNotFoundException fileNotFoundException){
			System.err.println("Cannot open file");
			System.exit(1);
		}	
	}
	
	//method to read the file
	public ArrayList<String> read(){
		String s;
		try{
			while((s = input.readLine()) != null)
			{	answerKey.add(s);
			}
			
		}
		catch(NoSuchElementException noSuchElementException){
			System.err.println("Cannot read from file");
			closeFile();
			System.exit(1);
		}
		
		catch(IOException ioException)
		{
			ioException.printStackTrace();
		}
		
		catch(IllegalStateException illegalStateException){
			System.out.println("Cannot read from file");
			System.exit(1);
		}
		return answerKey;	
	}	
	//method to close the file
		public void closeFile()
		{
			try{
			if(input != null){
				input.close();
			}
			}
			catch(IOException ioException)
			{
				ioException.printStackTrace();
			}
		}
		//getter for answers 
		//@ return the answer key
		public ArrayList<String> getAnswerKey(){
			return answerKey;
		}
}


