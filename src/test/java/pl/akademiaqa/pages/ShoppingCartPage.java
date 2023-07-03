package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.sections.shoppingCartPage.SummarySection;

import static pl.akademiaqa.utils.PageUtils.*;

public class ShoppingCartPage extends BasePage {

    @Getter
    private SummarySection summarySection;

    public ShoppingCartPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.summarySection = new SummarySection(page);
    }

    public OrderDetailsPage proceedToCheckoutOnShoppingCartPage() {
        return getSummarySection().proceedToCheckout();
    }
}
