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
import generic_utilities.Excel_File_Utility;
import generic_utilities.Java_File_Utility;
import generic_utilities.Properties_File_Utility;
import generic_utilities.WebDriverUtility;
import objectrepositary.ContactsPage;
import objectrepositary.CreateContact;
import objectrepositary.DashboardPage;
import objectrepositary.LoginPage;

//@Listeners(ListenerImp.class)
public class Create_Contact extends Baseclass{
	
	@Test
	//@Parameters("browser")
	//@Test(groups = {"Smoke Test"})
	public void createContactTest() throws InterruptedException, IOException {
		//public void createContactTest(String browserr) throws InterruptedException, IOException {
		Properties_File_Utility prop = new Properties_File_Utility();
//		String browser = prop.readingDataFromPropertyFile("browser");
//		//String browser = browserr;
//		String url = prop.readingDataFromPropertyFile("url");
//		String username = prop.readingDataFromPropertyFile("username");
//		String password = prop.readingDataFromPropertyFile("password");

//		WebDriverUtility utilobj = new WebDriverUtility();
//		WebDriver driver = utilobj.chooseBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		driver.get(url);
		// Login
//		LoginPage lp = new LoginPage(driver);
//		lp.login(username, password);
//		Thread.sleep(3000);

		// Reading data from excel
		Excel_File_Utility exlobj = new Excel_File_Utility();
		String organizationName = exlobj.readingDataFromExcelFile("Contact", 1, 0);
		String title = exlobj.readingDataFromExcelFile("Contact", 1, 1);
		String contactName = exlobj.readingDataFromExcelFile("Contact", 1, 2);
		String mobile = exlobj.readingDataFromExcelFile("Contact", 1, 3);
		String campaigname = exlobj.readingDataFromExcelFile("Contact", 1, 4);

		// getting the random number from java utility file
		Java_File_Utility javaobj = new Java_File_Utility();
		int number = javaobj.randomNumber(100);

		// Create contact 
		DashboardPage dp = new DashboardPage(driver);
		dp.getContactsLink().click();
		
		ContactsPage cp = new ContactsPage(driver);
		cp.getcreatecontactbtn().click();
		
		CreateContact cc = new CreateContact(driver);
		//cc.createContactWithVerification(organizationName, title+number, contactName+number, mobile, "selectCampaign", campaigname+number, "create-contact");
		cc.createContactWithoutVerification(organizationName, title+number, contactName+number, mobile, "selectCampaign", campaigname+number, "create-contact");
		Thread.sleep(3000);
		String ConfMsg = cp.getconfMsg().getText();
		System.out.println("Confirmation messsage =" + ConfMsg);
		System.out.println(contactName);
		boolean status = ConfMsg.contains(contactName+number);
		Assert.assertEquals(status, true, "Contact not added");
		Reporter.log("Contact " + contactName+number + " added successfully", true);
//		if (ConfMsg.contains(contactName)){
//			Reporter.log("Contact created successfully", true);
//		} else {
//			Reporter.log("Contact not added", true);
//		}
		System.out.println("*************************");
		Thread.sleep(3000);
		//logout
//		dp.logout();
//        driver.quit();
	}
}