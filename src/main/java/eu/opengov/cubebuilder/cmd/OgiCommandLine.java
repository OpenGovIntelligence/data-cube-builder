package eu.opengov.cubebuilder.cmd;

import java.util.HashMap;
import java.util.logging.Logger;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import eu.opengov.cubebuilder.tarqlservices.TarqlFormulator;
/**
 * 
 * @author moh.adelrezk@gmail.com
 * */
@Parameters(separators = ":")
public class OgiCommandLine {

	private static final Logger LOGGER = Logger.getLogger(OgiCommandLine.class
			.getName());
	public static JCommander Jcomm;
	public static TarqlFormulator tarqlformulator;

	@Parameter(names = { "--help", "-help", "-h" }, description = "Help", help = true)
	private boolean help;
	@Parameter(names = { "--csvFilePath", "-csv" }, description = "CSV input file Location and name")
	String csvFilePath;
	@Parameter(names = { "--marineDatasetName", "-schema" }, description = "Data Set Schema Currently Supporting (IWaveBNetwork30Min, IMI_EATL_WAVE, IrishNationalTideGaugeNetwork and IWBNetwork)")
	String marineDatasetName;// Data Set schema
	@Parameter(names = { "--serialization", "-format" }, description = "Output Cube RDF serlization format (turtle or ntriples)")
	String serialization = "turtle";// space if ttl
	@Parameter(names = { "--qbPath", "-qb" }, description = "Cube output file Location and name")
	String qbPath;
	@Parameter(names = { "--qbFileName", "-qbN" }, description = "Cube output file Location and name")
	String qbFileName;
	@Parameter(names = { "--dimOrMeasures", "-l" }, description = "Customized Dim and Measures are Not Available at this stage")
	String dimOrMeasures;

	// @DynamicParameter(names = "-D", description =
	// "Dynamic parameters go here")
	// public Map<String, String> dynamicParams = new HashMap<String, String>();

	public static void main(String[] args) {

		OgiCommandLine cmd = new OgiCommandLine();
		tarqlformulator = new TarqlFormulator();
		Jcomm = new JCommander(cmd, args);
		Jcomm.setProgramName("OGI EU");
		try {
			cmd.run();
		} catch (Exception ex) {
			Jcomm.usage();

			System.out.println("Error:" + ex.getMessage());

		}

	}

	public void run() {

		if (help == true) {
			Jcomm.usage();
		} else {

			try {
				if (serialization.equalsIgnoreCase("turtle"))
					tarqlformulator.tarqlAsLibraryExecution( csvFilePath,  qbPath,
							 qbFileName,  dimOrMeasures,  marineDatasetName,
							 serialization);
				else
					tarqlformulator.tarqlAsLibraryExecution( csvFilePath,  qbPath,
							 qbFileName,  dimOrMeasures,  marineDatasetName,
							 serialization);
			} catch (Exception ex) {
				Jcomm.usage();
				System.out.println("Error:" + ex.getMessage());
			}

			System.out.println("Check Cube output location:" + qbPath+qbFileName);
		}
	}
}
