package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateCampaigns
{
	
	WebDriver driver;
	
	public CreateCampaigns(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(name="campaignName")
	private WebElement campaigname;
	
	@FindBy(name="campaignStatus")
	private WebElement campaignstatus;
	
	@FindBy(name="expectedCloseDate")
	private WebElement expectedCloseDate;
	
	@FindBy(name="description")
	private WebElement description;
	
	@FindBy(name="targetSize")
	private WebElement targetsize;
	
	@FindBy(name="targetAudience")
	private WebElement targetAudience;
	
	@FindBy(xpath="//button[text()='Create Campaign']")
	private WebElement createcampaignbtn;
	
	public WebElement getcampaigname(){
		return campaigname;
	}
	
	public WebElement getcampaignstatus(){
		return campaignstatus;
	}
	
	public WebElement getexpectedCloseDate(){
		return expectedCloseDate;
	}
	
	public WebElement getdescription(){
		return description;
	}
	
	public WebElement gettargetsize(){
		return targetsize;
	}
	
	public WebElement gettargetAudience(){
		return targetAudience;
	}
	
	public WebElement getcreatecampaignbtn(){
		return createcampaignbtn;
	}
	
	public void createCampaignWithmandatoryFields(String campName,String target)
	{
		campaigname.sendKeys(campName);
		targetsize.clear();
		targetsize.sendKeys(target);
		createcampaignbtn.click();
	}
	public void createCampaignWithCloseDate(String campName,String target,String date)
	{
		campaigname.sendKeys(campName);
		targetsize.clear();
		targetsize.sendKeys(target);
		expectedCloseDate.sendKeys(date);
		createcampaignbtn.click();
	}
	
}