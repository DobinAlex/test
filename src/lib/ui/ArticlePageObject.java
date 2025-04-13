package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE_TPL = "//*[@text='{TITLE}']",
            FOOTER_ELEMENT = "//*[@content-desc='View article in browser']",
            ADD_TO_LIST_BUTTON = "//android.widget.Button[@text='Add to list']",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTT0N = "//*[@text='OK']";

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String getTitleArticleElement(String substring) {
        return TITLE_TPL.replace("{TITLE}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement(String substring) {
        String title_article_xpath = getTitleArticleElement(substring);
        return this.waitForElementPresent(By.xpath(title_article_xpath), "Cannot find article title on page!", 10);
    }

    public String getArticleTitle(String title_article) {
        WebElement title_element = waitForTitleElement(title_article);
        return title_element.getAttribute("name");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    public void saveArticleToNewMyList(String name_of_folder) {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSave();

        this.waitForElementAndClick(
                By.xpath(ADD_TO_LIST_BUTTON),
                "Cannot find button 'Add to list'",
                30
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTT0N),
                "Cannot press OK button",
                5
        );
    }

    public void saveArticleToAvailableMyList(String name_of_folder) {
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickSave();

        this.waitForElementAndClick(
                By.xpath("//android.widget.Button[@text='Add to list']"),
                "Cannot find button 'Add to list'",
                5
        );

        this.waitForElementAndClick(
                By.id("org.wikipedia:id/item_title_container"),
                "Cannot find list 'Learning programming'",
                0
        );
        NavigationUI.clickOptions();
        NavigationUI.clickExploreInOptions();
    }

    public void notWaitForElementPresent(String title_article) {
        String title_article_xpath = getTitleArticleElement(title_article);
        this.assertElementPresent(
                By.xpath(title_article_xpath),
                "Title is missing from article"
        );
    }
}