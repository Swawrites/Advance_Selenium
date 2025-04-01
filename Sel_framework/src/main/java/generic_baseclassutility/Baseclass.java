package generic_baseclassutility;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import generic_utilities.Database_Utility;
import generic_utilities.Properties_File_Utility;
import objectrepositary.DashboardPage;
import objectrepositary.LoginPage;

import org.testng.annotations.AfterTest;

public class Baseclass
{
//	@BeforeSuite
//	public void beforeSuite() {
//		System.out.println("Establishing DB connection");
//	}
//	
//	@AfterSuite
//	public void afterSuite() {
//		System.out.println("Close DB connection");
//	}
//	
//	@BeforeClass
//	public void beforeClass() {
//		System.out.println("Launch the browser");
//	}
//	
//	@AfterClass
//	public void afterClass() {
//		System.out.println("Close the browser");
//	}
//	
//	@BeforeMethod
//	public void beforeMethod() {
//		System.out.println("Login");
//	}
//	
//	@AfterMethod
//	public void afterMethod() {
//		System.out.println("Logout");
//	}
//	
//	@BeforeTest
//	public void beforeTest() {
//		System.out.println("Pre Configuration Setup");
//	}
//	
//	@AfterTest
//	public void afterTest() {
//		System.out.println("Post Configuration Setup");
//	}
	
	public WebDriver driver = null;
	public static WebDriver sdriver = null;
	Database_Utility dbUtil = new Database_Utility();
	Properties_File_Utility pro = new Properties_File_Utility();
	
	
	
	@BeforeSuite
	public void beforeSuite() throws SQLException
	{
		System.out.println("Established database connection");
		dbUtil.getDBConnection("jdbc:mysql://localhost:3306/Ninza_store", "root",
				"bangtan#swa2712");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("Pre config setup");
	}
	
	@BeforeClass
	public void beforeClass() throws IOException {
		System.out.println("Launch the browser");
		String browser = pro.readingDataFromPropertyFile("browser");
		ChromeOptions coptions = new ChromeOptions();
		EdgeOptions eoptions = new EdgeOptions();
		FirefoxOptions foptions = new FirefoxOptions();
		coptions.addArguments("--headless");
		eoptions.addArguments("--headless");
		foptions.addArguments("--headless");
		
	
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			driver = new EdgeDriver();
		}
		sdriver=driver;
		
	}
	
	@BeforeMethod
	public void beforeMethod() throws IOException {
		System.out.println("Login");
		String url = pro.readingDataFromPropertyFile("url");
		String UN = pro.readingDataFromPropertyFile("username");
		String PW = pro.readingDataFromPropertyFile("password");
		driver.get(url);
		LoginPage lp = new LoginPage(driver);
		lp.login(UN, PW);
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("Logout");
		DashboardPage dp = new DashboardPage(driver);
		dp.logout();
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Close the browser");
		driver.quit();
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("Post configuration setup");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("Close DB conncetion");
		dbUtil.closeDBConnection();
	}
	
}
