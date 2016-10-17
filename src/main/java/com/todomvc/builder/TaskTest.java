package com.todomvc.builder;

import org.junit.Test;


public class TaskTest {

    @Test
    public void testSetUp(){
        new Task.TaskBuilder()
                .activeTasks("a", "b")
                .completedTasks("c", "d")
                .atActiveFilter()
                .build();
    }
}
