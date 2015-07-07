package org.beadle.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyParser {
	private static Properties prop = new Properties();
	
	static{
	    InputStream in = PropertyParser.class.getClassLoader().getResourceAsStream("config/project.properties");   
        try {
			prop.load(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String get(String key){
        return prop.getProperty(key).trim();
	}
	
	public static String[] getArgs(String key){
        return prop.getProperty(key).trim().split(";");
	}
	
	public static void main(String args[]){
		String[] urlPermmited = PropertyParser.getArgs("urlPermitted");
		System.out.println(urlPermmited.length);
		for(String str : urlPermmited){
			System.out.println(str);
		}
	}
}
