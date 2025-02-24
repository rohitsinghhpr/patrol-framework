package Patrol.pages;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ScreenShotsUtility;
import Patrol.utilities.WaitUtility;

public class AddCasePage extends BasePage {
	public AddCasePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[normalize-space(text())='Supreme Court']")
	WebElement supremeCourtLink;

	@FindBy(xpath = "//span[normalize-space(text())='High Courts']")
	WebElement highCourtLink;

	@FindBy(xpath = "//span[normalize-space(text())='Tribunals']")
	WebElement tribunalsCourtLink;

	@FindBy(xpath = "//span[normalize-space(text())='District Courts']")
	WebElement districtCourtLink;

	@FindBy(xpath = "//button[contains(normalize-space(.), 'Search For Case')]")
	WebElement search_case_btn_ele;

	@FindBy(xpath = "//div[@id='nav-tab']")
	WebElement nav_tabs_ele;

	@FindBy(xpath = "//button[contains(normalize-space(.), 'Add Case')]")
	WebElement add_case_btn_ele;

	@FindBy(xpath = "//input[@id='diary_no']")
	WebElement diary_no_ele;

	@FindBy(xpath = "//select[@name='year']")
	WebElement year_ele;

	@FindBy(xpath = "//div[@class='case-list']")
	WebElement case_list_ele;

	@FindBy(xpath = "//div[@class='case-list']//p[contains(text(),'Currenly This searched case is not available.')]")
	WebElement case_not_found_msg_ele;

	@FindBy(xpath = "//div[@class='custom-check']//label")
	WebElement case_list_checkbox_ele;

	@FindBy(xpath = "//span[contains(@class,'select2-dropdown')]")
	WebElement select2_dropdown_ele;

	@FindBy(xpath = "//span[contains(@class,'select2-dropdown')]//input[@type='search']")
	WebElement select2_dropdown_search_ele;

	@FindBy(xpath = "//span[contains(@class,'select2-dropdown')]//ul[@class='select2-results__options']")
	WebElement select2_dropdown_options_ele;

	@FindBy(xpath = "swal2-container")
	WebElement popup_ele;

	@FindBy(xpath = "//div[contains(@class,'swal2-actions')]//button[text()='OK']")
	WebElement popup_ok_ele;

	public void clickOnSupremeCourt() {
		supremeCourtLink.click();
	}

	public void clickOnHighCourt() {
		highCourtLink.click();
	}

	public void clickOnTribunalsCourt() {
		tribunalsCourtLink.click();
	}

	public void clickOnDistrictCourt() {
		districtCourtLink.click();
	}

	// get Input Tag Form Tab
	public WebElement getInputElementFromTab(String tabName, String labelName) {
		String xpath = "//div[@id='tabName']//label[normalize-space(text())='labelName']//parent::*//following-sibling::input";
		xpath = xpath.replace("tabName", tabName);
		xpath = xpath.replace("labelName", labelName);
		WebElement input = driver.findElement(By.xpath(xpath));
		return input;
	}

	// get Select Tag Form Tab
	public WebElement getSelectElementFromTab(String tabName, String labelName) {
		String xpath = "//div[@id='tabName']//label[normalize-space(text())='labelName']//parent::*//following-sibling::select";
		xpath = xpath.replace("tabName", tabName);
		xpath = xpath.replace("labelName", labelName);
		WebElement select = driver.findElement(By.xpath(xpath));
		return select;
	}

	// get Custom drown down
	public WebElement getCustumSelectElementFromTab(String tabName, String labelName) {
		String xpath = "//div[@id='tabName']//label[normalize-space(text())='labelName']//parent::*//following-sibling::span";
		xpath = xpath.replace("tabName", tabName);
		xpath = xpath.replace("labelName", labelName);
		WebElement select = driver.findElement(By.xpath(xpath));
		return select;
	}

	// get Search Button Form Tab
	public WebElement getSearchCaseButtonFromTab(String tabName) {
		String xpath = "//div[@id='tabName']//button[contains(normalize-space(.), 'Search For Case')]";
		xpath = xpath.replace("tabName", tabName);
		WebElement button = driver.findElement(By.xpath(xpath));
		return button;
	}

	public void clickOnTab(String tabName) {
		// nav_tabs_ele
		List<WebElement> tabs = nav_tabs_ele.findElements(By.xpath("//button"));
		boolean found = false;
		for (WebElement tab : tabs) {
			BrowserUtility.scrollIntoView(driver, tab, true);
			if (tab.getText().trim().equalsIgnoreCase(tabName)) {
				tab.click();
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println(tabName + " Not Found");
		}
	}

	private boolean isCaseNotFoundMsgVisible() {
		return WaitUtility.waitForElementToBeVisible(driver, case_not_found_msg_ele);
	}

	public void supremeCourtByDiaryNumber(String year) throws IOException {
		File dataFile = new File("Supream-court-dairyNumber-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			String diaryNO = String.valueOf(CommonUtility.getRandomNumber(1, 100));
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Diary No *"), diaryNO);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);

			BrowserUtility.click(driver, search_case_btn_ele);

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, add_case_btn_ele);
				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
				// ScreenShotsUtility.takeScreenshot(driver);
				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
				BrowserUtility.click(driver, popup_ok_ele);
				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(diaryNO + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void supremeCourtByDiaryNumber(String diaryNO, String year) throws IOException {
		File dataFile = new File("Supream-court-dairyNO-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Diary No *"), diaryNO);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);

			BrowserUtility.click(driver, search_case_btn_ele);

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(diaryNO + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void supremeCourtByCaseType(String caseType, String caseNo, String year) throws IOException {
		File dataFile = new File("Supream-court-ByCaseType-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab2", "Case Type *"), caseType);
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab2", "Case No *"), caseNo);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab2", "Year *"), year);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab2"));

			if (isCaseNotFoundMsgVisible()) {
				writer.write(caseType + " " + caseNo + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(caseType + " " + caseNo + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void supremeCourtByPartyName(String partyName, String year) throws IOException {
		File dataFile = new File("Supream-court-ByPartyName-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab3", "Party Name *"), partyName);
			BrowserUtility.selectByValue(getSelectElementFromTab("tab3", "Year *"), year);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab3"));

			if (isCaseNotFoundMsgVisible()) {

				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(partyName + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void supremeCourtByAdvocateName(String advocate_name) {
		BrowserUtility.click(driver, getCustumSelectElementFromTab("tab4", "Advocate On-Record *"));
		WaitUtility.waitForElementToBeVisible(driver, select2_dropdown_ele);
		BrowserUtility.sendKeys(driver, select2_dropdown_search_ele, advocate_name);

		WebElement li = select2_dropdown_options_ele.findElement(By.tagName("li"));
		BrowserUtility.scrollIntoView(driver, li);
		BrowserUtility.click(driver, li);
		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab4"));
		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//			BrowserUtility.click(driver, case_list_checkbox_ele);
//			BrowserUtility.click(driver, add_case_btn_ele);
//			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//			BrowserUtility.click(driver, popup_ok_ele);
//			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	// High Court Methods
	// ----------------------------------------------------------------------
	public void highCourtByCase(String court, String bench, String caseType, String caseNo, String year) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), court);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench *"), bench);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No *"), caseNo);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data" + court + " " + bench + " " + caseType);
			ScreenShotsUtility.addScreenshotToReport(driver);
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void highCourtByCase(String court, String bench, String caseType) throws IOException {
		File dataFile = new File("High-court-ByCase-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), court);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench *"), bench);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(court + " " + bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void highCourtByCNR(String cnr) throws IOException {
		File dataFile = new File("high-court-CNR-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab2", "CNR Number *"), cnr);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab2"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(cnr + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void highCourtByPartyName(String Court, String CaseStatus, String year, String PartyName)
			throws IOException {
		File dataFile = new File("High-court-ByPartyName-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab3", "Court *"), Court);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab3", "Case Status *"), CaseStatus);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab3", "Year *"), year);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab3", "Party Name *"), PartyName);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab3"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.scrollToTop(driver);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(Court + " " + CaseStatus + " " + PartyName + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void highCourtByAdvocate(String Court, String Advocate, String Year) {

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab4", "Court *"), Court);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab4", "Advocate *"), Advocate);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab4", "Year *"), Year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab4"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.addScreenshotToReport(driver);
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void highCourtByAdvocate(String Court, String Advocate) throws IOException {
		File dataFile = new File("High-court-ByAdvocate-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab4", "Court *"), Court);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab4", "Advocate *"), Advocate);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab4"));
			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(Court + " " + Advocate + " " + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// Tribunals Court
	// Methods----------------------------------------------------------

	public void tribunalsCourtByCaseNCLT(String bench, String caseType, String caseNo, String year, String caseStatus,
			String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NCLT");
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench *"), bench);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Status *"), caseStatus);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.addScreenshotToReport(driver);
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseNCLT(String bench, String caseType) throws IOException {
		File dataFile = new File("NCLT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NCLT");
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench *"), bench);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Status *"), "All");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseNCLAT(String state, String caseType, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NCLAT");
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");

			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseNCLAT(String state, String caseType) throws IOException {
		File dataFile = new File("NCLAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NCLAT");
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(state + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseITAT(String state, String caseType, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "ITAT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseITAT(String state, String caseType) throws IOException {
		File dataFile = new File("ITAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "ITAT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State*"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(state + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseAPTEL(String caseType, String caseNo, String DFR, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "APTEL");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "DFR No"), DFR);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseAPTEL(String caseType) throws IOException {
		File dataFile = new File("APTEL-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "APTEL");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseConsumerCourt(String bench, String state, String district, String caseType,
			String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CONSUMER COURT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "District *"), district);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseConsumerCourt(String bench, String state, String district, String caseType)
			throws IOException {
		File dataFile = new File("ConsumerCourt-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CONSUMER COURT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "District *"), district);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + state + " " + district + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//pendding
	public void tribunalsCourtByCaseConsumerCourtNCDRC(String bench, String caseType,String caseTypeValue) throws IOException {
		File dataFile = new File("ConsumerCourt-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CONSUMER COURT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(5);

			BrowserUtility.selectByValue(getSelectElementFromTab("tab1", "Case Type *"), caseTypeValue);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "" + caseTypeValue +"\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void tribunalsCourtByCaseConsumerCourtSCDRC(String bench,String state, String caseType) throws IOException {
		File dataFile = new File("ConsumerCourt-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CONSUMER COURT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + state + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void tribunalsCourtByCaseConsumerCourtDCDRC(String bench, String state, String district, String caseType)
			throws IOException {
		File dataFile = new File("ConsumerCourt-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CONSUMER COURT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "District *"), district);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + state + " " + district + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void tribunalsCourtByCaseDRT(String bench, String state, String caseType, String caseNo, String year,
			String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "DRT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseDRT(String bench, String state, String caseType) throws IOException {
		File dataFile = new File("DRT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "DRT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + state + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseCAT(String bench, String caseType, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CAT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseCAT(String bench, String caseType) throws IOException {
		File dataFile = new File("CAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CAT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseTDSAT(String bench, String caseType, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "TDSAT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseTDSAT(String bench, String caseType) throws IOException {
		File dataFile = new File("TDSAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "TDSAT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseNGT(String bench, String caseType, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NGT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseNGT(String bench, String caseType) throws IOException {
		File dataFile = new File("NGT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "NGT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseSAT(String bench, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "SAT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseSAT(String bench, String year) throws IOException {
		File dataFile = new File("SAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "SAT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + year + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseRERA(String state, String bench, String caseNo, String year, String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "RERA");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseRERA(String state, String bench) throws IOException {
		File dataFile = new File("RERA-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "RERA");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(state + " " + bench + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseCESTAT(String bench, String caseType, String caseNo, String year,
			String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CESTAT");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseCESTAT(String bench, String caseType) throws IOException {
		File dataFile = new File("CESTAT-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "CESTAT");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));
			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void tribunalsCourtByCaseElectricityCommission(String bench, String caseType, String caseNo, String year,
			String partyName) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "ELECTRICITY COMMISSION");
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Party Name"), partyName);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void tribunalsCourtByCaseElectricityCommission(String bench, String caseType) throws IOException {
		File dataFile = new File("ElectricityCommission-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Court *"), "ELECTRICITY COMMISSION");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Bench*"), bench);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type*"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));
			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, case_list_checkbox_ele);
//				BrowserUtility.click(driver, add_case_btn_ele);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
//				// ScreenShotsUtility.takeScreenshot(driver);
//				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
//				BrowserUtility.click(driver, popup_ok_ele);
//				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(bench + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	// District Courts
	// Method-----------------------------------------------------------------------

	public void DistrictCourtByCase(String state, String district, String forum, String caseType, String caseNo,
			String year) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "District *"), district);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Forum *"), forum);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.sendKeys(driver, getInputElementFromTab("tab1", "Case No"), caseNo);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void districtCourtByCase(String state, String district, String forum, String caseType) throws IOException {
		File dataFile = new File("District-court-By Case-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "District *"), district);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Forum *"), forum);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab1", "Case Type *"), caseType);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab1"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				// BrowserUtility.click(driver, case_list_checkbox_ele);
				// BrowserUtility.click(driver, add_case_btn_ele);
				// WaitUtility.waitForElementToBeVisible(driver, popup_ele);
				// ScreenShotsUtility.takeScreenshot(driver);
				// WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
				// BrowserUtility.click(driver, popup_ok_ele);
				// WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(state + " " + district + " " + " " + forum + " " + caseType + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void districtCourtByPartyname(String state, String district, String caseStatus, String year) {
		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "State *"), state);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "District *"), district);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "Case Status *"), caseStatus);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "Year *"), year);
		WaitUtility.waitForSeconds(1);

		BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab_party"));

		if (isCaseNotFoundMsgVisible()) {
			System.out.println("Case Not Found");
			ScreenShotsUtility.takeScreenshot(driver);
		} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
			WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
			BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, case_list_checkbox_ele);
			BrowserUtility.click(driver, add_case_btn_ele);
			WaitUtility.waitForElementToBeVisible(driver, popup_ele);
			// ScreenShotsUtility.takeScreenshot(driver);
			WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
			BrowserUtility.click(driver, popup_ok_ele);
			WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
			System.out.println("Case Found");
		} else {
			System.out.println("Case not Found, Invalid Data");
			ScreenShotsUtility.addScreenshotToReport(driver);
			ScreenShotsUtility.takeScreenshot(driver);
		}
	}

	public void DistrictCourtByPartyname(String state, String district, String caseStatus) throws IOException {
		File dataFile = new File("District-court-PartyName-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "State *"), state);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "District *"), district);
			WaitUtility.waitForSeconds(1);

			BrowserUtility.selectByVisibleText(getSelectElementFromTab("tab_party", "Case Status *"), "All");
			WaitUtility.waitForSeconds(1);

			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab_party"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, add_case_btn_ele);
				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
				// ScreenShotsUtility.takeScreenshot(driver);
				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
				BrowserUtility.click(driver, popup_ok_ele);
				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(state + " " + district + " " + "All" + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void DistrictCourtByCNR(String cnr) throws IOException {
		File dataFile = new File("District-court-CNR-invalid-data.txt");

		// Check if file exists, if not, create it
		if (!dataFile.exists()) {
			System.out.println("File does not exist, creating file...");
			dataFile.createNewFile();
		} else {
			System.out.println("File exists.");
		}

		// Use try-with-resources to ensure the writer is closed automatically
		try (FileWriter writer = new FileWriter(dataFile, true)) { // 'true' enables appending to the file
			BrowserUtility.sendKeys(driver, getInputElementFromTab("tab2", "CNR Number *"), cnr);
			WaitUtility.waitForSeconds(1);
			BrowserUtility.click(driver, getSearchCaseButtonFromTab("tab2"));

			if (isCaseNotFoundMsgVisible()) {
				System.out.println("Case Not Found");
				ScreenShotsUtility.takeScreenshot(driver);
			} else if (WaitUtility.waitForElementToBeVisible(driver, case_list_checkbox_ele)) {
				WaitUtility.waitForElementToBeClickable(driver, case_list_checkbox_ele);
				BrowserUtility.mouseToElement(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, case_list_checkbox_ele);
				BrowserUtility.click(driver, add_case_btn_ele);
				WaitUtility.waitForElementToBeVisible(driver, popup_ele);
				// ScreenShotsUtility.takeScreenshot(driver);
				WaitUtility.waitForElementToBeVisible(driver, popup_ok_ele);
				BrowserUtility.click(driver, popup_ok_ele);
				WaitUtility.waitForElementToBeInvisible(driver, popup_ele);
				System.out.println("Case Found");
			} else {
				System.out.println("Case not Found, Invalid Data");
				// Write invalid CNR data to the file
				writer.write(cnr + "\n");
				ScreenShotsUtility.addScreenshotToReport(driver);
				ScreenShotsUtility.takeScreenshot(driver);
			}
		} catch (IOException e) {
			// Handle potential I/O exceptions
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
			e.printStackTrace();

		}

	}

}
