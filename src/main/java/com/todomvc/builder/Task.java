package com.todomvc.builder;

//given().build() - ни одной таски
//given().activeTasksRedo("a","b","c").completedTasksRedo("d", "e").atAllFilter().build() - разные таски на таком-то фильтре
//given()......build()

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Task {

    private final List<String> activeTasks;
    private final List<String> completedTasks;

    private Task(TaskBuilder builder) {

        this.activeTasks = builder.activeTasks;
        this.completedTasks = builder.completedTasks;
    }

    public static TaskBuilder given() {

        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }

        executeJavaScript("localStorage.clear()");

        return new TaskBuilder();
    }

    public static class TaskBuilder {

        private List<String> activeTasks;
        private List<String> completedTasks;

        public TaskBuilder() {

            activeTasks = new ArrayList<String>();
            completedTasks = new ArrayList<String>();
        }

        public TaskBuilder activeTasks(String... tasks) {

            for (String task : tasks) {
                this.activeTasks.add(task);
            }
            return this;
        }

        public TaskBuilder completedTasks(String... tasks) {

            for (String task : tasks) {
                this.completedTasks.add(task);
            }
            return this;
        }


        public TaskBuilder atAllFilter() {

            $("https://todomvc4tasj.herokuapp.com/#");

            return this;
        }

        public TaskBuilder atActiveFilter() {

            $("https://todomvc4tasj.herokuapp.com/#/active");

            return this;
        }

        public TaskBuilder atCompletedFilter() {

            $("https://todomvc4tasj.herokuapp.com/#/completed");

            return this;
        }

        public Task build() {

            String queryToExecute = "localStorage.setItem('todos-troopjs','[";
            String queryBuildActive = "";
            String queryBuildCompleted = "";

            for (String task : this.activeTasks) {
                queryBuildActive += "{\"completed\":false,\"title\":\""
                        + task
                        + "\"},";
            }

            for (String task : this.completedTasks) {
                queryBuildCompleted += "{\"completed\":true,\"title\":\""
                        + task
                        + "\"},";
            }

            queryToExecute = queryToExecute
                    + queryBuildActive  //.substring(0, queryBuildActive.length() - 1)
                    + queryBuildCompleted.substring(0, queryBuildCompleted.length() - 1)
                    + "]')";

            System.out.println(queryToExecute);
            executeJavaScript(queryToExecute);
            refresh();

            return new Task(this);
        }

    }

}
