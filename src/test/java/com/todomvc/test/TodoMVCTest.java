package com.todomvc.test;

import com.todomvc.page.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("task1", "task2", "task3", "task4");
        page.delete("task2");
//        assert
        page.toggle("task4");
        page.clearCompleted();
//        assert
        page.toggleAll();
        page.clearCompleted();
    }
}