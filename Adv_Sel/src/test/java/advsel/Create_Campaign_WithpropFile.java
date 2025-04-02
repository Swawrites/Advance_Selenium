package advsel;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import generic_utilities.Properties_File_Utility;


public class Create_Campaign_WithpropFile {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver;
		Properties_File_Utility prop = new Properties_File_Utility();
		String browser = prop.readingDataFromPropertyFile("browser");
		String url = prop.readingDataFromPropertyFile("url");
		String username = prop.readingDataFromPropertyFile("username");
		String password = prop.readingDataFromPropertyFile("password");
		
		
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
	
		//Create Campaign
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		String cmpgname = "XYZ67";
		driver.findElement(By.name("campaignName")).sendKeys(cmpgname);
		driver.findElement(By.name("targetSize")).sendKeys("1150");
		
		Actions action = new Actions(driver);
		//This is manually sending the dates
		//action.click(date_picker).sendKeys("0202").sendKeys(Keys.ARROW_RIGHT).sendKeys("2027").perform();
		//If we want to set the date from todays date, below is the code
		Date dateobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
		String todays_date = sim.format(dateobj);
		System.out.println(todays_date);
		
		Calendar cal = sim.getCalendar();
		cal.add(Calendar.DAY_OF_MONTH, 25);
		String closeDate = sim.format(cal.getTime());
		System.out.println(closeDate);
				
		driver.findElement(By.name("expectedCloseDate")).sendKeys(closeDate);
		
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		String confrmMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage =" + confrmMsg);
		if(confrmMsg.contains(cmpgname)) {
			System.out.println("Campaign created successfully");
		}else {
			System.out.println("Campaign not created! Check the code");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		//Logout
		driver.findElement(By.xpath("//div[@class='user-icon-container']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(ele).click().perform();
		System.out.println("Logged out successfully");
		driver.quit();
	}
}