package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
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
	
	@FindBy(xpath="//table[contains(@class,'table')]//tbody//tr")
	List<WebElement> table_rows;

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
	
	public boolean isLogoDisplayed() {
		return WaitUtility.waitForElementToBeVisible(driver, logo);
	}

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

	public boolean isTableVisible() {
		return WaitUtility.waitForElementToBeVisible(driver, table);
	}
	
	public int getTableRowsCount() {
		return table_rows.size();
	}

	public boolean isTagFound(String tagName) {
		List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
		boolean found = false;
		for (int i = 0; i < rows.size(); i++) {
			WebElement tagCell = rows.get(i).findElement(By.xpath(".//td[1]//a"));
			BrowserUtility.scrollIntoView(driver, tagCell);
			if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
				found = true;
				break;
			}
		}
		return found;
	}

	public void clickOnTag(String tagName) {
		WaitUtility.waitForElementToBeVisible(driver,table);
		List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
		for (int i = 0; i < rows.size(); i++) {
			WebElement tagCell = rows.get(i).findElement(By.xpath(".//td[1]//a"));
			BrowserUtility.scrollIntoView(driver, tagCell,true);
			if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
				tagCell.click();
				break;
			}
		}
	}

	public boolean isCaseNumberFound(String caseNumberToFind) {
		WaitUtility.waitForElementToBeVisible(driver,table);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		boolean found = false;
		for (int i = 1; i < rows.size(); i++) { // Skip the header row
			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
			if (cells.size() > 1 && cells.get(1).getText().equals(caseNumberToFind)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	public boolean isLinkFoundInRow(WebElement row) {
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement caseLink = row.findElement(By.xpath(".//td[3]//a[not(@class='addTag')]"));
		try {
			return WaitUtility.waitForElementToBeVisible(driver, caseLink);
		} catch (Exception ex) {
			WaitUtility.waitForSeconds(5);
			BrowserUtility.scrollIntoView(driver, caseLink, true);
			return WaitUtility.waitForElementToBeVisible(driver, caseLink);
		}
	}
	
	public void clickOnLinkInRow(String rowcount) {
		String xpath = ".//tbody//tr[1]";
		xpath = xpath.replace("1",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		WebElement caseLink = row.findElement(By.xpath(".//td[3]//a[not(@class='addTag')]"));
		WaitUtility.waitForElementToBeVisible(driver,caseLink);
		BrowserUtility.scrollIntoView(driver,row,true);
		BrowserUtility.mouseToElement(driver, caseLink);
        WaitUtility.waitForElementToBeClickable(driver, caseLink);
        caseLink.click();
	}

	public boolean isPattrnFoundInRow(String rowcount,String pattern) {
		String xpath = ".//tbody//tr[1]";
		xpath = xpath.replace("1",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		BrowserUtility.scrollIntoView(driver,row,true);
		String caseNumber = row.findElement(By.xpath(".//td[2]")).getText();
		if(caseNumber.contains(pattern)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getPattrnFoundInRow(String rowcount,String pattern) {
		String xpath = ".//tbody//tr[1]";
		xpath = xpath.replace("1",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		BrowserUtility.scrollIntoView(driver,row,true);
		String caseNumber = row.findElement(By.xpath(".//td[2]")).getText();
		return caseNumber;
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
		nextButton.click();
	}

	public boolean isPreButtonEnabled() {
		return false;
	}

	public void clickOnPreButton() {
	}

	public int getSecondLastPageNumber() {
		return Integer.parseInt(scecondLastPage.getText());
	}

	public void goToPreviousPage() {
		driver.navigate().back();
	}

	public void clickOnPage(String page) {
		String xpath = "//ul[contains(@class,'pagination')]//li//a[text()='page']";
		xpath = xpath.replace("page", page);
		WaitUtility.waitForElementToBeClickable(driver, driver.findElement(By.xpath(xpath)));
		driver.findElement(By.xpath(xpath)).click();
	}

}
