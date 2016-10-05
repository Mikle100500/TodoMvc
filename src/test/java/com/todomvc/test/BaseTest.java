package com.todomvc.test;

import org.junit.After;
import org.junit.Before;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest extends AllureAttachmentTest {

    @Before
    public void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @After
    public void cleanUp() {
        executeJavaScript("localStorage.clear()");
    }
}