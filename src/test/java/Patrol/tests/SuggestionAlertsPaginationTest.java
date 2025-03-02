package Patrol.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.pages.SuggestionAlertsPage2;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.BrowserUtility;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.ScreenShotsUtility;
import Patrol.utilities.WaitUtility;

public class SuggestionAlertsPaginationTest extends BaseTest2 {
	SuggestionAlertsPage2 suggestionAlertPage;

	@BeforeClass()
	public void dologin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickAlertsLink();

		suggestionAlertPage = new SuggestionAlertsPage2(driver);
		suggestionAlertPage.clickOnSuggestionAlert();
		Assert.assertEquals(suggestionAlertPage.isTableVisible(), true, "Table is not visible befor clicking on tag");
		Assert.assertEquals(suggestionAlertPage.isTagFound("Tanu"), true, "Tanu tag is not found");
		suggestionAlertPage.clickOnTag("Tanu");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Suggestion Alert / Tanu", "Header Miss Match");
	}

	public void checkPagination() {
		SoftAssert softAssert = new SoftAssert();
		int lastPageNumber = suggestionAlertPage.getSecondLastPageNumber();
		for (int page = 2; page <= lastPageNumber; page++) {
			if (suggestionAlertPage.isLogoDisplayed()) {
				BrowserUtility.scrollToDown(driver);
				WaitUtility.waitForSeconds(2);
				suggestionAlertPage.clickOnPage(String.valueOf(page));
				WaitUtility.waitForSeconds(5);
			} else {
				String screenshotName = "Skip Page = " + page;
				softAssert.assertEquals(suggestionAlertPage.isLogoDisplayed(),true,screenshotName);
				ScreenShotsUtility.addScreenshotToReport(driver, screenshotName);
				suggestionAlertPage.goToPreviousPage();
				page++;
				BrowserUtility.scrollToDown(driver);
				WaitUtility.waitForSeconds(2);
				suggestionAlertPage.clickOnPage(String.valueOf(page));
				WaitUtility.waitForSeconds(5);
			}
		}
		softAssert.assertAll();
	}

	@Test(priority = 1, enabled = false)
	public void supremeCourtCasesTest() throws InterruptedException {
		suggestionAlertPage.clickOnSupremeCourtTab();
		checkPagination();
	}

	@Test(priority = 2, enabled = false)
	public void highCourtCasesTest() throws InterruptedException {
		suggestionAlertPage.clickOnHighCourtTab();
		checkPagination();
	}

	@Test(priority = 3, enabled = true)
	public void districtCourtCasesTest() throws InterruptedException {
		suggestionAlertPage.clickOnDistrictCourtTab();
		checkPagination();
	}

	@Test(priority = 4, enabled = false)
	public void tribunalsCourtCasesTest() throws InterruptedException {
		suggestionAlertPage.clickOnTribunalCourtTab();
		checkPagination();
	}
}
