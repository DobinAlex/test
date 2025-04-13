package tests;

import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboardingPresent();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String name_of_folder = "Learning programming";
        ArticlePageObject.saveArticleToNewMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickOptions();
        NavigationUI.clickExploreInOptions();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.closeOverlayMessage();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");
    }

    @Test
    public void testSaveAndDeleteArticleToMyList() {
        OnboardingPageObject OnboardingPageObject = new OnboardingPageObject(driver);
        OnboardingPageObject.skipOnboardingPresent();

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Java (programming language)");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement("Java (programming language)");
        String name_of_folder = "Learning programming";
        ArticlePageObject.saveArticleToNewMyList(name_of_folder);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("PHP");
        SearchPageObject.clickByArticleWithSubstring("PHP");
        ArticlePageObject.waitForTitleElement("Scripting language created in 1994");
        ArticlePageObject.saveArticleToAvailableMyList(name_of_folder);

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.closeOverlayMessage();
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete("Java (programming language)");

        //article_title = ArticlePageObject.getArticleTitle("PHP");
        MyListsPageObject.clickArticleTitleInMyList("PHP");
        ArticlePageObject.waitForTitleElement("PHP");
    }
}