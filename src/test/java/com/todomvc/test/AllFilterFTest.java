package com.todomvc.test;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.TaskBuilder.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;


public class AllFilterFTest extends BaseTest {

    @Test
    public void testActivate(){

        page.given(build(ACTIVE, "a", "b", "c"));

        page.toggle("b");
        page.assertItemsLeft(2);

        page.filterActive();
        page.assertVisibleTasks("a", "c");
    }


    @Test
    public void testCompleteAll(){

        page.given(build(ACTIVE, "a", "b", "c"));

        page.toggleAll();
        page.assertItemsLeft(0);
    }


    @Test
    public void testClearCompleted(){

        page.given(build(COMPLETED, "a", "b", "c"));

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testActivateAll(){

        page.given(build(COMPLETED, "a", "b", "c"));

        page.toggleAll();
        page.assertVisibleTasks("a", "b", "c");
    }

    @Test
    public void testCancelEditWithEsc(){

        page.given(build(ACTIVE, "a"));

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
    }

    @Test
    public void testEditWithTab(){

        page.given(build(ACTIVE, "a"));

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        page.given(build(ACTIVE, "a"));

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        page.given(build(ACTIVE, "a"));

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }

}
