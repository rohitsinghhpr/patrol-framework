package Patrol.tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.dataprovider.add_case_page.HighCourtDP;
import Patrol.pages.ActiveFirmPage;
import Patrol.pages.AddCasePage;
import Patrol.pages.CasesPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class HCTC extends BaseTest2 {

	AddCasePage addCasePage;

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
		CasesPage casePage = new CasesPage(driver);
		addCasePage = casePage.clickAddCaseButton();
		addCasePage.clickOnHighCourt();
	}

	@Test(priority = 1, enabled = false, dataProvider = "high_court_data_By_Case", dataProviderClass = HighCourtDP.class)
	public void high_court_data_By_Case(String court, String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.highCourtByCase(court, bench, caseType);
	}

	@Test(priority = 2, enabled = false, dataProvider = "high_court_data_By_CNR_Number", dataProviderClass = HighCourtDP.class)
	public void high_court_data_By_CNR_Number(String cnr) throws IOException {
		addCasePage.clickOnTab("By CNR Number");
		addCasePage.highCourtByCNR(cnr);
	}

	@Test(priority = 3, enabled = false, dataProvider = "high_court_data_By_Party_Name", dataProviderClass = HighCourtDP.class)
	public void high_court_data_By_Party_Name(String Court, String CaseStatus, String year, String PartyName)
			throws IOException {
		addCasePage.clickOnTab("By Party Name");
		addCasePage.highCourtByPartyName(Court, CaseStatus, year, PartyName);
	}

	@Test(priority = 4, enabled = true, dataProvider = "high_court_data_By_Advocate", dataProviderClass = HighCourtDP.class)
	public void high_court_data_By_Advocate(String Court, String Advocate) throws IOException {
		addCasePage.clickOnTab("By Advocate");
		addCasePage.highCourtByAdvocate(Court, Advocate);
	}

}