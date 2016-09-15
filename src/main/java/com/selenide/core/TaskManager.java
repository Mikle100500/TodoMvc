package com.selenide.core;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Mikle on 14.09.2016.
 */
public class TaskManager {

    public void tasksCreator(int numOfTasks) {
        for (int i = 1; i >= numOfTasks; i++) {
            $("#new-todo").setValue("task" + i).pressEnter();
        }
    }

    public void markTaskAsCompleted(String teskName) {
        // TODO: 14.09.2016

    }

    // this overloaded method marks all tasks as completed
    public void markTaskAsCompleted() {
        // TODO: 14.09.2016

    }

    public void clearCompleted() {
        // TODO: 14.09.2016
    }

    public void deleteTask(String taskName) {

    }

}