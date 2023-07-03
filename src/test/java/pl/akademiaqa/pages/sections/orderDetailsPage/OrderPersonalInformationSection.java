package pl.akademiaqa.pages.sections.orderDetailsPage;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import pl.akademiaqa.pages.BasePage;
import pl.akademiaqa.utils.EmailUtils;

import static pl.akademiaqa.utils.EmailUtils.*;

public class OrderPersonalInformationSection extends BasePage {

    private final String personalInformationSection = "#checkout-personal-information-step ";
    private final String customerForm = personalInformationSection + "#customer-form ";
    private Locator socialTitleMr;
    private Locator socialTitleMrs;
    private Locator firstNameInput;
    private Locator lastNameInput;
    private Locator emailInput;
    private Locator passwordInput;
    private Locator dobInput;
    private Locator termsAndConditionsCheckBox;
    private Locator customerPrivacyCheckBox;
    private Locator continueButton;


    public OrderPersonalInformationSection(Page page) {
        super(page);
//        page.waitForLoadState(LoadState.NETWORKIDLE);
        this.socialTitleMr = page.locator(customerForm + "#field-id_gender-1");
        this.socialTitleMrs = page.locator(customerForm + "#field-id_gender-2");
        this.firstNameInput = page.locator(customerForm + "#field-firstname");
        this.lastNameInput = page.locator(customerForm + "#field-lastname");
        this.emailInput = page.locator(customerForm + "#field-email");
        this.passwordInput = page.locator(customerForm + "#field-password");
        this.dobInput = page.locator(customerForm + "#field-birthday");
        this.termsAndConditionsCheckBox = page.locator(customerForm + "input[name=psgdpr]");
        this.customerPrivacyCheckBox = page.locator(customerForm + "input[name=customer_privacy]");
        this.continueButton = page.locator(customerForm + "button[name=continue]");
    }

    public OrderAddressSection enterPersonalInformation() {
        selectSocialTitleMr()
                .enterFirstName("Bartek")
                .enterLastName("Testowy")
                .enterEmail(getRandomEmail())
                .enterPassword("123456!@#$%^__")
                .enterDob("05/30/1997")
                .checkTermsAndConditions()
                .checkCustomerPrivacy()
                .clickContinue();

        return new OrderAddressSection(page);
    }

    private OrderPersonalInformationSection selectSocialTitleMr() {
        socialTitleMr.check();
        return this;
    }

    private OrderPersonalInformationSection selectSocialTitleMrs() {
        socialTitleMrs.check();
        return this;
    }

    private OrderPersonalInformationSection enterFirstName(String firstName) {
        firstNameInput.fill(firstName);
        return this;
    }

    private OrderPersonalInformationSection enterLastName(String lastName) {
        lastNameInput.fill(lastName);
        return this;
    }

    private OrderPersonalInformationSection enterEmail(String email) {
        emailInput.fill(email);
        return this;
    }

    private OrderPersonalInformationSection enterPassword(String password) {
        passwordInput.fill(password);
        return this;
    }

    private OrderPersonalInformationSection enterDob(String dob) {
        dobInput.fill(dob);
        return this;
    }

    private OrderPersonalInformationSection checkTermsAndConditions() {
        termsAndConditionsCheckBox.check();
        return this;
    }

    private OrderPersonalInformationSection checkCustomerPrivacy() {
        customerPrivacyCheckBox.check();
        return this;
    }

    private OrderPersonalInformationSection clickContinue() {
        continueButton.click();
        return this;
    }
}
