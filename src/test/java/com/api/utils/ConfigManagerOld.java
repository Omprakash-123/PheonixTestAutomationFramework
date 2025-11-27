package com.api.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {

	// want to read properties file from src/test/resources/config/config.properties
	// Special Class: Properties
	private static Properties prop = new Properties();

	static {
		//Operation of loading the properties file in the memeory
		//Static block it will executed during the class loading time
		File configFile = new File(
				// load the properties file using the load()
				System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"
						+File.separator+"resources"+File.separator+"config"+File.separator+"config.properties");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) throws IOException {
		// Special Class: Properties

		// load the properties file using the load()
		return prop.getProperty(key);
	}
}
