package com.yatou.automation.test.store;

import com.yatou.automation.store.StoreMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店用户管理页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-21
 */
public class CustomerManageTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    private StoreMenu storeMenu;

    @BeforeClass
    public void setupClass() {
        threadDriver = LoginTest.threadDriver;
        storeMenu = new StoreMenu(threadDriver.get());
    }

    @Test(description = "新增预约量尺测试",groups = {"customerManage"})
    public void testAddMeasure() throws InterruptedException {
        storeMenu.clickCustomerManage().addMeasure();
    }

}