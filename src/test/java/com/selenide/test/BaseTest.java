package com.selenide.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.firefox.FirefoxProfile;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by Mikle on 14.09.2016.
 */

public class BaseTest {

    @BeforeClass
    public static void setUp() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.usedOnWindows10", false);
        profile.setPreference("browser.usedOnWindows10.introURL", "about:blank");
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @AfterClass
    public static void tearDown() {

        close();
    }
}