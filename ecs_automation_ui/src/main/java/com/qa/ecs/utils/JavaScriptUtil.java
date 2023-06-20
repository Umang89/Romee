package com.qa.ecs.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * This Class is used to provide methods related to Javascript Executor to perform action on UI
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class JavaScriptUtil {
    private WebDriver driver;

    public JavaScriptUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * This method is used to highlight the Interacted Element
     *
     * @param element WebElement
     */
    public void flash(WebElement element) {
        String bgcolor = element.getCssValue("backgroundColor");
        for (int i = 0; i < 10; i++) {
            changeColor("rgb(0,200,0)", element);// 1
            changeColor(bgcolor, element);// 2
        }
    }


    /**
     * This method is used to change the color of the element
     *
     * @param color   Color Code in the form of rgb in String Format
     * @param element WebElement
     */
    private void changeColor(String color, WebElement element) {
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + color + "'", element);

        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
        }
    }


    /**
     * This method is used to perform click action by JavaScript Executor
     *
     * @param element WebElement
     */
    public void clickElementByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    /**
     * This method is used to get text by JavaScript Executor
     *
     * @param element WebElement
     */
    public String getTextByJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String text = js.executeScript("arguments[0].innerText;", element).toString();
        return text;
    }


    /**
     * This method is used to perform Scroll down on the page
     */
    public void scrollPageDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    /**
     * This method is used to perform Scroll up on the page
     */
    public void scrollPageUp() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    /**
     * This will scroll the page Horizontally till the element is found
     *
     * @param locator Locator in By Format
     */
    public void scrollPageHorizontal(By locator) {
        WebElement element = driver.findElement(locator);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * This method is used to perform Scroll to the element
     *
     * @param element WebElement
     */
    public void scrollIntoView(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }


    /**
     * This method is used to wait for the Page to be loaded
     */
    public void waitForPageLoaded() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String loadingStatus = js.executeScript("return document.readyState;").toString();

        if (loadingStatus.equals("complete")) {
            System.out.println("page is fully loaded");
        } else {
            for (int i = 1; i <= 20; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadingStatus = js.executeScript("return document.readyState;").toString();
                System.out.println("current page loading status: " + loadingStatus);
                if (loadingStatus.equals("complete")) {
                    break;
                }

            }
        }
    }


}
