package advsel;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

public class CreateCampaign_withExcel {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Read from property file
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
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("inputPassword")).sendKeys(password);
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(3000);
		//Click on create campaign link
		driver.findElement(By.xpath("//span[text()='Create Campaign']")).click();
		
		//getting the input from the excel
		FileInputStream fis1 = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel_notes\\Exceldata02.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sheet = wb.getSheet("Sheet1");
		Row row = sheet.getRow(1);
		Cell cell = row.getCell(2);
		String Campaign_name = cell.getStringCellValue();
		Cell cell2 = row.getCell(3);
		String TargetSize = cell2.getStringCellValue();
		Random rdm = new Random();
		int number = rdm.nextInt(1000);
		System.out.println(Campaign_name+number);
		System.out.println(TargetSize);
		//Passing these values to the browser
		driver.findElement(By.name("campaignName")).sendKeys(Campaign_name+number);
		driver.findElement(By.name("targetSize")).sendKeys(TargetSize);
		driver.findElement(By.xpath("//button[text()='Create Campaign']")).click();
		Thread.sleep(2000);
		String confrmMsg = driver.findElement(By.xpath("//div[@role='alert']")).getText();
		System.out.println("Confirmation messsage =" + confrmMsg);
		if(confrmMsg.contains(Campaign_name)) {
			System.out.println("Campaign created successfully");
		}else {
			System.out.println("Campaign not created! Check the code");
		}
		driver.findElement(By.xpath("//button[@aria-label='close']")).click();
		
		// Logout
		Actions action = new Actions(driver);
		driver.findElement(By.xpath("//div[@class='user-icon-container']")).click();
		WebElement ele = driver.findElement(By.xpath("//div[@class='dropdown-item logout']"));
		action.moveToElement(ele).click().perform();
		System.out.println("Logged out successfully");
		driver.quit();
	}
}