package pl.akademiaqa.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pl.akademiaqa.pages.HomePage;
import pl.akademiaqa.pages.SearchResultsPage;

import java.util.stream.Stream;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.*;
import static org.assertj.core.api.Assertions.*;

class SearchTest extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);

        // to można przenieść do BAseTest
//        page.navigate(Properties.getProperty("app.url"));
//        homePage.getTopNavigationSection().setEnglishPageLanguage();
    }

//    @Test
//    void should_return_items_by_search_string_shirt() {
//        SearchResultsPage searchResultsPage = homePage.getTopMenuAndSearchSection().searchItems("t-shirt");
//        assertThat(searchResultsPage.getSearchResultsSection().getHeader()).hasText("Search results");
//        assertThat(searchResultsPage.getSearchResultsSection().getNumberOfItems()).isGreaterThan(0);
//    }
//
//    @Test
//    void should_return_items_by_search_string_mug() {
//        SearchResultsPage searchResultsPage = homePage.getTopMenuAndSearchSection().searchItems("mug");
//        assertThat(searchResultsPage.getSearchResultsSection().getHeader()).hasText("Search results");
//        assertThat(searchResultsPage.getSearchResultsSection().getNumberOfItems()).isGreaterThan(0);
//    }
//
//    // https://reflectoring.io/tutorial-junit5-parameterized-tests/
//    @ParameterizedTest
//    @ValueSource(strings = {"t-shirt", "mug", "frame"})
//    void should_return_items_by_search_string(String itemName) {
//        SearchResultsPage searchResultsPage = homePage.getTopMenuAndSearchSection().searchItems(itemName);
//        assertThat(searchResultsPage.getSearchResultsSection().getHeader()).hasText("Search results");
//        assertThat(searchResultsPage.getSearchResultsSection().getNumberOfItems()).isGreaterThan(0);
//    }

    @DisplayName("Search for item test")
    @ParameterizedTest(name = "Search for {0} should return {1} item(s)")
    @MethodSource("searchData")
    void should_return_products_by_search_product_name(String productName, int itemCounter) {
        SearchResultsPage searchResultsPage = homePage.getTopMenuAndSearchSection().searchForProduct(productName);
//        SearchResultsPage searchResultsPage = homePage.searchProduct(productName);
        assertThat(searchResultsPage.getSearchResultsSection().getHeader()).hasText("Search results");
        assertThat(searchResultsPage.getSearchResultsSection().getNumberOfProducts()).isEqualTo(itemCounter);
    }

    private static Stream<Arguments> searchData() {
        return Stream.of(
                Arguments.of("t-shirt", 1),
                Arguments.of("mug", 5),
                Arguments.of("frame", 4)
        );
    }
}
