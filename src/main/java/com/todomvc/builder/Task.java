package com.todomvc.builder;

//given().build() - ни одной таски
//given().activeTasks("a","b","c").completedTasks("d", "e").atAllFilter().build() - разные таски на таком-то фильтре
//given()......build() - можно разные делать - в зависимости от потребностей

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Task {

    private final String[] activeTasks;
    private final String[] completedTasks;

    private Task(TaskBuilder builder){

        this.activeTasks = builder.activeTasks;
        this.completedTasks = builder.completedTasks;
    }

    public static class TaskBuilder {

        private String[] activeTasks;
        private String[] completedTasks;

        public static void given(){

            if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
                open("https://todomvc4tasj.herokuapp.com/");
            }

            Selenide.executeJavaScript("localStorage.clear()");
            Selenide.refresh();
        }


        public TaskBuilder activeTasks(String... activeTasks){

            this.activeTasks = activeTasks;
            return this;
        }

        public TaskBuilder completedTasks(String... completedTasks){

            this.completedTasks = completedTasks;
            return this;
        }




        public final void atAllFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/");
        }

        public final void atActiveFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/active");
        }

        public final void atCompletedFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/completed");
        }


        public Task build(){
            return new Task(this);
        }
    }

}
