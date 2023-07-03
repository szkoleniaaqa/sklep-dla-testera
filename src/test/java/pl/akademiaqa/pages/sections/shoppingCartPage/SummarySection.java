package pl.akademiaqa.pages.sections.shoppingCartPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.pages.OrderDetailsPage;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderPersonalInformationSection;

public class SummarySection extends BasePage {

    private Locator proceedToCheckoutButton;

    public SummarySection(Page page) {
        super(page);
        this.proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public OrderDetailsPage proceedToCheckout() {
        proceedToCheckoutButton.click();
        return new OrderDetailsPage(page);
    }
}
