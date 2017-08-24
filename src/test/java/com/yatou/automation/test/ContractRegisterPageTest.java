package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 门店合同登记页面Test
 *
 * @author LiuXingHai
 * @date 2017-08-23
 */
@Test(groups = {"contractRegister"})
public class ContractRegisterPageTest {
    public static WebDriver driver ;

    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.driver;
    }

    @Test(description = "新增订单测试")
    public void testAddOrder() throws InterruptedException {
        System.out.println("新增订单测试driver:"+driver);
        Menu menu = PageFactory.initElements(driver, Menu.class);
        menu.clickContractRegister().addOrder();
    }
    @Test(description = "新增空间测试")
    public void testAddSpace() throws InterruptedException {
        System.out.println("新增空间测试driver:"+driver);
        Menu menu = PageFactory.initElements(driver, Menu.class);
        menu.clickContractRegister().addSpace();
    }
    @Test(description = "合同登记测试")
    public void testAddContractRegister() throws InterruptedException {
        System.out.println("合同登记测试driver:"+driver);
        Menu menu = PageFactory.initElements(driver, Menu.class);
        menu.clickContractRegister().addContractRegister();
    }


}