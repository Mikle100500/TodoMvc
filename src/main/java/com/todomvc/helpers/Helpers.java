package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.List;

public class Helpers {


    // localStorage.setItem('todos-troopjs','[
    // {"completed":true,"title":"a"},

    // {"active":false,"title":"b"},


    // {"completed":true,"title":"c"}
    // ]')


    //localStorage.setItem('todos-troopjs','[{"completed":ACTIVE,"title":test}]')

    public void given(TaskBuilder task) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','["
                + "{\"completed\":"
                + task.getStatus()
                + ",\"title\":\""
                + task.getName()
                + "\"}]')";

        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }

    // redo
    public void given(List<TaskBuilder> tasks) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','";
        String queryBuilder = "[";

        for (TaskBuilder task : tasks) {
            queryBuilder += "{\"completed\":"
                    + task.getStatus()
                    + ",\"title\":\""
                    + task.getName()
                    + "\"},";
        }
        System.out.print("Query: " + queryBuilder.substring(0, queryBuilder.length() - 1));
        queryBuilder.substring(0, queryBuilder.length() - 1);
        queryToExecute = queryToExecute + queryBuilder + "]')";

        System.out.print("Query is: " + queryToExecute);
        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }
}
