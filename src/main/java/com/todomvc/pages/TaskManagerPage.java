package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import java.lang.String;
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
        tasks.findBy(exactText(taskName)).find(".toggle").click();
    }

    public void toggleAll() {
        $("#toggle-all").click();
    }

    public void clearCompleted() {
        $("#clear-completed").click();
    }

    public void delete(String taskName) {
        tasks.findBy(exactText(taskName)).hover().find(".destroy").click();
    }

    public void assertTasks(String... taskNames) {
        tasks.shouldHave(exactTexts(taskNames));
    }

    public void assertVisibleTasks(String... taskNames) {
        tasks.filter(visible).shouldHave(exactTexts(taskNames));
    }

    public void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    public void assertItemsLeft(int itemsLeft){
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeft)));
    }

    public SelenideElement startEdit(String oldTaskName, String newTaskName) {

        tasks.find(exactText(oldTaskName)).doubleClick();
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