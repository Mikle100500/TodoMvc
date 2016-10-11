package com.todomvc.test;

import org.junit.Test;

import static com.todomvc.helpers.TaskBuilder.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;


public class AllFilterFTest extends BaseTest {

    @Test
    public void testActivate(){

        page.given(build(ACTIVE, "a", "b", "c"));

        page.toggle("b");
        page.assertItemsLeft(2);
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


}
