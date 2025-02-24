package Patrol.tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.dataprovider.add_case_page.TribunalsCourtDP;
import Patrol.pages.ActiveFirmPage;
import Patrol.pages.AddCasePage;
import Patrol.pages.CasesPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class TCTC extends BaseTest2 {

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
		addCasePage.clickOnTribunalsCourt();
	}

	@Test(priority = 1, enabled = false, dataProvider = "tribunals_court_data_NCLT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_NCLT(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseNCLT(bench, caseType);
	}

	@Test(priority = 2, enabled = false, dataProvider = "tribunals_court_data_NCLAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_NCLAT(String state, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseNCLAT(state, caseType);
	}

	@Test(priority = 3, enabled = false, dataProvider = "tribunals_court_data_ITAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_ITAT(String state, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseITAT(state, caseType);
	}

	@Test(priority = 4, enabled = false, dataProvider = "tribunals_court_data_APTET", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_APTET(String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseAPTEL(caseType);
	}

	//pendding
	@Test(priority = 5, enabled = true, dataProvider = "tribunals_court_data_CONSUMMER_COURT_NCDRC", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_CONSUMMER_COURT_NCDRC(String bench,String caseType,String caseTypeValue)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseConsumerCourtNCDRC(bench, caseType,caseTypeValue);
	}

	@Test(priority = 6, enabled = false, dataProvider = "tribunals_court_data_CONSUMMER_COURT_SCDRC", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_CONSUMMER_COURT_SCDRC(String bench, String state, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseConsumerCourtSCDRC(bench, state, caseType);
	}

	@Test(priority = 7, enabled = false, dataProvider = "tribunals_court_data_CONSUMMER_COURT_DCDRC", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_CONSUMMER_COURT_DCDRC(String bench, String state, String district, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseConsumerCourt(bench, state, district, caseType);
	}

	@Test(priority = 8, enabled = false, dataProvider = "tribunals_court_data_DRT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_DRT(String bench, String state, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseDRT(bench, state, caseType);
	}

	@Test(priority = 9, enabled = false, dataProvider = "tribunals_court_data_CAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_CAT(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseCAT(bench, caseType);
	}

	@Test(priority = 10, enabled = false, dataProvider = "tribunals_court_data_TDSAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_TDSAT(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseTDSAT(bench, caseType);
	}

	@Test(priority = 11, enabled = false, dataProvider = "tribunals_court_data_NGT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_NGT(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseNGT(bench, caseType);
	}

	@Test(priority = 12, enabled = false, dataProvider = "tribunals_court_data_SAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_SAT(String bench, String year) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseSAT(bench, year);
	}

	@Test(priority = 13, enabled = false, dataProvider = "tribunals_court_data_RERA", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_RERA(String state, String bench) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseRERA(state, bench);
	}

	@Test(priority = 14, enabled = false, dataProvider = "tribunals_court_data_CESTAT", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_CESTAT(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseCESTAT(bench, caseType);
	}

	@Test(priority = 15, enabled = false, dataProvider = "tribunals_court_data_ELECTRICITY_COMMISSION", dataProviderClass = TribunalsCourtDP.class)
	public void tribunals_court_data_ELECTRICITY_COMMISSION(String bench, String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.tribunalsCourtByCaseElectricityCommission(bench, caseType);
	}

}
