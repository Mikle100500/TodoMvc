package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Preconditions {

    private final List<String> activeTasks;
    private final List<String> completedTasks;

    private Preconditions(PreconditionBuilder builder) {

        this.activeTasks = builder.activeTasks;
        this.completedTasks = builder.completedTasks;
    }

    public static PreconditionBuilder given() {

        if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
            open("https://todomvc4tasj.herokuapp.com/");
        }

        executeJavaScript("localStorage.clear()");

        return new PreconditionBuilder();
    }

    public static class PreconditionBuilder {

        private List<String> activeTasks;
        private List<String> completedTasks;

        public PreconditionBuilder() {

            activeTasks = new ArrayList<String>();
            completedTasks = new ArrayList<String>();
        }

        public PreconditionBuilder activeTasks(String... tasks) {


            for (String task : tasks) {
                this.activeTasks.add(task);
            }
            return this;
        }

        public PreconditionBuilder completedTasks(String... tasks) {

            for (String task : tasks) {
                this.completedTasks.add(task);
            }
            return this;
        }

        public PreconditionBuilder atAllFilter() {

            open("https://todomvc4tasj.herokuapp.com/#");

            return this;
        }

        public PreconditionBuilder atActiveFilter() {

            open("https://todomvc4tasj.herokuapp.com/#/active");

            return this;
        }

        public PreconditionBuilder atCompletedFilter() {

            open("https://todomvc4tasj.herokuapp.com/#/completed");

            return this;
        }

        public Preconditions build() {

                String queryToExecute = "localStorage.setItem('todos-troopjs','[";
                String queryBuildActive = "";
                String queryBuildCompleted = "";

                if (!this.activeTasks.isEmpty()){

                    for (String task : this.activeTasks) {

                        queryBuildActive += "{\"completed\":false,\"title\":\""
                                + task
                                + "\"},";
                    }

                    queryBuildActive = queryBuildActive.substring(0, queryBuildActive.length() - 1);
                }

                if (!this.completedTasks.isEmpty()) {

                    for (String task : this.completedTasks) {

                        queryBuildCompleted += "{\"completed\":true,\"title\":\""
                                + task
                                + "\"},";
                    }

                    queryBuildCompleted = queryBuildCompleted.substring(0, queryBuildCompleted.length() - 1);
                }

                if (!this.activeTasks.isEmpty() & !this.completedTasks.isEmpty()) {

                    queryToExecute = queryToExecute
                            + queryBuildActive
                            + ","
                            + queryBuildCompleted
                            + "]')";
                }else {

                    queryToExecute = queryToExecute
                            + queryBuildActive
                            + queryBuildCompleted
                            + "]')";
                }


                executeJavaScript(queryToExecute);
                refresh();

            return new Preconditions(this);
        }

    }

}
