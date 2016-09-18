package com.todomvc.test;

import com.todomvc.page.TaskManagerPage;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();
    private List<String> listOfTasks = Arrays.asList("task1", "task2", "task3", "task4");

    @Test
    public void testTodoMvc() {
        open("https://todomvc4tasj.herokuapp.com/");
        page.create(listOfTasks);
        page.delete("task2");
        page.switchTask("task4");
        page.clearCompleted();
        page.switchAll();
        page.clearCompleted();
    }
}