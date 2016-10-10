package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

public class TaskBuilder {

    private String taskName;
    private TaskStatus taskStatus;

    public TaskBuilder(String taskName, TaskStatus taskStatus){

        this.taskName = taskName;
        this.taskStatus = taskStatus;
    }

    public String getTaskName() {
        return taskName;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public static TaskBuilder buildTask(TaskStatus taskStatus, String taskName){
        return new TaskBuilder(taskName, taskStatus);
    }

    public static List<TaskBuilder> buildTask(TaskStatus taskStatus, String... taskNames){

        List<TaskBuilder> tasks = new ArrayList<TaskBuilder>();

        for (String task : taskNames) {
            tasks.add(new TaskBuilder(task, taskStatus));
        }
        return tasks;
    }
}
