package eu.opengov.cubebuilder.webservice;

import static spark.Spark.get;
import eu.opengov.cubebuilder.lqboperations.LqbQuerying;
import eu.opengov.cubebuilder.tarqlservices.TarqlFormulator;

/**
 * 
 * @author moh.adelrezk@gmail.com
 * */

public class OgiWebService {
	static String csvFilePath;
	static String marineDatasetName;
	static String marineDatasetURI;
	static String serialization;
	static String qbPath;
	static String qbFileName;
	static String dimOrMeasures;
	static TarqlFormulator tarqlformulator;
	static LqbQuerying lqbquerying;
	static String SparqlQuery;
	static String fusekiPort;
	static String limit;

	public static void main(String args[]) {

		tarqlformulator = new TarqlFormulator();
		lqbquerying = new LqbQuerying();

		get("/", "application/json", (request, response) -> {

			return "Welcome to OGI Webservice API! !";
		});
		/*
		 * (0) Building Linked Cubes
		 */
		get("cubeBuilderAPI/cubeBuilderArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");

			csvFilePath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			qbPath = request.queryParams("qbPath");
			qbFileName = request.queryParams("qbName");
			fusekiPort = request.queryParams("fuseki");

			return run();
		});

		/*
		 * (1) List available Linked Cubes
		 */
		get("cubeQueryingAPI/listLqbs", "application/json",
				(request, response) -> {
					response.header("Access-Control-Allow-Origin", "*");
					response.header("Content-Type", "application/json");

					fusekiPort = request.queryParams("fuseki");
					limit = request.queryParams("limit");

					return lqbquerying.LqbQueryingForLqbSpaces(fusekiPort,
							limit);
				});

		/*
		 * (2) List Linked Cube metadata
		 */
		get("cubeQueryingAPI/LqbMeta", "application/json",
				(request, response) -> {
					response.header("Access-Control-Allow-Origin", "*");
					response.header("Content-Type", "application/json");

					marineDatasetURI = request.queryParams("dsuri");
					fusekiPort = request.queryParams("fuseki");
					// limit=request.queryParams("limit");
					// System.out.println(marineDatasetName+" ******** "+fusekiPort);

				return lqbquerying.LqbQueryingForDimAndMeasures(marineDatasetURI,
						fusekiPort);
			});
		/*
		 * (3) Retrieve Data of certain Linked Cube
		 */
		get("cubeQueryingAPI/listdataofLqb", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");

			marineDatasetURI = request.queryParams("dsuri");
			fusekiPort = request.queryParams("fuseki");
			limit = request.queryParams("limit");

			return lqbquerying.LqbQueryingForLqbData(marineDatasetName,
					fusekiPort, limit);
		});

		/*
		 * (4) Send Sparql Query over Linked Cubes
		 */
		get("cubeQueryingAPI/cubeQueryingArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");

			SparqlQuery = request.queryParams("query");
			fusekiPort = request.queryParams("fuseki");
			// limit=request.queryParams("limit");

				return lqbquerying.LqbQuerying(SparqlQuery, fusekiPort);
			});

	}

	public static String run() {

		try {
			if (serialization.equalsIgnoreCase("turtle")) {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dimOrMeasures, marineDatasetName,
						serialization);
				return "Success: Cube Created check distination folder!";
			} else {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dimOrMeasures, marineDatasetName,
						serialization);
				return "Success: Cube Created check distination folder!";
			}
		} catch (Exception ex) {
			return "Error: " + ex.getMessage();
		}

	}
}