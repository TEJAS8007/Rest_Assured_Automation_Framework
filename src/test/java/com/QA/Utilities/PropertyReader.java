package com.QA.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	static Properties pro;

	public static Properties Init_Properties() {
		
		try {
			FileInputStream file=new FileInputStream("src/test/resources/Token_API/Token.properties");
			pro=new Properties();
			pro.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pro;
	}
}
