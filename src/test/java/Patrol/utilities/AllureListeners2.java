package Patrol.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListeners2 implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult){
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
//	@Attachment
//	public byte[] saveFailureScreenShot(WebDriver driver) {
//		TakesScreenshot ts = (TakesScreenshot) driver;
//		return ts.getScreenshotAs(OutputType.BYTES);
//	}
	
	@Attachment(value = "Failure Screenshot", type = "image/png")
	public byte[] saveFailureScreenShot(WebDriver driver) {
	    try {
	        TakesScreenshot ts = (TakesScreenshot) driver;
	        return ts.getScreenshotAs(OutputType.BYTES);
	    } catch (Exception e) {
	        System.out.println("Failed to capture screenshot: " + e.getMessage());
	        return new byte[0]; // Return empty byte array if screenshot fails
	    }
	}
	
	@Attachment(value="{0}",type="text/plain")
	public static String saveTextMessage(String message) {
		return message;
	}
	
	@Override
	public void onTestFailure(ITestResult iTR) {
		System.out.println("i am in onTestFailure method "+ getTestMethodName(iTR));
		WebDriver driver = BaseTest2.getDriver();
		if(driver instanceof WebDriver) {
			System.out.println("screenshot capured for test case: "+ getTestMethodName(iTR));
			saveFailureScreenShot(driver);
		}
		saveTextMessage(getTestMethodName(iTR)+" failed and screenshot taken!");
	}
	
}