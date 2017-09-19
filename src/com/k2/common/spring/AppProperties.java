package com.k2.common.spring;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties extends Properties{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1384162412303025877L;

	public AppProperties(File propertiesFile) {
		try {
			if (propertiesFile == null) { throw new ApplicationError("The properties file cannot be null when creating a new application properties instance!"); }
			if (!propertiesFile.exists()) { throw new ApplicationError("The properties file "+propertiesFile.getCanonicalPath()+" must exist when creating a new application properties instance!"); }
			if (!propertiesFile.canRead()) { throw new ApplicationError("The properties file "+propertiesFile.getCanonicalPath()+" must be readable when creating a new application properties instance!"); }

			InputStream input = new FileInputStream(propertiesFile);
		
			load(input);
		} catch (IOException e) {
			// This shouldn't happen checked above
			e.printStackTrace();
			try {
				throw new ApplicationError("Unable to open the properties file for reading: "+propertiesFile.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw new ApplicationError("Unable to open the properties file for reading: "+propertiesFile.getAbsolutePath());
			}
		}
	
	
	}

}
