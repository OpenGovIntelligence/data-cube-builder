package eu.opengov.cubebuilder.dataservices;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * @deprecated
 * @editor moh.adelrezk@gmail.com
 * */
public class RDFtoJsonDeprecated {

	private static ArrayList<String> prefixes;
	private static HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();

	public static void main(String[] args) throws IOException {

		String filename = System.getProperty("user.dir")
				+ "/src/main/resources/output/last.ttl.observations";

		// "prefixes" to delete
		prefixes = new ArrayList<String>();
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
		prefixes.add("http://ogi.eu/observations/");
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

		FileInputStream input = null;
		try {
			input = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Model model = ModelFactory.createDefaultModel();
		model.read(input, "", "TURTLE");
		// System.out.println("Finished reading the input file"+);
		model.write(System.out);
		StmtIterator it = model.listStatements();
		Statement current = null;
		while (it.hasNext()) {
			current = it.nextStatement();
			String url = current.getSubject().toString();
			String predicate = removePrefix(current.getPredicate().toString());
			String object = removePrefix(current.getObject().toString());

			if (map.containsKey(url)) {
				HashMap<String, String> relations = map.get(url);
				relations.put(predicate, object);
				map.put(url, relations);
			} else {
				HashMap<String, String> relation = new HashMap<String, String>();
				relation.put(predicate, object);
				map.put(url, relation);
			}
		}

		generateJSON();
	}

	private static String removePrefix(String dirty) {
		for (String s : prefixes)
			if (dirty.startsWith(s))
				return dirty.replace(s, "");
		return dirty;
	}

	/**
	 * Removing schema in case we are dealing with a complete RDF Cube structure
	 * not observations file -not yet-
	 */
	private static String removeCubeSchema(String dirty) {
		for (String s : prefixes)
			if (dirty.startsWith(s))
				return dirty.replace(s, "");
		return dirty;
	}

	private static void generateJSON() throws JSONException, IOException {

		JSONArray json = new JSONArray();
		for (HashMap<String, String> h : map.values()) {
			JSONObject entity = new JSONObject();
			for (String predicate : h.keySet()) {
				/** checking NaN values */
				if (!h.get(predicate).equals("NaN"))
					entity.accumulate(predicate, h.get(predicate));
				else
					/** removing NaN values */
					entity.accumulate(predicate, "0.0");

			}
			json.put(entity);
		}

		FileWriter output;
		/**
		 * FIXME: Remove Local Dir
		 **/
		output = new FileWriter(System.getProperty("user.dir")
				+ "/src/main/resources/output/output.json");
		output.write(json.toString(2));
		System.out.print(output);
		output.flush();
	}

}