package com.todomvc.test;
import org.junit.BeforeClass;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest {

    @BeforeClass
    public static void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }
}