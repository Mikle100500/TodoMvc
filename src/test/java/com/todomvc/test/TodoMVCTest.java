package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("task1", "task2", "task3", "task4");
        page.assertTasksAre("task1", "task2", "task3", "task4");

        page.delete("task2");
        page.assertTasksAre("task1", "task3", "task4");

        page.toggle("task4");
        page.clearCompleted();
        page.assertTasksAre("task1", "task3");

        page.toggleAll();
        page.clearCompleted();
        page.assertTasksEmpty();

        // Use case starts from here
        page.create("task1", "task2", "task3");
        page.edit("task1", "task10");
        page.assertTasksAre("task10", "task2", "task3");

        page.filterAll();
        assert page.countAll() == 3;

        page.toggle("task10");
        page.filterCompleted();
        assert page.countCompleted() == 1;

        page.filterActive();
        assert page.countActive() == 2;
    }
}