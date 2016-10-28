package com.todomvc.testmodules;

import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;
import static com.todomvc.modules.TaskManagerModule.*;

public class AtCompletedFilterModuleTest {
    
    @Test
    public void testDelete() {

        precondition().completedTasks("a", "b").atCompletedFilter().prepare();

        delete("a");
        assertVisibleTasks("b");
        assertItemsLeft(0);
    }

    @Test
    public void testActivate() {

        precondition().completedTasks("a").atCompletedFilter().prepare();

        toggle("a");
        assertNoVisibleTasks();
        assertItemsLeft(1);
    }

    @Test
    public void testActivateAll() {

        precondition().completedTasks("a", "b").atCompletedFilter().prepare();

        toggleAll();
        assertNoVisibleTasks();
        assertItemsLeft(2);
    }

    @Test
    public void testClearCompleted() {

        precondition().completedTasks("a").atCompletedFilter().prepare();

        clearCompleted();
        assertNoVisibleTasks();
    }

    @Test
    public void testSwitchToAll() {

        precondition().completedTasks("a").activeTasks("b").completedTasks("c").atCompletedFilter().prepare();

        filterAll();
        assertTasks("a", "b", "c");
        assertItemsLeft(1);
    }

    @Test
    public void testSwitchToActive() {

        precondition().completedTasks("a", "b", "c").activeTasks("d", "e").atCompletedFilter().prepare();

        filterActive();
        assertVisibleTasks("d", "e");
        assertItemsLeft(2);
    }

    @Test
    public void testDeleteByEmptying() {

        precondition().completedTasks("a", "b").atCompletedFilter().prepare();

        startEdit("a", "").pressEnter();
        assertVisibleTasks("b");
        assertItemsLeft(0);
    }
}