package com.joinposter.containers.finance;

import com.codeborne.selenide.ElementsContainer;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class ModalWindow extends ElementsContainer {


    @FindBy(css = ".modal-title")
    private SelenideElement title;

    @FindBy(css = ".modal-body")
    private SelenideElement body;

    @FindBy(css = ".modal-footer > button")
    private SelenideElement cancel;

    @FindBy(css = ".btn-yes")
    private SelenideElement ok;

    public SelenideElement title() {
        return title;
    }

    public SelenideElement body() {
        return body;
    }

    public SelenideElement cancel() {
        return cancel;
    }

    public SelenideElement ok() {
        return ok;
    }
}
