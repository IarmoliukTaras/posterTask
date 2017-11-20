package com.joinposter.pages;

import com.joinposter.containers.Menu;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(css = "ul.menu")
    private Menu menu;

    public Menu menu() {
        return menu;
    }
}
