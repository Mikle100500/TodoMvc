package com.selenide.core;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManager {

    public void tasksCreator(int numOfTasks) {
        for (int i = 1; i <= numOfTasks; i++) {
            $(By.id("new-todo")).setValue("task" + i).pressEnter();
        }
    }

    public void markTaskAsCompleted(java.lang.String taskName) {
        java.lang.String labelXpath = "//label[contains(text(), " + taskName + ")]/../input";
        $(By.xpath(labelXpath)).click();

    }

    // this overloaded method marks all tasks as completed
    public void markTaskAsCompleted() {
        for (SelenideElement label : $$(By.className("toggle"))) {
            label.click();
        }

    }

    public void clearCompleted() {
        $(By.id("clear-completed")).hover().click();
    }

    public void deleteTask(java.lang.String taskName) {
        java.lang.String focusOnTheTask = "//label[contains(text(), " + taskName + ")]";
        SelenideElement deleteButton = $(By.xpath(focusOnTheTask)).hover();
        deleteButton.$(By.className("destroy")).click();
    }

    public void clickToggleAll() {
        $(By.id("toggle-all")).click();
    }

    public void clickAll() {
        $(By.className("selected")).click();
    }

    public void clickActive() {
        $(By.xpath("//a[contains(text(),'Active')]/..")).click();
    }

    public void clickCompleted() {
        $(By.xpath("//a[contains(text(),'Completed')]")).click();

    }

}