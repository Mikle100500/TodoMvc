package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("task1", "task2", "task3", "task4");
        assert $$("li[class=active]").size() == 4;

        page.delete("task2");
        assert $$("li[class=active]").size() == 3;

        page.toggle("task4");
        page.clearCompleted();
        assert $$("li[class=active]").size() == 2;

        page.toggleAll();
        page.clearCompleted();
        assert $$("li[class=active]").size() == 0;
    }
}