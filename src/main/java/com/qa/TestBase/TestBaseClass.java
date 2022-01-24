package com.qa.TestBase;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBaseClass {

		public static WebDriver driver;
		public static Logger logger;
		
		//Instance of utility classes
		ReadConfig readconfig = new ReadConfig();
		
		
		 
		
		//Common Data 
		public String baseURL = readconfig.getUrl();
		public String username = readconfig.getUserName();
		public String password = readconfig.getPassword();
		public String product_name = readconfig.productName();
		public String product_quantity = readconfig.productQuantity();
		public String barcode = readconfig.barcode();
		
		@Parameters("browser")
		@BeforeMethod
		
		public void setUp(String browsername)
		{
			//Launch Browser
			if(browsername.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromiumdriver().setup();
				driver=new ChromeDriver();
			}
			else if(browsername.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else
			{
				System.out.println("Plese enter a Valid BrowserName");
			}
			
			
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(baseURL);
//			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			logger = Logger.getLogger(TestBaseClass.class);
			PropertyConfigurator.configure("log4j.properties");
			
		}
		
		@AfterMethod
		public void tearDown()
		{
			driver.quit();
		}

	}

