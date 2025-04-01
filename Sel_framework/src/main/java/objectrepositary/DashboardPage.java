package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
	
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(linkText="Campaigns")
	WebElement campaignspagelink;
	
	@FindBy(linkText="Contacts")
	WebElement contactspagelink;
	
	@FindBy(linkText="Products")
	WebElement productspagelink;
	
	@FindBy(xpath="//*[name()='svg' and @role='img']")
	WebElement profileIcon;
	
	@FindBy(xpath="//div[text()='Logout ']")
	WebElement logoutBtn;

	public void logout()
	{
		profileIcon.click();
		Actions action=new Actions(driver);
		action.moveToElement(logoutBtn).click();
	}


	public WebElement getCampaignsLink() {
		return campaignspagelink;
	}

	public WebElement getContactsLink() {
		return contactspagelink;
	}

	public WebElement getProductsLink() {
		return productspagelink;
	}

	public WebElement getProfileIcon() {
		return profileIcon;
	}

	public WebElement getLogoutBtn() {
		return logoutBtn;
	}	
}