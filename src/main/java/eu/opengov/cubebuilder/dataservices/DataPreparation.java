package eu.opengov.cubebuilder.dataservices;
/**
 * 
 * @author moh.adelrezk@gmail.com
 * */
//TODO:Will be needed for data pre-processing 
public class DataPreparation {
	
	
	/** find NaN and -999 values inside the CSV and replace it with 0.0 // remove the reading not to miss with the data*/

	void removeNaNFromCSV (String datasetSourceFileCSV){
		
		//this is done at the rdf to json phase in the webservice and lq-querying modules

	}
	/** find NaN values inside the Data Stream and replace it with 0.0*/
	void removeNaNFromDataStream (String datasetSourceFileCSV){
	
	}


}
