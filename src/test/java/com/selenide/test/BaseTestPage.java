package com.selenide.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;


/**
 * Created by Mikle on 14.09.2016.
 */
public class BaseTestPage {

    @BeforeClass
    public static void setUp() {
//        FirefoxProfile profile = new FirefoxProfile();
//        profile.setPreference("browser.usedOnWindows10", false);
//        profile.setPreference("browser.usedOnWindows10.introURL", "about:blank");
//        self.browser = Webdriver.Firefox(firefox_profile=profile);
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @AfterClass
    public static void tearDown() {
        close();
    }
}