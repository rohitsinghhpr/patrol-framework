package Patrol.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.LoginPage;
import Patrol.pages.MatterPage;
import Patrol.utilities.BaseTest2;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.RetryAnalyzer;
import Patrol.utilities.WaitUtility;

public class MatterPageTest extends BaseTest2 {

	@BeforeClass()
	public void dologin() {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();
		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnCompany("Legitquest");
		WaitUtility.waitForSeconds(2);
		CommonUtility.clickOnLink(driver, "Manage Cases");
		WaitUtility.waitForSeconds(0.5);
		CommonUtility.clickOnLink(driver, "Matters");
		WaitUtility.waitForSeconds(5);
	}
	
	@Test(priority=1,retryAnalyzer = RetryAnalyzer.class)
	public void verifyAllTabTest(){
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Matter","Header Miss Match");
		MatterPage matterPage = new MatterPage(driver);
		Assert.assertEquals(matterPage.isTabActive("ALL"),true,"All Tab is not active");
	}
	
	@Test(priority=2,retryAnalyzer = RetryAnalyzer.class)
	public void verifyOpenTabTest(){
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Matter","Header Miss Match");
		MatterPage matterPage = new MatterPage(driver);
		matterPage.clickOnTab("Open");
		Assert.assertEquals(matterPage.isTabActive("Open"),false,"Open Tab is not active");
	}
	
	@Test(priority=3,retryAnalyzer = RetryAnalyzer.class)
	public void verifyPendingTabTest(){
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Matter","Header Miss Match");
		MatterPage matterPage = new MatterPage(driver);
		matterPage.clickOnTab("Pending");
		Assert.assertEquals(matterPage.isTabActive("Pending"),true,"Pending Tab is not active");
	}

	@Test(priority=4,retryAnalyzer = RetryAnalyzer.class)
	public void verifyClosedTabTest(){
		Assert.assertTrue(CommonUtility.isPageHeaderVisible(driver),"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Matter","Header Miss Match");
		MatterPage matterPage = new MatterPage(driver);
		matterPage.clickOnTab("Closed");
		Assert.assertEquals(matterPage.isTabActive("Closed"),true,"Closed Tab is not active");
	}

}
