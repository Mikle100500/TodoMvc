package com.selenide.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoMVCTest extends BaseTest {

    @Test
    public void test1TaskCreator() throws InterruptedException {

        testEntity.tasksCreator(4);
    }

    @Test
    public void test2DeleteTask() throws InterruptedException {

        testEntity.deleteTask("task2");
    }

    @Test
    public void test3MarkTheTaskAsCompleted() throws InterruptedException {

        testEntity.clickAll();
        testEntity.markTaskAsCompleted("task4");
    }

    @Test
    public void test4DeleteCompleted() throws InterruptedException{

        testEntity.clearCompleted();
    }

    @Test
    public void test5MarkAndDelete() throws InterruptedException{

        testEntity.markTaskAsCompleted();
        testEntity.clearCompleted();
    }

}