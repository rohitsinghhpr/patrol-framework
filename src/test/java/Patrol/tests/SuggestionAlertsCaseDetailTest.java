package Patrol.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.pages.SuggestionAlertsPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class SuggestionAlertsCaseDetailTest extends BaseTest2 {

	SuggestionAlertsPage suggestionAletrsPage;

	@BeforeClass()
	public void dologin() {
		LoginPage loginPage =new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();
		
        DashBoardPage dashBoardPage = new DashBoardPage(driver);
        dashBoardPage.clickOnManageCases();
        dashBoardPage.clickAlertsLink();
        
        suggestionAletrsPage = new SuggestionAlertsPage(driver);
        suggestionAletrsPage.clickOnSuggestionAlert();
	}
	
	@Test(priority = 1, enabled = false)
	public void supremeCourtCasesTest() throws InterruptedException {
		suggestionAletrsPage.clickOnTagAllPages("Tata");
		suggestionAletrsPage.clickOnSupremeCourtTab();
		suggestionAletrsPage.checkLinkAllPages();
	}
	
	@Test(priority = 2, enabled = false)
	public void highCourtCasesTest() throws InterruptedException {
		suggestionAletrsPage.clickOnTagAllPages("Tata");
		suggestionAletrsPage.clickOnHighCourtTab();
		suggestionAletrsPage.checkLinkAllPages();
	}
	
	@Test(priority = 3, enabled = false)
	public void districtCourtCasesTest() throws InterruptedException {
		suggestionAletrsPage.clickOnTagAllPages("Tata");
		suggestionAletrsPage.clickOnDistrictCourtTab();
		suggestionAletrsPage.checkLinkAllPages();
	}
	
	@Test(priority = 4, enabled = true)
	public void tribunalsCourtCasesTest() throws InterruptedException {
		suggestionAletrsPage.clickOnTagAllPages("Tata");
		suggestionAletrsPage.clickOnTribunalCourtTab();
		suggestionAletrsPage.checkLinkAllPages();
	}

}
