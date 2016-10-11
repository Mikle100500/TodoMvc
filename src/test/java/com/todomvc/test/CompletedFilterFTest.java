package com.todomvc.test;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.TaskBuilder.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;

public class CompletedFilterFTest extends BaseTest {

    @Test
    public void testCreate(){

        page.given(build("a", ACTIVE));
        page.filterCompleted();

        page.create("b");
        page.assertItemsLeft(2);
        page.assertNoVisibleTasks();
    }

    @Test
    public void testEdit(){

        page.given(build("a", COMPLETED));
        page.filterCompleted();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testDelete(){

        page.given(build(COMPLETED, "a", "b", "c"));
        page.filterCompleted();

        page.delete("a");
        page.assertVisibleTasks("b", "c");
    }

    @Test
    public void testCompleteAll(){

        page.given(build(ACTIVE, "a", "b", "c"));
        page.filterCompleted();

        page.toggleAll();
        page.assertVisibleTasks("a", "b", "c");
    }

    @Test
    public void testCancelEditWithEsc(){

        page.given(build(COMPLETED, "a"));
        page.filterCompleted();

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
    }

    @Test
    public void testEditWithTab(){

        page.given(build(COMPLETED, "a"));
        page.filterCompleted();

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        page.given(build(COMPLETED, "a"));
        page.filterCompleted();

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        page.given(build(COMPLETED, "a"));
        page.filterCompleted();

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }


}
