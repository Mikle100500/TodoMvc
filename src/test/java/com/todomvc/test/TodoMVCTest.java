package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static junit.framework.TestCase.assertEquals;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        page.create("a", "b", "c");
        page.edit("a", "a edited");
        page.toggle("a edited", "b");
        assertEquals(page.count(), 3);

        page.filterCompleted();
        page.toggle("b");
        page.assertTasksAre("a edited");

        page.filterActive();
        page.toggleAll();
        page.assertTasksEmpty();

        page.filterAll();
        page.delete("a edited", "c");
        page.clearCompleted();

    }
}