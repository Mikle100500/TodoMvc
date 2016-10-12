package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

public class Task {

    private String name;
    private TaskStatus status;

    public Task(TaskStatus status, String name){

        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public static List<Task> build(TaskStatus taskStatus, String... taskNames){

        List<Task> tasks = new ArrayList<Task>();

        for (String task : taskNames) {
            tasks.add(new Task(taskStatus, task));
        }

        return tasks;
    }
}
