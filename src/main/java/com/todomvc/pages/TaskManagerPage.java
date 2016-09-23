package com.todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

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

    public void assertTasksAre(String... taskNames) {

        tasks.shouldHave(exactTexts(taskNames));
    }

    public void assertTasksEmpty() {

        tasks.shouldBe(empty);
    }

    public void edit(String oldName, String newName) {

        tasks.find(exactText(oldName)).doubleClick();
        SelenideElement editedTask = tasks.find(cssClass("editing")).$(By.className("edit"));
        editedTask.setValue(newName).sendKeys(Keys.ENTER);
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

    public int countActive() {

        return $$(".active").size();
    }

    public int countCompleted() {

        return $$(".completed").size();
    }

    public int countAll() {

        return $$("#todo-list>li").size();
    }

}