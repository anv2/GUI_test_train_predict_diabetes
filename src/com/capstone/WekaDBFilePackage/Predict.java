package com.capstone.WekaDBFilePackage;

import java.sql.SQLException;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * This class makes prediction of the sample data and is called when the user
 * presses on the button to predict. Therefore, methods from this class is
 * called in the UI class that compiles all logic (i.e. DisplayJTable class)
 * 
 * @author Vithia An
 * @version 13
 */

public class Predict {
	String predictedString;
	String actual;
	// Method to make prediction using SVM model
	// @return the predicted value
	public String predictSVM() {
		try{
			DataSource dataTrain = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
		    Instances dataset = dataTrain.getDataSet();    		
	        
		    //set class index to the last attribute
	        dataset.setClassIndex(dataset.numAttributes()-1);
	          
	        //build classifier
	        SMO svm = new SMO();
	        svm.buildClassifier(dataset);
	        
	        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
	        Instances testDataset = dataTest.getDataSet();
	        
	        //set class index to the last attribute
	        testDataset.setClassIndex(testDataset.numAttributes()-1);

	        	Instance newInstance = testDataset.instance(testDataset.numInstances()-1 ); 
	        	double predicted = svm.classifyInstance(newInstance);
	        	predictedString = testDataset.classAttribute().value((int) predicted);

		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return predictedString;
	}

	// Method to make prediction using neural network model
	// @return the predicted value
	public String predictNN() {
		try {
			DataSource dataTrain = new DataSource(
					"/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
			Instances dataset = dataTrain.getDataSet();
			// set class index to the last attribute
			dataset.setClassIndex(dataset.numAttributes() - 1);

			MultilayerPerceptron mlp = new MultilayerPerceptron();
			mlp.buildClassifier(dataset);

	        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
	        Instances testDataset = dataTest.getDataSet();
	        
	        //set class index to the last attribute
	        testDataset.setClassIndex(testDataset.numAttributes()-1);

	        	Instance newInstance = testDataset.instance(testDataset.numInstances()-1 ); 
	        	double predicted = mlp.classifyInstance(newInstance);
	        	predictedString = testDataset.classAttribute().value((int) predicted);
			
		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return predictedString;
	}

	// Method to make prediction using decision tree model
	// @return the predicted value
	public String predictDTree() {
		try {
			DataSource dataTrain = new DataSource(
					"/Users/vithia/Documents/workspace/WekaDBTestTrain/combined.arff");
			Instances dataset = dataTrain.getDataSet();
			// set class index to the last attribute
			dataset.setClassIndex(dataset.numAttributes() - 1);

			J48 tree = new J48();
			tree.buildClassifier(dataset);

	        DataSource dataTest = new DataSource("/Users/vithia/Documents/workspace/WekaDBTestTrain/combinedTest.arff");
	        Instances testDataset = dataTest.getDataSet();
	        
	        //set class index to the last attribute
	        testDataset.setClassIndex(testDataset.numAttributes()-1);

	        	Instance newInstance = testDataset.instance(testDataset.numInstances()-1 ); 
	        	double predicted = tree.classifyInstance(newInstance);
	        	predictedString = testDataset.classAttribute().value((int) predicted);
	        	
		} catch (ClassNotFoundException classException) {
			classException.printStackTrace();
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return predictedString;
	}
}
