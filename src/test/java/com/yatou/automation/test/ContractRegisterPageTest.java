package com.yatou.automation.test;

import com.yatou.automation.store.Menu;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;
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
    public static Menu menu ;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContractRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginPageTest.threadDriver.get();
        menu = PageFactory.initElements(driver, Menu.class);
    }

    @Test(description = "新增订单测试")
    public void testAddOrder() throws InterruptedException {
        logger.debug("新增订单测试driver:"+driver);
        menu.clickContractRegister().addOrder();
    }
    @Test(description = "新增空间测试")
    public void testAddSpace() throws InterruptedException {
        logger.debug("新增空间测试driver:"+driver);
        menu.clickContractRegister().addSpace();
    }
    @Test(description = "合同登记测试")
    public void testAddContractRegister() throws InterruptedException {
        logger.debug("合同登记测试driver:"+driver);
        menu.clickContractRegister().addContractRegister();
    }


}