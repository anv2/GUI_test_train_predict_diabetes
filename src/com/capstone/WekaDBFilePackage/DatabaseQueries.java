package com.capstone.WekaDBFilePackage;

/**This class queries the database for the entity fields.
 * * @author Vithia An
 * @version 13
 * */

public class DatabaseQueries {
	
	String pSQL = "SELECT * FROM Person LIMIT 20";		//query
	int ageHadDiabetes;									//age first had diabetes
	String takeInsulin;									//takes insulin
	String smoke100cig;									//smokes 100 cigarettes 
	int ageStartedSmoking;								//age started smoking
	String questionnaire;								//location where survey was taken
	String doctorToldYou;								//doctor said you had diabetes		
	String WEKALIST_QUESTION1 = "";						//query selection 

	//method to declare query
	//@ return the query
	public String WEKALIST_QUESTION1()
	{
		WEKALIST_QUESTION1 = ageHadDiabetes + 
			takeInsulin + 
			smoke100cig +
			ageStartedSmoking +
			questionnaire +
			doctorToldYou;
		return WEKALIST_QUESTION1;
	}
}
