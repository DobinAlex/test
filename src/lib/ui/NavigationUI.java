package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    private static final String
            MY_LISTS_LINK = "org.wikipedia:id/nav_tab_reading_lists",
            SAVE_BUTTON = "//android.widget.TextView[@content-desc='Save']",
            OPTIONS_BUTTON = "org.wikipedia:id/page_toolbar_button_show_overflow_menu",
            OPTIONS_EXPLORE_BUTTON = "org.wikipedia:id/page_explore";

    public NavigationUI(AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists() {
        this.waitForElementAndClick(
                By.id(MY_LISTS_LINK),
                "Cannot find navigation button to 'Saved'",
                5
        );
    }

    public void clickSave() {
        this.waitForElementAndClick(
                By.xpath(SAVE_BUTTON),
                "Cannot find button 'Save'",
                5
        );
    }

    public void clickOptions() {
        this.waitForElementAndClick(
                By.id(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                0
        );
    }

    public void clickExploreInOptions() {
        this.waitForElementAndClick(
                By.id(OPTIONS_EXPLORE_BUTTON),
                "Cannot find option to open 'Explore'",
                5
        );
    }
}