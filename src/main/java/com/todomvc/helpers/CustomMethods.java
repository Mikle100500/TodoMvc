package com.todomvc.helpers;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class CustomMethods {

    public static SelenideElement doubleClick(String text, WebDriver driver){
        WebElement findText = driver.findElement(By.linkText(text));
        Actions action = new Actions(driver);
        action.moveToElement(findText).doubleClick().perform();
        return ;
    }
}
