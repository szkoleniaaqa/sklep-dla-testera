package pl.akademiaqa.facades;

import pl.akademiaqa.pages.AddToCartModalPage;
import pl.akademiaqa.pages.HomePage;

public class AddProductToCartFacade {

    private HomePage homePage;

    public AddProductToCartFacade(HomePage homePage) {
        this.homePage = homePage;
    }

    public AddToCartModalPage addProductWithCustomizationToCart(String productName, String customMessage) {
        return homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .customizeProduct(customMessage)
                .addProductToCart();
    }

    public AddToCartModalPage addProductToCart(String productName) {
        return homePage
                .searchForProduct(productName)
                .viewProductDetails(productName)
                .addProductToCart();
    }
}
