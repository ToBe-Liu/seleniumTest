package com.yatou.automation.test.store;

import com.yatou.automation.store.UserCenterPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店退出登录页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-30
 */

public class LogoutTest {
    public static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();//
    private UserCenterPage userCenterPage;

    @BeforeClass
    public void setupClass() {
        threadDriver = LoginTest.threadDriver;
        userCenterPage = new UserCenterPage(threadDriver.get());
    }

    @Test(description = "退出登录测试",groups = {"logout"})
    public void testLogout() throws InterruptedException {
        userCenterPage.logout();
    }

}