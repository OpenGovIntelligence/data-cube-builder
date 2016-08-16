package ui;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;

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

	/**
	 * main method used only for testing functions/methods, rather than running
	 * the whole thing
	 */

	// public static void main(String[] args) {
	//
	// TarqlFormulator t=new TarqlFormulator();
	// String CubePath= "/home/mohade/datasets/out/test66.nt";
	// String marineDatasetName= "IWBNetwork";
	//
	// t.prefixFormulation(marineDatasetName);
	// t.dataSetFormulation(marineDatasetName);
	// t.dataStructureFormulation(marineDatasetName);
	// t.dimensionsFormulation(marineDatasetName);
	// t.measuresFormulation(marineDatasetName);
	// t.slicesFormulation(marineDatasetName);
	// System.out.println(t.qbSchema);
	// t.qbSchemaAppending(t.qbSchema, CubePath);

	// tarqlExcution();
	// t.mergingSchemaFileWithObservationFile("/home/mohade/workspace/OGI1/output/ds3.ttl");
	// }

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
			// String prefixTarqlString = getPropValues(marineDatasetName
			// + "_Prefixes");
			/**
			 * String prefixTarqlString, is temporary storing the retrieved
			 * property value "Prefixes"
			 **/
			String prefixTarqlStringFORTESTING = getPropValues("IWBNetwork_Prefixes");
			// System.out.println(prefixTarqlString);
			/**
			 * Adding String prefixTarqlString value to the String qbSchema
			 **/
			qbSchema += prefixTarqlStringFORTESTING;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
			String dataSetTarqlString = getPropValues(marineDatasetName
					+ "_Dataset");
			/**
			 * Adding String prefixTarqlString value to the String qbSchema
			 **/
			qbSchema += dataSetTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
			String dataStructureTarqlString = getPropValues(marineDatasetName
					+ "_Data_Structure_Definitions");
			/**
			 * Adding String dataStructureTarqlString value to the String
			 * qbSchema
			 **/
			qbSchema += dataStructureTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
			String dimensionsTarqlString = getPropValues(marineDatasetName
					+ "_Dimensions");
			/**
			 * Adding String dimensionsTarqlString value to the String qbSchema
			 **/
			qbSchema += dimensionsTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
			String measuresTarqlString = getPropValues(marineDatasetName
					+ "_Measures");
			/**
			 * Adding String measuresTarqlString value to the String qbSchema
			 **/
			qbSchema += measuresTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
			String slicesTarqlString = getPropValues(marineDatasetName
					+ "_Slices");
			/**
			 * Adding String slicesTarqlString value to the String qbSchema
			 **/
			qbSchema += slicesTarqlString;
			// System.out.println(qbSchema);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
	void qbSchemaFileCreation(String qbSchema, String CubePath) {
		/**
		 * PrintWriter schemaFile, is the file where the schema will be stored,
		 * "CubePath example:{base.dir}/output/datasetname.ttl",
		 * "final file name and path example:{base.dir}/output/datasetname.ttl.schema"
		 */
		try (PrintWriter schemaFile = new PrintWriter(CubePath + ".schema")) {
			/**
			 * Printing String qbSchema value to the new schema file
			 */
			schemaFile.println(qbSchema);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
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
	void mergingSchemaFileWithObservationFile(String CubePath) {
		/**
		 * String MergedFilePath, the final corresponding dataset's RDF Cube
		 * path and file name "example: {base.dir}/output/datasetname.ttl"
		 */
		String MergedFilePath = CubePath;
		/**
		 * String schemaFilePath, the corresponding dataset's RDF Cube schema
		 * path and file name
		 * "example: {base.dir}/output/datasetname.ttl.schema"
		 */
		String schemaFilePath = CubePath + ".schema";
		/**
		 * String ObservationFilePath, the corresponding dataset's RDF Cube
		 * observations path and file name
		 * "example: {base.dir}/output/datasetname.ttl.observations"
		 */
		String ObservationFilePath = CubePath + ".observations";
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
			e.printStackTrace();

		} finally {
			if (merged != null)
				try {
					merged.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (schema != null)
				try {
					schema.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (observations != null)
				try {
					observations.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
	void observationsCapture(List<String> Dimensions, List<String> Measures,
			List<String> Metrics/* For schema only */) {
		/**
		 * This part of code will be used when the user supply its schema, dim,
		 * and measures but not for the fixed part
		 */
		String observTarqlString1 = "?observation a qb:observation;";
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
	public void tarqlExcution(String CsvPath, String CubePath,
			String DimOrMeasures, String marineDatasetName, String serlization) {
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
		// "sh /home/mohade/workspace/tarql/target/appassembler/bin/tarql  --ntriples /home/mohade/datasets/tarql/test5.sparql /home/mohade/datasets/tarql/IWBNetwork_ab94_01cb_752f.csv > /home/mohade/datasets/out/out5.nt";
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
				+ "/tarql-run/tarql/target/appassembler/bin/tarql  --"
				+ serlization + " " + workingDir + "/tarql-queries/"
				+ marineDatasetName + ".sparql " + CsvPath + " > " + CubePath
				+ ".observations";

		// String permissions = "chmod 777 -R /home/mohade/datasets/out";
		/**
		 * String whole[], storing the tarql query in an array with bash and -c
		 * to be passed to a process
		 */
		String whole[] = new String[] { "bash", "-c", runTarql };

		// String test = "cat /home/mohade/datasets/out > text.txt";
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
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			/**
			 * Logging will be added
			 **/
			e.printStackTrace();
		}

		/**
		 * creating the corresponding qbschema String
		 * */
		qbSchemaCreation(marineDatasetName);
		/**
		 * creating the corresponding .schema file
		 * */
		qbSchemaFileCreation(qbSchema, CubePath);
		/**
		 * releasing/flushing String qbSchema, to be used again in the same UI
		 * session
		 * */
		qbSchema = "";
		/**
		 * Creating the corresponding RDF Cube file, by merging .schema file and
		 * .observation file
		 * */
		mergingSchemaFileWithObservationFile(CubePath);

	}

	/**
	 * This function/method in used to Retrieve Properties values (mainly
	 * datasets' RDF cube schema components)
	 * 
	 * @author moh.adelrezk@gmail.com
	 * */
	public String getPropValues(String propertyName) throws IOException {

		String result = "";
		InputStream inputStream = null;

		try {

			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = getClass().getClassLoader().getResourceAsStream(
					propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '"
						+ propFileName + "' not found in the classpath");
			}
			result = prop.getProperty(propertyName);

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;

	}
}
/* /home/mohade/workspace/OGI1/data/IWBNetwork_9792_1ea4_7393.csv */
/*
 * $ mkdir ./DB $ ./fuseki-server --update --loc=DB /ds
 */

/*
 * $ ./fuseki-server --update --mem /ds
 */

/*
 * http://localhost:3030/
 */
/*
 * open fuseki-server with an editor you'll find the line
 * JVM_ARGS=${JVM_ARGS:--Xmx1200M} modify it to JVM_ARGS=${JVM_ARGS:--Xmx2048M}
 */

/*
 * sudo kill -9 {PID of java}
 */
