package eu.opengov.cubebuilder.webservice;

import static spark.Spark.get;
import eu.opengov.cubebuilder.lqboperations.LqbQuerying;
import eu.opengov.cubebuilder.tarqlservices.TarqlFormulator;
/**
 * 
 * @author moh.adelrezk@gmail.com
 * */
/**
FIXME: ADD LIMIT as variable
*/
public class OgiWebService {
	static String csvFilePath;
	static String marineDatasetName;
	static String serialization;
	static String qbPath;
	static String qbFileName;
	static String dimOrMeasures;
	static TarqlFormulator tarqlformulator;
	static LqbQuerying lqbquerying;
	static String SparqlQuery;
	static String fusekiPort;


	public static void main(String args[]) {

		tarqlformulator = new TarqlFormulator();
		lqbquerying = new LqbQuerying();

		// port(8080);
		// host:4567/cubeBuilderArgs?csv=inputFileNameAndLocation&schema=marineInstituteDatasetId&serialization=turtle&cube=outputFileAndLocation
		// http://localhost:4567/cubeBuilderArgs?csv=src%2Fmain%2Fresources%2Fdata%2FIWaveBNetwork30Min.csv&schema=IWaveBNetwork30Min&serialization=turtle&cube=src%2Fmain%2Fresources%2Foutput%2Fwebservice.ttl
		// curl http://localhost:4567/cubeBuilderAPI/cubeBuilderArgs?csv=src%2Fmain%2Fresources%2Fdata%2FIWBNetwork.csv&schema=IWBNetwork&serialization=turtle&cube=src%2Fmain%2Fresources%2Foutput%2FIWBNetwork.ttl
		get("/", "application/json", (request,
				response) -> {

			return "Welcome to OGI Webservice API! !";
		});

		get("cubeBuilderAPI/cubeBuilderArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");
			csvFilePath = request.queryParams("csv");
			
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			qbPath = request.queryParams("cube");
			qbFileName=request.queryParams("qbName");
			fusekiPort=request.queryParams("fuseki");

			return run();
		});

		get("cubeQueryingAPI/cubeQueryingArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");
			SparqlQuery = request.queryParams("query");
			fusekiPort=request.queryParams("fuseki");
			
			return lqbquerying.LqbQueryingForVizJson(SparqlQuery, fusekiPort);
		});

	}

	public static String run() {

		try {
			if (serialization.equalsIgnoreCase("turtle")) {
				tarqlformulator.tarqlAsLibraryExecution( csvFilePath,  qbPath,
						 qbFileName,  dimOrMeasures,  marineDatasetName,
						 serialization);
				return "Success: Cube Created check distination folder!";
			} else {
				tarqlformulator.tarqlAsLibraryExecution( csvFilePath,  qbPath,
						 qbFileName,  dimOrMeasures,  marineDatasetName,
						 serialization);
				return "Success: Cube Created check distination folder!";
			}
		} catch (Exception ex) {
			return "Error: " + ex.getMessage();
		}

	}
}