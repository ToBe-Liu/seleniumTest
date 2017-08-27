package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店沟通登记页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-24
 */
@Test(groups = {"communicationRegister"})
public class CommunicationRegisterPageTest {
    public static WebDriver driver ;
    public static Menu menu ;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommunicationRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.threadDriver.get();
        menu = PageFactory.initElements(driver, Menu.class);
    }

    @Test(description = "沟通登记测试")
    public void testAddCommunicationRegister() throws InterruptedException {
        logger.debug("沟通登记测试driver:"+driver);
        menu.clickCommunicationRegister().addCommunicationRegister();

    }

}