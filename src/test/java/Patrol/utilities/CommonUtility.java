package Patrol.utilities;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonUtility {
	// generate random number
	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt((max - min) + 1) + min; 
	}

	// to click on sidebar
	public static void clickOnLink(WebDriver driver, String link_name) {
		String xpath = "//aside//li//a[contains(@class, 'nav-link')]//span[normalize-space(text())='linkname']";
		xpath = xpath.replace("linkname", link_name);
		WebElement link = driver.findElement(By.xpath(xpath));
		BrowserUtility.click(driver, link);
	}

	// verify page header
	public static String verifyPageHeader(WebDriver driver) {
		String xpath = "//div[contains(@class,'page-header')]//h1";
		WebElement header = driver.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, header);
		return header.getText();
	}

	public static boolean isPageHeaderVisible(WebDriver driver) {
		String xpath = "//div[contains(@class,'page-header')]//h1";
		WebElement header = driver.findElement(By.xpath(xpath));
		return WaitUtility.waitForElementToBeVisible(driver, header);
	}

	// verify links status
	public static boolean isLinkStatus200(WebElement link) throws IOException, InterruptedException {
	    String url = link.getAttribute("href");
	    boolean status = false;
	    if (url != null && !url.isEmpty()) {
	        HttpClient client = HttpClient.newHttpClient();
	        HttpRequest request = HttpRequest.newBuilder()
	                .uri(URI.create(url))
	                .method("HEAD", HttpRequest.BodyPublishers.noBody())
	                .build();
	        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
	        int responseCode = response.statusCode();
	        if (responseCode >= 400) {
	            status = false;
	        } else {
	            status = true;
	        }
	    }
	    return status;
	}

}
