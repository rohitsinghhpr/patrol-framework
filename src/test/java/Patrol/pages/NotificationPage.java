package Patrol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Patrol.utilities.WaitUtility;

public class NotificationPage extends BasePage{

	public NotificationPage(WebDriver driver) {
		super(driver);
	}
	
	// xpath 
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='All']")
	WebElement allTab;
	
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='Case Detail']")
	WebElement caseDetailTab;
	
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='Cause List']")
	WebElement causeListTab;
	
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='Next Date']")
	WebElement nextDateTab;
	
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='Order']")
	WebElement orderTab;
	
	@FindBy(xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='Invoice']")
	WebElement invoiceTab;
	
	// method	
	public boolean isTabVisible(String tab_name) {
		String xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='tab_name']";
		xpath = xpath.replace("tab_name", tab_name);
		WebElement tab = driver.findElement(By.xpath(xpath));
		return WaitUtility.waitForElementToBeVisible(driver, tab);
	}
	
	public void clickOnTab(String tab_name) {
		String xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='tab_name']";
		xpath = xpath.replace("tab_name", tab_name);
		WebElement tab = driver.findElement(By.xpath(xpath));
		tab.click();
	}
	
	public boolean isTabActive(String tab_name) {
		String xpath = "//li[@class='nav-item']//a//div[normalize-space(text())='tab_name']//parent::*";
		xpath = xpath.replace("tab_name", tab_name);
		WebElement tab = driver.findElement(By.xpath(xpath));
		String activeClass = tab.getAttribute("class");  
		if (activeClass.contains("active")) {  
		   return true;  
		} else {  
		   return false;
		}
	}
	
	/* isHeaderVisible();
	 * isTabVisible()
	 * clickOnTab(tab_name);
	 * isTabActive();
	 * isPageURLContains(patten)
	 * */

}
