package com.capstone.WekaDBFilePackage;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 

import weka.experiment.InstanceQuery;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.classifiers.trees.J48;
import weka.classifiers.functions.SMO;

import java.util.ArrayList;
import java.util.Random;
import weka.classifiers.Evaluation;
/**Evaluate the data sets from training and testing data sets stored in separate files.
 * * @author Vithia An 
 * @version 13
 * */

public class Evaluate {

	ArrayList<String> carrierClassName = new ArrayList<String>();
	String className;
	
	public ArrayList<String> evaluateTree()
	{	
		try
		{
		DataSource dataTrain = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
	    Instances dataset = dataTrain.getDataSet();
          		
        //set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes()-1);
        
        	J48 tree = new J48();
        tree.buildClassifier(dataset);
        
        Evaluation eval = new Evaluation(dataset);
        
        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
        Instances testDataset = dataTest.getDataSet();
        //set class index to the last attribute
        testDataset.setClassIndex(testDataset.numAttributes()-1);
 
        eval.evaluateModel(tree,testDataset);
        int lastIndex = dataset.numAttributes() - 1;
        for(int i=0; i<testDataset.numInstances(); i++) {

            double index = tree.classifyInstance(testDataset.instance(i));
             className = dataset.attribute(lastIndex).value((int)index);
             carrierClassName.add(className);
        }
      
		}//try
		catch(ClassNotFoundException classException)
		{	classException.printStackTrace();
		}
		catch(SQLException sqlException)
		{	sqlException.printStackTrace();
		}
		catch(Exception exception)
		{	exception.printStackTrace();
		}
		return(carrierClassName);
	}//tree
	
	public ArrayList<String> evaluateSVM()
	{	
		try
		{
			
		DataSource dataTrain = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
	    Instances dataset = dataTrain.getDataSet();    		
        //set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes()-1);
           
        SMO svm = new SMO();
        svm.buildClassifier(dataset);
        
        Evaluation eval = new Evaluation(dataset);

        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
        Instances testDataset = dataTest.getDataSet();
        //set class index to the last attribute
        testDataset.setClassIndex(testDataset.numAttributes()-1);

        eval.evaluateModel(svm,testDataset);
        
        int lastIndex = dataset.numAttributes() - 1;
        for(int i=0; i<testDataset.numInstances(); i++) {

            double index = svm.classifyInstance(testDataset.instance(i));
             className = dataset.attribute(lastIndex).value((int)index);
             carrierClassName.add(className);
        }
      
		}//try
		catch(ClassNotFoundException classException)
		{	classException.printStackTrace();
		}
		catch(SQLException sqlException)
		{	sqlException.printStackTrace();
		}
		catch(Exception exception)
		{	exception.printStackTrace();
		}
		return(carrierClassName);
	}//svm
	
	public ArrayList<String> evaluateNN()
	{	
		try
		{
		DataSource dataTrain = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
	    Instances dataset = dataTrain.getDataSet();
          		
        //set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes()-1);        

        MultilayerPerceptron mlp = new MultilayerPerceptron();
        mlp.buildClassifier(dataset);
        
        Evaluation eval = new Evaluation(dataset);
                      
        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
        Instances testDataset = dataTest.getDataSet();
        //set class index to the last attribute
        testDataset.setClassIndex(testDataset.numAttributes()-1);

        eval.evaluateModel(mlp,dataset);
        
        int lastIndex = dataset.numAttributes() - 1;
        for(int i=0; i<testDataset.numInstances(); i++) {

            double index = mlp.classifyInstance(testDataset.instance(i));
             className = dataset.attribute(lastIndex).value((int)index);
             carrierClassName.add(className);
        }
      
		}//try
		catch(ClassNotFoundException classException)
		{	classException.printStackTrace();
		}
		catch(SQLException sqlException)
		{	sqlException.printStackTrace();
		}
		catch(Exception exception)
		{	exception.printStackTrace();
		}
		return(carrierClassName);
	}
}