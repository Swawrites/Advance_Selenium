package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage
{
	
	WebDriver driver =null;
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='Create Contact']")
	private WebElement createcontactbtn;
	
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement contactdropdown;
	
	@FindBy(xpath="//input[@placeholder='Search by Contact Id']")
	private WebElement contactinput;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;
	
	public WebElement getcreatecontactbtn(){
		return createcontactbtn;
	}
	
	public WebElement getcontactdropdown(){
		return contactdropdown;
	}
	
	public WebElement getcontactinput(){
		return contactinput;
	}
	
	public WebElement getconfMsg(){
		return confMsg;
	}
	
}
