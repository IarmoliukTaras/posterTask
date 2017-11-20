package com.joinposter.containers.finance.accounts;

import static com.codeborne.selenide.Condition.text;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import com.joinposter.enums.AccountType;
import org.openqa.selenium.support.FindBy;

public class AccountRow extends ElementsContainer {

    @FindBy(css = "td")
    private SelenideElement name;

    @FindBy(className = "center-cell")
    private SelenideElement type;

    @FindBy(className = "right-cell")
    private SelenideElement balance;

    @FindBy(linkText = "Ред.")
    private SelenideElement edit;

    @FindBy(css = ".edit-more")
    private SelenideElement editMore;

    public String name() {
        return name.getText();
    }

    public AccountType type() {
        return AccountType.getEnum(type.getText());
    }

    public String balance() {
        return amount() + currency();
    }

    public String amount() {
        String fullBalance = balance.getText().replace(',', '.');
        int index = fullBalance.lastIndexOf(" ");
        return index > 3 ? fullBalance.substring(0, index).replace(" ", "")
                : fullBalance.replace(" ", "");
    }

    public String currency() {
        String fullBalance = balance.getText();
        int index = fullBalance.lastIndexOf(" ");
        return index > 3 ? fullBalance.substring(index) : "";
    }

    public void edit() {
        edit.click();
    }

    public void delete() {
        editMore.$("button").click();
        editMore.$$("li").findBy(text("Удалить")).click();
    }

    public String snapshot() {
        return name() + " " + type() + " " + balance();
    }


}
