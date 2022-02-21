package com.saucedemo.paje_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.saucedemo.TestData.*;

public class CheckoutInformationPage {

    private SelenideElement firstNameField = $x("//input[@data-test='firstName']");
    private SelenideElement lastNameField = $x("//input[@data-test='lastName']");
    private SelenideElement postalCodeField = $x("//input[@data-test='postalCode']");
    private SelenideElement continueButton = $x("//input[@data-test='continue']");

    public OverviewPage addCustomerInfo() {
        firstNameField.setValue(FIRST_NAME);
        lastNameField.setValue(LAST_NAME);
        postalCodeField.setValue(POSTAL_CODE);
        continueButton.click();
        return new OverviewPage();
    }

}
