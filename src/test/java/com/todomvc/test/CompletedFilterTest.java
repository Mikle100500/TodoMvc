package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.GivenHelpers.Task.build;
import static com.todomvc.helpers.GivenHelpers.given;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;
import static com.todomvc.pages.TaskManagerPage.filterAll;

public class CompletedFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate(){

        given(build(ACTIVE, "a"), "Completed");

        page.create("b");
        page.assertItemsLeft(2);
        page.assertNoVisibleTasks();
    }

    @Test
    public void testEdit(){

        given(build(COMPLETED, "a"), "Completed");

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testDelete(){

        given(build(COMPLETED, "a", "b", "c"), "Completed");

        page.delete("a");
        page.assertVisibleTasks("b", "c");
    }

    @Test
    public void testCompleteAll(){

        given(build(ACTIVE, "a", "b", "c"), "Completed");

        page.toggleAll();
        page.assertVisibleTasks("a", "b", "c");
    }

    @Test
    public void testNavigateToAll(){

        given(build(COMPLETED, "a", "b", "c", "d"), "Completed");

        filterAll();
        page.assertVisibleTasks("a", "b", "c", "d");
    }

    @Test
    public void testCancelEditWithEsc(){

        given(build(COMPLETED, "a"), "Completed");

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
    }

    @Test
    public void testEditWithTab(){

        given(build(COMPLETED, "a"), "Completed");

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        given(build(COMPLETED, "a"), "Completed");

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        given(build(COMPLETED, "a"), "Completed");

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }

}
