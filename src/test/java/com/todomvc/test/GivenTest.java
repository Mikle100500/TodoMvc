package com.todomvc.test;

import org.junit.Test;

import static java.lang.Thread.sleep;

public class GivenTest extends BaseTest {

    @Test
    public void testGiven() throws InterruptedException {
        page.given("Active", "newA", "newB", "newC");
        sleep(7000);
    }
}
