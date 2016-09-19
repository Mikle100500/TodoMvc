package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;

import java.lang.String;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManagerPage {

    public ElementsCollection tasks = $$("#todo-list>li");

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