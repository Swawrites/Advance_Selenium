package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignsPage
{
	WebDriver driver =null;
	public CampaignsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement campaigndropdown;
	
	@FindBy(xpath="//input[@placeholder='Search by Campaign Id']")
	private WebElement searchinputforcampaign;
	
	@FindBy(xpath="//span[text()='Create Campaign']")
	private WebElement createcampaignbtn;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;
	
	
	public WebElement getcampaigndropdown(){
		return campaigndropdown;
	}
	
	public WebElement getsearchinputforcampaign(){
		return searchinputforcampaign;
	}
	
	public WebElement getcreatecampaignbtn(){
		return createcampaignbtn;
	}
	
	public WebElement getconfMsg(){
		return confMsg;
	}
	
	
}
