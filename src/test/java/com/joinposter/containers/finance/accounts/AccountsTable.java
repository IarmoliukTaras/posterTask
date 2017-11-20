package com.joinposter.containers.finance.accounts;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccountsTable extends ElementsContainer {

    @FindBy(css = "tbody > tr")
    private List<AccountRow> accounts;

    @FindBy(css = "thead > tr")
    private ElementsCollection headers;

    public List<AccountRow> accounts() {
        return accounts;
    }

    public ElementsCollection headers() {
        return headers;
    }

    public AccountRow getFirstAccount() {
        checkTableOnEmpty();
        return accounts.get(0);
    }

    public AccountRow getLastAccount() {
        checkTableOnEmpty();
        return accounts.get(accounts.size() - 1);
    }

    private void checkTableOnEmpty() {
        if (accounts.isEmpty()) {
            Assert.fail("Таблица пуста");
        }
    }

    public void scrollToRow(AccountRow row) {
        JavascriptExecutor js = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        row.getSelf().scrollTo();
        js.executeScript("javascript:window.scrollBy(0,250)");
    }

}
