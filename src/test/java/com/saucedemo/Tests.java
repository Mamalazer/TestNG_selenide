package com.saucedemo;

import com.codeborne.selenide.SelenideElement;
import com.saucedemo.paje_objects.LoginPage;
import com.saucedemo.paje_objects.ProductsPage;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$x;
import static com.saucedemo.Items.SAUCE_LABS_BACKPACK;
import static com.saucedemo.TestData.*;
import static org.testng.Assert.assertEquals;

public class Tests extends BaseTest {

    @Test(description = "Логин с валидными данными", priority = 1, testName = "Позитивный логин")
    public void positiveLogin() {
        LoginPage login = new LoginPage();
        login.login(LOGIN, PASSWORD);
        assertEquals($x("//span[text()='Products']").getText(), "PRODUCTS",
                "Ошибка при авторизации");
    }

    @Test(description = "Логин под удалённым пользователем", priority = 1, testName = "Негативный логин")
    public void negativeLogin() {
        LoginPage login = new LoginPage();
        login.login(LOCKED_LOGIN, PASSWORD);
        assertEquals(login.getErrorMessage(), "Epic sadface: Sorry, this user has been locked out.",
                "Успешная авторизация под удалённым пользователем");
    }

    @Test(description = "Добавление товара в корзину", priority = 1, testName = "Добавление товара в корзину",
            groups = {"Smoke"})
    public void addItemToBasket() {
        Map<String, String> itemInfo = new LoginPage()
                .login(LOGIN, PASSWORD)
                .addItemToBasketByName(SAUCE_LABS_BACKPACK.item)
                .goToBasket()
                .checkoutItem()
                .addCustomerInfo()
                .getItemInfo();

        assertEquals(itemInfo.get("name"), SAUCE_LABS_BACKPACK.item, "Названия товаров не совпадают");
        assertEquals(itemInfo.get("price"), SAUCE_LABS_BACKPACK.price, "Цены товаров не совпадают");
    }

}
