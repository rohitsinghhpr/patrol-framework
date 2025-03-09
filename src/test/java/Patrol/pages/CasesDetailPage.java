package Patrol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.WaitUtility;

public class CasesDetailPage extends BasePage{
 
	public CasesDetailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//a[normalize-space()='Cases Detail']")
	WebElement caseDetailTab;
	
	public boolean isCaseDetailTabVisible() {
		return WaitUtility.waitForElementToBeVisible(driver,caseDetailTab,10);
	}
	
}
