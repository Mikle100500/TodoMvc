package com.todomvc.builder;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.builder.Task.given;


public class TaskTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testAtCompleted() {

        given().activeTasks("a", "b").completedTasks("c", "d").atCompletedFilter().build();
        page.assertVisibleTasks("a", "b", "c", "d");
        page.assertItemsLeft(2);
    }

    @Test
    public void testNoTasks() {

        given().build();
        page.assertNoVisibleTasks();

    }

    @Test
    public void testAtAll() {

        given().completedTasks("g", "h").activeTasks("ab", "bc").atAllFilter().build();
        page.assertVisibleTasks("ab", "bc", "g", "h");
        page.assertItemsLeft(2);

    }

    @Test
    public void testAtActive() {

        given().activeTasks("ab", "bc").atActiveFilter().build();
        page.assertVisibleTasks("ab", "bc");
        page.assertItemsLeft(2);

    }
}
