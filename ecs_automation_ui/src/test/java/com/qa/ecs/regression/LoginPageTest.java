package com.qa.ecs.regression;

import com.qa.ecs.base.BaseTest;
import com.qa.ecs.utils.AssertionUtil;

import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(description = "Verify login page link")
    public void verificationOfLoginPage_TC_001() {

        AssertionUtil.verifyEqual(loginPage.isLoginButtonDisplayed(), true,
                "Verification of Login button's link and text 'Login' is displayed successfully");
        AssertionUtil.verifyEqual(loginPage.isForgotPasswordLinkDisplayed(), true,
                "Verification of Forgot Password link and text displayed is successfully");
        AssertionUtil.verifyEqual(loginPage.isChangePasswordLinkDisplayed(), true,
                "Verification of Change Password link and text displayed is successfully");
        AssertionUtil.verifyEqual(loginPage.isNgageLogoImageDisplayed(), true,
                "Verification of Correct logo image is loaded successfully");
    }

}
