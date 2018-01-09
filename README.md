README
## Health Predictions:
## Using multi-tiered application to train and visualize data

Contents</br>
Introduction</br>
Downloads</br>
Documentation</br>
Notes</br>
Feedback</br>
Contents of the Application</br>
Distribution</br>

### Introduction</br>
This application was developed with Java 8 programming features and MySQL database. It presents a graphic user interface (GUI) using Java swing components and accepts user input. The input selects an algorithm to be used on data borrowed from the Centers for Disease Control and Prevention (CDC). Users may select from the support vector machine (SVM), Neural Network, or Decision Tree. The algorithms are borrowed from the Weka API. Users are able to train, test, and predict which patients are at risk for diabetes. Correct results from this evaluation are shown in green cells, while incorrect results are placed in red cells. Accuracy of evaluation is also given. </br>

### Downloads</br>
The algorithms for SVM, neural network, and decision tree are derived from the Weka API; its installation is available at https://www.cs.waikato.ac.nz/ml/weka/ </br>
Modification of the DatabaseUtils.props file is needed to reflect your database connection. Instructions for this step is available at https://weka.wikispaces.com/Use+WEKA+in+your+Java+code </br>
In addition, instructions for the setup of MySQL is available at https://www.mysql.com/downloads/ .</br>
The Eclipse IDE with Java plugins was used.
Instruction on its installation is available at http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/neonr . </br>

### Documentation</br>
There are four packages: CheckThisFile, JTablePackage, WekaDBFilePackage, and addSample packages .</p>
##### CheckThisFile Package</br>
The “CheckThisFile” package reads the answer key from a text file, and place these values into an arraylist (ReadFile.java). The CompareTwoArrays.java class compares arrays from the ReadFile.java class against the test results taken from the Evaluate.java class (that is located in the WekaDBFilePackage package). In addition, the CompareTwoArrays.java class calculates the accuracy of the evaluation. Tables displaying the results using any of the three algorithms, SVM, Neural Network, and Decision Tree are made in PrepareTableSVM, PrepareTableNN, and PrepareTableTree classes, respectively.</br>

##### JTablePackage Package</br>
The “JTablePackage” package contains the DisplayJTable.java class that orchestrates the calling of logics from many other classes (Evaluate.java, MakeFile.java, Predict.java, Connect.java, and DisplayAddSample.java, CompareTwoArrays.java, and CheckThisFile.java classes). The main purpose of the DisplayJTable.java class is to make the graphic user interface using logics from the previously named classes. The TableQuery.java class establishes JDBC connection with the schema called “diabetes” in MySQL on port 3306 of localhost. Methods in this TableQuery.java class displays datasets that are used for training and testing, which are provided by the TableTrain.java TableTest.java classes, respectively.</br>

##### WekaDBFilePackage Package</br>
WekaDBFilePackage contains the main class, called Driver15.java. In addition, it also contains DatabaseQueries.java class that returns the fields of a sample (person being tested for diabetes) and getInstanceDataFromDatabase.java class that retrieves instances from the database. The UtilsWeka.java class creates instances from data written in .arff files.</br>

The Evaluate.java class builds classifiers for each algorithm and returns the results as array lists.</br>

Because Weka is designed to use data written in .arff file format, the usage of data from a database in our case was reformatted to .arff file format. This is achieved by taking instances from the database, then appending the header that is formatted to match .arff file format required by Weka. Hence, the conversion of database instances to match the .arff file format is written in the MakeFile.java class.</br>

An important function of machine learning is the ability to predict the outcome of data with unknown value; this is done in the Predict.java class. Prediction is made for the last sample added to the test dataset.</br>

##### AddSample Package</br>
AddSample package connects to the JDBC diabetes schema (Connect.java class) to enable the addition of new samples that could be used for testing. The user interface is created in DisplayAddSample.java class. The SampleForDiabetes.java class is the entity class containing fields, getters, and setters for the sample entity.</br>

Documentation of the java code is provided within each class file</br>

### Notes</br>
Please check the comment section on GitHub for version information.</br>

### Feedback</br>
Comments are welcomed. Please collaborate!</br>

### Contents of the Application</br>
This application contains:</br>

##### Source Code:</br>
Four packages described above</br>
##### CheckThisFile package contains the following .java files:</br>
CompareTwoArrays,
PrepareTableNN,
PrepareTableSVM,
PrepareTableTree, and
ReadFile.
##### JTablePackage contains the following .java files:</br>
DisplayJTable,
TableQuery,
TableTest, and
TableTrain.
##### WekaDBFilePackage contains the following .java files:</br>
DatabaseQueries,
Driver15,
Evaluate,
InstancesFromDatabase,
MakeFile,
Predict, and
UtilsWeka.
##### AddSample package contains the following .java files:</br>
Connect,
DisplayAddSample, and
SampleForDiabetes.
##### The files in .arff format:</br>
**checkForAccuracy**: contains the answer key to be checked against the evaluated test results that are taken from the Evaluate.java class</br>
**combined**: file with all training data </br>
**combinedTest**: file with all testing data </br>
**fromDatabase**:file containing training data from the database after the header had been appended, which is processed in the MakeFile.java class</br>
**fromDatabaseTest**:file containing testing data from the database after the header had been appended, which is processed in the MakeFile.java class</br>
**headerDiabetes**: contains information on formatted data type and their variable names in java files and database schema</br>

Drivers to MySQL and Weka.</br>

Power point slides to show an overview of data flow, UML diagram, and appearance of GUI.</br>

### Distribution</br>
Please distribute with citation:</br>
An, Vithia (2017). Health Predictions (Version 1.0) [Source code]. http://github.com/anv2/GUI_test_train_predict_diabetes.</br>
