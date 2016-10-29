package com.todomvc.testmodule;

import com.codeborne.selenide.Selenide;

import static com.todomvc.modules.TaskManagerModule.*;

import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;

public class AtAllFilterModuleTest {

    @Test
    public void testCreate() {

        precondition().atAllFilter().prepare();

        create("a");
        assertTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testEdit() {

        precondition().activeTasks("a").atAllFilter().prepare();

        startEdit("a", "a edited").pressEnter();
        assertTasks("a edited");
        assertItemsLeft(1);
    }

    @Test
    public void testDelete() {

        precondition().activeTasks("a", "a to delete").atAllFilter().prepare();

        delete("a to delete");
        assertTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testComplete() {

        precondition().activeTasks("a", "b").atAllFilter().prepare();

        toggle("a");
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testActivate() {

        precondition().completedTasks("a").atAllFilter().prepare();

        toggle("a");
        assertTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {

        precondition().activeTasks("a", "b").atAllFilter().prepare();

        toggleAll();
        assertTasks("a", "b");
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a", "b", "c").atAllFilter().prepare();

        clearCompleted();
        assertNoVisibleTasks();
    }

    @Test
    public void testSwitchToActive() {

        precondition().activeTasks("a").completedTasks("b").activeTasks("c").atAllFilter().prepare();

        filterActive();
        assertVisibleTasks("a", "c");
        assertItemsLeft(2);
    }

    @Test
    public void testSwitchToCompleted() {

        precondition().activeTasks("a", "b").completedTasks("c", "d").atAllFilter().prepare();

        filterCompleted();
        assertVisibleTasks("c", "d");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditByEsc() {

        precondition().completedTasks("a").atAllFilter().prepare();

        startEdit("a", "a edited").pressEscape();
        assertVisibleTasks("a");
        assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditByTab() {

        precondition().completedTasks("b").atAllFilter().prepare();

        startEdit("b", "b edited").pressTab();
        assertTasks("a edited", "b edited");
        assertItemsLeft(1);
    }
}
