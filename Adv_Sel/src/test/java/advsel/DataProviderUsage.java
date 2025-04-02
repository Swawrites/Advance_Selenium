package advsel;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderUsage {
	
	@Test(dataProvider = "getData")
	public void testCase(String firstName, String lastName) {
		System.out.println("Firstname " + firstName + " Lastname " + lastName);
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objarr = new Object[3][2];
		objarr[0][0] = "John";
		objarr[0][1] = "Dave";
		objarr[1][0] = "Marrie";
		objarr[1][1] = "Raven";
		objarr[2][0] = "Smith";
		objarr[2][1] = "Loiuse";
		return objarr;
	}
}
