package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import generic_utilities.WebDriverUtility;

public class CreateContact {

	WebDriver driver;

	public CreateContact(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "organizationName")
	private WebElement organizationName;

	@FindBy(name = "title")
	private WebElement title;

	@FindBy(name = "department")
	private WebElement department;

	@FindBy(name = "officePhone")
	private WebElement officePhone;

	@FindBy(name = "contactName")
	private WebElement contactName;

	@FindBy(name = "mobile")
	private WebElement mobile;

	@FindBy(name = "email")
	private WebElement email;

	@FindBy(xpath = "//label[text()='Campaign']/following-sibling::div/button")
	private WebElement campagainselection;
	
	@FindBy(xpath = "//label[text()='Campaign']/following-sibling::div/input")
	private WebElement campagainvalue;

	@FindBy(id = "search-criteria")
	private WebElement campaignfilter;

	@FindBy(id = "search-input")
	private WebElement campaignnameorid;

	@FindBy(xpath = "//button[@class='select-btn']")
	private WebElement selectBtn;
	
	@FindBy(xpath="//button[text()='Create Contact']")
	private WebElement createcontactbtn;
	
	public WebElement getcreatecontactbtn() {
		return createcontactbtn;
	}

	public WebElement getOrganizationName() {
		return organizationName;
	}

	public WebElement getTitle() {
		return title;
	}

	public WebElement getDepartment() {
		return department;
	}
	
	public WebElement getcampagainvalue() {
		return campagainvalue;
	}

	public WebElement getOfficePhone() {
		return officePhone;
	}

	public WebElement getContactName() {
		return contactName;
	}

	public WebElement getMobile() {
		return mobile;
	}

	public WebElement getEmail() {
		return email;
	}

	public WebElement getCampagainselection() {
		return campagainselection;
	}

	public WebElement getCampaignfilter() {
		return campaignfilter;
	}

	public WebElement getCampaignnameorid() {
		return campaignnameorid;
	}

	public WebElement getSelectBtn() {
		return selectBtn;
	}
	
	public void createContactWithVerification(String orgName, String title1, String contactname, String mobile1, String childUrl, String campName, String parentURL) throws InterruptedException
	{
		organizationName.sendKeys(orgName);
		title.sendKeys(title1);
		contactName.sendKeys(contactname);
		mobile.sendKeys(mobile1);
		campagainselection.click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.switchToWindow(driver, childUrl);
		wutil.selectByIndex(campaignfilter, 1);
		campaignnameorid.sendKeys(campName);
		selectBtn.click();
		wutil.switchToWindow(driver, parentURL);
		String value = campagainvalue.getAttribute("value");
		System.out.println("Campaign name actually selected " + value);
		if (value.equalsIgnoreCase(campName)) {
			System.out.println("Correct Campaign is selected");
		} else {
			System.out.println("Incorrect Campaign is selected");
		}
		createcontactbtn.click();
		Thread.sleep(2000);
		ContactsPage cp = new ContactsPage(driver);
		String confrmMsg = cp.getconfMsg().getText();
		System.out.println("Confirmation messsage = " + confrmMsg);
		if(confrmMsg.contains(contactname)) {
			Reporter.log("Contact created successfully", true);
		}else {
			Reporter.log("Contact not created! Check the code", true);
		}
	}	
	
	
	public void createContactWithoutVerification(String orgName, String title1, String contactname, String mobile1, String childUrl, String campName, String parentURL) throws InterruptedException
	{
		organizationName.sendKeys(orgName);
		title.sendKeys(title1);
		contactName.sendKeys(contactname);
		mobile.sendKeys(mobile1);
		campagainselection.click();
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.switchToWindow(driver, childUrl);
		wutil.selectByIndex(campaignfilter, 1);
		campaignnameorid.sendKeys(campName);
		selectBtn.click();
		wutil.switchToWindow(driver, parentURL);
		createcontactbtn.click();
	}
}