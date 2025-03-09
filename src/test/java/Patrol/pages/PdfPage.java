package Patrol.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Patrol.utilities.WaitUtility;

public class PdfPage extends BasePage{

	public PdfPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//pdf-viewer[@id='viewer']")
	WebElement pdfViewer;
	
	@FindBy(xpath = "//embed[@type='application/pdf']")
	WebElement embed;
	
	public boolean isPdfViewerVisible() {
		return WaitUtility.waitForElementToBeVisible(driver, pdfViewer);
	}
	
	public boolean isEembedTagPresent(){
		return WaitUtility.waitForElementToBePresent(driver,By.xpath("//embed[@type='application/pdf']"));
	}
	
	public boolean isEembedTagVisible(){
		return WaitUtility.waitForElementToBeVisible(driver, embed);
	}
	
	public void closePdf() {
		driver.close();
	}

}
