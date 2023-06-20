package com.qa.ecs.commonelements;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.apache.commons.lang3.function.TriFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.aventstack.extentreports.Status;
import com.qa.ecs.listeners.ExtentReportListener;
import com.qa.ecs.utils.ElementUtil;

/**
 * This Class is used to provide Generic Methods related to Common Application Element
 *
 * @author Nahian Omar Faruqe
 * @version 1.0
 * @since 2023-06-20
 */

public class Tables {

    private WebDriver driver;
    private ElementUtil eleUtil;

    private By allDocIDColumnValue = By.xpath("//*[contains(@aria-describedby,'_Doc ID')]");
    private Function<String, By> paginationInfoLabel = (index) -> By.xpath("(//*[@class='ui-paging-info'])[" + index + "]");
    private Function<String, By> allColumnValue = (columnHeaderName) -> By.xpath("//*[contains(@aria-describedby,'_" + columnHeaderName + "')]");
    private By paginationInfoLabelForSavedSearchTable = By.xpath("//td[contains(@id,'Results_toppager_right')]//*[@class='ui-paging-info']");
    private Function<String, By> locatorOfColumnHeadBar = (columnHeaderName) -> By
            .xpath("//th//*[text()='" + columnHeaderName + "']");
    private By columnHead = By.xpath("//*[contains(@aria-labelledby,'SearchResultsTable')]//thead//th");
    private Function<String, By> columnHeader = (index) -> By.xpath("//*[contains(@aria-labelledby,'SearchResultsTable')]//thead//th[" + index + "]");
    private By setLayoutIcon = By.xpath("//*[contains(@id,'SearchResultsTable_btnSetLayout')]");
    private By resetLayoutIcon = By.xpath("//*[contains(@id,'SearchResultsTable_btnResetLayout')]");
    private By exportToExcelIcon = By.xpath("//*[contains(@id,'SearchResultsTable_btnExport')]");
    private By refreshIcon = By.xpath("//*[contains(@id,'SearchResultsTable_btnRefresh')]");
    private BiFunction<String, String, By> rowValueForSpecificColumn = (columnHeader, rowNumber) -> By.xpath("(//td[contains(@aria-describedby,'SearchResults') and contains(@aria-describedby,'" + columnHeader + "')])[" + rowNumber + "]");
    private TriFunction<String, String, String, By> rowValueForSpecificColumnAndTable = (tableName, columnHeader, rowNumber) -> By.xpath("//table[contains(@id,'" + tableName + "')]//td[contains(@aria-describedby,'" + columnHeader + "')][" + rowNumber + "]");
    private TriFunction<String, String, String, By> rowValueForSpecificColumnIndexAndTable = (tableName, columnIndex, rowNumber) -> By.xpath("//table[@id='" + tableName + "']//tbody//tr[@id][" + rowNumber + "]//td[" + columnIndex + "]");
    private TriFunction<String, String, String, By> rowValueForSpecificColumnIndexAndTableWithAriaLabelledByAttribute = (tableName, columnIndex, rowNumber) -> By.xpath("//table[@aria-labelledby='" + tableName + "']//tbody//tr[@id][" + rowNumber + "]//td[" + columnIndex + "]");
    private BiFunction<String, String, By> locatorOfSortIcon = (columnHeaderName, sortType) -> By
            .xpath("//th//*[text()= '" + columnHeaderName + "']//child::span//span[@sort='" + sortType + "']");
    private Function<Integer, By> checkBox = (index) -> By.xpath("(//table[contains(@id,'SearchResultsTable')]//input)[" + index + "]");

    private Function<String, By> tableNavigationFirstButton = (tableIndex) ->
            By.xpath("(//td[contaNameins(@id,'first_t_') and contains(@id,'toppager')])[" + tableIndex + "]");
    private Function<String, By> tableNavigationPreviousButton = (tableIndex) ->
            By.xpath("(//td[contains(@id,'prev_t_') and contains(@id,'toppager')])[" + tableIndex + "]");
    private Function<String, By> tableNavigationNextButton = (tableIndex) ->
            By.xpath("(//td[contains(@id,'next_t_') and contains(@id,'toppager')])[" + tableIndex + "]");
    private Function<String, By> tableNavigationLastButton = (tableIndex) ->
            By.xpath("(//td[contains(@id,'last_t_') and contains(@id,'toppager')])[" + tableIndex + "]");
    private Function<String, By> currentPageTextField = (tableIndex) ->
            By.xpath("(//td[@dir='ltr']/input)[" + tableIndex + "]");
    private Function<String, By> totalPage = (tableIndex) ->
            By.xpath("(//td[@dir='ltr']/span)[" + tableIndex + "]");

    private Function<String, By> table = (tableId) ->
            By.xpath("//table[@id='" + tableId + "']");


    private Function<String, By> tableWithAriaLabelledByAttribute = (ariaLabelledByValue) ->
            By.xpath("//table[@aria-labelledby='" + ariaLabelledByValue + "']");


    private Function<String, By> tableHeaderBar = (headerBar) ->
            By.xpath("//h3//*[contains(text(),'" + headerBar + "')]");


    private static int sourceIndex = 0;
    private static int targetIndex = 0;

    public Tables(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(this.driver);
    }

    /**
     * This method is used to click on column head if not visible the column head
     * it will scroll the page Horizontally till the column head of result table found
     */
    public void clickOnColumnHeader(String columnHeadName) {
        eleUtil.waitForElementPresence(this.locatorOfColumnHeadBar.apply(columnHeadName), 20);
        eleUtil.scrollToElementByActionsClass(this.locatorOfColumnHeadBar.apply(columnHeadName));
        eleUtil.doClick(this.locatorOfColumnHeadBar.apply(columnHeadName));
        eleUtil.wait(2);
    }

    /**
     * This method is used to Sort Column in Descending order
     *
     * @param columnHeadName in String format
     */
    public void sortColumnInDscOrder(String columnHeaderName) {
        this.sortColumn(columnHeaderName, false);
    }

    /**
     * This method is used to Sort Column in Ascending order
     *
     * @param columnHeadName in String format
     */
    public void sortColumnInAscOrder(String columnHeaderName) {
        this.sortColumn(columnHeaderName, true);
    }

    /**
     * This method is used to Sort Column
     *
     * @param columnHeadName           in String format
     * @param ascendingSortingExpected is in boolean format -true or false
     */
    public void sortColumn(String columnHeaderName, boolean ascendingSortingExpected) {
        String sortType = null;
        int counter = 0;

        if (ascendingSortingExpected)
            sortType = "asc";
        else
            sortType = "desc";

        eleUtil.waitForElementPresence(this.locatorOfSortIcon.apply(columnHeaderName, sortType), 60);

        String getClassAttributeOfColumnHeaderSortIcon = null;

        do {
            counter++;
            getClassAttributeOfColumnHeaderSortIcon = eleUtil
                    .getAttributeValue(this.locatorOfSortIcon.apply(columnHeaderName, sortType), "class");
            if (getClassAttributeOfColumnHeaderSortIcon.contains("state-disabled")) {
                eleUtil.scrollToElementByActionsClass(this.locatorOfColumnHeadBar.apply(columnHeaderName));
                eleUtil.doClick(this.locatorOfColumnHeadBar.apply(columnHeaderName));
                eleUtil.wait(2);
            }
        } while (counter < 3);

    }


    /**
     * This method is used to get all doc Id from Result Table
     *
     * @param toolTipText is in String format
     */
    public List<String> getAllDocIDColumnValue() {
        List<String> docIDValues = new LinkedList<>();
        try {
            eleUtil.waitForElementPresence(this.allDocIDColumnValue, 120);
            docIDValues = eleUtil.getAllOptionsOfElement(this.allDocIDColumnValue);
            ExtentReportListener.test.get().log(Status.INFO, "Clicking on DocID Column Header Successfully");
            return docIDValues;
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while clicking on DocID Column Header");
            Assert.fail(e.getMessage());
        }
        return null;
    }


    /**
     * This method is used to get all values in a List for a specific column
     *
     * @return This will return the List of a column values
     */
    public List<String> getAllValuesForColumn(String columnHeader) {
        List<String> columnValues = new LinkedList<>();
        try {
            String[] allValues = eleUtil.getAttributeValue(this.locatorOfColumnHeadBar.apply(columnHeader), "id").split("_");
            String ariaDescribedByValue = allValues[allValues.length - 1];
            eleUtil.waitForElementPresence(this.allColumnValue.apply(ariaDescribedByValue), 120);
            columnValues = eleUtil.getAllOptionsOfElement(this.allColumnValue.apply(ariaDescribedByValue));
            ExtentReportListener.test.get().log(Status.INFO, "Fetching all values for Column Header " + columnHeader + " Successfully");
            return columnValues;
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching all values for Column Header " + columnHeader);
            Assert.fail(e.getMessage());
        }
        return null;
    }


    /**
     * This method is used update Table Layout
     *
     * @param sourceHeader is in String format
     * @param targetHeader is in String format
     */
    public void updateTableLayout(String sourceHeader, String targetHeader) {
        eleUtil.waitForElementPresence(this.columnHead, 60);
        sourceIndex = eleUtil.getElementIndex(this.columnHead, sourceHeader);
        eleUtil.wait(1);
        targetIndex = eleUtil.getElementIndex(this.columnHead, targetHeader);
        By source = columnHeader.apply(String.valueOf(sourceIndex));
        By target = columnHeader.apply(String.valueOf(targetIndex));

        eleUtil.dragAndDrop(source, target);

        eleUtil.wait(2);
        ExtentReportListener.test.get().log(Status.INFO,
                sourceHeader + " updated to " + targetHeader + " column position successfully");
    }

    /**
     * This method is used to verify Updated Table Layout
     *
     * @param expectedSourceHeader is in String format
     * @param expectedTargetHeader is in String format
     * @return This will return true of false
     */
    public boolean verifyUpdatedTableLayout(String expectedSourceHeader, String expectedTargetHeader) {
        eleUtil.wait(2);
        int updatedSourceColumnIndex = eleUtil.getElementIndex(this.columnHead, expectedSourceHeader);
        int updatedTargetColumnIndex = eleUtil.getElementIndex(this.columnHead, expectedTargetHeader);

        if (updatedSourceColumnIndex == targetIndex && updatedTargetColumnIndex == targetIndex - 1) {
            ExtentReportListener.test.get().log(Status.INFO, "Verification of columns are switched successfully");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.FAIL, "Verification of columns are not switched successfully");
            return false;
        }
    }

    /**
     * This method is used to verify Default Table Layout
     *
     * @param expectedSourceHeader is in String format
     * @param expectedTargetHeader is in String format
     * @return This will return true of false
     */
    public boolean verifyDefaultTableLayout(String expectedSourceHeader, String expectedTargetHeader) {
        eleUtil.wait(2);
        int updatedSourceColumnIndex = eleUtil.getElementIndex(this.columnHead, expectedSourceHeader);
        int updatedTargetColumnIndex = eleUtil.getElementIndex(this.columnHead, expectedTargetHeader);

        if (updatedSourceColumnIndex == sourceIndex && updatedTargetColumnIndex == targetIndex) {
            ExtentReportListener.test.get().log(Status.INFO, "Verification of columns are switched successfully");
            return true;
        } else {
            ExtentReportListener.test.get().log(Status.FAIL, "Verification of columns are not switched successfully");
            return false;
        }
    }

    /**
     * This method is used to click on Set layout Icon
     */
    public void clickOnSetLayoutIcon() {
        eleUtil.waitForElementPresence(this.setLayoutIcon, 60);
        eleUtil.doClick(this.setLayoutIcon);
        eleUtil.wait(1);

    }

    /**
     * This method is used to click on Reset layout Icon
     */
    public void clickOnResetLayoutIcon() {
        eleUtil.waitForElementPresence(this.resetLayoutIcon, 60);
        eleUtil.doClick(this.resetLayoutIcon);
        eleUtil.wait(1);

    }

    /**
     * This method is used to click on Export To Excel Icon
     */
    public void clickOnExportToExcelIcon() {
        eleUtil.waitForElementPresence(this.exportToExcelIcon, 60);
        eleUtil.doClick(this.exportToExcelIcon);
        eleUtil.wait(1);

    }

    /**
     * This method is used to click on Refresh Icon
     */
    public void clickOnRefreshIcon() {
        eleUtil.waitForElementPresence(this.refreshIcon, 60);
        eleUtil.doClick(this.refreshIcon);
        eleUtil.wait(1);

    }

    /**
     * This method is used to get Value from Search Result Table
     *
     * @param columnHeaderName is in String format
     * @param rowIndex         is in integer format
     * @return This will return value in String Format
     */
    public String getValueFromSearchResultTable(String columnHeaderName, int rowIndex) {
        String[] allValues = eleUtil.getAttributeValue(this.locatorOfColumnHeadBar.apply(columnHeaderName), "id").split("_");
        String ariaDescribedByValue = allValues[allValues.length - 1];
        eleUtil.scrollToElementByActionsClass(this.rowValueForSpecificColumn.apply(ariaDescribedByValue, Integer.toString(rowIndex)));
        String value = eleUtil.doGetText(this.rowValueForSpecificColumn.apply(ariaDescribedByValue, Integer.toString(rowIndex)));
        return value;
    }

    /**
     * This method is used to get Value from Table Identified by ID Attribute
     *
     * @param tableID          is in String format
     * @param columnHeaderName is in String format
     * @param rowIndex         is in integer format
     * @return This will return value in String Format
     */
    public String getValueFromTable(String tableID, String columnHeaderName, int rowIndex) {
        String columnIndex = eleUtil.getTableColumnHeaderIndex(this.table.apply(tableID), columnHeaderName);
        String value = eleUtil.doGetText(this.rowValueForSpecificColumnIndexAndTable.apply(tableID, columnIndex, Integer.toString(rowIndex)));
        return value;
    }


    /**
     * This method is used to get Value from Table Identified by Aria-Labelled Attribute
     *
     * @param tableAriaLabelledAttribute is in String format
     * @param columnHeaderName           is in String format
     * @param rowIndex                   is in integer format
     * @return This will return value in String Format
     */
    public String getValueFromTableIdentifiedByAriaLabelledAttribute(String tableAriaLabelledAttribute, String columnHeaderName, int rowIndex) {
        String columnIndex = eleUtil.getTableColumnHeaderIndex(this.tableWithAriaLabelledByAttribute.apply(tableAriaLabelledAttribute), columnHeaderName);
        String value = eleUtil.doGetText(this.rowValueForSpecificColumnIndexAndTableWithAriaLabelledByAttribute.apply(tableAriaLabelledAttribute, columnIndex, Integer.toString(rowIndex)));
        return value;
    }

    /**
     * This method is used to get the Pagination Info
     *
     * @return This will return the Pagination Info in String Format
     */
    public String getPaginationInfo(String index) {
        String paginationInfo = null;
        try {
            eleUtil.waitForElementPresence(this.paginationInfoLabel.apply(index), 40);
            paginationInfo = eleUtil.doGetText(this.paginationInfoLabel.apply(index));
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching pagination Info \'" + paginationInfo + "\' successfully");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching pagination Info");
            Assert.fail(e.getMessage());
        }
        return paginationInfo;
    }

    /**
     * This method is used to get the Pagination Info
     *
     * @return This will return the Pagination Info in String Format
     */
    public String getPaginationInfo() {
        return this.getPaginationInfo("1");
    }

    /**
     * This method is used to get Total Record Count From Table Pagination Info
     *
     * @return This will return intTotalActivities in Integer format
     */
    public int getTotalRecordCountFromTablePaginationInfo() {
        return this.getTotalRecordCountFromTablePaginationInfo("1");
    }

    /**
     * This method is used to get Total Record Count From Table Pagination Info
     *
     * @return This will return the Pagination Info in String Format
     */
    public int getTotalRecordCountFromTablePaginationInfo(String tableIndex) {
        String pageInfo = this.getPaginationInfo(tableIndex);
        String pageInfoData[] = pageInfo.split("of");
        String totalRecordCount = pageInfoData[1].replace(",", "").trim();
        int intTotalRecordCount = Integer.valueOf(totalRecordCount);
        return intTotalRecordCount;
    }

    public int getPageRecordCountFromTablePaginationInfo() {
        return this.getPageRecordCountFromTablePaginationInfo("1");
    }

    public int getPageRecordCountFromTablePaginationInfo(String tableIndex) {
        String pageInfo = this.getPaginationInfo(tableIndex);
        String pageInfoData[] = pageInfo.split("of");
        String totalPageRecordCount = pageInfoData[0].split("-")[1].trim();
        int intTotalPageRecordCount = Integer.valueOf(totalPageRecordCount);
        return intTotalPageRecordCount;
    }


    public int getInitialPageRecordCountFromTablePaginationInfo() {
        return this.getInitialPageRecordCountFromTablePaginationInfo("1");
    }

    public int getInitialPageRecordCountFromTablePaginationInfo(String tableIndex) {
        String pageInfo = this.getPaginationInfo(tableIndex);
        String pageInfoData[] = pageInfo.split("of");
        String initialPageRecordCount = pageInfoData[0].split("-")[0].replace("Showing", "").trim();
        int intInitialPageRecordCount = Integer.valueOf(initialPageRecordCount);
        return intInitialPageRecordCount;
    }

    /**
     * This method is used to click on check box in result table
     *
     * @param rowNumber in Integer format
     */
    public void clickOnCheckBoxInResultTable(int rowNumber) {
        eleUtil.waitForElementPresence(this.checkBox.apply(rowNumber), 60);
        eleUtil.doClick(this.checkBox.apply(rowNumber));
        eleUtil.wait(1);
    }


    public void clickOnFirstNavigationButton(String tableIndex) {
        eleUtil.waitForElementPresence(this.tableNavigationFirstButton.apply(tableIndex), 60);
        eleUtil.doClick(this.tableNavigationFirstButton.apply(tableIndex));
    }

    public void clickOnPreviousNavigationButton(String tableIndex) {
        eleUtil.waitForElementPresence(this.tableNavigationPreviousButton.apply(tableIndex), 60);
        eleUtil.doClick(this.tableNavigationPreviousButton.apply(tableIndex));
    }

    public void clickOnNextNavigationButton(String tableIndex) {
        eleUtil.waitForElementPresence(this.tableNavigationNextButton.apply(tableIndex), 60);
        eleUtil.doClick(this.tableNavigationNextButton.apply(tableIndex));
    }

    public void clickOnLastNavigationButton(String tableIndex) {
        eleUtil.waitForElementPresence(this.tableNavigationLastButton.apply(tableIndex), 60);
        eleUtil.doClick(this.tableNavigationLastButton.apply(tableIndex));
    }

    public void enterCurrentPage(String pageNumber, String tableIndex) {
        eleUtil.waitForElementPresence(this.currentPageTextField.apply(tableIndex), 60);
        eleUtil.doSendKeys(this.currentPageTextField.apply(tableIndex), pageNumber);
    }


    public boolean isFirstNavigationButtonEnable(String tableIndex) {
        try {
            String str = eleUtil.getAttributeValue(this.tableNavigationFirstButton.apply(tableIndex), "class");
        } catch (Throwable t) {
            System.out.println(t.getStackTrace());
        }
        if (eleUtil.getAttributeValue(this.tableNavigationFirstButton.apply(tableIndex), "class").contains("disable"))
            return false;
        return true;
    }

    public boolean isPreviousNavigationButtonEnable(String tableIndex) {
        if (eleUtil.getAttributeValue(this.tableNavigationPreviousButton.apply(tableIndex), "class").contains("disable"))
            return false;
        return true;
    }

    public boolean isNextNavigationButtonEnable(String tableIndex) {
        if (eleUtil.getAttributeValue(this.tableNavigationNextButton.apply(tableIndex), "class").contains("disable"))
            return false;
        return true;
    }

    public boolean isLastNavigationButtonEnable(String tableIndex) {
        if (eleUtil.getAttributeValue(this.tableNavigationLastButton.apply(tableIndex), "class").contains("disable"))
            return false;
        return true;
    }

    /**
     * This method is used to click on Value from Search Result Table
     *
     * @param columnHeaderName is in String format
     * @param rowIndex         is in integer format
     */
    public void clickOnValueFromSearchResultTable(String columnHeaderName, int rowIndex) {
        String[] allValues = eleUtil.getAttributeValue(this.locatorOfColumnHeadBar.apply(columnHeaderName), "id").split("_");
        String ariaDescribedByValue = allValues[allValues.length - 1];
        eleUtil.scrollToElementByActionsClass(this.rowValueForSpecificColumn.apply(ariaDescribedByValue, Integer.toString(rowIndex)));
        eleUtil.doClick(this.rowValueForSpecificColumn.apply(ariaDescribedByValue, Integer.toString(rowIndex)));
    }


    /**
     * This method is used to click on Value from Search Result Table
     *
     * @param columnHeaderName is in String format
     * @param rowIndex         is in integer format
     */
    public void clickOnValueFromSearchResultTable(String tableID, String columnHeaderName, int rowIndex) {
        String[] allValues = eleUtil.getAttributeValue(this.locatorOfColumnHeadBar.apply(columnHeaderName), "id").split("_");
        String ariaDescribedByValue = allValues[allValues.length - 1];
        eleUtil.scrollToElementByActionsClass(this.rowValueForSpecificColumnAndTable.apply(tableID, ariaDescribedByValue, Integer.toString(rowIndex)));
        eleUtil.doClick(this.rowValueForSpecificColumnAndTable.apply(tableID, ariaDescribedByValue, Integer.toString(rowIndex)));
    }

    /**
     * This method is used to get Value from Table Identified by ID Attribute
     *
     * @param tableID          is in String format
     * @param columnHeaderName is in String format
     * @param rowIndex         is in integer format
     * @return This will return value in String Format
     */
    public void clickOnValueInTable(String tableID, String columnHeaderName, int rowIndex) {
        String columnIndex = eleUtil.getTableColumnHeaderIndex(this.table.apply(tableID), columnHeaderName);
        eleUtil.doClick(this.rowValueForSpecificColumnIndexAndTable.apply(tableID, columnIndex, Integer.toString(rowIndex)));
    }


    /**
     * This method is used to get Value from Table Identified by Aria-Labelled Attribute
     *
     * @param tableAriaLabelledAttribute is in String format
     * @param columnHeaderName           is in String format
     * @param rowIndex                   is in integer format
     * @return This will return value in String Format
     */
    public void clickOnValueInTableIdentifiedByAriaLabelledAttribute(String tableAriaLabelledAttribute, String columnHeaderName, int rowIndex) {
        String columnIndex = eleUtil.getTableColumnHeaderIndex(this.tableWithAriaLabelledByAttribute.apply(tableAriaLabelledAttribute), columnHeaderName);
        eleUtil.doClick(this.rowValueForSpecificColumnIndexAndTableWithAriaLabelledByAttribute.apply(tableAriaLabelledAttribute, columnIndex, Integer.toString(rowIndex)));
    }


    public void clickOnHeaderBar(String headerBar) {
        eleUtil.doClick(this.tableHeaderBar.apply(headerBar));
    }

    /**
     * This method is used to get the Pagination Info from Saved Search Result Table
     *
     * @return This will return the Pagination Info in String Format
     */
    public String getPaginationInfoFromSavedSearchResultTable() {
        String paginationInfo = null;
        try {
            eleUtil.waitForElementPresence(this.paginationInfoLabelForSavedSearchTable, 40);
            paginationInfo = eleUtil.doGetText(this.paginationInfoLabelForSavedSearchTable);
            ExtentReportListener.test.get().log(Status.INFO,
                    "Fetching pagination Info for Saved Search Result Table is successfully");
        } catch (Throwable e) {
            ExtentReportListener.test.get().log(Status.FAIL, "Failed while fetching pagination Info for saved search result table");
            Assert.fail(e.getMessage());
        }
        return paginationInfo;
    }


}
