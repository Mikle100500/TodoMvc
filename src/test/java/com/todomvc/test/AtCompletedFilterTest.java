package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Task.given;


public class AtCompletedFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testDelete(){

        given().completedTasks("a", "b").atCompletedFilter().build();

        page.delete("a");
        page.assertVisibleTasks("b");
    }

    @Test
    public void testActivate(){

        given().completedTasks("a").atCompletedFilter().build();

        page.toggle("a");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }
}
