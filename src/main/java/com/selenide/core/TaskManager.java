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

    public void markTaskAsCompleted(String taskName) {

        String labelXpath = "//label[contains(text()," + "'" + taskName + "'" + ")]/../input";
        $(By.xpath(labelXpath)).click();
    }

    // this overloaded method marks all tasks as completed
    public void markTaskAsCompleted() {

        for (SelenideElement label : $$(By.className("toggle"))) {
            label.click();
        }
    }

    public void clearCompleted() {

        $(By.id("clear-completed")).click();
    }

    public void deleteTask(String taskName) {

        String focusOnTheTask = "//*[contains(text()," + "'" + taskName + "'" + ")]/..";
        SelenideElement deleteButton = $(By.xpath(focusOnTheTask)).hover();
        String focusOnDelete = "//*[contains(text()," + "'" + taskName + "'" + ")]/../button";
        deleteButton.$(By.xpath(focusOnDelete)).click();
    }


    public void clickAll() {

        $(By.className("selected")).click();
    }
}