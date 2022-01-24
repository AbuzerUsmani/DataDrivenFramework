package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import utility.DbManager;
import utility.ExcelReader;
import utility.monitoringMail;

public class BaseTest {
	
	/*
	 * WebDriver
	 * Excel
	 * Logs
	 * Properties
	 * Mail
	 * JDBC
	 * 
	 */
	public static WebDriver driver;
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\testdata.xlsx");
	public static Properties OR = new Properties();
	public static Properties Config = new Properties();
	public static FileInputStream fis;
	public static monitoringMail mail = new monitoringMail();
	public static Logger log = Logger.getLogger("devpinoyLogger");
	
	
	@BeforeSuite
	public void setUp(){
		
		
		
		if(driver==null){
			
			
			
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("Config property file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR property file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Config.getProperty("browser").equals("firefox")){
				
				driver = new FirefoxDriver();
				
			}else if(Config.getProperty("browser").equals("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(Config.getProperty("browser").equals("ie")){
				
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\executables\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
				
			}
			
			WebDriverWait wait = new WebDriverWait(driver,10);
			
			driver.get(Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
			try {
				DbManager.setMysqlDbConnection();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
	}
	
	
	
	public static boolean isElementPresent(String css){
		
		try{
			
			driver.findElement(By.cssSelector(css));
			return true;
			
		}catch(Throwable t){
			
			return false;
		}
		
	}
	
	@AfterSuite
	public void tearDown(){
		
		driver.quit();
		
	}
	
	

}
