package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("task1", "task2", "task3", "task4");
        page.tasks.filter(exist).shouldHave(size(4));
        page.tasks.shouldHave(exactTexts("task1", "task2", "task3", "task4"));

        page.delete("task2");
        page.tasks.shouldHave(exactTexts("task1", "task3", "task4"));

        page.toggle("task4");
        page.clearCompleted();
        page.tasks.shouldHave(exactTexts("task1", "task3"));

        page.toggleAll();
        page.clearCompleted();
        page.tasks.shouldBe(empty);
    }
}