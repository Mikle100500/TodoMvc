package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.List;

public class GivenHelpers {

    public static void given(List<Task> tasks) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','[";
        String queryBuilder = "";

        for (Task task : tasks) {
            queryBuilder += "{\"completed\":"
                    + task.getStatus()
                    + ",\"title\":\""
                    + task.getName()
                    + "\"},";
        }

        queryToExecute = queryToExecute + queryBuilder.substring(0, queryBuilder.length() - 1) + "]')";

        Selenide.executeJavaScript("localStorage.clear()");
        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }
}
