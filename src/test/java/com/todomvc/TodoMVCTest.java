package com.todomvc;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        precondition().prepare();

        page.create("a");
        page.assertTasks("a");

        page.filterActive();
        page.assertVisibleTasks("a");

        page.create("b");
        page.toggleAll();
        page.assertNoVisibleTasks();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");

        page.toggle("b");
        page.assertVisibleTasks("a");

        page.clearCompleted();
        page.assertNoVisibleTasks();

        page.filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();
    }


    @Test
    public void testEditAtAll() {

        precondition().prepare();

        page.create("a");

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCancelEditByEscAtActive() {

        precondition().prepare();

        page.create("b");
        page.filterActive();

        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    // extra coverage
    @Test
    public void testActivateAllAtCompleted() {

        precondition().prepare();

        page.create("a", "b", "c", "d");
        page.toggleAll();
        page.filterCompleted();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(4);

    }

}