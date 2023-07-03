package pl.akademiaqa.pages.sections;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.Getter;
import pl.akademiaqa.pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ProductsSection extends BasePage {

    private List<String> productPricesText;

    public ProductsSection(Page page) {
        super(page);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.productPricesText = productPricesAsText();
    }

    public List<Double> getProductPrices() {
        return productPricesAsText()
                .stream()
                .map(p -> p.replaceAll("zł", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    private List<String> productPricesAsText() {
        return page.locator(".js-product .price").allInnerTexts();
    }
}
