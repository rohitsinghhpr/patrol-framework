package Patrol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import Patrol.utilities.WaitUtility;

public class DashBoardPage extends BasePage {

	public DashBoardPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//div[@id='ajaxStatusDiv']//img")
	private WebElement loadder;

	@FindBy(xpath = "//span[text()='Manage Cases']")
	private WebElement manageCases;
	
	@FindBy(xpath = "//span[text()='Cases']")
	private WebElement casesLink;

	@FindBy(xpath = "//span[text()='Alerts']")
	private WebElement alertsLink;
	
	@FindBy(xpath = "//button[text()='Graphical View']")
	private WebElement graphicalViewTab;
	
	@FindBy(xpath = "//button[text()='Personal dashboard']")
	private WebElement personalDashboardTab;
	
	@FindBy(xpath = "//button[text()='Fisrm Feed']")
	private WebElement firmFeedTab;
	
	public boolean isGraphicalViewTabVisible(){
		return WaitUtility.waitForElementToBeVisible(driver, graphicalViewTab);
	}
	
	public boolean isPersonalDashboardTabVisible(){
		return WaitUtility.waitForElementToBeVisible(driver, personalDashboardTab);
	}
	
	public boolean isFirmFeedTabVisible(){
		return WaitUtility.waitForElementToBeVisible(driver, firmFeedTab);
	}

	public void clickOnManageCases() {
		WaitUtility.waitForElementToBeInvisible(driver,loadder);
		WaitUtility.waitForElementToBeClickable(driver, manageCases);
		manageCases.click();
	}

	public void clickAlertsLink() {
		WaitUtility.waitForElementToBeClickable(driver, alertsLink);
		alertsLink.click();
	}

	public void clickCasesLink() {
		WaitUtility.waitForElementToBeClickable(driver, casesLink);
		casesLink.click();
	}
}
