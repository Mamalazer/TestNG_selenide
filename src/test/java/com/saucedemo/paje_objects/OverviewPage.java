package com.saucedemo.paje_objects;

import com.codeborne.selenide.SelenideElement;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;

public class OverviewPage {

    private SelenideElement item = $x("//div[@class='cart_item']");

    public Map<String, String> getItemInfo() {
        Map<String, String> itemInfo = new HashMap<>();
        String name = item.$x(".//div[@class='inventory_item_name']").getText();
        String price = item.$x(".//div[@class='inventory_item_price']").getText();
        itemInfo.put("name", name);
        itemInfo.put("price", price);
        return itemInfo;
    }

}
