package pl.akademiaqa.pages.sections.common;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;
import pl.akademiaqa.pages.ArtPage;
import pl.akademiaqa.pages.SearchResultsPage;

@Getter
public class TopMenuAndSearchSection {

    private Page page;
    private Locator searchInput;
    private Locator artLink;
    private Locator autocomplete;

    public TopMenuAndSearchSection(Page page) {
        this.page = page;
        this.searchInput = page.locator("input[name=s]");
        this.artLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Art").setExact(true));
        this.autocomplete = page.locator(".ui-autocomplete");
    }

    public SearchResultsPage searchForProduct(String itemName) {
        searchInput.fill(itemName);
        page.waitForCondition(() -> autocomplete.isVisible());
        page.keyboard().press("Enter");
        return new SearchResultsPage(page);
    }

    public ArtPage clickArtLink() {
        artLink.click();
        return new ArtPage(page);
    }
}
