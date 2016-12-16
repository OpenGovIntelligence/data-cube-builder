package eu.opengov.cubebuilder.webservice;

import static spark.Spark.*;
import static spark.debug.DebugScreen.*;
import spark.*;


import javax.servlet.*;
import javax.servlet.http.*;

import java.io.*;
import java.nio.file.*;


import eu.opengov.cubebuilder.lqboperations.LqbQuerying;
import eu.opengov.cubebuilder.tarqlservices.TarqlFormulator;

/**
 * 
 * @author moh.adelrezk@gmail.com
 * */

public class OgiWebService {
	static String csvFilePath;
	static String schema;
	static String marineDatasetURI;
	static String serialization;
	static String qbPath;
	static String qbFileName;
	static String dims="";
	static String measures="";
	static String dataset="";
	static TarqlFormulator tarqlformulator;
	static LqbQuerying lqbquerying;
	static String SparqlQuery;
	// static String fusekiPort;
	static String limit;
//	static ImplRESTapi implRESTapi;

	public static void main(String[] args) {
       enableDebugScreen();

		
		tarqlformulator = new TarqlFormulator();
		lqbquerying = new LqbQuerying();
//		implRESTapi = new ImplRESTapi();
		
		File uploadDir = new File("upload");
        uploadDir.mkdir(); // create the upload directory if it doesn't exist

        staticFiles.externalLocation("upload");

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
		/**get 
		get("cubeBuilderAPI/cubeBuilderArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");
			String path= new File("").getAbsolutePath().substring(0, new File("").getAbsolutePath().length()-16);
			csvFilePath = path+request.queryParams("csv");
			if (request.queryParams("schema").contains("/")){
				schema = path+request.queryParams("schema");
			}
			else{
				schema = request.queryParams("schema");	
			}
			serialization = request.queryParams("serialization");
			qbPath = path+request.queryParams("qbPath");
			qbFileName = request.queryParams("qbName");
			// fusekiPort = request.queryParams("fuseki");

			System.out.println(csvFilePath+"\n"+schema+"\n"+serialization+"\n"+qbPath+"\n"+qbFileName);
				return run();
			});
		*/
		//post just incase a post request is used to call 
		post("cubeBuilderAPI/cubeBuilderArgs", "application/json", (request,
				response) -> {
			response.header("Access-Control-Allow-Origin", "*");
			response.header("Content-Type", "application/json");
			String path= new File("").getAbsolutePath().substring(0, new File("").getAbsolutePath().length()-16);
			csvFilePath = path+request.queryParams("csv");
			if (request.queryParams("schema").contains("/")){
				schema = path+request.queryParams("schema");
			}
			else{
				schema = request.queryParams("schema");	
			}
			serialization = request.queryParams("serialization");
			qbPath = path+request.queryParams("qbPath");
			qbFileName = request.queryParams("qbName");
			dims=request.queryParams("dims");
			measures=request.queryParams("measures");
			dataset=request.queryParams("dataset");
			// fusekiPort = request.queryParams("fuseki");

			System.out.println(csvFilePath+"\n"+schema+"\n"+serialization+"\n"+qbPath+"\n"+qbFileName+"\n"+dims+"\n"+measures+"\n"+dataset);
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
		
		
		/*
		 * (5) FILE UPLOAD for loose coupling
		 * 
		 * */
		
		
		 get("/upload", (req, res) ->
         "<form method='post' enctype='multipart/form-data'>" // note the enctype
       + "    <input type='file' name='uploaded_file' accept='.png'>" // make sure to call getPart using the same "name" in the post
       + "    <button>Upload picture</button>"
       + "</form>"
);

		
		 post("/upload", "multipart/form-data",(req, res) -> {

	            Path tempFile = Files.createTempFile(uploadDir.toPath(), "", "");

	            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/upload"));

	            try (InputStream input = req.raw().getPart("uploaded_file").getInputStream()) { // getPart needs to use same "name" as input field in form
	                Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
	            }

	            logInfo(req, tempFile);
	            return "<h1>You uploaded this image:<h1><img src='" + tempFile.getFileName() + "'>";

	        });

	    }

	public static String run() {

		String success="{\"success\": true,\"payload\": {}}";
		String failure="{\"success\": false,\"payload\": {},\"error\": {\"code\": 123,\"message\": \"An error occurred!\"}}";

		try {
			if (serialization.equalsIgnoreCase("turtle")) {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dims, measures,dataset, schema,
						serialization);
//				return "Success: Cube Created check distination folder!";
				return success;

			} else {
				tarqlformulator.tarqlAsLibraryExecution(csvFilePath, qbPath,
						qbFileName, dims, measures,dataset, schema,
						serialization);
//				return "Success: Cube Created check distination folder!";
				return success;
			}
		} catch (Exception ex) {
//			return "Error: " + ex.getMessage();
			return failure;
		}

	}
	
	
	 // methods used for logging
    private static void logInfo(Request req, Path tempFile) throws IOException, ServletException {
        System.out.println("Uploaded file '" + getFileName(req.raw().getPart("uploaded_file")) + "' saved as '" + tempFile.toAbsolutePath() + "'");
    }

    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

	
}