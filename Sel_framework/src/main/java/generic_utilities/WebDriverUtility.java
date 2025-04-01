package generic_utilities;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	WebDriver driver;

	public void waitForElementPresent(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebDriver driver, WebElement element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void switchToWindow(WebDriver driver, String partialURL) {
		Set<String> windowIDs = driver.getWindowHandles();
		for (String win : windowIDs) {
			driver.switchTo().window(win);
			String actual_url = driver.getCurrentUrl();
			if (actual_url.contains(partialURL)) {
				break;
			}
		}
	}

	public WebDriver chooseBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			driver = new EdgeDriver();
		}
		return driver;
	}

	public void selectByIndex(WebElement element, int index) {
		Select select1 = new Select(element);
		select1.selectByIndex(index);
	}

	public void selectByValue(WebElement element, String value) {
		Select select1 = new Select(element);
		select1.selectByValue(value);
	}

	public void selectByVisibleText(WebElement element, String visibletext) {
		Select select1 = new Select(element);
		select1.selectByVisibleText(visibletext);
	}

	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrame(WebDriver driver, String nameId) {
		driver.switchTo().frame(nameId);
	}

	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	public void actionMoveToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	public void actionDoubleClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	public void actionRightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	public void takingScreenshotofWebPage(WebDriver driver, String fileName) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots" + fileName + ".png");
		FileHandler.copy(temp, dest);
	}

	public void takingScreenshotofWebElement(WebElement element, String fileName) throws IOException {
		File temp = element.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenshots" + fileName + ".png");
		FileHandler.copy(temp, dest);
	}

}