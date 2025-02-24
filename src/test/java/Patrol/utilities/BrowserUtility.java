package Patrol.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class BrowserUtility {
	
	// click
	public static void click(WebDriver driver, WebElement element) {
		try {
			BrowserUtility.scrollIntoView(driver, element);
			WaitUtility.waitForElementToBeVisible(driver, element);
			WaitUtility.waitForElementToBeClickable(driver, element);
			element.click();
		} catch (Exception e) {
			System.err.println("Click Failed: " + e.getMessage());
		}
	}
	
	public static void click(WebDriver driver, WebElement element, String LogMessege) {
		try {
			BrowserUtility.scrollIntoView(driver, element);
			WaitUtility.waitForElementToBeVisible(driver, element);
			element.click();
			System.out.println(LogMessege);
		} catch (Exception e) {
			System.err.println("Click Failed: " + e.getMessage());
		}
	}
	
	// Sendkeys
	public static void sendKeys(WebDriver driver, WebElement element, String enterValue) {
		try {
			BrowserUtility.scrollIntoView(driver, element);
			WaitUtility.waitForElementToBeVisible(driver, element);
			element.clear();
			element.sendKeys(enterValue);
		} catch (Exception e) {
			System.err.println("SendKeys Failed: " + e.getMessage());
		}
	}
	
	public static void sendKeys(WebDriver driver, WebElement element, String enterValue,String LogMessege) {
		try {
			BrowserUtility.scrollIntoView(driver, element);
			WaitUtility.waitForElementToBeVisible(driver, element);
			element.clear();
			element.sendKeys(enterValue);
			System.out.println(LogMessege);
		} catch (Exception e) {
			System.err.println("SendKeys Failed: " + e.getMessage());
		}
	}

	// Select Dropdown Methods
	// ---------------------------------------------------------------------------------------

	// Selects an option in a dropdown by visible text.
	public static void selectByVisibleText(WebElement dropdownElement, String visibleText) {
		Select select = new Select(dropdownElement);
		select.selectByVisibleText(visibleText);
	}

	// Selects an option in a dropdown by value attribute
	public static void selectByValue(WebElement dropdownElement, String value) {
		Select select = new Select(dropdownElement);
		select.selectByValue(value);
	}
	
	// Selects an option in a dropdown by index
	public static void selectByIndex(WebElement dropdownElement, int index) {
        Select select = new Select(dropdownElement);
        select.selectByIndex(index);
    }
	
	// Selects an random option
	public static void selectRandomOption(WebElement dropdownElement) {
	    Select select = new Select(dropdownElement);
	    List<WebElement> options = select.getOptions();
	    if (options.size() > 1) {
	        int randomIndex = new Random().nextInt(options.size()); 
	        select.selectByIndex(randomIndex);
	        System.out.println("Selected option: " + options.get(randomIndex).getText());
	    } else {
	        System.err.println("Dropdown does not have enough options to select.");
	    }
	}
	
	// Gets the currently selected option text.
	public static String getSelectedOption(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }
	
	// retrun all options of select tag
    public static List<String> getSelectOptions(WebElement selectElement) {
        List<String> optionsText = new ArrayList<>();
        Select select = new Select(selectElement);
        // Loop through each option and add its text to the list
        for (WebElement option : select.getOptions()) {
            optionsText.add(option.getText());
        }
        return optionsText;
    }
	
	// Checks if the dropdown allows multiple selections.
	public static boolean isMultiple(WebElement dropdownElement) {
        Select select = new Select(dropdownElement);
        return select.isMultiple();
    }

	// Scroll Methods
	// ----------------------------------------------------------------------------------------

	// Scroll to top of page
	public static void scrollToTop(WebDriver driver) {
	    try {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scrollTo(0, 0);");
	    } catch (JavascriptException e) {
	        System.err.println("Failed to scroll to top: " + e.getMessage());
	    }
	}
	
	// Scroll to down of page
	public static void scrollToDown(WebDriver driver) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		} catch (JavascriptException e) {
			System.err.println("Failed to scroll vertically: " + e.getMessage());
		}
	}

	// Scroll vertically by a specific amount from 0 to given scroll amount
	public static void scrollPage(WebDriver driver, int scrollAmount) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
		} catch (JavascriptException e) {
			System.err.println("Failed to scroll vertically: " + e.getMessage());
		}
	}

	// Scroll vertically by specific amounts
	public static void scrollPage(WebDriver driver, int scrollAmountX, int scrollAmountY) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(arguments[0], arguments[1]);", scrollAmountX, scrollAmountY);
		} catch (JavascriptException e) {
			System.err.println("Failed to scroll: " + e.getMessage());
		}
	}

	// Scroll an element into view
	public static void scrollIntoView(WebDriver driver, WebElement ele) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (JavascriptException e) {
			System.err.println("Failed to scroll element into view: " + e.getMessage());
		}
	}

	// Scroll an element into view with customizable behavior
	public static void scrollIntoView(WebDriver driver, WebElement ele, boolean smoothScroll) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String scrollBehavior = smoothScroll ? "smooth" : "auto";
			js.executeScript("arguments[0].scrollIntoView({behavior: arguments[1], block: 'center'});", ele,
					scrollBehavior);
		} catch (JavascriptException e) {
			System.err.println("Failed to scroll element into view: " + e.getMessage());
		}
	}
	
	// Mouse Actions
	
	public static void mouseToElement(WebDriver driver, WebElement ele) {
		try {
			new Actions(driver).moveToElement(ele).perform();
		}catch(Exception e) {
			System.err.println("Failed to mouse to element: " + e.getMessage());
		}
	} 
	
}
