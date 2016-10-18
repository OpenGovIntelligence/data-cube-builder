package eu.opengov.cubebuilder.tarqlservices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.deri.tarql.CSVOptions;
import org.deri.tarql.StreamingRDFWriter;
import org.deri.tarql.TarqlParser;
import org.deri.tarql.TarqlQuery;
import org.deri.tarql.TarqlQueryExecution;
import org.deri.tarql.TarqlQueryExecutionFactory;
import org.openrdf.model.ValueFactory;
import org.openrdf.model.impl.ValueFactoryImpl;

import com.hp.hpl.jena.graph.Triple;

import eu.opengov.cubebuilder.util.PropertyReader;

/**
 * Tarql query formulation, and RDF Cube Schema building functions.
 * 
 * @author moh.adelrezk@gmail.com
 * */
public class TarqlFormulator {

	/**
	 * String qbSchema, is used to temporary store RDF Cube Schema formulated
	 * during the run time, till it is saved in the corresponding schema file
	 * "exampledatasetname.ttl.schema".
	 * */
	String qbSchema = "";
	PropertyReader pr=new PropertyReader();

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "prefixes", and appending it the qbSchema String
	 *
	 * @author moh.adelrezk@gmail.com
	 */

	void prefixFormulation(String marineDatasetName) {
		try {

			/**
			 * String prefixTarqlString, is temporary storing the retrieved
			 * property value "Prefixes"
			 **/
			String prefixTarqlStringFORTESTING = pr.getPropValues("IWBNetwork_Prefixes");
			/**
			 * Adding String prefixTarqlString value to the String qbSchema
			 **/
			qbSchema += prefixTarqlStringFORTESTING;

		} catch (IOException e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "dataset instance", and appending it the qbSchema String
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void dataSetFormulation(String marineDatasetName) {
		try {
			/**
			 * String dataSetTarqlString, is temporary storing the retrieved
			 * property value "Dataset"
			 **/
			String dataSetTarqlString = pr.getPropValues(marineDatasetName
					+ "_Dataset");
			/**
			 * Adding String prefixTarqlString value to the String qbSchema
			 **/
			qbSchema += dataSetTarqlString;
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "data Structure definition instance", and appending it
	 * the qbSchema String
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void dataStructureFormulation(String marineDatasetName) {
		try {
			/**
			 * String dataStructureTarqlString, is temporary storing the
			 * retrieved property value "Data_Structure_Definitions"
			 **/
			String dataStructureTarqlString = pr.getPropValues(marineDatasetName
					+ "_Data_Structure_Definitions");
			/**
			 * Adding String dataStructureTarqlString value to the String
			 * qbSchema
			 **/
			qbSchema += dataStructureTarqlString;
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "dimensions instance(s)", and appending it the qbSchema
	 * String
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void dimensionsFormulation(String marineDatasetName) {
		try {
			/**
			 * String dimensionsTarqlString, is temporary storing the retrieved
			 * property value "Dimensions"
			 **/
			String dimensionsTarqlString = pr.getPropValues(marineDatasetName
					+ "_Dimensions");
			/**
			 * Adding String dimensionsTarqlString value to the String qbSchema
			 **/
			qbSchema += dimensionsTarqlString;
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "measures instance(s)", and appending it the qbSchema
	 * String
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void measuresFormulation(String marineDatasetName) {
		try {
			/**
			 * String measuresTarqlString, is temporary storing the retrieved
			 * property value "Measures"
			 **/
			String measuresTarqlString = pr.getPropValues(marineDatasetName
					+ "_Measures");
			/**
			 * Adding String measuresTarqlString value to the String qbSchema
			 **/
			qbSchema += measuresTarqlString;
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function/method in used for creating the "dataset's" corresponding
	 * RDF cube schema "slices instance(s)", and appending it the qbSchema
	 * String
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void slicesFormulation(String marineDatasetName) {// not supported yet
		try {
			/**
			 * String slicesTarqlString, is temporary storing the retrieved
			 * property value "Slices"
			 **/
			String slicesTarqlString = pr.getPropValues(marineDatasetName
					+ "_Slices");
			/**
			 * Adding String slicesTarqlString value to the String qbSchema
			 **/
			qbSchema += slicesTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {

			System.out.println(e.getMessage());
		}
	}

	/**
	 * This function/method in used for calling all other functions to start
	 * building the dataset's corresponding Cube Schema
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void qbSchemaCreation(String marineDatasetName) {
		prefixFormulation(marineDatasetName);
		dataSetFormulation(marineDatasetName);
		dataStructureFormulation(marineDatasetName);
		dimensionsFormulation(marineDatasetName);
		measuresFormulation(marineDatasetName);
		// slicesFormulation(marineDatasetName); //not supported yet
	}

	/**
	 * This function/method in used for storing the dataset's corresponding Cube
	 * Schema to a schema file "exampledatasetname.ttl.schema"
	 *
	 * @author moh.adelrezk@gmail.com
	 */
	void qbSchemaFileCreation(String qbPath, String qbFileName) {
		/**
		 * PrintWriter schemaFile, is the file where the schema will be stored,
		 * "CubePath example:{base.dir}/output/datasetname.ttl",
		 * "final file name and path example:{base.dir}/output/datasetname.ttl.schema"
		 */

		try (PrintWriter schemaFile = new PrintWriter(qbPath + qbFileName
				+ ".schema")) {
			/**
			 * Printing String qbSchema value to the new schema file
			 */
			schemaFile.println(qbSchema);
		} catch (FileNotFoundException e) {

			System.out.println(e.getMessage());
		}

	}

	/**
	 * This function/method in used for merging and storing the dataset's
	 * corresponding Cube Schema to a schema file
	 * "example: datasetname.ttl.schema", and the observations file
	 * "example: datasetname.ttl.observations"
	 * 
	 * @author moh.adelrezk@gmail.com
	 */
	void mergingSchemaFileWithObservationFile(String qbPath, String qbFileName) {
		/**
		 * String MergedFilePath, the final corresponding dataset's RDF Cube
		 * path and file name "example: {base.dir}/output/datasetname.ttl"
		 */
		String MergedFilePath = qbPath + qbFileName;
		/**
		 * String schemaFilePath, the corresponding dataset's RDF Cube schema
		 * path and file name
		 * "example: {base.dir}/output/datasetname.ttl.schema"
		 */
		String schemaFilePath = qbPath + qbFileName + ".schema";
		/**
		 * String ObservationFilePath, the corresponding dataset's RDF Cube
		 * observations path and file name
		 * "example: {base.dir}/output/datasetname.ttl.observations"
		 */
		String ObservationFilePath = qbPath + qbFileName + ".observations";
		// ".nt" and ".ttl" should revisit and make it non static

		/**
		 * Merging steps
		 */
		BufferedWriter merged = null;
		BufferedReader schema = null;
		BufferedReader observations = null;
		try {
			merged = new BufferedWriter(new FileWriter(MergedFilePath));
			schema = new BufferedReader(new FileReader(schemaFilePath));
			observations = new BufferedReader(new FileReader(
					ObservationFilePath));
			String line = null;

			while ((line = schema.readLine()) != null) {
				merged.write(line);
				merged.write("\n");
			}
			while ((line = observations.readLine()) != null) {
				merged.write(line);
				merged.write("\n");
			}
		} catch (IOException e) {
			System.out.println(e.getMessage());

		} finally {
			if (merged != null)
				try {
					merged.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}
			if (schema != null)
				try {
					schema.close();
				} catch (IOException e) {
					System.out.println(e.getMessage());
				}

		}
		if (observations != null)
			try {
				observations.close();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
	}

	/**
	 * 
	 * This function/method in used for storing the dataset's corresponding RDF
	 * Cube observations to an observations file
	 * "exampledatasetname.ttl.observations"
	 * 
	 * @See In the second approach a single observation can provide values for
	 *      multiple different measures. This is particularly appropriate in
	 *      cases where each of those values relates to a single observational
	 *      event such as a multi-spectral sensor measurement. This
	 *      multi-measure approach is commonly used in applications such as
	 *      Business Intelligence and OLAP.
	 * 
	 * @See https://www.w3.org/TR/vocab-data-cube/#dsd-mm
	 * 
	 * @author moh.adelrezk@gmail.com
	 * */
	void CustomizedobservationsCapture(List<String> Dimensions,
			List<String> Measures, List<String> Metrics/* For schema only */) {
		/**
		 * This part of code will be used when the user supply his cutomized
		 * schema, dim, and measures but not for the fixed part
		 */
		String observTarqlString = "?observation a qb:observation;"
				+ "obs:time ?time;"
				+ "obs:longitude ?longitude"
				+ "obs:latitude ?latitude"
				+ "obs:AtmosphericPressure ?AtmosphericPressure"
				+ "obs:WindDirection ?WindDirection"
				+ "obs:WindSpeed ?WindSpeed"
				+ "obs:Gust ?Gust"
				+ "obs:WaveHeight ?WaveHeight"
				+ "obs:WavePeriod ?WavePeriod"
				+ "obs:MeanWaveDirection ?MeanWaveDirection"
				+ "obs:Hmax ?Hmax"
				+ "obs:AirTemperature ?AirTemperature"
				+ "obs:DewPoint ?DewPoint"
				+ "obs:SeaTemprature ?SeaTemprature"
				+ "obs:salinty ?salinty"
				+ "obs:RelativeHumadity ?RelativeHumadity"
				+ "obs:QC_Flag ?QC_Flag"
				+ "obs:station_id ?station_id"
				+ "."
				+ "}"
				+ "# FROM <file:datasetxx.csv>"
				+ "WHERE {"
				+ "BIND (URI(CONCAT('http://ogi.eu/observations/', ?obsid)) AS ?observation)"
				+ "}"

				+ "}";
	}

	/**
	 * This function/method in used to run the whole thing when the user supply
	 * the necessary parameters to generate the cube
	 * 
	 * @author moh.adelrezk@gmail.com
	 * */

	public void tarqlAsLibraryExecution(String csvFilePath, String qbPath,
			String qbFileName, String dimOrMeasures, String marineDatasetName,
			String serialization) throws IOException {

		/**
		 * String workingDir, used to relatively locate tarql and run tarql
		 * queries
		 *
		 * */
		// String workingDir = System.getProperty("user.dir");

		CSVOptions options = new CSVOptions();
		String delimiter = "";
		String encoding = "";
		String escapeChar = "";
		String quoteChar = "";
		String headerRow = "";

		// Delimiter
		if (StringUtils.isNotBlank(delimiter)) {
			if (delimiter.equals("tab")) {
				options.setDelimiter('\t');
			} else {
				options.setDelimiter(delimiter.charAt(0));
			}
		}

		// Encoding
		if (StringUtils.isNotBlank(encoding)
				&& (!encoding.equals("Autodetect"))) {
			options.setEncoding(encoding);
		}

		// Escape Char
		if (StringUtils.isNotBlank(escapeChar) && (!encoding.equals("None"))) {
			options.setEscapeChar(escapeChar.charAt(0));
		}

		// Quote Char
		if (StringUtils.isNotBlank(quoteChar)) {
			options.setQuoteChar(quoteChar.charAt(0));
		}

		// Header Row (default TRUE)
		if (StringUtils.isNotBlank(headerRow) && headerRow.equals("no")) {
			options.setColumnNamesInFirstRow(false);
		}
		/*
		 * Tarql execution stages
		 */

		TarqlQuery tq = new TarqlParser(new StringReader(
				pr.getPropValues(marineDatasetName + "_Query")), null).getResult();

		TarqlQueryExecution ex = TarqlQueryExecutionFactory.create(tq,
				csvFilePath, options);
		// TarqlQueryExecution ex = TarqlQueryExecutionFactory.
		Iterator<Triple> triples = ex.execTriples();

		ValueFactory factory = new ValueFactoryImpl();

		/**
		 * Printing String observation value to the new observations file
		 */
		try (OutputStream File = new FileOutputStream(qbPath + qbFileName
				+ ".observations")) {

			while (triples.hasNext()) {
				StreamingRDFWriter writer = new StreamingRDFWriter(File,
						triples);
				if (serialization.equalsIgnoreCase("ntriples")) {
					writer.writeNTriples();
				} else {
					writer.writeTurtle(tq.getPrologue().getBaseURI(), tq
							.getPrologue().getPrefixMapping());
				}

			}
			System.out.println("Waiting For Observation Capture!");
		} catch (FileNotFoundException e) {

			/**
			 * Logging will be added
			 **/
			System.out
					.println("Warning: Somthing went wrong at Observation Capture Stage!");
			System.out.println(e.getMessage());

		}
		/**
		 * creating the corresponding qbschema String
		 * */
		System.out.println("Creating Cube Schema!");
		qbSchemaCreation(marineDatasetName);
		/**
		 * creating the corresponding .schema file
		 * */
		qbSchemaFileCreation(qbPath, qbFileName);
		/**
		 * releasing/flushing String qbSchema, to be used again in the same UI
		 * session
		 * */
		qbSchema = "";
		/**
		 * Creating the corresponding RDF Cube file, by merging .schema file and
		 * .observation file
		 * */
		System.out.println("Creating Full Cube!");
		mergingSchemaFileWithObservationFile(qbPath, qbFileName);

	}

	

	public void tarqlExecution_bak(String csvFilePath, String qbPath,
			String qbFileName, String dimOrMeasures, String marineDatasetName,
			String serialization) {
		/**
		 * String workingDir, used to relatively locate tarql and run tarql
		 * queries
		 *
		 * */
		String workingDir = System.getProperty("user.dir");
		/**
		 * Runtime rt, used to run command line tarql queries .
		 *
		 */
		Runtime rt = Runtime.getRuntime();
		// String runTarql =
		// "sh ~/workspace/tarql/target/appassembler/bin/tarql  --ntriples ~/datasets/tarql/test5.sparql ~/datasets/tarql/IWBNetwork_ab94_01cb_752f.csv > ~/datasets/out/out5.nt";
		/**
		 * String runTarql, building and storing command line tarql query. "sh"
		 * ==>> to enable command line through java. workingDir+
		 * "/tarql-run/tarql/target/appassembler/bin/tarql ==>> tarql library
		 * location --"+ serlization + "
		 * " + ==>> Turtle or Ntriples (turtle is default " ") workingDir +
		 * "/tarql-queries/"+ marineDatasetName + ".sparql " ==>> stored tarql
		 * query (.sparql) CsvPath + ==>> data set name and location as inserted
		 * by user. " > " + CubePath+ ".observations"; ==>> output file name and
		 * location of the observations
		 */
		String runTarql = "sh " + workingDir
				+ "/tarql/target/appassembler/bin/tarql  --" + serialization
				+ " " + workingDir + "/src/main/resources/tarqlQueries/"
				+ marineDatasetName + ".sparql " + csvFilePath + " > " + qbPath
				+ qbFileName + ".observations";
		System.out.println("Tarql query:\n" + runTarql);

		// String permissions = "chmod 777 -R ~/datasets/out";
		/**
		 * String whole[], storing the tarql query in an array with bash and -c
		 * to be passed to a process
		 */
		String whole[] = new String[] { "bash", "-c", runTarql };

		// String test = "cat ~/datasets/out > text.txt";
		try {
			/**
			 * Process pr , runnnig the command line tarql query in a parallel
			 * thread
			 */
			Process pr = rt.exec(whole);
			/**
			 * waiting for the query to be executed and the observations file is
			 * ready for next steps, and to avoid null pointer exception
			 */
			pr.waitFor();
			// pr = rt.exec(test);
			System.out.println("Waiting For Observation Capture!");

		} catch (IOException | InterruptedException e) {

			System.out
					.println("Warning: Somthing went wrong at Observation Capture!");

			System.out.println(e.getMessage());

		}

		/**
		 * creating the corresponding qbschema String
		 * */
		System.out.println("Creating Cube Schema!");
		qbSchemaCreation(marineDatasetName);
		/**
		 * creating the corresponding .schema file
		 * */
		qbSchemaFileCreation(qbPath, qbFileName);
		/**
		 * releasing/flushing String qbSchema, to be used again in the same UI
		 * session
		 * */
		qbSchema = "";
		/**
		 * Creating the corresponding RDF Cube file, by merging .schema file and
		 * .observation file
		 * */
		System.out.println("Creating Full Cube!");
		mergingSchemaFileWithObservationFile(qbPath, qbFileName);

	}
}
