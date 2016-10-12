package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.ArrayList;
import java.util.List;

public class GivenHelpers {

    public static void given(List<Task> tasks, TaskStatus filter) {

        Selenide.executeJavaScript("localStorage.clear()");

        String queryToExecute = "localStorage.setItem('todos-troopjs','[";
        String queryBuilder = "";

        for (Task task : tasks) {
            queryBuilder += "{\"completed\":"
                    + task.status
                    + ",\"title\":\""
                    + task.name
                    + "\"},";
        }

        queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";

        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();

    }

    public static class Task {

        private String name;
        private TaskStatus status;

        public Task(TaskStatus status, String name){

            this.name = name;
            this.status = status;
        }

        public static List<Task> build(TaskStatus taskStatus, String... taskNames){

            List<Task> tasks = new ArrayList<Task>();

            for (String task : taskNames) {
                tasks.add(new Task(taskStatus, task));
            }

            return tasks;
        }
    }
}
