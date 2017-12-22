package com.capstone.addSample;

import javax.swing.JPanel;
/**Entity class for the sample 
 * @author Vithia An
 * @version 13
 * */

public class SampleForDiabetes extends JPanel{
    private int ageFirst;
    private String takeInsulin;
    private String smokeCig;
    private int ageStart;
    private String questionFlag;
    private String doctorTold;

    //constructor
    public SampleForDiabetes(int ageF, String insulin, String smoke, int age, String flag, String told) throws IllegalArgumentException
    {	
    		if(ageF >=0 && age >= 0 )
    		{
	    		ageFirst = ageF;
	    		takeInsulin = insulin;
	    		smokeCig = smoke;
	    		ageStart = age;
	    		questionFlag = flag;		//whether the person fills out survey at home or in an office
	    		doctorTold = told;
    		}
    		else
    		{
    			throw new IllegalArgumentException();
    		}
    }
    
//getter methods
    //@ return age that the sample first had diabetes
    public int getAge(){
    		return ageFirst;
    }
    //@ return if the patient takes insulin
    public String getTakeInsulin(){
        return takeInsulin;
    }
    //@ return age the patient started smoking
    public String getSmokeCig(){
        return  smokeCig;
    }
    //@ return age of the patient when s/he started smoking
    public int getAgeStart(){
    		return ageStart;
    }
    //@ return location where the survey was conducted
    public String getQuestionFlag(){
        return questionFlag;
    }
    //@ return the doctor told you that you have diabetes
    public String getDoctorTold(){
        return "?";
    }
    
//setter methods
    //@ param age that the sample first had diabetes
    public void setAge(int age)
    {	ageFirst = age;
    }
    //@ param if the patient takes insulin
    public void setTakeInsulin(String insulin){
    		takeInsulin = insulin;
    } 
    //@ param if the patient smokes
    public void setSmokecig(String cig){
    		smokeCig = cig;
    }
    //@ param age the patient started smoking
    public void setAgeStart(int age){
    		ageStart = age;
    }
    //@ param the location where the survey was conducted
    public void setQuestionFlag(String flag){
        questionFlag = flag;
    }
    //@ param the doctor said you had diabetes
    public void setDoctorTold(String told){
    		doctorTold = told;
    }
}
