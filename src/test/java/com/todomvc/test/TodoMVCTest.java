package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("a");
        page.startEdit("a", "a edited").pressEnter();
        page.toggle("a edited");
        page.assertTasksAre("a edited");

        page.filterActive();
        page.assertTasksEmpty();

        page.create("b");
        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertTasksAre("b");

        page.toggleAll();
        page.assertTasksEmpty();

        page.filterCompleted();
        page.assertTasksAre("a edited", "b");

        page.toggle("b");
        page.clearCompleted();
        page.assertTasksEmpty();
        page.assertItemsLeft(1);

        page.filterAll();
        page.delete("b");
        page.assertTasksEmpty();

    }
}