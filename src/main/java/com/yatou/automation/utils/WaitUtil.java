package com.yatou.automation.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;



/**
 * Wait的封装
 *
 * @author LiuXingHai
 * @date 2017-08-15
 */
public class WaitUtil {

    //static ExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
    static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);

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

    /**
     * 并发的查找2个页面元素，任意找到其中一个就返回找到的元素
     *
     * @param driver WebDriver
     * @param time 超时时间（单位：秒）
     * @param msg 自定义错误信息
     * @param isTrue1 函数式查找第一个元素
     * @param isTrue2 函数式查找第二个元素
     * @return 2个元素中最先找到的
     */
    public static WebElement concurrentFindWait(WebDriver driver, int time, String msg,
                                                     Function<WebDriver, WebElement> isTrue1, Function<WebDriver, WebElement> isTrue2) {
        WebElement webElement = null;
        try {
            Future<WebElement> future = fixedThreadPool.submit(() -> {
                Logger.log("开始查找线程...");
                WebElement element1 = null;
                WebElement element2 = null;
                while (true){
                    try {
                        element1 = isTrue1.apply(driver);
                    } catch (NoSuchElementException e) {}
                    try {
                        element2 = isTrue2.apply(driver);
                    } catch (NoSuchElementException e) {}
                    if (element1 != null) {
                        Logger.log("返回找到的元素.");
                        return element1;
                    }
                    if (element2 != null) {
                        Logger.log("返回找到的元素.");
                        return element2;
                    }
                    Thread.sleep(500);//每隔500毫秒查找一遍，直到超时
                }
            });
            webElement = future.get(time, TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
            Logger.log(msg);
        }finally {
            fixedThreadPool.shutdown();
            Logger.log("关闭查找线程.");
        }
        return webElement;
    }
}
