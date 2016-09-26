package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
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

    public void toggle(String taskName) {

        tasks.filter(visible).findBy(exactText(taskName)).find(".toggle").click();
    }

    public void toggleAll() {

        $("#toggle-all").click();
    }

    public void clearCompleted() {

        $("#clear-completed").click();
    }

    public void delete(String... taskNames) {
        for (String name : taskNames) {

            tasks.filter(visible).findBy(exactText(name)).hover().find(".destroy").click();
        }
    }

    public void assertTasksAre(String... taskNames) {


        tasks.filter(visible).shouldHave(exactTexts(taskNames));

    }

    public void assertTasksEmpty() {

        tasks.filter(visible).shouldBe(empty);

    }

    public SelenideElement startEdit(String oldTaskName, String newTaskName) {

        tasks.filter(visible).find(exactText(oldTaskName)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskName);

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
}