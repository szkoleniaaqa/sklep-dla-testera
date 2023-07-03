package pl.akademiaqa.facades;

import pl.akademiaqa.pages.AddToCartModalPage;
import pl.akademiaqa.pages.OrderConfirmationPage;

public class OrderProductFacade {

    public OrderConfirmationPage orderProduct(AddToCartModalPage confirmationPage) {
        return confirmationPage
                .proceedToCheckoutOnModalPage()
                .proceedToCheckoutOnShoppingCartPage()
                .enterOrderDetails();
    }
}
