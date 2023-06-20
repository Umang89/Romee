package com.qa.ecs.pages;

import com.aventstack.extentreports.Status;
import com.qa.ecs.listeners.ExtentReportListener;
import com.qa.ecs.utils.JavaScriptUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.qa.ecs.utils.ElementUtil;

/**
 * This Class is used to provide Object Repo and Actions related to Login Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;
    private String userName;
    private String password;

    // ****************** Locators ****************** //
    private By userNameTextField = By.id("Login1_UserName");
    private By passwordTextField = By.id("Login1_Password");
    private By loginButton = By.id("Login1_LoginImageButton");
    private By changePWLink = By.id("LnkBtnChangePassword");
    private By forgotPWLink = By.id("LnkBtn");
    private By requestNewPasswordButton = By.id("btnSubmitUserInfo");
    private By logoImg = By.xpath("//*[@id='imgEPMLogo' and contains(@src,'Ngage_banner1_sav.png')]");
    private By loginBox = By.xpath("//div[@class='loginrightpanel']");
    private By backToLoginLink = By.xpath("//a[@id='lbtnBackToLogin']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }
    
    public LoginPage(WebDriver driver, String userName, String password) {
    	this.userName = userName;
    	this.password = password;
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    /**
     * This method is used to enter UserName
     *
     * @param username Username value for Login
     * @return This will return the Object of LoginPage class
     */
    public LoginPage enterUserName(String username) {
        eleUtil.doSendKeys(this.userNameTextField, username);
        return this;
    }

    /**
     * This method is used to enter Password
     *
     * @param password Password value for Login
     * @return This will return the Object of LoginPage class
     */
    public LoginPage enterPassword(String password) {
        eleUtil.doSendKeys(this.passwordTextField, password);
        return this;
    }

    /**
     * This method is used to click on Login button
     *
     * @return This will return the Object of LoginPage class
     */
    public LoginPage clickLoginButton() {
        new JavaScriptUtil(this.driver).clickElementByJS(eleUtil.getElement(this.loginButton));
        return this;
    }

    /**
     * This method is used to enter the credentials on Login Page
     *
     * @param userName Username value for Login
     * @param password Password value for Login
     * @return This will return the Object of MainPage class
     */
    public HomePage doLogin() {    	
        return this.doLogin(this.userName,this.password);
    }


    /**
     * This method is used to enter the credentials on Login Page
     *
     * @param userName Username value for Login
     * @param password Password value for Login
     * @return This will return the Object of MainPage class
     */
    public HomePage doLogin(String userName, String password) {
        this.enterUserName(userName);
        this.enterPassword(password);
        this.clickLoginButton();
        if (eleUtil.isElementExist(this.loginButton)) {
            this.enterUserName(userName);
            this.enterPassword(password);
            this.clickLoginButton();
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
            driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
        }
        return new HomePage(this.driver);
    }

    /**
     * This method is used to validate submit button has Login value
     *
     * @return This will return result in boolean format
     */
    public boolean isLoginButtonDisplayed() {
        boolean login = eleUtil.isDisplay(this.loginButton);
        String loginBtnValue = eleUtil.getAttributeValue(this.loginButton, "value");
        if (login && loginBtnValue.contentEquals("Login")) {
            ExtentReportListener.test.get().log(Status.INFO, "Login Button is displayed with correct value '" + loginBtnValue + "'");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.INFO, "Login Button is either not displayed or have unexpected value '" + loginBtnValue + "'");
            return false;
        }
    }

    /**
     * This method is used to verify Change Password link is displayed in loginPage
     *
     * @return This will return result in boolean format
     */
    public boolean isChangePasswordLinkDisplayed() {
        boolean changePassword = eleUtil.isDisplay(this.changePWLink);
        String changePasswordText = eleUtil.doGetText(this.changePWLink);
        if (changePassword && changePasswordText.contentEquals("Change Password")) {
            ExtentReportListener.test.get().log(Status.INFO, "Change Password Link is displayed with correct value '" + changePasswordText + "'");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.INFO, "Change Password Link is either not displayed or have unexpected value '" + changePasswordText + "'");
            return false;
        }
    }

    /**
     * This method is used to verify Forgot Password link is displayed in loginPage
     * <p>
     * * @return This will return result in boolean format
     */
    public boolean isForgotPasswordLinkDisplayed() {
        boolean forgotPassword = eleUtil.isDisplay(this.forgotPWLink);
        String forgotPasswordText = eleUtil.doGetText(this.forgotPWLink);
        if (forgotPassword && forgotPasswordText.contentEquals("Forgot Password?")) {
            ExtentReportListener.test.get().log(Status.INFO, "Forgot Password Link is displayed with correct value'" + forgotPasswordText + "'");
            return true;
        } else
            ExtentReportListener.test.get().log(Status.INFO, "Forgot Password Link is either not displayed or have unexpected value '" + forgotPasswordText + "'");
        return false;
    }

    /**
     * This method is used to Verify correct image logo is loaded
     * <p>
     * * @return This will return result in boolean format
     */
    public boolean isNgageLogoImageDisplayed() {
        boolean imgLogo = eleUtil.isElementExist(this.logoImg);
        String imgLogoSrcValue = eleUtil.getAttributeValue(this.logoImg, "src");
        if (imgLogo == true & imgLogoSrcValue.contains("Ngage_banner1_sav.png")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to get login box width
     * <p>
     * * @return This will return loginBoxWidth in integer format
     */
    public int getLoginBoxWidth() {
        WebElement loginBoxEle = driver.findElement(this.loginBox);
        Rectangle loginBoxRec = loginBoxEle.getRect();
        int loginBoxWidth = loginBoxRec.getWidth();
        return loginBoxWidth;
    }

    /**
     * This method is used to get login box height
     * <p>
     * * @return This will return loginBoxHeight in integer format
     */
    public int getLoginPageWindowWidth() {
        Dimension winDimension = driver.manage().window().getSize();
        int winWidth = winDimension.getWidth();
        System.out.println(winWidth);
        return winWidth;
    }

    /**
     * This method is used to verify window size become smaller after reduce window dimension
     * <p>
     * * @return This will return true or false in boolean format
     */
    public boolean isWindowWidthSmallerAfterReduceWindowDimension() {
        driver.manage().window().maximize();
        int beforeResizeWindowWidth = this.getLoginPageWindowWidth();
        this.resizeWindowInSmallerDimension();
        int afterResizeWindowWidth = this.getLoginPageWindowWidth();
        if (beforeResizeWindowWidth > afterResizeWindowWidth) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to change widow size smaller
     * <p>
     * * @return This will return true or false in boolean format
     */
    public void resizeWindowInSmallerDimension() {
        Dimension d = new Dimension(1024, 768);
        driver.manage().window().setSize(d);
    }

    /**
     * This method is used to verify login box width after resize is same
     * <p>
     * * @return This will return true or false in boolean format
     */
    public boolean isLoginBoxWidthAfterResizeBeSame() {
        int beforeResizeLoginBoxWidth = this.getLoginBoxWidth();
        this.resizeWindowInSmallerDimension();
        int afterResizeLoginBoxWidth = this.getLoginBoxWidth();
        if (beforeResizeLoginBoxWidth == afterResizeLoginBoxWidth) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to verify Back to login link is present
     * <p>
     * * @return This will return true or false in boolean format
     */
    public boolean isBackLoginLinkDisplayed() {
        String backToLogin = eleUtil.doGetText(this.backToLoginLink);
        if (backToLogin.contentEquals("Back to Login") && eleUtil.isDisplay(this.backToLoginLink)) {
            ExtentReportListener.test.get().log(Status.INFO, "Back to Login link text value is correct");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.FAIL,
                    "Back to Login link text value is not displayed correctly");
            return false;

        }
    }

    /**
     * This method is used to validate 'Request New Password' button has value="Request New Password"
     *
     * @return This will return result in boolean format
     */
    public boolean isRequestNewPasswordDisplayed() {
        boolean RequestNewPassword = eleUtil.isDisplay(this.requestNewPasswordButton);
        String RequestNewPasswordValue = eleUtil.getAttributeValue(this.requestNewPasswordButton, "value");
        if (RequestNewPassword && RequestNewPasswordValue.contentEquals("Request New Password")) {
            ExtentReportListener.test.get().log(Status.INFO, "Request New Password button is displayed with correct value '" + RequestNewPasswordValue + "'");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.INFO, "Request New Password Button is either not displayed or have unexpected value '" + RequestNewPasswordValue + "'");
            return false;
        }
    }

    /**
     * This method is used to click on Back to login link is present
     */
    public LoginPage doClickOnBackToLoginLink() {
        eleUtil.doClick(this.backToLoginLink);
        return this;
    }

    /**
     * This method is used to click on forgot password link is present
     */
    public LoginPage doClickOnForgotPasswordLink() {
        eleUtil.doClick(this.forgotPWLink);
        return this;
    }
}

