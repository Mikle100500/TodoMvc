package com.todomvc.test;

import com.todomvc.helpers.TaskBuilder;
import org.junit.Test;

import static com.todomvc.helpers.TaskBuilder.build;
import static com.todomvc.helpers.TaskStatus.ACTIVE;
import static com.todomvc.helpers.TaskStatus.COMPLETED;

public class GivenTest extends BaseTest {

    @Test
    public void testGiven() throws InterruptedException {

//        page.given(build("test", ACTIVE));
//        page.given(new TaskBuilder("test2", COMPLETED));
        page.given(build(ACTIVE, "a", "b", "c"));
    }
}
