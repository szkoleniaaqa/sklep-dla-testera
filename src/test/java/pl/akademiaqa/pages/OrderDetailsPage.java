package pl.akademiaqa.pages;

import com.microsoft.playwright.Page;
import lombok.Getter;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderAddressSection;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderPersonalInformationSection;
import pl.akademiaqa.pages.sections.orderDetailsPage.OrderShippingSections;

import static pl.akademiaqa.utils.PageUtils.*;

@Getter
public class OrderDetailsPage extends BasePage {

    private OrderPersonalInformationSection personalInformationSection;
    private OrderAddressSection addressSection;

    private OrderShippingSections shippingSections;

    public OrderDetailsPage(Page page) {
        super(page);
        waitForPageToLoad(page);
        this.personalInformationSection = new OrderPersonalInformationSection(page);
        this.addressSection = new OrderAddressSection(page);
        this.shippingSections = new OrderShippingSections(page);
    }

    public OrderConfirmationPage enterOrderDetails() {
        return personalInformationSection
                .enterPersonalInformation()
                .enterAddress()
                .selectShippingMethod()
                .placeOrder();
    }
}
