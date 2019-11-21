package Reuse;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import WebDriverCommands.ActionDriver;
import base.LaunchBrowser;

public class BusinessFunctions {

	WebDriver driver;
	ActionDriver adriver;
	public static Properties prop;
	
	
	public BusinessFunctions() {
		driver = LaunchBrowser.driver;
		adriver = new ActionDriver();
		
			
			
			try {
				prop = new Properties();
				FileInputStream ip = new  FileInputStream("F:\\Kumar\\Akhil\\Automation\\src\\main\\java\\config\\config.properties");
				prop.load(ip);
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
				
			}
		}

	
	
	public void goToApplication() throws Exception {
		LaunchBrowser.childTest = LaunchBrowser.parentTest.createNode("Navigate to CMS");
		adriver.navigateToApplication(prop.getProperty("url"));
		Thread.sleep(3000);
	}
	
	
}
