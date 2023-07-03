package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.sections.SearchResultsSection;

import static pl.akademiaqa.utils.PageUtils.*;

@Getter
public class SearchResultsPage extends BasePage {

    private SearchResultsSection searchResultsSection;

    public SearchResultsPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.searchResultsSection = new SearchResultsSection(page);
    }

    public ProductDetailsPage viewProductDetails(String productName){
        return getSearchResultsSection().addProductToCart(productName);
    }

}
