package generic_listenerutility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import generic_baseclassutility.Baseclass;

public class ListenerImp implements ITestListener, ISuiteListener{
	

	public void onStart(ISuite suite) {
		System.out.println("Report Configuration");
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("HH-mm-ss");
		String time = sim.format(date);
		System.out.println(time);
	}

	public void onFinish(ISuite suite) {
		System.out.println("Report Backup");
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("HH:mm:ss");
		String time = sim.format(date);
		System.out.println(time);
	}

	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Starts ");
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName()+" Ends ");
	}

	public void onTestFailure(ITestResult result) {
		String testcaseName = result.getMethod().getMethodName();
		Date date = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("HH:mm:ss");
		String time = sim.format(date);
		TakesScreenshot ts = (TakesScreenshot)Baseclass.sdriver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		//File dest = new File("./screenshots/"+testcaseName+"+"+time+".png");
		File dest = new File("./screenshots/"+testcaseName+"_"+time+".png");
		try {
			FileHandler.copy(temp, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}
	public void onTestFailedWithTimeout(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		
	}

	public void onFinish(ITestContext context) {
		
	}


}
