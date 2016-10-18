package eu.opengov.cubebuilder.lqboperations;

/**
 @author moh.adelrezk@gmail.com
 */
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;

import eu.opengov.cubebuilder.util.PropertyReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//TODO: Fuseki server location in config.prop file (http://localhost:port?/ds/query)
public class LqbQuerying {

	PropertyReader pr=new PropertyReader();
	
	
	/*
	 * (1) List available Linked Cubes
	 */
	public JSONArray LqbQueryingForLqbSpaces(String limit)
			throws IOException {
		String LqbQueryingForLqbSpaces_queryCommand = "Error at LqbQueryingForLqbSpaces function!";

		LqbQueryingForLqbSpaces_queryCommand = "PREFIX qb:  <http://purl.org/linked-data/cube#>  \n"
				+ "PREFIX OGI:  <http://ogi.eu/#> \n"
				+ "PREFIX dct:      <http://purl.org/dc/terms/> \n"
				+ "PREFIX rdfs:     <http://www.w3.org/2000/01/rdf-schema#> \n"
				+ "SELECT ?dataset ?title  ?label ?comment   ?description ?publisher ?issued ?label \n"
				+ "WHERE \n"
				+ "{\n"
				+ "?dataset a qb:DataSet.\n"
				+ "?dataset dct:title ?title.\n"
				+ "?dataset rdfs:label  ?label.\n"
				+ "?dataset rdfs:comment    ?comment.\n"
				+ "?dataset dct:description ?description.\n"
				+ "?dataset dct:publisher   ?publisher.\n"
				+ "?dataset dct:issued ?issued.\n"
				+ "?dataset rdfs:label ?label.\n" + "} \n" + "LIMIT  " + limit;

		return LqbExecuteQuery(LqbQueryingForLqbSpaces_queryCommand);

	}

	/*
	 * (2) List Linked Cube metadata
	 */
	public JSONArray LqbQueryingForDimAndMeasures(String marineDatasetURI) throws IOException {

		String LqbQueryingForMetaData_queryCommand = "Error at LqbQueryingForMetaData function!";

		LqbQueryingForMetaData_queryCommand = "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
				+ "PREFIX OGI:  <http://ogi.eu/#> \n"
				+ "PREFIX dct:      <http://purl.org/dc/terms/> \n"
				+ "PREFIX rdfs:     <http://www.w3.org/2000/01/rdf-schema#> \n"
				+ "SELECT  ?type ?name \n"
				+ "WHERE{  \n"
//				+ "OGI:"
				+ "<"+marineDatasetURI+">"
//				+ "_dsd "
				+ "qb:structure ?dsd. \n"
				+ "?dsd qb:component [?type ?name]. \n"
				+ "} \n";
		/*
		 * String
		 * getCubeDimensions_query="PREFIX qb: <http://purl.org/linked-data/cube#>"
		 * + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>" +
		 * "PREFIX skos: <http://www.w3.org/2004/02/skos/core#>" +
		 * "select  distinct ?res ?label where {" +
		 * "<http://ogi.eu/#"+marineDatasetName+"_ds> qb:structure ?dsd." +
		 * "?dsd qb:component  ?cs." + "?cs qb:dimension ?res." +
		 * "OPTIONAL{?res skos:prefLabel|rdfs:label ?label.}}";
		 */
		return LqbExecuteQuery(LqbQueryingForMetaData_queryCommand);

	}

	/*
	 * (3) Retrieve Data of certain Linked Cube
	 */
	public JSONArray LqbQueryingForLqbData(String marineDatasetURI,
			 String limit) throws IOException {

		String LqbQueryingForLqbData_queryCommand = "embty";
System.out.print(marineDatasetURI);
		if (marineDatasetURI.equalsIgnoreCase("http://ogi.eu/#IWBNetwork_ds")) {

			LqbQueryingForLqbData_queryCommand = "PREFIX OGI:  <http://ogi.eu/#> \n"
					+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
					+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
					+ "SELECT  \n"
					+ "?station_id\n"
					+ "?AtmosphericPressure\n"
					+ "?WindDirection\n"
					+ "?WindSpeed\n"
					+ "?Gust\n"
					+ "?WaveHeight\n"
					+ "?WavePeriod\n"
					+ "?MeanWaveDirection\n"
					+ "?Hmax\n"
					+ "?AirTemperature\n"
					+ "?DewPoint\n"
					+ "?SeaTemperature\n"
					+ "?salinity\n"
					+ "?RelativeHumidity\n"
					+ "?time\n"
					+ "?longitude\n"
					+ "?latitude\n"
					+ "?QC_Flag \n"
					+ "WHERE {\n"
					+ "?observation a qb:Observation.\n"
					+ "?observation OGI:station_id ?station_id.\n"
					+ "?observation OGI:atmosphericPressure ?AtmosphericPressure.\n"
					+ "?observation OGI:windDirection ?WindDirection.\n"
					+ "?observation OGI:windSpeed ?WindSpeed.\n"
					+ "?observation OGI:gust ?Gust.\n"
					+ "?observation OGI:waveHeight ?WaveHeight.\n"
					+ "?observation OGI:wavePeriod ?WavePeriod.\n"
					+ "?observation OGI:meanWaveDirection ?MeanWaveDirection.\n"
					+ "?observation OGI:hmax ?Hmax.\n"
					+ "?observation OGI:airTemperature ?AirTemperature.\n"
					+ "?observation OGI:dewPoint ?DewPoint.\n"
					+ "?observation OGI:seaTemperature ?SeaTemperature.\n"
					+ "?observation OGI:salinity ?salinity.\n"
					+ "?observation OGI:relativeHumidity ?RelativeHumidity.\n"
					+ "?observation OGI:time ?time.\n"
					+ "?observation OGI:longitude ?longitude.\n"
					+ "?observation OGI:latitude ?latitude.\n"
					+ "?observation OGI:qC_Flag ?QC_Flag.\n"
					+ "?observation qb:dataSet OGI:IWBNetwork_ds.\n" + "}\n"
					// + "GROUP BY (?station_id) \n"
					+ "LIMIT" + limit;

		}
		if (marineDatasetURI.equalsIgnoreCase("http://ogi.eu/#IWaveBNetwork30Min_ds")) {

			LqbQueryingForLqbData_queryCommand = "PREFIX OGI:  <http://ogi.eu/#> \n"
					+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
					+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
					+ "SELECT  \n"
					+ "?time\n"
					+ "?longitude\n"
					+ "?latitude\n"
					+ "?station_id\n"
					+ "?PeakPeriod\n"
					+ "?PeakDirection\n"
					+ "?UpcrossPeriod\n"
					+ "?SignificantWaveHeight\n"
					+ "?SeaTemperature\n"
					+ "?Hmax\n"
					+ "?THmax\n"
					+ "?MeanCurDirTo\n"
					+ "?MeanCurSpeed\n"
					+ "?SignificantWaveHeight_qc\n"
					+ "?PeakPeriod_qc\n"
					+ "?PeakDirection_qc\n"
					+ "?UpcrossPeriod_qc\n"
					+ "?SeaTemperature_qc\n"
					+ "?Hmax_qc\n"
					+ "?THmax_qc\n"
					+ "?MeanCurDirTo_qc\n"
					+ "?MeanCurSpeed_qc\n"
					+ "WHERE {\n"
					+ "?observation a qb:Observation.\n"
					+ "?observation OGI:time ?time.\n"
					+ "?observation OGI:longitude ?longitude.\n"
					+ "?observation OGI:latitude ?latitude.\n"
					+ "?observation OGI:station_id ?station_id.\n"
					+ "?observation OGI:peakPeriod ?PeakPeriod.\n"
					+ "?observation OGI:peakDirection ?PeakDirection.\n"
					+ "?observation OGI:upcrossPeriod ?UpcrossPeriod.\n"
					+ "?observation OGI:significantWaveHeight ?SignificantWaveHeight.\n"
					+ "?observation OGI:seaTemperature ?SeaTemperature.\n"
					+ "?observation OGI:hmax ?Hmax.\n"
					+ "?observation OGI:tHmax ?THmax.\n"
					+ "?observation OGI:meanCurDirTo ?MeanCurDirTo.\n"
					+ "?observation OGI:meanCurSpeed ?MeanCurSpeed.\n"
					+ "?observation OGI:significantWaveHeight_qc ?SignificantWaveHeight_qc.\n"
					+ "?observation OGI:peakPeriod_qc ?PeakPeriod_qc.\n"
					+ "?observation OGI:peakDirection_qc ?PeakDirection_qc.\n"
					+ "?observation OGI:upcrossPeriod_qc ?UpcrossPeriod_qc.\n"
					+ "?observation OGI:seaTemperature_qc ?SeaTemperature_qc.\n"
					+ "?observation OGI:hmax_qc ?Hmax_qc.\n"
					+ "?observation OGI:tHmax_qc ?THmax_qc.\n"
					+ "?observation OGI:meanCurDirTo_qc ?MeanCurDirTo_qc.\n"
					+ "?observation OGI:meanCurSpeed_qc ?MeanCurSpeed_qc.\n"
					+ "?observation qb:dataSet OGI:IWaveBNetwork30Min_ds.\n"
					+ "}\n"
					// + "GROUP BY (?station_id) \n"
					+ "LIMIT" + limit;

		}
		if (marineDatasetURI.equalsIgnoreCase("http://ogi.eu/#IrishNatoinalTideGaugeNetwork_ds")) {

			LqbQueryingForLqbData_queryCommand = "PREFIX OGI:  <http://ogi.eu/#> \n"
					+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
					+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
					+ "SELECT  \n"
					+ "?longitude\n"
					+ "?latitude\n"
					+ "?altitude\n"
					+ "?time\n"
					+ "?station_id\n"
					+ "?Water_Level\n"
					+ "?Water_Level_LAT\n"
					+ "?Water_Level_OD_Malin\n"
					+ "?QC_Flag\n"
					+ "WHERE {\n"
					+ "?observation a qb:Observation.\n"
					+ "?observation OGI:longitude ?longitude. \n"
					+ "?observation OGI:latitude ?latitude. \n"
					+ "?observation OGI:altitude ?altitude. \n"
					+ "?observation OGI:time ?time. \n"
					+ "?observation OGI:station_id ?station_id. \n"
					+ "?observation OGI:water_Level ?Water_Level. \n"
					+ "?observation OGI:water_Level_LAT ?Water_Level_LAT. \n"
					+ "?observation OGI:water_Level_OD_Malin ?Water_Level_OD_Malin. \n"
					+ "?observation OGI:qC_Flag ?QC_Flag. \n"
					+ "?observation qb:dataSet OGI:IrishNatoinalTideGaugeNetwork_ds.\n"
					+ "}\n"
					// + "GROUP BY (?station_id) \n"
					+ "LIMIT" + limit;

		}
		if (marineDatasetURI.equalsIgnoreCase("http://ogi.eu/#IMI_EATL_WAVE_ds")) {

			LqbQueryingForLqbData_queryCommand = "PREFIX OGI:  <http://ogi.eu/#> \n"
					+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
					+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
					+ "SELECT  \n"
					+ "?time\n"
					+ "?longitude\n"
					+ "?latitude\n"
					+ "?significant_wave_height\n"
					+ "?swell_wave_height\n"
					+ "?mean_wave_direction\n"
					+ "?mean_wave_period\n"
					+ "WHERE {\n"
					+ "?observation a qb:Observation.\n"
					+ "?observation OGI:time ?time.\n"
					+ "?observation OGI:longitude ?longitude.\n"
					+ "?observation OGI:latitude ?latitude.\n"
					+ "?observation OGI:significant_wave_height ?significant_wave_height.\n"
					+ "?observation OGI:swell_wave_height ?swell_wave_height.\n"
					+ "?observation OGI:mean_wave_direction ?mean_wave_direction.\n"
					+ "?observation OGI:mean_wave_period ?mean_wave_period.\n"
					+ "?observation qb:dataSet OGI:IMI_EATL_WAVE_ds.\n" + "}\n"
					// + "GROUP BY (?station_id) \n"
					+ "LIMIT" + limit;

		}

		return LqbExecuteQuery(LqbQueryingForLqbData_queryCommand);

	}

	/*
	 * (4) Send Sparql Query over Linked Cubes
	 */
	public JSONArray LqbQuerying(String sparqlQuery)
			throws IOException {

		String queryCommand = sparqlQuery;
		String fusekiPort=pr.getPropValues("fusekiPort");
		System.out.println("Fuseki Request Thread started!");
		System.out.println("query:" + queryCommand);

		ResultSet results = queryServerWithDefaultGraph(fusekiPort + "/ds/query", queryCommand, "SELECT", "");

		return generateJSON(results);

	}

	public JSONArray LqbExecuteQuery(String sparqlQuery)
			throws IOException {

		String fusekiPort=pr.getPropValues("fusekiPort");
		String Lqb_queryCommand = "embty";
		Lqb_queryCommand = sparqlQuery;

		ResultSet results = null;
		JSONArray errorMessage = new JSONArray(
				"[ {\"errorMessage\": \"Somthing went wrong at query execution!\"} ]");

		if (!Lqb_queryCommand.equalsIgnoreCase("embty")
				& !Lqb_queryCommand
						.equalsIgnoreCase("Error at LqbQueryingForLqbSpaces function!")
				& !Lqb_queryCommand
						.equalsIgnoreCase("Error at LqbQueryingForMetaData function!")) {
			System.out.println("Fuseki Request Thread started!");
			System.out.println("query:\n" + Lqb_queryCommand);

			results = queryServerWithDefaultGraph(fusekiPort + "/ds/query", Lqb_queryCommand, "SELECT", "");
		}

		if (results == null) {
			System.out.println(errorMessage);
			return errorMessage;
		} else {
			System.out.println(results.toString());
			return generateJSON(results);
		}
	}

	private static JSONArray generateJSON(ResultSet results)
			throws JSONException, IOException {

		JSONArray finalJsonArrayForPivotTable = new JSONArray();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ResultSetFormatter.outputAsJSON(outputStream, results);
System.out.print(outputStream.toString());
		// and turn that into a String
		String stringOfjsonOfFusekiResultTemporaryStage = new String(
				outputStream.toByteArray());

		// System.out.println(json);
		JSONObject jsonOfFusekiResponse = new JSONObject(
				stringOfjsonOfFusekiResultTemporaryStage);
		// System.out.println(jo.toString(2));

		JSONObject jsonOfFusekiResults = jsonOfFusekiResponse
				.getJSONObject("results");
		JSONArray jsonOfFusekiBindings = new JSONArray();
		jsonOfFusekiBindings = jsonOfFusekiResults.getJSONArray("bindings");

		/** retrieving keys */
		JSONObject jtempreadkeys = new JSONObject();
		jtempreadkeys = jsonOfFusekiBindings.getJSONObject(0);
		Set<String> finalJsonKeys = jtempreadkeys.keySet();

		/** Building the final json response */
		for (int i = 0, size = jsonOfFusekiBindings.length(); i < size; i++) {
			JSONObject jtempreadLevel1 = jsonOfFusekiBindings.getJSONObject(i);
			JSONObject jtempwrite = new JSONObject();
			for (String key : finalJsonKeys) {
				JSONObject jtempreadLevel2 = jtempreadLevel1.getJSONObject(key);
				if (jtempreadLevel2.get("value").equals("NaN")) {
					jtempwrite.accumulate(key, "0.0");
				} else {
//					jtempwrite.accumulate(key, removePrefix(jtempreadLevel2
//							.get("value").toString()));
					jtempwrite.accumulate(key, jtempreadLevel2
							.get("value").toString());
				}
			}
			finalJsonArrayForPivotTable.put(jtempwrite);

		}
		System.out.println(finalJsonArrayForPivotTable.toString(2));

		return finalJsonArrayForPivotTable;

	}

	/**
	 * Perform the select query.
	 * 
	 * @param pathToQueryEndpoint
	 * @param queryCommand
	 * @param resultFormat
	 * @param defaultGraph
	 * @return a {@link ResultSet} containing the specified response.
	 */
	private static ResultSet queryServerWithDefaultGraph(
			final String pathToQueryEndpoint, final String queryCommand,
			final String resultFormat, final String defaultGraph) {
		Query q = QueryFactory.create(queryCommand);
		QueryExecution queryEx = QueryExecutionFactory.sparqlService(
				pathToQueryEndpoint, q, defaultGraph);
		if (resultFormat.equals("SELECT")) {
			ResultSet results = queryEx.execSelect(); // SELECT returns a
														// ResultSet
			return results;
		}
		return null;
	}

	private static String removePrefix(String dirty) {
		/* "prefixes" to delete */
		ArrayList<String> prefixes = new ArrayList<String>();
		// prefixes.add("http://quixey.com/app/2600430904/t/");
		// prefixes.add("http://quixey.com/app/2600430904/p/");
		prefixes.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/metadata#");
		prefixes.add("https://www.auto.tuwien.ac.at/downloads/thinkhome/ontology/WeatherOntology.owl#");
		prefixes.add("http://www.w3.org/2003/01/geo/wgs84_pos#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/dimension#");
		prefixes.add("http://ogi.eu/observation/a#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/subject#");
		prefixes.add("http://purl.org/dc/terms/");
//		prefixes.add("http://ogi.eu/observations/");
		prefixes.add("http://www.w3.org/2004/02/skos/core#");
		prefixes.add("http://vocab.nerc.ac.uk/collection/P07/current/");
		prefixes.add("http://www.w3.org/ns/org#");
		prefixes.add("http://www.w3.org/2001/XMLSchema#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/attribute#");
		prefixes.add("http://www.w3.org/1999/02/22-rdf-syntax-ns#");
		prefixes.add("http://dati.isprambiente.it/ontology/core#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/measure#");
		prefixes.add("http://www.w3.org/2006/timezone#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/concept#");
		prefixes.add("http://purl.org/linked-data/sdmx/2009/code#");
		prefixes.add("http://www.opengis.net/def/naming-system/EPSG/0/");
		prefixes.add("http://www.w3.org/2000/01/rdf-schema#");
		prefixes.add("http://ogi.eu/#");
		prefixes.add("http://rdfs.org/ns/void#");
		prefixes.add("http://purl.org/twc/ontologies/cmo.owl#");
		prefixes.add("http://www.w3.org/2006/time#");
		prefixes.add("http://www.w3.org/2002/07/owl#");
		prefixes.add("http://purl.org/linked-data/cube#");
		prefixes.add("http://www.w3.org/ns/ssn/");
		prefixes.add("http://xmlns.com/foaf/0.1/");
		prefixes.add("http://vocab.datex.org/terms#");
		for (String s : prefixes)
			if (dirty.startsWith(s))
				return dirty.replace(s, "");
		return dirty;
	}

}