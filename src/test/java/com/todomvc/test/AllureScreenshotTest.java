package com.todomvc.test;


import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import org.junit.After;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;

public class AllureScreenshotTest {
    @After
    public void tearDown() throws IOException {
        screenshot();
    }

    @Attachment(type = "image/png")
    public static byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        return Files.toByteArray(screenshot);
    }
}
