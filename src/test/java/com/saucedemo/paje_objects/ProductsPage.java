package com.saucedemo.paje_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage {

    private ElementsCollection listOfItems = $$x("//div[@class='inventory_item']");
    private SelenideElement item = $x("//div[@class='inventory_item']");
    private SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");

    public SelenideElement getItemByName(String name) {
        return item.$x(".//div[text()='" + name + "']");
    }

    public ProductsPage addItemToBasketByName(String name) {
        item.$x(".//div[text()='" + name + "']//ancestor::div[@class='inventory_item_description']//button[text()='Add to cart']").click();
        return this;
    }

    public BasketPage goToBasket() {
        basketButton.click();
        return new BasketPage();
    }

}
