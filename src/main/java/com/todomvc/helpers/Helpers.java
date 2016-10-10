package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

public class Helpers {



    // localStorage.setItem('todos-troopjs','[
    // {"completed":true,"title":"a"},
    // {"active":false,"title":"b"},
    // {"completed":true,"title":"c"}
    // ]')



    public void given(String taskStatus, String... taskNames) {

        String queryToExecute = "localStorage.setItem('todos-troopjs','[";


        Selenide.executeJavaScript(queryToExecute.toString());
        Selenide.refresh();
    }
}
