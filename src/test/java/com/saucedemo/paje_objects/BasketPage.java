package com.saucedemo.paje_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {

    private SelenideElement item = $x("//div[@class='cart_item']");
    private SelenideElement checkoutButton = $x("//button[@data-test='checkout']");

    public String getItemNameFromBasket() {
        return item.$x(".//div[@class='inventory_item_name']").getText();
    }

    public CheckoutInformationPage checkoutItem() {
        checkoutButton.click();
        return new CheckoutInformationPage();
    }

}
