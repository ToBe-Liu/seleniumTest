package com.yatou.automation.common;

import com.yatou.automation.store.Menu;
import com.yatou.automation.utils.Logger;
import com.yatou.automation.utils.StringUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Quotes;
import org.openqa.selenium.support.ui.Select;
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
    public Integer timeout = 10;//超时时间
    public Integer timePolling = 500;//轮询时间（默认单位：毫秒）
    public TimeUnit timeoutUnit = TimeUnit.SECONDS;//超时时间（默认单位：秒）
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

    public void setTimeoutUnit(TimeUnit timeoutUnit) {
        this.timeoutUnit = timeoutUnit;
    }


    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param text 元素包含的text（可选，不需要请填null）
     * @return 找到的WebElement
     */
    protected WebElement fluentFind(Class clazz, String webElementName,String text) {
        Function<WebDriver, WebElement> isTrue = null;
        if (text!=null){
            isTrue = findElementByText(clazz, webElementName,text);
        } else {
            isTrue = findElement(clazz, webElementName);
        }
        String msg = findDescription(clazz, webElementName);

        WebDriver driver = threadDriver.get();
        WebElement element = null;

        try {
            element = concurrentFind(msg,isTrue);
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
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并点击
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndClick(Class clazz, String webElementName) {
        WebElement element = fluentFind(clazz,webElementName,null);
        String msg = findDescription(clazz, webElementName);
        click(element,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并点击
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndClickByText(Class clazz, String webElementName,String text) {
        WebElement element = fluentFind(clazz,webElementName,text);
        String msg = findDescription(clazz, webElementName);
        click(element,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并输入content
     *
     * @param content 输入内容
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndType(Class clazz, String webElementName,String content) {
        WebElement element = fluentFind(clazz,webElementName,null);
        String msg = findDescription(clazz, webElementName);
        type(element,content,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并输入指定{@link Keys}
     *
     * @param keys 指定keys
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndTypeKeys(Class clazz, String webElementName,Keys keys) {
        WebElement element = fluentFind(clazz,webElementName,null);
        String msg = findDescription(clazz, webElementName);
        typeKeys(element,keys,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并输入指定日期
     *（对日期输入框的封装）
     *
     * @param date 指定日期
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndTypeDate(Class clazz, String webElementName,String date) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        removeAttribute(element,"readonly",msg); //移除输入框的只读属性不然无法直接设置日期
        type(element,date,msg);
        fluentFindAndClick(Menu.class,"header");//点击头部，以便使日期弹出框消失
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并上传指定的文件
     *（对文件上传的封装）
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param filePath 上传的文件的绝对路径
     */
    protected void fluentFindAndTypeFile(Class clazz, String webElementName,String filePath) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        removeAttribute(element,"style",msg); //移除输入框的只读属性不然无法直接设置件的绝对路径
        type(element,filePath,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并下拉选择指定值
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param value 指定值
     */
    protected void fluentFindAndSelectByValue(Class clazz, String webElementName,String value) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz, webElementName,null);
        selectByValue(element,value,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并直接设定指定值为选中状态
     * （适用于一些无法通过{@link #fluentFindAndSelectByValue}方法选值的select）
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param value 指定值
     */
    protected void fluentFindAndSetSelectedByValue(Class clazz, String webElementName,String value) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz, webElementName,null);
        setSelectedByValue(element,value,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并设置Html
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param content 设置的内容
     */
    protected void fluentFindAndSetHtml(Class clazz, String webElementName,String content) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        setHtml(element,content,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并设置属性
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param attrName 属性名
     * @param attrValue 属性值
     */
    protected void fluentFindAndSetAttribute(Class clazz, String webElementName,String attrName,String attrValue) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        setAttribute(element,attrName,attrValue,msg);
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并移除属性
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param attrName 属性名(多个属性名通过逗号隔开)
     */
    protected void fluentFindAndRemoveAttribute(Class clazz, String webElementName,String attrName) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        List<String> attrNames = StringUtil.splitByComma(attrName, String.class);
        attrNames.forEach((at)->removeAttribute(element,at,msg));
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并移除class
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     * @param className class名(多个class名通过逗号隔开)
     */
    protected void fluentFindAndRemoveClass(Class clazz, String webElementName,String className) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        List<String> classNames = StringUtil.splitByComma(className, String.class);
        classNames.forEach((at)->removeClass(element,at,msg));
    }
    /**
     * 在{@link #timeout}内每隔{@link #timePolling}查找一个WebElement并移除
     *
     * @param clazz 定义元素的类
     * @param webElementName 元素名
     */
    protected void fluentFindAndRemoveElement(Class clazz, String webElementName) {
        String msg = findDescription(clazz, webElementName);
        WebElement element = fluentFind(clazz,webElementName,null);
        removeElement(element,msg);
    }

    /**
     * 对操作返回信息的封装
     */
    protected void fluentFindReturnMessage() {
        WebElement returnMessage = fluentFind(Menu.class, "message",null);
        Logger.log("操作返回信息:["+returnMessage.getText()+"]");
        if(!returnMessage.getText().trim().contains(StoreConstants.OPERATION_SUCCEED) ){
            Assert.assertNull(1,"操作失败：" + returnMessage.getText());
        }
    }

    /**
     * 通过反射获取指定类中指定字段名的一个查找函数
     *
     * @param clazz 需要反射的类
     * @param webElementName 字段名
     * @return 查找函数
     */
    protected Function<WebDriver, WebElement> findElement(Class clazz, String webElementName) {
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
     * 通过反射获取指定类中指定字段名并包含指定text的一个查找函数
     *
     * @param clazz 需要反射的类
     * @param webElementName 字段名
     * @param text 元素包含的text（不支持模糊查找，请填写完整的text）
     * @return 查找函数
     */
    protected Function<WebDriver, WebElement> findElementByText(Class clazz, String webElementName,String text) {
        WebDriver driver = threadDriver.get();
        try {
            Field field = clazz.getDeclaredField(webElementName);
            field.setAccessible(true);
            FindBy findBy = field.getAnnotation(FindBy.class);
            String xpath = findBy.xpath()+"[text()='"+text+"']";
            if (!"".equals(findBy.id().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.name().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.className().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.css().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.xpath().trim())) {
                return (driver1)->driver.findElement(By.xpath(xpath));
            }
            if (!"".equals(findBy.linkText().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.partialLinkText().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
            if (!"".equals(findBy.tagName().trim())) {
                Assert.assertNull(1,"findElementByText方法只支持xpath.");
            }
        } catch (NoSuchFieldException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
            Assert.assertNull(1,"NoSuchFieldException：["+e.getMessage()+"].");
        }
        return null;
    }
    /**
     * 通过反射获取指定类中指定字段名的描述信息
     *
     * @param clazz 需要反射的类
     * @param webElementName 字段名
     * @return 指定字段名的描述信息
     */
    protected String findDescription(Class clazz, String webElementName) {
        WebDriver driver = threadDriver.get();
        try {
            Field field = clazz.getDeclaredField(webElementName);
            field.setAccessible(true);
            FindBy findBy = field.getAnnotation(FindBy.class);
            String description = findBy.description();
            return description;
        } catch (NoSuchFieldException e) {
            logger.debug(e.getMessage());
            e.printStackTrace();
            Assert.assertNull(1,"NoSuchFieldException：["+e.getMessage()+"].");
        }
        return "";
    }

    /**
     * 在{@link #timeout}内每隔{@link #timePolling}并发的查找多个页面元素，任意找到其中一个就返回找到的元素
     *
     * @param msg 元素描述
     * @param isTrue 查找一个元素的Function
     * @return 多个Function中最先找到的元素
     */
    protected WebElement concurrentFind(String msg,Function<WebDriver, WebElement>... isTrue) {
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
                    Thread.sleep(timePolling);//每隔500毫秒查找一遍，直到超时
                }
            });
            webElement = future.get(timeout, timeoutUnit);
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
     * 在指定的元素输入指定内容
     *
     * @param element 指定的元素
     * @param content 输入内容
     * @param msg 元素描述
     */
    protected void type(WebElement element,String content,String msg){
        if(!element.isEnabled()){
            Assert.assertNull(1,"当前元素：["+msg+"]被禁，无法输入！");
        }
        if(!element.isDisplayed()){
            Assert.assertNull(1,"当前元素：["+msg+"]不可见，无法输入！");
        }
        if(isReadonly(element)){
            Assert.assertNull(1,"当前元素：["+msg+"]只读，无法输入！");
        }
        clean(element,msg);
        element.sendKeys(content);
        Logger.log("填入：[" + msg + ":" + content + "].");
    }
    /**
     * 在指定的元素输入指定{@link Keys}
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
                Assert.assertNull(1,"当前元素：["+msg+"]不可见，无法清除！");
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
     * 移除指定元素
     *
     * @param element 指定的元素
     * @param msg 元素描述
     */
    protected void removeElement(WebElement element,String msg){
        WebDriver driver = threadDriver.get();
        String js = "arguments[0].parentNode.removeChild(arguments[0]);";
        ((JavascriptExecutor) driver).executeScript(js, element);
        Logger.log("移除：[" + msg + "]元素.");

    }
    /**
     * 指定元素添加class
     *
     * @param element 指定的元素
     * @param className class名
     * @param msg 元素描述
     */
    protected void addClass(WebElement element,String className,String msg){
        WebDriver driver = threadDriver.get();
        String js = "arguments[0].className+=' '+arguments[1];";
        ((JavascriptExecutor)driver).executeScript(js, element,className);
        Logger.log("添加：[" + msg + "]的[class："+className+"]属性.");
    }
    /**
     * 指定元素移除class
     *
     * @param element 指定的元素
     * @param className class名
     * @param msg 元素描述
     */
    protected void removeClass(WebElement element,String className,String msg){
        WebDriver driver = threadDriver.get();
        String js = "arguments[0].className=arguments[0].className.replace(arguments[1],' ');";
        ((JavascriptExecutor)driver).executeScript(js, element,className);
        Logger.log("移除：[" + msg + "]的[class："+className+"]属性.");
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

        //只有在元素高度高于浏览器高度时才滚动(这里减50是因为我们的项目前端上下各有50的遮罩层)
        if((elementPosition) > (clientHeight-50)){
            logger.debug(msg+"元素的纵坐标："+elementPosition);
            logger.debug("浏览器窗口的高度："+clientHeight);
            //50的固定头部遮罩层再加有时候会有操作信息弹出层的50，所以减100
            String js = String.format("window.scroll(0, %s)", (elementPosition-100));
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