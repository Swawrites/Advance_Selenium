package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver =null;
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath="//span[text()='Add Product']")
	private WebElement addProductbtn;
	
	@FindBy(xpath="//select[@class='form-control']")
	private WebElement productdropdown;
	
	@FindBy(xpath="//input[@placeholder='Search by product Id']")
	private WebElement productinput;
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confMsg;
	

	public WebElement getAddProductbtn() {
		return addProductbtn;
	}

	public WebElement getProductdropdown() {
		return productdropdown;
	}

	public WebElement getProductinput() {
		return productinput;
	}

	public WebElement getconfMsg(){
		return confMsg;
	}

}
