package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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
    public static WebDriver driver ;
    public static Menu menu ;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CustomerManagePageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.driver;
        menu = PageFactory.initElements(driver, Menu.class);
    }

    @Test(description = "新增预约量尺测试")
    public void testAddMeasure() throws InterruptedException {
        logger.debug("新增预约量尺测试driver:"+driver);
        menu.clickCustomerManage().addMeasure();
    }

}