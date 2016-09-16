package com.todomvc.page;

import com.codeborne.selenide.SelenideElement;
import org.apache.xpath.operations.String;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManagerPage {

    public void createTasks(List<String> taskNames) {

        for (String stringName : taskNames) {
            $("#new-todo").setValue(stringName.toString()).pressEnter();
        }
    }

    public void switchTask(String taskName) {

        java.lang.String labelXpath = "//label[contains(text()," + "'" + taskName + "'" + ")]/../input";
        $(By.xpath(labelXpath)).click();
    }

    // this overloaded method marks all tasks as completed
    public void switchTask() {

        for (SelenideElement label : $$(By.className("toggle"))) {
            label.click();
        }
    }

    public void clearCompleted() {

        $(By.id("clear-completed")).click();
    }

    public void deleteTask(String taskName) {

        java.lang.String focusOnTheTask = "//*[contains(text()," + "'" + taskName + "'" + ")]/..";
        SelenideElement deleteButton = $(By.xpath(focusOnTheTask)).hover();
        java.lang.String focusOnDelete = "//*[contains(text()," + "'" + taskName + "'" + ")]/../button";
        deleteButton.$(By.xpath(focusOnDelete)).click();
    }


    public void filterAll() {

        $(By.className("selected")).click();
    }
}