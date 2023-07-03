package pl.akademiaqa.pages.sections.productDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import pl.akademiaqa.pages.BasePage;

public class ProductCustomizationSection extends BasePage {

    private Locator customMessageInput;
    private Locator saveCustomizationButton;
    private Locator customizationLabel;

    public ProductCustomizationSection(Page page) {
        super(page);
//        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.customMessageInput = page.locator("#field-textField1");
        this.saveCustomizationButton = page.locator("button[name=submitCustomizedData]");
        this.customizationLabel = page.locator(".customization-label");
    }

    public void customizeProduct(String customMessage) {
        customMessageInput.fill(customMessage);
        saveCustomizationButton.click();
        page.waitForCondition(() -> customizationLabel.isVisible());
    }
}
