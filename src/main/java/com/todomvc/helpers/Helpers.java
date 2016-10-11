package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.List;

public class Helpers {

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

    public void given(List<TaskBuilder> tasks) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','[";
        String queryBuilder = "";

        for (TaskBuilder task : tasks) {
            queryBuilder += "{\"completed\":"
                    + task.getStatus()
                    + ",\"title\":\""
                    + task.getName()
                    + "\"},";
        }

        queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";

        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }
}
