package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.GivenHelpers.given;
import static com.todomvc.pages.TaskManagerPage.*;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        given();

        page.create("a");
        page.assertTasks("a");

        filterActive();
        page.assertVisibleTasks("a");

        page.create("b");
        page.toggleAll();
        page.assertNoVisibleTasks();

        filterCompleted();
        page.assertVisibleTasks("a", "b");

        page.toggle("b");
        page.assertVisibleTasks("a");

        page.clearCompleted();
        page.assertNoVisibleTasks();

        filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();
    }


    @Test
    public void testEditAtAll() {

        given();
        page.create("a");

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCancelEditByEscAtActive() {

        given();
        page.create("b");
        filterActive();

        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    // extra coverage
    @Test
    public void testActivateAllAtCompleted() {

        given();
        page.create("a", "b", "c", "d");
        page.toggleAll();
        filterCompleted();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(4);

    }

}