package pl.akademiaqa.pages.sections.productDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import pl.akademiaqa.pages.AddToCartModalPage;
import pl.akademiaqa.pages.BasePage;

public class AddToCartSection extends BasePage {

    private Locator addProductButton;
    private Locator removeProductButton;
    private Locator addToCartButton;

    public AddToCartSection(Page page) {
        super(page);
//        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.addProductButton = page.locator(".touchspin-up");
        this.removeProductButton = page.locator(".touchspin-down");
        this.addToCartButton = page.locator(".add-to-cart");
    }

    public AddToCartSection addProduct() {
        addProductButton.click();
        return this;
    }

    public AddToCartModalPage addProductToCart() {
        addToCartButton.click();
        return new AddToCartModalPage(page);
    }
}
