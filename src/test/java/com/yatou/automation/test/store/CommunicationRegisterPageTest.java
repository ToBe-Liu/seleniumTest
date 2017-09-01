package com.yatou.automation.test.store;

import com.yatou.automation.store.StoreMenu;
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
    public static StoreMenu storeMenu;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CommunicationRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginTest.threadDriver.get();
        storeMenu = PageFactory.initElements(driver, StoreMenu.class);
    }

    @Test(description = "沟通登记测试")
    public void testAddCommunicationRegister() throws InterruptedException {
        logger.debug("沟通登记测试driver:"+driver);
        storeMenu.clickCommunicationRegister().addCommunicationRegister();

    }

}