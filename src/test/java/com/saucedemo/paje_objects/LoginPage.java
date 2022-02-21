package com.saucedemo.paje_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.saucedemo.TestData.LOGIN_PAGE;

public class LoginPage {

    private static SelenideElement loginField = $x("//input[@id='user-name']");
    private static SelenideElement passwordField = $x("//input[@id='password']");
    private static SelenideElement submitButton = $x("//input[@id='login-button']");

    public LoginPage() {
        open(LOGIN_PAGE);
    }

    public void login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
    }

}
