package stepDefinitions;

import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import pageObjects.addCustomerPage;
import pageObjects.loginPage;
import pageObjects.searchCustomerPage;

public class baseClass {

	public WebDriver driver;
	public loginPage lp;
	public addCustomerPage addCust;
	public searchCustomerPage searchCust;
	public static Logger logger;
	Properties configProp;

	// Created for generating random string for Unique email
	public static String randomestring(){
			String generatedString1 = RandomStringUtils.randomAlphabetic(5);
			return (generatedString1);
	}
	
	public static Logger getLogger() 
	{		 
		logger=LogManager.getLogger(); //Log4j
		return logger;
	}
}
