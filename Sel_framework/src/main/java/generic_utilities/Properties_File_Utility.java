package generic_utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;

public class Properties_File_Utility
{
	public WebDriver driver;

	public String readingDataFromPropertyFile(String Key) throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\sa287\\OneDrive - DXC Production\\Desktop\\J_Run\\Adv_Sel\\src\\test\\resources\\Common_data1.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String data = prop.getProperty(Key);
		return data;
	}
	
}
