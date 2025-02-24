package Patrol.utilities;

import java.io.ByteArrayInputStream;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

// To Take SceenShots Manually
public class ScreenShotsUtility {

	@Attachment(value = "Screenshot - {0}", type = "image/png")
	private static byte[] captureScreenshot(WebDriver driver, String screenshotName) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	private static void captureScreenshotToReport(WebDriver driver, String screenshotName) {
		byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		Allure.addAttachment(screenshotName, new ByteArrayInputStream(screenshotBytes));
	}

	// Static method to attach screenshot to allure report
	public static void addScreenshotToReport(WebDriver driver) {
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm").format(new Date());
		captureScreenshot(driver, timestamp);
	}

	// Static method to attach screenshot with custum name to allure report
	public static void addScreenshotToReport(WebDriver driver, String screenshotName) {
		captureScreenshotToReport(driver, screenshotName);
	}

	// Static method to take a screenshot in screenshots
	public static void takeScreenshot(WebDriver driver) {
		final String SCREENSHOTS_DIR = "screenshots";
		// Create the screenshots directory if it doesn't exist
		File directory = new File(SCREENSHOTS_DIR);
		if (!directory.exists()) {
			directory.mkdir();
		}
		// Take the screenshot and save it to the desired location
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// Add timestamp to the screenshot file name
		String timestamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		String filePath = SCREENSHOTS_DIR + File.separator + "screenshot" + "_" + timestamp + ".png";
		try {
			FileUtils.copyFile(srcFile, new File(filePath));
		} catch (IOException e) {
			System.err.println("Failed to save screenshot: " + e.getMessage());
		}
	}

}
