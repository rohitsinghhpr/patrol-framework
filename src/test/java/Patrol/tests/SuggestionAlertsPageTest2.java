
package Patrol.tests;

import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.pages.SuggestionAlertsPage2;
import Patrol.utilities.BaseTest;
import Patrol.utilities.BrowserUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.WaitUtility;

public class SuggestionAlertsPageTest2 extends BaseTest {
	@Test
	public void verifyPagination() {
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

		suggestionAlertPage.clickOnTag("Tanu");
		
//       int page = 0;
//       while(!suggestionAlertPage.isNextButtonDisabled()) {
//    	   suggestionAlertPage.clickOnNextButton();
//       }

		int lastPageNumber = suggestionAlertPage.getSecondLastPageNumber();
		for (int page = 2; page <= lastPageNumber; page++) {
			boolean logo = true;
			if (page == 5) {
				logo = false;
			}
			// suggestionAlertPage.isLogoDisplayed()
			if (logo) {
				BrowserUtility.scrollToDown(driver);
				WaitUtility.waitForSeconds(2);
				suggestionAlertPage.clickOnPage(String.valueOf(page));
				System.out.println("Page = " + page);
				WaitUtility.waitForSeconds(5);
			} else {
				System.out.println("Skip Page = " + page);
				suggestionAlertPage.goToPreviousPage();
				page++;
				BrowserUtility.scrollToDown(driver);
				WaitUtility.waitForSeconds(2);
				suggestionAlertPage.clickOnPage(String.valueOf(page));
				WaitUtility.waitForSeconds(5);
			}
		}

	}
}
