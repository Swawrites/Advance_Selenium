package objectrepositary;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(id="username")
	private WebElement usernamefield;
	
	@FindBy(id="inputPassword")
	private WebElement passwordfield;
	
	@FindBy(xpath="//button[text()='Sign In']")
	private WebElement signin;
	
	@FindBy(linkText="Forgot password")
	private WebElement forgotpasswordlink;	
	
	@FindBy(linkText="Create Account")
	private WebElement createaccountlink;
	

	public WebElement getUsernameField() {
		return usernamefield;
	}
	
	public WebElement getPasswordField() {
		return passwordfield;
	}
	
	public WebElement getSignInField() {
		return signin;
	}
	
	public WebElement getForgotPasswordField() {
		return forgotpasswordlink;
	}
	
	public WebElement getCreateAccountLinkt() {
		return createaccountlink;
	}

	public void login(String uname,String pwd)
	{
		usernamefield.sendKeys(uname);
		passwordfield.sendKeys(pwd);
		signin.click();
	}	
}