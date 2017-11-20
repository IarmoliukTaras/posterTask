package com.joinposter.pages.finance.accounts;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.joinposter.containers.finance.ModalWindow;
import com.joinposter.containers.finance.accounts.AccountsTable;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;

public class AccountsPage {

    @FindBy(linkText = "Добавить счет")
    private SelenideElement addAccountBtn;

    @FindBy(css = ".form-search > button")
    private SelenideElement searchBtn;

    @FindBy(css = ".form-search > input")
    private SelenideElement searchTextField;

    @FindBy(css = "table")
    private AccountsTable table;

    @FindBy(css = ".alert")
    private SelenideElement alert;

    @FindBy(css = ".modal-dialog")
    private ModalWindow modalWindow;

    @FindBy(css = ".quantity")
    private SelenideElement quantity;


    public void checkAlert(String message) {
        alert.shouldHave(text(message));
    }

    public int quantity() {
        return Integer.valueOf(quantity.getText());
    }

    public void search(String text) {
        searchTextField.setValue(text);
    }

    public AccountsTable table() {
        return table;
    }

    public ModalWindow modalWindow() {
        return modalWindow;
    }

    public AccountEditorPage addAccount() {
        addAccountBtn.click();
        return Selenide.page(AccountEditorPage.class);
    }
}

