package com.bulain.crawler;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Base {
    protected static final String CHROME_DRIVER = "ChromeDriver";
    protected static final String FIREFOX_DRIVER = "FirefoxDriver";
    protected static final String INTERNET_EXPLORER_DRIVER = "InternetExplorerDriver";

    protected static WebDriver driver;

    protected static String webDriver;
    protected static Long waitSeconds = 30L;

    public static void setUp() {
        if (driver != null) {
            return;
        }
        if (INTERNET_EXPLORER_DRIVER.equalsIgnoreCase(webDriver)) {
            DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
            ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            driver = new InternetExplorerDriver(ieCapabilities);
        } else if (FIREFOX_DRIVER.equalsIgnoreCase(webDriver)) {
            driver = new FirefoxDriver();
        } else if (CHROME_DRIVER.equalsIgnoreCase(webDriver)) {
            driver = new ChromeDriver();
        } else {
            HtmlUnitDriver htmlUnitDriver = new HtmlUnitDriver();
            htmlUnitDriver.setJavascriptEnabled(true);
            driver = htmlUnitDriver;
        }
        driver.manage().timeouts().implicitlyWait(waitSeconds, TimeUnit.SECONDS);
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        driver = null;
    }
    
}
