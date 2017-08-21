package com.yatou.automation.test;

import com.yatou.automation.store.CustomerManagePage;
import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
    public static WebDriver driver ;

    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.driver;
    }

    @Test(description = "新增预约量尺测试")
    public void testAddMeasure() throws InterruptedException {
        System.out.println("新增预约量尺测试driver:"+driver);
        Menu menu = PageFactory.initElements(driver, Menu.class);
        CustomerManagePage customerManagePage = menu.clickCustomerManage();
        customerManagePage.addMeasure();
    }

}