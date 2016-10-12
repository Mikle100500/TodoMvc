package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import com.todomvc.testconfigs.BaseTest;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.GivenHelpers.given;
import static com.todomvc.helpers.GivenHelpers.Task.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;

public class ActiveFilterTest extends BaseTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testEdit(){

        given(build(ACTIVE, "a"), "Active");

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testDelete(){

        given(build(ACTIVE, "a", "b"), "Active");

        page.delete("a");
        page.assertVisibleTasks("b");

    }

    @Test
    public void testComplete(){

        given(build(ACTIVE, "a", "b"), "Active");

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);

    }

    @Test
    public void testActivateAll(){

        given(build(ACTIVE, "a", "b", "c", "d"), "Active");

        page.toggleAll();
        page.assertNoVisibleTasks();

    }


    @Test
    public void testClearCompleted(){

        given(build(COMPLETED, "a", "b", "c", "d"), "Active");

        page.clearCompleted();
        page.assertNoVisibleTasks();

    }

    @Test
    public void testEditWithTab(){

        given(build(ACTIVE, "a"), "Active");

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testEditWithClick(){

        given(build(ACTIVE, "a"), "Active");

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
    }


    @Test
    public void testDeleteWithEmptying(){

        given(build(ACTIVE, "a"), "Active");

        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();
    }
}