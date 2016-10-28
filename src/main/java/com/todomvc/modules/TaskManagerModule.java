package com.todomvc.modules;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManagerModule {

    private static ElementsCollection tasks = $$("#todo-list>li");
    public static final SelenideElement newTodo = $("#new-todo");

    @Step
    public static void create(String... taskNames) {

        for (String name : taskNames) {
            newTodo.setValue(name).pressEnter();
        }
    }

    @Step
    public static void toggle(String taskName) {
        tasks.findBy(exactText(taskName)).find(".toggle").click();
    }

    @Step
    public static void toggleAll() {
        $("#toggle-all").click();
    }

    @Step
    public static void clearCompleted() {
        $("#clear-completed").click();
    }

    @Step
    public static void delete(String taskName) {
        tasks.findBy(exactText(taskName)).hover().find(".destroy").click();
    }

    public static void assertTasks(String... taskNames) {
        tasks.shouldHave(exactTexts(taskNames));
    }

    public static void assertVisibleTasks(String... taskNames) {
        tasks.filter(visible).shouldHave(exactTexts(taskNames));
    }

    public static void assertNoVisibleTasks() {
        tasks.filter(visible).shouldBe(empty);
    }

    public static void assertItemsLeft(int itemsLeft) {
        $("#todo-count>strong").shouldHave(exactText(Integer.toString(itemsLeft)));
    }

    @Step
    public static SelenideElement startEdit(String oldTaskName, String newTaskName) {

        tasks.find(exactText(oldTaskName)).doubleClick();
        return tasks.find(cssClass("editing")).$(".edit").setValue(newTaskName);
    }

    @Step
    public static void filterAll() {
        $(By.linkText("All")).click();
    }

    @Step
    public static void filterActive() {
        $(By.linkText("Active")).click();
    }

    @Step
    public static void filterCompleted() {
        $(By.linkText("Completed")).click();
    }
}