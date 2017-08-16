package com.yatou.automation.utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
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


    public static WebElement fluentWait(WebDriver driver,int time,String msg,Function<WebDriver, WebElement> isTrue) {
        WebElement element = null;
        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(time, TimeUnit.SECONDS)
                .pollingEvery(500, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            element = wait.until(isTrue);
        }catch (TimeoutException e) {
            Logger.log(msg);
        } catch (NoSuchElementException e) {
            Logger.log(msg);
        }
        return element;
    }
}
