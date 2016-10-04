package com.todomvc.test;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import com.todomvc.pages.TaskManagerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;


public class TodoMVCTest {

    private TaskManagerPage page = new TaskManagerPage();

    @Before
    public void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void cleanUp() {
        executeJavaScript("localStorage.clear()");
    }

    @After
    public void tearDown() throws IOException {
        File lastSelenideScreenshot = Screenshots.getLastScreenshot();
        if (lastSelenideScreenshot != null) {
            screenshot(Files.toByteArray(lastSelenideScreenshot));
        }

    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }


    @Test
    public void testTasksCommonFlow() {

        page.create("a");
        page.assertTasks("a");

        page.filterActive();
        page.assertVisibleTasks("a");

        page.create("b");
        page.toggleAll();
        page.assertNoVisibleTasks();

        page.filterCompleted();
        page.assertVisibleTasks("a", "b");

        page.toggle("b");
        page.assertVisibleTasks("a");

        page.clearCompleted();
        page.assertNoVisibleTasks();
        page.assertItemsLeft(1);

        page.filterAll();
        page.delete("b");
        page.assertNoVisibleTasks();
    }

    @Test
    public void testActivateAll() {

        //given
        page.create("a", "b", "c", "d");
        page.toggleAll();
        page.filterCompleted();

        page.toggleAll();
        page.filterActive();
        page.assertVisibleTasks("a", "b", "c", "d");
    }

    @Test
    public void testEditByTab() {

        //given
        page.create("a");

        page.startEdit("a", "a edited").pressTab();
        page.assertVisibleTasks("a edited");
    }

    @Test
    public void testCancelEditByEsc() {

        //given
        page.create("b");

        page.startEdit("b", "b cancel edit").pressEscape();
        page.assertVisibleTasks("b");
    }

}