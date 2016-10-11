package com.todomvc.test;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.TaskBuilder.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;

public class ActiveFilterFTest extends BaseTest {

    @Test
    public void testEdit(){

        page.given(build("a", ACTIVE));
        page.filterActive();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testDelete(){

        page.given(build(ACTIVE, "a", "b"));
        page.filterActive();

        page.delete("a");
        page.assertVisibleTasks("b");

    }

    @Test
    public void testComplete(){

        page.given(build(ACTIVE, "a", "b"));
        page.filterActive();

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);

    }

    @Test
    public void testActivateAll(){

        page.given(build(ACTIVE, "a", "b", "c", "d"));
        page.filterActive();

        page.toggleAll();
        page.assertNoVisibleTasks();

    }


    @Test
    public void testClearCompleted(){

        page.given(build(COMPLETED, "a", "b", "c", "d"));
        page.filterActive();

        page.clearCompleted();
        page.assertNoVisibleTasks();

    }

    @Test
    public void testEditWithTab(){

        page.given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        page.given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        page.given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }
}
