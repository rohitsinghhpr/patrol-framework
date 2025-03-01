package Patrol.tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.dataprovider.add_case_page.DistrictCourtDP;
import Patrol.pages.ActiveFirmPage;
import Patrol.pages.AddCasePage;
import Patrol.pages.CasesPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.ConfingDataProvider;

public class DCTC extends BaseTest2 {

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
		addCasePage.clickOnDistrictCourt();
	}

	@Test(priority = 1, enabled = true, dataProvider = "district_court_by_case_Andaman_and_Nicobar", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Andaman_and_Nicobar(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 2, enabled = false, dataProvider = "district_court_by_case_Andhra_Pradesh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Andhra_Pradesh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 3, enabled = false, dataProvider = "district_court_by_case_Arunachal_Pradesh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Arunachal_Pradesh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 4, enabled = false, dataProvider = "district_court_by_case_Assam", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Assam(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 5, enabled = false, dataProvider = "district_court_by_case_Bihar", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Bihar(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 6, enabled = false, dataProvider = "district_court_by_case_Chandigarh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Chandigarh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 7, enabled = false, dataProvider = "district_court_by_case_Dadra_and_Nagar_Haveli", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Dadra_and_Nagar_Haveli(String state, String district, String form,
			String caseType) throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 8, enabled = false, dataProvider = "district_court_by_case_Daman_and_Diu", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Daman_and_Diu(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 9, enabled = false, dataProvider = "district_court_by_case_Delhi", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Delhi(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 10, enabled = false, dataProvider = "district_court_by_case_Goa", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Goa(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 11, enabled = false, dataProvider = "district_court_by_case_Gujarat", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Gujarat(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 12, enabled = false, dataProvider = "district_court_by_case_Haryana", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Haryana(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 13, enabled = false, dataProvider = "district_court_by_case_Himachal_Pradesh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Himachal_Pradesh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 14, enabled = false, dataProvider = "district_court_by_case_Jammu_and_Kashmir", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Jammu_and_Kashmir(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 15, enabled = false, dataProvider = "district_court_by_case_Jharkhand", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Jharkhand(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 16, enabled = false, dataProvider = "district_court_by_case_Karnataka", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Karnataka(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 17, enabled = false, dataProvider = "district_court_by_case_Kerala", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Kerala(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 18, enabled = false, dataProvider = "district_court_by_case_Lakshadweep", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Lakshadweep(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 19, enabled = false, dataProvider = "district_court_by_case_Leh_Ladakh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Leh_Ladakh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 20, enabled = false, dataProvider = "district_court_by_case_Madhya_Pradesh_(MP)", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Madhya_Pradesh_MP(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 21, enabled = false, dataProvider = "district_court_by_case_Maharashtra", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Maharashtra(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 22, enabled = false, dataProvider = "district_court_by_case_Manipur", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Manipur(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 23, enabled = false, dataProvider = "district_court_by_case_Meghalaya", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Meghalaya(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 24, enabled = false, dataProvider = "district_court_by_case_Mizoram", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Mizoram(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 25, enabled = false, dataProvider = "district_court_by_case_Nagaland", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Nagaland(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 26, enabled = false, dataProvider = "district_court_by_case_Odisha", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Odisha(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 27, enabled = false, dataProvider = "district_court_by_case_Puducherry", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Puducherry(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 28, enabled = false, dataProvider = "district_court_by_case_Punjab", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Punjab(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 29, enabled = false, dataProvider = "district_court_by_case_Rajasthan", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Rajasthan(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 30, enabled = false, dataProvider = "district_court_by_case_Sikkim", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Sikkim(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 31, enabled = false, dataProvider = "district_court_by_case_Tamil_Nadu", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Tamil_Nadu(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 32, enabled = false, dataProvider = "district_court_by_case_Telangana", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Telangana(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 33, enabled = false, dataProvider = "district_court_by_case_Uttarakhand", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Uttarakhand(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 34, enabled = false, dataProvider = "district_court_by_case_Uttar_Pradesh", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_Uttar_Pradesh(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}

	@Test(priority = 35, enabled = false, dataProvider = "district_court_by_case_West_Bengal", dataProviderClass = DistrictCourtDP.class)
	public void district_court_by_case_West_Bengal(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Case");
		addCasePage.districtCourtByCase(state, district, form, caseType);
	}
	
	@Test(priority = 36, enabled = true, dataProvider = "district_court_party_name", dataProviderClass = DistrictCourtDP.class)
	public void district_court_party_name(String state, String district, String form, String caseType)
			throws IOException {
		addCasePage.clickOnTab("By Party Name");
		addCasePage.districtCourtByPartyname(state, district, form, caseType);
	}
}