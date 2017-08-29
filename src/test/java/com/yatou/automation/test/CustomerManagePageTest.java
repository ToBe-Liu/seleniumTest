package com.yatou.automation.test;

import com.yatou.automation.store.StoreMenu;
import org.openqa.selenium.WebDriver;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店用户管理页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-21
 */
@Test(groups = {"customerManage"})
public class CustomerManagePageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    public static StoreMenu storeMenu;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerManagePageTest.class);
    @BeforeClass
    public void setupClass() {
        threadDriver = LoginPageTest.threadDriver;
        storeMenu = new StoreMenu(threadDriver.get());
    }

    @Test(description = "新增预约量尺测试")
    public void testAddMeasure() throws InterruptedException {
        WebDriver driver = threadDriver.get();
        logger.debug("新增预约量尺测试driver:"+driver);
        //storeMenu.clickCustomerManage().addMeasure();
    }

}