package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.WaitUtility;

public class CasesPage2 extends BasePage{
	public CasesPage2(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[contains(@class,'navbar-brand')]//img")
	WebElement logo;

	@FindBy(xpath = "//table[contains(@class,\"table\")]")
	WebElement table;
	
	@FindBy(xpath="//table[contains(@class,'table')]//tbody//tr")
	List<WebElement> table_rows;
	
	@FindBy(css = "ul.pagination>li:last-child")
	WebElement nextButton;

	@FindBy(css = "ul.pagination>li:nth-last-child(2)")
	WebElement scecondLastPage;

	public boolean isLogoDisplayed() {
		return WaitUtility.waitForElementToBeVisible(driver, logo);
	}
	
	public boolean isTableVisible() {
		return WaitUtility.waitForElementToBeVisible(driver, table);
	}
	
	public int getTableRowsCount() {
		return table_rows.size();
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
	
	public void clickOnLink(String rowcount) {
		String xpath = ".//tbody//tr[1]";
		xpath = xpath.replace("1",rowcount);
		WaitUtility.waitForElementToBeVisible(driver,table);
		WebElement row = table.findElement(By.xpath(xpath));
		WaitUtility.waitForElementToBeVisible(driver, row);
		WebElement caseLink = row.findElement(By.xpath(".//td[2]//a[not(@class='addTag')]"));
		WaitUtility.waitForElementToBeVisible(driver,caseLink);
		BrowserUtility.scrollIntoView(driver,row,true);
		BrowserUtility.mouseToElement(driver, caseLink);
        WaitUtility.waitForElementToBeClickable(driver, caseLink);
        caseLink.click();
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
