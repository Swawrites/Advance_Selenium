package selframework;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import generic_baseclassutility.Baseclass;
import generic_listenerutility.ListenerImp;
import generic_utilities.Properties_File_Utility;
import generic_utilities.WebDriverUtility;
import objectrepositary.DashboardPage;
import objectrepositary.LoginPage;

//@Listeners(ListenerImp.class)
public class LoginandLogoutTest extends Baseclass{
	
	@Test
	//@Parameters("browser")
	//@Test(groups = {"Regression Test"})
	public void loginLogoutTest() throws IOException {
		//public void loginLogoutTest(String browserr) throws IOException {
		// get the data from the properties utility file
//		Properties_File_Utility prop = new Properties_File_Utility();
//		String browser = prop.readingDataFromPropertyFile("browser");
//		//String browser = browserr;
//		String url = prop.readingDataFromPropertyFile("url");
//		String username = prop.readingDataFromPropertyFile("username");
//		String password = prop.readingDataFromPropertyFile("password");

		// Object of web driver utility
//		WebDriverUtility utilobj = new WebDriverUtility();
//		WebDriver driver = utilobj.chooseBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.get(url);

//		LoginPage lp = new LoginPage(driver);
//		lp.login(username, password);
		String expectedURL = "http://49.249.28.218:8098/dashboard";
		String actualURL = driver.getCurrentUrl();
		//The messgae given "Validation failed will be printed only when the test case is failed
		Assert.assertEquals(actualURL,expectedURL,"Validation failed");
		Reporter.log("Validation is passed", true);
//		if(actualURL.contains(expectedURL)) {
//			Reporter.log("Validation passed", true);
//		}else {
//			Reporter.log("Validation failed", true);
//		}
		Reporter.log("Logged in Successfully", true);

//		DashboardPage dp = new DashboardPage(driver);
//		dp.logout();
		Reporter.log("Logged out Successfully", true);
		System.out.println("*************************");
		//driver.quit();
	}
}