package com.saucedemo;

import com.saucedemo.paje_objects.LoginPage;
import core.BaseTest;
import org.testng.annotations.Test;

import java.util.*;
import java.util.stream.Collectors;

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

    @Test(description = "Создание заказа и проверка компонентов", priority = 1, testName = "Создание заказа",
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

    @Test(description = "Проверка сортировки товаров по алфавиту", priority = 2, testName = "Сортировка товаров по алфавиту")
    public void sortAscendingOrder() {
        List<String> actualOrder = new LoginPage()
                .login(LOGIN, PASSWORD)
                .sortProducts(0)
                .getProductAttributes("names");

        List<String> ascOrder = actualOrder
                .stream()
                .sorted()
                .collect(Collectors.toList());

        assertEquals(actualOrder, ascOrder, "Товары не отсортированы в алфавитном порядке");
    }

    @Test(description = "Проверка сортировки товаров по алфавиту в обратном порядке", priority = 2,
            testName = "Обратная сортировка товаров по алфавиту")
    public void sortDescendingOrder() {
        List<String> actualOrder = new LoginPage()
                .login(LOGIN, PASSWORD)
                .sortProducts(1)
                .getProductAttributes("names");

        List<String> descOrder = actualOrder
                .stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        assertEquals(actualOrder, descOrder, "Товары не отсортированы в обратном алфавитном порядке");
    }

    @Test(description = "Проверка сортировки товаров по цене", priority = 2,
            testName = "Сортировка товаров по цене")
    public void sortPriceAscendingOrder() {
        List<String> actualOrder = new LoginPage()
                .login(LOGIN, PASSWORD)
                .sortProducts(2)
                .getProductAttributes("prices");

        List<String> ascOrder = actualOrder
                .stream()
                .mapToDouble(Double::parseDouble)
                .sorted()
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        assertEquals(actualOrder, ascOrder, "Товары не отсортированы по цене");
    }

    @Test(description = "Проверка сортировки товаров по цене в обратном порядке", priority = 2,
            testName = "Сортировка товаров по цене в обратном порядке")
    public void sortPriceDescendingOrder() {
        List<String> actualOrder = new LoginPage()
                .login(LOGIN, PASSWORD)
                .sortProducts(3)
                .getProductAttributes("prices");

        List<String> descOrder =  actualOrder
                .stream()
                .mapToDouble(Double::parseDouble)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .map(String::valueOf)
                .collect(Collectors.toList());


        assertEquals(actualOrder, descOrder, "Товары не отсортированы по цене в обратном порядке");
    }

}
