package com.todomvc.testmodule;

import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;
import static com.todomvc.pages.testmodule.TaskManager.*;


public class TodoMVCIntegrationModuleTest {


    @Test
    public void testTasksCommonFlow() {

        precondition().prepare();

        create("a");
        assertTasks("a");

        filterActive();
        assertVisibleTasks("a");

        create("b");
        toggleAll();
        assertNoVisibleTasks();

        filterCompleted();
        assertVisibleTasks("a", "b");

        toggle("b");
        assertVisibleTasks("a");

        clearCompleted();
        assertNoVisibleTasks();

        filterAll();
        delete("b");
        assertNoVisibleTasks();
    }
}