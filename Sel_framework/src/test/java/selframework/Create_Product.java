package selframework;

	import java.io.IOException;
	import java.time.Duration;
	import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import generic_baseclassutility.Baseclass;
import generic_listenerutility.ListenerImp;
import generic_utilities.Excel_File_Utility;
	import generic_utilities.Java_File_Utility;
	import generic_utilities.Properties_File_Utility;
	import generic_utilities.WebDriverUtility;
	import objectrepositary.CreateProduct;
	import objectrepositary.DashboardPage;
	import objectrepositary.LoginPage;
	import objectrepositary.ProductPage;

	//@Listeners(ListenerImp.class)
	public class Create_Product extends Baseclass{
		
		@Test
		//@Parameters("browser")
		//@Test(groups = {"Integration Test"})
		//public void createProduct(String browserr) throws IOException, InterruptedException {
			public void createProduct() throws IOException, InterruptedException {
//			Properties_File_Utility propUtil=new Properties_File_Utility();
//			String BROWSER = propUtil.readingDataFromPropertyFile("browser");
//			//String BROWSER = browserr;
//			String URL = propUtil.readingDataFromPropertyFile("url");
//			String UN = propUtil.readingDataFromPropertyFile("username");
//			String PWD = propUtil.readingDataFromPropertyFile("password");
						
			Java_File_Utility jutil=new Java_File_Utility();
			int randNum = jutil.randomNumber(2000);
			Excel_File_Utility excelUtil=new Excel_File_Utility();
			
			String prodName = excelUtil.readingDataFromExcelFile("Product", 1, 1)+randNum;
			String quantity = excelUtil.readingDataFromExcelFile("Product", 1, 2);
			String price = excelUtil.readingDataFromExcelFile("Product", 1, 3);
		
			//Launching the browser
//			WebDriverUtility wutil = new WebDriverUtility();
//			WebDriver driver = null;
//			driver = wutil.chooseBrowser(BROWSER);
			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			//navigating to ninza CRM
//			driver.get(URL);
			//enter the username and password
//			LoginPage lp=new LoginPage(driver);
//			lp.login(UN, PWD);
//			Thread.sleep(2000);
			//verification of dashboard
			DashboardPage dp=new DashboardPage(driver);
			dp.getProductsLink().click();
			ProductPage pp = new ProductPage(driver);
			pp.getAddProductbtn().click();
			CreateProduct cp = new CreateProduct(driver);
			//cp.createProduct(prodName, "Others", quantity, price, 1);
			cp.createProduct(prodName, "Others", quantity, price, "VID_001");
			Thread.sleep(4000);
			String ConfMsg = pp.getconfMsg().getText();
			System.out.println("Confirmation messsage =" + ConfMsg);
			System.out.println(prodName);
			boolean status = ConfMsg.contains(prodName);
			Assert.assertEquals(status, true, "Product not created");
			Reporter.log("Product " + prodName + " added successfully", true);
//			if (ConfMsg.contains(prodName)){
//				Reporter.log("Product added successfully", true);
//			} else {
//				Reporter.log("Product not added", true);
//			}
			System.out.println("*************************");
			Thread.sleep(6000);
			//logout
//			dp.logout();
//	        driver.quit();
		}
	}