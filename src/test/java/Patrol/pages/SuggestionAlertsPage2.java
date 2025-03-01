package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.WaitUtility;

public class SuggestionAlertsPage2 extends BasePage {

	public SuggestionAlertsPage2(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//a[contains(@class,'navbar-brand')]//img")
	WebElement logo;
	
	@FindBy(xpath = "//table[contains(@class,\"table\")]")
	WebElement table;

	@FindBy(xpath = "//span[text()='Alerts']")
	WebElement alertsLink;

	@FindBy(xpath = "//a[normalize-space()='Suggestion Alert']")
	WebElement suggestionAlert;
	
	@FindBy(xpath = "//div[normalize-space(text())='High Court']")
	WebElement highcourtTab;

	@FindBy(xpath = "//div[contains(text(),'District Court')]")
	WebElement districtCourtTab;
	
	@FindBy(xpath = "//div[normalize-space(text())='Supreme Court']")
	WebElement supremecourtTab;

	@FindBy(xpath = "//div[contains(text(),'Tribunal Court')]")
	WebElement tribunalsCourtTab;

	@FindBy(css = "ul.pagination>li:last-child")
	WebElement nextButton;
	
	@FindBy(css = "ul.pagination>li:nth-last-child(2)")
	WebElement scecondLastPage;
	
	public void clickOnSuggestionAlert() {
		suggestionAlert.click();
	}

	public void clickAlertsLink() {
		alertsLink.click();
	}

	public void clickOnSupremeCourtTab() {
		supremecourtTab.click();
	}
	
	public void clickOnHighCourtTab() {
		highcourtTab.click();
	}
	
	public void clickOnTribunalCourtTab() {
		tribunalsCourtTab.click();
	}

	public void clickOnDistrictCourtTab() {
		districtCourtTab.click();
	}

	public boolean isTagFound() {
		return false;
	}
	
	public void clickOnTag(String tagName) {
		if (table == null) {
			throw new IllegalStateException("Table element not initialized.");
		}
		List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
		boolean found = false;
		for (int i = 0; i < rows.size(); i++) {
			try {
				WebElement tagCell = rows.get(i).findElement(By.xpath(".//td[1]//a"));
				BrowserUtility.scrollIntoView(driver, tagCell);
				if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
					tagCell.click();
					found = true;
					break;
				}
			} catch (NoSuchElementException e) {
				WebElement tagCell = rows.get(i).findElement(By.xpath(".//td[1]"));
				BrowserUtility.scrollIntoView(driver, tagCell);
				if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
					System.out.println(tagName + " => found but tag not executable");
					found = true;
				} else {
					continue;
				}
			}
		}
		if (!found) {
			System.out.println(tagName + " => Tag Not Found");
		}
	}
	
	public boolean isTableVisible() {
		return false;
	}
	
	public int getRowCount() {
		int row_cout = 0;
		return row_cout;
	}
	
	public boolean isCaseNumberFound() {
		return false;
	}
	
	public boolean isLinkFoundInRow() {
		return false;
	}
	
	public boolean isPattrnFoundInRow() {
		return false;
	}
	
	public String getLinkOfRow(){
		return "link";
	}
	
	public void clickOnLink(String link) {}
	
	public boolean isCaseDetailsOpened() {
		return false;
	}
	
	public boolean isNextButtonDisabled() {
		WaitUtility.waitForElementToBeVisible(driver, nextButton);
		String buttonClass = nextButton.getAttribute("class");  
		if (buttonClass.contains("disabled")) {  
		   return true;  
		} else {  
		   return false;
		}
	}
	public void clickOnNextButton() {
		BrowserUtility.click(driver, nextButton);
	}
	
	public boolean isPreButtonEnabled() {return false;}
	public void clickOnPreButton() {}
	
	public int getSecondLastPageNumber() {
		return Integer.parseInt(scecondLastPage.getText());
	}
	
	public boolean isLogoDisplayed() {
		return WaitUtility.waitForElementToBeVisible(driver,logo);
	}
	
	public void goToPreviousPage(){
		driver.navigate().back();
	}
	
	public void clickOnPage(String page) {
		String xpath =  "//ul[contains(@class,'pagination')]//li//a[text()='page']";
		xpath = xpath.replace("page", page);
		WaitUtility.waitForElementToBeClickable(driver,driver.findElement(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
	}
	

}
