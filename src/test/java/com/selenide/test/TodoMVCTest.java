package com.selenide.test;

import com.selenide.core.TaskManager;
import org.junit.Test;


public class TodoMVCTest extends BaseTest {

    private TaskManager testEntity = new TaskManager();

    @Test
    public void testTaskCreator() throws InterruptedException {

        testEntity.tasksCreator(4);
        testEntity.clickToggleAll();
        testEntity.markTaskAsCompleted("task2");
        testEntity.deleteTask("task2");
        testEntity.markTaskAsCompleted();
    }

    @Test
    public void testMarkTaskAsCompleted() throws InterruptedException {

        testEntity.clickAll();
        testEntity.clickActive();
        testEntity.clickCompleted();
        testEntity.clearCompleted();
    }

}