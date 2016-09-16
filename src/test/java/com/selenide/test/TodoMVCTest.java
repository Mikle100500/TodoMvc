package com.selenide.test;

import org.junit.Test;

import static java.lang.Thread.sleep;


public class TodoMVCTest extends BaseTest {

    @Test
    public void testTaskCreator() throws InterruptedException {

        testEntity.tasksCreator(4);

    }

    @Test
    public void testDeleteTask() throws InterruptedException {

        testEntity.deleteTask("task2");
        sleep(1500);
    }

    @Test
    public void testMarkTheTaskAsCompleted() throws InterruptedException {

        testEntity.clickAll();
        sleep(3000);
        testEntity.markTaskAsCompleted("task4");
        sleep(3000);
    }

    @Test
    public void testDeleteCompleted() throws InterruptedException{

        testEntity.clearCompleted();
        sleep(3000);
    }

    @Test
    public void testMarkAndDelete() throws InterruptedException{

        testEntity.markTaskAsCompleted();
        sleep(3000);
        testEntity.clearCompleted();
        sleep(3000);

    }

}