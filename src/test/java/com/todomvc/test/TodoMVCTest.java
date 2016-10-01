package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("a");
        page.startEdit("a", "a edited").pressEnter();
        page.toggle("a edited");
        page.assertTasks("a edited");

        page.filterActive();
        page.assertNoVisibleTasks();

        page.create("b");
        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertVisibleTasks("b");

        page.toggleAll();
        page.assertNoVisibleTasks();

        page.filterCompleted();
        page.assertVisibleTasks("a edited", "b");

        page.toggle("b");
        page.assertVisibleTasks("a edited");

        page.clearCompleted();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);

        page.filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();

        close();

    }

    @Test
    public void testActivateAll(){

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("a");
        page.toggleAll();
        page.filterCompleted();
        page.assertVisibleTasks("a");

        page.toggleAll();
        page.filterActive();
        page.assertVisibleTasks("a");

        close();
    }

    @Test
    public void testEditByTab(){

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("a");
        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");

        close();
    }

}