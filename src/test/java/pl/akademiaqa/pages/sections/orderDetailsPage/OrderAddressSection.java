package pl.akademiaqa.pages.sections.orderDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.utils.PageUtils;

import static pl.akademiaqa.utils.PageUtils.*;

public class OrderAddressSection extends BasePage {

    private final String addressSection = "#checkout-addresses-step ";
    private Locator addressInput;
    private Locator zipCode;
    private Locator city;
    private Locator continueButton;

    public OrderAddressSection(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.addressInput = page.locator(addressSection + "#field-address1");
        this.zipCode = page.locator(addressSection + "#field-postcode");
        this.city = page.locator(addressSection + "#field-city");
        this.continueButton = page.locator(addressSection + "button[name=confirm-addresses]");
    }

    public OrderShippingSections enterAddress() {
        addressInput.fill("Ul. Sezamkowa 8");
        zipCode.fill("90-210");
        city.fill("Kraków");
        continueButton.click();

        return new OrderShippingSections(page);
    }
}
