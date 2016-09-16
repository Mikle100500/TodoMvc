package com.todomvc.test;

import com.todomvc.page.TaskManagerPage;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class TodoMVCTest extends BaseTest {
    private TaskManagerPage testEntity = new TaskManagerPage();
    private List<String> listOfTasks = Arrays.asList("task1", "task2", "task3" , "task4");

    @Test
    public void testManageTask() throws InterruptedException {

        testEntity.createTasks(listOfTasks);
        testEntity.deleteTask("task2");
        testEntity.filterAll();
        testEntity.switchTask("task4");
        testEntity.clearCompleted();
        testEntity.switchTask();
        testEntity.clearCompleted();
    }
}