package com.todomvc.test;

import org.junit.Test;

public class GivenTest extends BaseTest {

    @Test
    public void testGiven() throws InterruptedException {
        page.given("Active", "newA", "newB", "newC");
    }
}
