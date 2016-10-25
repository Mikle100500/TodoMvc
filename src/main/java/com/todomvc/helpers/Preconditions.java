package com.todomvc.helpers;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Preconditions {

    private final List<String> taskNames;
    private final List<String> taskStatus;
    private final String filter;
    private final static String URL = "https://todomvc4tasj.herokuapp.com";

    private Preconditions(PreconditionBuilder builder) {

        this.taskNames = builder.taskNames;
        this.taskStatus = builder.taskStatus;
        this.filter = builder.filter;
    }

    public static PreconditionBuilder precondition() { return new PreconditionBuilder(); }

    public void prepare() {

        if (!url().equals(URL)) {
            open(URL);
            executeJavaScript("localStorage.clear()");
        }

        if (!taskNames.isEmpty()) {

            String queryToExecute = "";

            for (int i = 0; i < taskNames.size(); i++) {

                queryToExecute += "{\"completed\":"
                        + taskStatus.get(i)
                        + ",\"title\":\""
                        + taskNames.get(i)
                        + "\"},";
            }

            queryToExecute = "localStorage.setItem('todos-troopjs','["
                    + queryToExecute.substring(0, queryToExecute.length() - 1)
                    + "]')";

            executeJavaScript(queryToExecute);
        }

        open(filter);
        executeJavaScript("location.reload()");
    }

    public static class PreconditionBuilder {

        private List<String> taskNames;
        private List<String> taskStatus;
        private String filter = URL;


        public PreconditionBuilder() {

            taskNames = new ArrayList<String>();
            taskStatus = new ArrayList<String>();
        }

        public void prepare() { build().prepare(); }

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

            this.filter = URL + "/#";
            return this;
        }

        public PreconditionBuilder atActiveFilter() {

            this.filter = URL + "/#/active";
            return this;
        }

        public PreconditionBuilder atCompletedFilter() {

            this.filter = URL + "/#/completed";
            return this;
        }

        public Preconditions build() { return new Preconditions(this); }
    }
}

