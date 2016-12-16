package eu.opengov.cubebuilder.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	/**
	 * This function/method in used to Retrieve Properties values (mainly
	 * datasets' RDF cube schema components)
	 * 
	 * @author moh.adelrezk@gmail.com
	 * */
	public String getPropValues(String propertyName) throws IOException {

		String result = "";
		InputStream inputStream = null;
		// String WorkingDir = System.getProperty("user.dir");

		try {

			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(propFileName);

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
