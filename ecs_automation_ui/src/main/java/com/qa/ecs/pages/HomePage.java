package com.qa.ecs.pages;

import com.qa.ecs.utils.CommonUtil;
import com.qa.ecs.utils.ElementUtil;
import io.qameta.allure.Step;

import java.util.function.BiFunction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.ecs.commonelements.Tables;
import com.qa.ecs.listeners.ExtentReportListener;

/**
 * This Class is used to provide Object Repo and Actions related to Home Screen
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2022-09-28
 */
public class HomePage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// ****************** Locators ****************** //
	private By recentDocumentsPageIframe = By.id("iframe_103");
	private By recentDocumentLink = By.xpath("//*[@id='westContent']//*[@id='RECENT_anchor']");
	private By favoriteDocumentLink = By.id("FAVORITE");
	private By docID = By.xpath("//th[@id='tblMyDocumentsResults_DocId']");
	private By docIdData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_DocId'])[1]");
	private By docTitleData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_DocTitle'])[1]");
	private By docLastActionData = By.xpath("(//td[@aria-describedby='tblMyDocumentsResults_LastAction'])[1]");
	private By recentDocResultInfo = By.xpath("//div[@class='ui-paging-info']");
	private By lastUserLoginDateTime = By.id("lblLastLoginDate");
	private BiFunction<String, String, By> rowValueForSpecificColumn = (columnHeader, rowNumber) -> By
			.xpath("(//td[@aria-describedby='tblMyDocumentsResults_" + columnHeader + "'])[" + rowNumber + "]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
		eleUtil.switchToDefaultContent();
	}

	/**
	 * This method is used to switch to Home Screen frame
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	public HomePage switchToPageFrame() {
		eleUtil.switchToFrame(this.recentDocumentsPageIframe, 20);
		return this;
	}

	/**
	 * This method is used to click on Recent Document Link
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	@Step("Click On Recent Document Link")
	public HomePage clickRecentDocumentLink() {
		try {
			eleUtil.clickElementWhenReady(this.recentDocumentLink, 3);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \"Recent Documents\" link successfully");

		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \"Recent Documents\" link");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to get grid result
	 *
	 * @return This will return the count of grid value
	 */
	@Step("Fetching Grid Results")
	public int getGridResult() {
		eleUtil.wait(2);
		eleUtil.switchToFrame(this.recentDocumentsPageIframe, 20);
		eleUtil.waitForElementPresence(this.recentDocResultInfo, 50);
		String textInfo = eleUtil.doGetText(recentDocResultInfo);
		String resultCount = textInfo.substring(textInfo.length() - 3);
		int cnt = Integer.parseInt(resultCount.trim());
		eleUtil.switchToDefaultContent();
		return cnt;
	}

	/**
	 * This method is used to get grid result
	 *
	 * @return This will return the count of grid value
	 */
	@Step("Validate Grid Results")
	public boolean validateGridResult() {
		if (getGridResult() >= 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to click on Favourites Document Link
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	@Step("Click On Favourite Document Link")
	public HomePage clickFavouritesDocumentLink() {
		try {
			eleUtil.clickElementWhenReady(this.favoriteDocumentLink, 10);
			ExtentReportListener.test.get().log(Status.INFO, "Clicked on \"Favourites Documents\" link successfully");
		} catch (Throwable e) {
			ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on \"Favourites Documents\" link");
			Assert.fail(e.getMessage());
		}
		return this;
	}

	/**
	 * This method is used to click on DocID Column Header
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	@Step("Click On Doc ID column header")
	public HomePage clickOnDocIDColumnHeader() {
		this.switchToPageFrame();
		eleUtil.waitForElementToBeClickable(this.docID, 10);
		eleUtil.doDoubleClick(this.docID);
		eleUtil.switchToDefaultContent();
		return this;
	}

	/**
	 * This method is used to get First Available DocID value
	 *
	 * @return This will return the first available DocID value in String Format
	 */
	@Step("Get First Available DocID")
	public String getFirstAvailableDocID() {
		this.switchToPageFrame();
		String docID = eleUtil.doGetText(this.docIdData);
		eleUtil.switchToDefaultContent();
		return docID;
	}

	/**
	 * This method is used to get First Available DocType value
	 *
	 * @return This will return the first available DocType value in String Format
	 */
	@Step("Get First Available DocType")
	public String getFirstAvailableDocType() {
		eleUtil.wait(2);
		this.switchToPageFrame();
		String docType = eleUtil.doGetText(this.docTitleData);
		eleUtil.switchToDefaultContent();
		return docType;
	}

	/**
	 * This method is used to get First Available DocLastAction value
	 *
	 * @return This will return the first available DocLastAction value in String
	 *         Format
	 */
	@Step("Get First Available DocLast Action")
	public String getFirstAvailableDocLastAction() {
		eleUtil.wait(2);
		this.switchToPageFrame();
		String docLastAction = eleUtil.doGetText(this.docLastActionData);
		eleUtil.switchToDefaultContent();
		return docLastAction;
	}

	/**
	 * This method is used to get First Available DocLastAction value in Numeric
	 * Format
	 *
	 * @return This will return the first available DocLastAction value in int
	 *         Format
	 */
	public int getFirstAvailableDocIDInNumericFormat() {
		return new CommonUtil().convertStringToNumber(this.getFirstAvailableDocID());
	}

	// ************************************************//
	// ************ Complete Actions/Flows ************//
	// ************************************************//

	/**
	 * This method is used Sort DocID Column in Descending order
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	@Step("Sort Doc Id in descending order")
	public HomePage sortDocIDInDescOrder() {
		this.clickOnDocIDColumnHeader();
		return this;
	}

	/**
	 * This method is used to get Latest Doc ID by sort the DocID in Desc Order and
	 * Fetch the first value
	 *
	 * @return This will return the Latest Doc ID Value in int Format
	 */
	@Step("Get Latest Doc ID")
	public int getLatestDocID() {
		this.sortDocIDInDescOrder();
		eleUtil.wait(2);
		return this.getFirstAvailableDocIDInNumericFormat();
	}

	/**
	 * This method is used Sort DocID Column in Descending order for Recent
	 * Documents List
	 *
	 * @return This will return the Object of HomeScreen class
	 */
	@Step("Sort Document List by Doc ID")
	public HomePage sortDocumentListByDocID() {
		this.clickRecentDocumentLink();
		this.sortDocIDInDescOrder();
		return this;
	}

	/**
	 * This method is used to get First Available Doc Type Text
	 *
	 * @return This will return value in String format
	 */
	@Step("Get First Available Doc Type Value")
	public String getFirstDocTypeValue() {
		eleUtil.wait(2);
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.docTitleData);
		eleUtil.switchToDefaultContent();
		return value;
	}

	/**
	 * This method is used to get Last User Login date time
	 *
	 * @return This will return the value in String format
	 */
	public String getLastUserLoginDateTime() {
		this.switchToPageFrame();
		String value = eleUtil.doGetText(this.lastUserLoginDateTime);
		eleUtil.switchToDefaultContent();
		return value;
	}
}
