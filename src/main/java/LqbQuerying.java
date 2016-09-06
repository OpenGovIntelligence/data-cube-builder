package main.java;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LqbQuerying {

	public JSONArray LqbQueryingForVizJson(String sparqlQuery, String fusekiPort)
			throws IOException {
		// public static void main(String[] args) throws IOException {
		// String filename =
		// "/home/mohade/workspace/OGI1/src/main/resources/output/newcube2.ttl.observations"
		// ;
		String queryCommand = "PREFIX OGI:  <http://ogi.eu/#> \n"
				+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
				+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "SELECT  \n"
				+ "?station_id\n"
				+ "(count(?atmosphericPressure) AS ?Observations_Count)\n"
				+ "(sum(xsd:float(?atmosphericPressure))/count(?atmosphericPressure) AS ?Average_atmosphericPressure)\n"
				+ "(sum(xsd:float(?windDirection))/count(?windDirection) AS ?Average_windDirection)\n"
				+ "(sum(xsd:float(?windSpeed))/count(?windSpeed) AS ?Average_windSpeed)\n"
				+ "(sum(xsd:float(?gust))/count(?gust) AS ?Average_gust)\n"
				+ "(sum(xsd:float(?waveHeight))/count(?waveHeight) AS ?Average_waveHeight)\n"
				+ "(sum(xsd:float(?wavePeriod))/count(?wavePeriod) AS ?Average_wavePeriod)\n"
				+ "(sum(xsd:float(?meanWaveDirection))/count(?meanWaveDirection) AS ?Average_meanWaveDirection)\n"
				+ "(sum(xsd:float(?hmax))/count(?hmax) AS ?Average_hmax)\n"
				+ "(sum(xsd:float(?airTemperature))/count(?airTemperature) AS ?Average_airTemperature)\n"
				+ "(sum(xsd:float(?dewPoint))/count(?dewPoint) AS ?Average_dewPoint)\n"
				+ "WHERE {\n"
				+ "?observation a qb:observation.\n"
				+ "?observation OGI:station_id ?station_id.\n"
				+ "?observation OGI:atmosphericPressure ?atmosphericPressure.\n"
				+ "?observation OGI:windDirection ?windDirection.\n"
				+ "?observation OGI:windSpeed ?windSpeed.\n"
				+ "?observation OGI:gust ?gust.\n"
				+ "?observation OGI:waveHeight ?waveHeight.\n"
				+ "?observation OGI:wavePeriod ?wavePeriod.\n"
				+ "?observation OGI:meanWaveDirection ?meanWaveDirection.\n"
				+ "?observation OGI:hmax ?hmax.\n"
				+ "?observation OGI:airTemperature ?airTemperature.\n"
				+ "?observation OGI:dewPoint ?dewPoint.\n" 
				+ "}\n"
				+ "GROUP BY (?station_id) \n";

		String queryCommand2 = "PREFIX OGI:  <http://ogi.eu/#> \n"
				+ "PREFIX xsd:  <http://www.w3.org/2001/XMLSchema#> \n"
				+ "PREFIX qb:  <http://purl.org/linked-data/cube#> \n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "SELECT  \n"
				+ "?station_id\n"
				+ "?atmosphericPressure\n"
				+ "?windDirection\n"
				+ "?windSpeed\n"
				+ "?gust\n"
				+ "?waveHeight\n"
				+ "?wavePeriod\n"
				+ "?meanWaveDirection\n"
				+ "?hmax\n"
				+ "?airTemperature\n"
				+ "?dewPoint\n"
				+ "WHERE {\n"
				+ "?observation a qb:observation.\n"
				+ "?observation OGI:station_id ?station_id.\n"
				+ "?observation OGI:atmosphericPressure ?atmosphericPressure.\n"
				+ "?observation OGI:windDirection ?windDirection.\n"
				+ "?observation OGI:windSpeed ?windSpeed.\n"
				+ "?observation OGI:gust ?gust.\n"
				+ "?observation OGI:waveHeight ?waveHeight.\n"
				+ "?observation OGI:wavePeriod ?wavePeriod.\n"
				+ "?observation OGI:meanWaveDirection ?meanWaveDirection.\n"
				+ "?observation OGI:hmax ?hmax.\n"
				+ "?observation OGI:airTemperature ?airTemperature.\n"
				+ "?observation OGI:dewPoint ?dewPoint.\n" 
				+ "}\n"
//				+ "GROUP BY (?station_id) \n" 
				+ "LIMIT  100";

		System.out.println("Fuseki Request Thread started!");
		System.out.println("query:" + queryCommand);

		ResultSet results = queryServerWithDefaultGraph(
				"http://localhost:"+fusekiPort+"/ds/query", queryCommand2, "SELECT", "");

		return generateJSON(results);

	}

	public String LqbQueryingForRDFrow(String sparqlQuery) throws IOException {
		return sparqlQuery;
	};

	private static JSONArray generateJSON(ResultSet results)
			throws JSONException, IOException {

		JSONArray finalJsonArrayForPivotTable = new JSONArray();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		ResultSetFormatter.outputAsJSON(outputStream, results);

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
				if (!jtempreadLevel2.get("value").equals("NaN")) {
					jtempwrite.accumulate(key, jtempreadLevel2.get("value"));
				} else {
					jtempwrite.accumulate(key, "0.0");
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

}