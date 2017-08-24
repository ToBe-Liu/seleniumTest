package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
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

    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.driver;
    }

    @Test(description = "沟通登记测试")
    public void testAddCommunicationRegister() throws InterruptedException {
        System.out.println("沟通登记测试driver:"+driver);
        Menu menu = PageFactory.initElements(driver, Menu.class);
        menu.clickCommunicationRegister().addCommunicationRegister();

    }

}