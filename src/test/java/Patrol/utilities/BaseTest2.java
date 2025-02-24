package Patrol.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest2 {

    public WebDriver driver;
    public static ThreadLocal<WebDriver> tdriver = new ThreadLocal<WebDriver>();

    public WebDriver initializeDriver() {
        String browser = ConfingDataProvider.Browser.toLowerCase();
        boolean headless = Boolean.parseBoolean(ConfingDataProvider.Headless);

        switch (browser) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                if (headless) {
                    chromeOptions.addArguments("--headless");
                }
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) {
                    edgeOptions.addArguments("--headless");
                }
                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                throw new IllegalArgumentException("Invalid browser specified: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(ConfingDataProvider.URL);
        tdriver.set(driver);
        return BaseTest.getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return tdriver.get();
    }

    @BeforeClass
    public void launchBrowser() {
    	initializeDriver();
    }

    @AfterClass
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
