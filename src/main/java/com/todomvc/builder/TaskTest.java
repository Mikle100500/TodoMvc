package com.todomvc.builder;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.builder.Task.given;


public class TaskTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testAll() {

        given().activeTasksRedo("a", "b").atAllFilter().build();
        page.assertVisibleTasks("a", "b");

    }

    @Test
    public void testActive() {

        given().activeTasksRedo("c", "d").atActiveFilter().build();
        page.assertVisibleTasks("c", "d");

    }

    @Test
    public void testCompleted() {

        given().completedTasksRedo("g", "h").atCompletedFilter().build();
        page.assertVisibleTasks("g", "h");

    }
}
