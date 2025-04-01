package objectrepositary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import generic_utilities.WebDriverUtility;

public class CreateProduct {
	
	WebDriver driver =null;
	public CreateProduct(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
		
	@FindBy(name="productName")
	private WebElement productName;
	
	@FindBy(name="productCategory")
	private WebElement productCategory;
	
	@FindBy(name="quantity")
	private WebElement productquantity;
	
	@FindBy(name="price")
	private WebElement productprice;
	
	@FindBy(name="vendorId")
	private WebElement productvendorId;
	
	@FindBy(xpath="//button[text()='Add']")
	private WebElement addproductbtn;
	

	public WebElement getProductName() {
		return productName;
	}

	public WebElement getProductCategory() {
		return productCategory;
	}

	public WebElement getProductquantity() {
		return productquantity;
	}

	public WebElement getProductprice() {
		return productprice;
	}

	public WebElement getProductvendorId() {
		return productvendorId;
	}
	
	public WebElement getaddproductbtn() {
		return addproductbtn;
	}
	
	public void createProduct(String prodname, String category, String prodquantity, String prodprice, String visibletext) {
		
		productName.sendKeys(prodname);
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.selectByVisibleText(productCategory,category);
		productquantity.sendKeys(prodquantity);
		productprice.sendKeys(prodprice);
		//wutil.selectByIndex(productvendorId, index);
		wutil.selectByVisibleText(productvendorId, visibletext);
		addproductbtn.click();
		System.out.println("Product added successfully");
		System.out.println("*************************");
	}
}
