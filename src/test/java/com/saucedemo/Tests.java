package com.saucedemo;

import com.saucedemo.paje_objects.LoginPage;
import core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.saucedemo.TestData.LOGIN;
import static com.saucedemo.TestData.PASSWORD;

public class Tests extends BaseTest {

    @Test(description = "Логин с валидными данными", priority = 1, testName = "Позитивный логин")
    public void positiveLogin() {
        LoginPage login = new LoginPage();
        login.login(LOGIN, PASSWORD);
        Assert.assertEquals($x("//span[text()='Products']").getText(), "PRODUCTS",
                "Ошибка при авторизации");
    }

}
