package com.capstone.WekaDBFilePackage;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.experiment.DatabaseUtils;
/** This class connects to the database. This facilitates the registration of files that were made and place them into the database. 
 * The registration of data into the database is not necessary as the Weka API could be used with data sets stored in files. 
 * However, the database is used to allow for a more dynamic design. 
 * @author Vithia An
 * @version 13
 * */

public class InstancesFromDatabase 
{
	//method to get instances from database
	//@ return instances 
	public static Instances getInstanceDataFromDatabase(String pSql)
	{
	try 
	{	InstanceQuery query = new InstanceQuery();
		query.setUsername("root");
		query.setPassword("");
		query.setQuery(pSql);
		
		Instances data = query.retrieveInstances();

		if (data.classIndex() == -1)
		{	data.setClassIndex(data.numAttributes() - 1);
		}
		return data;
		} catch (Exception e) {
		throw new RuntimeException(e);
		}
	}
}
