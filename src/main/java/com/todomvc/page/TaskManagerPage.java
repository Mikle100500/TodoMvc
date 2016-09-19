package com.todomvc.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.lang.String;

import java.util.List;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
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

}