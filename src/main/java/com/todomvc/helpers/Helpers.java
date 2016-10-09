package com.todomvc.helpers;

import com.codeborne.selenide.Selenide;

import java.util.HashMap;
import java.util.Map;

public class Helpers {

    private final Map<String, String> status = new HashMap<String, String>() {{

        put("All", "");
        put("Active", "false");
        put("Completed", "true");
    }};


    //localStorage.setItem("todos-troopjs", "[{\"active\":false, \"title\":\"new\"}]", "[{\"active\":false, \"title\":\"newNew\"}]" )
    // "[{\"active\":false, \"title\":\"new\"}]"

    public void given(String taskStatus, String... taskNames) {

        StringBuilder queryToExecute = new StringBuilder("localStorage.setItem(\"todos-troopjs\", \"[{\\\"active\\\":");

        for (String name : taskNames) {

            queryToExecute.append(status.get(taskStatus)).append(", \\\"title\\\":\\\"").append(name).append("\\\"}]\")");
        }

        Selenide.executeJavaScript(queryToExecute.toString());
        Selenide.refresh();
    }
}
