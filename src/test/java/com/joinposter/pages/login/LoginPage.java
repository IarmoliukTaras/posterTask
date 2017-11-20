package com.joinposter.pages.login;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "email")
    public SelenideElement email;

    @FindBy(id = "password")
    public SelenideElement password;

    @FindBy(className = "checkbox")
    public SelenideElement checkbox;

    @FindBy(className = "pull-left")
    public SelenideElement enter;

    @FindBy(className = "forgot-password")
    public SelenideElement forgotPassword;

    private static String url = "https://qa-auto-iarmoliuk.joinposter.com/manage/login";

    public static String getUrl() {
        return url;
    }

    public void logIn() {
        email.setValue("iarmoliuktaras@gmail.com");
        password.setValue("123456qwerty");
        enter.click();
    }

    public void logIn(String email, String password) {
        this.email.setValue(email);
        this.password.setValue(password);
        enter.click();

    }

}
