package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.todomvc.helpers.Preconditions.given;


public class AtCompletedFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testDelete(){

        given().completedTasks("a", "b").atCompletedFilter().build();

        page.delete("a");
        page.assertVisibleTasks("b");
    }

    @Test
    public void testActivate(){

        given().completedTasks("a").atCompletedFilter().build();

        page.toggle("a");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivateAll(){

        given().completedTasks("a", "b", "c").atCompletedFilter().build();

        page.toggleAll();
        page.assertTasks("a", "b", "c");
        page.assertItemsLeft(3);
    }

    @Test
    public void testClearCompleted(){

        given().completedTasks("a", "b").atCompletedFilter().build();

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testMoveToAll(){

        given().completedTasks("a").atCompletedFilter().build();

        page.filterAll();
        page.assertTasks("a");
        page.assertItemsLeft(0);
    }

    @Test
    public void testMoveToActive(){

        given().completedTasks("a").atCompletedFilter().build();

        page.filterActive();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testDeleteByEmptying(){

        given().completedTasks("a").atCompletedFilter().build();
        sleep(5000);
        page.startEdit("a", "").pressEnter();
        page.assertNoVisibleTasks();

    }
}
