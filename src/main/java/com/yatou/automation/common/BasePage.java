package com.yatou.automation.common;

import com.yatou.automation.store.Menu;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.StringUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 基础页面
 *
 * @author LiuXingHai
 * @date 2017-08-14
 */
public abstract class BasePage {
    public ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    public Integer timeout = 10;//超时时间（单位：秒）
    public Integer timePolling = 500;//轮询时间（单位；毫秒）

    ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BasePage.class);
    /*
     * 构造方法
     */
    public BasePage(WebDriver driver1) {
        threadDriver.set(driver1);
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public void setTimePolling(Integer timePolling) {
        this.timePolling = timePolling;
    }

    /**
     * 在{@link #timeout}内查找一个WebElement
     *
     * @param msg 元素描述
     * @param isTrue 函数式查找一个WebElement
     * @return 找到的WebElement
     */
    public WebElement fluentFind(Function<WebDriver, WebElement> isTrue, String msg) {
        WebDriver driver = threadDriver.get();
        WebElement element = null;

        Wait<WebDriver> wait = new FluentWait(driver)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(timePolling, TimeUnit.MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            element = wait.until(isTrue);
        }catch (TimeoutException e) {
            Logger.log(timeout+"秒内没有找到"+msg+e);
        } catch (NoSuchElementException e) {
            Logger.log(timeout+"秒内没有找到"+msg+e);
        }
        if(element == null){
            Assert.assertNull(1,timeout+"秒内没有找到"+msg);
        }
        return element;
    }
    /**
     * 对操作返回信息的封装
     */
    public void fluentFindReturnMessage() {
        WebElement returnMessage = fluentFind(findElement(Menu.class, "message"), "操作返回信息");
        Logger.log("操作返回信息:["+returnMessage.getText()+"]");
        if(!returnMessage.getText().trim().contains(ReturnMessageConstants.OPERATION_SUCCEED) ){
            Assert.assertNull(1,"操作失败：" + returnMessage.getText());
        }
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并点击
     *
     * @param msg 元素描述
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndClick(Function<WebDriver, WebElement> isTrue,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        click(element,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并输入content
     *
     * @param msg 元素描述
     * @param content 输入内容
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndType(Function<WebDriver, WebElement> isTrue,String content,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        type(element,content,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并输入指定keys
     *
     * @param msg 元素描述
     * @param keys 指定keys
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndTypeKeys(Function<WebDriver, WebElement> isTrue,Keys keys,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        typeKeys(element,keys,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并下拉选择指定值
     *
     * @param msg 元素描述
     * @param value 指定值
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndSelectByValue(Function<WebDriver, WebElement> isTrue,String value,String msg) {
        WebElement element = fluentFind(isTrue, msg);
        selectByValue(element,value,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并直接设定指定值为选中状态
     * （适用于一些无法通过{@link #fluentFindAndSelectByValue}方法选值的select）
     *
     * @param msg 元素描述
     * @param value 指定值
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndSetSelectedByValue(Function<WebDriver, WebElement> isTrue,String value,String msg) {
        WebElement element = fluentFind(isTrue, msg);
        setSelectedByValue(element,value,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并设置Html
     *
     * @param msg 元素描述
     * @param content 设置的内容
     * @param isTrue 函数式查找一个WebElement
     */
    public void fluentFindAndSetHtml(Function<WebDriver, WebElement> isTrue,String content,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        setHtml(element,content,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并设置属性
     *
     * @param attrName 属性名
     * @param attrValue 属性值
     * @param isTrue 函数式查找一个WebElement
     * @param msg 元素描述
     */
    public void fluentFindAndSetAttribute(Function<WebDriver, WebElement> isTrue,String attrName,String attrValue,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        setAttribute(element,attrName,attrValue,msg);
    }
    /**
     * 在{@link #timeout}内查找一个WebElement并移除属性
     *
     * @param attrName 属性名(多个属性名通过逗号隔开)
     * @param isTrue 函数式查找一个WebElement
     * @param msg 元素描述
     */
    public void fluentFindAndRemoveAttribute(Function<WebDriver, WebElement> isTrue,String attrName,String msg) {
        WebElement element = fluentFind(isTrue,msg);
        List<String> attrNames = StringUtil.splitByComma(attrName, String.class);
        attrNames.forEach((at)->removeAttribute(element,at,msg));
    }

    /**
     * 在{@link #timeout}内并发的查找多个页面元素，任意找到其中一个就返回找到的元素
     *
     * @param msg 元素描述
     * @param isTrue 查找一个元素的Function
     * @return 多个Function中最先找到的元素
     */
    public WebElement concurrentFind(String msg,Function<WebDriver, WebElement>... isTrue) {
        WebDriver driver = threadDriver.get();
        WebElement webElement = null;
        try {
            Future<WebElement> future = fixedThreadPool.submit(() -> {

                logger.debug("当前查找线程："+Thread.currentThread().getName());
                logger.debug("当前查找线程driver："+driver);
                WebElement element1 = null;
                while (true){
                    for (Function<WebDriver, WebElement> f : isTrue) {
                        try {
                            element1 = f.apply(driver);
                        } catch (NoSuchElementException e) {}
                        if (element1 != null) {
                            logger.debug("返回找到的element1:"+element1.getText());
                            return element1;
                        }
                    }
                    Thread.sleep(500);//每隔500毫秒查找一遍，直到超时
                }
            });
            webElement = future.get(timeout, TimeUnit.SECONDS);
        }catch (Exception e) {
            e.printStackTrace();
            Logger.log(msg);
        }finally {
            //fixedThreadPool.shutdown();
            //Logger.log("关闭查找线程.");
        }
        return webElement;
    }


    /**
     * 通过反射获取指定类中指定字段名的一个查找函数
     *
     * @param clazz 需要反射的类
     * @param webElementName 字段名
     * @return 查找函数
     */
    public Function<WebDriver, WebElement> findElement(Class clazz, String webElementName) {
        WebDriver driver = threadDriver.get();
        try {
            Field field = clazz.getDeclaredField(webElementName);
            field.setAccessible(true);
            FindBy findBy = field.getAnnotation(FindBy.class);

            if (!"".equals(findBy.id().trim())) {
                return (driver1)->driver.findElement(By.id(findBy.id()));
            }
            if (!"".equals(findBy.name().trim())) {
                return (driver1)->driver.findElement(By.name(findBy.name()));
            }
            if (!"".equals(findBy.className().trim())) {
                return (driver1)->driver.findElement(By.className(findBy.className()));
            }
            if (!"".equals(findBy.css().trim())) {
                return (driver1)->driver.findElement(By.cssSelector(findBy.css()));
            }
            if (!"".equals(findBy.xpath().trim())) {
                return (driver1)->driver.findElement(By.xpath(findBy.xpath()));
            }
            if (!"".equals(findBy.linkText().trim())) {
                return (driver1)->driver.findElement(By.linkText(findBy.linkText()));
            }
            if (!"".equals(findBy.partialLinkText().trim())) {
                return (driver1)->driver.findElement(By.partialLinkText(findBy.partialLinkText()));
            }
            if (!"".equals(findBy.tagName().trim())) {
                return (driver1)->driver.findElement(By.tagName(findBy.tagName()));
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
            Assert.assertNull(1,"NoSuchFieldException：["+e.getMessage()+"].");
        }
        return null;
    }


    /**
     * 在指定的元素输入指定内容
     *
     * @param element 指定的元素
     * @param content 输入内容
     * @param msg 元素描述
     */
    protected void type(WebElement element,String content,String msg){
        if(!element.isEnabled()){
            Logger.log("当前元素；["+ msg+"]被禁！");
        }
        if(!element.isDisplayed()){
            Logger.log("当前元素：["+ msg+"]不可见！");
        }
        if(isReadonly(element)){
            Assert.assertNull(1,"当前元素：["+msg+"]只读，无法输入！");
        }
        clean(element,msg);
        element.sendKeys(content);
        Logger.log("填入: [" + msg + ":" + content + "].");
    }
    /**
     * 在指定的元素输入指定keys
     *
     * @param element 指定的元素
     * @param keys 指定keys
     * @param msg 元素描述
     */
    protected void typeKeys(WebElement element,Keys keys,String msg){
        if(!element.isEnabled()){
            Logger.log("当前元素；["+ msg+"]被禁！");
        }
        if(!element.isDisplayed()){
            Logger.log("当前元素：["+ msg+"]不可见！");
        }
        if(isReadonly(element)){
            Assert.assertNull(1,"当前元素：["+msg+"]只读，无法输入！");
        }
        element.sendKeys(keys);
        Logger.log("填入：[" + msg + ":" + keys.toString() + "].");
    }

    /**
     * 点击指定的元素
     *
     * @param element 指定的元素
     * @param msg 元素描述
     */
    protected void click(WebElement element,String msg){
        try{
            if(!element.isDisplayed()){
                Assert.assertNull(1,"当前元素：["+ msg+"]不可见，无法点击！");
            }
            scrollIntoView(element,msg);
            if(msg.equals(element.getText().trim())){
                Logger.log("点击：[" + msg + "].");
            }else {
                Logger.log("点击：[" + msg + ":"+ element.getText().trim() + "].");
            }
            //注意在元素点击之后可能跳转页面，跳转之后的页面可能没有这个元素
            //这时再调用element.getText()会报错，所以这里的日志写在操作之前
            element.click();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 下拉选择指定的值
     *
     * @param element 指定的元素
     * @param element 指定的值
     * @param msg 元素描述
     */
    protected void selectByValue(WebElement element,String value,String msg){
        if(!element.isDisplayed()){
            Assert.assertNull(1,"当前元素：["+ msg+"]不可见，无法下拉选择！");
        }
        Select select = new Select(element);
        select.selectByValue(value);
        Logger.log("下拉：[" + msg + "]选择[" + value + "].");
    }
    /**
     * 下拉直接设定指定的值
     *（有些元素调用selenium的selectByValue方法会报错，所以直接设定）
     * @param element 指定的元素
     * @param element 指定的值
     * @param msg 元素描述
     */
    protected void setSelectedByValue(WebElement element,String value,String msg){
        WebElement option = element.findElement(By.xpath(".//option[@value = " + Quotes.escape(value) + "]"));
        setAttribute(option,"selected","selected",msg);
        Logger.log("下拉：[" + msg + "]直接设定[" + value + "]为selected.");
    }

    /**
     * 清空指定的元素的内容
     *
     * @param element 指定的元素
     * @param msg 元素描述
     */
    protected void clean(WebElement element,String msg){
        try{
            if(!element.isEnabled()){
                Assert.assertNull(1,"当前元素：["+msg+"]被禁，无法清除！");
            }
            if(isReadonly(element)){
                Assert.assertNull(1,"当前元素：["+msg+"]只读，无法清除！");
            }
            if(!element.isDisplayed()){
                Logger.log("当前元素：["+msg+"]不可见！");
            }
            element.clear();
            Logger.log("清空：[" + msg + "].");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 设置指定的元素的属性
     *
     * @param element 指定的元素
     * @param attrName 属性名
     * @param attrValue 属性值
     * @param msg 元素描述
     */
    protected void setAttribute(WebElement element,String attrName,String attrValue,String msg){
        WebDriver driver = threadDriver.get();
        ((JavascriptExecutor)driver).executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element,attrName,attrValue);
        Logger.log("设置：[" + msg + "]的属性["+attrName+"=‘"+attrValue+"']");
    }
    /**
     * 移除指定的元素的属性
     *
     * @param element 指定的元素
     * @param attrName 属性名
     * @param msg 元素描述
     */
    protected void removeAttribute(WebElement element,String attrName,String msg){
        WebDriver driver = threadDriver.get();
        ((JavascriptExecutor)driver).executeScript("arguments[0].removeAttribute(arguments[1])", element,attrName);
        Logger.log("移除：[" + msg + "]的["+attrName+"]属性.");
    }

    /**
     * 设置指定的元素的html
     *
     * @param element 指定的元素
     * @param content 设置的值
     * @param msg 元素描述
     */
    protected void setHtml(WebElement element,String content,String msg){
        WebDriver driver = threadDriver.get();
        if(!element.isDisplayed()){
            Logger.log("当前元素：["+msg+"]不可见！");
        }
        ((JavascriptExecutor)driver).executeScript("arguments[0].innerHTML=arguments[1]", element,content);
        Logger.log("设置：[" + msg + "]的html为："+content);
    }
    /**
     * 滚动页面直到元素可见
     *(不考虑页面的宽度，一般在浏览器初始化时设置大一点的窗口size)
     *
     * @param element 指定的元素
     * @param msg 元素描述
     */
    protected void scrollIntoView(WebElement element,String msg){
        WebDriver driver = threadDriver.get();
        if(!element.isDisplayed()){//这里的不可见是display属性的不可见
            Assert.assertNull(1,"当前元素：["+msg+"]不可见！");
        }
        int elementPosition = element.getLocation().getY();
        Long clientHeight = (Long)((JavascriptExecutor) driver).executeScript("return document.documentElement.clientHeight;");

        //只有在元素高度高于浏览器高度时(这里减50是因为我们的项目前端上下各有50的遮罩层)才滚动
        if((elementPosition) > (clientHeight-50)){
            logger.debug(msg+"元素的纵坐标："+elementPosition);
            logger.debug("浏览器窗口的高度："+clientHeight);
            String js = String.format("window.scroll(0, %s)", (elementPosition-50));
            ((JavascriptExecutor)driver).executeScript(js);
            Logger.log("滚动页面直到[" + msg + "]可见");//这里的不可见是元素在浏览器窗口的可见性
        }
    }
    /**
     * 判断指定的元素是否只读
     *
     * @param element 指定的元素
     * @return 只读（true）反之（false）
     */
    protected boolean isReadonly(WebElement element){
        String readonly = element.getAttribute("readonly");
        if(readonly != null && readonly.contains("true")){
            return true;
        }
        return  false;
    }

}  