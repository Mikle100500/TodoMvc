package com.todomvc.builder;

//given().build() - ни одной таски
//given().activeTasks("a","b","c").completedTasks("d", "e").atAllFilter().build() - разные таски на таком-то фильтре
//given()......build()

import com.codeborne.selenide.Selenide;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Task {

    private List<String> activeTasks;
    private List<String> completedTasks;

    private Task(TaskBuilder builder){

        this.activeTasks = builder.activeTasks;
        this.completedTasks = builder.completedTasks;
    }

    public static TaskBuilder given(){

        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }

        Selenide.executeJavaScript("localStorage.clear()");

        return new TaskBuilder();
    }

    public static class TaskBuilder {

        private List<String> activeTasks;
        private List<String> completedTasks;

        public TaskBuilder(){

            activeTasks = new ArrayList<String>();
            completedTasks = new ArrayList<String>();
        }

        public TaskBuilder activeTasks(String... tasks){

            String queryToExecute = "localStorage.setItem('todos-troopjs','[";
            String queryBuilder = "";

            for (String task : tasks) {
                queryBuilder += "{\"completed\":false,\"title\":\""
                        + task
                        + "\"},";
            }

            queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";

            Selenide.executeJavaScript(queryToExecute);
            System.out.println("Active query: " + queryToExecute);
            Selenide.refresh();
            return this;
        }

        public TaskBuilder completedTasks(String... tasks){

            String queryToExecute = "localStorage.setItem('todos-troopjs','[";
            String queryBuilder = "";

            for (String task : tasks) {
                queryBuilder += "{\"completed\":true,\"title\":\""
                        + task
                        + "\"},";
            }

            queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";

            Selenide.executeJavaScript(queryToExecute);
            System.out.println("Completed query: " + queryToExecute);
            Selenide.refresh();
            return this;
        }

        public TaskBuilder atAllFilter(){

            $("https://todomvc4tasj.herokuapp.com/#");

            return this;
        }

        public TaskBuilder atActiveFilter(){

            open("https://todomvc4tasj.herokuapp.com/#/active");

            return this;
        }

        public TaskBuilder atCompletedFilter(){

            open("https://todomvc4tasj.herokuapp.com/#/completed");

            return this;
        }

        public Task build(){

            return new Task(this);
        }

    }

}
