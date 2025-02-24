package Patrol.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListeners implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult){
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
	
	@Attachment
	public byte[] saveFailureScreenShot(WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		return ts.getScreenshotAs(OutputType.BYTES);
	}
	
	@Attachment(value="{0}",type="text/plain")
	public static String saveTextMessage(String message) {
		return message;
	}
	
	@Override
	public void onStart(ITestContext iTC){
		System.out.println("i am in onStart method "+ iTC.getName());
		iTC.setAttribute("WebDriver",BaseTest.getDriver());
	}
	
	@Override
	public void onFinish(ITestContext iTC){
		System.out.println("i am in onFinish method "+ iTC.getName());
	}
	
	@Override
	public void onTestStart(ITestResult iTR){
		System.out.println("i am in onTestStart method "+ getTestMethodName(iTR));
	}
	
	@Override
	public void onTestSuccess(ITestResult iTR){
		System.out.println("i am in onTestSuccess method "+ getTestMethodName(iTR));
		
		WebDriver driver = BaseTest.getDriver();
		if(driver instanceof WebDriver) {
			System.out.println("screenshot capured for test case: "+ getTestMethodName(iTR));
			saveFailureScreenShot(driver);
		}
		saveTextMessage(getTestMethodName(iTR)+" Passed and screenshot taken!");
	}
	
	@Override
	public void onTestFailure(ITestResult iTR) {
		System.out.println("i am in onTestFailure method "+ getTestMethodName(iTR));
		WebDriver driver = BaseTest.getDriver();
		if(driver instanceof WebDriver) {
			System.out.println("screenshot capured for test case: "+ getTestMethodName(iTR));
			saveFailureScreenShot(driver);
		}
		saveTextMessage(getTestMethodName(iTR)+" failed and screenshot taken!");
	}
	
	@Override
	public void onTestSkipped(ITestResult iTR) {
		System.out.println("i am in onTestSkipped method "+ getTestMethodName(iTR));
	}	
}