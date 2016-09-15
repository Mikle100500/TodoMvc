package com.selenide.test;

import com.selenide.core.TaskManager;
import org.junit.Test;

/**
 * Created by Mikle on 15.07.2016.
 */
public class TodoMVCTest extends BaseTest {

    @Test
    public void testTasksCreator() throws InterruptedException {
        TaskManager createTask = new TaskManager();
        createTask.tasksCreator(5);
    }
}