package Patrol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.WaitUtility;

public class MyCauselistPage extends BasePage{

	public MyCauselistPage(WebDriver driver) {
		super(driver);
		
	}
	//x path

     @FindBy(xpath="//a[normalize-space()='Daily']")  
     WebElement daily;
     @FindBy(xpath="//a[normalize-space()='Weekly']")  
     WebElement weekly;
     @FindBy(xpath="//a[normalize-space()='Archived']")  
     WebElement archived;
     
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

}
