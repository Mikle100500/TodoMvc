package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Preconditions {

    private final List<String> taskNames;
    private final List<String> taskStatus;
    private String filter;


    private Preconditions(PreconditionBuilder builder) {

        this.taskNames = builder.taskNames;
        this.taskStatus = builder.taskStatus;
        this.filter = builder.filter;
    }

    public static PreconditionBuilder given() {
        return new PreconditionBuilder();
    }

    public static class PreconditionBuilder {

        private List<String> taskNames;
        private List<String> taskStatus;
        private String filter;

        public PreconditionBuilder() {

            taskNames = new ArrayList<String>();
            taskStatus = new ArrayList<String>();
        }

        public PreconditionBuilder activeTasks(String... tasks) {

            for (String task : tasks) {
                this.taskNames.add(task);
                this.taskStatus.add("false");
            }
            return this;
        }

        public PreconditionBuilder completedTasks(String... tasks) {

            for (String task : tasks) {
                this.taskNames.add(task);
                this.taskStatus.add("true");
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

            if (!this.taskNames.isEmpty()) {

                String queryToExecute = "";

                for (int i = 0; i < this.taskNames.size(); i++) {

                    queryToExecute += "{\"completed\":"
                            + this.taskStatus.get(i)
                            + ",\"title\":\""
                            + this.taskNames.get(i)
                            + "\"},";
                }

                queryToExecute = "localStorage.setItem('todos-troopjs','["
                        + queryToExecute.substring(0, queryToExecute.length() - 1)
                        + "]')";

                System.out.print(queryToExecute);
                executeJavaScript(queryToExecute);
            }

            executeJavaScript("location.reload()");
            open(this.filter);

            return new Preconditions(this);
        }
    }
}

