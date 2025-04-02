package create_campaign;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Create_Campaign {

	public static void main(String[] args) throws InterruptedException {
		//Web browser launch
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8098/");
		//Login
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		String cmpgname = "Beyond the Moon";
		driver.findElement(By.name("campaignName")).sendKeys(cmpgname);
		driver.findElement(By.name("targetSize")).sendKeys("1150");
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
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//div[@class='user-icon-container']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(ele).click().perform();
		System.out.println("Logged out successfully");
		driver.quit();
	}

}
