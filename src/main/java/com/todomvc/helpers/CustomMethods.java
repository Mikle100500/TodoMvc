package com.todomvc.helpers;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.actions;

public class CustomMethods {

    public static void doubleClick(String oldTaskName, ElementsCollection tasks){
        SelenideElement element = tasks.find(exactText(oldTaskName)).find(By.tagName("label"));
        actions().moveToElement(element).doubleClick().perform();
    }
}
