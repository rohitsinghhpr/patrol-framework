package Patrol.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.ScreenShotsUtility;
import Patrol.utilities.WaitUtility;

public class SuggestionAlertsPage extends BasePage {

	WebDriverWait wait;

	public SuggestionAlertsPage(WebDriver driver) {
		super(driver);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

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

	@FindBy(xpath = "//a[@aria-label=\"Next »\"]")
	WebElement nextButton;

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

	public void clickOnTagAllPages(String tagName) {
		int pageNum = 1; // Track the page number
		boolean found = false;

		while (true) {
			// Wait for the table to be visible on each page
			wait.until(ExpectedConditions.visibilityOf(table));
			System.out.println("Page Number: " + pageNum);

			List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));

			for (WebElement row : rows) {
				try {
					WebElement tagCell = row.findElement(By.xpath(".//td[1]//a"));
					BrowserUtility.scrollIntoView(driver, tagCell);

					if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
						tagCell.click();
						System.out.println(tagName + " => Tag found and clicked.");
						found = true;
						return; // Exit once the tag is clicked
					}
				} catch (NoSuchElementException e) {
					WebElement tagCell = row.findElement(By.xpath(".//td[1]"));
					BrowserUtility.scrollIntoView(driver, tagCell);

					if (tagCell.getText().trim().equalsIgnoreCase(tagName)) {
						System.out.println(tagName + " => Found but not clickable.");
						found = true;
						return; // Exit as the tag is found, even if not clickable
					}
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

		if (!found) {
			System.out.println(tagName + " => Tag Not Found on any page.");
		}
	}

	public boolean findCaseNumber(String caseNumberToFind) {
		if (table == null) {
			throw new IllegalStateException("Table element not initialized.");
		}
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int i = 1; i < rows.size(); i++) { // Skip the header row
			List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
			if (cells.size() > 1 && cells.get(1).getText().equals(caseNumberToFind)) {
				System.out.println(cells.get(1).getText());
				return true; // Case Number found
			}
		}
		System.out.println("Case number not found");
		return false; // Case Number not found
	}

	public boolean findCaseNumberAllPage(String caseNumberToFind) throws InterruptedException {
		int pageNum = 1; // to track the page, on which page case is found
		while (true) {
			// Wait for the table to be visible on each page
			wait.until(ExpectedConditions.visibilityOf(table));
			System.out.println("Page Number: " + pageNum);
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			for (int i = 1; i < rows.size(); i++) { // Skipping the header row
				List<WebElement> cells = rows.get(i).findElements(By.tagName("td"));
				if (cells.size() > 1 && cells.get(1).getText().equals(caseNumberToFind)) {
					System.out.println("Case Number found: " + cells.get(1).getText());
					return true; // Case Number found
				}
			}
			if (table.isDisplayed()) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
				Thread.sleep(5000);
				nextButton.click(); // Click "Next" button
				pageNum++;
			} else {
				System.out.println("Case Number not found in any page.");
				return false; // Case Number not found after pagination
			}
		}
	}

	public void checkLinkAllPages() throws InterruptedException {
		int pageNum = 1; // Track the page number
		while (true) {
			// Wait for the table to be visible on each page
			table = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
			List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
			for (int i = 0; i < rows.size(); i++) {
				// Refresh table and rows to handle any stale element exceptions
				try {
					table = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
					rows = table.findElements(By.xpath("//tbody//tr"));
				} catch (Exception ex) {
					// Retry in case of stale element or timing issues
					table = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
					rows = table.findElements(By.xpath("//tbody//tr"));
				}

				// Getting row and cselink form table
				WebElement row = rows.get(i);
				WebElement caseLink = row.findElement(By.xpath(".//td[3]//a[not(@class='addTag')]"));

				if (caseLink.getText().length() != 0) {
					try {
						WaitUtility.waitForElementToBeClickable(driver, caseLink);
						caseLink.click();
						System.out.println("page no => " + pageNum + " case link => " + (i + 1));
					} catch (Exception ex) {
						BrowserUtility.scrollIntoView(driver, caseLink, true);
						Thread.sleep(5000);
						WaitUtility.waitForElementToBeClickable(driver, caseLink);
						caseLink.click();
						System.out.println("page no => " + pageNum + " case link => " + (i + 1));
					}
					// Check if the "Cases Detail" text is visible on the page
					try {
						CasesDetailPage caseDetailPage = new CasesDetailPage(driver);
						Assert.assertEquals(caseDetailPage.isCaseDetailTabVisible(), true, "Case Detail Tab Not Found");
					} catch (Exception e) {
						System.out.println("Case Detail Tab Not Found For Link " + driver.getCurrentUrl());
						ScreenShotsUtility.addScreenshotToReport(driver,
								"case-detail-tab-not-found-on-page-no-" + pageNum + "-and-row-" + (i + 1));
					}
					driver.navigate().back(); // Navigate back to the table page
				} else {
					System.out.println("Case Link Not Found In Page No " + pageNum + " And Row No " + (i + 1));
					ScreenShotsUtility.addScreenshotToReport(driver,
							"case-link-not-found-page-no-" + pageNum + "and-row-" + (1 + i));
					continue;
				}
			}
			// Check if pagination is available and proceed to the next page
			try {
				if (nextButton.isDisplayed()) {
					BrowserUtility.scrollToDown(driver);
					WaitUtility.waitForElementToBeInvisible(driver, nextButton);
					nextButton.click();
					// ScreenShotsUtility.takeScreenshot(driver);
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
	}
	
	public void matchCaseNumberAllPages(String pattern) throws InterruptedException {
		int pageNum = 1; // Track the page number
		while (true) {
			// Wait for the table to be visible on each page
			table = wait.until(
					ExpectedConditions.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
			List<WebElement> rows = table.findElements(By.xpath("//tbody//tr"));
			for (int i = 0; i < rows.size(); i++) {
				// Refresh table and rows to handle any stale element exceptions
				try {
					table = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
					rows = table.findElements(By.xpath("//tbody//tr"));
				} catch (Exception ex) {
					// Retry in case of stale element or timing issues
					table = wait.until(ExpectedConditions
							.presenceOfElementLocated(By.xpath("//table[contains(@class,\"table\")]")));
					rows = table.findElements(By.xpath("//tbody//tr"));
				}

				// Getting row and cselink form table
				WebElement row = rows.get(i);
				String caseNumber = row.findElement(By.xpath(".//td[2]")).getText();

				if (caseNumber.length() != 0) {
					if(caseNumber.contains(pattern)) {
						System.out.println("contains pattern -> "+caseNumber);
					}else {
						System.out.println("does not contain pattern -> "+caseNumber);
					}
				} else {
					System.out.println("Case Number Not Found In Page No " + pageNum + " And Row No " + (i + 1));
					continue;
				}
			}
			// Check if pagination is available and proceed to the next page
			try {
				if (nextButton.isDisplayed()) {
					BrowserUtility.scrollToDown(driver);
					WaitUtility.waitForElementToBeInvisible(driver, nextButton);
					nextButton.click();
					// ScreenShotsUtility.takeScreenshot(driver);
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
	}

}