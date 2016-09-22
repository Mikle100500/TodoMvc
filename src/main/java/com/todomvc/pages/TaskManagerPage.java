package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
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

    public void assertTasksAre(String... taskNames){

        tasks.shouldHave(exactTexts(taskNames));
    }

    public void assertTasksEmpty(){

        tasks.shouldBe(empty);
    }

    public void edit(String oldName, String newName){

        tasks.find(exactText(oldName)).doubleClick();
        tasks.find(cssClass("active editing")).$(By.className("edit")).sendKeys(newName);

    }

    public void filterAll(){

        $(By.partialLinkText("#/")).click();

    }

    public void filterActive(){

        $(By.partialLinkText("#/active")).click();

    }

    public void filterCompleted(){

        $(By.partialLinkText("#/completed")).click();

    }


}