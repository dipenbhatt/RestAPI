package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public static Properties prop;
	public int RESPONSE_STATUS_CODE_200=200;
	public int RESPONSE_STATUS_CODE_400=400;
	public int RESPONSE_STATUS_CODE_300=300;
	public int RESPONSE_STATUS_CODE_401=401;
	public int RESPONSE_STATUS_CODE_201=201;
	
	 public TestBase() {
	try {
		 prop=new Properties();
		FileInputStream ip=new FileInputStream("C:\\QA\\SeleniumWorkSpace\\restapi\\src\\main\\java\\com\\qa\\config\\config.properties");
		
		prop.load(ip);
	} catch(FileNotFoundException e) {
		   e.printStackTrace();
	        }
	catch (IOException e) 
	{
		e.printStackTrace();
		
	}
	 }
}
