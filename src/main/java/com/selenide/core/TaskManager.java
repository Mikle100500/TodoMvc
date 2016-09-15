package com.selenide.core;

import org.apache.xpath.operations.String;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;


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
        // TODO: 14.09.2016

    }

    public void clearCompleted() {
        $(By.id("clear-completed")).click();
    }

    public void deleteTask(String taskName) {
        // TODO: 15/09/2016
    }

    public void clickToggleAll() {
        $(By.id("toggle-all")).click();
    }

    public void clickAll() {
        $(By.className("selected")).hover().click();
    }

    public void clickActive() {
        $(By.xpath("//a[contains(text(),'Active')]/..")).hover().click();
    }

    public void clickCompleted() {
        $(By.xpath("//a[contains(text(),'Completed')]")).hover().click();

    }

}