package com.todomvc.test;

import com.todomvc.pages.TaskManagerPage;
import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest extends AllureAttachmentTest {

    protected TaskManagerPage page = new TaskManagerPage();

    @Before
    public void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void cleanUp() {
        executeJavaScript("localStorage.clear()");
    }
}