package Patrol.tests;

import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.dataprovider.add_case_page.SupremeCourtDP;
import Patrol.pages.ActiveFirmPage;
import Patrol.pages.AddCasePage;
import Patrol.pages.CasesPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class SCTC extends BaseTest2 {

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
		addCasePage.clickOnSupremeCourt();
	}

	@Test(priority = 1, enabled = false, dataProvider = "supreme_court_data_By_Dairy_Number", dataProviderClass = SupremeCourtDP.class)
	public void supreme_court_data_By_Dairy_Number(String dairy_no, String year) throws IOException {
		addCasePage.clickOnTab("By Dairy Number");
		addCasePage.supremeCourtByDiaryNumber(dairy_no, year);
	}

	@Test(priority = 2, enabled = false, dataProvider = "supreme_court_data_By_Case_Type", dataProviderClass = SupremeCourtDP.class)
	public void supreme_court_data_By_Case_Type(String caseType, String caseNo, String year) throws IOException {
		addCasePage.clickOnTab("By Case Type");
		addCasePage.supremeCourtByCaseType(caseType, caseNo, year);
	}

	@Test(priority = 3, enabled = false, dataProvider = "supreme_court_data_By_Party_Name", dataProviderClass = SupremeCourtDP.class)
	public void supreme_court_data_By_Party_Name(String partyName, String year) throws IOException {
		addCasePage.clickOnTab("By Party Name");
		addCasePage.supremeCourtByPartyName(partyName, year);
	}

	@Test(priority = 4, enabled = false, dataProvider = "supreme_court_data_By_Advocate_Name", dataProviderClass = SupremeCourtDP.class)
	public void supreme_court_data_By_Advocate_Name(String advocate_name) throws IOException {
		addCasePage.clickOnTab("By Advocate Name");
		addCasePage.supremeCourtByAdvocateName(advocate_name);
	}

}
