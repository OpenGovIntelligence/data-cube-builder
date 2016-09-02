package main.java;

import static spark.Spark.get;

public class OgiWebService {
	static String csvPath;
	static String marineDatasetName;
	static String serialization;
	static String cubePath;
	static String dimOrMeasures;
	static TarqlFormulator tarqlformulator;

	public static void main(String args[]) {

		tarqlformulator = new TarqlFormulator();

		// port(8080);
		// host:4567/cubeBuilderArgs?csv=inputFileNameAndLocation&schema=marineInstituteDatasetId&serialization=turtle&cube=outputFileAndLocation
		//http://localhost:4567/cubeBuilderArgs?csv=src%2Fmain%2Fresources%2Fdata%2FIWaveBNetwork30Min.csv&schema=IWaveBNetwork30Min&serialization=turtle&cube=src%2Fmain%2Fresources%2Foutput%2Fwebservice.ttl
		get("cubeBuilderAPI/cubeBuilderArgs", "application/json", (request, response) -> {
			csvPath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			cubePath = request.queryParams("cube");

			return run();
		});
		
		get("cubeBuilderAPI/listCubes", "application/json", (request, response) -> {
			csvPath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			cubePath = request.queryParams("cube");

			return run();
		});
		
		get("cubeBuilderAPI/getJson", "application/json", (request, response) -> {
			csvPath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			cubePath = request.queryParams("cube");

			return run();
		});
		
		get("cubeBuilderAPI/runSparql", "application/json", (request, response) -> {
			csvPath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			cubePath = request.queryParams("cube");

			return run();
		});

	}

	public static String run() {

		try {
			if (serialization.equalsIgnoreCase("turtle")) {
				tarqlformulator.tarqlExcution(csvPath, cubePath, dimOrMeasures,
						marineDatasetName, " ");
				return "Success: Cube Created check distination folder!";
			} else {
				tarqlformulator.tarqlExcution(csvPath, cubePath, dimOrMeasures,
						marineDatasetName, serialization);
				return "Success: Cube Created check distination folder!";
			}
		} catch (Exception ex) {
			return "Error: "+ex.getMessage();
		}

	}
}