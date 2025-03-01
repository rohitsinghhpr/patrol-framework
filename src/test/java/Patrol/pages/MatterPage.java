package Patrol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Patrol.utilities.WaitUtility;

public class MatterPage extends BasePage{

	public MatterPage(WebDriver driver) {
		super(driver);
	}
	
    //Methods
    
 	public boolean isTabVisible(String tab_name) {
 		String xpath = "//nav//a[normalize-space(text())='tab_name']";
 		xpath = xpath.replace("tab_name", tab_name);
 		WebElement tab = driver.findElement(By.xpath(xpath));
 		return WaitUtility.waitForElementToBeVisible(driver, tab);
 	}
 	
 	public void clickOnTab(String tab_name) {
		String xpath = "//nav//a[normalize-space(text())='tab_name']";
		xpath = xpath.replace("tab_name", tab_name);
		WebElement tab = driver.findElement(By.xpath(xpath));
		tab.click();
	}
	
	public boolean isTabActive(String tab_name) {
		String xpath = "//nav//a[normalize-space(text())='tab_name']";
		xpath = xpath.replace("tab_name", tab_name);
		WebElement tab = driver.findElement(By.xpath(xpath));
		String activeClass = tab.getAttribute("class");  
		if (activeClass.contains("active")) {  
		   return true;  
		} else {  
		   return false;
		}
	}

}
