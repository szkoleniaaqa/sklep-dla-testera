package pl.akademiaqa.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import lombok.Getter;

import static pl.akademiaqa.utils.PageUtils.*;

public class AddToCartModalPage extends BasePage {

    @Getter
    private String confirmationLabel;
    private Locator proceedToCheckoutButton;

    public AddToCartModalPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.confirmationLabel = page.locator("#myModalLabel").innerText();
        this.proceedToCheckoutButton = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Proceed to checkout"));
    }

    public ShoppingCartPage proceedToCheckoutOnModalPage() {
        proceedToCheckoutButton.click();
        return new ShoppingCartPage(page);
    }
}
