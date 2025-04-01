package selframework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ObjectIdentification {

	public static void main(String[] args) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demowebshop.tricentis.com/login");
		WebElement searchstore = driver.findElement(By.id("small-searchterms"));
		WebElement searchBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		searchstore.sendKeys("Mobiles");
		searchBtn.click();
		
		//First time will find it next time when you refresh, the value or the address might have changed.
		//So we will go with the @FindBy annotation
		//Cuz everytime we refresh the address will change and it is dynamic
		driver.navigate().refresh();
		searchstore.sendKeys("Mobiles");
		searchBtn.click();
	}

}
