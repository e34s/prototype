/*
 * Copyright (c) 2014 - 2017.  Element34 Solutions - All Rights Reserved
 * Unauthorized copying and redistribution of this file or parts thereof,
 * via any medium is strictly prohibited without explicit consent of Element34 Solutions GmbH.
 */

package axa.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.sql.Timestamp;
import java.util.Date;

public class Helpers {

    //TODO: change to c:\\eplatform\\....
    public static final String SCREENSHOT_DIRECTORY = "C:\\Users\\mpalotas\\IdeaProjects\\axaprototype\\screenshots\\";

    public static boolean isElementPresent(WebDriver driver, By by) {

        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public static void sleepTight(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    public static void highlight(WebDriver driver, WebElement element) {
        //JavascriptExecutor jse = (JavascriptExecutor)driver;
        //jse.executeScript("arguments[0].style.border='2px solid red'", element);
    }

    public static void screenshot(RemoteWebDriver driver) {

        File tmp = driver.getScreenshotAs(OutputType.FILE);
        File ss = new File(SCREENSHOT_DIRECTORY + System.currentTimeMillis() + ".png");
        tmp.renameTo(ss);
        System.out.println("Screenshot: " + ss.getAbsoluteFile());
    }
}
