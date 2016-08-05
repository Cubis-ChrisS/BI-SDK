package rest.json;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BOPropertyValues {
	
	InputStream inputStream;
	Properties prop;
	
	public BOPropertyValues() {
		
		Properties prop = new Properties();
		try {
			this.prop = this.setPropValues(prop);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public Properties setPropValues(Properties prop) throws IOException {
		

	
		try {
			

			String propFileName = "config.properties";
 
			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
			// get the property value and print it out
			

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		
		return prop;

	}
	
	public Properties getPropValues() {
		return this.prop;
	}

}
