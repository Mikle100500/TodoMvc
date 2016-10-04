package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;


public class TodoMVCTest extends BaseTest {

    private TaskManagerPage page = new TaskManagerPage();


    @Test
    public void testTasksCommonFlow() {

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
    public void testEditByTab() {

        //given
        page.create("a");

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testCancelEditByEsc() {

        //given
        page.create("b");
        page.filterActive();

        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    // extra coverage
    @Test
    public void testActivateAllAtCompleted() {

        //given - completed task
        page.create("a", "b", "c", "d");
        page.toggleAll();
        page.filterCompleted();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(4);

        page.filterActive();
        page.assertVisibleTasks("a", "b", "c", "d");
    }

}