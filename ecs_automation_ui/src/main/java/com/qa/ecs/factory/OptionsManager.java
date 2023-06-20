package com.qa.ecs.factory;

import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * This Class mainly created to manage Desired Capabilities of Browser
 * <b>Note:</b> Currently have support to ChromeOption and FirefoxOptions
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;

    /**
     * This is constructor to initialize the Properties
     *
     * @param prop Properties Class object
     */
    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    /**
     * This method is used to set the ChromeOptions
     *
     * @return this will return the ChromeOptions Object
     */
    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        co.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        co.addArguments("--no-sandbox"); // Bypass OS security model
        co.addArguments("--ignore-ssl-errors=yes");
        co.addArguments("--ignore-certificate-errors");
        co.addArguments("--remote-allow-origins=*");

        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            co.addArguments("--window-size=1920,1080");
            co.addArguments("--start-maximized");
            co.addArguments("--headless");
        }
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) co.addArguments("--incognito");
        return co;
    }

    /**
     * This method is used to set the FirefoxOptions
     *
     * @return this will return the FirefoxOptions Object
     */
    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();
        if (Boolean.parseBoolean(prop.getProperty("headless"))) fo.addArguments("--headless");
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) fo.addArguments("--incognito");
        return fo;
    }

}
