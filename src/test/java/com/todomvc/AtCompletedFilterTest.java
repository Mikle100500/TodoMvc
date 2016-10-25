package com.todomvc;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.todomvc.helpers.Preconditions.precondition;


public class AtCompletedFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testDelete(){

        precondition().completedTasks("a", "b").atCompletedFilter().build();

        page.delete("a");
        page.assertVisibleTasks("b");
    }

    @Test
    public void testActivate(){

        precondition().completedTasks("a").atCompletedFilter().build();

        page.toggle("a");
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);
    }

    @Test
    public void testActivateAll(){

        precondition().completedTasks("a", "b", "c").atCompletedFilter().build();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(3);
    }

    @Test
    public void testClearCompleted(){

        precondition().completedTasks("a", "b").atCompletedFilter().build();

        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testMoveToAll(){

        precondition().completedTasks("a").atCompletedFilter().build();

        page.filterAll();
        page.assertTasks("a");
        page.assertItemsLeft(0);
    }

    @Test
    public void testMoveToActive(){

        precondition().completedTasks("a").atCompletedFilter().build();

        page.filterActive();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testDeleteByEmptying(){

        precondition().completedTasks("a", "b").atCompletedFilter().build();

        page.startEdit("a", "").pressEnter();
        page.assertVisibleTasks("b");
        page.assertItemsLeft(0);

    }
}
