package com.todomvc.builder;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.builder.Task.given;


public class TaskTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testAll() {

        given().activeTasks("a", "b").completedTasks("c", "d").atCompletedFilter().build();
        page.assertVisibleTasks("a", "b", "c", "d");
        page.assertItemsLeft(2);
    }
//
//    @Test
//    public void testActive() {
//
//        given().activeTasksRedo("c", "d").atActiveFilter().build();
//        page.assertVisibleTasks("c", "d");
//
//    }
//
//    @Test
//    public void testCompleted() {
//
//        given().completedTasksRedo("g", "h").atCompletedFilter().build();
//        page.assertVisibleTasks("g", "h");
//
//    }
}
