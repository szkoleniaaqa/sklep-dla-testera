package pl.akademiaqa.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.pages.ArtPage;
import pl.akademiaqa.pages.HomePage;

import static org.assertj.core.api.Assertions.*;

class FilterByPriceTest extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage(page);
    }

    @Test
    void should_return_products_with_price_greater_than_40() {
        // System.out.println(page.url());
        // page.navigate(page.url() + "&q=Price-zł-40-44");

//        ArtPage artPage = homePage.getTopMenuAndSearchSection().clickArtLink();
        ArtPage artPage = homePage.clickArtLink();
        artPage.getFilterBySection().filterItemsByFromPrice(40.00);
        assertThat(artPage.getProductsSection().getProductPrices().stream().allMatch(p -> p > 40.00)).isTrue();
    }
}
