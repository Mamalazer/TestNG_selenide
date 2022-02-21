package com.saucedemo;

public enum Items {

    SAUCE_LABS_BACKPACK("Sauce Labs Backpack", "$29.99"),
    SAUCE_LABS_BIKE_LIGHT("Sauce Labs Bike Light", "$9.99"),
    SAUCE_LABS_BOLT_T_SHIRT("Sauce Labs Bolt T-Shirt", "$15.99"),
    SAUCE_LABS_FLEECE_JACKET("Sauce Labs Fleece Jacket", "$49.99"),
    SAUCE_LABS_ONESIE("Sauce Labs Onesie", "$7.99"),
    TEST_ALL_THE_THINGS_T_SHIRT("Test.allTheThings() T-Shirt (Red)", "$15.99");

    String item;
    String price;

    Items(String item, String price) {
        this.item = item;
        this.price = price;
    }
}
