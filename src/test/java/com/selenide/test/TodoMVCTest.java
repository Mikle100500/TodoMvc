package com.selenide.test;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static java.lang.Thread.sleep;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TodoMVCTest extends BaseTest {

    @Test
    public void test1TaskCreator() throws InterruptedException {

        testEntity.tasksCreator(4);

    }

    @Test
    public void test2DeleteTask() throws InterruptedException {

        testEntity.deleteTask("task2");
        sleep(1500);
    }

    @Test
    public void test3MarkTheTaskAsCompleted() throws InterruptedException {

        testEntity.clickAll();
        sleep(3000);
        testEntity.markTaskAsCompleted("task4");
        sleep(3000);
    }

    @Test
    public void test4DeleteCompleted() throws InterruptedException{

        testEntity.clearCompleted();
        sleep(3000);
    }

    @Test
    public void test5MarkAndDelete() throws InterruptedException{

        testEntity.markTaskAsCompleted();
        sleep(3000);
        testEntity.clearCompleted();
        sleep(3000);

    }

}