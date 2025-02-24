package Patrol.tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;

public class LinksTest extends BaseTest{
	@Test(priority = 1)
	public void reportlinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"Reports");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Report");
	}
	@Test(priority = 2)
	public void caeuselistlinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"My Cause List");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Causelist");
	}
	@Test(priority = 3)
	public void invoicelinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"Invoice");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Invoice");
	}
	@Test(priority = 4)
	public void ordertrackerlinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"Order Tracker");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Order Tracker");
	}
	@Test(priority = 1)
	public void notificationlinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"Notification");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Notification");
	}
	@Test(priority = 5)
	public void companylinkTest() throws IOException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		CommonUtility.clickOnLink(driver,"Active Firms");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver),"Active Firms");
	}
}
