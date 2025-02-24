package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.ScreenShotsUtility;
import Patrol.utilities.WaitUtility;

public class ActiveFirmPage extends BasePage {

	public ActiveFirmPage (WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='ajaxStatusDiv']//img")
	private WebElement loadder;
	
	@FindBy(xpath = "//input[@id='companySearch']")
	private WebElement serach_bar_ele;
	
	@FindBy(xpath = "//p[text()='No companies found.']")
	WebElement search_message_ele;
	
	@FindBy(xpath="//a[text()='Legitquest']")
	private WebElement legitquest;
	public void clickOnLegitquest(){
        legitquest.click();
	}
	
	public void clickOnCompany(String companyName) {
	    String xpath = "//div[contains(@class,'company-item')]//a";
	    List<WebElement> companies = driver.findElements(By.xpath(xpath));
	    boolean found = false;
	    for (WebElement company : companies) {
	    	BrowserUtility.scrollIntoView(driver, company);
	        if (company.getText().trim().equalsIgnoreCase(companyName)) {
	        	WaitUtility.waitForElementToBeVisible(driver, company);
	        	WaitUtility.waitForElementToBeClickable(driver, company);
	            company.click();
	            found = true;
	            break;
	        }
	    }
	    if (!found) {
	        System.out.println(companyName + " Not Found");
	        ScreenShotsUtility.takeScreenshot(driver);
	    }
	}
	
	public void searchCompany(String companyName) {
		BrowserUtility.sendKeys(driver, serach_bar_ele,companyName);
		WaitUtility.waitForSeconds(5);
		boolean isMsgDisplayed  = WaitUtility.waitForElementToBeVisible(driver, search_message_ele);
		if(isMsgDisplayed){
			System.out.println(companyName + " Not Found");	
			ScreenShotsUtility.takeScreenshot(driver);
		}else {
			ScreenShotsUtility.takeScreenshot(driver);
			clickOnCompany(companyName);
		}
	}

}
