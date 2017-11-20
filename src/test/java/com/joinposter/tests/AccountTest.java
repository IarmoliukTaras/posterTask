package com.joinposter.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.joinposter.enums.AccountType;
import com.joinposter.enums.Currency;
import com.joinposter.pages.MainPage;
import com.joinposter.pages.finance.accounts.AccountEditorPage;
import com.joinposter.containers.finance.accounts.AccountRow;
import com.joinposter.pages.finance.accounts.AccountsPage;
import com.joinposter.containers.finance.accounts.AccountsTable;
import com.joinposter.pages.login.LoginPage;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.Assert.assertEquals;
import static com.codeborne.selenide.Condition.text;

/**
 * Класс для тестирование докумена "СЧЕТА" в разделе "ФИНАНСЫ".
 */

public class AccountTest {

    @BeforeClass
    public static void setup() {
        ChromeDriverManager.getInstance().setup();
        Configuration.browser = System.getProperty("browser", "chrome");
        open(LoginPage.getUrl(), LoginPage.class).logIn();

    }

    public AccountTest() {
        Selenide.page(MainPage.class).menu().goTo("Финансы", "Счета");
    }

    private AccountEditorPage editor() {
        return Selenide.page(AccountEditorPage.class);
    }

    private AccountsPage page() {
        return Selenide.page(AccountsPage.class);
    }

    private AccountsTable table() {
        return page().table();
    }

    /**
     * Создание счета.
     */
    @Test
    public void createNoCashAccount() {
        page().addAccount();
        editor().setName("NoCashAcc");
        editor().selectType(AccountType.NO_CASH);
        editor().selectCurrency(Currency.RUS);
        editor().setBalance("123133.00");
        editor().setPersentAcquiring("15.00");
        String snapshot = editor().listSnapshot();
        editor().save();
        page().checkAlert("Счет успешно добавлен");
        assertEquals("Информация в таблице отличается от той, которую вводили при создание счета",
                snapshot, table().getLastAccount().snapshot());
    }

    /**
     * Редактирование счета.
     */
    @Test
    public void editNoCashAccount() {
        page().addAccount();
        editor().setName("NoCashAccForEdit");
        editor().save();
        table().getLastAccount().edit();
        editor().setBalance("1234.00");
        String snapshot = editor().listSnapshot();
        editor().save();
        page().checkAlert("Счет успешно изменен");
        assertEquals("Информация в таблице отличается от той, которую вводили при создание счета",
                snapshot, table().getLastAccount().snapshot());
    }

    /**
     * Удаление счета.
     */
    @Test
    public void deleteAccount() {
        page().addAccount();
        editor().setName("ForDelete");
        editor().save();
        int rowsBeforeDelete = table().accounts().size();
        AccountRow row = table().getLastAccount();
        table().scrollToRow(row);
        String snapshot = row.snapshot();
        row.delete();
        page().modalWindow().title().shouldHave(text("Подтверждение"));
        String expectedText = String.format("Вы действительно хотите удалить счет «%s»?", row.name());
        page().modalWindow().body().shouldHave(text(expectedText));
        page().modalWindow().cancel().shouldHave(text("Отменить")).shouldBe(visible);
        page().modalWindow().ok().click();
        sleep(1000);
        page().checkAlert("Счет успешно удален");
        assertEquals("Документ - \" " + snapshot + "\" не был удален.",
                rowsBeforeDelete - 1, table().accounts().size());
    }

    /**
     * Поиск счета.
     */
    @Test
    public void search() {
        String searchWord = "ForSearch";
        page().addAccount();
        editor().setName(searchWord);
        editor().save();
        page().search(searchWord);
        sleep(1000);
        for (AccountRow row : table().accounts()) {
            if (row.getSelf().isDisplayed()) {
                if (!row.snapshot().contains(searchWord)) {
                    Assert.fail("Счет - \"" + row.snapshot() + "\" не долежн отображатся в таблице. Текст поиска - "
                            + searchWord);
                }
            }
        }
    }

    /**
     * Проверка информации о количестве счетов.
     */
    @Test
    public void checkQuantity() {
        assertEquals("Информация о количестве счетов в таблице неверна", page().quantity(), table().accounts().size());
    }

    @After
    public void afterMethod() {
        if ($(".modal").is(visible)) {
            $(".modal > .close").click();
        }
        $(".logo").click();
        sleep(1000);
    }

    @AfterClass
    public static void close() {
        Selenide.close();
    }

}
