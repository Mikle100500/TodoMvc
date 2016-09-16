package com.selenide.test;

import com.selenide.core.TaskManager;
import org.junit.Test;

import static java.lang.Thread.sleep;


public class TodoMVCTest extends BaseTest {

    private TaskManager testEntity = new TaskManager();

    @Test
    public void testTaskCreator() throws InterruptedException {

        testEntity.tasksCreator(4);

    }

    @Test
    public void testDeleteTask() throws InterruptedException{

        testEntity.deleteTask("task2");
        sleep(2000); //being deleted later
    }

    @Test
    public void testMarkTheTaskAsCompleted() throws InterruptedException{


    }
}