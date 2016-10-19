package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class GivenHelpers {

    private static void ensureURL() {

        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }
    }

    public static void given(){

        ensureURL();
        Selenide.executeJavaScript("localStorage.clear()");
        Selenide.refresh();
    }

//    public static void given(List<Task> tasks, String navigateToFilter) {
//
//        ensureURL();
//        Selenide.executeJavaScript("localStorage.clear()");
//
//        String queryToExecute = "localStorage.setItem('todos-troopjs','[";
//        String queryBuilder = "";
//
//        for (Task task : tasks) {
//            queryBuilder += "{\"completed\":"
//                    + task.status
//                    + ",\"title\":\""
//                    + task.name
//                    + "\"},";
//        }
//
//        queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";
//
//        Selenide.executeJavaScript(queryToExecute);
//        Selenide.refresh();
//
//        if (navigateToFilter.equals("All")) {
//            filterAll();
//
//        } else if (navigateToFilter.equals("Active")) {
//            filterActive();
//
//        } else if (navigateToFilter.equals("Completed")) {
//            filterCompleted();
//        }
//
//    }
//
//    public static List<Task> build(TaskStatus taskStatus, String... taskNames) {
//
//        List<Task> tasks = new ArrayList<Task>();
//
//        for (String task : taskNames) {
//            tasks.add(new Task(taskStatus, task));
//        }
//
//        return tasks;
//    }

    public static class Task {

        private String name;
        private TaskStatus status;

        public Task(TaskStatus status, String name) {

            this.name = name;
            this.status = status;
        }

    }

    public enum TaskStatus{

        ACTIVE("false"),
        COMPLETED("true");

        private final String status;

        TaskStatus(String status){
            this.status = status;
        }

        @Override
        public String toString(){
            return status;
        }
    }
}
