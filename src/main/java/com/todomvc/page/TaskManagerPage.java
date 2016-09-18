package com.todomvc.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.lang.String;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class TaskManagerPage {

    private ElementsCollection tasksSheet = $$(".view");

    public void create(List<String> taskNames) {

        for (String name : taskNames) {
            $("#new-todo").setValue(name).pressEnter();
        }
    }

    public void switchTask(String taskName) {

        tasksSheet.findBy(text(taskName)).find(".toggle").click();
    }

    public void switchAll() {

        for (SelenideElement label : tasksSheet) {
            label.$(".toggle").click();
        }
    }

    public void clearCompleted() {

        $("#clear-completed").click();
    }

    public void delete(String taskName) {

        tasksSheet.findBy(text(taskName)).hover().find(".destroy").click();
    }

}