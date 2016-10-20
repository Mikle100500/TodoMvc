package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.given;

public class AtAllFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate(){

        given().atAllFilter().build();

        page.create("a");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEdit(){

        given().activeTasks("a").atAllFilter().build();

        page.startEdit("a", "a edited").pressEnter();
        page.assertTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete(){

        given().activeTasks("a", "active to delete").completedTasks("b", "completed to delete").atAllFilter().build();

        page.delete("active to delete");
        page.delete("completed to delete");
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testComplete(){

        given().activeTasks("a", "b").atAllFilter().build();

        page.toggle("a");
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivate(){

        given().completedTasks("a").atAllFilter().build();

        page.toggle("a");
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll(){

        given().activeTasks("a", "b").atAllFilter().build();

        page.toggleAll();
        page.assertTasks("a", "b");
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted(){

        given().completedTasks("a", "b", "c").atAllFilter().build();

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testMoveToActive(){

        given().activeTasks("a", "b", "c").atAllFilter().build();

        page.filterActive();
        page.assertVisibleTasks("a", "b", "c");
        page.assertItemsLeft(3);
    }

    @Test
    public void testMoveToCompleted(){

        given().activeTasks("a", "b").completedTasks("c", "d").atAllFilter().build();

        page.filterCompleted();
        page.assertVisibleTasks("c", "d");
        page.assertItemsLeft(2);
    }

    @Test
    public void testCancelByEsc(){

        given().activeTasks("a").completedTasks("b").atAllFilter().build();

        page.startEdit("a", "a edited").pressEscape();
        page.startEdit("b", "b edited").pressEscape();
        page.assertTasks("a", "b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditByTab(){

        given().activeTasks("a").completedTasks("b").atAllFilter().build();

        page.startEdit("a", "a edited").pressTab();
        page.startEdit("b", "b edited").pressTab();
        page.assertTasks("a edited", "b edited");
        page.assertItemsLeft(1);
    }
}
