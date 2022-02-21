package com.saucedemo.paje_objects;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.saucedemo.TestData.LOGIN_PAGE;

public class LoginPage {

    private SelenideElement loginField = $x("//input[@id='user-name']");
    private SelenideElement passwordField = $x("//input[@id='password']");
    private SelenideElement submitButton = $x("//input[@id='login-button']");
    private SelenideElement errorMessage = $x("//h3[@data-test='error']");

    public LoginPage() {
        open(LOGIN_PAGE);
    }

    public ProductsPage login(String login, String password) {
        loginField.setValue(login);
        passwordField.setValue(password);
        submitButton.click();
        return new ProductsPage();
    }

    public String getErrorMessage() {
        return errorMessage.getOwnText();
    }

}
