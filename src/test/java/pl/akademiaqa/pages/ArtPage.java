package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.Getter;
import pl.akademiaqa.pages.sections.FilterBySection;
import pl.akademiaqa.pages.sections.ProductsSection;

@Getter
public class ArtPage extends BasePage {

    private FilterBySection filterBySection;
    private ProductsSection productsSection;

    public ArtPage(Page page) {
        super(page);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.filterBySection = new FilterBySection(page);
        this.productsSection = new ProductsSection(page);
    }
}
