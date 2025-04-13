package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject {

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains(@text, 'Search Wikipedia')]",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='{SUBSTRING}']",
            SEARCH_RESULT_TITLE_AND_DESCRIPTION_BY_SUBSTRING_TPL = "//*[@text='{TITLE_SUBSTRING}']/following-sibling::android.widget.TextView[@text='{DESCRIPTION_SUBSTRING}']",
            SEARCH_RESULT_DESCRIPTION_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/page_list_item_description'][@text='{SUBSTRING}']",
            SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@resource-id='org.wikipedia:id/page_list_item_title']",
            SEARCH_EMPTY_RESULT_ELEMENT = "//*[@text='No results']";

    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getResultSearchTitleElement(String substring) {
        return SEARCH_RESULT_TITLE_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchDescriptionElement(String substring) {
        return SEARCH_RESULT_DESCRIPTION_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    private static String getResultSearchTitleAndDescriptionElement(String title_substring, String description_substring) {
        return SEARCH_RESULT_TITLE_AND_DESCRIPTION_BY_SUBSTRING_TPL.replace("{TITLE_SUBSTRING}", title_substring).replace("{DESCRIPTION_SUBSTRING}", description_substring);
    }
    /* TEMPLATE METHODS */

    public void initSearchInput() {
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input");
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click on search input element", 5);
    }

    public void waitForCancelButtonToAppear() {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear() {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON), "Search cancel button is still present!", 5);
    }

    public void clickCancelSearch() {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line) {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchTitleResult(String substring) {
        String search_result_xpath = getResultSearchTitleElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search title result with substring " + substring);
    }

    public void waitForSearchDescriptionResult(String substring) {
        String search_result_xpath = getResultSearchDescriptionElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search description result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring) {
        String search_result_xpath = getResultSearchTitleElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public void waitForElementByTitleAndDescription(String title, String description) {
        String search_result_xpath = getResultSearchTitleAndDescriptionElement(title , description);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with title '" + title + "' and description '" + description + "'", 10);
    }

    public int getAmountOfFoundArticles() {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by the request"
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel() {
        this.waitForElementPresent(By.xpath(SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element.", 15);
    }

    public void assertThereIsNoResultOfSearch() {
        this.assertElementNotPresent(By.xpath(SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }
}