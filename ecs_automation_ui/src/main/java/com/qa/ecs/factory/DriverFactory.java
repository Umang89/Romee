package com.qa.ecs.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This Class mainly created to initiates the Driver
 * <b>Note:</b> Currently have support to Chrome and Firefox
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class DriverFactory {

    public WebDriver driver;
    public Properties prop;
    public static String highlight;
    public OptionsManager optionsManager;
    public String userName, password;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This method is used to initialize the webdriver and Currently supporting Chrome and Firefox
     *
     * @param prop Properties Class object
     * @return this will return the driver
     */
    public WebDriver init_driver(Properties prop) {
        String browserName = prop.getProperty("browser").trim();
        String environmentName = prop.getProperty("environment").trim();

        System.out.println("browser name is : " + browserName);
        highlight = prop.getProperty("highlight");
        optionsManager = new OptionsManager(prop);

        if (browserName.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
        } else if (browserName.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
        } else {
            System.out.println("please pass the right browser: " + browserName);
        }

        getDriver().manage().window().fullscreen();
        getDriver().manage().deleteAllCookies();

        URL url;
        
        try {
            if (environmentName.equalsIgnoreCase("dev")) {
                url = new URL(prop.getProperty("dev_url"));
                userName = prop.getProperty("dev_username");
                password = prop.getProperty("dev_password");
            }
            else if (environmentName.equalsIgnoreCase("qa")) {
                url = new URL(prop.getProperty("qa_url"));
                userName = prop.getProperty("qa_username");
                password = prop.getProperty("qa_password");
            }
            else {
                url = new URL(prop.getProperty("url"));
                userName = prop.getProperty("username");
                password = prop.getProperty("password");
            }

            openUrl(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return getDriver();
    }


    /**
     * This will return a Thread Local copy of the Webdriver
     *
     * @return this will return thread Local copy of Driver
     */
    public static synchronized WebDriver getDriver() {
        return tlDriver.get();
    }


    /**
     * This method is used to initialize the properties
     *
     * @return this will return properties prop reference
     */
    public Properties init_prop() {
        prop = new Properties();
        FileInputStream ip = null;
        try {
            ip = new FileInputStream("./src/test/resources/config/config.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }

    /**
     * This method takes the screenshot
     *
     * @return path where the screenshot is stored
     */

    public String getScreenshot() {
        File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(src, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    /**
     * This method will launch the url in browser
     *
     * @param url in String Format
     */
    public void openUrl(String url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Throwable e) {

        }
        getDriver().get(url);
    }


    /**
     * This method will launch the url in browser
     *
     * @param url in URL Format
     */
    public void openUrl(URL url) {
        try {
            if (url == null) {
                throw new Exception("url is null");
            }
        } catch (Throwable e) {

        }
        getDriver().navigate().to(url);
    }

}
