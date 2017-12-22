package com.capstone.WekaDBFilePackage;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
/**This class takes files containing the header information and append them to files containing the data sets. 
 * The header is necessary because the Weka API needs header information in order to apply its algorithms
 * @author Vithia An
 * @version 13
 * */

public class MakeFile {

	//make combined training set
	public void makeTrainingSet()
	{
		//combine header and data for training
		Instances instances = UtilsWeka.getSampleInstances();
		DataSource source1 = new DataSource(instances);
	          
		String pSql = "SELECT * FROM Person LIMIT 20";     
	    Instances data2 = InstancesFromDatabase
	            .getInstanceDataFromDatabase(pSql);
	    UtilsWeka.instancesToFile(data2, "fromDatabase.arff");
	    DataSource source2 = new DataSource(data2);  
	    Instances i1;
	    Instances i2;
	    StringBuilder sb = new StringBuilder();

	    try {
	        i1 = source1.getStructure();
	        sb.append(i1);
	        i2 = source2.getStructure();
	        while (source2.hasMoreElements(i2)) {
	            String elementString = source2.nextElement(i2)
	                    .toString();
	            sb.append(elementString);
	            sb.append("\n");               
	        }

	    } catch (Exception ex) {
	        throw new RuntimeException(ex);
	    }

	    UtilsWeka.saveInstancesToFileSB(sb.toString(), "combined.arff");
	}
	
	//Method to combine header and data from database for testing  	
	public void makeTestingSet()
	{
    	Instances SampleInstanceTest = UtilsWeka.getSampleInstances();
    	DataSource source1Test = new DataSource(SampleInstanceTest);
		       
    	String pSqlTest = "SELECT * FROM PersonTest";     
		 Instances data2Test = InstancesFromDatabase
		         .getInstanceDataFromDatabase(pSqlTest);
		 UtilsWeka.instancesToFile(data2Test, "fromDatabaseTest.arff");
		
		 DataSource source2Test = new DataSource(data2Test);
		         
		 Instances i1Test;
		 Instances i2Test;
		 StringBuilder sbTest = new StringBuilder();
		
		 try {
		     i1Test = source1Test.getStructure();
		     sbTest.append(i1Test);
		     i2Test = source2Test.getStructure();
		     while (source2Test.hasMoreElements(i2Test)) {
		         String elementStringTest = source2Test.nextElement(i2Test)
		                 .toString();
		         sbTest.append(elementStringTest);
		         sbTest.append("\n");               
		     }
		
		 } catch (Exception ex) {
		     throw new RuntimeException(ex);
		 }
		UtilsWeka.saveInstancesToFileSB(sbTest.toString().replace('\'', ' '), "combinedTest.arff");
	}
}
