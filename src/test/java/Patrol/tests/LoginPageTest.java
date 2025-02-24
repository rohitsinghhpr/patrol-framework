package Patrol.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import Patrol.dataprovider.add_case_page.LoginPageDataProvider;
import Patrol.pages.LoginPage;
import Patrol.utilities.BaseTest;

public class LoginPageTest extends BaseTest{
	@Test(priority = 1, enabled = true, dataProvider = "loginTestData", dataProviderClass = LoginPageDataProvider.class)
	public void verifyLoginPage(String email,String password,boolean expected) throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.setEmail(email);
		loginPage.setPassword(password);
		loginPage.performAction();
		Assert.assertEquals(loginPage.isLoggedSuccessFull(), expected,"Login was NOT successful!");
	}
}
