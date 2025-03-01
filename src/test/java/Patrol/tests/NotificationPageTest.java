package Patrol.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.LoginPage;
import Patrol.pages.NotificationPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.WaitUtility;

public class NotificationPageTest extends BaseTest2 {

	@BeforeClass()
	public void dologin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
	}
	
	@Test(priority=1)
	public void verifyTabsTest1(){
		CommonUtility.clickOnLink(driver, "Notification");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Notification","Header Miss Match");
		NotificationPage notificationPage = new NotificationPage(driver);
		Assert.assertEquals(notificationPage.isTabActive("All"),true,"All Tab is not active");
	}
	
	@Test(priority=2)
	public void verifyTabsTest2(){
		CommonUtility.clickOnLink(driver, "Notification");
		WaitUtility.waitForSeconds(5);
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Notification","Header Miss Match");
		NotificationPage notificationPage = new NotificationPage(driver);
		Assert.assertEquals(notificationPage.isTabVisible("Case Detail"),true,"Case Detail Tab is not visible");
		notificationPage.clickOnTab("Case Detail");
		Assert.assertEquals(notificationPage.isTabActive("Case Detail"),true,"Case Detail Tab is not active");
	}
}
