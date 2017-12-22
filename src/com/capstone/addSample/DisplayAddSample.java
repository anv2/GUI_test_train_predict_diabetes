package com.capstone.addSample;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.capstone.JTablePackage.DisplayJTable;
import com.capstone.JTablePackage.TableTest;
/** Swing class that allows sample to be added 
 * @author Vithia An
 * @version 13
 */

public class DisplayAddSample extends JPanel {
    
    private Connect connect;					//connects to database
    private JRadioButton rbInsulinYes;		//button for insulin taken
    private JRadioButton rbInsulinNo; 		//button for insulin not taken 
    private JRadioButton rbSmokeYes;			//button for smoking
    private JRadioButton rbSmokeNo; 			//button indicating that patient does not smoke
    private JRadioButton rbFlagYes;			//button indicating location of survey
    private JRadioButton rbFlagNo;			//button indicating location of survey
    
    private ButtonGroup btnGroupInsulin;		//to group buttons for insulin
    private ButtonGroup btnGroupSmoke;		//to group buttons for smoking
    private ButtonGroup btnGroupFlag;		//to group buttons for location of survey

    private JLabel ageFirstLabel;			//label indicating age first had diabetes
    private JLabel takeInsulinLabel;			//label indicating insulin taken
    private JLabel smoke100Label;			//label indicating smoking
    private JLabel ageStartedLabel;			//label indicating age started smoking
    private JLabel questionnaireLabel;		//label indicating location of survey
    private JTextField ageFirst_TextField;	//text field for age when diabetes was first diagnosed
    private JTextField ageStarted_TextField;	//text field for age started smoking

    JPanel row1; 
    JPanel row2; 
    JPanel row3;
    JPanel row4;
    JPanel row5;
    JPanel row6;
    JPanel row7;		//to hold insert button 
    
    GridLayout gridLayout;
    Border loweredetched;
    
    private final int WINDOW_WIDTH = 500;
    private final int WINDOW_LENGTH = 350;

    private JButton insertButton;
    TableTest tableTest;
    //constructor
    public DisplayAddSample()
    {	tableTest = new TableTest();
		setBorder (BorderFactory.createTitledBorder (BorderFactory.createEtchedBorder (),
                    "Add new sample to test",
                    TitledBorder.CENTER,
                    TitledBorder.TOP));
    		
    		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 
	
    		connect = new Connect();
        setSize(WINDOW_WIDTH, WINDOW_LENGTH);

        insertButton = new JButton("Add sample");
        rbInsulinYes = new JRadioButton("Yes");
        rbInsulinNo = new JRadioButton("No");
        rbSmokeYes = new JRadioButton("Yes");
        rbSmokeNo = new JRadioButton("No");
        rbFlagYes = new JRadioButton("at home"); 
        rbFlagNo = new JRadioButton("in office");

        btnGroupInsulin = new ButtonGroup();
        btnGroupSmoke = new ButtonGroup() ;
        btnGroupFlag= new ButtonGroup();
        
        btnGroupInsulin.add(rbInsulinYes );
        btnGroupInsulin.add(rbInsulinNo );
        btnGroupSmoke.add(rbSmokeYes);
        btnGroupSmoke.add(rbSmokeNo);
        btnGroupFlag.add(rbFlagYes);
        btnGroupFlag.add(rbFlagNo);
        
        setLayout(new GridLayout(7,2));
                
    		row1 = new JPanel();
        row2 = new JPanel();
        row3 = new JPanel();
        row4 = new JPanel();
        row5 = new JPanel();
        row6 = new JPanel();
        row7 = new JPanel();

    		ageFirstLabel = new JLabel("Age first had diabetes");
        takeInsulinLabel = new JLabel("Taking insulin now");
        smoke100Label = new JLabel("Smoke at least 100 cigarettes");
        ageStartedLabel = new JLabel("Age when first smoked");   
        questionnaireLabel = new JLabel("Where did you take the survey");

        ageFirst_TextField = new JTextField(4);
        ageStarted_TextField = new JTextField(4);
 
        row1.add(ageFirstLabel);
        row2.add(takeInsulinLabel);
        row3.add(smoke100Label);
        row4.add(ageStartedLabel);
        row5.add(questionnaireLabel);
 
        row1.add(ageFirst_TextField);
        row4.add(ageStarted_TextField);
        
        row2.add(rbInsulinYes);
        row2.add(rbInsulinNo);   
        row3.add(rbSmokeYes);
        row3.add(rbSmokeNo); 
        row5.add(rbFlagYes);
        row5.add(rbFlagNo);
        row7.add(insertButton);
        
        add(row1);
        add(row2);
        add(row3);
        add(row4);
        add(row5);
        add(row6);
        add(row7);
        
        row1.setBorder(loweredetched);
        row2.setBorder(loweredetched);
        row3.setBorder(loweredetched);
        row4.setBorder(loweredetched);
        row5.setBorder(loweredetched);
        row7.setBorder(loweredetched);
        
        
        insertButton.setText("Add this sample");
        makeInsertButton(); 
        
		setVisible(true);
    }
    //method to add insert button

    private void makeInsertButton()
    {
        insertButton.setText("Add this sample");
        insertButton.addActionListener(
        		new ActionListener()
            {	public void actionPerformed(ActionEvent e){
                        insertButtonActionPerformed(e);
                    }
                });
     	
    }  
    //action listener for the insert button
    private void insertButtonActionPerformed(ActionEvent e) 
    {
    		SampleForDiabetes sample;
    		try
    		{	sample = new SampleForDiabetes(Integer.parseInt(ageFirst_TextField.getText()),
    						getYesTo2(),
    						getYesTo2(),
                        Integer.parseInt(ageStarted_TextField.getText()),
                        getYesTo2(),
                        "?"
                        );           
    			int key = connect.addSample(sample);		
    			if(key == 1)
    			{
    				JOptionPane.showMessageDialog(this, "Person added", "person added", JOptionPane.PLAIN_MESSAGE);
    				new DisplayJTable();
    				revalidate();
    				repaint();
    			}
    			else
    			{	
    				JOptionPane.showMessageDialog(this, "Person not added", "Error", JOptionPane.PLAIN_MESSAGE);
    			}
    			

    		}
    		catch (IllegalArgumentException exception)
    		{
    			JOptionPane.showMessageDialog(this, "Invalid Sample", "Error", JOptionPane.PLAIN_MESSAGE);
    		}
    }
    
    //coded positive as 2
    public String getYesTo2()
    {	String bubble = "";
	    	if(rbInsulinYes.isSelected() || 
	    			rbSmokeYes.isSelected() || 
	    			rbFlagYes.isSelected() )
	    		bubble = "2";
	    	else{
	    		bubble = "1";
	    	}
	    	return bubble;
    }
}