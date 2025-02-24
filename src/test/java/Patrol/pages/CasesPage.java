package Patrol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.ScreenShotsUtility;
import Patrol.utilities.WaitUtility;

public class CasesPage extends BasePage {

	public CasesPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space(text())='Add Case']")
	WebElement addCaseButton;

	@FindBy(xpath = "//span[text()='Filter']")
	WebElement filter;

	@FindBy(xpath = "//li//a[text()='Supreme Court']")
	WebElement supreme_court_option;

	@FindBy(xpath = "//a[normalize-space()='High Court']")
	WebElement high_court_option;

	@FindBy(xpath = "//a[normalize-space()='District Court']")
	WebElement district_court_option;

	@FindBy(xpath = "//a[normalize-space()='Tribunals']")
	WebElement tribunals_court_option;

	@FindBy(xpath = "//h6[normalize-space(text())='Supreme Court Cases']")
	WebElement supreme_court_text;

	@FindBy(xpath = "//h6[normalize-space()='High Court Cases']")
	WebElement high_court_textElement;

	@FindBy(xpath = "//h6[normalize-space()='District Court Cases']")
	WebElement district_court_textElement;

	@FindBy(xpath = "//h6[normalize-space()='Tribunals Cases']")
	WebElement tribunals_court_textElement;

	@FindBy(xpath = "//table[contains(@class,\"table\")]")
	WebElement table;

	@FindBy(xpath = "//a[@aria-label=\"Next »\"]")
	WebElement nextButton;
	
	@FindBy(xpath = "//a[@href='#tab0-detail']")
	WebElement case_detail_text;

	public void clickOnFilter() {
		filter.click();
	}

	public AddCasePage clickAddCaseButton() {
		addCaseButton.click();
		return new AddCasePage(driver);
	}

	public void clickOnSupremeCourt() {
		supreme_court_option.click();
	}

	public void clickOnHighCourt() {
		high_court_option.click();
	}

	public void clickOnDistrictCourt() {
		district_court_option.click();
	}

	public void clickOnTribunalsCourt() {
		tribunals_court_option.click();
	}

	public void checkLink() throws InterruptedException {
		WaitUtility.waitForElementToBeVisible(driver, table);
		List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
		int rowCount = 0;
		for (int i = 0; i < rows.size(); i++) {
			// Refresh table and rows to handle any stale element exceptions
			try {
				WaitUtility.waitForElementToBeVisible(driver, table);
				rows = table.findElements(By.xpath("//tbody//tr"));
			} catch (Exception ex) {
				// Retry in case of stale element or timing issues
				WaitUtility.waitForElementToBeVisible(driver, table);
				rows = table.findElements(By.xpath("//tbody//tr"));
			}

			// Getting row and cselink form table
			WebElement row = rows.get(i);

			if (row.getText().trim().isEmpty()) {
				System.out.println("row is empty in dom  row " + (1 + i));
				++rowCount;
				continue;
			}

			WebElement caseNumer = row.findElement(By.xpath(".//td[1]//input"));
			WebElement caseLink = row.findElement(By.xpath(".//td[2]//a[not(@class='addTag')]"));

			if (caseLink.getText().length() != 0) {
				try {
					BrowserUtility.scrollIntoView(driver, caseNumer, true);
					WaitUtility.waitForSeconds(5);
					WaitUtility.waitForElementToBeClickable(driver, caseLink);
					caseLink.click();
					ScreenShotsUtility.takeScreenshot(driver);
					System.out.println("case link => " + (i + 1-rowCount));
				} catch (Exception ex) {
					BrowserUtility.scrollIntoView(driver, caseNumer, true);
					WaitUtility.waitForSeconds(5);
					WaitUtility.waitForElementToBeClickable(driver, caseLink);
					caseLink.click();
					ScreenShotsUtility.takeScreenshot(driver);
					System.out.println("case link => " + (i + 1-rowCount));
				}

				// Check if the "Cases Detail" text is visible on the page
				try {
					WaitUtility.waitForElementToBeVisible(driver, case_detail_text);
					String actualText = case_detail_text.getText();
					String expectedText = "Cases Detail";
					Assert.assertEquals(actualText, expectedText, "Case Detail text mismatch on row " + (i + 1-rowCount));
					ScreenShotsUtility.addScreenshotToReport(driver,
							"case-detail on row " + (i + 1));
				} catch (Exception e) {
					System.out.println("Case Detail not found for link on row " + (i + 1-rowCount) + ". Test Failed.");
					System.out.println(
							"Case Detail not found for link on row " + driver.getCurrentUrl() + ". Test Failed.");
					ScreenShotsUtility.addScreenshotToReport(driver,
							"not-found-case-detail on row " + (i + 1-rowCount));
				}
				driver.navigate().back(); // Navigate back to the table page
			} else {
				// skipping, when link not found.
				ScreenShotsUtility.addScreenshotToReport(driver,
						"case-link-not-found row " + (1 + i-rowCount));
				continue;
			}
		}
	}

	public void checkLinkAllPages() {
		int pageNum = 1; // Track the page number
		SoftAssert softAssert = new SoftAssert();
		while (true) {
			// Wait for the table to be visible on each page
			WaitUtility.waitForElementToBeVisible(driver, table);
			List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
			for (int i = 0; i < rows.size(); i++) {
				int rowCount = 0;
				// Refresh table and rows to handle any stale element exceptions
				try {
					WaitUtility.waitForElementToBeVisible(driver, table);
					rows = table.findElements(By.xpath("//tbody//tr"));
				} catch (Exception ex) {
					// Retry in case of stale element or timing issues
					WaitUtility.waitForElementToBeVisible(driver, table);
					rows = table.findElements(By.xpath("//tbody//tr"));
				}

				// Getting row and cselink form table
				WebElement row = rows.get(i);

				if (row.getText().trim().isEmpty()) {
					System.out.println("row is empty in dom page no-" + pageNum + " row-" + (1 + i));
					rowCount++;
					continue;
				}

				WebElement caseNumer = row.findElement(By.xpath(".//td[1]//input"));
				WebElement caseLink = row.findElement(By.xpath(".//td[2]//a[not(@class='addTag')]"));

				if (caseLink.getText().length() != 0) {
					try {
						BrowserUtility.scrollIntoView(driver, caseNumer, true);
						WaitUtility.waitForSeconds(5);
						WaitUtility.waitForElementToBeClickable(driver, caseLink);
						caseLink.click();
						ScreenShotsUtility.takeScreenshot(driver);
						System.out.println("page no => " + pageNum + "case link => " + (i + 1-rowCount));
					} catch (Exception ex) {
						BrowserUtility.scrollIntoView(driver, caseNumer, true);
						WaitUtility.waitForSeconds(5);
						WaitUtility.waitForElementToBeClickable(driver, caseLink);
						caseLink.click();
						ScreenShotsUtility.takeScreenshot(driver);
						System.out.println("page no => " + pageNum + " case link => " + (i + 1-rowCount));
					}
					// Check if the "Cases Detail" text is visible on the page
//					} catch (Exception e) {
//						System.out.println("Case Detail not found for link on row " + (i + 1-rowCount) + ". Test Failed.");
//						System.out.println(
//								"Case Detail not found for link on row " + driver.getCurrentUrl() + ". Test Failed.");
//						ScreenShotsUtility.addScreenshotToReport(driver,
//								"not-found-case-detail on page no-" + pageNum + " and row " + (i + 1-rowCount));
//					}
					String errorMsg = "Case detail not found for link => " + driver.getCurrentUrl();
					softAssert.assertEquals(WaitUtility.waitForElementToBeVisible(driver, case_detail_text), true,errorMsg);
					driver.navigate().back(); // Navigate back to the table page
				} else {
					// skipping, when link not found.
					ScreenShotsUtility.addScreenshotToReport(driver,
							"case-link-not-found page no " + pageNum + " row " + (1 + i-rowCount));
					continue;
				}
			}
			// Check if pagination is available and proceed to the next page
			try {
				if (nextButton.isDisplayed()) {
					BrowserUtility.scrollToDown(driver);
					WaitUtility.waitForElementToBeInvisible(driver, nextButton);
					nextButton.click();
					pageNum++;
				} else {
					System.out.println("Reached the last page. Completed processing all links.");
					break; // Exit loop when no next page is available
				}
			} catch (Exception e) {
				System.out.println("No more pages available or error navigating to the next page. Exiting.");
				break;
			}
		}
		softAssert.assertAll();
	}

}