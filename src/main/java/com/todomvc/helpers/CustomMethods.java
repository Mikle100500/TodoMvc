package com.todomvc.helpers;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class CustomMethods {

    public static void doubleClick(SelenideElement element, WebDriver driver){

        Actions action = new Actions(driver);
        action.doubleClick(element).perform();
    }
}
