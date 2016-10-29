package com.todomvc.testmodule;

import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;
import static com.todomvc.modules.TaskManagerModule.*;


public class AtActiveFilterModuleTest {

    @Test
    public void testCreate() {

        precondition().activeTasks("b").atActiveFilter().prepare();

        create("a");
        assertVisibleTasks("b", "a");
        assertItemsLeft(2);
    }

    @Test
    public void testEdit() {

        precondition().activeTasks("b", "a").atActiveFilter().prepare();

        startEdit("a", "a edited").pressEnter();
        assertVisibleTasks("b", "a edited");
        assertItemsLeft(2);
    }

    @Test
    public void testDelete() {

        precondition().activeTasks("a").completedTasks("b").atActiveFilter().prepare();

        delete("a");
        assertNoVisibleTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testComplete() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        toggle("a");
        assertVisibleTasks("b");
        assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {

        precondition().activeTasks("a", "b", "c").atActiveFilter().prepare();

        toggleAll();
        assertNoVisibleTasks();
        assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a").activeTasks("b", "c").atActiveFilter().prepare();

        clearCompleted();
        assertVisibleTasks("b", "c");
        assertItemsLeft(2);
    }

    @Test
    public void testSwitchToAll() {

        precondition().activeTasks("a").completedTasks("b").atActiveFilter().prepare();

        filterAll();
        assertTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testSwitchToCompleted() {

        precondition().completedTasks("a", "b").activeTasks("c", "d").atActiveFilter().prepare();

        filterCompleted();
        assertVisibleTasks("a", "b");
        assertItemsLeft(2);
    }

    @Test
    public void testCancelEditByEsc() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        startEdit("b", "b edited").pressEscape();
        assertVisibleTasks("a", "b");
        assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditClickOutside() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        startEdit("a", "a edited");
        newTodo.click();
        assertVisibleTasks("a edited");
        assertItemsLeft(1);
    }
}
