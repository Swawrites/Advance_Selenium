package selframework;

import java.io.IOException;
import java.time.Duration;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import generic_baseclassutility.Baseclass;
import generic_listenerutility.ListenerImp;
import generic_utilities.Excel_File_Utility;
import generic_utilities.Java_File_Utility;
import objectrepositary.CampaignsPage;
import objectrepositary.CreateCampaigns;
import objectrepositary.DashboardPage;

//@Listeners(ListenerImp.class)
public class Create_Campaign extends Baseclass{
	
	@Test
	//@Parameters("browser")
	//@Test(groups = {"Regression Test"})
	//@Test(priority=0)
	//@Test(invocationCount = 0) //or @Test(invocationCount = -1) negative also same
	//@Test(invocationCount = 4, threadPoolSize = 5)
	// We can also give like @Test(groups = {"Smoke test"}, priority = 1, invocation count = 2)
	//@Test(enabled = true)
	//@Test(dependsOnMethods = "createCampaignTestWithMandatoryFields()")
	public void createCampaignTest() throws InterruptedException, IOException {
		//public void createCampaignTest(String browserr) throws InterruptedException, IOException {
		// get the data from the properties utility file
//		Properties_File_Utility prop = new Properties_File_Utility();
//		String browser = prop.readingDataFromPropertyFile("browser");
//		//String browser = browserr;
//		String url = prop.readingDataFromPropertyFile("url");
//		String username = prop.readingDataFromPropertyFile("username");
//		String password = prop.readingDataFromPropertyFile("password");

		// getting the input from the excel utility file
		Excel_File_Utility excelobj = new Excel_File_Utility();
		String Campaign_name = excelobj.readingDataFromExcelFile("Campaign", 1, 2);
		String TargetSize = excelobj.readingDataFromExcelFile("Campaign", 1, 3);

		// getting the random number from java utility file
		Java_File_Utility javaobj = new Java_File_Utility();
		int number = javaobj.randomNumber(1000);

		// getting the system date
		String closeDate = javaobj.generateDate(25);

		// Object of web driver utility
//		WebDriverUtility utilobj = new WebDriverUtility();
//		WebDriver driver = utilobj.chooseBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.get(url);

//		LoginPage lp = new LoginPage(driver);
//		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.getCampaignsLink().click();

		// Create object for Dashboard page
		CampaignsPage cp = new CampaignsPage(driver);
		Thread.sleep(2000);
		cp.getcreatecampaignbtn().click();

		// Create object for create campaign page
		CreateCampaigns cc = new CreateCampaigns(driver);
		cc.createCampaignWithCloseDate(Campaign_name+number, TargetSize, closeDate);
		Thread.sleep(4000);
		
		String ConfMsg = cp.getconfMsg().getText();
		System.out.println("Confirmation messsage =" + ConfMsg);
//		if (ConfMsg.contains(Campaign_name + number)) {
//			Reporter.log("Campaign created successfully", true);
//		} else {
//			Reporter.log("Campaign not created! Check the code", true);
//		}
		boolean status = ConfMsg.contains(Campaign_name+number);
		Assert.assertEquals(status, true, "Campaign not added");
		Reporter.log("Campaign " + Campaign_name+number + " added successfully", true);
		System.out.println("************************* from createCampaignTest");
		Thread.sleep(5000);
		// Logout
//		dp.logout();
//		System.out.println("Logged out successfully");
//		driver.quit();
	}
	
	//@Parameters("browser")
	//@Test(groups = {"Smoke Test"})
	//@Test(priority=-1)
	//@Test(invocationCount = 2)
	//@Test(invocationCount = 4, threadPoolSize = 3)
	//@Test(enabled = false)
	@Test
	public void createCampaignTestWithMandatoryFields() throws InterruptedException, IOException {
		//public void createCampaignTestWithMandatoryFields(String browserr) throws InterruptedException, IOException {
		
		// get the data from the properties utility file
//		Properties_File_Utility prop = new Properties_File_Utility();
//		String browser = prop.readingDataFromPropertyFile("browser");
//		//String browser = browserr;
//		String url = prop.readingDataFromPropertyFile("url");
//		String username = prop.readingDataFromPropertyFile("username");
//		String password = prop.readingDataFromPropertyFile("password");

		// getting the input from the excel utility file
		Excel_File_Utility excelobj = new Excel_File_Utility();
		String Campaign_name = excelobj.readingDataFromExcelFile("Campaign", 1, 2);
		String TargetSize = excelobj.readingDataFromExcelFile("Campaign", 1, 3);

		// getting the random number from java utility file
		Java_File_Utility javaobj = new Java_File_Utility();
		int number = javaobj.randomNumber(1000);

		// Object of web driver utility
//		WebDriverUtility utilobj = new WebDriverUtility();
//		WebDriver driver = utilobj.chooseBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		//driver.get(url);

//		LoginPage lp = new LoginPage(driver);
//		lp.login(username, password);

		DashboardPage dp = new DashboardPage(driver);
		dp.getCampaignsLink().click();

		// Create object for Dashboard page
		CampaignsPage cp = new CampaignsPage(driver);
		Thread.sleep(2000);
		cp.getcreatecampaignbtn().click();

		// Create object for create campaign page
		CreateCampaigns cc = new CreateCampaigns(driver);
		cc.createCampaignWithmandatoryFields(Campaign_name+number, TargetSize);
		Thread.sleep(4000);
		
		String ConfMsg = cp.getconfMsg().getText();
		System.out.println("Confirmation messsage =" + ConfMsg);
		boolean status = ConfMsg.contains(Campaign_name+number);
		Assert.assertEquals(status, true, "Campaign not added");
		Reporter.log("Campaign " + Campaign_name+number + " added successfully", true);
//		if (ConfMsg.contains(Campaign_name + number)) {
//			Reporter.log("Campaign created successfully", true);
//		} else {
//			Reporter.log("Campaign not created! Check the code", true);
//		}
		System.out.println("************************* from createCampaignTestWithMandatoryFields");
		Thread.sleep(5000);
		// Logout
//		dp.logout();
//		driver.quit();
	}
}