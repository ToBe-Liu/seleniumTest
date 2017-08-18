package com.yatou.automation.common;

import com.yatou.automation.utils.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 基础页面
 *
 * @author LiuXingHai
 * @date 2017-08-14
 */
public abstract class BasePage {

    public static WebDriver driver;
    public static String pageTitle;
    public static String pageUrl;

    /* 
     * 构造方法 
     */
    public BasePage(WebDriver driver) {
        BasePage.driver = driver;
    }

    /* 
     * 在文本框内输入字符 
     */
    protected void type(WebElement element, String text) {
        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.log("清空" + element.toString() + "输入框.");
                element.sendKeys(text);
                Logger.log("填入值: " + text + ".");
            }
        } catch (Exception e) {
            Logger.log(e.getMessage() + ".");
        }

    }

    /* 
     * 点击元素，这里指点击鼠标左键 
     */
    protected void click(WebElement element) {

        try {
            if (element.isEnabled()) {
                element.click();
                Logger.log("点击: " + element.toString() + ".");
            }
        } catch (Exception e) {
            Logger.log(e.getMessage() + ".");
        }

    }

    /* 
     * 在文本输入框执行清除操作 
     */
    protected void clean(WebElement element) {

        try {
            if (element.isEnabled()) {
                element.clear();
                Logger.log("清除 " + element.toString() + "输入框.");
            }
        } catch (Exception e) {
            Logger.log(e.getMessage() + ".");
        }

    }

    /* 
     * 判断一个页面元素是否显示在当前页面 
     */
    protected boolean verifyElementIsPresent(WebElement element) {

        try {
            if (element.isDisplayed()) {
                Logger.log("元素" + element.toString().trim() + "存在.");
                return true;
            }
        } catch (Exception e) {
            Logger.log(e.getMessage() + ".");
        }
        return false;
    }

    /* 
     * 获取页面的标题 
     */
    protected String getCurrentPageTitle() {

        pageTitle = driver.getTitle();
        Logger.log("当前页的标题： " + pageTitle);
        return pageTitle;
    }

    /* 
     * 获取页面的url 
     */
    protected String getCurrentPageUrl() {

        pageUrl = driver.getCurrentUrl();
        Logger.log("当前页的url： " + pageUrl);
        return pageUrl;
    }


}  