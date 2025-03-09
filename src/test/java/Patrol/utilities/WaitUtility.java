package Patrol.utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;

public class WaitUtility {
	
	private static int seconds = 20;
	
	// Wait for seconds 
	public static void waitForSeconds(int seconds) {
	    try {
	    	// Convert seconds to milliseconds
	        Thread.sleep(seconds * 1000L); 
	    } catch (InterruptedException e) {
	    	e.printStackTrace();
	    }
	}
	
	public static void waitForSeconds(double seconds) {
	    try {
	        // Convert seconds to milliseconds and cast to long
	        Thread.sleep((long) (seconds * 1000));
	    } catch (InterruptedException e) {
	        Thread.currentThread().interrupt(); // Restore interrupted status
	        e.printStackTrace();
	    }
	}
	
	// 
	
	public static boolean waitForElementToBePresent(WebDriver driver,By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not presence within the timeout: " + e.getMessage());
            return false;
        }
    }

    // Wait for a WebElement to be visible
    public static boolean waitForElementToBeVisible(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not visible within the timeout: " + e.getMessage());
            return false;
        }
    } 

    public static boolean waitForElementToBeVisible(WebDriver driver, WebElement element,int seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not visible within the timeout: " + e.getMessage());
            return false;
        }
    }
    
    // Wait for a WebElement to be clickable
    public static boolean waitForElementToBeClickable(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not clickable within the timeout: " + e.getMessage());
            return false;
        }
    }

    // Wait for a WebElement to have specific text
    public static boolean waitForTextToBePresentInElement(WebDriver driver, WebElement element, String text) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Text not present in element within the timeout: " + e.getMessage());
            return false;
        }
    }

    // Wait for a WebElement to have a specific attribute value
    public static boolean waitForAttributeToBe(WebDriver driver, WebElement element, String attribute, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.attributeToBe(element, attribute, value));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Attribute '" + attribute + "' did not reach value '" + value + "' within the timeout: " + e.getMessage());
            return false;
        }
    }

    // Wait for a WebElement to be invisible
    public static boolean waitForElementToBeInvisible(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not invisible within the timeout: " + e.getMessage());
            return false;
        }
    }

    // Wait for a WebElement to be selected
    public static boolean waitForElementToBeSelected(WebDriver driver, WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeSelected(element));
            return true;
        } catch (TimeoutException e) {
            System.err.println("Element not selected within the timeout: " + e.getMessage());
            return false;
        }
    }

}
