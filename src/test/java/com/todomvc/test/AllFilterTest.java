package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.GivenHelpers.build;
import static com.todomvc.helpers.GivenHelpers.given;
import static com.todomvc.helpers.GivenHelpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.GivenHelpers.TaskStatus.COMPLETED;
import static com.todomvc.pages.TaskManagerPage.filterActive;


public class AllFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testActivate(){

        given(build(ACTIVE, "a", "b", "c"), "All");

        page.toggle("b");
        page.assertItemsLeft(2);

        filterActive();
        page.assertVisibleTasks("a", "c");
    }


    @Test
    public void testCompleteAll(){

        given(build(ACTIVE, "a", "b", "c"), "All");

        page.toggleAll();
        page.assertItemsLeft(0);
    }


    @Test
    public void testClearCompleted(){

        given(build(COMPLETED, "a", "b", "c"), "All");

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testActivateAll(){

        given(build(COMPLETED, "a", "b", "c"), "All");

        page.toggleAll();
        page.assertVisibleTasks("a", "b", "c");
    }

    @Test
    public void testCancelEditWithEsc(){

        given(build(ACTIVE, "a"), "All");

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
    }

    @Test
    public void testEditWithTab(){

        given(build(ACTIVE, "a"), "All");

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        given(build(ACTIVE, "a"), "All");

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        given(build(ACTIVE, "a"), "All");

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }

}
