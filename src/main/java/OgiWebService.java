package main.java;

import static spark.Spark.get;

public class OgiWebService {
	static String csvPath;
	static String marineDatasetName;
	static String serialization;
	static String cubePath;
	String dimOrMeasures;
	static TarqlFormulator tarqlformulator;
	
	public static void main(String args[]) {

		tarqlformulator =new TarqlFormulator();
		
		
		// port(8080);
		get("cubeBuilderArgs", "application/json", (request, response) -> {
			csvPath = request.queryParams("csv");
			marineDatasetName = request.queryParams("schema");
			serialization = request.queryParams("serialization");
			cubePath = request.queryParams("cube");

			return "HI ";
		});

	}

	public String run() {

		if (serialization.equalsIgnoreCase("turtle"))
			tarqlformulator.tarqlExcution(csvPath, cubePath, dimOrMeasures,
					marineDatasetName, " ");
		else
			tarqlformulator.tarqlExcution(csvPath, cubePath, dimOrMeasures,
					marineDatasetName, serialization);

		return null;

	}

}