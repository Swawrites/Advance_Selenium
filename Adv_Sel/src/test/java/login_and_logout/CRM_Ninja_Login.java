package login_and_logout;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

public class CRM_Ninja_Login {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver;
		FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Common_data1.properties");
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
		@Nullable
		
		//Verification
		String str = driver.getCurrentUrl();
		if(str.contains("dashboard")) {
			System.out.println("Landed on dashboard");
		}else {
			System.out.println("Incorrect page landed");
		}
		
		//Logout
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//div[@class='user-icon-container']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(ele).click().perform();
		System.out.println("Logged out successfully");
		driver.quit();
	}
}