package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import WebDriverCommands.ActionDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import util.TestUtil;


public class LaunchBrowser {
	
	public static WebDriver driver;
	String method;
	ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest parentTest;
	public static ExtentTest childTest;
	public static Properties prop;
	
	public LaunchBrowser() {
					
			
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
	


	@BeforeTest
	public void generateReport() {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/MyReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}
	
	@BeforeMethod
	public void methodname(Method method) {
		parentTest = extent.createTest(method.getName());
		
	}
	
	
	@BeforeClass
	public void beforeClass() {
	  
		String browserName = prop.getProperty("browser");
		
		if(browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();	
			  System.out.println("BinaryPath: "+WebDriverManager.firefoxdriver().getBinaryPath());		//It gives downloaded driver path
			  System.out.println("Downloaded Version: "+ WebDriverManager.firefoxdriver().getDownloadedVersion());	//It gives downloaded driver version
			  driver = new FirefoxDriver();
		}
		else if(browserName.equals("chrome")) {
			  WebDriverManager.chromedriver().setup();
			  System.out.println("BinaryPath: "+WebDriverManager.chromedriver().getBinaryPath());		//It gives downloaded driver path
			  System.out.println("Downloaded Version: "+ WebDriverManager.chromedriver().getDownloadedVersion());	//It gives downloaded driver version
			  driver = new ChromeDriver();
		}
		  
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		  
	  }
	
	
	  @AfterClass
		public void CloseBrowser() {
	  	driver.quit();
	  	extent.flush();
	  }
	
	
}
