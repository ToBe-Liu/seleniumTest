package com.yatou.automation.test.store;

import com.yatou.automation.store.StoreMenu;
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
    public static StoreMenu storeMenu;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContractRegisterPageTest.class);
    @BeforeClass
    public void setupClass() {
        driver = LoginTest.threadDriver.get();
        storeMenu = PageFactory.initElements(driver, StoreMenu.class);
    }

    @Test(description = "新增订单测试")
    public void testAddOrder() throws InterruptedException {
        logger.debug("新增订单测试driver:"+driver);
        storeMenu.clickContractRegister().addOrder();
    }
    @Test(description = "新增空间测试")
    public void testAddSpace() throws InterruptedException {
        logger.debug("新增空间测试driver:"+driver);
        storeMenu.clickContractRegister().addSpace();
    }
    @Test(description = "合同登记测试")
    public void testAddContractRegister() throws InterruptedException {
        logger.debug("合同登记测试driver:"+driver);
        storeMenu.clickContractRegister().addContractRegister();
    }


}