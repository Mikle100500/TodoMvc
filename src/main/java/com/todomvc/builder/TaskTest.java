package com.todomvc.builder;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.builder.Task.given;


public class TaskTest {

    TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testActive() {

        given().activeTasks("a", "b").atActiveFilter().build();
        page.assertVisibleTasks("a", "b");

    }

    @Test
    public void testCompleted() {

        given().completedTasks("a", "b").atCompletedFilter().build();
        page.assertVisibleTasks("a", "b");

    }
}
