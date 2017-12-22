package com.capstone.JTablePackage;

import javax.swing.*;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;

//import com.capstone.ResultsDisplayPackage.DisplayResults;
import com.capstone.WekaDBFilePackage.Evaluate;
import com.capstone.WekaDBFilePackage.MakeFile;
import com.capstone.WekaDBFilePackage.Predict;
import com.capstone.addSample.Connect;
import com.capstone.addSample.DisplayAddSample;
import com.capstone.CheckThisFile.CompareTwoArrays;
import com.capstone.CheckThisFile.*;

import java.awt.*;
import java.awt.event.*;
/**JFrame display.
 * This is a UI class that calls logic from other classes.
 * @author Vithia An
 * @version 13
 **/

public class DisplayJTable extends JFrame {
	DisplayAddSample displayAddSample;
	TableTrain tableTrain;
	TableTest tableTest;
	Evaluate eval;
	Predict predict;
	JPanel panelPredict;
	
	JPanel panelHoldJButtons;		//holds buttons
	JTextArea textAreaResult;		//holds area entry of query by user
	JButton buttonTrain;				//submits query to view train data set
	JButton buttonTest; 				//submits query to view test data set
	JButton buttonExit;				//exit 
	TableQuery tQuery;
	
	JPanel panelResultDisplay; 		//holds options for model selection (SVM NN tree)
	JRadioButton choiceSVM;			//to select SVM
	JRadioButton choiceNN;			//to select neural network
	JRadioButton choiceTree;			//to select decision tree
	ButtonGroup buttonGroup; 		//holds buttons for svm nn tree
	
	JPanel panelTrainTestData;		//holds datasets of training and testing samples
	
	JButton buttonEvaluate;
	JButton buttonpredict;
	
	MakeFile makeFile ;				//to make files for training and testing 
	CompareTwoArrays compareTwoArrays; //to call methods to compare results to the answer key
	
	JPanel panelTestTrainResButtons;	//panel to hold data sets for training, testing, results, and radio buttons
	JPanel panelTrainTest;			//holds train and test data sets
	JPanel panelRadioButtons;   		// holds radio buttons
	
	PrepareTableTree prepareTableTree;	//instantiation of classes
	PrepareTableSVM prepareTableSVM;
	PrepareTableNN prepareTableNN;
	
	JPanel panelNorth;
	JPanel panelTop;
	
	JPanel panelEquator;
	JPanel panelMiddle;

	JTextArea textAreaPredict;
	JButton buttonPredict;
	
	//constructor
	public DisplayJTable()
	{	
		predict = new Predict();
		
		
		setTitle("Displays results from machine learning algorithms");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	
		buildPanelButton();
		buildPanelSelection();	
		buildPanelTextArea();		//create panel to display results
		buildPanelAddSample();		//inserting a panel for add sample	
		buildPanelPredict();
		
		setLayout(new BorderLayout());
		panelNorth =  new JPanel(new BorderLayout());
		panelTop = new JPanel(new BorderLayout());
		
		panelNorth.add(new TableTrain(), BorderLayout.WEST);
		panelNorth.add(new TableTest(), BorderLayout.EAST);
		panelTop.add(panelNorth, BorderLayout.NORTH);
		add(panelTop, BorderLayout.NORTH);
		
		panelEquator =  new JPanel(new BorderLayout());
		panelMiddle = new JPanel(new BorderLayout());

		//panelEquator.add(displayAddSample, BorderLayout.WEST);
		panelEquator.add(new DisplayAddSample(), BorderLayout.WEST);
		panelEquator.add(panelPredict, BorderLayout.CENTER);
		
		panelMiddle.add(panelEquator, BorderLayout.CENTER);
		add(panelMiddle, BorderLayout.CENTER);

		add(panelRadioButtons, BorderLayout.SOUTH);		

		pack();	
		revalidate();
		repaint();
		setVisible(true);
	}
	
	
	//method to provide the option to add sample 
	private void buildPanelAddSample()
	{	displayAddSample = new DisplayAddSample();	
	}
	
	//method to make evaluate button listener
	private void buildPanelTextArea() 
	{	panelResultDisplay = new JPanel();
		buttonEvaluate.addActionListener(new buttonEvaluateListener());
		buttonpredict.addActionListener(new buttonPredictListener());
	}

	//method to build the panel that holds predict value
	private void buildPanelPredict()
	{	panelPredict = new JPanel();
		TitledBorder title;
		title = BorderFactory.createTitledBorder("Predicted value");
		title.setTitleJustification(TitledBorder.CENTER);
		panelPredict.setBorder(title);
		setVisible(true);
	}
	
	//action listener for the button used to make prediction
	private class buttonPredictListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(choiceTree.isSelected() )
			{	displayPredictTable();
				
				if(predict.predictDTree().equals("2")){
					textAreaPredict.setText("Does not have diabetes");
					
				}
				else{
					textAreaPredict.setText("Has diabetes");
				}
			}
			else if(choiceNN.isSelected() ){
				displayPredictTable();
				if(predict.predictNN().equals("2")){
					textAreaPredict.setText("Does not have diabetes");
				}
				else{
					textAreaPredict.setText("Has diabetes");
				}
			}
			else if (choiceSVM.isSelected()) {
				displayPredictTable();
				if(predict.predictSVM().equals("2")){
					textAreaPredict.setText("Does not have diabetes");	
				}
				else if (predict.predictSVM().equals("1")) {
					textAreaPredict.setText("Has diabetes");
				}
			}
		}
	}
	
	//make table to display prediction
	private void displayPredictTable(){
		textAreaPredict = new JTextArea(10,10);
		textAreaPredict.setEditable(false);
		textAreaPredict.setLineWrap(true);
		panelPredict.add(textAreaPredict);
		buildPanelPredict();
		revalidate();
		repaint();
		setVisible(true);
	}

	//action listener for the button to evaluate samples
	private class buttonEvaluateListener implements ActionListener{	
		public void actionPerformed(ActionEvent e)
		{	
			if(choiceTree.isSelected() )
			{	panelEquator.add(new PrepareTableTree(), BorderLayout.EAST);
				displayTable();
			}
			else if(choiceNN.isSelected() )
			{	panelEquator.add(new PrepareTableNN(), BorderLayout.EAST);
				displayTable();
			}
			else
			{	panelEquator.add(new PrepareTableSVM(), BorderLayout.EAST);
				displayTable();
			}
		}
	}
	
	//method to make table to display results
	private void displayTable(){
		panelEquator.add(displayAddSample, BorderLayout.WEST);
		panelMiddle.add(panelEquator, BorderLayout.CENTER);
		add(panelMiddle, BorderLayout.CENTER);
		add(panelRadioButtons, BorderLayout.SOUTH);			

		setVisible(true);
	}
	
	//method to build panel that holds options for users
	private void buildPanelSelection()
	{	panelRadioButtons = new JPanel();
		choiceSVM = new JRadioButton("use SVM", true);
		choiceNN = new JRadioButton("use Neural Network");
		choiceTree = new JRadioButton("use Decision Tree");		
		buttonEvaluate = new JButton("Evaluate");
		buttonpredict = new JButton("Predict");
		
		buttonGroup = new ButtonGroup();
	    buttonGroup.add(choiceSVM );  
	    buttonGroup.add(choiceNN );
	    buttonGroup.add(choiceTree );
		buttonGroup.add(buttonEvaluate);
		buttonGroup.add(buttonpredict);

		panelRadioButtons.add(choiceSVM);
		panelRadioButtons.add(choiceNN);
		panelRadioButtons.add(choiceTree);
		panelRadioButtons.add(buttonEvaluate);
		panelRadioButtons.add(buttonpredict);
		panelRadioButtons.add(buttonExit);
	}

	//method to build panel to hold buttons
	private void buildPanelButton()
	{	panelHoldJButtons = new JPanel();
		buttonExit = new JButton("Exit");
		buttonExit.addActionListener(new ExitButtonListener());	
	}
	
	//action listener for exit button
	private class ExitButtonListener implements ActionListener
	{	public void actionPerformed(ActionEvent e)
		{	//end program
			System.exit(0);
		}
	}
}
