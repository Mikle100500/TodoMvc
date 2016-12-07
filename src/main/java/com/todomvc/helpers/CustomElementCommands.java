package com.todomvc.helpers;


import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.actions;

public class CustomElementCommands {

    public static void doubleClick(SelenideElement element) {
        actions().doubleClick(element).perform();
    }
}
