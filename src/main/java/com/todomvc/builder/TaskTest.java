package com.todomvc.builder;

import org.junit.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.todomvc.builder.Task.given;


public class TaskTest {

    @Test
    public void testSetUp(){

        given().addActive("a", "b").addCompleted("c", "d").atAllFilter().build();
        sleep(5000);
    }
}
