package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testTasksCommonFlow() {

        open("https://todomvc4tasj.herokuapp.com/");

        //All filter test:
        page.create("a", "b", "c");                     // create tasks - checked (1/9 - method's coverage)
        page.assertTasksAre("a", "b", "c");

        page.startEdit("a", "a edited").pressEnter();                     // startEdit a task - checked (2/9)
        page.assertTasksAre("a edited", "b", "c");

        // Active filter test:
        page.filterActive();                            // filterActive - checked (3/9)
        page.assertTasksAre("a edited", "b", "c");

        page.toggle("b");                               // toggle - checked (4/9)
        page.assertTasksAre("a edited", "c");           // invisibility of toggled task at Active filter - checked

        //Completed filter test:
        page.filterCompleted();                         // filterCompleted - checked (5/9)
        page.assertTasksAre("b");

        page.startEdit("b", "b cancel editing").pressEscape();        // cancel editing - checked (6/9)
        page.assertTasksAre("b");                                       
        
        page.clearCompleted();                          // clearCompleted - checked (7/9)
        page.assertTasksEmpty();

        //All filter test:
        page.filterAll();
        page.assertTasksAre("a edited", "c");

        page.delete("c");                               // delete - checked (8/9)
        page.assertTasksAre("a edited");
        
        page.toggleAll();                               // toggleAll (9/9) - all actions are checked
        page.filterActive();
        page.assertTasksEmpty();                        // check that all toggled tasks are not at Active filter

    }
}