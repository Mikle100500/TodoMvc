package com.todomvc.helpers;

public class Task {

    private String taskStatus;
    private String taskName;

    public Task(String taskStatus, String taskName){

        this.taskStatus = taskStatus;
        this.taskName = taskName;
    }

    public String getTaskStatus() {
        return taskStatus;
    }
}
