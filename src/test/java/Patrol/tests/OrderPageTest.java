package Patrol.tests;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Patrol.pages.ActiveFirmPage;
import Patrol.pages.LoginPage;
import Patrol.pages.OrderPage;
import Patrol.utilities.BaseTest;
import Patrol.utilities.BrowserUtility;
import Patrol.utilities.CommonUtility;
import Patrol.utilities.ConfingDataProvider;
import Patrol.utilities.WaitUtility;

public class OrderPageTest extends BaseTest{
			
	@Test(enabled = false)
	public void verifyViewOrder(){
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();
		
		CommonUtility.clickOnLink(driver,"Order Tracker");
		Assert.assertEquals(CommonUtility.isPageHeaderVisible(driver),true,"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Order Tracker");
		OrderPage orderPage = new OrderPage(driver);
		String tabHandle = orderPage.getMainTab();
		int rowCount = orderPage.getTableRowsCount();
		//SoftAssert softAssert = new  SoftAssert();
		for(int i=1;i<rowCount;i++) {
			orderPage.clickOnViewOrder(String.valueOf(i));
			driver.close();
			orderPage.goToMainTab(tabHandle);
		}
	}
	
	@Test(enabled = false)
	public void TC002() throws IOException, InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();
		
		CommonUtility.clickOnLink(driver,"Order Tracker");
		Assert.assertEquals(CommonUtility.isPageHeaderVisible(driver),true,"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Order Tracker");
		OrderPage orderPage = new OrderPage(driver);
		int rowCount = orderPage.getTableRowsCount();
		SoftAssert softAssert = new  SoftAssert();
		for(int i=1;i<rowCount;i++) {
			WebElement link = orderPage.getViewOrderElement(String.valueOf(i));
			softAssert.assertEquals(CommonUtility.isLinkStatus200(link),true,"Links status found 400 not found"+i);
		}
	}

	@Test(enabled = true)
	public void TC003() throws IOException, InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(ConfingDataProvider.Email);
		loginPage.setPassword(ConfingDataProvider.Password);
		loginPage.performAction();

		ActiveFirmPage activeFirmpage = new ActiveFirmPage(driver);
		activeFirmpage.clickOnLegitquest();
		
		CommonUtility.clickOnLink(driver,"Order Tracker");
		Assert.assertEquals(CommonUtility.isPageHeaderVisible(driver),true,"Header is not visible");
		Assert.assertEquals(CommonUtility.verifyPageHeader(driver), "Order Tracker");
		OrderPage orderPage = new OrderPage(driver);
		SoftAssert softAssert = new  SoftAssert();
		int page = 1;
		while (true) {
			for(int i=1;i<orderPage.getTableRowsCount();i++) {
				WebElement link = orderPage.getViewOrderElement(String.valueOf(i));
				String msg = "Page => pNO, Row => rNo, ViewOrderLink => voLink";
				if(!CommonUtility.isLinkStatus200(link)) {
					msg = msg.replace("pNO",String.valueOf(page));
					msg = msg.replace("rNo",String.valueOf((i+1)));
					msg = msg.replace("voLink",orderPage.getViewOrderElementLink(String.valueOf(i)));
				}
				softAssert.assertEquals(CommonUtility.isLinkStatus200(link),true,msg);
			}
			BrowserUtility.scrollToDown(driver);
			WaitUtility.waitForSeconds(0.5);
			BrowserUtility.mouseToElement(driver, orderPage.getNextPageButton());
			if (!orderPage.isNextButtonDisabled()) {
				orderPage.clickOnNextButton();
			}else {
				break;
			}
			page++;
		}
		softAssert.assertAll();		
	}
}
