package pl.akademiaqa.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import lombok.Getter;
import pl.akademiaqa.dto.ProductDTO;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.pages.ProductDetailsPage;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SearchResultsSection extends BasePage {

    private Locator header;
    private List<Locator> products;

    public SearchResultsSection(Page page) {
        super(page);
        this.header = page.locator("#wrapper h1");
        this.products = page.locator(".js-product").all();
    }

    public int getNumberOfProducts() {
        return products.size();
    }

    public List<Double> getPrices() {
        return products.stream()
                .map(p -> p.locator(".price").innerText())
                .map(p -> p.replaceAll("zł", ""))
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public ProductDetailsPage addProductToCart(String productName) {
        final var product = productsToDto().stream()
                .filter(p -> p.name().equalsIgnoreCase(productName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Can not find product with name: " + productName));

        product.thumbnail().click();

        return new ProductDetailsPage(page);
    }

    private List<ProductDTO> productsToDto() {
        return products.stream()
                .map(p -> ProductDTO.builder()
                        .thumbnail(p.locator(".thumbnail-top"))
                        .name(p.locator(".product-title").innerText())
                        .price(Double.parseDouble(p.locator(".price").innerText().replaceAll("zł", "")))
                        .build())
                .collect(Collectors.toList());
    }
}
