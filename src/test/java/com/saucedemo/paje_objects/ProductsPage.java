package com.saucedemo.paje_objects;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ProductsPage {

    private ElementsCollection listOfItems = $$x("//div[@class='inventory_item']");
    private ElementsCollection listNamesOfItems = $$x("//div[@class='inventory_item_name']");
    private ElementsCollection listPriceOfItems = $$x("//div[@class='inventory_item_price']");
    private SelenideElement item = $x("//div[@class='inventory_item']");
    private SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");
    private By productSortDropDown = By.xpath("//select[@data-test='product_sort_container']");

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

    /**
     * 0 - ascending order for name,
     * 1 - descending order for name,
     * 2 - ascending order for price,
     * 3 - descending order for price
     */
    public ProductsPage sortProducts(int option) {
        Select select = new Select(WebDriverRunner.getWebDriver().findElement(productSortDropDown));
        select.selectByIndex(option);
        return this;
    }

    /**
     * names - return names list of products,
     * prices - return prices list of product
     * @return list of attributes
     */
    public List<String> getProductAttributes(String attr) {
        if (attr.equalsIgnoreCase("names")) {
            return listNamesOfItems.texts();
        } else if (attr.equalsIgnoreCase("prices")) {
            return listPriceOfItems.texts()
                    .stream()
                    .map(x -> x.replaceAll("\\$", ""))
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }

    }

}
