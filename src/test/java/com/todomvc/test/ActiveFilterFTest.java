package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import com.todomvc.testconfigs.BaseTest;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.GivenHelpers.given;
import static com.todomvc.helpers.Task.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;

public class ActiveFilterFTest extends BaseTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testEdit(){

        given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testDelete(){

        given(build(ACTIVE, "a", "b"));
        page.filterActive();

        page.delete("a");
        page.assertVisibleTasks("b");

    }

    @Test
    public void testComplete(){

        given(build(ACTIVE, "a", "b"));
        page.filterActive();

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);

    }

    @Test
    public void testActivateAll(){

        given(build(ACTIVE, "a", "b", "c", "d"));
        page.filterActive();

        page.toggleAll();
        page.assertNoVisibleTasks();

    }


    @Test
    public void testClearCompleted(){

        given(build(COMPLETED, "a", "b", "c", "d"));
        page.filterActive();

        page.clearCompleted();
        page.assertNoVisibleTasks();

    }

    @Test
    public void testEditWithTab(){

        given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        given(build(ACTIVE, "a"));
        page.filterActive();

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }
}
