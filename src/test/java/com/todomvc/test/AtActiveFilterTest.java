package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.given;

public class AtActiveFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate(){

        given().atActiveFilter().build();

        page.create("a");
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEdit(){

        given().activeTasks("a").atActiveFilter().build();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete(){

        given().activeTasks("a", "b").atActiveFilter().build();

        page.delete("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testComplete(){

        given().activeTasks("a", "b").atActiveFilter().build();

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll(){

        given().activeTasks("a", "b", "c").atActiveFilter().build();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(0);
    }

    @Test
    public void testMoveToAll(){

        given().activeTasks("a").atActiveFilter().build();

        page.filterAll();
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testMoveToCompleted(){

        given().completedTasks("a", "b").atActiveFilter().build();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(0);
    }

    @Test
    public void testCancelEditByEsc(){

        given().activeTasks("a").atActiveFilter().build();

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

}
