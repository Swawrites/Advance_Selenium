package selframework;

import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import generic_utilities.Excel_File_Utility;
import generic_utilities.Java_File_Utility;
import generic_utilities.Properties_File_Utility;
import generic_utilities.WebDriverUtility;
import objectrepositary.CampaignsPage;
import objectrepositary.CreateCampaigns;
import objectrepositary.DashboardPage;
import objectrepositary.LoginPage;

public class Create_CampaignandContact {

	public static void main(String[] args) throws IOException, InterruptedException {
		Properties_File_Utility prop = new Properties_File_Utility();
		String browser = prop.readingDataFromPropertyFile("browser");
		String url = prop.readingDataFromPropertyFile("url");
		String username = prop.readingDataFromPropertyFile("username");
		String password = prop.readingDataFromPropertyFile("password");

		// Choosing the Browser
		WebDriverUtility utilobj = new WebDriverUtility();
		WebDriver driver = utilobj.chooseBrowser(browser);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		// Login
		LoginPage lp = new LoginPage(driver);
		lp.login(username, password);
		
		// Reading data from excel
		Excel_File_Utility excelobj = new Excel_File_Utility();
		String organizationName = excelobj.readingDataFromExcelFile("Contact", 1, 0);
		String title = excelobj.readingDataFromExcelFile("Contact", 1, 1);
		String contactname = excelobj.readingDataFromExcelFile("Contact", 1, 2);
		String mobile_num = excelobj.readingDataFromExcelFile("Contact", 1, 3);
		String campaigname = excelobj.readingDataFromExcelFile("Contact", 1, 4);
		String targetsize = excelobj.readingDataFromExcelFile("Contact", 1, 5);

		// getting the random number from java utility file
		Java_File_Utility javaobj = new Java_File_Utility();
		int number = javaobj.randomNumber(100);

		//Campaigns Page
		DashboardPage dp = new DashboardPage(driver);
		dp.getCampaignsLink().click();
		
		CampaignsPage cp = new CampaignsPage(driver);
		cp.getcreatecampaignbtn().click();
		
		CreateCampaigns cc = new CreateCampaigns(driver);
		cc.getcampaigname().sendKeys(args);
		driver.findElement(By.name("campaignName")).sendKeys(campaigname + number);
		driver.findElement(By.name("targetSize")).sendKeys(targetsize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		String confrmMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage for campaign creation = " + confrmMsg);
		if (confrmMsg.contains(campaigname)) {
			System.out.println("Campaign created successfully");
		} else {
			System.out.println("Campaign not created!");
		}
		Thread.sleep(5000);
		// Create contact link
		WebElement contactLink = driver.findElement(By.linkText("Contacts"));

		utilobj.waitForElementToBeClickable(driver, contactLink, 20);
		contactLink.click();
		Thread.sleep(5000);

		WebElement createContactBtn = driver.findElement(By.xpath("//span[text()='Create Contact']"));
		utilobj.waitForElementPresent(driver, createContactBtn);
		createContactBtn.click();
		driver.findElement(By.name("organizationName")).sendKeys(organizationName + number);
		driver.findElement(By.name("title")).sendKeys(title + number);
		driver.findElement(By.name("contactName")).sendKeys(contactname + number);
		driver.findElement(By.name("mobile")).sendKeys(mobile_num);
		driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/button")).click();

		// selecting the window
		utilobj.switchToWindow(driver, "selectCampaign");

		WebElement selectTypeDD = driver.findElement(By.id("search-criteria"));
		utilobj.selectByValue(selectTypeDD, "campaignName");
		Thread.sleep(5000);
		driver.findElement(By.id("search-input")).sendKeys(campaigname + number);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[@class='select-btn']")).click();

		utilobj.switchToWindow(driver, "create-contact");

		String value = driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/input"))
				.getAttribute("value");
		System.out.println("Campaign name actually selected " + value);
		if (value.equalsIgnoreCase(campaigname + number)) {
			System.out.println("Correct Campaign is selected");
		} else {
			System.out.println("Incorrect Campaign is selected");
		}

		driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		Thread.sleep(2000);

		String confrmMsg1 = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage = " + confrmMsg1);
		if (confrmMsg1.contains(contactname)) {
			System.out.println("Contact created successfully");
		} else {
			System.out.println("Contact not created! Check the code");
		}
		Thread.sleep(5000);

		// Logout
		driver.findElement(By.xpath("//div[@class='user-icon']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		utilobj.actionMoveToElement(driver, ele);
		System.out.println("Logged out successfully");
		driver.quit();
	}
}