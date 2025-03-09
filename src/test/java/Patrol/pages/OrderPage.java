package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.WaitUtility;

public class OrderPage extends BasePage{

	public OrderPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[contains(@class,'navbar-brand')]//img")
	WebElement logo;

	@FindBy(xpath = "//main//div[@class='table-responsive']//table")
	WebElement table;
	
	@FindBy(xpath="//main//div[@class='table-responsive']//table//tbody//tr")
	List<WebElement> table_rows;
	
	@FindBy(css="div.dataTables_paginate>span>a:last-child")
	WebElement lastPageNum;
	
	@FindBy(xpath ="//div[contains(@class,'dataTables_paginate ')]//a[contains(@class,'next') and text()='Next']")
	WebElement nextPageButton;

	public boolean isLogoDisplayed() {
		return WaitUtility.waitForElementToBeVisible(driver, logo);
	}
	
	public boolean isTableVisible() {
		return WaitUtility.waitForElementToBeVisible(driver, table);
	}
	
	public int getTableRowsCount() {
		return table_rows.size();
	}
	
	public void clickOnViewOrder(String rowcount) {
		String xpath = ".//tbody//tr[rowcount]";
		xpath = xpath.replace("rowcount",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		WebElement vieworder = row.findElement(By.xpath(".//td[6]//a[normalize-space(text())='View Order']"));
		WaitUtility.waitForElementToBeVisible(driver,vieworder);
		BrowserUtility.scrollIntoView(driver,row,true);
		BrowserUtility.mouseToElement(driver, vieworder);
        WaitUtility.waitForElementToBeClickable(driver, vieworder);
        vieworder.click();
	}
	
	public WebElement getViewOrderElement(String rowcount) {
		String xpath = ".//tbody//tr[rowcount]";
		xpath = xpath.replace("rowcount",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		WebElement vieworder = row.findElement(By.xpath(".//td[6]//a[normalize-space(text())='View Order']"));
		return vieworder;
	}
	
	public String getViewOrderElementLink(String rowcount) {
		String xpath = ".//tbody//tr[rowcount]";
		xpath = xpath.replace("rowcount",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		WebElement vieworder = row.findElement(By.xpath(".//td[6]//a[normalize-space(text())='View Order']"));
		return vieworder.getAttribute("href");
	}
	
	public String getMainTab() {
		return driver.getWindowHandle();
	}
	
	public void goToMainTab(String tabHandle) {
		driver.switchTo().window(tabHandle);
	}
	
	public int getSecondLastPageNumber() {
		return Integer.parseInt(lastPageNum.getText());
	}
	
	public boolean isNextButtonDisabled() {
		WaitUtility.waitForElementToBeVisible(driver, nextPageButton);
		String buttonClass = nextPageButton.getAttribute("class");
		if (buttonClass.contains("disabled")) {
			return true;
		} else {
			return false;
		}
	}
	
	public WebElement getNextPageButton() {
		return nextPageButton;
	}

	public void clickOnNextButton() {
		nextPageButton.click();
	}
 
}
