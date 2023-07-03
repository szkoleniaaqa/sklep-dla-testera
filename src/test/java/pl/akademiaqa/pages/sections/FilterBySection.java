package pl.akademiaqa.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.options.LoadState;
import lombok.Getter;
import pl.akademiaqa.pages.BasePage;

import java.util.Arrays;

@Getter
public class FilterBySection extends BasePage {

    private Locator leftSlider;
    private String price;

    private Locator filterPrice;

    public FilterBySection(Page page) {
        super(page);
        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.leftSlider = page.locator(".ui-slider-handle").first();
        this.price = page.locator("#search_filters li p").innerText();
        this.filterPrice = page.locator(".filter-block");
    }

    public void filterItemsByFromPrice(double fromPrice) {
        boolean filter = true;
        while (filter) {
            leftSlider.press("ArrowRight");
            page.waitForCondition(() -> page.locator(".overlay__inner").isHidden());
            if (fromPrice == getFromPrice()) {
                filter = false;
            }
        }
    }

    public void filterItemsByFromPrice2(double fromPrice) {
        boolean filter = true;
        while (filter) {
            PlaywrightAssertions.assertThat(leftSlider).isVisible();

            double x = leftSlider.boundingBox().x;
            double middleX = x + leftSlider.boundingBox().width / 2;
            double y = leftSlider.boundingBox().y;
            double middleY = y + leftSlider.boundingBox().height / 2;

            leftSlider.scrollIntoViewIfNeeded();
            page.mouse().move(middleX, middleY);
            page.mouse().down();
            page.mouse().move(x + 7.00, y);
            page.mouse().up();

            if (fromPrice == getFromPrice()) {
                filter = false;
            }
        }
    }

    private double getFromPrice() {
        return Arrays.asList(page.locator("#search_filters li p").innerText().split(" "))
                .stream()
                .map(p -> p.replaceAll("zł", ""))
                .map(Double::parseDouble)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid price format"));
    }
}
