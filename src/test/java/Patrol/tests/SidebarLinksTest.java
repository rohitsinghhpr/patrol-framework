package Patrol.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.DashBoardPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.RetryAnalyzer;
import Patrol.utilities.WaitUtility;

public class SidebarLinksTest extends BaseTest2 {

	@BeforeClass()
	public void dologin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
	}
	
	@Test(priority = 0,retryAnalyzer = RetryAnalyzer.class)
	public void dashboardlinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Dashboard");
		DashBoardPage dashboard = new DashBoardPage(driver);
		Assert.assertTrue(dashboard.isGraphicalViewTabVisible(),"Graphical view Tab is not visible");
	}

	@Test(priority = 1,retryAnalyzer = RetryAnalyzer.class)
	public void reportlinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Reports");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Report","Header Miss Match");
	}

	@Test(priority = 2,retryAnalyzer = RetryAnalyzer.class)
	public void caeuselistlinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "My Cause List");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Causelist","Header Miss Match");
	}

	@Test(priority = 3,retryAnalyzer = RetryAnalyzer.class)
	public void invoicelinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Invoice");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Invoice","Header Miss Match");
	}

	@Test(priority = 4,retryAnalyzer = RetryAnalyzer.class)
	public void ordertrackerlinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Order Tracker");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Order Tracker","Header Miss Match");
	}

	@Test(priority = 5,retryAnalyzer = RetryAnalyzer.class)
	public void notificationlinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Notification");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Notification","Header Miss Match");
	}

	@Test(priority = 6,retryAnalyzer = RetryAnalyzer.class)
	public void companylinkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Company");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Active Firms","Header Miss Match");
	}
	
	@Test(priority = 7,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_cases_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Cases");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Cases","Header Miss Match");
	}
	
	@Test(priority = 8,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_matters_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Matters");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Matter","Header Miss Match");
	}
	
	@Test(priority = 9,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_calendar_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Calendar");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Calendar","Header Miss Match");
	}
	
	@Test(priority = 10,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_tasks_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Tasks");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Tasks","Header Miss Match");
	}
	
	@Test(priority = 11,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_documents_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Documents");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Document","Header Miss Match");
	}
	
	@Test(priority = 12,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_contacts_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Contacts");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Contacts","Header Miss Match");
	}
	
	@Test(priority = 13,retryAnalyzer = RetryAnalyzer.class)
	public void manageCase_alert_linkTest() throws IOException {
		CommonUtility.clickOnLink(driver, "Manage Cases");
		CommonUtility.clickOnLink(driver, "Alerts");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Appeal Alert","Header Miss Match");
	}
}
