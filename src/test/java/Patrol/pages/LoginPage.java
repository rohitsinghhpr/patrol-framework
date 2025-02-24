package Patrol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.BrowserUtility;
import Patrol.utilities.WaitUtility;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='email']")
	private WebElement email;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement password;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement Login;

	@FindBy(xpath = "//div[contains(@class,'page-header')]//h1")
	private WebElement header;

	public void setEmail(String EmailID) {
		BrowserUtility.sendKeys(driver, email, EmailID, "setEmail");
	}

	public void setPassword(String usrPass) {
		BrowserUtility.sendKeys(driver, password, usrPass, "setPass");
	}

	public void performAction() {
		BrowserUtility.click(driver, Login, "ClickedOnLoginBtn");
	}

	public void login(String EmailId, String usrPass) {
		setEmail(EmailId);
		setPassword(usrPass);
		performAction();
	}

	public boolean isLoggedSuccessFull() {
		return WaitUtility.waitForElementToBeVisible(driver, header);
	}
}
