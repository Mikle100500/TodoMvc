package com.todomvc.testconfigs;

import org.junit.Before;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest extends AllureAttachmentTest {

    @Before
    public void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

}