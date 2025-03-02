
package Patrol.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.CasesDetailPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.pages.SuggestionAlertsPage2;
import Patrol.utilities.BaseTest;
import Patrol.utilities.BrowserUtility;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.WaitUtility;

public class SuggestionAlertsPageTest2 extends BaseTest {

	@Test(priority = 1, enabled = false)
	public void verifyTag() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickAlertsLink();

		SuggestionAlertsPage2 suggestionAlertPage = new SuggestionAlertsPage2(driver);
		suggestionAlertPage.clickOnSuggestionAlert();
		Assert.assertEquals(suggestionAlertPage.isTableVisible(), true, "Table is not visible befor clicking on tag");
		Assert.assertEquals(suggestionAlertPage.isTagFound("Tanu"), true, "Tanu tag is not found");
		suggestionAlertPage.clickOnTag("Tanu");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Suggestion Alert / Tanu", "Header Miss Match");
	}

	@Test(priority = 3, enabled = false)
	public void verifyCaseNumber() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickAlertsLink();

		SuggestionAlertsPage2 suggestionAlertPage = new SuggestionAlertsPage2(driver);
		suggestionAlertPage.clickOnSuggestionAlert();
		Assert.assertEquals(suggestionAlertPage.isTableVisible(), true, "Table is not visible befor clicking on tag");
		Assert.assertEquals(suggestionAlertPage.isTagFound("Tanu"), true, "Tanu tag is not found");
		suggestionAlertPage.clickOnTag("Tanu");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Suggestion Alert / Tanu", "Header Miss Match");
		while (true) {
			if (!suggestionAlertPage.isCaseNumberFound("2023/S.T./62")) {
				BrowserUtility.scrollToDown(driver);
				WaitUtility.waitForSeconds(2);
				if(!suggestionAlertPage.isNextButtonDisabled()) {
					suggestionAlertPage.clickOnNextButton();
				}else {
					break;
				}
			} else {
				break;
			}
		}
		Assert.assertEquals(suggestionAlertPage.isCaseNumberFound("2023/S.T./62"), true,
				"2023/S.T./62 case number is not found");
	}

	@Test(enabled = true)
	public void verifyAllLinksInAllPage() {
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickAlertsLink();

		SuggestionAlertsPage2 suggestionAlertPage = new SuggestionAlertsPage2(driver);
		suggestionAlertPage.clickOnSuggestionAlert();
		Assert.assertEquals(suggestionAlertPage.isTableVisible(), true, "Table is not visible befor clicking on tag");
		Assert.assertEquals(suggestionAlertPage.isTagFound("Tanu"), true, "Tanu tag is not found");
		suggestionAlertPage.clickOnTag("Tanu");
		suggestionAlertPage.clickOnHighCourtTab();
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Suggestion Alert / Tanu", "Header Miss Match");
		int page = 1;
		while (true) {
			for (int i = 0; i < suggestionAlertPage.getTableRowsCount(); i++) {
				suggestionAlertPage.clickOnLinkInRow(String.valueOf((i + 1)));
				CasesDetailPage caseDetailPage = new CasesDetailPage(driver);
				String msg = "Page => pNO, Row => rNo, CaseLink => cLink";
				if(!caseDetailPage.isCaseDetailTabVisible()) {
					msg = msg.replace("pNO",String.valueOf(page));
					msg = msg.replace("rNo",String.valueOf((i+1)));
					msg = msg.replace("cLink",driver.getCurrentUrl());
				}
				softAssert.assertEquals(caseDetailPage.isCaseDetailTabVisible(), true,msg);
				suggestionAlertPage.goToPreviousPage();
				System.out.println("row no => " + (i + 1));
			}
			BrowserUtility.scrollToDown(driver);
			WaitUtility.waitForSeconds(0.5);
			if (!suggestionAlertPage.isNextButtonDisabled()) {
				suggestionAlertPage.clickOnNextButton();
			}else {
				break;
			}
			page++;
		}
		softAssert.assertAll();
	}
	
	@Test(enabled = false)
	public void verifyPatternInAllPage() {
		SoftAssert softAssert = new SoftAssert();
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickAlertsLink();

		SuggestionAlertsPage2 suggestionAlertPage = new SuggestionAlertsPage2(driver);
		suggestionAlertPage.clickOnSuggestionAlert();
		Assert.assertEquals(suggestionAlertPage.isTableVisible(), true, "Table is not visible befor clicking on tag");
		Assert.assertEquals(suggestionAlertPage.isTagFound("Tanu"), true, "Tanu tag is not found");
		suggestionAlertPage.clickOnTag("Tanu");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Suggestion Alert / Tanu", "Header Miss Match");
		int page = 1;
		while (true) {
			System.out.println("Current Page Before Loop: " + page);
			for (int i = 0; i < suggestionAlertPage.getTableRowsCount(); i++) {
				String msg = "Page => pNO, Row => rNo, CaseNumber => cNo";
				if(!suggestionAlertPage.isPattrnFoundInRow(String.valueOf((i + 1)),"2025")) {
					msg = msg.replace("pNO",String.valueOf(page));
					msg = msg.replace("rNo",String.valueOf((i+1)));
					msg = msg.replace("cNo",String.valueOf(suggestionAlertPage.getPattrnFoundInRow(String.valueOf((i + 1)),"2025")));
				}
				softAssert.assertEquals(suggestionAlertPage.isPattrnFoundInRow(String.valueOf((i + 1)),"2025"),true,msg);
				System.out.println("row no => " + (i + 1));
			}
			BrowserUtility.scrollToDown(driver);
			WaitUtility.waitForSeconds(0.5);
			if (!suggestionAlertPage.isNextButtonDisabled()) {
				suggestionAlertPage.clickOnNextButton();
			}else {
				break;
			}
			System.out.println("Checking isNextButtonDisabled() on Page: " + page);
			System.out.println("isNextButtonDisabled(): " + suggestionAlertPage.isNextButtonDisabled());
			page++;
		}
		softAssert.assertAll();
	}

	@Test(enabled = false)
	public void verifyNumberPagination() {
//       int page = 0;
//       while(!suggestionAlertPage.isNextButtonDisabled()) {
//    	   suggestionAlertPage.clickOnNextButton();
//       }

//		int lastPageNumber = suggestionAlertPage.getSecondLastPageNumber();
//		for (int page = 2; page <= lastPageNumber; page++) {
//			boolean logo = true;
//			if (page == 5 || page == 10 || page == 14) {
//				logo = false;
//			}
//			// suggestionAlertPage.isLogoDisplayed()
//			if (logo) {
//				BrowserUtility.scrollToDown(driver);
//				WaitUtility.waitForSeconds(2);
//				suggestionAlertPage.clickOnPage(String.valueOf(page));
//				System.out.println("Page = " + page);
//				WaitUtility.waitForSeconds(5);
//			} else {
//				String shootName = "Skip Page = " + page;
//				ScreenShotsUtility.addScreenshotToReport(driver, shootName);
//				System.out.println("Skip Page = " + page);
//				suggestionAlertPage.goToPreviousPage();
//				page++;
//				BrowserUtility.scrollToDown(driver);
//				WaitUtility.waitForSeconds(2);
//				suggestionAlertPage.clickOnPage(String.valueOf(page));
//				WaitUtility.waitForSeconds(5);
//			}
//		}

	}
}
