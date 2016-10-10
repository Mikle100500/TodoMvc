package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

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

        System.out.print("Query is: " + queryToExecute);
        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }

    public void given(TaskStatus statusOfAll, TaskBuilder... tasks) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','[";
        String queryBuilder = "{\"completed\":";

        for (TaskBuilder task : tasks) {
            queryBuilder += ","
                    + "{\"completed\":"
                    + ",\"title\":\""
                    + task.getName()
                    + "\"}";
        }

        queryToExecute = queryToExecute + queryBuilder + "]')";

        System.out.print("Query is: " + queryToExecute);
        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }
}
