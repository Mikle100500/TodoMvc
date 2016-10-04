package com.todomvc.test;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import org.junit.Before;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;


public class BaseTest {
    @Before
    public void setUp() {
        open("https://todomvc4tasj.herokuapp.com/");
    }

    @Before
    public void clearScreenshotList(){
        Screenshots.screenshots.getScreenshots().clear();
    }

    @After
    public void cleanUp() {
        executeJavaScript("localStorage.clear()");
    }

    @After
    public void tearDown() throws IOException {

        File lastSelenideScreenshot = Screenshots.getLastScreenshot();
        if (lastSelenideScreenshot != null) {
            screenshot(Files.toByteArray(lastSelenideScreenshot));
        }
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot(byte[] dataForScreenshot) {
        return dataForScreenshot;
    }
}
