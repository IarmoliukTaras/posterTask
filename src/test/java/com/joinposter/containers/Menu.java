package com.joinposter.containers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.not;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.sleep;


public class Menu extends ElementsContainer {

    @FindBy(css = "li")
    private ElementsCollection items;

    public void goTo(String section, String docName) {
        SelenideElement menuItem = items.findBy(text(section));
        if (menuItem.is(not(attribute("class", "active")))) {
            menuItem.$("span").click();
        }
        SelenideElement subMenuItem = menuItem.$$(".sub-menu > li").findBy(text(docName));
        subMenuItem.click();
        sleep(1000);
    }


}
