package Patrol.utilities;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.qameta.allure.Attachment;

public class AllureListeners2 implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextMessage(String message) {
		return message;
	}
	
	@Override
	public void onTestSuccess(ITestResult iTR) {
		System.out.println("i am in onTestSuccess method " + getTestMethodName(iTR));
		WebDriver driver = BaseTest2.getDriver();
		if (driver instanceof WebDriver) {
			ScreenShotsUtility.addScreenshotToReport(driver,getTestMethodName(iTR));
		}
	}

	@Override
	public void onTestFailure(ITestResult iTR) {
		System.out.println("i am in onTestFailure method " + getTestMethodName(iTR));
		WebDriver driver = BaseTest2.getDriver();
		if (driver instanceof WebDriver) {
			ScreenShotsUtility.addScreenshotToReport(driver,getTestMethodName(iTR));
		}
	}

	@Override
	public void onTestSkipped(ITestResult iTR) {
		System.out.println("i am in onTestSkipped method " + getTestMethodName(iTR));
		WebDriver driver = BaseTest2.getDriver();
		if (driver instanceof WebDriver) {
			ScreenShotsUtility.addScreenshotToReport(driver,getTestMethodName(iTR));
		}
	}
}