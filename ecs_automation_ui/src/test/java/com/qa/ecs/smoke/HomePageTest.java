package com.qa.ecs.smoke;

import com.qa.ecs.base.BaseTest;
import com.qa.ecs.utils.AssertionUtil;
import com.qa.ecs.utils.Constants;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(description = "Verify Newly Created DocID, Document Title and Last Action value")
    public void SampleTest_TC_001() {

    	homePage = loginPage.doLogin();

        int oldDocId = homePage.sortDocumentListByDocID().getLatestDocID();

        int newDocId = homePage.getLatestDocID();

        // ****** Verification of Newly created DocID should be greater than Existed DocID
        AssertionUtil.verifyGreaterThan(newDocId, oldDocId,
                "Verification of Newly created DocID should be Greater than old DocID");

    }


}
