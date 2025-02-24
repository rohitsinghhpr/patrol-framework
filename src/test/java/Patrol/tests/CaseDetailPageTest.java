package Patrol.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.CasesPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class CaseDetailPageTest extends BaseTest2 {

	CasesPage casePage;
	
	@BeforeClass()
	public void dologin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");

		DashBoardPage dashBoardPage = new DashBoardPage(driver);
		dashBoardPage.clickOnManageCases();
		dashBoardPage.clickCasesLink();
		casePage = new CasesPage(driver);
	}
	
	@Test(priority = 1, enabled = false)
	public void allCasesTest() {
		casePage.checkLinkAllPages();
	}
	
	@Test(priority = 2, enabled = false)
	public void supremeCourtCasesTest() {
		casePage.clickOnFilter();
		casePage.clickOnSupremeCourt();
		casePage.checkLinkAllPages();
	}
	
	@Test(priority = 3, enabled = false)
	public void highCourtCasesTest() {
		casePage.clickOnFilter();
		casePage.clickOnHighCourt();
		casePage.checkLinkAllPages();
	}
	
	@Test(priority = 4, enabled = false)
	public void districtCourtCasesTest() {
		casePage.clickOnFilter();
		casePage.clickOnDistrictCourt();
		casePage.checkLinkAllPages();
	}
	
	@Test(priority = 5, enabled = false)
	public void tribunalsCourtCasesTest() {
		casePage.clickOnFilter();
		casePage.clickOnTribunalsCourt();
		casePage.checkLinkAllPages();
	}
}
