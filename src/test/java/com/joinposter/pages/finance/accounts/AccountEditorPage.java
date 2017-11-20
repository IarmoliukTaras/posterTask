package com.joinposter.pages.finance.accounts;

import com.codeborne.selenide.SelenideElement;
import com.joinposter.enums.AccountType;
import com.joinposter.enums.Currency;
import org.openqa.selenium.support.FindBy;

public class AccountEditorPage {

    @FindBy(id = "name")
    private SelenideElement name;

    @FindBy(css = "select[name='currency_id']")
    private SelenideElement currency;

    @FindBy(css = "select[name='type']")
    private SelenideElement type;

    @FindBy(id = "balance_start")
    private SelenideElement balance;

    @FindBy(id = "percent-acquiring")
    private SelenideElement percentAcquiring;

    @FindBy(css = "input[type='submit']")
    private SelenideElement submitBtn;

    public String getName() {
        return name.getValue();
    }

    public Currency getCurrency() {
        return Currency.getEnum(currency.getText());
    }

    public AccountType getType() {
        return AccountType.getEnum(type.getText());
    }

    public String getBalance() {
        return balance.getValue().trim();
    }

    public String getPersentAcquiring() {
        return percentAcquiring.getText();
    }

    public void setName(String text) {
        name.setValue(text);
    }

    public void selectCurrency(Currency currency) {
        this.currency.selectOption(currency.toString());
    }

    public void selectType(AccountType type) {
        this.type.selectOption(type.getName());
    }

    public void setBalance(String value) {
        balance.setValue(value);
    }

    public void setPersentAcquiring(String value) {
        percentAcquiring.setValue(value);
    }


    public void save() {
        submitBtn.click();
    }

    public String listSnapshot() {
        String snapshot = getName() + " " + getType() + " " + getBalance();
        String currSymbol = getCurrency().getSymbol();

        return currSymbol.isEmpty() ? snapshot : snapshot + " " + currSymbol;
    }

}
