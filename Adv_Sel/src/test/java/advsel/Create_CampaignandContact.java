package advsel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Create_CampaignandContact {

	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver;
		FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Common_data2.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		//Reading from property file
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		String username = prop.getProperty("username");
		String password = prop.getProperty("password");
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		}else {
			driver = new EdgeDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
		//Login
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		
		//Reading data from excel
		FileInputStream fis1 = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Exceldata03.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		String organizationName = wb.getSheet("Sheet1").getRow(1).getCell(0).getStringCellValue();
		String title = wb.getSheet("Sheet1").getRow(1).getCell(1).getStringCellValue();
		String contactname = wb.getSheet("Sheet1").getRow(1).getCell(2).getStringCellValue();
		String mobile_num = wb.getSheet("Sheet1").getRow(1).getCell(3).getStringCellValue();
		String campaigname = wb.getSheet("Sheet1").getRow(1).getCell(4).getStringCellValue();
		String targetsize = wb.getSheet("Sheet1").getRow(1).getCell(5).getStringCellValue();
		
		Random rdm = new Random();
		int number = rdm.nextInt(1000);
		
		//Create Campaign test
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		driver.findElement(By.name("campaignName")).sendKeys(campaigname+number);
		driver.findElement(By.name("targetSize")).sendKeys(targetsize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(3000);
		String confrmMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage for campaign creation = " + confrmMsg);
		if(confrmMsg.contains(campaigname)) {
			System.out.println("Campaign created successfully");
		}else {
			System.out.println("Campaign not created! Check the code");
		}
		Thread.sleep(5000);
		//Create contact link
		WebElement contactLink = driver.findElement(By.linkText("Contacts"));
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(contactLink));
		contactLink.click();
		Thread.sleep(5000);
		
		driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
		driver.findElement(By.name("organizationName")).sendKeys(organizationName+number);
		driver.findElement(By.name("title")).sendKeys(title+number);
		driver.findElement(By.name("contactName")).sendKeys(contactname+number);
		driver.findElement(By.name("mobile")).sendKeys(mobile_num);
		driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/button")).click();
		Set<String> windowhandles = driver.getWindowHandles();
		Iterator<String> it = windowhandles.iterator();
		String parentID = it.next();
		System.out.println("Parent window ID = " + parentID);
		String childWindow = it.next();
		System.out.println("Child window ID = " + childWindow);
		driver.switchTo().window(childWindow);
		driver.findElement(By.xpath("//option[@value='campaignName']")).click();
		String campname = campaigname+number;
		driver.findElement(By.id("search-input")).sendKeys(campname);
		System.out.println("Campaign name from excel " + campname);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='select-btn']")).click();
		driver.switchTo().window(parentID);
		String value = driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/input")).getAttribute("value");
		System.out.println("Campaign name actually selected " + value);
		if(value.equalsIgnoreCase(campname)) {
			System.out.println("Correct Campaign is selected");
		}else {
			System.out.println("Incorrect Campaign is selected");
		}
		
		driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
		Thread.sleep(2000);
		String confrmMsg1 = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage for contact creation = " + confrmMsg1);
		if(confrmMsg1.contains(contactname)) {
			System.out.println("Contact created successfully");
		}else {
			System.out.println("Contact not created! Check the code");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		//Logout
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//div[@class='user-icon-container']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(ele).click().perform();
		System.out.println("Logged out successfully");
		driver.quit();
	}
}