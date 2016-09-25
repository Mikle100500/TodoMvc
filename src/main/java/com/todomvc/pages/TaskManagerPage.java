package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManagerPage {

    private ElementsCollection tasks = $$("#todo-list>li");

    public void create(String... taskNames) {

        for (String name : taskNames) {
            $("#new-todo").setValue(name).pressEnter();
        }
    }

    public void toggle(String... taskNames) {
        for (String name : taskNames) {
            if (isTasksVisible(name)) {
                tasks.findBy(exactText(name)).find(".toggle").click();
            }
        }
    }

    public void toggleAll() {

        $("#toggle-all").click();
    }

    public void clearCompleted() {

        $("#clear-completed").click();
    }

    public void delete(String... taskNames) {
        for (String name : taskNames) {
            if (isTasksVisible(name)) {
                tasks.findBy(exactText(name)).hover().find(".destroy").click();
            }
        }
    }

    public void assertTasksAre(String... taskNames) {

        if (isTasksVisible(taskNames)) {
            tasks.shouldHave(exactTexts(taskNames));
        }
    }

    public void assertTasksEmpty() {
        if (isTasksVisible()) {
            tasks.shouldBe(empty);
        }
    }

    public void edit(String oldTaskName, String newTaskName) {

        tasks.find(exactText(oldTaskName)).doubleClick();
        tasks.find(cssClass("editing")).$(".edit").setValue(newTaskName).pressEnter();
    }

    public void filterAll() {

        $(By.linkText("All")).click();
    }

    public void filterActive() {

        $(By.linkText("Active")).click();
    }

    public void filterCompleted() {

        $(By.linkText("Completed")).click();
    }

    public int count() {

        return tasks.filter(visible).size();

    }

    public boolean isTasksVisible(String... taskNames) {

        return tasks.filter(visible).contains(taskNames);
    }
}