package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

public class TaskBuilder {

    private String name;
    private TaskStatus status;

    public TaskBuilder(String name, TaskStatus status){

        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public static TaskBuilder build(String taskName, TaskStatus taskStatus){
        return new TaskBuilder(taskName, taskStatus);
    }

    public static List<TaskBuilder> build(TaskStatus taskStatus, String... taskNames){

        List<TaskBuilder> tasks = new ArrayList<TaskBuilder>();

        for (String task : taskNames) {
            tasks.add(new TaskBuilder(task, taskStatus));
        }

        return tasks;
    }
}
