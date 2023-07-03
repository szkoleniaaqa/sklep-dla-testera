package pl.akademiaqa.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.akademiaqa.facades.AddProductToCartFacade;
import pl.akademiaqa.facades.OrderProductFacade;
import pl.akademiaqa.pages.HomePage;

import static org.assertj.core.api.Assertions.*;

class FullPurchaseTest extends BaseTest {

    private final String productName = "Customizable Mug";
    private HomePage homePage;
    private AddProductToCartFacade addProductToCartFacade;
    private OrderProductFacade orderProductFacade;

    @BeforeEach
    public void beforeEach() {
        homePage = new HomePage(page);
        addProductToCartFacade = new AddProductToCartFacade(homePage);
        orderProductFacade = new OrderProductFacade();
    }

    // poprawić ten test:
    // 1. ma być bardziej z biznesowego punktu widzenia
    // 2. przekazać DTO z danymi do testów
    @Test
    void should_purchase_selected_product_test() {
        final var searchResultsPage = homePage.getTopMenuAndSearchSection()
                .searchForProduct("Customizable Mug");

        final var productDetailsPage = searchResultsPage.getSearchResultsSection()
                .addProductToCart("Customizable Mug");

        productDetailsPage.getProductCustomizationSection()
                .customizeProduct("THIS IS CUSTOM MUG!");

        final var addToCartModalPage = productDetailsPage.getAddToCartSection()
                .addProduct()
                .addProductToCart();


        assertThat(addToCartModalPage.getConfirmationLabel()).contains("Product successfully added to your shopping cart");

        final var shoppingCartPage = addToCartModalPage.proceedToCheckoutOnModalPage();

        final var orderDetailsPage = shoppingCartPage.getSummarySection()
                .proceedToCheckout();

        final var orderConfirmationPage = orderDetailsPage.getPersonalInformationSection()
                .enterPersonalInformation()
                .enterAddress()
                .selectShippingMethod()
                .placeOrder();

        assertThat(orderConfirmationPage.getConfirmationDetailsSection().getTitle()).containsIgnoringCase("Your order is confirmed");
    }

    @Test
    void should_purchase_selected_product_test_1() {
        final var confirmationPage =
                homePage
                        .searchForProduct(productName)
                        .viewProductDetails(productName)
                        .customizeProduct("THIS IS CUSTOM MUG!")
                        .addProductToCart();
        assertThat(confirmationPage.getConfirmationLabel()).contains("Product successfully added to your shopping cart");


        final var orderConfirmationPage =
                confirmationPage
                        .proceedToCheckoutOnModalPage()
                        .proceedToCheckoutOnShoppingCartPage()
                        .enterOrderDetails();
        assertThat(orderConfirmationPage.getConfirmationDetailsSection().getTitle()).containsIgnoringCase("Your order is confirmed");
    }

    @Test
    void should_purchase_selected_product_test_facade() {
        final var confirmationPage = addProductToCartFacade.addProductWithCustomizationToCart("CUSTOMIZABLE MUG", "123");
        assertThat(confirmationPage.getConfirmationLabel()).contains("Product successfully added to your shopping cart");

        final var orderConfirmationPage = orderProductFacade.orderProduct(confirmationPage);
        assertThat(orderConfirmationPage.getConfirmationDetailsSection().getTitle()).containsIgnoringCase("Your order is confirmed");
    }
}
