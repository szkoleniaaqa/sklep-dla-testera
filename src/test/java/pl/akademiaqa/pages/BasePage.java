package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.sections.common.TopMenuAndSearchSection;
import pl.akademiaqa.pages.sections.common.TopNavigationSection;

public class BasePage {

    protected Page page;
    @Getter
    protected TopMenuAndSearchSection topMenuAndSearchSection;
    @Getter
    protected TopNavigationSection topNavigationSection;

    public BasePage(Page page) {
        this.page = page;
        this.topMenuAndSearchSection = new TopMenuAndSearchSection(page);
        this.topNavigationSection = new TopNavigationSection(page);
    }

    public SearchResultsPage searchForProduct(String productName) {
        return topMenuAndSearchSection.searchForProduct(productName);
    }

    public void setPageLanguageToEn() {
        topNavigationSection.setPageLanguageToEn();
    }

    public ArtPage clickArtLink() {
        return getTopMenuAndSearchSection().clickArtLink();
    }
}
