package com.selenide.test;

import com.selenide.core.TaskManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest {

    protected TaskManager testEntity = new TaskManager();

    @BeforeClass
    public static void setUp() {

        open("https://todomvc4tasj.herokuapp.com/");
    }

    @AfterClass
    public static void tearDown() {

        close();
    }
}