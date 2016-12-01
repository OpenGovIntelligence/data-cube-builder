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
	// static String fusekiPort;
	static String limit;
//	static ImplRESTapi implRESTapi;

	public static void main(String[] args) {

		tarqlformulator = new TarqlFormulator();
		lqbquerying = new LqbQuerying();
//		implRESTapi = new ImplRESTapi();

		get("/", "application/json", (request, response) -> {

			return "Welcome to OGI Webservice API! !";
		});
/*
		get("cubeBuilderAPI/TESTJSONAPICERTH", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");
			return implRESTapi.getDimensions("IWBNetwork_ds");

		});
*/
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
			// fusekiPort = request.queryParams("fuseki");

				return run();
			});

		/*
		 * (1) List available Linked Cubes
		 */
		get("cubeQueryingAPI/listLqbs", "application/json",
				(request, response) -> {
					response.header("Access-Control-Allow-Origin", "*");
					response.header("Content-Type", "application/json");

					// fusekiPort = request.queryParams("fuseki");
				limit = request.queryParams("limit");

				return lqbquerying.LqbQueryingForLqbSpaces(limit);
			});

		/*
		 * (2) List Linked Cube metadata
		 */
		get("cubeQueryingAPI/LqbMeta", "application/json",
				(request, response) -> {
					response.header("Access-Control-Allow-Origin", "*");
					response.header("Content-Type", "application/json");
					// String url = request.splat()[0];
				String url = request.splat().toString();

				marineDatasetURI = request.queryParams("dsuri");
				// fusekiPort = request.queryParams("fuseki");
				// limit=request.queryParams("limit");
				System.out.println(marineDatasetURI);
				System.out.println(request.raw().getRequestURL().toString()
						+ "---" + request.raw().getQueryString() + "----");

				return lqbquerying.LqbQueryingForDimAndMeasures(request
						.queryParams("dsuri"));
			});
		/*
		 * (3) Retrieve Data of certain Linked Cube
		 */
		get("cubeQueryingAPI/listdataofLqb", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");

			marineDatasetURI = request.queryParams("dsuri");
			// fusekiPort = request.queryParams("fuseki");
			limit = request.queryParams("limit");

				return lqbquerying.LqbQueryingForLqbData(marineDatasetURI,
						limit);
			});

		/*
		 * (4) Send Sparql Query over Linked Cubes
		 */
		get("cubeQueryingAPI/cubeQueryingArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");

			SparqlQuery = request.queryParams("query");
			// fusekiPort = request.queryParams("fuseki");
				// limit=request.queryParams("limit");

				return lqbquerying.LqbDirectQuerying(SparqlQuery);
			});

	}

	public static String run() {

		String success="{\"success\": true,\"payload\": {}}";
		String failure="{\"success\": false,\"payload\": {},\"error\": {\"code\": 123,\"message\": \"An error occurred!\"}}";

		try {
			if (serialization.equalsIgnoreCase("turtle")) {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dimOrMeasures, marineDatasetName,
						serialization);
//				return "Success: Cube Created check distination folder!";
				return success;

			} else {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dimOrMeasures, marineDatasetName,
						serialization);
//				return "Success: Cube Created check distination folder!";
				return success;
			}
		} catch (Exception ex) {
//			return "Error: " + ex.getMessage();
			return failure;
		}

	}
}