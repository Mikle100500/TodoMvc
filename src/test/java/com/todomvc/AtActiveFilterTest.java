package com.todomvc;

import com.todomvc.pages.TaskManagerPage;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.todomvc.helpers.Preconditions.precondition;

public class AtActiveFilterTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Test
    public void testCreate() {

        precondition().atActiveFilter().prepare();

        page.create("a");
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testEdit() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        page.startEdit("a", "a edited").pressEnter();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);
    }

    @Test
    public void testDelete() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        page.delete("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testComplete() {

        precondition().activeTasks("a", "b").atActiveFilter().prepare();

        page.toggle("a");
        page.assertVisibleTasks("b");
        page.assertItemsLeft(1);
    }

    @Test
    public void testCompleteAll() {

        precondition().activeTasks("a", "b", "c").atActiveFilter().prepare();

        page.toggleAll();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(0);
    }

    @Test
    public void testClearCompleted() {

        precondition().activeTasks("a", "b", "c").atActiveFilter().prepare();

        page.toggleAll();
        page.clearCompleted();
        page.assertNoVisibleTasks();
    }

    @Test
    public void testSwitchToAll() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        page.filterAll();
        page.assertTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testSwitchToCompleted() {

        precondition().completedTasks("a", "b").atActiveFilter().prepare();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");
        page.assertItemsLeft(0);
    }

    @Test
    public void testCancelEditByEsc() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        page.startEdit("a", "a edited").pressEscape();
        page.assertVisibleTasks("a");
        page.assertItemsLeft(1);
    }

    @Test
    public void testConfirmEditClickOutside() {

        precondition().activeTasks("a").atActiveFilter().prepare();

        page.startEdit("a", "a edited");
        $("#header").click();
        page.assertVisibleTasks("a edited");
        page.assertItemsLeft(1);

    }
}
