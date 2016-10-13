package com.todomvc.builder;

//given().build() - ни одной таски
//given().activeTasks("a","b","c").completedTasks("d", "e").atAllFilter().build() - разные таски на таком-то фильтре
//given()......build() - можно разные делать - в зависимости от потребностей

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;

public class Task {

    private String name;

    private Task(TaskBuilder builder){
        this.name = builder.name;
    }

    public static class TaskBuilder {

        private String name;

        public TaskBuilder(String name){
            this.name = name;
        }

        public void given(){

            if (!url().equals("https://todomvc4tasj.herokuapp.com/")) {
                open("https://todomvc4tasj.herokuapp.com/");
            }

            Selenide.executeJavaScript("localStorage.clear()");
            Selenide.refresh();
        }

        public void activeTasks(String... taskNames){
            // TODO: 13/10/2016
        }

        public void completedTasks(String... taskNames){
            // TODO: 13/10/2016
        }

        public void atAllFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/");
        }

        public void atActiveFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/active");
        }

        public void atCompletedFilter(){
            $("https://todomvc4tasj.herokuapp.com/#/completed");
        }


        public Task build(){
            return new TaskBuilder(this);
        }
    }

}
