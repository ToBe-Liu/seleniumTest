package com.yatou.automation.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;



/**
 * Wait的封装
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class WaitUtil {


    public static WebElement fluentWait(WebDriver driver,Function<WebDriver, WebElement> isTrue) {
        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        return wait.until(isTrue);
    }
}
