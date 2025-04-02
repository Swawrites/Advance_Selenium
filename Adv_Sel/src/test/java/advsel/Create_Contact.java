package advsel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class Create_Contact
{
	public static void main(String[] args) throws InterruptedException, IOException
	{
		
	WebDriver driver;
	FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel\\src\\test\\resources\\Common_data1.properties");
	Properties prop = new Properties();
	prop.load(fis);
	
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
	
	//Create contact link
	driver.findElement(By.linkText("Contacts")).click();
	driver.findElement(By.xpath("//span[text()='Create Contact']")).click();
	driver.findElement(By.name("organizationName")).sendKeys("Salvatore hub");
	driver.findElement(By.name("title")).sendKeys("Salva-CEO");
	String contactname = "Salvatorebrothers";
	driver.findElement(By.name("contactName")).sendKeys(contactname);
	driver.findElement(By.name("mobile")).sendKeys("7291807611");
	driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/button")).click();
	Set<String> windowhandles = driver.getWindowHandles();
	Iterator<String> it = windowhandles.iterator();
	String parentID = it.next();
	System.out.println(parentID);
	String childWindow = it.next();
	System.out.println(childWindow);
	driver.switchTo().window(childWindow);
	String campaigname = "homeofsanes";
	driver.findElement(By.xpath("//option[@value='campaignName']")).click();
	driver.findElement(By.id("search-input")).sendKeys(campaigname);
	driver.findElement(By.xpath("//button[@class='select-btn']")).click();
	driver.switchTo().window(parentID);
	String value = driver.findElement(By.xpath("//label[text()='Campaign']/following-sibling::div/input")).getAttribute("value");
	if(value.equalsIgnoreCase(campaigname)) {
		System.out.println("Correct Campaign is selected");
	}else {
		System.out.println("Incorrect Campaign is selected");
	}

	driver.findElement(By.xpath("//button[text()='Create Contact']")).click();
	Thread.sleep(2000);
	String confrmMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
	System.out.println("Confirmation messsage = " + confrmMsg);
	if(confrmMsg.contains(contactname)) {
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