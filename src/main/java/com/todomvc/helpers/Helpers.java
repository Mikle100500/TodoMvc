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
                                + "{\"completed\":" + task.getTaskStatus() + ",\"title\":\"" + task.getTaskName()
                                +"\"}]')";

        System.out.print("Query is: " + queryToExecute);
        Selenide.executeJavaScript(queryToExecute);
        Selenide.refresh();
    }
}
