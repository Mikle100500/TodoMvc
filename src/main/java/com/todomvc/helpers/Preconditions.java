package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Preconditions {

    private final List<String> activeTasks;
    private final List<String> completedTasks;
    private String filter;


    private Preconditions(PreconditionBuilder builder) {

        this.activeTasks = builder.activeTasks;
        this.completedTasks = builder.completedTasks;
        this.filter = builder.filter;
    }

    public static PreconditionBuilder given() {
        return new PreconditionBuilder();
    }

    public static class PreconditionBuilder {

        private List<String> activeTasks;
        private List<String> completedTasks;
        private List<Task> taskList;
        private String filter;

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

            this.filter = "https://todomvc4tasj.herokuapp.com/#";
            return this;
        }

        public PreconditionBuilder atActiveFilter() {

            this.filter = "https://todomvc4tasj.herokuapp.com/#/active";
            return this;
        }

        public PreconditionBuilder atCompletedFilter() {

            this.filter = "https://todomvc4tasj.herokuapp.com/#/completed";
            return this;
        }

        public Preconditions build() {

            if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
                open("https://todomvc4tasj.herokuapp.com/");
            }

            if (!this.activeTasks.isEmpty()) {

                String queryBuildActive = "";

                for (String task : this.activeTasks) {

                    queryBuildActive += "{\"completed\":false,\"title\":\""
                            + task
                            + "\"},";
                }

                queryBuildActive = "localStorage.setItem('todos-troopjs','["
                        + queryBuildActive.substring(0, queryBuildActive.length() - 1)
                        + "]')";

                executeJavaScript(queryBuildActive);
            }

            if (!this.completedTasks.isEmpty()) {

                String queryBuildCompleted = "";

                for (String task : this.completedTasks) {

                    queryBuildCompleted += "{\"completed\":true,\"title\":\""
                            + task
                            + "\"},";
                }

                queryBuildCompleted = "localStorage.setItem('todos-troopjs','["
                        + queryBuildCompleted.substring(0, queryBuildCompleted.length() - 1)
                        + "]')";

                executeJavaScript(queryBuildCompleted);
            }

            executeJavaScript("location.reload()");
            open(this.filter);

            return new Preconditions(this);
        }
    }
}
